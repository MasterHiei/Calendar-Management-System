package org.tmhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmhi.dao.EventDao;
import org.tmhi.facade.SessionFacade;
import org.tmhi.model.dto.CalendarSessionDto;
import org.tmhi.model.entity.EventEntity;
import org.tmhi.model.form.CalendarForm;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Author:       Hiei
 * Date:         2018/04/25
 * Description: 事件相关业务逻辑实现类
 * Modified By:
 */
@Service
public class EventServiceImpl implements EventService {

    /** 事件数据访问层对象 */
    private EventDao eventDao;

    /** 构造器注入 */
    @Autowired
    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }
    
    @Override
    public CalendarForm getEventInfo(HttpServletRequest request, CalendarForm input, Long userId)
            throws Exception {

        CalendarSessionDto sessionDto = SessionFacade.getCalendarSession(request);

        // 判断是否使用session值
        LocalDate date;
        if (input.getIsUseSession().equals(CalendarForm.USE_SESSION_YES) && Objects.nonNull(sessionDto)) {
            date = sessionDto.getDate();
        } else {
            date = LocalDate.of(input.getYear(), input.getMonth(), 1);
        }

        // 计算起始日期和结束日期
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
        params.setUserId(userId);
        params.setEventStartDate(Date.valueOf(startDate));
        params.setEventEndDate(Date.valueOf(endDate));

        List<EventEntity> eventList = eventDao.queryEventByParams(params);

        // 保存/更新session
        SessionFacade.saveCalendarSession(request, date);

        CalendarForm calendarForm = new CalendarForm();
        // 设置页面参数
        if (Objects.nonNull(eventList) && eventList.size() > 0) {
            calendarForm.setEventList(eventList);
        }
        calendarForm.setYear(date.getYear());
        calendarForm.setMonth(date.getMonthValue());

        return calendarForm;
    }
}
