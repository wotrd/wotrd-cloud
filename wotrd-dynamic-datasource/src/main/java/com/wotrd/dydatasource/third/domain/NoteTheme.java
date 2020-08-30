package com.wotrd.dydatasource.third.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * note_theme
 * @author 
 */
@Data
public class NoteTheme implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 主题类型 0系统 1学校
     */
    private Integer type;

    /**
     * 组织id
     */
    private String orgId;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 内容
     */
    private String content;

    /**
     * 删除标记(0否1是)
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date gmtModify;

    /**
     * 修改人
     */
    private String modifyUser;

    /**
     * 版本号
     */
    private Integer version;

    private static final long serialVersionUID = 1L;
}