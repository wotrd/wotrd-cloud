package com.wotrd.dydatasource.third.domain;

import lombok.Data;

/**
 * @author joemsu 2019-01-24 1:51 PM
 */
@Data
public class LikeExtendBO {

    private String toUserId;

    public LikeExtendBO(String toUserId) {
        this.toUserId = toUserId;
    }
}
