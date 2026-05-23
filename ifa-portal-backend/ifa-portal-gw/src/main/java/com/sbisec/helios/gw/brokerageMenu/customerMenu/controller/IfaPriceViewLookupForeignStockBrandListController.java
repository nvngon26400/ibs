package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA008ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaPriceViewLookupForeignStockBrandListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaPriceViewLookupForeignStockBrandListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaPriceViewLookupForeignStockBrandListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaPriceViewLookupForeignStockBrandListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaPriceViewLookupForeignStockBrandListA008ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaPriceViewLookupForeignStockBrandListA008ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0302-01", screenNumber = "")
public class IfaPriceViewLookupForeignStockBrandListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaPriceViewLookupForeignStockBrandListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaPriceViewLookupForeignStockBrandListA001ApiRequest
     * Apiレスポンス：IfaPriceViewLookupForeignStockBrandListA001ApiResponse
     * Dtoリクエスト：IfaPriceViewLookupForeignStockBrandListA001RequestDto
     * Dtoレスポンス：IfaPriceViewLookupForeignStockBrandListA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaPriceViewLookupForeignStockBrandListInitializeA001")
    public String initializeA001(@RequestBody IfaPriceViewLookupForeignStockBrandListA001ApiRequest apiReq)
            throws Exception {
        
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaPriceViewLookupForeignStockBrandListA001RequestDto appReq = new IfaPriceViewLookupForeignStockBrandListA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaPriceViewLookupForeignStockBrandListA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaPriceViewLookupForeignStockBrandListService", "initializeA001",
                new TypeReference<DataList<IfaPriceViewLookupForeignStockBrandListA001ResponseDto>>() {
                }, appReq);
        
        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaPriceViewLookupForeignStockBrandListA001ApiResponse> apiRes = new DataList<IfaPriceViewLookupForeignStockBrandListA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaPriceViewLookupForeignStockBrandListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaPriceViewLookupForeignStockBrandListA002ApiRequest
     * Apiレスポンス：IfaPriceViewLookupForeignStockBrandListA002ApiResponse
     * Dtoリクエスト：IfaPriceViewLookupForeignStockBrandListA002RequestDto
     * Dtoレスポンス：IfaPriceViewLookupForeignStockBrandListA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaPriceViewLookupForeignStockBrandListDisplayA002")
    public String displayA002(@RequestBody IfaPriceViewLookupForeignStockBrandListA002ApiRequest apiReq)
            throws Exception {
        
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaPriceViewLookupForeignStockBrandListA002RequestDto appReq = new IfaPriceViewLookupForeignStockBrandListA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaPriceViewLookupForeignStockBrandListA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaPriceViewLookupForeignStockBrandListService", "displayA002",
                new TypeReference<DataList<IfaPriceViewLookupForeignStockBrandListA002ResponseDto>>() {
                }, appReq);
        
        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaPriceViewLookupForeignStockBrandListA002ApiResponse> apiRes = new DataList<IfaPriceViewLookupForeignStockBrandListA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaPriceViewLookupForeignStockBrandListBackButtonRedisplayA008
     * アクションID：A008
     * アクション名：戻るボタン再表示
     * APIリクエスト：IfaPriceViewLookupForeignStockBrandListA008ApiRequest
     * Apiレスポンス：IfaPriceViewLookupForeignStockBrandListA008ApiResponse
     * Dtoリクエスト：IfaPriceViewLookupForeignStockBrandListA008RequestDto
     * Dtoレスポンス：IfaPriceViewLookupForeignStockBrandListA008ResponseDto
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaPriceViewLookupForeignStockBrandListBackButtonRedisplayA008")
    public String backButtonRedisplayA008(@RequestBody IfaPriceViewLookupForeignStockBrandListA008ApiRequest apiReq)
            throws Exception {
        
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaPriceViewLookupForeignStockBrandListA008RequestDto appReq = new IfaPriceViewLookupForeignStockBrandListA008RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaPriceViewLookupForeignStockBrandListA008ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaPriceViewLookupForeignStockBrandListService", "backButtonRedisplayA008",
                new TypeReference<DataList<IfaPriceViewLookupForeignStockBrandListA008ResponseDto>>() {
                }, appReq);
        
        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaPriceViewLookupForeignStockBrandListA008ApiResponse> apiRes = new DataList<IfaPriceViewLookupForeignStockBrandListA008ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
