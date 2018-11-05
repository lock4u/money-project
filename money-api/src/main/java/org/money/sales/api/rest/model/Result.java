package org.money.sales.api.rest.model;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Created by Lee on 2018/11/3.
 */

@Accessors(fluent = true)
@Getter
public class Result<T> {


    private int ret = 0;

    private String msg = "complete";

    private T data;


    private Result(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    private Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> complete(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> fail(int ret, String msg) {
        return new Result<>(ret, msg);
    }

    public static <T> Result<T> fail() {
        return new Result<>(-1, "fail");
    }


}
