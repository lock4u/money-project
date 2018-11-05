package org.money.sales.admin.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.money.sales.api.admin.model.Boss;
import org.money.sales.api.admin.model.Broker;
import org.money.sales.api.admin.model.Customer;
import org.money.sales.basic.data.Supporter;

/**
 * Created by Lee on 2018/10/27.
 */
public class EmployeeManager extends Supporter {


    public EmployeeManager(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }

    public void update(Long uid, JsonObject args, Handler<AsyncResult<Void>> completer) {
        update(args.put("_id", uid), completer);

    }

    public void register(Broker broker, Handler<AsyncResult<Broker>> handler) {
        create(broker.toJson(), ar -> handler.handle(ar.map(json -> json.mapTo(Broker.class))));

    }

    public void register(Boss boss, Handler<AsyncResult<Boss>> handler) {
        create(boss.toJson(), ar -> handler.handle(ar.map(json -> json.mapTo(Boss.class))));
    }


    public void register(Customer customer, Handler<AsyncResult<Customer>> handler) {
        create(customer.toJson(), ar -> handler.handle(ar.map(json -> json.mapTo(Customer.class))));
    }

    /**
     * 解雇|辞职
     *
     * @param uid       雇员id
     * @param completer
     */
    public void dismiss(String uid, Handler<AsyncResult<Void>> completer) {
        drop(new JsonObject().put("id", uid), completer);
    }

    public void find(String uid, Handler<AsyncResult<JsonObject>> handler) {
        mongo.findOne(collection(), new JsonObject().put("_id", uid), null, handler);
    }

    @Override
    protected String collection() {
        return "employee";
    }
}
