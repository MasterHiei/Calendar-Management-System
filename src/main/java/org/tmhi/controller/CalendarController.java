package org.tmhi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tmhi.facade.SessionFacade;
import org.tmhi.model.dto.UserSessionDto;
import org.tmhi.model.entity.UserEntity;
import org.tmhi.model.enums.AuthEnum;
import org.tmhi.model.form.CalendarForm;
import org.tmhi.service.UserService;
import org.tmhi.util.CommonUtils;
import org.tmhi.util.DateConvertUtils;
import org.tmhi.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
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
    
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(CalendarController.class);
    
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
    public ModelAndView initCalendar(HttpServletRequest request) 
            throws Exception {

        // 返回值
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<>();

        // 获取request请求的IP地址
        String ip = RequestUtils.getIPAddress(request);
        // 输出日志
        LOGGER.info(ip + " - 初始化日历页面");
        
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
        LocalDate now = LocalDate.now();
        
        String today = DateConvertUtils.getTodayString();
        String[] dateArray = today.split("-");
        calendarForm.setTargetYear(dateArray[0]);
        calendarForm.setTargetMonth(dateArray[1]);
        calendarForm.setTargetDay(dateArray[2]);
        calendarForm.setFirstDayOfWeek(DateConvertUtils.getFirstDayOfWeek(now));
        calendarForm.setLengthOfMonth(DateConvertUtils.getLengthOfMonth(now));
        calendarForm.setLengthOfPrevMonth(DateConvertUtils.getLengthOfPrevMonth(now));
        calendarForm.setIsToday(1);
        map.put("calendarForm" ,calendarForm);
        
        mv.addAllObjects(map);
        mv.setViewName("auth/calendar");
        return mv;
    }

    /**
     * 变更日历
     *
     * @param request HttpServletRequest
     * @param input 参数               
     * @return Map对象
     * @throws Exception 异常
     */
    @RequestMapping(value = "doDateChange.html", method = {RequestMethod.POST})
    @ResponseBody
    public Map doDateChange(HttpServletRequest request, @RequestBody CalendarForm input) 
            throws Exception {

        // 处理模式：前月
        final String MODE_PREV = "prev";
        // 处理模式：次月
        final String MODE_NEXT = "next";
        
        // 返回值
        Map<String, Object> jsonMap = new HashMap<>();

        // 获取request请求的IP地址
        String ip = RequestUtils.getIPAddress(request);
        // 输出日志
        LOGGER.info(ip + " - 变更日历");

        // 获取Session中的用户信息
        UserSessionDto userSessionDto = SessionFacade.getUserSession(request);

        if (Objects.isNull(userSessionDto)) {
            // 用户信息不存在，请求重新登录
            jsonMap.put("type", "error");
            jsonMap.put("url", "login.html");
            return jsonMap;
        }

        // 根据Session获取数据库中的用户信息
        UserEntity user = userService.queryUserByName(userSessionDto.getUserName());

        // 验证用户权限
        if (!user.getAuthCode().equals(AuthEnum.HIEI.getAuthCode())
                && !user.getAuthCode().equals(AuthEnum.MEMBER.getAuthCode())) {
            // 权限未通过
            jsonMap.put("type", "error");
            jsonMap.put("url", "403.html");
            return jsonMap;
        }

        // TODO 获取个人日程表

        // 设置页面参数
        CalendarForm calendarForm = new CalendarForm();
        
        // 日期变更处理
        LocalDate targetDate = DateConvertUtils.getLocalDateByYMD(input.getTargetYear(), input.getTargetMonth(), input.getTargetDay());
        switch (input.getMode()) {
            case MODE_PREV:
                targetDate = targetDate.minusMonths(1);
                break;
            case MODE_NEXT:
                targetDate = targetDate.plusMonths(1);
                break;
        }
        
        calendarForm.setTargetYear(String.valueOf(targetDate.getYear()));
        calendarForm.setTargetMonth(String.valueOf(targetDate.getMonthValue()));
        calendarForm.setTargetDay(String.valueOf(targetDate.getDayOfMonth()));
        calendarForm.setFirstDayOfWeek(DateConvertUtils.getFirstDayOfWeek(targetDate));
        calendarForm.setLengthOfMonth(DateConvertUtils.getLengthOfMonth(targetDate));
        calendarForm.setLengthOfPrevMonth(DateConvertUtils.getLengthOfPrevMonth(targetDate));
        calendarForm.setIsToday(1);

        jsonMap.put("type", "success");
        jsonMap.put("data" ,CommonUtils.convertObjectToJSONString(calendarForm));
        return jsonMap;
    }
}
