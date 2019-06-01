package com.wotrd.authresource.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (TbContent)实体类
 *
 * @author wotrd
 * @since 2019-05-31 17:27:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbContent implements Serializable {

    private static final long serialVersionUID = 834961302387603922L;

    //主键ID
    private Long id;
    //策略ID
    private Long categoryId;
    //标题
    private String title;
    //子标题
    private String subTitle;
    //标题描述
    private String titleDesc;
    //资源地址
    private String url;
    //图片一
    private String pic;
    //图片二
    private String pic2;
    //内容
    private String content;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;


}