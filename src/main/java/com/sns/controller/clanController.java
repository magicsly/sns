package com.sns.controller;

import com.sns.model.snsClan;
import com.sns.model.snsClanUser;
import com.sns.model.snsClanContent;
import com.sns.model.snsContentAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
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
                       ) throws UnsupportedEncodingException {

        cname= new String(cname.getBytes("ISO-8859-1"),"utf-8");
        info= new String(info.getBytes("ISO-8859-1"),"utf-8");
        snsClan snsClan = new snsClan();
        snsClan.setCname(cname);
        snsClan.setInfo(info);
        snsClan.setImg(img);
        snsClan.setUid(uid);
        return clanService.addClan(snsClan);
    }

    @RequestMapping(value = "/editclan")
    @ResponseBody
    public Map editClan(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid,
                   @RequestParam(value="cname",defaultValue = "",required=false) String cname,
                   @RequestParam(value="info",defaultValue = "",required=false) String info,
                   @RequestParam(value="img",defaultValue = "",required=false) String img,
                   @CookieValue(value="uid",defaultValue = "",required=false) Integer uid
    ) throws UnsupportedEncodingException {

        cname= new String(cname.getBytes("ISO-8859-1"),"utf-8");
        info= new String(info.getBytes("ISO-8859-1"),"utf-8");
        snsClan snsClan = new snsClan();
        snsClan.setCid(cid);
        snsClan.setCname(cname);
        snsClan.setInfo(info);
        snsClan.setImg(img);
        snsClan.setUid(uid);
        return clanService.editClan(snsClan);
    }

    @RequestMapping(value = "/claninfo")
    @ResponseBody
    public Map clanInfo(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid
    ){
        return clanService.clanInfo(cid);
    }

    @RequestMapping(value = "/clanuseradd")
    @ResponseBody
    public Map clanUserAdd(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid,
                           @CookieValue(value="uid",defaultValue = "",required=false) Integer uid
    ){
        snsClanUser clanUser = new snsClanUser();
        clanUser.setCid(cid);
        clanUser.setUid(uid);
        clanUser.setLevel((byte)9);
        clanUser.setState((byte)1);
        return clanService.clanAddUser(clanUser);
    }

    @RequestMapping(value = "/joinclan")
     @ResponseBody
     public Map joinclan(@RequestParam(value="id",defaultValue = "",required=false) Integer id){
        snsClanUser clanUser = new snsClanUser();
        clanUser.setId(id);
        clanUser.setState((byte)0);
        return clanService.clanUserUpadte(clanUser);
    }

    @RequestMapping(value = "/outclan")
    @ResponseBody
    public Map outclan(@RequestParam(value="id",defaultValue = "",required=false) Integer id){
        snsClanUser clanUser = new snsClanUser();
        clanUser.setId(id);
        clanUser.setState((byte)2);
        return clanService.clanUserUpadte(clanUser);
    }

    @RequestMapping(value = "/userclan_list")
    @ResponseBody
    public Map userClanList(@RequestParam(value="uid",defaultValue = "",required=false) Integer uid){
        return clanService.userClanList(uid);
    }

    @RequestMapping(value = "/clanuser_list")
    @ResponseBody
    public Map clanUserList(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid){
        return clanService.clanUserList(cid);
    }

    @RequestMapping(value = "/contentadd")
    @ResponseBody
    public Map contentAdd(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid,
                          @RequestParam(value="content",defaultValue = "",required=false) String content,
                          @CookieValue(value="uid",defaultValue = "",required=false) Integer uid

    ) throws UnsupportedEncodingException {
        content= new String(content.getBytes("ISO-8859-1"),"utf-8");
        snsClanContent clanContent = new snsClanContent();
        clanContent.setCid(cid);
        clanContent.setUid(uid);
        clanContent.setContent(content);
        return clanService.clanContentAdd(clanContent);
    }
    @RequestMapping(value = "/contentbycaln")
    @ResponseBody
    public Map contentListByClan(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid,
                          @CookieValue(value="uid",defaultValue = "",required=false) Integer uid,
                          @RequestParam(value="page",defaultValue = "1",required=false) Integer page

    ){
        return clanService.ContentListByClan(cid,uid,page);
    }

    @RequestMapping(value = "/contentbyuser")
    @ResponseBody
    public Map contentListByUser(@CookieValue(value="uid",defaultValue = "",required=false) Integer uid,
                                 @RequestParam(value="page",defaultValue = "1",required=false) Integer page

    ){
        return clanService.ContentListByUser(uid, page);
    }
}
