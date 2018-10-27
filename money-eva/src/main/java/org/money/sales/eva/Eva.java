package org.money.sales.user;

import io.vertx.core.Future;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.api.user.service.UserService;
import org.money.sales.user.service.impl.EvaluateImpl;

/**
 * Created by Lee on 2018/10/17.
 */

@Slf4j
public class Eva extends AbstractVerticle {


    @Override
    public void start(Future<Void> future) throws Exception {


        UserService userService = EvaluateImpl.instance(vertx, config());


        new ServiceBinder(vertx.getDelegate())
                .setAddress(UserService.address)
                .register(UserService.class, userService)
                .completionHandler(future);

    }
}