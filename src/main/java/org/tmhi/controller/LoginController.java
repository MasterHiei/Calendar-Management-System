package org.tmhi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tmhi.model.form.LoginForm;
import org.tmhi.service.UserService;
import org.tmhi.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:       Hiei
 * Date:         2018/03/17
 * Description:  登录界面Controller
 * Modified By:
 */
@Controller
public class LoginController {

    /** 用户业务逻辑对象 */
    private UserService userService;

    /** 构造器注入 */
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * 初始化登录界面（允许GET请求直接访问）
     *
     * @return ModelAndView
     */
    @GetMapping(value = "login.html")
    public ModelAndView initLogin() {

        // 初始化登录界面
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
    
    /**
     * 用户登录
     *
     * @param request HttpServletRequest
     * @param input 用户输入参数
     * @param result 参数验证结果
     * @return 客户端响应信息（Map格式）
     * @throws Exception 异常
     */
    @PostMapping(value = "doLogin.html")
    @ResponseBody
    public Map doLogin(HttpServletRequest request, @RequestBody @Validated LoginForm input, BindingResult result) 
            throws Exception {
        
        // 获取request请求的IP地址
        String ip = RequestUtils.getIPAddress(request);
        
        // 输出日志
        LOGGER.info(ip + " - 用户登录");
        
        // 创建前端消息容器
        Map<String, String> jsonMap = new HashMap<>();
        
        // Validated输入验证
        if (result.hasErrors()) {
            // 验证未通过，返回最初的错误信息
            jsonMap.put("type", "message");
            jsonMap.put("code", result.getAllErrors().get(0).getDefaultMessage());
            return jsonMap;
        }
        
        // 用户登录
        String errorCode = userService.checkLogin(request, input.getUserName(), input.getPassword());
        
        if (StringUtils.hasText(errorCode)) {
            // 验证未通过，返回错误信息
            jsonMap.put("type", "message");
            jsonMap.put("code", errorCode);
            return jsonMap;
        }
        
        // 写入客户端响应信息
        jsonMap.put("type", "transition");
        jsonMap.put("url", "calendar.html");
        // 输出日志
        LOGGER.info(ip + " - 用户登录成功");
        
        return jsonMap;
    }
}
