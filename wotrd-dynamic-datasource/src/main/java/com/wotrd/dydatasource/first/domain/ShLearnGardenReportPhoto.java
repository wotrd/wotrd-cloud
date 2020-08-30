package com.wotrd.dydatasource.first.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * sh_learn_garden_report_photo
 * @author 
 */
@Data
public class ShLearnGardenReportPhoto implements Serializable {
    private Long pkId;

    /**
     * 相册ID
     */
    private Long albumPk;

    /**
     * 图片url
     */
    private String photoUrl;

    /**
     * 远程图片存储ID
     */
    private String photoRmId;

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片高度
     */
    private Integer hight;

    /**
     * 1:未上传;2:上传中;3:上传成功;4:上传失败
     */
    private String uploadStatus;

    /**
     * 上传信息，如出错信息
     */
    private String uploadInfo;

    private static final long serialVersionUID = 1L;
}