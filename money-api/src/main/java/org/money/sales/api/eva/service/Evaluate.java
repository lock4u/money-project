package org.money.sales.api.eva.service;

import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * Created by Lee on 2018/10/27.
 */

@ProxyGen
@VertxGen
public interface Evaluate {

    String address = "1ad";

    void eva(JsonObject js, Handler<AsyncResult<JsonObject>> handler);

    @ProxyClose
    void close();
}
