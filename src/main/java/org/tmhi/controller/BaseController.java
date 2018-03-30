package org.tmhi.controller;

import lombok.experimental.PackagePrivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author:       Hiei
 * Date:         2018/03/29
 * Description:  Controller基类
 * Modified By:
 */
@PackagePrivate
public class BaseController {
    
    /** logger处理相关类 */
    final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
}
