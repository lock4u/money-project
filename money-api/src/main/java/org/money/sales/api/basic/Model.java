package org.money.sales.api.basic;

import io.vertx.codegen.annotations.DataObject;
import lombok.Data;

/**
 * Created by Lee on 2018/10/25.
 */
@DataObject(generateConverter = true)
@Data
public abstract class Model {

    private Long id;

    private long create_time;

    private long update_time;


}
