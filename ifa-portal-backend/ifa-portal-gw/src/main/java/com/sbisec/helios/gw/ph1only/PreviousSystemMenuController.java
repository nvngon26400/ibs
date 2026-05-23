package com.sbisec.helios.gw.ph1only;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.RtnCdEnum;
import com.sbisec.helios.ap.common.model.AuthCtrlListExclusionSettings;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前システムのメニュー情報を取得する
 *
 * @author 河口
 *
 */
@RestController
@ScreenId(groupId = "SUB99-01", id = "SUB99-99", screenNumber = "")
public class PreviousSystemMenuController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 前システムのメニュー情報を取得する
     *
     * @return 結果文字列
     * @throws Exception 例外
     */
    @PostMapping("/common/previousSystemMenu")
    public String getPreviousSystemMenu() throws Exception {
        
        String jsonString = IfaGwCommonUtil.getDataFromRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_ENVIRONMENT, false,
                "authCtrlListExclusionSettings", String.class);
        // メニュー除外設定情報が取得できる場合は、JSONから変換する。取得できない場合はnullとする。
        AuthCtrlListExclusionSettings settings = null;
        if (!StringUtils.isBlank(jsonString)) {
            settings = JsonConverter.getInstance().toObject(jsonString, AuthCtrlListExclusionSettings.class);
        }
        
        // APサーバのユーザ情報取得を呼び出し
        final UserAccount userAccount = ApiRequestUtil.invoke("previousSystemUserAdministrationService",
                "getUserAccount", new TypeReference<UserAccount>() {
                }, IfaCommonUtil.getUserAccount().getUserId(), settings);
        
        return jc.toString(IfaCommonUtil.createDtaList(userAccount.getAccessibleViewList(), ErrorLevel.INFO,
                RtnCdEnum.SUCCESS.toString()));
    }
}
