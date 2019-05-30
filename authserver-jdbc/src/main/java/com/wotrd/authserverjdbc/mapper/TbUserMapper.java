package com.wotrd.authserverjdbc.mapper;

import com.wotrd.authserverjdbc.domain.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TbUser)表数据库访问层
 *
 * @author wotrd
 * @since 2019-05-30 16:42:21
 */
@Repository
@Mapper
public interface TbUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbUser queryById(Long id);

    TbUser getByUsername(String username);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbUser 实例对象
     * @return 对象列表
     */
    List<TbUser> queryAll(TbUser tbUser);

    /**
     * 新增数据
     *
     * @param tbUser 实例对象
     * @return 影响行数
     */
    int insert(TbUser tbUser);

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 影响行数
     */
    int update(TbUser tbUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}