package com.wotrd.dydatasource.third.domain;

import lombok.Data;

/**
 * @author xjf
 * @date 2020/2/27
 */
@Data
public class CommentPictureExtendBO {

    private String pictureUrl;
    private String toUserId;
    private String toUserName;
    private Boolean commentOthers;

    public CommentPictureExtendBO(String pictureUrl, String toUserId, String toUserName,
        Boolean commentOthers) {
        this.pictureUrl = pictureUrl;
        this.toUserId = toUserId;
        this.toUserName = toUserName;
        this.commentOthers = commentOthers;
    }
}
