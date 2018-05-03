package org.tmhi.service;

import org.tmhi.model.entity.EventEntity;

import java.util.List;

/**
 * Author:       Hiei
 * Date:         2018/04/25
 * Description:  事件业务逻辑抽象类
 * Modified By:
 */
public interface EventService {

    /**
     * 根据动态参数查询事件信息
     *
     * @param   params 参数（e.g. eventId, userId, eventStartDatetime, eventEndDatetime）
     * @return  事件信息集合
     * @throws  Exception 异常
     */
    List<EventEntity> queryEventByParams(EventEntity params) throws Exception;
}
