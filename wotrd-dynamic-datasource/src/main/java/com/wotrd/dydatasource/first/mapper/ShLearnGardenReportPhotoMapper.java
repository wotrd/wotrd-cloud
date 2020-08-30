package com.wotrd.dydatasource.first.mapper;

import com.wotrd.dydatasource.first.domain.ShLearnGardenReportPhoto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShLearnGardenReportPhotoMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(ShLearnGardenReportPhoto record);

    int insertSelective(ShLearnGardenReportPhoto record);

    List<ShLearnGardenReportPhoto> selectByPkId(Long pkId);

    int updateByPrimaryKeySelective(ShLearnGardenReportPhoto record);

    int updateByPrimaryKey(ShLearnGardenReportPhoto record);


}