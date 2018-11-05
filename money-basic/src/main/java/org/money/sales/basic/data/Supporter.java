package org.money.sales.basic.data;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
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

        JsonObject c = new JsonObject();
        c.put("connection_string", "mongodb://111.231.103.112:27017");
        c.put("db_name", "test");

        this.mongo = MongoClient.createNonShared(vertx, c);
    }


    protected int calcPage(int page, int limit) {
        if (page <= 0)
            return 0;
        return limit * (page - 1);
    }


    protected Future<JsonObject> single(JsonObject args) {
        Future<JsonObject> future = Future.future();
        single(args, future.completer());
        return future;
    }

    protected void single(JsonObject args, Handler<AsyncResult<JsonObject>> handler) {
        mongo.findOne(collection(), args, null, handler);
    }

    protected void create(JsonObject args, Handler<AsyncResult<JsonObject>> handler) {

        Future<String> execute = Future.future();
        mongo.insert(collection(), args, execute);
        execute.compose(s -> {
            Future<JsonObject> q = Future.future();
            mongo.findOne(collection(), new JsonObject().put("_id", s), null, q.completer());
            return q;
        }).setHandler(handler);
    }


    protected void update(JsonObject args, Handler<AsyncResult<Void>> completer) {

        mongo.save(collection(), args, ar -> completer.handle(ar.map(id -> null)));
    }

    protected void drop(JsonObject args, Handler<AsyncResult<Void>> handler) {
        mongo.findOneAndDelete(collection(), args, ar -> handler.handle(ar.map(a -> (Void) null)));
    }

    protected abstract String collection();

}
