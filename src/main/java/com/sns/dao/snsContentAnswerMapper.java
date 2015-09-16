package com.sns.dao;

import com.sns.model.snsContentAnswer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface snsContentAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(snsContentAnswer record);

    int insertSelective(snsContentAnswer record);

    snsContentAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(snsContentAnswer record);

    int updateByPrimaryKey(snsContentAnswer record);
}