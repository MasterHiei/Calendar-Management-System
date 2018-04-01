package org.tmhi.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmhi.model.form.LoginForm;
import org.tmhi.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author:       Hiei
 * Date:         2018/03/17
 * Description:  登录界面Controller
 * Modified By:
 */
@Controller
public class LoginController {

    /** 用户数据访问层对象 */
    private UserService userService;

    /** 构造器注入 */
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    /**
     * 用户登录
     * 
     * @param input 用户输入参数
     * @return 客户端响应信息（Map格式）
     * @throws Exception 异常
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map Login(@RequestBody @Validated LoginForm input, BindingResult result) throws Exception {
        
        // 输出日志
        LOGGER.info("用户登录");
        
        // 前端消息容器
        Map<String, String> jsonMap = new HashMap<>();
        
        // Validated输入验证
        if (result.hasErrors()) {
            // 验证未通过，返回最初的错误信息
            jsonMap.put("type", "message");
            jsonMap.put("code", result.getAllErrors().get(0).getDefaultMessage());
            return jsonMap;
        }

        // 获取shiro subject
        Subject subject = SecurityUtils.getSubject();
        // 生成用户Token
        UsernamePasswordToken token = new UsernamePasswordToken(input.getUserName(), input.getPassword());
        // RememberMe
        token.setRememberMe(Objects.nonNull(input.getRememberMe()));
        // shiro登录验证
        subject.login(token);

        // 验证成功，更新登录时间
        userService.updateUserLoginTime(input.getUserName());
        
        // 写入客户端响应信息
        jsonMap.put("type", "success");
        jsonMap.put("url", "calendar.do");
        // 输出日志
        LOGGER.info("用户登录成功");
        
        return jsonMap;
    }
}
