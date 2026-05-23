package com.sbisec.helios.gw.systemManageMenu.portalNotification.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationNewRegisterA007RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationNewRegisterA007ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.FullWidthCharacterRedisEnvironment.FullwidthCharacterRoles;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationNewRegisterA007ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationNewRegisterA007ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0602-02_1
 * 画面名：ポータルお知らせ新規登録
 *
 * @author <author-name>
 2024/06/11 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN06", id = "SUB0602-02_1", screenNumber = "")
public class IfaPortalNotificationNewRegisterController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** "ご連絡内容" */
    private static final String CONTACTCONTENT = "ご連絡内容";
    
    /**
     * 
     * アクセス：/systemManageMenu/portalNotification/ifaPortalNotificationNewRegisterPortalNoticeNewRegistrationOkA007
     * アクションID：A007
     * アクション名：ポータルお知らせ新規登録OK
     * APIリクエスト：IfaPortalNotificationNewRegisterA007ApiRequest
     * Apiレスポンス：IfaPortalNotificationNewRegisterA007ApiResponse
     * Dtoリクエスト：IfaPortalNotificationNewRegisterA007RequestDto
     * Dtoレスポンス：IfaPortalNotificationNewRegisterA007ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/systemManageMenu/portalNotification/ifaPortalNotificationNewRegisterPortalNoticeNewRegistrationOkA007")
    public String portalNoticeNewRegistrationOkA007(@RequestBody IfaPortalNotificationNewRegisterA007ApiRequest apiReq)
            throws Exception {
        
        IfaPortalNotificationNewRegisterA007RequestDto appReq = new IfaPortalNotificationNewRegisterA007RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 画面.重要なお知らせ = false の場合、　'0':対象外 をセット
        if (StringUtils.equals(apiReq.getImportantNotification(), "false")) {
            appReq.setImportantNotification("0");
            // 画面.重要なお知らせ = true の場合、　'1':対象 をセット
        } else if (StringUtils.equals(apiReq.getImportantNotification(), "true")) {
            appReq.setImportantNotification("1");
        }
        
        // 画面.非表示 = false の場合、　'0':対象外 をセット
        if (StringUtils.equals(apiReq.getNonDisplay(), "false")) {
            appReq.setNonDisplay("0");
            // 画面.非表示 = true の場合、　'1':対象 をセット
        } else if (StringUtils.equals(apiReq.getNonDisplay(), "true")) {
            appReq.setNonDisplay("1");
        }
        
        String value = apiReq.getNotificationContent();
        String message;
        
        String utf8Str = UnicodeCheckUtil.getErrorUtf8Str(value);
        if (!utf8Str.isEmpty()) {
            // 無効文字がある場合、Redis環境変数のエラーコードに応じてメッセージを返却
            String utf8StrCheckMassageCode = "errors.specialWords";
            if (ERRORS_SPECIAL_WORDS.equals(utf8StrCheckMassageCode)) {
                message = getMessage(ERRORS_SPECIAL_WORDS, new String[] { CONTACTCONTENT, utf8Str });
                return jc.toString(
                        IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, ERRORS_SPECIAL_WORDS, message));
            } // メッセージコードが未定義の場合は無効文字チェック対象外
        }
        
        DataList<IfaPortalNotificationNewRegisterA007ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaPortalNotificationNewRegisterService", "portalNoticeNewRegistrationOkA007",
                new TypeReference<DataList<IfaPortalNotificationNewRegisterA007ResponseDto>>() {
                }, appReq);
        
        DataList<IfaPortalNotificationNewRegisterA007ApiResponse> apiRes = new DataList<IfaPortalNotificationNewRegisterA007ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
