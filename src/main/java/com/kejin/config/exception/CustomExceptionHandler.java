package com.kejin.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.kejin.utils.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({Exception.class,RuntimeException.class})
    public Result<JSONObject> exception(HttpServletRequest request, Exception e) {
        e.printStackTrace();

        Result result =new Result();

        JSONObject json = new JSONObject();
        JSONObject resultJSON = SystemStateCode.responseResult(json, "-1", e.getMessage());

        result.setCode(-1).setMessage(e.getMessage()).setResult(resultJSON);

        return result;
    }

    @ExceptionHandler({SQLException.class})
    public Result<JSONObject> sqlException(HttpServletRequest request, SQLException e) {
        e.printStackTrace();

        Result result =new Result();

        JSONObject json = new JSONObject();
        JSONObject resultJSON = SystemStateCode.responseResult(json, "0424", e.getMessage());

        result.setCode(-1).setMessage(e.getMessage()).setResult(resultJSON);
        return result;
    }

    @ExceptionHandler({BindException.class})
    public Result<JSONObject> bindException(HttpServletRequest request, BindException e) {
        e.printStackTrace();

        Result result =new Result();

        JSONObject json = new JSONObject();
        JSONObject resultJSON = null;
        String message = e.getMessage().replace("\n", "").replaceAll("org\\.springframework\\.validation\\.BeanPropertyBindingResult:.+default message \\[(.+)\\]", "$1");
        if (message.matches("参数.*错误")) {
            resultJSON = SystemStateCode.responseResult(json, "0220", message);
        } else if (message.matches("缺少必填参数：.*")) {
            resultJSON = SystemStateCode.responseResult(json, "0200", message);
        } else if (message.matches("参数.*不能为空")) {
            resultJSON = SystemStateCode.responseResult(json, "0201", message);
        } else if (message.matches("参数.*超出长度.*")) {
            resultJSON = SystemStateCode.responseResult(json, "0202", message);
        } else {
            resultJSON = SystemStateCode.responseResult(json, "0226", message);
        }

        result.setCode(-1).setMessage(e.getMessage()).setResult(resultJSON);
        return result;
    }


    @ExceptionHandler({TokenExpiredException.class , JWTDecodeException.class})
    public Result<JSONObject> tokenExpiredException(HttpServletRequest request, Exception e) {
        e.printStackTrace();

        Result result =new Result();

        JSONObject json = new JSONObject();
        JSONObject resultJSON = SystemStateCode.responseResult(json, "0424", e.getMessage());

        result.setCode(401).setMessage(e.getMessage()).setResult(resultJSON);
        return result;
    }

}
