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
import lombok.extern.slf4j.Slf4j;
import org.money.sales.gateway.model.Result;
import org.money.sales.gateway.util.XML;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lee on 2018/10/14.
 */

@Slf4j
public class MoneyServer extends AbstractVerticle {


    @Override
    public void start(Future<Void> startFuture) throws Exception {


        Router router = Router.router(getVertx());


        router.route().handler(TimeoutHandler.create(2000, 767));

        router.route().handler(LoggerHandler.create());
        router.route().handler(BodyHandler.create());
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx, "money.session")));

        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
        allowHeaders.add("X-Token");


        router.route("/admin/*")
                .handler(CorsHandler.create("*")
                        .allowedMethod(HttpMethod.GET)
                        .allowedMethod(HttpMethod.POST)
                        .allowedMethod(HttpMethod.PUT)
                        .allowedMethod(HttpMethod.DELETE)
                        .allowedMethod(HttpMethod.PATCH)
                        .allowedMethod(HttpMethod.OPTIONS)
//                        .allowCredentials(true)
                        .allowedHeaders(allowHeaders));


        router.get("/wx").handler(rtx -> {

            String signature = rtx.request().getParam("signature");
            String timestamp = rtx.request().getParam("timestamp");
            String nonce = rtx.request().getParam("nonce");
            String echostr = rtx.request().getParam("echostr");
            String token = rtx.request().getParam("signature");

            if (StringUtil.isNullOrEmpty(echostr)) {
                rtx.response().end("满shi钱来");
            } else
                rtx.response().end(echostr);
        });


        router.route("/admin/*")
                .produces("application/json");


        router.post("/admin/user/login")
                .handler(this::login);

        router.get("/admin/user/info")
                .handler(this::user);


        router.route().handler(StaticHandler.create());
        router.route().handler(ErrorHandler.create());

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(Integer.getInteger("port", 80), http -> {
                    if (http.succeeded()) {
                        startFuture.complete();
                        log.info("HTTP server started on port {}", http.result().actualPort());
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


    private void login(RoutingContext rtx) {

        JsonObject token = new JsonObject();
        token.put("token", "admin");
        JsonObject result = new JsonObject()
                .put("code", 20000)
                .put("message", "OK")
                .put("data", token);
        rtx.response()
                .end(result.encode());
    }

}
