package org.money.sales.wechat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ServiceBinder;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.wechat.service.TokenService;

/**
 * Created by Lee on 2018/10/17.
 */

@Slf4j
public class Schedule extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {


        TokenService tokenService = TokenService.create(vertx);

        new ServiceBinder(vertx)
                .setAddress("abc")
                .register(TokenService.class, tokenService)
                .completionHandler(startFuture);


    }

}
