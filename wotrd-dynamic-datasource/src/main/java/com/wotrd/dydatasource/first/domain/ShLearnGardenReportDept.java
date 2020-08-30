package com.wotrd.dydatasource.first.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sh_learn_garden_report_dept
 * @author 
 */
@Data
public class ShLearnGardenReportDept implements Serializable {
    private Long pkId;

    /**
     * 相册表主键
     */
    private Long albumPk;

    /**
     * 相册类型; record：记录；week-report：报告：month-report 月报
     */
    private String albumType;

    /**
     * 微信企业号id
     */
    private String wxCorpId;

    /**
     * 是否私密，1：私密；0：公开
     */
    private String isPrivate;

    /**
     * 相册对应的报告信息
     */
    private String reportInfo;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 创建人企业号 user_id
     */
    private String createWxUserId;

    /**
     * 状态：0:正常;1:删除
     */
    private String status;

    /**
     * 1:未上传;2:上传中;3:上传成功;4:上传失败
     */
    private String uploadStatus;

    /**
     * 创建时间
     */
    private Date createDate;

    private String xyhDeptId;

    private String deptId;

    private String userOrgId;

    private String xyhOpenId;

    private static final long serialVersionUID = 1L;
}