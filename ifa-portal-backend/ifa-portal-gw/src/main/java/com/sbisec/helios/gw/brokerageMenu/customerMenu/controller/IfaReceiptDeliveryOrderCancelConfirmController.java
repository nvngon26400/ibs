package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderCancelConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderCancelConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-09_1
 * 画面名：現引現渡注文取消確認
 *
 * @author SCSK
 2024/05/21 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-09_1", screenNumber = "")
public class IfaReceiptDeliveryOrderCancelConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaReceiptDeliveryOrderCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaReceiptDeliveryOrderCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaReceiptDeliveryOrderCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaReceiptDeliveryOrderCancelConfirmA001ApiRequest apiReq)
            throws Exception {
        
        IfaReceiptDeliveryOrderCancelConfirmA001RequestDto appReq = new IfaReceiptDeliveryOrderCancelConfirmA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaReceiptDeliveryOrderCancelConfirmService", "initializeA001",
                new TypeReference<DataList<IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaReceiptDeliveryOrderCancelConfirmA001ApiResponse> apiRes = new DataList<IfaReceiptDeliveryOrderCancelConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderCancelConfirmOrderPlacementA002
     * アクションID：A002
     * アクション名：注文発注
     * APIリクエスト：IfaReceiptDeliveryOrderCancelConfirmA002ApiRequest
     * Apiレスポンス：IfaReceiptDeliveryOrderCancelConfirmA002ApiResponse
     * Dtoリクエスト：IfaReceiptDeliveryOrderCancelConfirmA002RequestDto
     * Dtoレスポンス：IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderCancelConfirmOrderPlacementA002")
    public String orderPlacementA002(@RequestBody IfaReceiptDeliveryOrderCancelConfirmA002ApiRequest apiReq) throws Exception {

        IfaReceiptDeliveryOrderCancelConfirmA002RequestDto appReq = new IfaReceiptDeliveryOrderCancelConfirmA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaReceiptDeliveryOrderCancelConfirmService", "orderPlacementA002b",
                new TypeReference<DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaReceiptDeliveryOrderCancelConfirmA002ApiResponse> apiRes = new DataList<IfaReceiptDeliveryOrderCancelConfirmA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

