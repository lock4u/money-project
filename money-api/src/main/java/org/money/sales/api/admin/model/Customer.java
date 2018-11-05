package org.money.sales.api.admin.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.vertx.core.json.JsonObject;
import lombok.Getter;
import lombok.Singular;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

/**
 * Created by Lee on 2018/10/17.
 */
@JsonTypeName("Customer")
@Accessors(fluent = true, chain = true)
@Getter
public class Customer extends Client {


    private Cert cert;

    private Marriage marriage;

    @lombok.Builder(builderClassName = "Builder")
    private Customer(Instant create_time, Instant update_time, String name, Gender gender, @Singular List<Contact> contacts, Cert cert, Marriage marriage) {
        super(create_time, update_time, name, gender, contacts);
        this.cert = cert;
        this.marriage = marriage;
    }


    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }


}
