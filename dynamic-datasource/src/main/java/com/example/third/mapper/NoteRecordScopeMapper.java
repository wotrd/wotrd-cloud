package com.example.third.mapper;

import com.example.third.domain.NoteRecordScope;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoteRecordScopeMapper {
    int deleteByRecordId(String recordId);

    int insert(NoteRecordScope record);

    int insertSelective(NoteRecordScope record);

    NoteRecordScope selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NoteRecordScope record);

    int updateByPrimaryKey(NoteRecordScope record);
}