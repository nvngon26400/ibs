package com.sbisec.helios.gw.testtool.controller;

import java.util.Map;

import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN91", id = "SUBEXTAPI_SYS01_01")
@RequestMapping(value = "/extapi/servicenow", method = { RequestMethod.POST })
public class IfapExtApiTestController {
    
    private static JsonConverter jc = JsonConverter.getInstance();
    
    @RequestMapping(value = "/testA1", method = { RequestMethod.POST })
    public String test(@RequestBody Map<String, String> params) throws Exception {
        
        return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "SUCCESS"));
    }

}
