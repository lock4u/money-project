package org.money.sales.gateway.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.vertx.core.json.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Lee on 2018/9/29.
 */
@Getter
@ToString
@EqualsAndHashCode
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class Result<T> implements Serializable {

    private int code = 0;

    private String message = "success";

    private T data;

    private Result() {
    }

    private Result(int ret, String msg, T data) {
        this.code = ret;
        this.message = msg;
        this.data = data;
    }


    public static <T> Result<T> fail(int ret, String msg) {
        return new Result<>(ret, msg, null);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(FAIL, msg, null);
    }

    public static <T> Result<T> fail() {
        return fail(FAIL, "fail");
    }

    public static Result complete() {
        return new Result();
    }

    public static <T> Result complete(T data) {
        return new Result<>(OK, "complete", data);
    }

    public String encode() {
        return Json.encode(this);
    }

    private static final int OK = 20000;
    private static final int FAIL = 5000;

}
