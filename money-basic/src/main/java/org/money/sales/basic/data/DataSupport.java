package org.money.sales.basic.data;

import com.mysql.jdbc.Driver;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.jdbc.spi.impl.C3P0DataSourceProvider;
import io.vertx.ext.sql.ResultSet;

import java.util.List;

/**
 * Helper and wrapper class for JDBC repository services.
 *
 * @author Eric Zhao
 */
public abstract class DataSupport {

    protected final JDBCClient _SQL;


    @SuppressWarnings("unchecked")
    public DataSupport(Vertx vertx, JsonObject config) {

        JsonObject js = new JsonObject();
        js.put("url", "jdbc:mysql://111.231.103.112:3306/MONEY?characterEncoding=UTF-8&useSSL=false");
        js.put("driver_class", Driver.class.getName());
        js.put("provider_class", C3P0DataSourceProvider.class.getName());
        js.put("user", "money");
        js.put("password", "money1234");

        this._SQL = JDBCClient.createNonShared(vertx, js);

    }


    protected int calcPage(int page, int limit) {
        if (page <= 0)
            return 0;
        return limit * (page - 1);
    }


    protected Future<JsonObject> single(String sql, JsonArray args) {
        Future<JsonObject> future = Future.future();
        _SQL.queryWithParams(sql, args, ar -> {
            if (ar.failed()) {
                future.fail(ar.cause());
            } else {
                ResultSet resultSet = ar.result();
                if (resultSet == null) {
                    future.fail("No response founded");
                } else {
                    List<JsonObject> js = resultSet.getRows();
                    if (js == null || js.isEmpty()) {
                        future.complete(null);
                    } else if (js.size() > 0) {
                        future.complete(js.get(0));
                    } else {
                        future.fail("More than single result founded");
                    }
                }

            }
        });
        return future;
    }

}
