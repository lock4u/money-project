package org.money.sales.wechat;


import io.vertx.core.VertxOptions;

/**
 * Created by Lee on 2018/10/18.
 */
public class Launcher extends io.vertx.core.Launcher {


    public static void main(String[] args) {
        new Launcher().dispatch(args);
    }

    @Override
    public void beforeStartingVertx(VertxOptions options) {
        options.setPreferNativeTransport(true);
    }

}
