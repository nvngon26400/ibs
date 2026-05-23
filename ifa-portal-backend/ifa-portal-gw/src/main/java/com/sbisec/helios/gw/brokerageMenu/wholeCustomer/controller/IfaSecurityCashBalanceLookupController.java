package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

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
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA006DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaSecurityCashBalanceLookupA006DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaSecurityCashBalanceLookupCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaSecurityCashBalanceLookupA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaSecurityCashBalanceLookupA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaSecurityCashBalanceLookupA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaSecurityCashBalanceLookupA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaSecurityCashBalanceLookupA006ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaSecurityCashBalanceLookupA006ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaSecurityCashBalanceLookupA006bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020302_0301-01
 * 画面名：証券・金銭・残高照会
 *
 * @author SCSK濱田
 2024/05/07 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0301-01", screenNumber = "18")
public class IfaSecurityCashBalanceLookupController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /** エラーメッセージID：文字数チェックエラー */
    private static final String ERRORS_NOT_LENGTH = "errors.notLength";
	
    /** エラーメッセージID：重複チェックエラー */
    // private static final String ERRORS_CODE_DUPLICATE = "errors.codeDuplicate";

    /** エラーメッセージID：仲介業者コード文字数チェックエラー */
    private static final String ERRORS_BROKER_CODE_NOT_LENGTH = "errors.brokerCodeNotLength";

    /** エラーメッセージID：仲介業者コード重複チェックエラー */
    // private static final String ERRORS_BROKER_CODE_DUPLICATE = "errors.brokerCodeDuplicate";

    /** エラーメッセージ文言：閲覧可能部店 */
    private static final String MSG_BUTEN_CODE_ARRAY = "閲覧可能部店";


    /** ダウンロードファイルの接頭語 */
    private static final String PREFIX_CSV_FILE_NAME = "証券・金銭・残高照会";


    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaSecurityCashBalanceLookupInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSecurityCashBalanceLookupA001ApiRequest
     * APIレスポンス：IfaSecurityCashBalanceLookupA001ApiResponse
     * Dtoリクエスト：IfaSecurityCashBalanceLookupA001DtoRequest
     * Dtoレスポンス：IfaSecurityCashBalanceLookupA001DtoResponse
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaSecurityCashBalanceLookupInitializeA001")
    public String initializeA001(@RequestBody IfaSecurityCashBalanceLookupA001ApiRequest apiReq) throws Exception {

        IfaSecurityCashBalanceLookupA001DtoRequest appReq = new IfaSecurityCashBalanceLookupA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSecurityCashBalanceLookupA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaSecurityCashBalanceLookupService",
                "initializeA001", new TypeReference<DataList<IfaSecurityCashBalanceLookupA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaSecurityCashBalanceLookupA001ApiResponse> apiRes = new DataList<IfaSecurityCashBalanceLookupA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaSecurityCashBalanceLookupDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaSecurityCashBalanceLookupA002ApiRequest
     * APIレスポンス：IfaSecurityCashBalanceLookupA002ApiResponse
     * Dtoリクエスト：IfaSecurityCashBalanceLookupA002DtoRequest
     * Dtoレスポンス：IfaSecurityCashBalanceLookupA002DtoResponse
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaSecurityCashBalanceLookupDisplayA002")
    public String displayA002(@RequestBody IfaSecurityCashBalanceLookupA002ApiRequest apiReq) throws Exception {

        DataList<IfaSecurityCashBalanceLookupA002ApiResponse> apiRes 
             = new DataList<IfaSecurityCashBalanceLookupA002ApiResponse>();

        // 入力チェック
        
        // 閲覧可能部店(複数指定)
        List<String> butenCodeList = new ArrayList<String>();
        if (apiReq.getButenCodeArray() != null && apiReq.getButenCodeArray() != "") {
            butenCodeList.addAll(Arrays.asList(apiReq.getButenCodeArray().split(",")));
            
            
            // ・3桁チェック
            int butenCodeLength = 3;
            String notLengthButenCodeArray = checkLengthListVal(butenCodeList,butenCodeLength);
    
            // 3桁でない場合はエラー
            if (notLengthButenCodeArray != null) {
                apiRes = IfaCommonUtil.createDataList(
                        new ArrayList<IfaSecurityCashBalanceLookupA002ApiResponse>(),
                        ErrorLevel.FATAL,
                        ERRORS_NOT_LENGTH,
                        IfaCommonUtil.getMessage(ERRORS_NOT_LENGTH, new String[] 
                            { 
                                MSG_BUTEN_CODE_ARRAY,
                                String.valueOf(butenCodeLength),
                                MSG_BUTEN_CODE_ARRAY,
                                notLengthButenCodeArray
                            }
                        )
                );                 
                return jc.toString(apiRes);
            }
        }

        // 仲介業者コード（複数指定）
        List<String> brokerCodeList = new ArrayList<String>();
        if (apiReq.getBrokerCode() != null && apiReq.getBrokerCode() != "") {
            
            // ※カンマ区切りで分解し各項目をチェック
            // ・4桁
            // ・複数指定の仲介業者が重複していない
            brokerCodeList.addAll(Arrays.asList(apiReq.getBrokerCode().split(",")));
            
            // ・4桁チェック
            int brokerCodeLength = 4;
            String notLengthBrokerCode = checkLengthListVal(brokerCodeList,brokerCodeLength);
    
            // 4桁でない場合はエラー
            if (notLengthBrokerCode != null) {
                apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSecurityCashBalanceLookupA002ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_BROKER_CODE_NOT_LENGTH,
                    IfaCommonUtil.getMessage(ERRORS_BROKER_CODE_NOT_LENGTH, new String[]{ notLengthBrokerCode })
                );                 
                return jc.toString(apiRes);
            }
        }

        IfaSecurityCashBalanceLookupA002DtoRequest appReq = new IfaSecurityCashBalanceLookupA002DtoRequest();

        // リクエストをコピー
        BeanUtils.copyProperties(appReq, apiReq);
        // 閲覧可能部店、仲介業者コードをリスト型でリクエストに登録
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setVisibleButenCodeList(butenCodeList);

        // サービス呼び出し
        DataList<IfaSecurityCashBalanceLookupA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaSecurityCashBalanceLookupService",
                "displayA002", new TypeReference<DataList<IfaSecurityCashBalanceLookupA002DtoResponse>>() {
                }, appReq);
        apiRes = new DataList<IfaSecurityCashBalanceLookupA002ApiResponse>();
        // レスポンスをコピー
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaSecurityCashBalanceLookupCsvOutputA006a
     * アクションID：A006
     * アクション名：CSV出力
     * APIリクエスト：IfaSecurityCashBalanceLookupA006ApiRequest
     * APIレスポンス：IfaSecurityCashBalanceLookupA006ApiResponse
     * Dtoリクエスト：IfaSecurityCashBalanceLookupA006DtoRequest
     * Dtoレスポンス：IfaSecurityCashBalanceLookupA006DtoResponse
     * 
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaSecurityCashBalanceLookupCsvOutputA006a")
    public String csvOutputA006a(@RequestBody IfaSecurityCashBalanceLookupA006ApiRequest apiReq)throws Exception
    {
        
        DataList<IfaSecurityCashBalanceLookupA006ApiResponse> apiRes = new DataList<IfaSecurityCashBalanceLookupA006ApiResponse>();        
        
       // 入力チェック
        
        // 閲覧可能部店(複数指定)
        List<String> butenCodeList = new ArrayList<String>();
        if (apiReq.getButenCodeArray() != null && apiReq.getButenCodeArray() != "") {
            butenCodeList.addAll(Arrays.asList(apiReq.getButenCodeArray().split(",")));
            
            
            // ・3桁チェック
            int butenCodeLength = 3;
            String notLengthButenCodeArray = checkLengthListVal(butenCodeList,butenCodeLength);
    
            // 3桁でない場合はエラー
            if (notLengthButenCodeArray != null) {
                apiRes = IfaCommonUtil.createDataList(
                        new ArrayList<IfaSecurityCashBalanceLookupA006ApiResponse>(),
                        ErrorLevel.FATAL,
                        ERRORS_NOT_LENGTH,
                        IfaCommonUtil.getMessage(ERRORS_NOT_LENGTH, new String[] 
                            { 
                                MSG_BUTEN_CODE_ARRAY,
                                String.valueOf(butenCodeLength),
                                MSG_BUTEN_CODE_ARRAY,
                                notLengthButenCodeArray
                            }
                        )
                );                 
                return jc.toString(apiRes);
            }
        }

        // 仲介業者コード（複数指定）
        List<String> brokerCodeList = new ArrayList<String>();
        if (apiReq.getBrokerCode() != null && apiReq.getBrokerCode() != "") {
            
            // ※カンマ区切りで分解し各項目をチェック
            // ・4桁
            // ・複数指定の仲介業者が重複していない
            brokerCodeList.addAll(Arrays.asList(apiReq.getBrokerCode().split(",")));
            
            // ・4桁チェック
            int brokerCodeLength = 4;
            String notLengthBrokerCode = checkLengthListVal(brokerCodeList,brokerCodeLength);
    
            // 4桁でない場合はエラー
            if (notLengthBrokerCode != null) {
                apiRes = IfaCommonUtil.createDataList(
                    new ArrayList<IfaSecurityCashBalanceLookupA006ApiResponse>(),
                    ErrorLevel.FATAL,
                    ERRORS_BROKER_CODE_NOT_LENGTH,
                    IfaCommonUtil.getMessage(ERRORS_BROKER_CODE_NOT_LENGTH, new String[]{ notLengthBrokerCode })
                );                 
                return jc.toString(apiRes);
            }
        }

        IfaSecurityCashBalanceLookupA006DtoRequest appReq = new IfaSecurityCashBalanceLookupA006DtoRequest();

        // リクエストをコピー
        BeanUtils.copyProperties(appReq, apiReq);
        // 閲覧可能部店、仲介業者コードをリスト型でリクエストに登録
        appReq.setBrokerCodeList(brokerCodeList);
        appReq.setVisibleButenCodeList(butenCodeList);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);


        // サービス呼び出し
        DataList<IfaSecurityCashBalanceLookupA006DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaSecurityCashBalanceLookupService",
        "csvOutputA006", new TypeReference<DataList<IfaSecurityCashBalanceLookupA006DtoResponse>>() {
        }, appReq, fwSessionId);
        
        // レスポンスをコピー
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaSecurityCashBalanceLookupCsvOutputA006b
     * アクションID：A006
     * アクション名：CSV出力（ダウンロード）
     * APIリクエスト：IfaSecurityCashBalanceLookupA006bApiRequest
     * 
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @ResponseFile
    @PostMapping("/brokerageMenu/wholeCustomer/ifaSecurityCashBalanceLookupCsvOutputA006b")
    public void csvOutputA006b(@RequestBody IfaSecurityCashBalanceLookupA006bApiRequest apiReq, HttpServletResponse response)
    throws Exception {

        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // CSVファイル ダウンロード
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(PREFIX_CSV_FILE_NAME), IfaCommonUtil.getUserAccount(), apiReq.getPattern());
        
    }

    @Override
    protected String getCsvHeader(String pattern) {
        return IfaSecurityCashBalanceLookupCsvOut.getHeaders(pattern);
    }
    

    /**
     * 桁数チェック
     * リストの値を渡された桁数かどうかチェック
     * 桁数と一致しない場合はエラーメッセージ用の文字列に追加
     * 
     * @param list 値
     * @param len 桁数
     * @return エラーとなった値を","区切りで結合した文字列
     */
    private String checkLengthListVal(List<String> list,int len) {

        String notLengthVal = null;
        for (String val : list) {
            
            if (val != null && val.length() != len) {
                    
                // 桁数が一致しない場合はメッセージに追加
                if (notLengthVal == null) {
                    notLengthVal = val;
                } else {
                    notLengthVal = notLengthVal + "," + val;
                }
            }           
        }
        return notLengthVal;
    }


    @Override
    protected String getFirstViewName() {
        return null;
    }
}

