package org.money.sales.api.user.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.money.sales.api.basic.Model;

/**
 * Created by Lee on 2018/10/25.
 */
@EqualsAndHashCode(callSuper = true)
@DataObject(generateConverter = true, inheritConverter = true)
@Data
public class Role extends Model {

    private Long uid;

    private String role;


    public Role(JsonObject json) {
        RoleConverter.fromJson(json, this);
    }
}
