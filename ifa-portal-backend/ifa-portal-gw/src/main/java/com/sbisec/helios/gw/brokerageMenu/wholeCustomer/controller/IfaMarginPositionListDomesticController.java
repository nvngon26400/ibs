package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaMarginPositionListDomesticCsvOut;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListDomesticA002aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListDomesticA002aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListDomesticA002bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListDomesticA002bApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListDomesticA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListDomesticA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListDomesticA004bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB020302_0302-01
 * 画面名：信用建玉一覧（国内）
 * @author <author-name>
 *
 * 2023/09/07 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0302-01", screenNumber = "44")
public class IfaMarginPositionListDomesticController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustome/ifaMarginPositionListDomesticDisplayA002ManagingContractList
     * アクションID：A002
     * アクション名：表示 顧客口座情報検索
     * APIリクエスト：IfaMarginPositionListDomesticA002ApiRequest
     * Apiレスポンス：IfaMarginPositionListDomesticA002ApiResponse
     * Dtoリクエスト：IfaMarginPositionListDomesticA002DtoRequest
     * Dtoレスポンス：IfaMarginPositionListDomesticA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名不明のため現行から取得
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMarginPositionListDomesticDisplayA002a")
    public String displayA002a(@RequestBody IfaMarginPositionListDomesticA002aApiRequest apiReq) throws Exception {
        
        IfaMarginPositionListDomesticA002aRequestDto appReq = new IfaMarginPositionListDomesticA002aRequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 画面IDの取得
        ScreenId screenId = (ScreenId) this.getClass().getAnnotation(ScreenId.class);
        appReq.setScreenId(screenId.id());
        DataList<IfaMarginPositionListDomesticA002aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginPositionListDomesticService", "displayA002a",
                new TypeReference<DataList<IfaMarginPositionListDomesticA002aResponseDto>>() {
                }, appReq);
        
        DataList<IfaMarginPositionListDomesticA002aApiResponse> apiRes = new DataList<IfaMarginPositionListDomesticA002aApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustome/ifaMarginPositionListDomesticDisplayA002
     * アクションID：A002
     * アクション名：表示 信用建玉情報検索
     * APIリクエスト：IfaMarginPositionListDomesticA002ApiRequest
     * Apiレスポンス：IfaMarginPositionListDomesticA002ApiResponse
     * Dtoリクエスト：IfaMarginPositionListDomesticA002DtoRequest
     * Dtoレスポンス：IfaMarginPositionListDomesticA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名不明のため現行から取得
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMarginPositionListDomesticDisplayA002b")
    public String displayA002b(@RequestBody IfaMarginPositionListDomesticA002bApiRequest apiReq) throws Exception {
        
        IfaMarginPositionListDomesticA002bRequestDto appReq = new IfaMarginPositionListDomesticA002bRequestDto();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaMarginPositionListDomesticA002bResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginPositionListDomesticService", "displayA002b",
                new TypeReference<DataList<IfaMarginPositionListDomesticA002bResponseDto>>() {
                }, appReq);
        
        DataList<IfaMarginPositionListDomesticA002bApiResponse> apiRes = new DataList<IfaMarginPositionListDomesticA002bApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaMarginPositionListDomesticDisplayA004
     * アクションID：A004
     * アクション名：表示 CSV出力（CSV作成）
     * APIリクエスト：IfaMarginPositionListDomesticA002ApiRequest
     * Apiレスポンス：IfaMarginPositionListDomesticA002ApiResponse
     * Dtoリクエスト：IfaMarginPositionListDomesticA002DtoRequest
     * Dtoレスポンス：IfaMarginPositionListDomesticA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名不明
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMarginPositionListDomesticCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaMarginPositionListDomesticA004aApiRequest apiReq) throws Exception {
        
        IfaMarginPositionListDomesticA004aRequestDto appReq = new IfaMarginPositionListDomesticA004aRequestDto();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaMarginPositionListDomesticA004aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginPositionListDomesticService", "csvOutputA004a",
                new TypeReference<DataList<IfaMarginPositionListDomesticA004aResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaMarginPositionListDomesticA004aApiResponse> apiRes = new DataList<IfaMarginPositionListDomesticA004aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaMarginPositionListDomesticDisplayA004
     * アクションID：A004
     * アクション名：表示 CSV出力（ダウンロード）
     * APIリクエスト：IfaMarginPositionListDomesticA002ApiRequest
     * Apiレスポンス：IfaMarginPositionListDomesticA002ApiResponse
     * Dtoリクエスト：IfaMarginPositionListDomesticA002DtoRequest
     * Dtoレスポンス：IfaMarginPositionListDomesticA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名不明
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMarginPositionListDomesticCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaMarginPositionListDomesticA004bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("信用建玉一覧"), IfaCommonUtil.getUserAccount());
        
    }
    
    @Override
    protected String getCsvHeader() {
        
        return IfaMarginPositionListDomesticCsvOut.getHeaders();
        
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
