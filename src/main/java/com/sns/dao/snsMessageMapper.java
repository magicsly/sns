package com.sns.dao;

import com.sns.model.snsMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface snsMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(snsMessage record);

    int insertSelective(snsMessage record);

    snsMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(snsMessage record);

    int updateByPrimaryKey(snsMessage record);
}