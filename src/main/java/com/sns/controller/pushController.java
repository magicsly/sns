package com.sns.controller;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.sns.model.snsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ElNino on 15/9/23.
 */
@Controller
@RequestMapping("/push")
public class pushController {
    @Autowired
    com.sns.service.pushService pushService;


    @RequestMapping(value = "/push")
    @ResponseBody
    public Map push(@RequestParam(value = "title") String title,
                    @RequestParam(value = "content") String content,
                    @RequestParam(value = "tags", required = false) ArrayList<String> tags,
                    @RequestParam(value = "alias", required = false) ArrayList<String> alias,
                    HttpServletRequest request
    ) throws APIConnectionException, APIRequestException {
        Map extra = new HashMap();
        pushService.BulidPushByTagsAndAlias(tags,content,title,extra,alias);
        Map map = new HashMap();
        map.put("code",0);
        return map;
    }


//    public Map addMessage(@RequestParam(value="type",defaultValue = "",required=false) byte type,
//                          @RequestParam(value="stype",defaultValue = "",required=false) byte stype,
//                          @RequestParam(value="uid",defaultValue = "",required=false) Integer uid,
//                          @RequestParam(value="fid",defaultValue = "",required=false) Integer fid,
//                          @RequestParam(value="name",defaultValue = "",required=false) String name,
//                          @RequestParam(value="title",defaultValue = "",required=false) String title,
//                          @RequestParam(value="message",defaultValue = "",required=false) String message){
//        snsMessage snsMessage = new snsMessage();
//        snsMessage.setType(type);
//        snsMessage.setStype(stype);
//        snsMessage.setUid(uid);
//        snsMessage.setFid(fid);
//        snsMessage.setName(name);
//        snsMessage.setTitle(title);
//        snsMessage.setMessage(message);
//        return pushService.addMessage(snsMessage);
//    }

}
