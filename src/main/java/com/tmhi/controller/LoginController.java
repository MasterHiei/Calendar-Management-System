package com.tmhi.controller;

import com.tmhi.entity.UserEntity;
import com.tmhi.form.LoginForm;
import com.tmhi.service.CommonLogic;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:       Hiei
 * Date:         2018/03/17
 * Description:
 * Modified By:
 */
@Controller
public class LoginController {
    
    /**
     * 用户登录
     * @param input 用户输入参数
     * @return JSON字符串
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String Login(@RequestBody LoginForm input) {
        // 定义返回值
        Map<String, String> jsonMap = new HashMap<>();
        
        // 输入验证(Server)
        if (!StringUtils.hasText(input.getUserName()) || !StringUtils.hasText(input.getPassword())) {
            jsonMap.put("type", "error");
            jsonMap.put("message", "请输入用户名或密码。");
            return CommonLogic.getJSONFromObject(jsonMap);
        }

        // 获取shiro subject
        Subject subject = SecurityUtils.getSubject();
        
        try {
            // shiro登录验证
            subject.login(new UsernamePasswordToken(input.getUserName(), input.getPassword()));

            // 验证成功
            jsonMap.put("type", "success");
            jsonMap.put("url", "calendar.jsp");
        } catch (AuthenticationException aex) {
            // TODO 密码验证异常处理
            aex.printStackTrace();
            // 验证失败
            switch (aex.getMessage()) {
                case "unknown":
                    jsonMap.put("type", "message");
                    jsonMap.put("message", "对不起，您输入的用户名不存在。");
                    break;
                case "db":
                    jsonMap.put("type", "error");
                    jsonMap.put("url", "error.jsp");
                    break;
                default:
                    jsonMap.put("type", "error");
                    jsonMap.put("url", "error.jsp");
                    break;    
            }
        }
        return CommonLogic.getJSONFromObject(jsonMap);
    }
}
