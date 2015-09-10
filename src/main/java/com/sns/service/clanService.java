package com.sns.service;

import com.sns.dao.snsClanMapper;
import com.sns.model.snsClan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ElNino on 15/9/9.
 */
@Service
public class clanService {

    @Autowired
    snsClanMapper snsClanMapper;

    public Map addClan(snsClan clan){
        Map<String, Object> map = new HashMap<String, Object>();
        snsClanMapper.insertSelective(clan);
        return map;
    }



}
