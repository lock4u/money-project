package org.money.sales.gateway;

import io.netty.util.internal.StringUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.serviceproxy.ServiceProxyBuilder;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.api.user.service.UserService;
import org.money.sales.gateway.model.Result;
import org.money.sales.gateway.util.XML;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lee on 2018/10/14.
 */

@Slf4j
public class Gateway extends AbstractVerticle {


    UserService us;

    @Override
    public void start(Future<Void> startFuture) throws Exception {




        Router router = Router.router(getVertx());



        router.route().handler(LoggerHandler.create());
        router.route().handler(TimeoutHandler.create(2000, 767));
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx, "money.session")));
        router.route().handler(BodyHandler.create());


        router.route().handler(StaticHandler.create());
        router.route().handler(ErrorHandler.create());

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(Integer.getInteger("port", 80), http -> {
                    if (http.succeeded()) {
                        startFuture.complete();
                        log.info("HTTP server started on port {} {}", http.result().actualPort(),deploymentID());
                    } else {
                        startFuture.fail(http.cause());
                    }
                });
    }

    private void user(RoutingContext rtx) {
        JsonObject js = new JsonObject();
        js.put("token", "admin");
        js.put("name", "管理员");
        js.put("roles", new JsonArray().add("admin"));
        js.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        rtx.response().end(Result.complete(js).encode());
    }


    private void toXml(RoutingContext rtx) {

        XML.parse(rtx.getBody(), (Handler<AsyncResult<HashMap>>) result -> {
            if (result.failed()) {
                rtx.response().setStatusCode(500);
                rtx.response().end(result.cause().getMessage());
            } else {
                rtx.response().end(Json.encode(result.result()));
            }
        });
    }




}
