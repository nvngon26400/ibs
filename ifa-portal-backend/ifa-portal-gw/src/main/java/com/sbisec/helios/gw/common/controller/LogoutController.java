package com.sbisec.helios.gw.common.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.form.LogoutResponseForm;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ログアウトコントローラ
 *
 * @author SCSK
 *
 */
@RestController
@ScreenId(groupId = "COMMON", id = "Logout", ignoreCheck = true)
public class LogoutController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final String CCS_LOGOUT_URL = "CCS_LOGOUT_URL";
    
    /**
     * ログアウト
     *
     * @return 空データリスト
     * @throws Exception 例外
     */
    @RequestMapping(value = "/common/logout", method = { RequestMethod.GET, RequestMethod.POST })
    public String handleLogout() throws Exception {
        
        // Redisを削除する
        try {
            IfaGwCommonUtil.evictCustomerCommon();
        } catch (Exception e) {
        }
        
        try {
            IfaCommonUtil.logout();
        } catch (Exception e) {
        }
        
        List<LogoutResponseForm> dataList = null;
        try {
            // CCSログアウトURLを取得する
            String ccsLogoutUrl = ApiRequestUtil.invoke("medSystemVariableService", "getMedSystemVariable",
                    new TypeReference<String>() {
                    }, CCS_LOGOUT_URL);
            
            // 取得できた場合、レスポンスにデータをセットする
            if (!StringUtils.isEmpty(ccsLogoutUrl)) {
                dataList = new ArrayList<>();
                LogoutResponseForm resp = new LogoutResponseForm();
                resp.setCcsLogoutUrl(ccsLogoutUrl);
                dataList.add(resp);
            }
        } catch (Exception e) {
        }
        
        return jc.toString(IfaCommonUtil.createDataList(dataList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), ""));
    }
}
