package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA006ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaResponseStatusUpdateA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaResponseStatusUpdateA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaResponseStatusUpdateA006ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaResponseStatusUpdateA006ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020301_03-01_1
 * 画面名：対応状況更新
 *
 * @author <author-name>
 2024/05/28 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020301_03-01_1", screenNumber = "")
public class IfaResponseStatusUpdateController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/IfaResponseStatusUpdateInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaResponseStatusUpdateA001ApiRequest
     * Apiレスポンス：IfaResponseStatusUpdateA001ApiResponse
     * Dtoリクエスト：IfaResponseStatusUpdateA001RequestDto
     * Dtoレスポンス：IfaResponseStatusUpdateA001ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaResponseStatusUpdateInitializeA001")
    public String initializeA001(@RequestBody IfaResponseStatusUpdateA001ApiRequest apiReq) throws Exception {

        IfaResponseStatusUpdateA001RequestDto appReq = new IfaResponseStatusUpdateA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaResponseStatusUpdateA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaResponseStatusUpdateService",
                "initializeA001", new TypeReference<DataList<IfaResponseStatusUpdateA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaResponseStatusUpdateA001ApiResponse> apiRes = new DataList<IfaResponseStatusUpdateA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/IfaResponseStatusUpdateResponseStatusUpdateConfirmOkA006
     * アクションID：A006
     * アクション名：対応状況更新確認OK
     * APIリクエスト：IfaResponseStatusUpdateA006ApiRequest
     * Apiレスポンス：IfaResponseStatusUpdateA006ApiResponse
     * Dtoリクエスト：IfaResponseStatusUpdateA006RequestDto
     * Dtoレスポンス：IfaResponseStatusUpdateA006ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaResponseStatusUpdateResponseStatusUpdateConfirmOkA006")
    public String responseStatusUpdateConfirmOkA006(@RequestBody IfaResponseStatusUpdateA006ApiRequest apiReq) throws Exception {

        IfaResponseStatusUpdateA006RequestDto appReq = new IfaResponseStatusUpdateA006RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaResponseStatusUpdateA006ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaResponseStatusUpdateService",
                "responseStatusUpdateConfirmOkA006", new TypeReference<DataList<IfaResponseStatusUpdateA006ResponseDto>>() {
                }, appReq);
        
        DataList<IfaResponseStatusUpdateA006ApiResponse> apiRes = new DataList<IfaResponseStatusUpdateA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

