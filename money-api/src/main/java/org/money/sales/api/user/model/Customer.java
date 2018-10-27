package org.money.sales.api.user.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.money.sales.api.basic.Model;

/**
 * Created by Lee on 2018/10/17.
 */
@EqualsAndHashCode(callSuper = true)
@DataObject(inheritConverter = true, generateConverter = true)
@Data
public class Customer extends Model {

    private String name;

    private String phone;

    private Cert cert;

    private Marriage marriage;




    public Customer(JsonObject json) {
        CustomerConverter.fromJson(json, this);
    }


    public Customer() {
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        CustomerConverter.toJson(this, json);
        return json;
    }



}
