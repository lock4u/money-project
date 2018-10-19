package org.money.sales.wechat;

import io.netty.util.internal.StringUtil;
import io.vertx.core.*;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.serviceproxy.ServiceProxyBuilder;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.wechat.service.TokenService;
import org.money.sales.wechat.util.XML;

import java.util.HashMap;

/**
 * Created by Lee on 2018/10/14.
 */

@Slf4j
public class MoneyServer extends AbstractVerticle {

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
        System.setProperty("vertx.disableFileCaching", "true");
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {


        ServiceProxyBuilder builder = new ServiceProxyBuilder(vertx).setAddress("abc");

        TokenService ts = builder.build(TokenService.class);

        Router router = Router.router(getVertx());


        router.route().handler(TimeoutHandler.create(2000, 767));

        router.route().handler(LoggerHandler.create());
        router.route().handler(BodyHandler.create());
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx, "shopping.user.session")));


        router.get("/wx").handler(rtx -> {

            String signature = rtx.request().getParam("signature");
            String timestamp = rtx.request().getParam("timestamp");
            String nonce = rtx.request().getParam("nonce");
            String echostr = rtx.request().getParam("echostr");
            String token = rtx.request().getParam("signature");

            log.info("signature {}", rtx.request().params());

            if (StringUtil.isNullOrEmpty(echostr)) {
                rtx.response().end("满shi钱来");
            } else
                rtx.response().end(echostr);
        });

        router.route("/abc")
                .handler(this::toXml);

        router.route("/aaa")
                .handler(rtx -> ts.refresh(r -> {
                    if (r.succeeded()) {
                        rtx.response().end(r.result().toString());
                    } else {
                        rtx.fail(r.cause());
                    }
                }));


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
