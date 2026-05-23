package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA005aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA005aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaTodayTradeListCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTodayTradeListA002aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTodayTradeListA002aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTodayTradeListA002bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTodayTradeListA002bApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTodayTradeListA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaTodayTradeListA005ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020302_0102-01
 * 画面名：国内株当日約定一覧
 * @author <author-name>
 *
 * 2023/11/26 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0102-01", screenNumber = "43", ignoreCheck = true)
public class IfaTodayTradeListController extends BaseController{
 
    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaTodayTradeListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaTodayTradeListA002ApiRequest
     * Apiレスポンス：IfaTodayTradeListA002ApiResponse
     * Dtoリクエスト：IfaTodayTradeListA002DtoRequest
     * Dtoレスポンス：IfaTodayTradeListA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaTodayTradeListDisplayA002a")
    public String displayA002a(@RequestBody IfaTodayTradeListA002aApiRequest apiReq)throws Exception
    {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaTodayTradeListA002aDtoRequest appReq = new IfaTodayTradeListA002aDtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaTodayTradeListA002aDtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaTodayTradeListService",
                "displayA002a", new TypeReference<DataList<IfaTodayTradeListA002aDtoResponse>>() {
                }, appReq);
        
        DataList<IfaTodayTradeListA002aApiResponse> apiRes = new DataList<IfaTodayTradeListA002aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaTodayTradeListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaTodayTradeListA002ApiRequest
     * Apiレスポンス：IfaTodayTradeListA002ApiResponse
     * Dtoリクエスト：IfaTodayTradeListA002DtoRequest
     * Dtoレスポンス：IfaTodayTradeListA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaTodayTradeListDisplayA002b")
    public String displayA002b(@RequestBody IfaTodayTradeListA002bApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaTodayTradeListA002bDtoRequest appReq = new IfaTodayTradeListA002bDtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaTodayTradeListA002bDtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaTodayTradeListService",
                "displayA002b", new TypeReference<DataList<IfaTodayTradeListA002bDtoResponse>>() {
                }, appReq);
        
        DataList<IfaTodayTradeListA002bApiResponse> apiRes = new DataList<IfaTodayTradeListA002bApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaTodayTradeListCsvOutputA005
     * アクションID：A005
     * アクション名：CSV出力
     * APIリクエスト：IfaTodayTradeListA005ApiRequest
     * Apiレスポンス：IfaTodayTradeListA005ApiResponse
     * Dtoリクエスト：IfaTodayTradeListA005DtoRequest
     * Dtoレスポンス：IfaTodayTradeListA005DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaTodayTradeListCsvOutputA005a")
    public String csvOutputA005a(@RequestBody IfaTodayTradeListA005ApiRequest apiReq)throws Exception
    {

        IfaTodayTradeListA005aDtoRequest appReq = new IfaTodayTradeListA005aDtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaTodayTradeListA005aDtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaTodayTradeListService",
                "csvOutputA005", new TypeReference<DataList<IfaTodayTradeListA005aDtoResponse>>() {
                }, appReq);
        
        DataList<IfaTodayTradeListA005ApiResponse> apiRes = new DataList<IfaTodayTradeListA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaTodayTradeListCsvOutputA005
     * アクションID：A005
     * アクション名：CSV出力
     * APIリクエスト：IfaTodayTradeListA005ApiRequest
     * Apiレスポンス：IfaTodayTradeListA005ApiResponse
     * Dtoリクエスト：IfaTodayTradeListA005DtoRequest
     * Dtoレスポンス：IfaTodayTradeListA005DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @ResponseFile
    @PostMapping("/brokerageMenu/wholeCustomer/ifaTodayTradeListCsvOutputA005b")
    public void csvOutputA005b(@RequestBody IfaTodayTradeListA005ApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("国内株当日約定一覧"), IfaCommonUtil.getUserAccount());
        
    }
    
    @Override
    protected String getCsvHeader() {
        return IfaTodayTradeListCsvOut.getHeaders();
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

