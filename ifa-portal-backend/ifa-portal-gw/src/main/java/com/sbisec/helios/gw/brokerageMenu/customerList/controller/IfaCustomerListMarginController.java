package com.sbisec.helios.gw.brokerageMenu.customerList.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.util.IfaCustomerListMarginCsvUtil;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListMarginA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListMarginA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListMarginA005aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListMarginA005aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaCustomerListMarginA005bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0201_02-01
 * 画面名：顧客一覧・信用
 * @author 鄒
 *
 * 2024/01/11 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0201_02-01", screenNumber = "32", ignoreCheck = true)
public class IfaCustomerListMarginController extends BaseController{
    
    private static final String CSV_FILE_NAME = "信用";

    private JsonConverter jc = JsonConverter.getInstance();
    
    @Override
    protected String getCsvHeader(String pattern) {
        
        return IfaCustomerListMarginCsvUtil.getHeaders(pattern);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerList/ifaCustomerListMarginDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaCustomerListMarginA002ApiRequest
     * Apiレスポンス：IfaCustomerListMarginA002ApiResponse
     * Dtoリクエスト：IfaCustomerListMarginA002DtoRequest
     * Dtoレスポンス：IfaCustomerListMarginA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping(value = "/brokerageMenu/customerList/ifaCustomerListMarginDisplayA002")
    public String displayA002(@RequestBody IfaCustomerListMarginA002ApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaCustomerListMarginA002RequestDto appReq = new IfaCustomerListMarginA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaCustomerListMarginA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaCustomerListMarginService",
                "displayA002", new TypeReference<DataList<IfaCustomerListMarginA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaCustomerListMarginA002ApiResponse> apiRes = new DataList<IfaCustomerListMarginA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerList/ifaCustomerListMarginCsvOutputA005a
     * アクションID：A005a
     * アクション名：CSV出力
     * APIリクエスト：IfaCustomerListMarginA005aApiRequest
     * Apiレスポンス：IfaCustomerListMarginA005aApiResponse
     * Dtoリクエスト：IfaCustomerListMarginA005DtoRequest
     * Dtoレスポンス：IfaCustomerListMarginA005DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping(value = "/brokerageMenu/customerList/ifaCustomerListMarginCsvOutputA005a")
    public String csvOutputA005a(@RequestBody IfaCustomerListMarginA005aApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaCustomerListMarginA005RequestDto appReq = new IfaCustomerListMarginA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaCustomerListMarginA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaCustomerListMarginService",
                "csvOutputA005", new TypeReference<DataList<IfaCustomerListMarginA005ResponseDto>>() {
                }, appReq, IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
        
        DataList<IfaCustomerListMarginA005aApiResponse> apiRes = new DataList<IfaCustomerListMarginA005aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerList/ifaCustomerListMarginCsvOutputA005b
     * アクションID：A005b
     * アクション名：CSV出力
     * APIリクエスト：IfaCustomerListMarginA005bApiRequest
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping(value = "/brokerageMenu/customerList/ifaCustomerListMarginCsvOutputA005b")
    public void csvOutputA005b(@RequestBody IfaCustomerListMarginA005bApiRequest apiReq, HttpServletResponse response)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(CSV_FILE_NAME),
                IfaCommonUtil.getUserAccount(), apiReq.getPattern());
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

