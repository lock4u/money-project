package org.money.sales.api.eva.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.money.sales.api.basic.Model;

import java.math.BigDecimal;

/**
 * Created by Lee on 2018/10/27.
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class House extends Model {


    public String province;

    public String city;


    public String location;

    public String owner;

    /**
     * 联系方式
     */
    public String contact;

    /**
     * 评估价值
     */
    public BigDecimal value;


}
