package com.demo.flume.interceptor;

import com.demo.flume.utils.JSONUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class ETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    /**
     * 判断单个事件是否符合json格式
     *
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {
        // 1. 获取事件体
        byte[] body = event.getBody();
        // 2. 解析事件体为字符串
        String log = new String(body, StandardCharsets.UTF_8);
        // 3.判断是否是JSON字符串
        if (JSONUtils.isJSONValidate(log)) {
            // 是，返回事件
            return event;
        } else {
            // 不是，返回空
            return null;
        }
    }

    /**
     * @param events
     * @return
     * @describe 在这里，真正的将不符合要求的事件移除
     */

    @Override
    public List<Event> intercept(List<Event> events) {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event next = iterator.next();
            if (intercept(next) == null) {
                // 将不是json格式的数据移除
                iterator.remove();
            }
        }
        return events;

    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
