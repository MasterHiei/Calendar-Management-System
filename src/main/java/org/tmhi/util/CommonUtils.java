package org.tmhi.util;

import java.util.Map;

/**
 * Author:       Hiei
 * Date:         2018/03/18
 * Description:  用于定义通用方法
 * Modified By:
 */
public class CommonUtils {
    /**
     * 将对象序列化为JSON格式
     * @param map 序列化对象
     * @return 转换后的JSON字符串
     */
    public static String getJSONFromObject(Map map) {
        return com.alibaba.fastjson.JSONObject.toJSONString(map);
    }

    /**
     * 获取增长后的版本号
     * @param version 版本号
     * @return 增长后的版本号
     */
    public static int getNextVersion(int version) {
        return version + 1;
    }
}
