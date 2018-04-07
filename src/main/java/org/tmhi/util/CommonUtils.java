package org.tmhi.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Author:       Hiei
 * Date:         2018/03/18
 * Description:  通用方法封装类
 * Modified By:
 */
public class CommonUtils {

    /**
     * 将Object对象转换为JSON字符串
     * 
     * @param object 需要转换的Object对象
     * @return JSON字符串
     */
    public static String convertObjectToJSONString(Object object) {
        return JSONObject.toJSONString(object);
    }
}
