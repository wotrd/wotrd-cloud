package com.example.third.mapper;

import com.example.third.domain.NoteRecordFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface NoteRecodeFileMapper {
    int deleteByRecordId(String recordId);

    int insert(NoteRecordFile record);

    int insertSelective(NoteRecordFile record);

    NoteRecordFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NoteRecordFile record);

    int updateByPrimaryKey(NoteRecordFile record);
}