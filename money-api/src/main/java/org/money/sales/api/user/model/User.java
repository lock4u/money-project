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
public class User extends Model {

    private String name;

    private String phone;

    private String salt;

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

    public boolean verify(@NonNull String password) {
        return password.equals(this.password);
    }


}
