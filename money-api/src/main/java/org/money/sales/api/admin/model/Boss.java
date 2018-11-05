package org.money.sales.api.admin.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.vertx.core.json.JsonObject;
import lombok.Getter;
import lombok.Singular;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

/**
 * Created by Lee on 2018/10/27.
 */
@JsonTypeName("Boss")
@Accessors(fluent = true, chain = true)
@Getter
public class Boss extends Employee {


    @lombok.Builder(builderClassName = "Builder")
    public Boss(Instant create_time, Instant update_time, String name, Gender gender, @Singular List<Contact> contacts) {
        super(create_time, update_time, name, gender, contacts);
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }


}
