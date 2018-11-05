package org.money.sales.api.admin.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

/**
 * Created by Lee on 2018/10/25.
 */
@DataObject(generateConverter = true, inheritConverter = true)
@Data
public class Role  {

    private Long uid;

    private String role;


    public Role(JsonObject json) {
//        RoleConverter.fromJson(json, this);
    }


}
