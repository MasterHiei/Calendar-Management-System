package org.tmhi.util.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:       Hiei
 * Date:         2018/03/31
 * Description:  全局异常处理类
 * Modified By:
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    /** logger处理类 */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** 前端消息容器 */
    private static Map<String, String> jsonMap = new HashMap<>();
    
    /**
     * shiro账户不存在异常处理
     * 
     * @return 客户端响应信息（Map格式）
     */
    @ExceptionHandler(UnknownAccountException.class)
    @ResponseBody
    public Object HandleUnknownAccountException() {
        // 账户不存在
        jsonMap.put("type", "message");
        jsonMap.put("message", "对不起，您输入的用户名不存在。");
        return jsonMap;
    }
    /**
     * shiro账户密码未匹配异常处理
     *
     * @return 客户端响应信息（Map格式）
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseBody
    public Object HandleIncorrectCredentialsException() {
        // 账户不存在
        jsonMap.put("type", "message");
        jsonMap.put("message", "对不起，您输入的密码不正确。");
        return jsonMap;
    }

    /**
     * 默认异常处理
     *
     * @return 客户端响应信息（Map格式）
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ModelAndView HandleDefaultException() {
        return null;
    }
}
