package com.wotrd.dydatasource.third.mapper;

import com.wotrd.dydatasource.third.domain.NoteRecordFile;
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