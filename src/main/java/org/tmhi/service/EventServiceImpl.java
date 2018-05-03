package org.tmhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmhi.dao.EventDao;
import org.tmhi.model.entity.EventEntity;

import java.util.List;

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
    public List<EventEntity> queryEventByParams(EventEntity params) throws Exception {
        return eventDao.queryEventByParams(params);
    }
}
