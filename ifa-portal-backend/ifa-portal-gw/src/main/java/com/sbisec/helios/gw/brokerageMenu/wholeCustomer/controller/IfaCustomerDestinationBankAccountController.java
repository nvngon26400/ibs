package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import com.sbibits.earth.servlet.annotation.ResponseFile;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA002DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCustomerDestinationBankAccountA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCustomerDestinationBankAccountA002ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaCustomerDestinationBankAccountCsvOut;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCustomerDestinationBankAccountA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCustomerDestinationBankAccountA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaCustomerDestinationBankAccountA004bApiRequest;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbibits.earth.util.StringUtil;
import org.springframework.validation.annotation.Validated;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 画面ID：SUB020303-01
 * 画面名：顧客振込先金融機関口座

 * @author 大崎 辰弥
    2023/10/27 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020303-01", screenNumber = "51")
public class IfaCustomerDestinationBankAccountController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /** 仲介業者コードを4桁で入力してください。仲介業者コード: [{0}]. */
    private static final String ERRORS_BROKERCODE_NOTLENGTH = "errors.brokerCodeNotLength";

    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaCustomerDestinationBankAccountDisplayA002
     * アクションID：A002 アクション名：表示
     * APIリクエスト：IfaCustomerDestinationBankAccountA002ApiRequest
     * Apiレスポンス：IfaCustomerDestinationBankAccountA002ApiResponse
     * Dtoリクエスト：IfaCustomerDestinationBankAccountA002DtoRequest
     * Dtoレスポンス：IfaCustomerDestinationBankAccountA002DtoResponse

     * @param apiReq リクエスト
     * @return String
     * @exception Exception SQLExceptionなど 
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaCustomerDestinationBankAccountDisplayA002")
    public String displayA002(@RequestBody @Validated IfaCustomerDestinationBankAccountA002ApiRequest apiReq) throws Exception {

        IfaCustomerDestinationBankAccountA002DtoRequest appReq = new IfaCustomerDestinationBankAccountA002DtoRequest();
        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // 仲介業者コードチェック
        if (!StringUtil.isNullOrEmpty(apiReq.getBrokerCode())) {
            String msgBrokerCode = this.validateBrokerCodeExcludeList(apiReq.getBrokerCode());
            if (!"".equals(msgBrokerCode)) {
                return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_BROKERCODE_NOTLENGTH, msgBrokerCode));
            }
            
            List<String> brokerCodeList = new ArrayList<String>();        
            brokerCodeList.addAll(Arrays.asList(apiReq.getBrokerCode().split(",")));
            apiReq.setBrokerCodeList(brokerCodeList);
        }

        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaCustomerDestinationBankAccountA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaCustomerDestinationBankAccountService", "displayA002",
                new TypeReference<DataList<IfaCustomerDestinationBankAccountA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaCustomerDestinationBankAccountA002ApiResponse> apiRes = new DataList<IfaCustomerDestinationBankAccountA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaCustomerDestinationBankAccountCsvOutputA004a
     * アクションID：A004 アクション名：CSV出力
     * APIリクエスト：IfaCustomerDestinationBankAccountA004ApiRequest
     * Apiレスポンス：IfaCustomerDestinationBankAccountA004ApiResponse
     * Dtoリクエスト：IfaCustomerDestinationBankAccountA004DtoRequest
     * Dtoレスポンス：IfaCustomerDestinationBankAccountA004DtoResponse

     * @param apiReq リクエスト
     * @return String
     * @exception Exception SQLExceptionなど 
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaCustomerDestinationBankAccountCsvOutputA004a")
    public String csvOutputA004a(@RequestBody @Validated IfaCustomerDestinationBankAccountA004aApiRequest apiReq)
            throws Exception {

        IfaCustomerDestinationBankAccountA004aDtoRequest appReq = new IfaCustomerDestinationBankAccountA004aDtoRequest();
        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // 仲介業者コードチェック
        if (!StringUtil.isNullOrEmpty(apiReq.getBrokerCode())) {
            String msgBrokerCode = this.validateBrokerCodeExcludeList(apiReq.getBrokerCode());
            if (!"".equals(msgBrokerCode)) {
                return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_BROKERCODE_NOTLENGTH, msgBrokerCode));
            }
            
            List<String> brokerCodeList = new ArrayList<String>();        
            brokerCodeList.addAll(Arrays.asList(apiReq.getBrokerCode().split(",")));
            apiReq.setBrokerCodeList(brokerCodeList);
        }
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        
        DataList<IfaCustomerDestinationBankAccountA004aDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaCustomerDestinationBankAccountService", "csvOutputA004a",
                new TypeReference<DataList<IfaCustomerDestinationBankAccountA004aDtoResponse>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaCustomerDestinationBankAccountA004aApiResponse> apiRes = new DataList<IfaCustomerDestinationBankAccountA004aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaCustomerDestinationBankAccountCsvOutputA004b
     * アクションID：A004 アクション名：CSV出力
     * APIリクエスト：IfaCustomerDestinationBankAccountA004ApiRequest
     * Apiレスポンス：IfaCustomerDestinationBankAccountA004ApiResponse
     * Dtoリクエスト：IfaCustomerDestinationBankAccountA004DtoRequest
     * Dtoレスポンス：IfaCustomerDestinationBankAccountA004DtoResponse

     * @param apiReq リクエスト
     * @exception Exception SQLExceptionなど 
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaCustomerDestinationBankAccountCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaCustomerDestinationBankAccountA004bApiRequest apiReq,
            HttpServletResponse response) throws Exception {

        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // CSVダウンロード
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("顧客振込先金融機関口座"),
                IfaCommonUtil.getUserAccount());
    }

    /**
    * 仲介業者コードが固定長4桁区切りになっているかのチェック

    * @param brokerCode 仲介業者コード
    * @return String
    */
    private String validateBrokerCodeExcludeList(String brokerCodeExcludeList) {
        if (!StringUtil.isNullOrEmpty(brokerCodeExcludeList)) {              
            int strLength = 0; // 入力した仲介業者コードの長さ        
            List<String> brokerCodeList = null;      
            String msgBrokerCodeNotLength = ""; // 仲介業者の長さが4桁ではないmessage   
                    
            // 入力した仲介業者除外コードをリストに分ける        
            brokerCodeList = new ArrayList<String>();        
            brokerCodeList.addAll(Arrays.asList(brokerCodeExcludeList.split(",")));       
                    
            // 仲介業者を4桁で入力チェック       
            for (String str : brokerCodeList) {      
                strLength = str.length();   
                if (strLength != 4) {   
                    msgBrokerCodeNotLength += str + ", ";
                }   
            }       
            if (!StringUtil.isNullOrEmpty(msgBrokerCodeNotLength)) {        
                return getMessage(ERRORS_BROKERCODE_NOTLENGTH,  
                    new String[] { msgBrokerCodeNotLength.substring(0, msgBrokerCodeNotLength.length() - 2) });
            }       
        }
        return "";
    }
    
    @Override
    protected String getCsvHeader() {

        return IfaCustomerDestinationBankAccountCsvOut.getHeaders();

    }

    @Override
    protected String getFirstViewName() {

        return null;
    }
}
