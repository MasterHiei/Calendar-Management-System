package org.tmhi.service;

import org.tmhi.model.form.CalendarForm;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:       Hiei
 * Date:         2018/04/25
 * Description:  事件业务逻辑抽象类
 * Modified By:
 */
public interface EventService {

    /**
     * 获取事件信息
     *
     * @param   request HttpServletRequest
     * @param   inputForm 日历页面传递的参数
     * @param   userId 用户ID
     * @return  日历页面
     * @throws  Exception 异常
     */
    CalendarForm getEventInfo(HttpServletRequest request, CalendarForm inputForm, Long userId) throws Exception;
}
