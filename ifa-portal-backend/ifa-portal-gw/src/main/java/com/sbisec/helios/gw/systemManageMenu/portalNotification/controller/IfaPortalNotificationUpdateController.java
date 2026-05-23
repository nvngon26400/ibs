package com.sbisec.helios.gw.systemManageMenu.portalNotification.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA004RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA004ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA006RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA006ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA007RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA007ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.FullWidthCharacterRedisEnvironment.FullwidthCharacterRoles;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationUpdateA001ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationUpdateA001ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationUpdateA004ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationUpdateA004ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationUpdateA006ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationUpdateA006ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationUpdateA007ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationUpdateA007ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0602-04_1
 * 画面名：ポータルお知らせ更新
 *
 * @author <author-name>
 2024/06/13 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN06", id = "SUB0602-04_1", screenNumber = "")
public class IfaPortalNotificationUpdateController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** "ご連絡内容" */
    private static final String CONTACTCONTENT = "ご連絡内容";
    
    /**
     * 
     * アクセス：/systemManageMenu/portalNotification/ifaPortalNotificationUpdateInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaPortalNotificationUpdateA001ApiRequest
     * Apiレスポンス：IfaPortalNotificationUpdateA001ApiResponse
     * Dtoリクエスト：IfaPortalNotificationUpdateA001RequestDto
     * Dtoレスポンス：IfaPortalNotificationUpdateA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/systemManageMenu/portalNotification/ifaPortalNotificationUpdateInitializeA001")
    public String initializeA001(@RequestBody IfaPortalNotificationUpdateA001ApiRequest apiReq) throws Exception {
        
        IfaPortalNotificationUpdateA001RequestDto appReq = new IfaPortalNotificationUpdateA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaPortalNotificationUpdateA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaPortalNotificationUpdateService", "initializeA001",
                new TypeReference<DataList<IfaPortalNotificationUpdateA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaPortalNotificationUpdateA001ApiResponse> apiRes = new DataList<IfaPortalNotificationUpdateA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/portalNotification/ifaPortalNotificationUpdateResetA004
     * アクションID：A004
     * アクション名：リセット
     * APIリクエスト：IfaPortalNotificationUpdateA004ApiRequest
     * Apiレスポンス：IfaPortalNotificationUpdateA004ApiResponse
     * Dtoリクエスト：IfaPortalNotificationUpdateA004RequestDto
     * Dtoレスポンス：IfaPortalNotificationUpdateA004ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/systemManageMenu/portalNotification/ifaPortalNotificationUpdateResetA004")
    public String resetA004(@RequestBody IfaPortalNotificationUpdateA004ApiRequest apiReq) throws Exception {
        
        IfaPortalNotificationUpdateA004RequestDto appReq = new IfaPortalNotificationUpdateA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaPortalNotificationUpdateA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaPortalNotificationUpdateService", "resetA004",
                new TypeReference<DataList<IfaPortalNotificationUpdateA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaPortalNotificationUpdateA004ApiResponse> apiRes = new DataList<IfaPortalNotificationUpdateA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/portalNotification/ifaPortalNotificationUpdateUpdateA006
     * アクションID：A006
     * アクション名：更新
     * APIリクエスト：IfaPortalNotificationUpdateA006ApiRequest
     * Apiレスポンス：IfaPortalNotificationUpdateA006ApiResponse
     * Dtoリクエスト：IfaPortalNotificationUpdateA006RequestDto
     * Dtoレスポンス：IfaPortalNotificationUpdateA006ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/systemManageMenu/portalNotification/ifaPortalNotificationUpdateUpdateA006")
    public String updateA006(@RequestBody IfaPortalNotificationUpdateA006ApiRequest apiReq) throws Exception {
        
        IfaPortalNotificationUpdateA006RequestDto appReq = new IfaPortalNotificationUpdateA006RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String value = apiReq.getNotificationContent();
        String message;
        // キャッシュされたデータから適切なチェックルールを取得
//        FullwidthCharacterRoles checkRoles = factory.findCheckRoles("SUB0602-04_1", "notificationContent_SUB0602-04_1");
        
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
        
        DataList<IfaPortalNotificationUpdateA006ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaPortalNotificationUpdateService", "updateA006",
                new TypeReference<DataList<IfaPortalNotificationUpdateA006ResponseDto>>() {
                }, appReq);
        
        DataList<IfaPortalNotificationUpdateA006ApiResponse> apiRes = new DataList<IfaPortalNotificationUpdateA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/portalNotification/ifaPortalNotificationUpdatePortalNoticeUpdateConfirmOkA007
     * アクションID：A007
     * アクション名：ポータルお知らせ更新確認OK
     * APIリクエスト：IfaPortalNotificationUpdateA007ApiRequest
     * Apiレスポンス：IfaPortalNotificationUpdateA007ApiResponse
     * Dtoリクエスト：IfaPortalNotificationUpdateA007RequestDto
     * Dtoレスポンス：IfaPortalNotificationUpdateA007ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/systemManageMenu/portalNotification/ifaPortalNotificationUpdatePortalNoticeUpdateConfirmOkA007")
    public String portalNoticeUpdateConfirmOkA007(@RequestBody IfaPortalNotificationUpdateA007ApiRequest apiReq)
            throws Exception {
        
        IfaPortalNotificationUpdateA007RequestDto appReq = new IfaPortalNotificationUpdateA007RequestDto();
        
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
        
        DataList<IfaPortalNotificationUpdateA007ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaPortalNotificationUpdateService", "portalNoticeUpdateConfirmOkA007",
                new TypeReference<DataList<IfaPortalNotificationUpdateA007ResponseDto>>() {
                }, appReq);
        
        DataList<IfaPortalNotificationUpdateA007ApiResponse> apiRes = new DataList<IfaPortalNotificationUpdateA007ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
