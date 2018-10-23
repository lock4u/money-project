package org.money.sales.gateway;

import io.vertx.core.Future;
import io.vertx.reactivex.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.token.TokenVerticle;

@Slf4j
public class Bootstrap extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {


        vertx.rxDeployVerticle(TokenVerticle.class.getName())
                .flatMap(s -> vertx.rxDeployVerticle(MoneyServer.class.getName()))
                .toCompletable()
                .subscribe(startFuture::complete, startFuture::fail);

    }


}
