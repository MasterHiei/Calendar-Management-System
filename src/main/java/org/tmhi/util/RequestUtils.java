package org.tmhi.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:       Hiei
 * Date:         2018/03/31
 * Description:  Servlet Request通用方法
 * Modified By:
 */
public class RequestUtils {
    
    /**  Ajax Http请求头*/
    private static final String X_REQUESTED_WITH = "X-Requested-With";
    /**  Http交换数据请求*/
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    
    /**
     * 判断是否为Ajax请求
     * 
     * @param request HttpServletRequest
     * @return Ajax请求‘：true 普通请求：false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader(X_REQUESTED_WITH);
        return StringUtils.hasText(header) && header.equalsIgnoreCase(XML_HTTP_REQUEST);
    }
}
