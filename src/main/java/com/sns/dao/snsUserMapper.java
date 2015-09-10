package com.sns.dao;

import com.sns.model.snsUser;
import org.springframework.stereotype.Repository;

@Repository
public interface snsUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(snsUser record);

    int insertSelective(snsUser record);

    snsUser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(snsUser record);

    int updateByPrimaryKey(snsUser record);
    
    int userlogin(snsUser record);

    int isuser(String mobile);

    snsUser selectByMobile(String mobile);
}