package org.tmhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tmhi.facade.SessionFacade;
import org.tmhi.model.dto.UserSessionDto;
import org.tmhi.model.entity.UserEntity;
import org.tmhi.model.enums.AuthEnum;
import org.tmhi.model.form.CalendarForm;
import org.tmhi.service.UserService;
import org.tmhi.util.DateConvertUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author:       Hiei
 * Date:         2018/03/29
 * Description:  日历界面Controller
 * Modified By:
 */
@Controller
public class CalendarController {
    
    /** 用户业务逻辑对象 */
    private UserService userService;

    /** 构造器注入 */
    @Autowired
    public CalendarController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 初始化日历界面
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     * @throws Exception 异常
     */
    @RequestMapping(value = "calendar.html", method = {RequestMethod.POST, RequestMethod.GET}) 
    public ModelAndView initCalendar(HttpServletRequest request) throws Exception {

        // 返回值
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        
        // 获取Session中的用户信息
        UserSessionDto userSessionDto = SessionFacade.getUserSession(request);
        
        if (Objects.isNull(userSessionDto)) {
            // 用户信息不存在，请求重新登录
            mv.setViewName("login");
            return mv;
        }
        
        // 根据Session获取数据库中的用户信息
        UserEntity user = userService.queryUserByName(userSessionDto.getUserName());
        
        // 验证用户权限
        if (!user.getAuthCode().equals(AuthEnum.HIEI.getAuthCode()) 
                && !user.getAuthCode().equals(AuthEnum.MEMBER.getAuthCode())) {
            // 权限未通过
            mv.setViewName("error/403");
            return mv;
        }
        
        // TODO 获取个人日程表
        
        // 设置页面参数
        CalendarForm calendarForm = new CalendarForm();
        String today = DateConvertUtils.getTodayString();
        calendarForm.setTargetYear(today.split("-")[0]);
        calendarForm.setTargetMonth(today.split("-")[1]);
        calendarForm.setTargetDay(today.split("-")[2]);
        calendarForm.setFirstDayOfWeek(DateConvertUtils.getFirstDayOfWeek());
        calendarForm.setLengthOfMonth(DateConvertUtils.getLengthOfMonth());
        calendarForm.setLengthOfPrevMonth(DateConvertUtils.getLengthOfPrevMonth());
        calendarForm.setIsToday(1);
        map.put("calendarForm" ,calendarForm);
        
        mv.addAllObjects(map);
        mv.setViewName("auth/calendar");
        return mv;
    }
}
