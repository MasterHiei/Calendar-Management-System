package org.tmhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:       Hiei
 * Date:         2018/03/29
 * Description:  日历界面Controller
 * Modified By:
 */
@Controller
public class CalendarController {

    /**
     * 初始化日历界面
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     * @throws Exception 异常
     */
    @RequestMapping(value = "calendar.html", method = RequestMethod.POST) 
    public ModelAndView initCalendar(HttpServletRequest request) throws Exception {
        
        // 检查用户权限
        
        // 返回值
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("auth/calendar");
        return mv;
    }
}
