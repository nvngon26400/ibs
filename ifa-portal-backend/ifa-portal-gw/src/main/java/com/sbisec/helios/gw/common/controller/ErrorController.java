package com.sbisec.helios.gw.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sbibits.earth.servlet.annotation.ScreenId;

/**
 * エラーコントローラ
 *
 * @author SCSK
 *
 */
@Controller
@ScreenId(groupId = "COMMON", id = "Error", ignoreCheck = true)
public class ErrorController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
    private static final String DEFAULT_ERROR = "500";

    @RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
    public String handle(HttpServletRequest request) throws Exception {

        String page = request.getParameter("page");

        if (page == null) {
            page = DEFAULT_ERROR;
        }

        logger.warn("ErrorController.handle : page:[{}]", page);

        return "/error/" + page;
    }
}
