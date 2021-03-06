package org.money.sales;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.reactivex.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.admin.Admin;
import org.money.sales.gateway.Gateway;

import java.io.Console;

@Slf4j
public class Starter extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {


        vertx.rxDeployVerticle(Admin.class.getName(), new DeploymentOptions().setWorker(true).setWorkerPoolName("USER-"))
                .flatMap(s -> vertx.rxDeployVerticle(Gateway.class.getName(), new DeploymentOptions().setInstances(2)))
                .flatMap(s -> vertx.rxDeployVerticle(Console.class.getName(), new DeploymentOptions().setInstances(2)))
                .toCompletable()
                .subscribe(startFuture::complete, startFuture::fail);


    }


}
