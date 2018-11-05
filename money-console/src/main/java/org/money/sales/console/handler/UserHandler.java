package org.money.sales.console.handler;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by Lee on 2018/10/29.
 */
public class UserHandler implements Handler<RoutingContext> {


    private UserHandler(Vertx vertx) {

    }

    public static UserHandler create(Vertx vertx) {
        return new UserHandler(vertx);
    }

    @Override
    public void handle(RoutingContext rtx) {

        JsonObject mock = new JsonObject();

        mock.put("code", 20000)
                .put("message", "ok")
                .put("data", new JsonObject()
                        .put("roles", new JsonArray().add("admin"))
                        .put("name", "管理元"));

        rtx.response().end(mock.encode());
    }


}
