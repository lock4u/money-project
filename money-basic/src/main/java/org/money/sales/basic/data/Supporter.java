package org.money.sales.basic.data;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

/**
 * Helper and wrapper class for JDBC repository services.
 *
 * @author Eric Zhao
 */
public abstract class Supporter {

    protected final MongoClient mongo;


    @SuppressWarnings("unchecked")
    public Supporter(Vertx vertx, JsonObject config) {

        JsonObject js = new JsonObject();
        js.put("url", "jdbc:mysql://111.231.103.112:27017/MONEY?characterEncoding=UTF-8&useSSL=false");
        js.put("user", "money");
        js.put("password", "money1234");


        JsonObject c = new JsonObject();
        c.put("connection_string", "mongodb://111.231.103.112:27017");
        c.put("db_name", "test");

        this.mongo = MongoClient.createNonShared(vertx, js);
    }


    protected int calcPage(int page, int limit) {
        if (page <= 0)
            return 0;
        return limit * (page - 1);
    }


    protected Future<JsonObject> single(JsonObject args) {
        Future<JsonObject> future = Future.future();
        mongo.findOne(collection(), args, args, future.completer());
        return future;
    }



    protected abstract String collection();

}
