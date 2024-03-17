package com.demo.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class TimestampAndTableNameInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    /**
     * 拦截单个事件
     *
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {
        // 1. 获取事件header
        Map<String, String> headers = event.getHeaders();
        // 2. 获取解析body
        String body = new String(event.getBody(), StandardCharsets.UTF_8);
        // 3. 使用fastjson，将body字符串转化为JSONObject对象
        JSONObject jsonObject = JSONObject.parseObject(body);
        // 4. 获取数据中的时间戳
        Long ts = jsonObject.getLong("ts");
        // 5. Maxwell输出的数据的ts字段时间单位是秒，HDFSSink要求的时间单位是毫秒
        String timeMills = String.valueOf(ts * 1000);
        // 6. 获取body数据中的table的值
        String tableName = jsonObject.getString("table");
        // 7. 将时间戳添加到事件头部
        headers.put("timestamp", timeMills);
        // 8. 将table的名字插入到事件头部
        headers.put("tableName", tableName);
        return event;
    }

    /**
     * 拦截批量事件
     *
     * @param events
     * @return
     */

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;

    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {
        @Override
        public Interceptor build() {
            return new TimestampAndTableNameInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
