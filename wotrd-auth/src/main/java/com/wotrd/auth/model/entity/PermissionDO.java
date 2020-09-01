package com.wotrd.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (TbPermission)实体类
 *
 * @author wotrd
 * @since 2019-05-30 17:04:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDO implements Serializable {
    private static final long serialVersionUID = -78501820450936838L;
    //主键ID
    private Long id;
    //用户ID
    private Long parentId;
    //权限名字
    private String name;
    //权限名字
    private String ename;
    //请求路径
    private String url;
    //描述
    private String description;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

}