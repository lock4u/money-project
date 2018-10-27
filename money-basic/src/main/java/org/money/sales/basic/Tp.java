package org.money.sales.basic;

import io.vertx.ext.sql.ResultSet;

import java.util.function.Function;

/**
 * Created by Lee on 2018/10/26.
 */
public interface Tp<U> extends Function<ResultSet, U> {

    @Override
    U apply(ResultSet resultSet);
}
