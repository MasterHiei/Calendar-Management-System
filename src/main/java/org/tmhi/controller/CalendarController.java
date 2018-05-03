package org.tmhi.controller;

import com.alibaba.fastjson.JSONObject;
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
import org.tmhi.model.entity.EventEntity;
import org.tmhi.model.form.CalendarForm;
import org.tmhi.service.EventService;
import org.tmhi.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
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


    /** DateTimeFormatter */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /** 事件业务逻辑对象 */
    private EventService eventService;

    /** 构造器注入 */
    @Autowired
    public CalendarController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * 初始化日历界面
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     */
    @RequestMapping(value = "calendar.html", method = {RequestMethod.GET})
    public ModelAndView initCalendar(HttpServletRequest request) {

        // 返回值
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<>();

        // 获取request请求的IP地址
        String ip = RequestUtils.getIPAddress(request);
        // 输出日志
        LOGGER.info(ip + " - 显示日历");

        map.put("calendarForm" ,new CalendarForm());
        mv.addAllObjects(map);
        mv.setViewName("auth/calendar");
        return mv;
    }
    
    /**
     * 变更日历界面
     *
     * @param request HttpServletRequest
     * @param input 参数               
     * @return Map对象
     * @throws Exception 异常
     */
    @RequestMapping(value = "getEventList.html", method = {RequestMethod.POST})
    @ResponseBody
    public Map setCalendar(HttpServletRequest request, @RequestBody CalendarForm input) 
            throws Exception {
        
        Map<String, Object> jsonMap = new HashMap<>();

        // 获取request请求的IP地址
        String ip = RequestUtils.getIPAddress(request);
        LOGGER.info(ip + " - 获取事件列表");

        // 获取Session中的用户信息
        UserSessionDto userSessionDto = SessionFacade.getUserSession(request);
        if (Objects.isNull(userSessionDto)) {
            jsonMap.put("type", "error");
            jsonMap.put("url", "login.html");
            return jsonMap;
        }

        CalendarForm calendarForm = new CalendarForm();
        
        if (Objects.isNull(input.getYear()) || Objects.isNull(input.getMonth())) {
            jsonMap.put("type", "error");
            jsonMap.put("url", "404.html");
            return jsonMap;
        }

        // 计算起始日期和结束日期
        LocalDate date = LocalDate.of(input.getYear(), input.getMonth(), 1);
        LocalDate startDate = date;
        LocalDate endDate = date.withDayOfMonth(date.lengthOfMonth());
        
        if (date.getDayOfWeek().getValue() < 7) {
            startDate = date.minusMonths(1).withDayOfMonth(date.minusMonths(1).lengthOfMonth()).minusDays(date.getDayOfWeek().getValue() - 1);
            endDate = endDate.plusDays((5 * 7) - date.getDayOfWeek().getValue() - date.lengthOfMonth());
        } else {
            endDate = endDate.plusDays((5 * 7) - date.lengthOfMonth());
        }
        
        // 获取日程列表
        EventEntity params = new EventEntity();
        params.setUserId(userSessionDto.getUserId());
        params.setEventStartDate(Date.valueOf(startDate));
        params.setEventEndDate(Date.valueOf(endDate));
        
        List<EventEntity> eventList = eventService.queryEventByParams(params);
        
        // 设置页面参数
        if (Objects.nonNull(eventList) && eventList.size() > 0) {
            calendarForm.setEventList(eventList);
        }
       
        jsonMap.put("type", "success");
        jsonMap.put("data" ,JSONObject.toJSONString(calendarForm));
        return jsonMap;
    }
}
