package com.sns.dao;

import com.sns.model.snsClan;
import org.springframework.stereotype.Repository;

@Repository
public interface snsClanMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(snsClan record);

    int insertSelective(snsClan record);

    snsClan selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(snsClan record);

    int updateByPrimaryKey(snsClan record);

    int isClan(snsClan record);
}