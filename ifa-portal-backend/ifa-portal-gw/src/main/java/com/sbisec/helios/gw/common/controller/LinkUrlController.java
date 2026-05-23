package com.sbisec.helios.gw.common.controller;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dto.LinkUrlDto;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * リンクURLコントローラ
 *
 * @author 河口
 *
 */
@RestController
@RequestMapping(value = "/common", method = { RequestMethod.POST })
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "LinkUrl", ignoreCheck = true)
public class LinkUrlController extends BaseController {
    
    // /** ロガー */
    // private static final Logger LOGGER
    // =LoggerFactory.getLogger(LinkUrlController.class);
    
    /* JSON CONVERTER */
    private JsonConverter jc = JsonConverter.getInstance();
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
    /**
     * リンクURL／リンクURL取得
     *
     * @param params JSONパラメータ
     * @return リンク情報
     */
    @GetMapping("/ifaLinkUrlUrlAcquire")
    public String handlePostGetLinkUrl(@RequestBody Map<String, String> params) throws Exception {
        
        LinkUrlDto result = ApiRequestUtil.invoke(ServiceNameConstants.LINK_URL_SERVICE, "getLinkUrl",
                new TypeReference<LinkUrlDto>() {
                }, params.get("urlId"), params.get("patternId"), params.get("httpMethod"));
        
        return jc.toString(result);
    }
    
    /**
     * リンクURL／リンクHTML取得
     *
     * @param params JSONパラメータ
     * @return リンク先から取得したHTML
     */
    @GetMapping("/ifaLinkUrlHtmlAcquire")
    public String handlePostGetLinkHtml(@RequestBody Map<String, String> params) throws Exception {
        
        Map<String, String> result = ApiRequestUtil.invoke(ServiceNameConstants.LINK_URL_SERVICE, "getLinkHtml",
                new TypeReference<Map<String, String>>() {
                }, params.get("urlId"), params.get("patternId"), params.get("httpMethod"));
        
        return jc.toString(result);
    }
}
