package org.money.sales.wechat.service;

import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.money.sales.wechat.model.Token;


/**
 * Created by Lee on 2018/10/18.
 */
@ProxyGen
@VertxGen
public interface TokenService {


    void refresh(Handler<AsyncResult<Token>> handler);

    static TokenService create(Vertx vertx) {
        return new TokenServiceImpl(vertx);
    }


    @ProxyClose
    void close();
}
