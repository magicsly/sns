package com.sns.service;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.sns.model.snsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ElNino on 15/9/30.
 */

@Service
public class messageService extends pushService{

    @Autowired
    com.sns.dao.snsClanMapper snsClanMapper;

    @Autowired
    com.sns.dao.snsMessageMapper snsMessageMapper;

    public Map addMessage(snsMessage snsMessage,Integer pushType,
                          ArrayList<String> tag, String content, String title,
                          Map extra, ArrayList<String> alias)
            throws APIConnectionException, APIRequestException {
        Map<String, Object> map = new HashMap<String, Object>();
        snsMessageMapper.insertSelective(snsMessage);
        if(pushType==1){
            this.BulidPushByTagsAndAlias(tag, content, title, extra, alias);
        }else if(pushType==2){
            this.BuildPush_all(title, content, extra);
        }
        map.put("code",0);
        return map;
    }
}
