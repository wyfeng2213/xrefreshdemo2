package com.example.administrator.xrefreshviewdemo.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * 将EventBus封装一层.
 */
public class EventBusUtil {

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    // 其他
}
