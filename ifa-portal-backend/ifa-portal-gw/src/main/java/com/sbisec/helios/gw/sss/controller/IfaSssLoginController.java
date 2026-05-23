package com.sbisec.helios.gw.sss.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.sss.model.SssCertificationInfoModel;
import com.sbisec.helios.ap.sss.util.SssApiReturnCode;
import com.sbisec.helios.ap.sss.util.SssApiUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.sss.form.IfaSssLoginA001ApiRequest;
import com.sbisec.helios.gw.sss.form.IfaSssLoginA001ApiResponse;

/**
 * 画面ID：SUB08-01 
 * 画面名：SSS(債券メニュー)
 *
 * @author SBI大連 劉
 *
 * 2024/08/02 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SsoLogin", ignoreCheck = true)
public class IfaSssLoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IfaSssLoginController.class);

    private JsonConverter jc = JsonConverter.getInstance();

    private static final String SSS_API_KEY = "b955def0-9c00-49e1-a851-5bc62f09c5b9";

    private static final String SSS_403_KEY = "403";

    private static final String SSS_403_ERROR_CODE = "errors.sssAuthError";

    private static final String SSS_LOGIN_URL = "SSS_LOGIN_URL";

    private static final String SSS_NEW_LOGIN_URL = "SSS_NEW_LOGIN_URL";

    /**
     * A001 初期化
     *
     * @return {@code String}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/sss/ifaSssLoginInitializeA001")
    @ResponseBody
    @ResponseJson
    public String initializeA001(@RequestBody IfaSssLoginA001ApiRequest apiReq) throws Exception {
        final long start = System.currentTimeMillis();
        logger.debug("IfaSssLoginController.initializeA001 >> {}", hashCode());

        // =============================================================================
        // okhttp3でSSS認証情報を取得(get SssCertificationInfo by okhttp3)
        // =============================================================================
        String apiKey = SSS_API_KEY;
        String sssType = apiReq.getSssType();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String userId = userAccount.getUserId();
        DataList<SssCertificationInfoModel> dataList = new DataList<SssCertificationInfoModel>();
        dataList = ApiRequestUtil.invoke("sssCertificationInfoService", "getSssCertificationInfo",
                new TypeReference<DataList<SssCertificationInfoModel>>() {
                }, userId, apiKey, sssType);

        // =============================================================================
        // APIで発生したエラーごとに表示する画面の制御を行う。
        // =============================================================================
        IfaSssLoginA001ApiResponse apiRes = new IfaSssLoginA001ApiResponse();
        if (SssApiReturnCode.SSS_API_CONNECTION_ERROR_CODE.equals(dataList.getReturnCode())) {
            // SSS接続 warning画面用のフラグとエラーメッセージを設定
            apiRes.setErrorFlg(SssApiUtil.ErrorFlg.SCREEN_ID_WARNING);
            apiRes.setErrorMessage(getMessage(dataList.getMessage(), new String[] {}));
        } else if (SssApiReturnCode.SSS_API_ERROR_CODE.equals(dataList.getReturnCode())) {
            // SSS接続 error画面用のフラグとエラーコード、エラーメッセージを設定
            apiRes.setErrorFlg(SssApiUtil.ErrorFlg.SCREEN_ID_ERROR);
            apiRes.setErrorCode(dataList.getTitle());
            apiRes.setErrorMessage(dataList.getMessage());
        } else if (dataList == null || dataList.getDataList() == null || dataList.getDataList().size() <= 0) {
            logger.info("IfaSssLoginController.initializeA001: 403 error other than SSS authentication");
            apiRes.setErrorFlg(SSS_403_KEY);
            apiRes.setErrorMessage(getMessage(SSS_403_ERROR_CODE, new String[] {}));
        } else {
            // sssCode取得
            String sssCode = dataList.getDataList().get(0).getCode();
            String loginUrl = SSS_LOGIN_URL;
            if ("1".equals(sssType)) {
                loginUrl = SSS_NEW_LOGIN_URL;
            }
            String sssLoginUrl = ApiRequestUtil.invoke(ServiceNameConstants.MED_SYSTEM_VARIABLE_SERVICE,
                    "getMedSystemVariable", new TypeReference<String>() {
                    }, loginUrl);
            if (sssLoginUrl == null) {
                apiRes.setErrorFlg(SSS_403_KEY);
                apiRes.setErrorMessage(getMessage(SSS_403_ERROR_CODE, new String[] {}));
            } else {
                // Create token.
                String token = new Object().hashCode() + String.valueOf(System.currentTimeMillis());
                logger.info("IfaSssLoginController.initializeA001: token:[{}]", token);
                apiRes.setSssLoginParam(sssCode);
                apiRes.setSssLoginUrl(sssLoginUrl);
            }
        }
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(apiRes);

        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        // 検索結果をJsonで戻す
        return resultJson;
    }
}
