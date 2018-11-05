package org.money.sales.api.eva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.Accessors;
import org.money.sales.api.admin.model.Customer;
import org.money.sales.api.basic.model.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2018/11/3.
 */

@Getter
@Accessors(fluent = true,chain = true)
@Setter
public class House extends Model {

    private String address;

    private List<Customer> customers = new ArrayList<>();

    private BigDecimal purchase;



}
