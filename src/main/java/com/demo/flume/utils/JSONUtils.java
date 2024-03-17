package com.demo.flume.utils;

import com.alibaba.fastjson.JSON;

public class JSONUtils {
    public static boolean isJSONValidate(String log){
        try {
            // 1. 解析JSON字符串
            JSON.parse(log);
            return true;
        } catch (Exception e) {
            // 2. 失败了，证明不是JSON字符串
            return false;
        }
    }

}
