package com.wotrd.springsecurity.mapper;

import com.wotrd.springsecurity.model.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    /**
     * 根据用户名字查询
     *
     * @param userName
     * @return
     */
    UserDO selectByUserName(@Param("userName") String userName);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}