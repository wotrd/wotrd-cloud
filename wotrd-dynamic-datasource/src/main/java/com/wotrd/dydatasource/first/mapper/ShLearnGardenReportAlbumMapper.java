package com.wotrd.dydatasource.first.mapper;

import com.wotrd.dydatasource.first.domain.ShLearnGardenReportAlbum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShLearnGardenReportAlbumMapper {
    int deleteByPrimaryKey(Long pkId);

    int insert(ShLearnGardenReportAlbum record);

    int insertSelective(ShLearnGardenReportAlbum record);

    ShLearnGardenReportAlbum selectByPrimaryKey(Long pkId);

    List<ShLearnGardenReportAlbum> selectAll();

    int updateByPrimaryKeySelective(ShLearnGardenReportAlbum record);

    int updateByPrimaryKey(ShLearnGardenReportAlbum record);


}