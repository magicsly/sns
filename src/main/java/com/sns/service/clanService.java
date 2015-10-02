package com.sns.service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.sns.dao.snsClanMapper;
import com.sns.model.snsClan;
import com.sns.dao.snsClanUserMapper;
import com.sns.model.snsClanUser;
import com.sns.dao.snsClanContentMapper;
import com.sns.model.snsClanContent;
import com.sns.dao.snsContentAnswerMapper;
import com.sns.model.snsContentAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ElNino on 15/9/9.
 */
@Service
public class clanService {

    @Autowired
    snsClanMapper snsClanMapper;

    @Autowired
    snsClanUserMapper snsClanUserMapper;

    @Autowired
    snsClanContentMapper snsClanContentMapper;

    @Autowired
    snsContentAnswerMapper snsContentAnswerMapper;

    public static final Integer pageSize = 20;
    public Map addClan(snsClan clan){
        //创建部落
        Map<String, Object> map = new HashMap<String, Object>();
        clan.setCreattime(new Date());
        snsClanMapper.insertSelective(clan);
        //生成部落id
        Integer code = clan.getCid();
        code = code + 1000000;
        clan.setCode(code.toString());
        snsClanMapper.updateByPrimaryKeySelective(clan);
        //创建管理员
        snsClanUser clanUser = new snsClanUser();
        clanUser.setCid(clan.getCid());
        clanUser.setUid(clan.getUid());
        clanUser.setLevel((byte) 1);
        clanUser.setState((byte) 0);
        this.clanAddUser(clanUser);

        map.put("code",0);
        map.put("info",clan);
        return map;
    }

    public Map editClan(snsClan clan){
        Map<String, Object> map = new HashMap<String, Object>();
        Integer isclan = snsClanMapper.isClan(clan);
        if(isclan>0) {
            if(clan.getCname().equals("")){
                clan.setCname(null);
            }
            if(clan.getInfo().equals("")){
                clan.setInfo(null);
            }
            if(clan.getImg().equals("")){
                clan.setImg(null);
            }
            snsClanMapper.updateByPrimaryKeySelective(clan);
            map.put("code", 0);
            map.put("info", clan);
        }else{
            map.put("code", 1);
            map.put("message", "不能修改非本人的部落");
        }
        return map;
    }

    public Map delClan(snsClan clan){
        Map<String, Object> map = new HashMap<String, Object>();
        Integer isclan = snsClanMapper.isClan(clan);
        if(isclan>0) {
            snsClanUserMapper.deleteByPrimaryKey(clan.getCid());
            map.put("code", 0);
            map.put("message", "解散成功");
        }else{
            map.put("code", 1);
            map.put("message", "不能解散非本人的部落");
        }
        return map;
    }

    public Map clanInfo(Integer cid,Integer uid){
        Map<String, Object> map = new HashMap<String, Object>();
        snsClan clan = new snsClan();
        clan = snsClanMapper.selectByPrimaryKey(cid);
        map.put("code",0);
        snsClanUser clanUser = new snsClanUser();
        clanUser.setCid(cid);
        clanUser.setUid(uid);
        Integer isclanUser = snsClanUserMapper.isClanUser(clanUser);
        if(isclanUser>0) {
            snsClan cu = new snsClan();
            cu.setUid(uid);
            cu.setCid(cid);
            Integer isclan = snsClanMapper.isClan(cu);
            if(isclan>0) {
                map.put("isuser",0);
            }else {
                map.put("isuser",1);
            }
        }else{
            map.put("isuser",2);
        }
        map.put("info",clan);
        return map;
    }

    public Map clanAddUser(snsClanUser clanUser){
        Map<String, Object> map = new HashMap<String, Object>();
        clanUser.setCreattime(new Date());
        snsClanUserMapper.insertSelective(clanUser);
        map.put("code",0);
        return map;
    }

    public Map clanUserUpadte(snsClanUser clanUser){
        Map<String, Object> map = new HashMap<String, Object>();
        clanUser.setUpdatetime(new Date());
        snsClanUserMapper.updateByPrimaryKeySelective(clanUser);
        map.put("code",0);
        return map;
    }

    public Map userExit(snsClanUser clanUser){
        Map<String, Object> map = new HashMap<String, Object>();
        snsClanUserMapper.deleteUser(clanUser);
        map.put("code",0);
        return map;
    }

    public Map userClanList(Integer uid){
        Map<String, Object> map = new HashMap<String, Object>();
        ArrayList clanList = snsClanUserMapper.userClan_list(uid);
        map.put("code",0);
        map.put("list",clanList);
        return map;
    }

    public Map clanUserList(Integer cid){
        Map<String, Object> map = new HashMap<String, Object>();
        ArrayList userList = snsClanUserMapper.clanUser_list(cid);
        map.put("code",0);
        map.put("list",userList);
        return map;
    }

    public Map clanContentAdd(snsClanContent clanContent){
        Map<String, Object> map = new HashMap<String, Object>();
        snsClan clan = new snsClan();
        clan.setCid(clanContent.getCid());
        clan.setUid(clanContent.getUid());
        Integer isclan = snsClanMapper.isClan(clan);
        if(isclan>0) {
            clanContent.setCreattime(new Date());
            snsClanContentMapper.insertSelective(clanContent);
            map.put("code",0);
        }else{
            map.put("code", 1);
            map.put("message", "你不是部落首领，不能发表");
        }
        return map;
    }

    public Map ContentListByUser(Integer uid ,Integer page){
        Map<String, Object> map = new HashMap<String, Object>();
        PageBounds pageBounds = new PageBounds(page,pageSize);
        ArrayList contentList = snsClanContentMapper.selectByUid(uid,pageBounds);
        PageList pageList = (PageList)contentList;
        map.put("code",0);
        map.put("list",contentList);
        map.put("count",pageList.getPaginator().getTotalCount());
        map.put("pagesize",pageSize);
        map.put("page",page);
        return map;
    }

    public Map ContentListByClan(Integer cid,Integer uid ,Integer page){
        Map<String, Object> map = new HashMap<String, Object>();
        snsClanUser clanUser = new snsClanUser();
        clanUser.setCid(cid);
        clanUser.setUid(uid);
        Integer isclanUser = snsClanUserMapper.isClanUser(clanUser);
        if(isclanUser>0) {
            snsClan clan = snsClanMapper.selectByPrimaryKey(cid);
            Map<String, Object> pushmap = new HashMap<String, Object>();
            pushmap.put("cid",cid);
            pushmap.put("uid",uid);
            PageBounds pageBounds = new PageBounds(page,pageSize);
            ArrayList contentList = snsClanContentMapper.selectByCid(pushmap,pageBounds);
            PageList pageList = (PageList)contentList;
            map.put("code",0);
            map.put("info",clan);
            map.put("list",contentList);
            map.put("count",pageList.getPaginator().getTotalCount());
            map.put("pagesize",pageSize);
            map.put("page",page);
        }else{
            map.put("code", 1);
            map.put("message", "你还未加入该部落");
        }
        return map;
    }

    public Map myContentList(Integer uid,Integer page){
        Map<String, Object> map = new HashMap<String, Object>();
        PageBounds pageBounds = new PageBounds(page,pageSize);
        ArrayList contentList = snsClanContentMapper.selectByUser(uid,pageBounds);
        PageList pageList = (PageList)contentList;
        map.put("code",0);
        map.put("list",contentList);
        map.put("count",pageList.getPaginator().getTotalCount());
        map.put("pagesize",pageSize);
        map.put("page",page);
        return map;
    }

    public Map addAnswer(snsContentAnswer contentAnswer){
        Map<String, Object> map = new HashMap<String, Object>();
        snsClanUser clanUser = new snsClanUser();
        clanUser.setCid(contentAnswer.getCid());
        clanUser.setUid(contentAnswer.getUid());
        Integer isclanUser = snsClanUserMapper.isClanUser(clanUser);
        if(isclanUser>0) {
            contentAnswer.setCreattime(new Date());
            snsContentAnswerMapper.insertSelective(contentAnswer);
            map.put("code",0);
        }else{
            map.put("code", 1);
            map.put("message", "你还未加入该部落");
        }
        return map;
    }

    public Map answerListById(Integer id,Integer uid,Integer page){
        Map<String, Object> map = new HashMap<String, Object>();
        PageBounds pageBounds = new PageBounds(page,pageSize);
        Map<String, Object> pushmap = new HashMap<String, Object>();
        pushmap.put("id",id);
        pushmap.put("uid",uid);
        ArrayList answerList = snsContentAnswerMapper.selectByCid(pushmap);
        map.put("code",0);
        map.put("list",answerList);
        return map;
    }

    public Map isClanUser (Integer uid, Integer cid){
        Map<String, Object> map = new HashMap<String, Object>();
        snsClanUser clanUser = new snsClanUser();
        clanUser.setCid(cid);
        clanUser.setUid(uid);
        Integer isclanUser = snsClanUserMapper.isClanUser(clanUser);
        if(isclanUser>0) {
            map.put("code",0);
        }else{
            map.put("code",1);
        }
        return map;
    }

    public Map clanSearch (String code){
        Map<String, Object> map = new HashMap<String, Object>();
        ArrayList clanList = snsClanMapper.clanSearch(code);
        map.put("code",0);
        map.put("list",clanList);
        return map;
    }
}
