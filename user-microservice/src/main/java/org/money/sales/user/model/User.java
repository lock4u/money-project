package org.money.sales.user.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Lee on 2018/10/17.
 */
@DataObject(generateConverter = true)
@Data
public class User implements Serializable {

    private String name;

    private String id;

    private String password;

    public User(JsonObject json) {
        UserConverter.fromJson(json, this);
    }


    public User() {
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        UserConverter.toJson(this, json);
        return json;
    }

    public boolean verify(String password) {
        return true;
    }


}
