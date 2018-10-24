package org.money.sales.gateway.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.vertx.core.json.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by Lee on 2018/9/29.
 */
@Getter
@ToString
@EqualsAndHashCode
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class Result<T> extends JsonObject {

    private static final int OK = 20000;
    private static final int FAIL = 5000;


    private Result(int ret, String msg, T data) {
        put("code", ret);
        put("message", msg);
        put("data", data);
    }

    public static <T> Result<T> fail(int ret, String msg) {
        return new Result<>(ret, msg, null);
    }

    public static <T> Result<T> fail() {
        return fail(FAIL, "fail");
    }

    public static <T> Result<T> complete() {
        return new Result<>(OK, "done", null);
    }

    public static <T> Result<T> complete(T data) {
        return new Result<>(OK, "complete", data);
    }

    public static void main(String[] args) {
        System.out.println(Result.complete().encode());
    }


}
