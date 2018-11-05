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
import org.money.sales.api.admin.model.Boss;
import org.money.sales.api.admin.model.Wechat;
import org.money.sales.basic.Launcher;
import org.money.sales.basic.data.MysqlSupporter;
import org.money.sales.basic.data.Supporter;

/**
 * EmployeeManager Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11/03/2018</pre>
 */
@RunWith(VertxUnitRunner.class)
public class SupportTest {


    static {
        Launcher.customizer();
    }

    Vertx vertx;

    MysqlSupporter supporter;


    @Before
    public void before(TestContext context) {
        vertx = Vertx.vertx();

        // Register the context exception handler
        vertx.exceptionHandler(context.exceptionHandler());

        supporter = new MysqlSupporter(vertx, new JsonObject()) {
        };
    }

    /**
     * Method: update(Long uid, JsonObject args, Handler<AsyncResult<Void>> completer)
     */
    @Test
    public void testTx(TestContext context) throws Exception {
        Async async = context.async();

        Boss boss = Boss.builder()
                .contact(new Wechat().nickname("xxx").portrait("xxxx"))
                .build();

        supporter.tx();
    }


} 
