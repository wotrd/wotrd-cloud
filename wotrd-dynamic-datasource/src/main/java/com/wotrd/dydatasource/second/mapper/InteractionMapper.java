package com.wotrd.dydatasource.second.mapper;

import com.wotrd.dydatasource.second.domain.Interaction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InteractionMapper {
    int deleteByPrimaryKey(String interactionNo);

    int insert(Interaction record);

    int insertSelective(Interaction record);

    Interaction selectByPrimaryKey(String interactionNo);
    List<Interaction> selectAll();
    int updateByPrimaryKeySelective(Interaction record);

    int updateByPrimaryKey(Interaction record);



}