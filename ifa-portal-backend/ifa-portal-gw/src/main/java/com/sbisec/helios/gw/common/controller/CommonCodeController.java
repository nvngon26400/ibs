package com.sbisec.helios.gw.common.controller;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * コード値公開用汎用コントローラ（API）
 *
 * @author 河口
 *
 */
@RestController
@RequestMapping(value = "/common", method = { RequestMethod.POST })
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "CommonCode", ignoreCheck = true)
public class CommonCodeController extends BaseController {
    
    // /** ロガー */
    // private static final Logger LOGGER
    // =LoggerFactory.getLogger(CommonCodeController.class);
    
    /* JSON CONVERTER */
    private JsonConverter jc = JsonConverter.getInstance();
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
    /**
     * コード値公開用汎用コントローラ（API）
     *
     * @param params パラメータ
     * @return コード情報
     * @throws Exception 任意の例外
     */
    @PostMapping("/commonCodeCodeList")
    public String handleGet(@RequestBody Map<String, String> params) throws Exception {
        
        DataList<?> result = ApiRequestUtil.invoke(ServiceNameConstants.COMMON_CODE_SERVICE, "getCommonCode",
                new TypeReference<DataList<?>>() {
                }, params.get("dispPattern"), params.get("selectPattern"), params.get("codeListId"));
        
        return jc.toString(result);
    }
}
