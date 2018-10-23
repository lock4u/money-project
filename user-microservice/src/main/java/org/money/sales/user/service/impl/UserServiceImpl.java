package org.money.sales.user.service.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import org.money.sales.user.model.User;
import org.money.sales.user.service.UserService;

import java.util.Optional;
import java.util.function.Function;


/**
 * Created by Lee on 2018/10/22.
 */
public class UserServiceImpl implements UserService {


    private AsyncSQLClient _sql;


    public UserServiceImpl(Vertx vertx, JsonObject config) {
        this._sql = MySQLClient.createNonShared(vertx, config);
    }


    public static UserService instance(Vertx vertx, JsonObject config) {
        return new UserServiceImpl(vertx, config);
    }


    @Override
    public void findByName(String name, Handler<AsyncResult<Optional<User>>> handler) {

        retrieve(name, "")
                .map(new Function<JsonArray, Optional<User>>() {
                    @Override
                    public Optional<User> apply(JsonArray objects) {
                        return null;
                    }
                }).setHandler(handler);

    }

    @Override
    public void verify(String name, String password, Handler<AsyncResult<Void>> handler) {

        Future<Void> future = Future.future();
        findByName(name, r -> {
            if (r.succeeded()) {
                if (r.result().isPresent()) {
                    User target = r.result().get();
                    if (target.verify(password)) {
                        future.complete();
                    } else {
                        future.fail("密码错误");
                    }
                } else {
                    future.fail("Not found");
                }
            } else {
                future.fail(r.cause());
            }
        });
        future.setHandler(handler);
    }

    protected <K> Future<JsonArray> retrieve(K param, String sql) {

        Future<JsonArray> future = Future.future();
        _sql.querySingleWithParams(sql, new JsonArray().add(param),
                future.completer());
        return future;
    }
}
