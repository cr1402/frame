package com.kejin.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.kejin.utils.CommonUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.*;

public class SystemStateCode {

    public static final Map<String, String> resultMap = new HashMap<>();

    static {
        resultMap.put("200", "请求成功");
        resultMap.put("-1", "请求错误");
        resultMap.put("0200", "缺少必需的参数");
        resultMap.put("0201", "参数为空");
        resultMap.put("0202", "参数超出长度");
        resultMap.put("0220", "请求参数无效");
        resultMap.put("0226", "参数校验错误");
        resultMap.put("0424", "数据库操作出错");
        resultMap.put("401", "登录超时,请重新登录!");
    }

    public static JSONObject responseResult(JSONObject json, String result, String message) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String requestPath = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();

        JSONObject resultJSON = new JSONObject();

        resultJSON.put("result", "error");
        resultJSON.put("resultCode", result);
        resultJSON.put("responseDate", formatter.format(new Date()));

        if (!CommonUtils.strIsNull(message)){
            message = "，错误原因：" + message;
        }
        resultJSON.put("resultMsg",   message);

        resultJSON.put("path", requestPath);

        return resultJSON;
    }

    public static JSONObject responseResult(JSONObject json, String result) {
        return responseResult(json, result, "");
    }

    public static String getResultMsg(String result) {
        return resultMap.get(result);
    }

    public static void put(String code, String msg) {
        resultMap.put(code, msg);
    }
}
