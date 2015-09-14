package com.sns.dao;

import com.sns.model.snsClanUser;
import org.springframework.stereotype.Repository;

@Repository
public interface snsClanUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(snsClanUser record);

    int insertSelective(snsClanUser record);

    snsClanUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(snsClanUser record);

    int updateByPrimaryKey(snsClanUser record);
}