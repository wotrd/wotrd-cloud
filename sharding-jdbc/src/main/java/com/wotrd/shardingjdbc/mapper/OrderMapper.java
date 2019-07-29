package com.wotrd.shardingjdbc.mapper;

import com.wotrd.shardingjdbc.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Order0)表数据库访问层
 *
 * @author wotrd
 * @since 2019-07-28 23:39:02
 */
@Repository
@Mapper
public interface OrderMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Order queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param userId 查询条数
     * @return 对象列表
     */
    List<Order> queryByUserId(@Param("userId") Long userId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Order> queryAll();

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    int insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    int update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}