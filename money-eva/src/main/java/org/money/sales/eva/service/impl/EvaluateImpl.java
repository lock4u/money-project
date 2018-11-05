package org.money.sales.eva.service.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.money.sales.api.eva.service.Evaluate;
import org.money.sales.basic.data.Supporter;


/**
 * Created by Lee on 2018/10/22.
 */
@Slf4j
public class EvaluateImpl extends Supporter implements Evaluate {

    private EvaluateImpl(Vertx vertx, JsonObject config) {

        super(vertx, config);
    }

    @Override
    protected String collection() {
        return "evaluate";
    }

    @Override
    public void eva(JsonObject js, Handler<AsyncResult<JsonObject>> handler) {

    }

    @Override
    public void close() {

    }
}