package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundBuyAbleListA001ApiRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA007ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundBuyAbleListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundBuyAbleListA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundBuyAbleListA007ApiRequest;

/**
 * 画面ID：SUB0202_0401-01
 * 画面名：国内投信購入可能一覧
 * @author SCSK浦田
 *
 * 2023/10/27 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0401-01", screenNumber = "")
public class IfaDomesticMutualFundBuyAbleListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundBuyAbleListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticMutualFundBuyAbleListA001ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundBuyAbleListA001ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundBuyAbleListA001RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundBuyAbleListA001ResponseDto
     *
     * @param apiReq リクエストデータ
     * @return 実行結果
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundBuyAbleListInitializeA001")
    public String initializeA001(@RequestBody IfaDomesticMutualFundBuyAbleListA001ApiRequest apiReq) throws Exception {
        
        IfaDomesticMutualFundBuyAbleListA001RequestDto appReq = new IfaDomesticMutualFundBuyAbleListA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticMutualFundBuyAbleListA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundBuyAbleListService", "initializeA001",
                new TypeReference<DataList<IfaDomesticMutualFundBuyAbleListA001ResponseDto>>() {
                }, appReq);

        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundBuyAbleListPurchaseDirectInputA002
     * アクションID：A002
     * アクション名：購入（直接入力）
     * APIリクエスト：IfaDomesticMutualFundBuyAbleListA002ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundBuyAbleListA002ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundBuyAbleListA002RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundBuyAbleListA002ResponseDto
     *
     * @param apiReq 直接購入する銘柄
     * @return 直接購入の可否
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundBuyAbleListPurchaseDirectInputA002")
    public String purchaseDirectInputA002(@RequestBody IfaDomesticMutualFundBuyAbleListA002ApiRequest apiReq)
            throws Exception {
        
        IfaDomesticMutualFundBuyAbleListA002RequestDto appReq = new IfaDomesticMutualFundBuyAbleListA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticMutualFundBuyAbleListA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundBuyAbleListService", "purchaseDirectInputA002",
                new TypeReference<DataList<IfaDomesticMutualFundBuyAbleListA002ResponseDto>>() {
                }, appReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundBuyAbleListAccumulateDirectInputA004
     * アクションID：A004
     * アクション名：積立（直接入力）
     * APIリクエスト：IfaDomesticMutualFundBuyAbleListA004ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundBuyAbleListA004ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundBuyAbleListA004RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundBuyAbleListA004ResponseDto
     *
     * @param apiReq 直接積立する銘柄
     * @return 直接積立の可否
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundBuyAbleListAccumulateDirectInputA004")
    public String accumulateDirectInputA004(@RequestBody IfaDomesticMutualFundBuyAbleListA004ApiRequest apiReq)
            throws Exception {
        
        IfaDomesticMutualFundBuyAbleListA004RequestDto appReq = new IfaDomesticMutualFundBuyAbleListA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticMutualFundBuyAbleListA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundBuyAbleListService", "accumulateDirectInputA004",
                new TypeReference<DataList<IfaDomesticMutualFundBuyAbleListA004ResponseDto>>() {
                }, appReq);
        
        return jc.toString(appRes);
    }
    
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundBuyAbleListDirectInputSelectMFNameA007
     * アクションID：A007
     * アクション名：投信銘柄検索取得
     * APIリクエスト：IfaDomesticMutualFundBuyAbleListA007ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundBuyAbleListA007ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundBuyAbleListA007RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundBuyAbleListA007ResponseDto
     *
     * @param apiReq 直接入力銘柄
     * @return 銘柄名の取得
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundBuyAbleListDirectInputSelectMFNameA007")
    public String directInputSelectMFNameA007(@RequestBody IfaDomesticMutualFundBuyAbleListA007ApiRequest apiReq)
            throws Exception {
        
        IfaDomesticMutualFundBuyAbleListA007RequestDto appReq = new IfaDomesticMutualFundBuyAbleListA007RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaDomesticMutualFundBuyAbleListA007ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticMutualFundBuyAbleListService", "directInputSelectMFNameA007",
                new TypeReference<DataList<IfaDomesticMutualFundBuyAbleListA007ResponseDto>>() {
                }, appReq);
        
        return jc.toString(appRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
