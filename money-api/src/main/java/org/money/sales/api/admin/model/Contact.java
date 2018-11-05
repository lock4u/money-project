package org.money.sales.api.admin.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.money.sales.api.basic.model.Model;

/**
 * Created by Lee on 2018/10/29.
 */

/**
 * 联系方式
 */
@Getter
@Setter
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, include = JsonTypeInfo.As.PROPERTY, property = "@Class")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = Email.class),
        @JsonSubTypes.Type(value = Wechat.class),
        @JsonSubTypes.Type(value = Phone.class)})
@Accessors(fluent = true, chain = true)
public abstract class Contact  {

    private String value;

}
