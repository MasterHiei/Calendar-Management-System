package org.tmhi.dao;

import org.springframework.stereotype.Repository;
import org.tmhi.model.entity.EventEntity;

import java.util.List;

/**
 * Author:       Hiei
 * Date:         2018/04/24
 * Description:  事件信息数据访问层
 * Modified By:
 */
@Repository
public interface EventDao {

    /**
     * 根据动态参数获取事件信息
     * @param   params 参数
     * @return  事件信息集合
     * @throws  Exception 异常
     */
    List<EventEntity> queryEventByParams(EventEntity params) throws Exception;

    /**
     * 根据事件ID更新事件信息
     * @param   params 参数
     * @return  影响行数
     * @throws  Exception 异常
     */
    int updateEventById(EventEntity params) throws Exception;
}
