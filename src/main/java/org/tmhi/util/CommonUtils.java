package org.tmhi.util;

/**
 * Author:       Hiei
 * Date:         2018/03/18
 * Description:  用于封装通用方法
 * Modified By:
 */
public class CommonUtils {

    /**
     * 获取增长后的版本号
     * @param version 版本号
     * @return 增长后的版本号
     */
    public static int getNextVersion(int version) {
        return version + 1;
    }
}
