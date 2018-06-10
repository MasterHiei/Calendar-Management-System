package org.tmhi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tmhi.facade.SessionFacade;
import org.tmhi.model.dto.UserSessionDto;
import org.tmhi.model.entity.UserEntity;
import org.tmhi.model.form.ProfileForm;
import org.tmhi.service.UserService;
import org.tmhi.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

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

    /** 用户业务逻辑对象 */
    private UserService userService;

    /** 构造器注入 */
    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 初始化用户信息界面
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     */
    @GetMapping(value = "profile.html")
    public ModelAndView initProfile(HttpServletRequest request) throws Exception {

        // 返回值
        ModelAndView mav = new ModelAndView();

        // 获取request请求的IP地址
        String ip = RequestUtils.getIPAddress(request);
        // 输出日志
        LOGGER.info(ip + " - 显示用户信息");

        // 获取并返回用户信息
        UserSessionDto userSessionDto = SessionFacade.getUserSession(request);
        if (Objects.isNull(userSessionDto) || !StringUtils.hasText(userSessionDto.getUserName())) {
            mav.setViewName("login");
            return mav;
        }

        UserEntity user = userService.queryUserByName(userSessionDto.getUserName());
        ProfileForm profileForm = new ProfileForm();
        profileForm.setUserName(user.getUserName());
        profileForm.setMailAddress(user.getMailAddress());
        profileForm.setUserAvatar(user.getUserAvatar());
        profileForm.setNowAction(user.getNowAction());

        mav.addObject("profile", profileForm);
        mav.setViewName("auth/profile");
        return mav;
    }
}
