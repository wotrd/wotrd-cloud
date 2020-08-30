package com.wotrd.dydatasource.first.mapper;

import com.wotrd.dydatasource.first.domain.ShLearnGardenReportAccess;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShLearnGardenReportAccessMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(ShLearnGardenReportAccess record);

    int insertSelective(ShLearnGardenReportAccess record);

    List<ShLearnGardenReportAccess> selectById(Long pkId);

    int updateByPrimaryKeySelective(ShLearnGardenReportAccess record);

    int updateByPrimaryKey(ShLearnGardenReportAccess record);

}