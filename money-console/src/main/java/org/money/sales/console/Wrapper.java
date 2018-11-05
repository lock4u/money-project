package org.money.sales.console;

import io.vertx.core.Handler;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;


/**
 * Created by Lee on 2018/10/29.
 */
public class Wrapper {

    static Route wrap(Route route, Handler<RoutingContext>... handlers) {
        for (Handler handler : handlers) {
            route.handler(handler);
        }
        return route;
    }


}
