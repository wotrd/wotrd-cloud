package com.wotrd.dydatasource.four.mapper;

import com.wotrd.dydatasource.four.domain.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TUserMapper {
    /**
     * 根据组织ID和用户名字查询
     *
     * @param orgId
     * @param userName
     * @return
     */
    TUser selectByOrgIdAndUserName(@Param("orgId") String orgId, @Param("userName") String userName);

    int insert(TUser record);

    int insertSelective(TUser record);
}