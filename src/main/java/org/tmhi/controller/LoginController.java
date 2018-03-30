package org.tmhi.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmhi.model.form.LoginForm;
import org.tmhi.util.CommonUtils;

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
public class LoginController extends BaseController {
    
    /**
     * 用户登录
     * @param input 用户输入参数
     * @return JSON字符串
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String Login(@RequestBody @Validated LoginForm input, BindingResult result) {
        // 输出日志
        LOGGER.info("用户登录");
        // 定义返回值
        Map<String, String> jsonMap = new HashMap<>();
        
        // Validated输入验证
        if (result.hasErrors()) {
            // 验证未通过，返回最初的错误信息
            jsonMap.put("type", "message");
            jsonMap.put("message", result.getAllErrors().get(0).getDefaultMessage());
            return CommonUtils.getJSONFromObject(jsonMap);
        }

        // 获取shiro subject
        Subject subject = SecurityUtils.getSubject();

        try {
            // 生成用户Token
            UsernamePasswordToken token = new UsernamePasswordToken(input.getUserName(), input.getPassword());
            // RememberMe
            token.setRememberMe(Objects.nonNull(input.getRememberMe()));
            // shiro登录验证
            subject.login(token);
            
            // 验证成功
            jsonMap.put("type", "success");
            jsonMap.put("url", "calendar.do");
            // 输出日志
            LOGGER.info("用户登录成功");
        } catch (UnknownAccountException uaex) {
            // 账户不存在
            jsonMap.put("type", "message");
            jsonMap.put("message", "对不起，您输入的用户名不存在。");
        } catch (IncorrectCredentialsException icex) {
            // 密码不匹配
            jsonMap.put("type", "message");
            jsonMap.put("message", "对不起，您输入的密码不正确。");
        } catch (Exception ex) {
            // 其他异常
            jsonMap.put("type", "error");
            jsonMap.put("url", "404.do");
            // 输出日志
            LOGGER.error("用户登录异常：", ex);
        }
        return CommonUtils.getJSONFromObject(jsonMap);
    }
}
