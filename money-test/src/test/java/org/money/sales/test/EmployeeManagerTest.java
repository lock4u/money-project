package org.money.sales.test;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.money.sales.admin.service.EmployeeManager;
import org.money.sales.api.admin.model.Boss;
import org.money.sales.api.admin.model.Wechat;
import org.money.sales.basic.Launcher;

/**
 * EmployeeManager Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11/03/2018</pre>
 */
@RunWith(VertxUnitRunner.class)
public class EmployeeManagerTest {


    static {
        Launcher.customizer();
    }

    Vertx vertx;

    EmployeeManager employeeManager;

    HttpServer server;

    @Before
    public void before(TestContext context) {
        vertx = Vertx.vertx();

        // Register the context exception handler
        vertx.exceptionHandler(context.exceptionHandler());

        employeeManager = new EmployeeManager(vertx, new JsonObject());
    }

    /**
     * Method: update(Long uid, JsonObject args, Handler<AsyncResult<Void>> completer)
     */
    @Test
    public void testUpdate(TestContext context) throws Exception {
        Async async = context.async();

        Boss boss = Boss.builder()
                .contact(new Wechat().nickname("xxx").portrait("xxxx"))
                .build();

        employeeManager.register(boss, new Handler<AsyncResult<Boss>>() {
            @Override
            public void handle(AsyncResult<Boss> boss) {
                if (boss.failed()) {
                    boss.cause().printStackTrace();
                    async.complete();
                } else {
                    System.out.println(boss.result().toJson());
                    async.complete();
                }

            }
        });
    }

    /**
     * Method: register(Broker broker, Handler<AsyncResult<Broker>> handler)
     */
    public void testRegisterForBrokerHandler() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: register(Boss boss, Handler<AsyncResult<Boss>> handler)
     */
    public void testRegisterForBossHandler() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: register(Customer customer, Handler<AsyncResult<Customer>> handler)
     */
    public void testRegisterForCustomerHandler() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: dismiss(Long uid, Handler<AsyncResult<Void>> completer)
     */
    public void testDismiss() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: collection()
     */
    public void testCollection() throws Exception {
//TODO: Test goes here... 
    }


} 
