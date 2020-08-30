package com.wotrd.dydatasource.first.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sh_learn_garden_report_comment
 * @author 
 */
@Data
public class ShLearnGardenReportComment implements Serializable {
    /**
     * 主键
     */
    private Long pkId;

    /**
     * 相册主键
     */
    private Long albumPk;

    /**
     * 评化人的所在的corpid
     */
    private String wxCorpId;

    /**
     * 用户ID
     */
    private String commentUserId;

    /**
     * 用户名称
     */
    private String commentUserName;

    /**
     * 评论者头像
     */
    private String commentHeadImgUrl;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 创建时间
     */
    private Date commentDate;

    /**
     * 状态：0:正常;1:删除
     */
    private String status;

    /**
     * 被回复的评论PK，仅当只有是回复消息的值时才有此些
     */
    private Long replyPk;

    /**
     * 被回复的人，冗余在此处，主于页面数据展示
     */
    private String replyUserName;

    /**
     * 被回复的人，冗余在此处，主于页面数据展示
     */
    private String replyUserId;

    private String userOrgId;

    private String xyhOpenId;

    private static final long serialVersionUID = 1L;
}