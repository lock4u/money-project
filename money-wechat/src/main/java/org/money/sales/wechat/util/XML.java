package org.money.sales.wechat.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;

import java.io.IOException;

/**
 * Created by Lee on 2018/10/17.
 */
public class XML {

    static XmlMapper _xml = new XmlMapper();

    static {
//    _xml.configure(Xmlf)
    }

    public static <T> void parse(Buffer bf, Handler<AsyncResult<T>> handler) {

        try {
            T t = _xml.readValue(bf.toString("utf-8"), new TypeReference<T>() {
            });
            handler.handle(Future.succeededFuture(t));
        } catch (IOException e) {
            handler.handle(Future.failedFuture(e));
        }

    }
}
