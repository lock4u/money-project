package org.money.sales.basic.data;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

/**
 * Helper and wrapper class for JDBC repository services.
 *
 * @author Eric Zhao
 */
@Slf4j
public abstract class MysqlSupporter {

    protected final AsyncSQLClient mysql;


    @SuppressWarnings("unchecked")
    public MysqlSupporter(Vertx vertx, JsonObject config) {
        this.mysql = MySQLClient.createNonShared(vertx, config);
    }


    protected int calcPage(int page, int limit) {
        if (page <= 0)
            return 0;
        return limit * (page - 1);
    }


    protected void single(String sql, JsonArray args, Handler<AsyncResult<Optional<JsonObject>>> handler) {

        getConnection(conn -> {
            if (conn.failed()) {
                handler.handle(Future.failedFuture(conn.cause()));
            } else {
                SQLConnection sc = conn.result();
                log.debug("@execute command [query],sql [{}] =======>", sql);
                sc.queryWithParams(sql, args, q -> {
                    if (q.failed()) {
                        close(sc, close -> {
                            if (close.failed()) {
                                handler.handle(Future.failedFuture(close.cause()));
                            } else {
                                handler.handle(Future.failedFuture(q.cause()));
                            }
                        });
                    } else {
                        ResultSet rs = q.result();
                        List<JsonObject> rows = rs.getRows();
                        if (rows == null || rows.isEmpty()) {
                            handler.handle(Future.succeededFuture(empty()));
                        } else {
                            handler.handle(Future.succeededFuture(Optional.of(rows.get(0))));
                        }
                    }
                });
            }
        });
    }


    private void getConnection(Handler<AsyncResult<SQLConnection>> handler) {
        mysql.getConnection(conn -> {
            log.debug("@getConnection =======> {}", conn);
            handler.handle(conn);
        });
    }

    private void close(SQLConnection conn, Handler<AsyncResult<Void>> handler) {
        log.debug("@SqlConnection[{}] closing =======>", conn);
        conn.close(close -> {
            handler.handle(close);
            log.debug("@SqlConnection[{}] closed =======>", close);
        });
    }

    public  <T> void tx(Handler<AsyncResult<T>> handler, Command<T> c) {

        Future<SQLConnection> fc = Future.future();

        mysql.getConnection(conn -> {
            if (conn.failed()) {
                fc.fail(conn.cause());
            } else {
                SQLConnection sc = conn.result();
                sc.setAutoCommit(false, auto -> {
                    if (auto.failed()) {
                        fc.fail(auto.cause());
                    } else {
                        Future<T> exec = c.exec(sc);
                        exec.setHandler(cc -> {
                            if (cc.failed()) {
                                sc.rollback(rollback -> {
                                    if (rollback.failed()) {
                                        handler.handle(Future.failedFuture(rollback.cause()));
                                    } else {
                                        sc.close(close -> {
                                            if (close.failed()) {
                                                handler.handle(Future.failedFuture(close.cause()));
                                            } else {
                                                handler.handle(Future.failedFuture(cc.cause()));
                                            }
                                        });
                                    }
                                });
                            } else {
                                handler.handle(cc);
                            }
                        });
                    }
                });
            }
        });

    }


    public static interface Command<T> {

        Future<T> exec(SQLConnection sql);
    }

}
