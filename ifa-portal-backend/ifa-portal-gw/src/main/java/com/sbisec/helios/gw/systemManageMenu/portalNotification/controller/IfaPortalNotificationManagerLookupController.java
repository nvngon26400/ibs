package com.sbisec.helios.gw.systemManageMenu.portalNotification.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA008RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA008ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationManagerLookupA002ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationManagerLookupA002ApiResponse;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationManagerLookupA008ApiRequest;
import com.sbisec.helios.gw.systemManageMenu.portalNotification.form.IfaPortalNotificationManagerLookupA008ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0602-01
 * 画面名：ポータルお知らせ（管理者用）照会
 *
 * @author <author-name>
 2024/06/06 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN06", id = "SUB0602-01", screenNumber = "")
public class IfaPortalNotificationManagerLookupController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/systemManageMenu/portalNotification/ifaPortalNotificationManagerLookupDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaPortalNotificationManagerLookupA002ApiRequest
     * Apiレスポンス：IfaPortalNotificationManagerLookupA002ApiResponse
     * Dtoリクエスト：IfaPortalNotificationManagerLookupA002RequestDto
     * Dtoレスポンス：IfaPortalNotificationManagerLookupA002ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/systemManageMenu/portalNotification/ifaPortalNotificationManagerLookupDisplayA002")
    public String displayA002(@RequestBody IfaPortalNotificationManagerLookupA002ApiRequest apiReq) throws Exception {

        IfaPortalNotificationManagerLookupA002RequestDto appReq = new IfaPortalNotificationManagerLookupA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaPortalNotificationManagerLookupA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaPortalNotificationManagerLookupService",
                "displayA002", new TypeReference<DataList<IfaPortalNotificationManagerLookupA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaPortalNotificationManagerLookupA002ApiResponse> apiRes = new DataList<IfaPortalNotificationManagerLookupA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/systemManageMenu/portalNotification/ifaPortalNotificationManagerLookupPortalNoticeDeletionConfirmOkA008
     * アクションID：A008
     * アクション名：ポータルお知らせ削除確認OK
     * APIリクエスト：IfaPortalNotificationManagerLookupA008ApiRequest
     * Apiレスポンス：IfaPortalNotificationManagerLookupA008ApiResponse
     * Dtoリクエスト：IfaPortalNotificationManagerLookupA008RequestDto
     * Dtoレスポンス：IfaPortalNotificationManagerLookupA008ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/systemManageMenu/portalNotification/ifaPortalNotificationManagerLookupPortalNoticeDeletionConfirmOkA008")
    public String portalNoticeDeletionConfirmOkA008(@RequestBody IfaPortalNotificationManagerLookupA008ApiRequest apiReq) throws Exception {

        IfaPortalNotificationManagerLookupA008RequestDto appReq = new IfaPortalNotificationManagerLookupA008RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaPortalNotificationManagerLookupA008ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaPortalNotificationManagerLookupService",
                "portalNoticeDeletionConfirmOkA008", new TypeReference<DataList<IfaPortalNotificationManagerLookupA008ResponseDto>>() {
                }, appReq);
        
        DataList<IfaPortalNotificationManagerLookupA008ApiResponse> apiRes = new DataList<IfaPortalNotificationManagerLookupA008ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

