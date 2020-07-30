package com.example.third.mapper;

import com.example.third.domain.NoteRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoteRecordMapper {

    int deleteByPrimaryKey(String id);

    int insert(NoteRecord record);

    int insertSelective(NoteRecord record);

    NoteRecord selectById(String id);

    List<NoteRecord> selectAll();

    int updateByPrimaryKeySelective(NoteRecord record);

    int updateByPrimaryKey(NoteRecord record);


}