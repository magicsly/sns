package com.sns.aop;

import com.sns.exception.MyException;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ElNino on 15/9/7.
 */
@Component
@Aspect
public class userlogin {
    @Pointcut("execution(* com.sns.service.userService.userInfo(..)) ||" +
            "execution(* com.sns.service.userService.addUserinfo(..)) ||" +
            "execution(* com.sns.service.clanService.addClan(..)) ||" +
            "execution(* com.sns.service.clanService.editClan(..)) ||" +
            "execution(* com.sns.service.clanService.clanAddUser(..)) ||" +
            "execution(* com.sns.service.clanService.clanContentAdd(..)) ||" +
            "execution(* com.sns.service.clanService.clanUserUpadte(..)) ||" +
            "execution(* com.sns.service.clanService.ContentListByClan(..)) ||" +
            "execution(* com.sns.service.clanService.ContentListByUser(..)) ||" +
            "execution(* com.sns.service.userService.editUserinfo(..))")
    public void isLogin() {}

    @Before("isLogin()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookie = request.getCookies();
        String uid = "";
        String key = "";
        if(cookie == null){
            throw new MyException(-1,"用户未登录");
        }
        for (Cookie c : cookie){
            if(c.getName().equals("uid")){
                uid = c.getValue().toString();
            }
            if(c.getName().equals("key")){
                key = c.getValue().toString();
            }
        }
        String newkey = DigestUtils.md5Hex(uid + "123456789");
        if(!key.equals(newkey)){
            throw new MyException(-1,"用户未登录");
        }
    }
}
