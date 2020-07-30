package com.example.third.mapper;

import com.example.third.domain.NoteTheme;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface NoteThemeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoteTheme record);

    int insertSelective(NoteTheme record);

    NoteTheme selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NoteTheme record);

    int updateByPrimaryKey(NoteTheme record);
}