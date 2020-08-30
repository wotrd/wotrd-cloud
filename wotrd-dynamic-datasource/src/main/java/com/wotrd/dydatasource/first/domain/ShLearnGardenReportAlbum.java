package com.wotrd.dydatasource.first.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * sh_learn_garden_report_album
 * @author 
 */
@Data
public class ShLearnGardenReportAlbum implements Serializable {
    private Long pkId;

    /**
     * 相册类型; record：记录；week-report：报告：month-report 月报
     */
    private String albumType;

    /**
     * 相册描述
     */
    private String albumDesc;

    /**
     * 微信企业号ID
     */
    private String wxCorpId;

    /**
     * common:普通；supervisor_china：国家督导；supervisor_province：省督导；
     */
    private String cropType;

    /**
     * 创建都ID
     */
    private String createWxUserId;

    /**
     * 创建者名称
     */
    private String createWxUserName;

    /**
     * 创建者头像
     */
    private String createHeadImgUrl;

    /**
     * 督学报告 子相册id 列表【补充内容】
     */
    private String childAlbumPkArr;

    /**
     * 督学报告id 列表【笔记 入选为报告报到国家督导后，此处记录其报告 ID】
     */
    private String reportPkArr;

    /**
     * 可以查看的部门name
     */
    private String queryDeptNameArr;

    /**
     * 新用户中心；存如果一个用户有两个娃，存对应的名称
     */
    private String createUserNameArr;

    /**
     * 发送者身份
     */
    private String createUserCard;

    /**
     * 1:未上传;2:上传中;3:上传成功;4:上传失败
     */
    private String uploadStatus;

    /**
     * 报告编号（用户发送的第几个报告）
     */
    private String reportNo;

    /**
     * 状态：0:正常;1:删除
     */
    private String status;

    /**
     * 是否私密，1：私密；0：公开
     */
    private String isPrivate;

    /**
     * 督学笔记类型编号
     */
    private String noteTypeCode;

    /**
     * 督学笔记类型
     */
    private String noteTypeName;

    /**
     * 点赞人数
     */
    private Integer praiseNum;

    /**
     * 点赞人信息，本个相册的前三个人的性名
     */
    private String praiseInfo;

    /**
     * 报告信息：默认保侟 最背后的
     */
    private String reportMemo;

    /**
     * 评论人数
     */
    private Integer commentNum;

    /**
     * 评论人概要信息，保存评论的前3个人的评论信息[{"commentPk":"","userName":"","content":""}]
     */
    private String comentInfo;

    private Date createDate;

    /**
     * 可以查看的部门ID,如果私密，此处为用户ID
     */
    private String queryDeptIdArr;

    /**
     * 可以查看的部门ID,如果私密，此处为用户ID
     */
    private String xyhDeptIdArr;

    /**
     * 学校id
     */
    private String userOrgId;

    /**
     * 用户openid
     */
    private String xyhOpenId;

    /**
     * userId
     */
    private String userId;

    private static final long serialVersionUID = 1L;
}