package org.money.sales.api.admin.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.money.sales.api.basic.model.Model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2018/10/27.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, include = JsonTypeInfo.As.PROPERTY, property = "@Class")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = Broker.class),
        @JsonSubTypes.Type(value = Boss.class)
})
@Getter
@Accessors(fluent = true, chain = true)
public abstract class Employee extends Model {


    private String name;

    private Gender gender;

    private List<Contact> contacts = new ArrayList<>();


    protected Employee( Instant create_time, Instant update_time, String name, Gender gender, List<Contact> contacts) {
        super(create_time, update_time);
        this.name = name;
        this.gender = gender;
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee other = (Employee) o;

        return id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return id().hashCode();
    }
}
