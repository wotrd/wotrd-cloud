package com.example.first.mapper;

import com.example.first.domain.ShLearnGardenReportDept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShLearnGardenReportDeptMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(ShLearnGardenReportDept record);

    int insertSelective(ShLearnGardenReportDept record);

    List<ShLearnGardenReportDept> selectByPkId(Long pkId);

    int updateByPrimaryKeySelective(ShLearnGardenReportDept record);

    int updateByPrimaryKey(ShLearnGardenReportDept record);


}