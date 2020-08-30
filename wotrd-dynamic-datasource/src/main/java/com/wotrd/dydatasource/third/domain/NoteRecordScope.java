package com.wotrd.dydatasource.third.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * note_record_scope
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteRecordScope implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 组织id
     */
    private String orgId;

    /**
     * 记录id
     */
    private String recordId;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

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