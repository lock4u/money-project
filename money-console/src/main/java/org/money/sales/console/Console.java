package org.money.sales.console;


import io.netty.util.internal.StringUtil;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.*;
import io.vertx.reactivex.ext.web.sstore.LocalSessionStore;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.api.reactivex.user.service.UserService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lee on 2018/10/18.
 */
@Slf4j
public class Console extends AbstractVerticle {

    UserService us;

    @Override
    public void start(Future<Void> future) throws Exception {


        us = UserService.proxy(vertx, new DeliveryOptions());

        Router router = Router.router(vertx);
        router.route().failureHandler(ErrorHandler.create(true));


        router.route().handler(LoggerHandler.create());
        router.route().handler(TimeoutHandler.create(2000, 767));
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx, "money.session")));
        router.route().handler(BodyHandler.create());

        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
        allowHeaders.add("X-Token");

        router.route().handler(CorsHandler.create("*")
                .allowedHeaders(allowHeaders)
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST));

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
                .produces("application/json").handler(RoutingContext::next);


        router.route("/admin/user/login")
                .handler(this::login);

        router.get("/admin/user")
                .handler(this::user);
//
//        router.route("/admin/user/info")
//                .handler(this::user);


        router.route().handler(FaviconHandler.create());

        router.route().handler(StaticHandler.create());


        vertx.createHttpServer()
                .requestHandler(router::accept)
                .rxListen(8888)
                .doOnSuccess(httpServer -> log.info("HTTP server started on port {}", httpServer.actualPort()))
                .subscribe(httpServer -> future.complete(), future::fail);
    }

    private void login(RoutingContext rtx) {

        JsonObject body = rtx.getBodyAsJson();
        us.rxVerify(body.getString("name"), body.getString("password"))
                .subscribe(() -> out(rtx, null), rtx::fail);


    }

    private void user(RoutingContext rtx) {

        us.rxFindByName(rtx.request().getParam("name"))

                .subscribe(user -> out(rtx, user), rtx::fail);


    }


    protected <T> void out(RoutingContext rtx, T data) {
        if (data == null)
            rtx.response().end();
        else
            rtx.response().end(Json.encode(data));
    }

}
