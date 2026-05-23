package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.controller;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.FrameworkSessionInfo;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterRegisterConfirmA003RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterRegisterConfirmA003ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.service.TokenService;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterRegisterConfirmA003ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form.IfaSelfInspectBlotterRegisterConfirmA003ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0401_02-02
 * 画面名：自己点検記録簿登録確認
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0401_02-02", screenNumber = "")
public class IfaSelfInspectBlotterRegisterConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    @Autowired
    private TokenService tokenService;
    
    /**
     * アクセス：/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterRegisterConfirmRegisterA003
     * アクションID：A003
     * アクション名：登録
     * APIリクエスト：IfaSelfInspectBlotterRegisterConfirmA003ApiRequest
     * Apiレスポンス：IfaSelfInspectBlotterRegisterConfirmA003ApiResponse
     * Dtoリクエスト：IfaSelfInspectBlotterRegisterConfirmA003RequestDto
     * Dtoレスポンス：IfaSelfInspectBlotterRegisterConfirmA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/monthlyImplementationItem/ifaSelfInspectBlotterRegisterConfirmRegisterA003")
    public String registerA003(@RequestBody IfaSelfInspectBlotterRegisterConfirmA003ApiRequest apiReq,
            HttpServletRequest request) throws Exception {
        
        IfaSelfInspectBlotterRegisterConfirmA003RequestDto appReq = new IfaSelfInspectBlotterRegisterConfirmA003RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSelfInspectBlotterRegisterConfirmA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSelfInspectBlotterRegisterConfirmService", "registerA003",
                new TypeReference<DataList<IfaSelfInspectBlotterRegisterConfirmA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaSelfInspectBlotterRegisterConfirmA003ApiResponse> apiRes = new DataList<IfaSelfInspectBlotterRegisterConfirmA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        if (appRes != null && !CollectionUtils.isEmpty(appRes.getDataList())) {
            // ユーザ共通情報.自己点検記録表示flgにServiceで算出した自己点検記録未読flgの値を設定する。
            UserAccount userAccount = IfaCommonUtil.getUserAccount();
            userAccount.setUserNeedsToReadMonthlySelfcheck(
                    appRes.getDataList().get(0).getUserNeedsToReadMonthlySelfcheck());
            
            // ユーザ共通情報を更新する。
            FrameworkSessionInfo sessionInfo = tokenService.getSessionInfo(
                    IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
            tokenService.updateSessionInfo(sessionInfo, userAccount);
            request.setAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        }
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
