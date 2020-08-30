package com.wotrd.dydatasource.first.mapper;

import com.wotrd.dydatasource.first.domain.ShLearnGardenReportComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShLearnGardenReportCommentMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(ShLearnGardenReportComment record);

    int insertSelective(ShLearnGardenReportComment record);

    List<ShLearnGardenReportComment> selectById(Long pkId);

    int updateByPrimaryKeySelective(ShLearnGardenReportComment record);

    int updateByPrimaryKey(ShLearnGardenReportComment record);

}