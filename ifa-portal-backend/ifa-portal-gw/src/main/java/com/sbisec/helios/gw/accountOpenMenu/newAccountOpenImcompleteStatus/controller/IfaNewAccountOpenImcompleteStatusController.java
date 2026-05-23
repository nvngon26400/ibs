package com.sbisec.helios.gw.accountOpenMenu.newAccountOpenImcompleteStatus.controller;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dto.IfaNewAccountOpenImcompleteStatusA002DtoRequest;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dto.IfaNewAccountOpenImcompleteStatusA002DtoResponse;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.accountOpenMenu.newAccountOpenImcompleteStatus.form.IfaNewAccountOpenImcompleteStatusA002ApiRequest;
import com.sbisec.helios.gw.accountOpenMenu.newAccountOpenImcompleteStatus.form.IfaNewAccountOpenImcompleteStatusA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbibits.earth.util.StringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 画面ID：SUB020305-01.
 * 画面名：新規口座開設不備状況

 * @author 富永侑希子、大崎辰弥
　　　　2023/10/27 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN07", id = "SUB020305-01", screenNumber = "")
public class IfaNewAccountOpenImcompleteStatusController extends BaseController {

    /** 発送予定日ToにはFromと同日以降の日付を指定して下さい。. */
    private static final String ERRORS_DATE_SPECIFY_FROM_TO = "errors.date.specifyFromTo";
    
    /** 仲介業者コードを4桁で入力してください。仲介業者コード: [{0}]. */
    private static final String ERRORS_BROKERCODE_NOTLENGTH = "errors.brokerCodeNotLength";
    
    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/newAccountOpenImcompleteStatus/ifaNewAccountOpenImcompleteStatusDisplayA002.
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaNewAccountOpenImcompleteStatusA002ApiRequest
     * Apiレスポンス：IfaNewAccountOpenImcompleteStatusA002ApiResponse
     * Dtoリクエスト：IfaNewAccountOpenImcompleteStatusA002DtoRequest
     * Dtoレスポンス：IfaNewAccountOpenImcompleteStatusA002DtoResponse

     * @param apiReq リクエスト
     * @return appRes
     * @exception Exception SQLExceptionなど 
     */
    @PostMapping("/newAccountOpenImcompleteStatus/ifaNewAccountOpenImcompleteStatusDisplayA002")
    public String displayA002(@RequestBody @Validated IfaNewAccountOpenImcompleteStatusA002ApiRequest apiReq, HttpServletRequest request)
            throws Exception {     

        // 募集期間(TO) < 募集期間(FROM)のチェック
        if (!DateUtil.isValidFromTo(apiReq.getDispatchScheduleDateFrom(), apiReq.getDispatchScheduleDateTo(), DateUtil.YYYYMMDD, DateUtil.SEPARATED_YYYYMMDD)) {
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, 
                    ERRORS_DATE_SPECIFY_FROM_TO, IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFY_FROM_TO, new String[] {"発送予定日"})));
        }
        
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

        IfaNewAccountOpenImcompleteStatusA002DtoRequest appReq 
                = new IfaNewAccountOpenImcompleteStatusA002DtoRequest();
         
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaNewAccountOpenImcompleteStatusA002ApiResponse> apiRes 
                = new DataList<IfaNewAccountOpenImcompleteStatusA002ApiResponse>();
        
        DataList<IfaNewAccountOpenImcompleteStatusA002DtoResponse> appRes 
                = ApiRequestUtil.invoke("cmpIfaNewAccountOpenImcompleteStatusService",
                    "displayA002", new TypeReference<DataList
                        <IfaNewAccountOpenImcompleteStatusA002DtoResponse>>() {
                    }, appReq);
            
        BeanUtils.copyProperties(apiRes, appRes);
            
        return jc.toString(apiRes);
    }
    
    /**
    * 仲介業者コードが固定長4桁区切りになっているかのチェック

    * @param brokerCode 仲介業者コード
    * @return String　エラーとなっている入力した仲介業者コード
    */
    private String validateBrokerCodeExcludeList(String brokerCodeExcludeList) {          
        int strLength = 0; // 入力した仲介業者コードの長さ        
        String msgBrokerCodeNotLength = ""; // 仲介業者の長さが4桁ではないmessage   
                
        // 入力した仲介業者除外コードをリストに分ける        
        List<String> brokerCodeList = new ArrayList<String>();        
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
        
        return "";
    }
    
    @Override
        protected String getFirstViewName() {
        return null;
    }
}

