package org.money.sales.user.service.impl;

import com.google.common.hash.Hashing;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLConnection;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.money.sales.api.user.model.User;
import org.money.sales.api.user.service.UserService;
import org.money.sales.basic.data.DataSupport;

import java.time.Instant;

import static com.google.common.base.Charsets.UTF_8;


/**
 * Created by Lee on 2018/10/22.
 */
@Slf4j
public class UserServiceImpl extends DataSupport implements UserService {


    String C_USER = "INSERT INTO USER (name,hash,phone,salt,create_time,update_time) VALUES(?,?,?,?,?,?)";


    private UserServiceImpl(Vertx vertx, JsonObject config) {

        super(vertx, config);
    }

    public static UserService instance(Vertx vertx, JsonObject config) {
        return new UserServiceImpl(vertx, config);
    }


    @Override
    public UserService findByName(@NonNull String name, Handler<AsyncResult<User>> handler) {
        log.info("@findByName===> {}", name);

        single("SELECT * FROM MONEY_USER where name =?", new JsonArray().add(name))
                .map(User::new).setHandler(handler);
        return this;
    }


    /**
     * @param name
     * @param cert
     * @param handler
     * @return
     */
    @Override
    public UserService create(@NonNull String name, @NonNull String cert, Handler<AsyncResult<Void>> handler) {

        _SQL.getConnection(conn -> {
            if (conn.failed()) {
                handler.handle(Future.failedFuture(conn.cause()));
            } else {

                SQLConnection sc = conn.result();
                String slat = salt();
                String p = hash(cert, slat);

                sc.updateWithParams(C_USER, new JsonArray()
                                .add(name)
                                .add(p)
                                .add("18662207727")
                                .add(slat)
                                .add(Instant.now().toEpochMilli())
                                .add(Instant.now().toEpochMilli()),
                        update -> {
                            if (update.failed()) {
                                sc.close(close -> {
                                    if (close.failed()) {
                                        handler.handle(Future.failedFuture(close.cause()));
                                    } else {
                                        handler.handle(Future.failedFuture(update.cause()));
                                    }
                                });
                            } else {
                                handler.handle(Future.succeededFuture());
                            }
                        });
            }
        });
        return this;
    }

    @NonNull
    private String salt() {
        return RandomStringUtils.randomAlphabetic(32);
    }

    @NonNull
    private String hash(String cert, String slat) {
        return Hashing.adler32()
                .newHasher()
                .putString(slat, UTF_8)
                .putString(cert, UTF_8)
                .hash()
                .toString();
    }


    @Override
    public UserService verify(String name, String cert, Handler<AsyncResult<Void>> handler) {

        single("SELECT * FROM MONEY_USER where name =?", new JsonArray().add(name))
                .compose(json -> {
                    Future<Void> f = Future.future();
                    if (json == null) {
                        f.fail("No User named [" + name + "] matched");
                    } else {
                        User user = new User(json);
                        if (validate(user.getPassword(), cert, user.getSalt())) {
                            f.complete();
                        } else {
                            f.fail("密码错误");
                        }
                    }
                    return f;
                }).setHandler(handler);

        return this;

    }

    private boolean validate(String password, String cert, String salt) {
        return hash(cert, salt).equals(password);
    }


}
