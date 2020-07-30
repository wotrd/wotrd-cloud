package com.example.third.mapper;

import com.example.third.domain.NoteRecordTheme;
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