package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002bDtoResponse;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCancelConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCancelConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0208-04_1", screenNumber = "")
public class IfaDomesticStockOrderCancelConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticStockOrderCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderCancelConfirmA001DtoRequest
     * Dtoレスポンス：IfaDomesticStockOrderCancelConfirmA001DtoResponse
     *
     * @param apiReq リクエストボディ
     * @return 画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaDomesticStockOrderCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaDomesticStockOrderCancelConfirmA001ApiRequest apiReq)
            throws Exception {
        
        IfaDomesticStockOrderCancelConfirmA001DtoRequest appReq = new IfaDomesticStockOrderCancelConfirmA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderCancelConfirmService", "initializeA001",
                new TypeReference<DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaDomesticStockOrderCancelConfirmA001ApiResponse> apiRes = new DataList<IfaDomesticStockOrderCancelConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderCancelConfirmOrderPlacementA002
     * アクションID：A002
     * アクション名：注文発注
     * APIリクエスト：IfaDomesticStockOrderCancelConfirmA002ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderCancelConfirmA002ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderCancelConfirmA002DtoRequest
     * Dtoレスポンス：IfaDomesticStockOrderCancelConfirmA002DtoResponse
     *
     * @param apiReq リクエストボディ
     * @return 更新結果
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaDomesticStockOrderCancelConfirmOrderPlacementA002")
    public String orderPlacementA002(@RequestBody IfaDomesticStockOrderCancelConfirmA002ApiRequest apiReq)throws Exception {

        IfaDomesticStockOrderCancelConfirmA002aDtoRequest appReqa = new IfaDomesticStockOrderCancelConfirmA002aDtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqa, apiReq);

        DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderCancelConfirmService", "orderPlacementA002a",
                new TypeReference<DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse>>() {
                }, appReqa);
        // エラー
        if (appRes.getErrorLevel() == ErrorLevel.FATAL.getId()) {
            DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse> apiRes = new DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse>();
            
            BeanUtils.copyProperties(apiRes, appRes);
            
            return jc.toString(apiRes);
        }
        
        IfaDomesticStockOrderCancelConfirmA002bDtoRequest appReqb = new IfaDomesticStockOrderCancelConfirmA002bDtoRequest();
        appReqb.setIfaOrderNo(appRes.get(0).getIfaOrderNo());
        appReqb.setIfaOrderSubNo(appRes.get(0).getIfaOrderSubNo());
        BeanUtils.copyProperties(appReqb, apiReq);
        DataList<IfaDomesticStockOrderCancelConfirmA002bDtoResponse> appResb = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderCancelConfirmService", "orderPlacementA002b",
                new TypeReference<DataList<IfaDomesticStockOrderCancelConfirmA002bDtoResponse>>() {
                }, appReqb);
        
        DataList<IfaDomesticStockOrderCancelConfirmA002ApiResponse> apiRes = new DataList<IfaDomesticStockOrderCancelConfirmA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

