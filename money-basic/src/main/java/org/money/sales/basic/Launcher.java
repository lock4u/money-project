package org.money.sales.basic;


import io.vertx.core.VertxOptions;

/**
 * Created by Lee on 2018/10/18.
 */
public class Launcher extends io.vertx.core.Launcher {


    public static void main(String[] args) {
        System.setProperty("vertx.disableFileCaching", "true");
        new Launcher().dispatch(args);
    }

    @Override
    public void beforeStartingVertx(VertxOptions options) {
        options.setPreferNativeTransport(false);
    }

}
