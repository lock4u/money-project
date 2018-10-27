package org.money.sales.eva.visitor;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.money.sales.basic.data.Supporter;

/**
 * Created by Lee on 2018/10/27.
 */
public class EvaDataVisitor extends Supporter {

    public EvaDataVisitor(Vertx vertx, JsonObject config) {
        super(vertx, config);
    }


}
