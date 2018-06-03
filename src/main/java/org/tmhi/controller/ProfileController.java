package org.tmhi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tmhi.facade.SessionFacade;
import org.tmhi.model.dto.UserSessionDto;
import org.tmhi.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:       Hiei
 * Date:         2018/06/03
 * Description:  用户信息界面Controller
 * Modified By:
 */
@Controller
public class ProfileController {

    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(CalendarController.class);

    /**
     * 初始化用户信息界面
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     */
    @GetMapping(value = "profile.html")
    public ModelAndView initProfile(HttpServletRequest request) {

        // 返回值
        ModelAndView mav = new ModelAndView();

        // 获取request请求的IP地址
        String ip = RequestUtils.getIPAddress(request);
        // 输出日志
        LOGGER.info(ip + " - 显示用户信息");

        // 获取并返回用户信息
        UserSessionDto userSessionDto = SessionFacade.getUserSession(request);

        mav.addObject("user", userSessionDto);
        mav.setViewName("auth/profile");
        return mav;
    }
}
