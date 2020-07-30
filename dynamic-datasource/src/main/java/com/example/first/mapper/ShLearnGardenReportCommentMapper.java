package com.example.first.mapper;

import com.example.first.domain.ShLearnGardenReportComment;
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