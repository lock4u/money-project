package org.money.sales.user.service.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import lombok.NonNull;
import org.money.sales.api.user.model.User;
import org.money.sales.api.user.service.UserService;
import org.money.sales.basic.data.JdbcRepositoryWrapper;

import java.util.function.Function;


/**
 * Created by Lee on 2018/10/22.
 */

public class UserServiceImpl extends JdbcRepositoryWrapper implements UserService {

    private UserServiceImpl(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public static UserService instance(Vertx vertx, JsonObject config) {
        return new UserServiceImpl(vertx, config);
    }


    @Override
    public UserService findByName(@NonNull String name, Handler<AsyncResult<User>> handler) {

        Future<ResultSet> future = Future.future();

        _SQL.queryWithParams("select * from \"MONEY_USER\" where name = ? ",
                new JsonArray().add(name),
                future.completer());

        future
                .map(toUser())
                .setHandler(handler);

        return this;
    }

    private Function<ResultSet, User> toUser() {
        return objects -> {
            if (objects == null) {
                return null;
            } else if (objects.getRows() == null || objects.getRows().isEmpty()) {
                return null;
            } else {
                return new User(objects.getRows().get(0));
            }
        };
    }


    @Override
    public UserService verify(String name, String password, Handler<AsyncResult<Void>> handler) {

        findByName(name, r -> {
            if (r.failed()) {
                handler.handle(Future.failedFuture(r.cause()));
            } else {
                User u = r.result();
                if (u != null) {
                    if (u.verify(password)) {
                        handler.handle(Future.succeededFuture());
                    } else {
                        handler.handle(Future.failedFuture(new RuntimeException("密码错误")));
                    }
                } else {
                    handler.handle(Future.failedFuture(new RuntimeException("No match user found")));
                }
            }
        });
        return this;
    }

}
