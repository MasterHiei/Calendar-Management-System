package org.tmhi.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.tmhi.facade.SessionFacade;
import org.tmhi.model.dto.UserSessionDto;
import org.tmhi.model.entity.UserEntity;
import org.tmhi.service.UserService;
import org.tmhi.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author:       Hiei
 * Date:         2018/04/06
 * Description:  用户登录状态检测用拦截器
 * Modified By:
 */
public class LoginInterceptor implements HandlerInterceptor {

    /** 登录页面URL*/
    private static final String LOGIN_PAGE_URL = "login.html";
    /** 用户登录请求URL*/
    private static final String USE_LOGIN_URL = "doLogin.html";
    
    /** 用户业务逻辑对象 */
    private UserService userService;

    /** 构造器注入 */
    @Autowired
    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    /**
     * 调用Controller前的预处理（用户登录状态验证）
     */
    @ResponseBody
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        
        // 获取request请求的IP地址
        String ip = RequestUtils.getIPAddress(request);
        
        // 获取请求的地址
        String requestURL = request.getRequestURL().toString();
        // 获取当前session
        HttpSession session = request.getSession(false);
        // 判断请求类型
        if (requestURL.contains(LOGIN_PAGE_URL) || requestURL.contains(USE_LOGIN_URL)) {
            // 登录相关请求，允许通过
            return true;
        } else {
            // 非登录相关请求，需要验证session中的用户信息
            UserSessionDto userSession = SessionFacade.getUserSession(request);
            
            // 创建Map对象
            Map<String, String> jsonMap = new HashMap<>();
            
            if (Objects.nonNull(userSession)) {
                // session存在，获取用户信息
                UserEntity user = userService.queryUserByName(userSession.getUserName());
                
                if (Objects.isNull(user)) {
                    // 用户不存在，抛出异常
                    throw new Exception(ip + " - 用户信息获取异常");
                }
                
                // 验证SessionID
                if (!session.getId().equals(user.getSessionId())) {
                    // 验证失败，判断是否为Ajax请求
                    if (RequestUtils.isAjaxRequest(request)) {
                        // 创建返回值
                        jsonMap.put("type", "error");
                        jsonMap.put("url", "multiLoginError.html");
                        // 转换为JSON字符串并返回客户端
                        response.getWriter().print(JSONObject.toJSONString(jsonMap));
                    } else {
                        // 重定向至重复登录错误页面
                        response.sendRedirect("multiLoginError.html");
                    }
                    return false;
                }
                // 验证通过
                return true;
            } else {
                // 用户Session不存在，返回登录页面
                // 判断是否为Ajax请求
                if (RequestUtils.isAjaxRequest(request)) {
                    // 创建返回值
                    jsonMap.put("type", "transition");
                    jsonMap.put("url", "login.html");
                    // 转换为JSON字符串并返回客户端
                    response.getWriter().print(JSONObject.toJSONString(jsonMap));
                } else {
                    // 重定向至登录页面
                    response.sendRedirect("login.html");
                }
                return false;
            }
        }
    }

    /**
     * 执行Controller后的处理
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) {}

    /**
     * 完成DispatcherServlet请求后的处理
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {}
}
