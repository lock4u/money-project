package org.money.sales.console.handler;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import io.vertx.serviceproxy.ServiceProxyBuilder;
import org.money.sales.api.eva.service.Evaluate;

/**
 * Created by Lee on 2018/10/29.
 */
public class EvaluateHandler implements Handler<RoutingContext> {

    private final Evaluate evaluate;

    private EvaluateHandler(Evaluate evaluate) {
        this.evaluate = evaluate;
    }

    public static EvaluateHandler create(Vertx vertx) {
        return new EvaluateHandler(new ServiceProxyBuilder(vertx)
                .setAddress(Evaluate.address)
                .build(Evaluate.class));
    }

    @Override
    public void handle(RoutingContext rtx) {

        evaluate.eva(rtx.getBodyAsJson(), ar -> {
            if (ar.failed()) {
                rtx.fail(ar.cause());
            } else {
                rtx.response().end(ar.result().encode());
            }
        });
    }


}
