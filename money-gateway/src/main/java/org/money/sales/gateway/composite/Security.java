package org.money.sales.gateway.composite;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ServiceProxyBuilder;
import org.money.sales.api.admin.service.EmployeeManager;

/**
 * Created by Lee on 2018/10/24.
 */
public class Security {

    EmployeeManager us;

    private Security(Vertx vertx) {
        us = new ServiceProxyBuilder(vertx)
                .setAddress(EmployeeManager.address)
                .build(EmployeeManager.class);
    }

    public static Security create(Vertx vertx) {
        return new Security(vertx);
    }

    public void login(String username, String password, Handler<AsyncResult<Void>> handler) {
        us.verify(username, password, handler);
    }

}
