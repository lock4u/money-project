package org.money.sales.token;

import io.reactivex.functions.Consumer;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Lee on 2018/10/17.
 */

@Slf4j
public class TokenVerticle extends AbstractVerticle {


    private static final String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx71e24f97f3e79c03&secret=c8098175624fff6af146b39f53224508";


    private WebClient _cli;

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        _cli = WebClient.create(vertx, new WebClientOptions()
                .setTcpNoDelay(true))

        ;
//        _cli.getAbs(URL)
//                .rxSend()
//                .filter(response -> response.statusCode() == 200)
//                .map(response -> response.bodyAsJson(Token.class))
//                .subscribe(token -> {
//                    vertx.sharedData()
//                            .<String, Token>getLocalMap("access_token")
//                            .put("token", token);
//                    startFuture.complete();
//                }, startFuture::fail);


        _cli.getAbs(URL)
                .rxSend()
                .filter(r -> r.statusCode() == 200)
                .map(r -> r.bodyAsJsonObject())
                .subscribe(new Consumer<JsonObject>() {
                    @Override
                    public void accept(JsonObject jsonObject) throws Exception {
                        log.info("123123123123123123$%^HJKL {}", jsonObject);
                        startFuture.complete();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        startFuture.fail(throwable);
                    }
                });
//        vertx.sharedData().
//                <String, Token>rxGetAsyncMap("assess_token")
//                .flatMapCompletable(as -> _cli.getAbs(URL)
//                        .timeout(500)
//                        .rxSend()
//                        .map(response -> response.bodyAsJson(Token.class))
//                        .flatMapCompletable(token -> as.rxPut("token", token)))
//                .doOnComplete(() -> log.info("#########"))
//                .subscribe(startFuture::complete, startFuture::fail);

    }
}