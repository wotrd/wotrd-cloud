package com.wotrd.sharding.mapper;

import com.wotrd.sharding.domain.Config;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConfigMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Long id);

    List<Config> selectAll();

    int updateByPrimaryKey(Config record);

}