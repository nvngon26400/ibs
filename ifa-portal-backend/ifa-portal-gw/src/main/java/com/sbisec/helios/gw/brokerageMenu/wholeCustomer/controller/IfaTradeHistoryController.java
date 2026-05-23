package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaTradeHistoryCsvOut;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeHistoryA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeHistoryA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeHistoryA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeHistoryA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeHistoryA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeHistoryA005ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTradeHistoryA005bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020302_0201-01
 * 画面名：取引履歴
 *
 * @author SCSK
 2024/06/13 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0201-01", screenNumber = "13")
public class IfaTradeHistoryController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOrderListController.class);
    
    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME = "取引履歴";
    
    @Override
    protected String getCsvHeader(String pattern) {
        
        return IfaTradeHistoryCsvOut.getHeaders(pattern);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaTradeHistoryInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaTradeHistoryA001ApiRequest
     * Apiレスポンス：IfaTradeHistoryA001ApiResponse
     * Dtoリクエスト：IfaTradeHistoryA001RequestDto
     * Dtoレスポンス：IfaTradeHistoryA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaTradeHistoryInitializeA001")
    public String initializeA001(@RequestBody IfaTradeHistoryA001ApiRequest apiReq) throws Exception {
        
        IfaTradeHistoryA001RequestDto appReq = new IfaTradeHistoryA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaTradeHistoryA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaTradeHistoryService",
                "initializeA001", new TypeReference<DataList<IfaTradeHistoryA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaTradeHistoryA001ApiResponse> apiRes = new DataList<IfaTradeHistoryA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaTradeHistoryDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaTradeHistoryA002ApiRequest
     * Apiレスポンス：IfaTradeHistoryA002ApiResponse
     * Dtoリクエスト：IfaTradeHistoryA002RequestDto
     * Dtoレスポンス：IfaTradeHistoryA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaTradeHistoryDisplayA002")
    public String displayA002(@RequestBody IfaTradeHistoryA002ApiRequest apiReq) throws Exception {
        
        IfaTradeHistoryA002RequestDto appReq = new IfaTradeHistoryA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaTradeHistoryA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaTradeHistoryService",
                "displayA002", new TypeReference<DataList<IfaTradeHistoryA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaTradeHistoryA002ApiResponse> apiRes = new DataList<IfaTradeHistoryA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaTradeHistoryCsvOutputA005
     * アクションID：A005
     * アクション名：CSV出力
     * APIリクエスト：IfaTradeHistoryA005ApiRequest
     * Apiレスポンス：IfaTradeHistoryA005ApiResponse
     * Dtoリクエスト：IfaTradeHistoryA005RequestDto
     * Dtoレスポンス：IfaTradeHistoryA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaTradeHistoryCsvOutputA005")
    public String csvOutputA005(@RequestBody IfaTradeHistoryA005ApiRequest apiReq) throws Exception {

        IfaTradeHistoryA005RequestDto appReq = new IfaTradeHistoryA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaTradeHistoryA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaTradeHistoryService",
                "csvOutputA005", new TypeReference<DataList<IfaTradeHistoryA005ResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaTradeHistoryA005ApiResponse> apiRes = new DataList<IfaTradeHistoryA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaTradeHistoryCsvOutputA005
     * アクションID：A005
     * アクション名：CSV出力
     * APIリクエスト：IfaTradeHistoryA005ApiRequest
     * Apiレスポンス：IfaTradeHistoryA005ApiResponse
     * Dtoリクエスト：IfaTradeHistoryA005RequestDto
     * Dtoレスポンス：IfaTradeHistoryA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaTradeHistoryCsvOutputA005b")
    public void csvOutputA005b(@RequestBody IfaTradeHistoryA005bApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        long start = System.currentTimeMillis();
        LOGGER.debug("IfaTradeHistoryController.csvOutputA005b >> {}", hashCode());
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // Star ファイル ダウンロード CRLF
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(STAR_UPLOAD_CSV_FILE_NAME),
                userAccount, apiReq.getPattern());
        
        LOGGER.debug("cost -> {}", (System.currentTimeMillis() - start));
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

