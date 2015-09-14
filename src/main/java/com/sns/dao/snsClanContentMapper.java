package com.sns.dao;

import com.sns.model.snsClanContent;
import org.springframework.stereotype.Repository;

@Repository
public interface snsClanContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(snsClanContent record);

    int insertSelective(snsClanContent record);

    snsClanContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(snsClanContent record);

    int updateByPrimaryKey(snsClanContent record);
}