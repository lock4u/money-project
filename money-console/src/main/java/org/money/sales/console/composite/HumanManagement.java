package org.money.sales.console.composite;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import org.money.sales.api.admin.model.Broker;
import org.money.sales.api.admin.service.EmployeeManager;

import java.util.List;

/**
 * Created by Lee on 2018/10/29.
 */
public class HumanManagement {

    EmployeeManager admin;


    /**
     * 经纪人
     *
     * @param broker
     * @param handler
     */
    public void hire(Broker broker, Handler<AsyncResult<Broker>> handler) {
        admin.register(broker.toJson(), ar -> handler.handle(ar.map(js -> new Broker())));
    }


    public void list(JsonObject params,Handler<AsyncResult<List<Broker>>> handler) {
//        admin.list(params, new Handler<AsyncResult<List<Employee>>>() {
//            @Override
//            public void handle(AsyncResult<List<Employee>> event) {
//
//            }
//        });
    }

}
