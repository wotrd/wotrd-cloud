package com.wotrd.dydatasource.second.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * interaction
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interaction implements Serializable {
    /**
     * 互动序列号
     */
    private String interactionNo;

    /**
     * 互动项目序列号
     */
    private String subjectNo;

    /**
     * 发布内容id
     */
    private String momentId;

    /**
     * 操作用户id
     */
    private String fromUserId;

    /**
     * 操作用户名
     */
    private String fromUserName;

    /**
     * 互动类型：点赞，收藏，评论
     */
    private String type;

    /**
     * 对应类型下的信息
     */
    private Object extend;

    private Date gmtCreate;

    private Date gmtModify;

    private Boolean read;

    private Boolean isDelete;

    /**
     * 学校id
     */
    private String orgId;

    private static final long serialVersionUID = 1L;
}