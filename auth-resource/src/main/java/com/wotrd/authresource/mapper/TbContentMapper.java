package com.wotrd.authresource.mapper;

import com.wotrd.authresource.domain.TbContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbContent)表数据库访问层
 *
 * @author wotrd
 * @since 2019-05-31 17:27:53
 */
@Repository
@Mapper
public interface TbContentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbContent queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param categoryId 查询条数
     * @return 对象列表
     */
    List<TbContent> queryByCategoryid(@Param("categoryId") Long categoryId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbContent 实例对象
     * @return 对象列表
     */
    List<TbContent> queryAll(TbContent tbContent);

    /**
     * 新增数据
     *
     * @param tbContent 实例对象
     * @return 影响行数
     */
    int insert(TbContent tbContent);

    /**
     * 修改数据
     *
     * @param tbContent 实例对象
     * @return 影响行数
     */
    int update(TbContent tbContent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}