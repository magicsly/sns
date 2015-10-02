package com.sns.service;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ElNino on 15/9/23.
 */
@Service
public class pushService {

    public static final String MASTERSECRET = "4c88dda57b6721d9592c9f7a"; //MASTERSECRET
    public static final String APPKEY = "7195e8776fa6a8d7ddfdb229";  //appkey


    public void Push(PushPayload payload) throws APIConnectionException, APIRequestException{
        JPushClient jPushClient = new JPushClient(MASTERSECRET, APPKEY);
        PushResult result = jPushClient.sendPush(payload);
    }
    /**
     * 特殊推送
     * @param tag     tag 标签推送
     * @param title   推送标题
     * @param content 推送内容
     * @param extra   附件内容 key value  如果没有传null
     * @param alias   alias个体
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    public void BulidPushByTagsAndAlias(ArrayList<String> tag, String content, String title, Map extra, ArrayList<String> alias) throws APIConnectionException, APIRequestException {
        PushPayload.Builder builder = PushPayload.newBuilder();

        //设置platform
        builder.setPlatform(Platform.android_ios());

        //设置audience 判断 tag和alias
        if (tag != null || alias != null) {
            Audience.Builder audience = Audience.newBuilder();
            if (tag != null) {
                audience.addAudienceTarget(AudienceTarget.tag(tag));
            }
            if (alias != null) {
                audience.addAudienceTarget(AudienceTarget.alias(alias));
            }
            builder.setAudience(audience.build());
        }else {
            builder.setAudience(Audience.all());
        }

        //设置notification
        builder.setNotification(Notification.android( content,title, extra));
        builder.setMessage(Message.newBuilder()
                .setMsgContent(content)
                .setTitle(title)
                .addExtras(extra)
                .build());

        PushPayload payload = builder.build();


        //推送
        Push(payload);
    }

    /**
     * 对所有用户发送
     * @param title
     * @param content
     */
    public void BuildPush_all(String title, String content, Map extra) throws APIConnectionException, APIRequestException {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.android( content,title, extra))
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .setTitle(title)
                        .addExtras(extra)
                        .build())
                .build();
        //推送
        Push(payload);
    }
}
