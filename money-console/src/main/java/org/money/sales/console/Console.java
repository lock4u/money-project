package org.money.sales.console;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.api.admin.model.Contact;
import org.money.sales.console.handler.LoginHandler;
import org.money.sales.console.handler.UserHandler;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lee on 2018/10/18.
 */
@Slf4j
public class Console extends AbstractVerticle {


    @Override
    public void start(Future<Void> future) throws Exception {


        JsonObject js = new JsonObject();

        js.put("number", "11111");
        js.put("now", Instant.now());

        System.out.println(js.encode());
        System.out.println(Json.encode(new Contact()));
        System.out.println(js.mapTo(Contact.class));


        Router router = Router.router(vertx);

        router.route().failureHandler(ErrorHandler.create(true));

        Wrapper.wrap(router.route(), LoggerHandler.create(), TimeoutHandler.create(2000), CookieHandler.create(), SessionHandler.create(LocalSessionStore.create(vertx)));

//        SimpleAccountRealm realm = new SimpleAccountRealm();
//        realm.addAccount("admin", "admin", "admin");
//        ShiroAuth authProvider = ShiroAuth.create(vertx, realm);
//        router.route().handler(UserSessionHandler.create(authProvider));
//
//        router.route().handler(new AuthHandlerImpl(authProvider) {
//            @Override
//            public void parseCredentials(RoutingContext context, Handler<AsyncResult<JsonObject>> handler) {
//                handler.handle(Future.succeededFuture());
//            }
//
//            @Override
//            public void authorize(User user, Handler<AsyncResult<Void>> handler) {
//                handler.handle(Future.succeededFuture());
//            }
//        });

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

        router.post("/admin/user/login")
                .handler(LoginHandler.create(vertx));

        router.get("/admin/user/info")
                .handler(UserHandler.create(vertx));


        router.route().handler(FaviconHandler.create());

        router.route().handler(StaticHandler.create());


        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(Integer.getInteger("port", 8080), ar -> future.handle(ar.map(httpServer -> null)));

    }


}
