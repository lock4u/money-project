package org.money.sales.api.user.model;

import lombok.Data;

/**
 * Created by Lee on 2018/10/27.
 */
@Data
public class Cert {

    private String number;
    private Classify classify;

    public enum Classify {
        identity, residence;

    }
}
