package com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form.IfaAuthMailAddressUpdateA004ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form.IfaAuthMailAddressUpdateA004ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0404-02_1
 * 画面名：認証用メールアドレス更新
 *
 * @author SCSK
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0404-02_1", screenNumber = "")
public class IfaAuthMailAddressUpdateController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IfaAuthMailAddressUpdateController.class);

    private JsonConverter jc = JsonConverter.getInstance();
 
    /**
    * A002 OK（更新）
    *
    * @param apiReq {@code IfaAuthMailAddressUpdateA004ApiRequest }
    * @return {@code String}
    * @throws Exception OK（更新）処理で例外が発生した場合
    */
    @PostMapping("/internalAdminMenu/authMailAddressChange/ifaAuthMailAddressUpdateOkA004")
    @ResponseBody
    @ResponseJson
    public String okA004(@RequestBody @Validated IfaAuthMailAddressUpdateA004ApiRequest apiReq) throws Exception {
        
        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        logger.debug("okA004()");

        List<IfaAuthMailAddressUpdateA004ApiResponse> apiList = 
                new ArrayList<IfaAuthMailAddressUpdateA004ApiResponse>();
        DataList<IfaAuthMailAddressUpdateA004ApiResponse> apiRes = 
                new DataList<IfaAuthMailAddressUpdateA004ApiResponse>();

        if (!isEmail(apiReq.getMailAddr())) {
            apiRes = IfaCommonUtil.createDataList(apiList, ErrorLevel.FATAL, ERRORS_ACCURATELY, 
                    getMessage(ERRORS_ACCURATELY, new String[] { "認証用メールアドレス" }));
            return jc.toString(apiRes);
        }

        // Get UserAccount.
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        String userId = apiReq.getLoginId();
        String mailAddr = apiReq.getMailAddr();
        String sysUserid = userAccount.getUserId();

        int cnt = ApiRequestUtil.invoke("cmpModifyEmailAddressForCertifyService", "updateMailInfo",
                new TypeReference<Integer>() { }, userId, mailAddr, sysUserid);

        if (cnt == 0) {
            // Deleting failed
            apiRes = IfaCommonUtil.createDataList(apiList, ErrorLevel.FATAL, ERRORS_DELETE_DATA_EXIST, 
                    getMessage(ERRORS_DELETE_DATA_EXIST, new String[] { "ログインID" }));
        } else {
            // Deleting successful
            apiRes = IfaCommonUtil.createDataList(apiList, ErrorLevel.INFO, INFO_UPDATE_COMPLETED, 
                    getMessage(INFO_UPDATE_COMPLETED, new String[] { "認証用メールアドレス" }));
        } 

        return jc.toString(apiRes);
    }

    /**
    * メール形式チェック
    *
    * @param email {@code String }
    * @return {@code boolean}
    */
    private boolean isEmail(String email) {  
        if (StringUtil.isNullOrEmpty(email)) {
            return false; 
        }
        String regex = "\\S+@\\S+\\.\\S+"; 
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
