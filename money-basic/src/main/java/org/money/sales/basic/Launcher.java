package org.money.sales.basic;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.Json;
import org.money.sales.basic.json.InstantDeserializer;

import java.time.Instant;

/**
 * Created by Lee on 2018/10/18.
 */
public class Launcher extends io.vertx.core.Launcher {


    public static void main(String[] args) {
        System.setProperty("vertx.disableFileCaching", "true");
        customizer();
        new Launcher().dispatch(args);
    }

    public static void customizer() {
        SimpleModule m = new SimpleModule();
        m.addDeserializer(Instant.class, InstantDeserializer.INTANCE);
        Json.mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        Json.mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Json.prettyMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        Json.prettyMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Json.prettyMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.DEFAULT);
        Json.mapper.registerModule(m);
        Json.prettyMapper.registerModule(m);
        Json.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Json.prettyMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Json.mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        Json.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Json.prettyMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        Json.prettyMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void beforeStartingVertx(VertxOptions options) {
        options.setPreferNativeTransport(true);
    }


}
