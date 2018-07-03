package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.utils;

import com.yolanda.nohttp.Logger;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * 注释：消息列表监听
 * 作者：碧血染银枪 on 2017/5/16 11:43
 * 邮箱：ydc_0128@163.com
 */

public class ReceiveMessageListener implements RongIMClient.OnReceiveMessageListener  {
    @Override
    public boolean onReceived(Message message, int i) {
        Logger.e(i+" onReceived "+message.getTargetId()+"  "+message.getSenderUserId());
        return false;
    }
}