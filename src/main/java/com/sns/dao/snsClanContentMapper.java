package com.sns.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.sns.model.snsClanContent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface snsClanContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(snsClanContent record);

    int insertSelective(snsClanContent record);

    snsClanContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(snsClanContent record);

    int updateByPrimaryKey(snsClanContent record);

    ArrayList<snsClanContent> selectByCid(Integer cid , PageBounds pageBounds);

    ArrayList<snsClanContent> selectByUid(Integer uid , PageBounds pageBounds);
}