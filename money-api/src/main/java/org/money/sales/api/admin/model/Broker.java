package org.money.sales.api.admin.model;

/**
 * Created by Lee on 2018/10/29.
 */

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.vertx.core.json.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

/**
 * 经纪人
 */
@JsonTypeName("Broker")
@EqualsAndHashCode(callSuper = true)
@Getter
@Accessors(fluent = true, chain = true)
public class Broker extends Employee {


    private String company;

    @lombok.Builder(builderClassName = "Builder")
    public Broker(Instant create_time, Instant update_time, String name, Gender gender, @Singular List<Contact> contacts, String company) {
        super(create_time, update_time, name, gender, contacts);
        this.company = company;
    }

    @lombok.Builder(builderClassName = "Builder")


    public JsonObject toJson() {
        return null;
    }


}
