package com.sns.controller;

import com.sns.exception.MyException;
import com.sns.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by ElNino on 15/9/14.
 */
@Controller
@RequestMapping("/")
public class clanController extends baseController{
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

    @RequestMapping(value = "/delclan")
    @ResponseBody
    public Map delClan(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid,
                        @CookieValue(value="uid",defaultValue = "",required=false) Integer uid
    ){
        snsClan snsClan = new snsClan();
        snsClan.setCid(cid);
        snsClan.setUid(uid);
        return clanService.delClan(snsClan);
    }


    @RequestMapping(value = "/claninfo")
    @ResponseBody
    public Map clanInfo(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid,
                        @CookieValue(value="uid",defaultValue = "",required=false) Integer uid
    ){
        return clanService.clanInfo(cid,uid);
    }

    @RequestMapping(value = "/clanuseradd")
    @ResponseBody
    public Map clanUserAdd(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid,
                           @RequestParam(value="uid",defaultValue = "",required=false) Integer uid
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

    @RequestMapping(value = "/exitclan")
    @ResponseBody
    public Map exitclan(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid,
                        @CookieValue(value="uid",defaultValue = "",required=false) Integer uid
    ){
        snsClanUser clanUser = new snsClanUser();
        clanUser.setCid(cid);
        clanUser.setUid(uid);
        return clanService.userExit(clanUser);
    }

    @RequestMapping(value = "/clandeluser")
    @ResponseBody
    public Map clandeluser(@RequestParam(value="cid",defaultValue = "",required=false) Integer cid,
                        @RequestParam(value="uid",defaultValue = "",required=false) Integer uid
    ){
        snsClanUser clanUser = new snsClanUser();
        clanUser.setCid(cid);
        clanUser.setUid(uid);
        return clanService.userExit(clanUser);
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

    @RequestMapping(value = "/mycontent")
    @ResponseBody
    public Map myContentList(@CookieValue(value="uid",defaultValue = "",required=false) Integer uid,
                                 @RequestParam(value="page",defaultValue = "1",required=false) Integer page

    ){
        return clanService.myContentList(uid, page);
    }

    @RequestMapping(value = "/addanswer")
    @ResponseBody
    public Map addAnswer(@CookieValue(value="uid",defaultValue = "",required=false) Integer uid,
                         @RequestParam(value="id",defaultValue = "1",required=false) Integer id,
                         @RequestParam(value="aid",defaultValue = "1",required=false) Integer aid,
                         @RequestParam(value="auid",defaultValue = "1",required=false) Integer auid,
                         @RequestParam(value="content",defaultValue = "1",required=false) String content
    ) throws UnsupportedEncodingException {
        content= new String(content.getBytes("ISO-8859-1"),"utf-8");
        snsContentAnswer snsContentAnswer = new snsContentAnswer();
        snsContentAnswer.setUid(uid);
        snsContentAnswer.setCid(id);
        snsContentAnswer.setContent(content);
        snsContentAnswer.setAid(aid);
        snsContentAnswer.setAuid(auid);
        return clanService.addAnswer(snsContentAnswer);
    }

    @RequestMapping(value = "/answerlist")
    @ResponseBody
    public Map answerList(@RequestParam(value="id",defaultValue = "1",required=false) Integer id,
                          @CookieValue(value="uid",defaultValue = "",required=false) Integer uid,
                          @RequestParam(value="page",defaultValue = "1",required=false) Integer page){
        return clanService.answerListById(id,uid,page);
    }

    @RequestMapping(value = "/isuserclan")
    @ResponseBody
    public Map isUserClan(@CookieValue(value="uid",defaultValue = "",required=false) Integer uid,
                          @RequestParam(value="cid",defaultValue = "",required=false) Integer cid){
        return clanService.isClanUser(uid, cid);
    }

    @RequestMapping(value = "/searchclan")
    @ResponseBody
    public Map searchClan(@RequestParam(value="code",defaultValue = "",required=false) String code){
        return clanService.clanSearch(code);
    }
}
