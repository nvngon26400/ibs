package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA001DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaFxTradeHistoryA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaFxTradeHistoryA001ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA002DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaFxTradeHistoryA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaFxTradeHistoryA002ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaFxTradeHistoryCsvOut;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaFxTradeHistoryA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaFxTradeHistoryA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaFxTradeHistoryA004bApiRequest;

/**
 * 画面ID：SUB020302_0202-01
 * 画面名：為替取引履歴
 *
 * @author SCSK川崎
 2024/05/08 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0202-01", screenNumber = "14")
public class IfaFxTradeHistoryController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaFxTradeHistoryInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaFxTradeHistoryA001ApiRequest
     * Apiレスポンス：IfaFxTradeHistoryA001ApiResponse
     * Dtoリクエスト：IfaFxTradeHistoryA001DtoRequest
     * Dtoレスポンス：IfaFxTradeHistoryA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaFxTradeHistoryInitializeA001")
    public String initializeA001(@RequestBody IfaFxTradeHistoryA001ApiRequest apiReq) throws Exception {
        
        IfaFxTradeHistoryA001DtoRequest appReq = new IfaFxTradeHistoryA001DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaFxTradeHistoryA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFxTradeHistoryService",
                "initializeA001", new TypeReference<DataList<IfaFxTradeHistoryA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFxTradeHistoryA001ApiResponse> apiRes = new DataList<IfaFxTradeHistoryA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaFxTradeHistoryDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaFxTradeHistoryA002ApiRequest
     * Apiレスポンス：IfaFxTradeHistoryA002ApiResponse
     * Dtoリクエスト：IfaFxTradeHistoryA002DtoRequest
     * Dtoレスポンス：IfaFxTradeHistoryA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaFxTradeHistoryDisplayA002")
    public String displayA002(@RequestBody IfaFxTradeHistoryA002ApiRequest apiReq) throws Exception {
        
        IfaFxTradeHistoryA002DtoRequest appReq = new IfaFxTradeHistoryA002DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaFxTradeHistoryA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFxTradeHistoryService",
                "displayA002", new TypeReference<DataList<IfaFxTradeHistoryA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFxTradeHistoryA002ApiResponse> apiRes = new DataList<IfaFxTradeHistoryA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaFxTradeHistoryCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力（CSV作成）
     * APIリクエスト：IfaFxTradeHistoryA004aApiRequest
     * Apiレスポンス：IfaFxTradeHistoryA004aApiResponse
     * Dtoリクエスト：IfaFxTradeHistoryA004aDtoRequest
     * Dtoレスポンス：IfaFxTradeHistoryA004aDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaFxTradeHistoryCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaFxTradeHistoryA004aApiRequest apiReq) throws Exception {
        
        IfaFxTradeHistoryA004aDtoRequest appReq = new IfaFxTradeHistoryA004aDtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaFxTradeHistoryA004aDtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFxTradeHistoryService",
                "csvOutputA004a", new TypeReference<DataList<IfaFxTradeHistoryA004aDtoResponse>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaFxTradeHistoryA004aApiResponse> apiRes = new DataList<IfaFxTradeHistoryA004aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaFxTradeHistoryCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力（ダウンロード）
     * APIリクエスト：IfaFxTradeHistoryA004bApiRequest
     *
     * @param apiReq リクエスト
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaFxTradeHistoryCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaFxTradeHistoryA004bApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("為替取引履歴"),
                IfaCommonUtil.getUserAccount());
        
    }
    
    @Override
    protected String getCsvHeader() {
        
        return IfaFxTradeHistoryCsvOut.getHeaders();
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
