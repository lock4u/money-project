package org.money.sales.wechat.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.wechat.model.Token;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;


/**
 * Created by Lee on 2018/10/18.
 */

@Slf4j
public class TokenServiceImpl implements TokenService {


    Vertx vertx;

    private WebClient _client;


    private AtomicReference<Token> reference = new AtomicReference<>();


    public TokenServiceImpl(Vertx vertx) {
        this.vertx = vertx;

        _client = WebClient.create(vertx, new WebClientOptions()
                .setMaxPoolSize(3));

//        vertx.setPeriodic(1000, seq -> _client.getAbs("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx71e24f97f3e79c03&secret=c8098175624fff6af146b39f53224508")
//                .send(r -> {
//                    if (r.succeeded()) {
//                        Token t = r.result().bodyAsJson(Token.class);
//                        if (t.isSuccess()) {
//                            reference.set(t);
//
//                            log.info("access_token {}", t);
//                        } else {
//                            log.error("{}", t);
//                        }
//                    } else {
//                        log.error("xxxx", r.cause());
//                    }
//                }));

    }

    @Override
    public void refresh(Handler<AsyncResult<Token>> handler) {

        _client.getAbs("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx71e24f97f3e79c03&secret=c8098175624fff6af146b39f53224508")
                .send(r -> handler.handle(r.map(new Function<HttpResponse<Buffer>, Token>() {
                            @Override
                            public Token apply(HttpResponse<Buffer> response) {
                                return new Token(response.bodyAsJsonObject());
                            }
                        })));
    }

    @Override
    public void close() {
        _client.close();
    }
}
