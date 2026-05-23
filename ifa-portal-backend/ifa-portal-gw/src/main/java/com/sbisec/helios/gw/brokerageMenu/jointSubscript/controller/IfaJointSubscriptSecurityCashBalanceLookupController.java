package com.sbisec.helios.gw.brokerageMenu.jointSubscript.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptSecurityCashBalanceLookupCsvOut;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptSecurityCashBalanceLookupA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptSecurityCashBalanceLookupA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptSecurityCashBalanceLookupA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptSecurityCashBalanceLookupA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptSecurityCashBalanceLookupA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptSecurityCashBalanceLookupA004bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0206_04-01
 * 画面名：共同募集　証券・金銭　残高照会
 *
 * @author SBIえん
 2024/12/10 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0206_04-01", screenNumber = "20")
public class IfaJointSubscriptSecurityCashBalanceLookupController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /** ダウンロードファイルの接頭語 */
    private static final String PREFIX_CSV_FILE_NAME = "共同募集証券金銭残高照会";

    
    /**
     * 
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptSecurityCashBalanceLookupInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaJointSubscriptSecurityCashBalanceLookupA001ApiRequest
     * APIレスポンス：IfaJointSubscriptSecurityCashBalanceLookupA001ApiResponse
     * Dtoリクエスト：IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest
     * Dtoレスポンス：IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptSecurityCashBalanceLookupInitializeA001")
    public String initializeA001(@RequestBody IfaJointSubscriptSecurityCashBalanceLookupA001ApiRequest apiReq) throws Exception {

    	IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest appReq = new IfaJointSubscriptSecurityCashBalanceLookupA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaJointSubscriptSecurityCashBalanceLookupService",
                "initializeA001", new TypeReference<DataList<IfaJointSubscriptSecurityCashBalanceLookupA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaJointSubscriptSecurityCashBalanceLookupA001ApiResponse> apiRes = new DataList<IfaJointSubscriptSecurityCashBalanceLookupA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptSecurityCashBalanceLookupDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaJointSubscriptSecurityCashBalanceLookupA002ApiRequest
     * APIレスポンス：IfaJointSubscriptSecurityCashBalanceLookupA002ApiResponse
     * Dtoリクエスト：IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest
     * Dtoレスポンス：IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptSecurityCashBalanceLookupDisplayA002")
    public String displayA002(@RequestBody IfaJointSubscriptSecurityCashBalanceLookupA002ApiRequest apiReq) throws Exception {

        DataList<IfaJointSubscriptSecurityCashBalanceLookupA002ApiRequest> apiRes 
             = new DataList<IfaJointSubscriptSecurityCashBalanceLookupA002ApiRequest>();

        // 入力チェック
        
        // 閲覧可能部店(複数指定)
        List<String> butenCodeList = new ArrayList<String>();
        if (apiReq.getButenCodeArray() != null && apiReq.getButenCodeArray() != "") {
        	
            butenCodeList.addAll(Arrays.asList(apiReq.getButenCodeArray().split(",")));
        }

        // 仲介業者コード（複数指定）
        List<String> brokerCodeList = new ArrayList<String>();
        if (apiReq.getBrokerCode() != null && apiReq.getBrokerCode() != "") {
            
            brokerCodeList.addAll(Arrays.asList(apiReq.getBrokerCode().split(",")));
        }

        IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest appReq = new IfaJointSubscriptSecurityCashBalanceLookupA002DtoRequest();

        // リクエストをコピー
        BeanUtils.copyProperties(appReq, apiReq);
        // 閲覧可能部店、仲介業者コードをリスト型でリクエストに登録
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setVisibleButenCodeList(butenCodeList);

        // サービス呼び出し
        DataList<IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaJointSubscriptSecurityCashBalanceLookupService",
                "displayA002", new TypeReference<DataList<IfaJointSubscriptSecurityCashBalanceLookupA002DtoResponse>>() {
                }, appReq);
        apiRes = new DataList<IfaJointSubscriptSecurityCashBalanceLookupA002ApiRequest>();
        // レスポンスをコピー
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    /**
     * 
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptSecurityCashBalanceLookupCsvOutputA004a
     * アクションID：A004
     * アクション名：CSV出力
     * APIリクエスト：IfaJointSubscriptSecurityCashBalanceLookupA004ApiRequest
     * APIレスポンス：IfaJointSubscriptSecurityCashBalanceLookupA004ApiResponse
     * Dtoリクエスト：IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest
     * Dtoレスポンス：IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse
     * 
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptSecurityCashBalanceLookupCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaJointSubscriptSecurityCashBalanceLookupA004ApiRequest apiReq)throws Exception
    {
        
        DataList<IfaJointSubscriptSecurityCashBalanceLookupA004ApiResponse> apiRes = new DataList<IfaJointSubscriptSecurityCashBalanceLookupA004ApiResponse>();        
        
       // 入力チェック
        
        // 閲覧可能部店(複数指定)
        List<String> butenCodeList = new ArrayList<String>();
        if (apiReq.getButenCodeArray() != null && apiReq.getButenCodeArray() != "") {
        	
            butenCodeList.addAll(Arrays.asList(apiReq.getButenCodeArray().split(",")));
        }

        // 仲介業者コード（複数指定）
        List<String> brokerCodeList = new ArrayList<String>();
        if (apiReq.getBrokerCode() != null && apiReq.getBrokerCode() != "") {
            
            brokerCodeList.addAll(Arrays.asList(apiReq.getBrokerCode().split(",")));
        }

        IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest appReq = new IfaJointSubscriptSecurityCashBalanceLookupA004DtoRequest();

        // リクエストをコピー
        BeanUtils.copyProperties(appReq, apiReq);
        // 閲覧可能部店、仲介業者コードをリスト型でリクエストに登録
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setVisibleButenCodeList(butenCodeList);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);

        // サービス呼び出し
        DataList<IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaJointSubscriptSecurityCashBalanceLookupService",
        "csvOutputA004", new TypeReference<DataList<IfaJointSubscriptSecurityCashBalanceLookupA004DtoResponse>>() {
        }, appReq, fwSessionId);
        
        // レスポンスをコピー
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/jointSubscript/ifaSecurityCashBalanceLookupCsvOutputA006b
     * アクションID：A004
     * アクション名：CSV出力（ダウンロード）
     * APIリクエスト：IfaJointSubscriptSecurityCashBalanceLookupA004bApiRequest
     * 
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @ResponseFile
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptSecurityCashBalanceLookupCsvOutputA004b")
    public void csvOutputA006b(@RequestBody IfaJointSubscriptSecurityCashBalanceLookupA004bApiRequest apiReq, HttpServletResponse response)
    throws Exception {

        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // CSVファイル ダウンロード
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(PREFIX_CSV_FILE_NAME), IfaCommonUtil.getUserAccount(), apiReq.getPattern());
        
    }

    @Override
    protected String getCsvHeader(String pattern) {
        return IfaJointSubscriptSecurityCashBalanceLookupCsvOut.getHeaders(pattern);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

