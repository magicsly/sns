package com.sns.service;

import com.sns.dao.snsUserMapper;
import com.sns.dao.snsClanUserMapper;
import com.sns.model.snsUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ElNino on 15/9/7.
 */
@Service
public class userService {

    public static final String key = "123456789";
    @Autowired
    snsUserMapper snsUserMapper;

    @Autowired
    snsClanUserMapper snsClanUserMapper;

    public Map regist(snsUser user,HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        Integer code = confUser(user,0);
        if(code == 0) {
            String md5pwd = DigestUtils.md5Hex(user.getPwd());
            user.setPwd(md5pwd);
            snsUserMapper.insertSelective(user);

            snsUser loginUser = new snsUser();
            loginUser = snsUserMapper.selectByMobile(user.getMobile());
            Integer uid = loginUser.getUid();
            Cookie cookie = new Cookie("uid", uid.toString());
            Cookie cookie2 = new Cookie("key", DigestUtils.md5Hex(uid.toString()+key));
            response.addCookie(cookie);
            response.addCookie(cookie2);

            map.put("code", 0);
            map.put("message", "注册成功");
        }else{
            map.put("code",code);
            map.put("message",userMsg(code));

        }
        return map;
    }

    public Map login(snsUser user,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String, Object>();
        String md5pwd = DigestUtils.md5Hex(user.getPwd());
        user.setPwd(md5pwd);
        Integer count = snsUserMapper.userlogin(user);
        if(count>0){
            snsUser loginUser = new snsUser();
            loginUser = snsUserMapper.selectByMobile(user.getMobile());
            Integer uid = loginUser.getUid();
            Cookie cookie = new Cookie("uid", uid.toString());
            Cookie cookie2 = new Cookie("key", DigestUtils.md5Hex(uid.toString()+key));
            response.addCookie(cookie);
            response.addCookie(cookie2);
            map.put("code",0);
            map.put("message","登录成功");
        }else{
            map.put("code",1);
            map.put("message","用户名或者密码错误");
        }
        return map;
    }

    public Map userInfo(Integer uid){
        snsUser snsUser = snsUserMapper.selectByPrimaryKey(uid);
        snsUser.setPwd(null);
        String mobile = snsUser.getMobile();
        mobile = mobile.substring(0,3)+"*****"+mobile.substring(7,11);
        snsUser.setMobile(mobile);
        String img = snsUser.getImg();
        snsUser.setImg(img);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("info",snsUser);
        map.put("list",snsClanUserMapper.userClan_list(uid));
        return map;
    }

    public Map addUserinfo(snsUser user){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",0);
        return map;
    }

    public Map searchUser(String mobile){
        Map<String,Object> map = new HashMap<String, Object>();
        snsUser snsUser = snsUserMapper.selectByMobile(mobile);
        snsUser.setPwd(null);
        map.put("code",0);
        map.put("info",snsUser);
        return map;
    }

    public Map editUserinfo(snsUser user){
        if(user.getUname().equals("")){
            user.setUname(null);
        }
        if(user.getInfo().equals("")){
            user.setInfo(null);
        }
        if(user.getImg().equals("")){
            user.setImg(null);
        }
        snsUserMapper.updateByPrimaryKeySelective(user);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",0);
        return map;
    }

    private Integer confUser(snsUser user , Integer type) {
        Integer code = 0;
        if (type == 0){//如果是注册,检查用户名和密码
            Integer isuser = snsUserMapper.isuser(user.getMobile());
            if(isuser > 0) {//手机号已存在
                code = 1001;
            }
            if (!user.getMobile().matches("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$")) {
                code = 1002;
            }
            //验证密码
            if(user.getPwd()==""){
                code = 2001;
            }else if(user.getPwd().length() > 20){
                code = 2002;
            }else if(user.getPwd().length() < 6){
                code = 2003;
            }
        }
        return code;
    }
    private String userMsg (Integer code){
        String msg = "";
        switch (code){
            case 1001 : msg = "手机号已存在";break;
            case 1002 : msg = "手机号码错误";break;
            case 2001 : msg = "密码不能为空";break;
            case 2002 : msg = "密码不能大于20位";break;
            case 2003 : msg = "密码不能少于6位";break;
        }
        return msg;
    }
}
