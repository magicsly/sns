package com.sns.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.sns.model.snsContentAnswer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface snsContentAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(snsContentAnswer record);

    int insertSelective(snsContentAnswer record);

    snsContentAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(snsContentAnswer record);

    int updateByPrimaryKey(snsContentAnswer record);

    ArrayList<Map> selectByCid(Map map);
}