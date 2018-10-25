package org.money.sales.api.user.service;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import org.money.sales.api.user.model.User;


/**
 * Created by Lee on 2018/10/22.
 */
@VertxGen
@ProxyGen
public interface UserService {


    String address = "service_user";


    static UserService proxy(Vertx vertx, DeliveryOptions options) {
        return new UserServiceVertxEBProxy(vertx, address, options);
    }

    @Fluent
    UserService findByName(String name, Handler<AsyncResult<User>> handler);

    @Fluent
    UserService verify(String name, String password, Handler<AsyncResult<Void>> handler);
}
