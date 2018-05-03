package org.tmhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author:       Hiei
 * Date:         2018/04/30
 * Description: 404错误页面Controller
 * Modified By:
 */
public class NotFoundController {

    /**
     * 初始化404错误界面
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "404.html", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView initNotFound() {
        // 返回值
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error/404");
        return mv;
    }
}
