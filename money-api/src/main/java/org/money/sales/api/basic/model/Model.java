package org.money.sales.api.basic.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * Created by Lee on 2018/10/25.
 */

@Getter
@Accessors(fluent = true, chain = true)
public abstract class Model {

    @JsonProperty("id")
    @JsonAlias("_id")
    private String id;

    private Instant create_time;

    private Instant update_time;

    protected Model(Instant create_time, Instant update_time) {
        this.create_time = create_time;
        this.update_time = update_time;
    }
}
