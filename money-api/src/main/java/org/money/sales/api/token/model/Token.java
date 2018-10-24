package org.money.sales.api.token.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

/**
 * Created by Lee on 2018/10/17.
 */
@DataObject(generateConverter = true)
@Data
public class Token {

    private String access_token;

    private String expire_in;

    private String errcode;

    private String errmsg;


    public Token(JsonObject json) {
        TokenConverter.fromJson(json, this);
    }


    public Token() {
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        TokenConverter.toJson(this, json);
        return json;
    }


    public boolean isSuccess() {
        return errcode == null;
    }
}
