package org.money.sales.basic.data;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.PostgreSQLClient;

/**
 * Helper and wrapper class for JDBC repository services.
 *
 * @author Eric Zhao
 */
public class JdbcRepositoryWrapper {

    protected final AsyncSQLClient _SQL;

    public JdbcRepositoryWrapper(Vertx vertx, JsonObject config) {

        JsonObject js = new JsonObject();
        js.put("host","localhost");
        js.put("port",5432);
        js.put("maxPoolSize",30);
        js.put("username","vertx");
        js.put("password","vertx");
        js.put("database","money");
        js.put("charset","UTF-8");
        js.put("queryTimeout",10000);
//        config.put("sslMode","");
//        config.put("sslRootCert","");
        this._SQL = PostgreSQLClient.createNonShared(vertx, js);
    }


    protected int calcPage(int page, int limit) {
        if (page <= 0)
            return 0;
        return limit * (page - 1);
    }

//    protected Future<List<JsonObject>> retrieveByPage(int page, int limit, String sql) {
//        JsonArray params = new JsonArray().add(calcPage(page, limit)).add(limit);
//        return getConnection().compose(connection -> {
//            Future<List<JsonObject>> future = Future.future();
//            connection.queryWithParams(sql, params, r -> {
//                if (r.succeeded()) {
//                    future.complete(r.result().getRows());
//                } else {
//                    future.fail(r.cause());
//                }
//                connection.close();
//            });
//            return future;
//        });
//    }


}
