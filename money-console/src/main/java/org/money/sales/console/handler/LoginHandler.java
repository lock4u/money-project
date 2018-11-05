package org.money.sales.console.handler;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by Lee on 2018/10/29.
 */
public class LoginHandler implements Handler<RoutingContext> {


    private LoginHandler(Vertx vertx) {

    }

    public static LoginHandler create(Vertx vertx) {
        return new LoginHandler(vertx);
    }

    @Override
    public void handle(RoutingContext rtx) {

        JsonObject mock = new JsonObject();

        mock.put("code", 20000)
                .put("message", "ok")
                .put("data", new JsonObject().put("token", "admin"));

        rtx.response().end(mock.encode());
    }


}
