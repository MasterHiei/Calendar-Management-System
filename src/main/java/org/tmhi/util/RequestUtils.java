package org.tmhi.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:       Hiei
 * Date:         2018/03/31
 * Description:  Servlet Request相关通用方法封装类
 * Modified By:
 */
public class RequestUtils {
    
    /** Ajax Http请求头 */
    private static final String X_REQUESTED_WITH = "X-Requested-With";
    /** Http交换数据请求 */
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    /** unknown未知IP地址 */
    private static final String UNKNOWN = "unknown";
    /** Proxy-Client-IP代理请求头 */
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    /** WL-Proxy-Client-IP代理请求头 */
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    /** HTTP_CLIENT_IP代理请求头 */
    private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    /** HTTP_X_FORWARDED_FOR代理请求头 */
    private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    /**
     * 获取request请求的IP地址
     *
     * @param request HttpServletRequest
     * @return IP地址
     */
    public static String getIPAddress(HttpServletRequest request) {
        
        // 获取request请求中的真实IP地址
        String ip = request.getHeader("x-forwarded-for");
        
        // 获取下列条件中最先捕获到的IP地址
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_CLIENT_IP);
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_X_FORWARDED_FOR);
        }
        if (!StringUtils.hasText(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        // 返回获取到的IP地址
        return ip;
    }
    
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
