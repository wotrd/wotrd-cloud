package com.example.first.mapper;

import com.example.first.domain.Order0;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Order0)表数据库访问层
 *
 * @author wotrd
 * @since 2019-10-23 17:49:38
 */
public interface Order0Mapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Order0 queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Order0> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param order0 实例对象
     * @return 对象列表
     */
    List<Order0> queryAll(Order0 order0);

    /**
     * 新增数据
     *
     * @param order0 实例对象
     * @return 影响行数
     */
    int insert(Order0 order0);

    /**
     * 修改数据
     *
     * @param order0 实例对象
     * @return 影响行数
     */
    int update(Order0 order0);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}