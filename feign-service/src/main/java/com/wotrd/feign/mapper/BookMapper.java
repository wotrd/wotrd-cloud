package com.wotrd.feign.mapper;

import com.wotrd.feign.model.entity.Books;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Books record);

    int insertSelective(Books record);

    /**
     * 查询全部
     *
     * @return
     */
    List<Books> selectAll();

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);


}