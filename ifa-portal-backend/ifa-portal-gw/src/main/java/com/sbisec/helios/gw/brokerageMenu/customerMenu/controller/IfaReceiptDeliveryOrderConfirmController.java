package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderConfirmA001DtoResponse;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaReceiptDeliveryOrderConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-08_2
 * 画面名：現引現渡注文確認
 * 2024/04/01 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-08_2", screenNumber = "", ignoreCheck = true)
public class IfaReceiptDeliveryOrderConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderConfirmOrderPlacementA001
     * アクションID：A001
     * アクション名：注文発注
     * APIリクエスト：IfaReceiptDeliveryOrderConfirmA001ApiRequest
     * Apiレスポンス：IfaReceiptDeliveryOrderConfirmA001ApiResponse
     * Dtoリクエスト：IfaReceiptDeliveryOrderConfirmA001DtoRequest
     * Dtoレスポンス：IfaReceiptDeliveryOrderConfirmA001DtoResponse
     *
     * @param apiReq {@code IfaReceiptDeliveryOrderConfirmA001ApiRequest }
     * @return {@code String}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaReceiptDeliveryOrderConfirmOrderPlacementA001")
    public String orderPlacementA001(@RequestBody IfaReceiptDeliveryOrderConfirmA001ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaReceiptDeliveryOrderConfirmA001DtoRequest appReq = new IfaReceiptDeliveryOrderConfirmA001DtoRequest();
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaReceiptDeliveryOrderConfirmA001DtoResponse> appResA = ApiRequestUtil.invoke(
                "cmpIfaReceiptDeliveryOrderConfirmService", "orderPlacementA001a",
                new TypeReference<DataList<IfaReceiptDeliveryOrderConfirmA001DtoResponse>>() {
                }, appReq);
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            DataList<IfaReceiptDeliveryOrderConfirmA001ApiResponse> apiRes = new DataList<IfaReceiptDeliveryOrderConfirmA001ApiResponse>();
            BeanUtils.copyProperties(apiRes, appResA);
            return jc.toString(apiRes);
        }
        
        BeanUtils.copyProperties(appReq, appResA.getDataList().get(0).getReq());
        DataList<IfaReceiptDeliveryOrderConfirmA001DtoResponse> appResB = ApiRequestUtil.invoke(
                "cmpIfaReceiptDeliveryOrderConfirmService", "orderPlacementA001b",
                new TypeReference<DataList<IfaReceiptDeliveryOrderConfirmA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaReceiptDeliveryOrderConfirmA001ApiResponse> apiRes = new DataList<IfaReceiptDeliveryOrderConfirmA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appResB);
        return jc.toString(apiRes);
    }
}
