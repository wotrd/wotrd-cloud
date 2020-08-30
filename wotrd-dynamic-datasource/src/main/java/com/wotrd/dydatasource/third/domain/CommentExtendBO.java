package com.wotrd.dydatasource.third.domain;

import lombok.Data;

/**
 * @author joemsu 2019-01-24 1:51 PM
 */
@Data
public class CommentExtendBO {

    private String message;
    private String toUserId;
    private String toUserName;
    private Boolean commentOthers;

    public CommentExtendBO(String message, String toUserId, String toUserName, Boolean commentOthers) {
        this.message = message;
        this.toUserId = toUserId;
        this.toUserName = toUserName;
        this.commentOthers = commentOthers;
    }
}
