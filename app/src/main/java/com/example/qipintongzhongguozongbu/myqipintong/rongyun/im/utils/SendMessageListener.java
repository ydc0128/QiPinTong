package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.utils;

import com.yolanda.nohttp.Logger;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Message;

/**
 * 注释：消息监听
 * 作者：碧血染银枪 on 2017/5/16 11:44
 * 邮箱：ydc_0128@163.com
 */

public class SendMessageListener implements RongIM.OnSendMessageListener {
    @Override
    public Message onSend(Message message) {
        Logger.e(" onSend "+message.getContent()+" id "+message.getSenderUserId()+"  "+message.getTargetId());
        return message;
    }

    @Override
    public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
        return false;
    }
}