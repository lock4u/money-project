package org.money.sales.user.service;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import org.money.sales.user.model.User;

import java.util.Optional;


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

    void findByName(@NonNull String name, Handler<AsyncResult<Optional<User>>> handler);

    @Fluent
    void verify(@NonNull String name, @Nullable String password, Handler<AsyncResult<Void>> handler);
}
