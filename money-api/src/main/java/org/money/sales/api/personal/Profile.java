package org.money.sales.api.personal;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.money.sales.api.basic.model.Model;

import java.time.Instant;

/**
 * Created by Lee on 2018/11/5.
 */

@Accessors(fluent = true)
@Getter
public class Profile extends Model {

    private String nickname;


    /**
     * 头像地址
     */
    private String portrait;


    /**
     * 家庭地址
     */
    private String address;


    public Profile(Instant create_time, Instant update_time, String nickname, String portrait, String address) {
        super(create_time, update_time);
        this.nickname = nickname;
        this.portrait = portrait;
        this.address = address;
    }
}
