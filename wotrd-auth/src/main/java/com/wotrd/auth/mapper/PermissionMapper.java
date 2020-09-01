package com.wotrd.auth.mapper;

import com.wotrd.auth.model.entity.PermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbPermission)表数据库访问层
 *
 * @author wotrd
 * @since 2019-05-30 17:04:05
 */
@Repository
@Mapper
public interface PermissionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PermissionDO queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbPermission 实例对象
     * @return 对象列表
     */
    List<PermissionDO> queryAll(PermissionDO tbPermission);

    List<PermissionDO> queryByUserid(Long userId);

    /**
     * 新增数据
     *
     * @param tbPermission 实例对象
     * @return 影响行数
     */
    int insert(PermissionDO tbPermission);

    /**
     * 修改数据
     *
     * @param tbPermission 实例对象
     * @return 影响行数
     */
    int update(PermissionDO tbPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}