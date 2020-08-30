package com.wotrd.dydatasource.third.mapper;

import com.wotrd.dydatasource.third.domain.NoteRecordTheme;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoteRecodeThemeMapper {
    int deleteByPrimaryKey(String id);

    int insert(NoteRecordTheme record);

    int insertSelective(NoteRecordTheme record);

    NoteRecordTheme selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NoteRecordTheme record);

    int updateByPrimaryKey(NoteRecordTheme record);
}