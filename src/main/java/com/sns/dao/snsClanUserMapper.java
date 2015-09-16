package com.sns.dao;

import com.sns.model.snsClanUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface snsClanUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(snsClanUser record);

    int insertSelective(snsClanUser record);

    snsClanUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(snsClanUser record);

    int updateByPrimaryKey(snsClanUser record);

    ArrayList<snsClanUser> userClan_list(Integer uid);

    ArrayList<snsClanUser> clanUser_list(Integer cid);

    int isClanUser(snsClanUser record);
}