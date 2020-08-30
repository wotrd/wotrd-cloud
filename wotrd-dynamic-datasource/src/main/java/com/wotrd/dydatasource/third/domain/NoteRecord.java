package com.wotrd.dydatasource.third.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * note_record
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteRecord implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 组织id
     */
    private String orgId;

    /**
     * 内容
     */
    private String content;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 用户图像
     */
    private String userImage;

    /**
     * 部门id，多个‘，’隔开
     */
    private String deptId;

    /**
     * 部门名称，多个‘，’隔开
     */
    private String deptName;

    /**
     * 权限 0所有人 1部门权限
     */
    private Integer scopeType;

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
    /**
     * 版本号
     */
    private Integer type;

    private static final long serialVersionUID = 1L;
}