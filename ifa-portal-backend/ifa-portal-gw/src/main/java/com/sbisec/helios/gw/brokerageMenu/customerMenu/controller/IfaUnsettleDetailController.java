package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaUnsettleDetailA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaUnsettleDetailA001DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaUnsettleDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaUnsettleDetailA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_010301-02
 * 画面名：未精算明細
 * @author <author-name>
 *
 * 2023/09/15 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010301-02", screenNumber = "")
public class IfaUnsettleDetailController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/IfaUnsettleDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaUnsettleDetailA001ApiRequest
     * Apiレスポンス：IfaUnsettleDetailA001ApiResponse
     * Dtoリクエスト：IfaUnsettleDetailA001DtoRequest
     * Dtoレスポンス：IfaUnsettleDetailA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaUnsettleDetailInitializeA001")
    public String initializeA001(@RequestBody IfaUnsettleDetailA001ApiRequest apiReq)throws Exception
    {

        IfaUnsettleDetailA001DtoRequest appReq = new IfaUnsettleDetailA001DtoRequest();

        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaUnsettleDetailA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaUnsettleDetailService",
                "initializeA001", new TypeReference<DataList<IfaUnsettleDetailA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaUnsettleDetailA001ApiResponse> apiRes = new DataList<IfaUnsettleDetailA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
    
    
}

