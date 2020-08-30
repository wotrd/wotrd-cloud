package com.wotrd.dydatasource.first.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sh_learn_garden_report_access
 * @author 
 */
@Data
public class ShLearnGardenReportAccess implements Serializable {
    private Long pkId;

    /**
     * 相册主键
     */
    private Long ablumPk;

    /**
     * 点赞人的所在的corpid
     */
    private String wxCorpId;

    private String accessUserId;

    private String accessUserName;

    /**
     * 头像地址
     */
    private String accessHeadImgUrl;

    /**
     * 0：收藏，1：未收藏
     */
    private String isHouse;

    /**
     * 0：仅查看，1：已点赞
     */
    private String status;

    /**
     * 收藏时间
     */
    private Date houseDate;

    private Date accessDate;

    /**
     * 学校id
     */
    private String userOrgId;

    /**
     * openId
     */
    private String xyhOpenId;

    private static final long serialVersionUID = 1L;
}