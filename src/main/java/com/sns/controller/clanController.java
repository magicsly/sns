package com.sns.controller;

import com.sns.model.snsClan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ElNino on 15/9/14.
 */
@Controller
@RequestMapping("/")
public class clanController {
    @Autowired
    com.sns.service.clanService clanService;

    @RequestMapping(value = "/addclan")
    @ResponseBody
    public Map addClan(@RequestParam(value="cname",defaultValue = "",required=false) String cname,
                       @RequestParam(value="info",defaultValue = "",required=false) String info,
                       @RequestParam(value="img",defaultValue = "",required=false) String img,
                       @CookieValue(value="uid",defaultValue = "",required=false) Integer uid
                       ) {

        snsClan snsClan = new snsClan();
        snsClan.setCname(cname);
        snsClan.setInfo(info);
        snsClan.setImg(img);
        snsClan.setUid(uid);
        return clanService.addClan(snsClan);
    }
}
