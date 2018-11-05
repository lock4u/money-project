package org.money.sales.api.admin.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by Lee on 2018/11/3.
 */

@JsonTypeName("Wechat")
@Getter
@Setter
@Accessors(fluent = true, chain = true)
public class Wechat extends Contact {

    private String nickname;
    private String portrait;


}
