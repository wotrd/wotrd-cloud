package com.wotrd.dydatasource.four.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_user
 * @author 
 */
@Data
public class TUser implements Serializable {
    private String id;

    /**
     * 企业ID
     */
    private String orgId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户名拼音
     */
    private String pinyin;

    /**
     * 姓名标签
     */
    private String userTag;

    /**
     * 人员唯一标志（学号，学籍号，工号。。。） 现在暂为 学生：学号，老师：工号
     */
    private String userSign;

    /**
     * 头像地址 ../user_id.jpg
     */
    private String photoUrl;

    private String oldPhotoUrl;

    /**
     * 出生日期
     */
    private String bornDate;

    /**
     * 姓别  男; 女
     */
    private String sex;

    /**
     * 新用户中心的中没有此数据，从老教育宝平台，导过来，保留其数据
     */
    private String jybUserType;

    /**
     * 是否删除 1:删除；0：正常
     */
    private String isDelete;

    private String createUser;

    private Date gmtCreate;

    /**
     * modify_user为-1表示是自助注册写入
     */
    private String modifyUser;

    private Date gmtModify;

    /**
     * 操作版本
     */
    private Long operationVersion;

    /**
     * 学籍号
     */
    private String studentCode;

    /**
     * 用户类型
     */
    private String userType;

    private static final long serialVersionUID = 1L;
}