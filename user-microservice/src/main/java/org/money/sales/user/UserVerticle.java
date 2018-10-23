package org.money.sales.user;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ServiceBinder;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.user.service.UserService;
import org.money.sales.user.service.impl.UserServiceImpl;

/**
 * Created by Lee on 2018/10/17.
 */

@Slf4j
public class UserVerticle extends AbstractVerticle {


    @Override
    public void start(Future<Void> future) throws Exception {


        UserService userService = UserServiceImpl.instance(vertx, config());

        new ServiceBinder(vertx)
                .setAddress(UserService.address)
                .register(UserService.class, userService)
                .completionHandler(future);

    }
}