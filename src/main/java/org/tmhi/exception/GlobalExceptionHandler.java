package org.tmhi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tmhi.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 默认异常处理
     *
     * @return Ajax请求：客户端响应信息（Map格式）普通请求：ModelAndView（错误页面）
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object HandleDefaultException(HttpServletRequest request, Exception ex) {
        
        // 前端消息容器 
         Map<String, String> jsonMap = new HashMap<>();
        
        // 获取request请求的IP地址
        String ip = RequestUtils.getIPAddress(request);
        
        // 记录错误日志
        LOGGER.error(ip + "- 系统异常：", ex);
        
        // 判断是否为Ajax请求
        if (RequestUtils.isAjaxRequest(request)) {
            // 创建Ajax响应对象并返回
            jsonMap.put("type", "error");
            jsonMap.put("url", "404.html");
            return jsonMap;
        } else {
            // 普通请求直接跳转至错误页面
            return new ModelAndView("redirect:/404.html");
        }
    }
}
