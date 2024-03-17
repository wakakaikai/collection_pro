package com.demo.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class TimestampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 1. 获取Event的Body
        String log = new String(event.getBody(), StandardCharsets.UTF_8);
        // 2. 解析log为json对象
        JSONObject jsonObject = JSONObject.parseObject(log);
        // 3. 获取log中的时间戳
        String ts = jsonObject.getString("ts");
        // 4. 将时间戳属性配置到header中
        event.getHeaders().put("timestamp",ts);
        return event;

    }

    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;

    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new TimestampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
