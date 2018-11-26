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
import java.util.function.Function;

/**
 * Helper and wrapper class for JDBC repository services.
 *
 * @author Eric Zhao
 */
@Slf4j
public abstract class MysqlSupporter {

    protected final AsyncSQLClient mysql;


    private Function<ResultSet, Optional<JsonObject>> single = resultSet -> {
        List<JsonObject> rows = resultSet.getRows();
        if (rows == null || rows.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(rows.get(0));
        }
    };


    @SuppressWarnings("unchecked")
    public MysqlSupporter(Vertx vertx, JsonObject config) {
        this.mysql = MySQLClient.createNonShared(vertx, config);
    }


    protected int calcPage(int page, int limit) {
        if (page <= 0)
            return 0;
        return limit * (page - 1);
    }


    protected Future<Optional<JsonObject>> single(String sql, JsonArray args) {
        Future<Optional<JsonObject>> f = Future.future();
        mysql.queryWithParams(sql, args, q -> f.handle(q.map(single)));
        return f;
    }


    private void close(SQLConnection conn, Handler<AsyncResult<Void>> handler) {
        log.debug("@SqlConnection[{}] closing =======>", conn);
        conn.close(close -> {
            handler.handle(close);
            log.debug("@SqlConnection[{}] closed =======>", close);
        });
    }


    private Future<Void> close(SQLConnection conn, boolean rollback) {
        Future f = Future.future();

        if (rollback) {

            conn.rollback(new Handler<AsyncResult<Void>>() {
                @Override
                public void handle(AsyncResult<Void> rollback) {
                    if (rollback.failed()) {
                        conn.close(new Handler<AsyncResult<Void>>() {
                            @Override
                            public void handle(AsyncResult<Void> close) {

                            }
                        });
                    }
                }
            });
        } else {

        }

        conn.close(new Handler<AsyncResult<Void>>() {
            @Override
            public void handle(AsyncResult<Void> close) {
                if (close.failed()) {
                    conn.rollback()
                    f.fail(close.cause());
                } else {
                    f.complete();
                }
            }
        });

    }


    public <T> void tx(Command<T> c, Handler<AsyncResult<T>> completer) {
        mysql.getConnection(getConnection -> {
            if (getConnection.failed()) {
                completer.handle(Future.failedFuture(getConnection.cause()));
            } else {
                SQLConnection sc = getConnection.result();
                sc.setAutoCommit(false, autoCommit -> {
                    if (autoCommit.failed()) {
                        completer.handle(Future.failedFuture(autoCommit.cause()));
                    } else {

                        Future co =
                                c.run(sc);
//                                .setHandler(new Handler<AsyncResult<T>>() {
//                                    @Override
//                                    public void handle(AsyncResult<T> exec) {
//                                        if (exec.failed()) {
//                                            sc.rollback(new Handler<AsyncResult<Void>>() {
//                                                @Override
//                                                public void handle(AsyncResult<Void> rollback) {
//                                                    if (rollback.failed()) {
//                                                        completer.handle(Future.failedFuture(rollback.cause()));
//                                                    } else {
//
//                                                    }
//                                                }
//                                            });
//
//                                        } else {
//                                            sc.close(new Handler<AsyncResult<Void>>() {
//                                                @Override
//                                                public void handle(AsyncResult<Void> close) {
//                                                    if (close.failed()) {
//                                                        completer.handle(Future.failedFuture(close.cause()));
//                                                    } else {
//                                                        completer.handle(exec);
//                                                    }
//                                                }
//                                            });
//                                        }
//                                    }
//                                });
                    }
                });
            }
        });

    }


    public interface Command<T> {

        Future<T> run(final SQLConnection connection);
    }


    public <T> Handler<AsyncResult<T>> invoke(SQLConnection connection, Future<T> future) {
        return result -> {
            if (result.failed()) {
                connection.rollback(rollback -> {
                    if (rollback.failed()) {
                        future.fail(rollback.cause());
                    } else {
                        connection.close(close -> {
                            if (close.failed()) {
                                future.fail(close.cause());
                            } else {
                                future.fail(result.cause());
                            }
                        });
                    }
                });
            } else {
                future.complete(result.result());
            }
        };
    }
}
