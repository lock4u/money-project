package org.money.sales.wechat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Booter extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {


        vertx.deployVerticle(Schedule.class.getName());

        vertx.deployVerticle(MoneyServer.class.getName(),
                new DeploymentOptions()
                        .setConfig(new JsonObject()));

//        vertx.rxDeployVerticle(Schedule.class.getName())
//                .flatMap(s -> vertx.rxDeployVerticle(MoneyServer.class.getName(),
//                        new DeploymentOptions()
//                                .setInstances(4)
//                                .setConfig(new JsonObject())))
//                .subscribe(o -> startFuture.complete(), startFuture::fail);
    }


}
