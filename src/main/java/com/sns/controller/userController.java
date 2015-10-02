package com.sns.controller;

import com.sns.model.MyError;
import com.sns.exception.MyException;
import com.sns.model.snsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by ElNino on 15/9/7.
 */

@Controller
@RequestMapping("/")
public class userController extends baseController{
    @Autowired
    com.sns.service.userService userService;

    @RequestMapping(value = "/regrist")
    @ResponseBody
    public Map regrist(@RequestParam(value="mobile",defaultValue = "",required=false) String mobile,
                       @RequestParam(value="pwd",defaultValue = "",required=false) String pwd,
                       HttpServletResponse response
    ) {
        snsUser snsUser = new snsUser();
        snsUser.setMobile(mobile);
        snsUser.setPwd(pwd);
        return userService.regist(snsUser,response);
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map login(@RequestParam(value="mobile",defaultValue = "",required=false) String mobile,
                     @RequestParam(value="pwd",defaultValue = "",required=false) String pwd,
                     HttpServletResponse response
    ) {
        snsUser snsUser = new snsUser();
        snsUser.setMobile(mobile);
        snsUser.setPwd(pwd);
        return userService.login(snsUser,response);
    }

    @RequestMapping(value = "/userinfo")
    @ResponseBody
    public Map userInfo(@CookieValue(value="uid",defaultValue = "",required=false) Integer uid) {
        return userService.userInfo(uid);
    }

    @RequestMapping(value = "/edituserinfo")
    @ResponseBody
    public Map editUserinfo(@CookieValue("uid") Integer uid,
                            @RequestParam(value="uname",defaultValue = "",required=false) String uname,
                            @RequestParam(value="info",defaultValue = "",required=false) String info,
                            @RequestParam(value="img",defaultValue = "",required=false) String img

    ) throws UnsupportedEncodingException {
        uname= new String(uname.getBytes("ISO-8859-1"),"utf-8");
        info= new String(info.getBytes("ISO-8859-1"),"utf-8");
        snsUser snsUser = new snsUser();
        snsUser.setUid(uid);
        snsUser.setUname(uname);
        snsUser.setInfo(info);
        snsUser.setImg(img);
        return userService.editUserinfo(snsUser);
    }

    @RequestMapping(value = "/usersearch")
    @ResponseBody
    public Map userSearch(@RequestParam(value="mobile",defaultValue = "",required=false) String mobile) {
        return userService.searchUser(mobile);
    }
}
