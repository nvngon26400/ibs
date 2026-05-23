package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeCompleteA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeCompleteA001DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDistributionReceiveMethodChangeCompleteA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDistributionReceiveMethodChangeCompleteA001ApiResponse;

/**
 * 画面ID：SUB0202_010201-02_2
 * 画面名：分配金受取方法変更完了
 * @author <author-name>
 *
 * 2023/12/04 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010201-02_2", screenNumber = "")
public class IfaDistributionReceiveMethodChangeCompleteController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaDistributionReceiveMethodChangeCompleteInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDistributionReceiveMethodChangeCompleteA001ApiRequest
     * Apiレスポンス：IfaDistributionReceiveMethodChangeCompleteA001ApiResponse
     * Dtoリクエスト：IfaDistributionReceiveMethodChangeCompleteA001DtoRequest
     * Dtoレスポンス：IfaDistributionReceiveMethodChangeCompleteA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/brokerageMenu/customerMenu/ifaDistributionReceiveMethodChangeCompleteInitializeA001",method={RequestMethod.POST})
    public String initializeA001(@RequestBody IfaDistributionReceiveMethodChangeCompleteA001ApiRequest apiReq)throws Exception
    {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaDistributionReceiveMethodChangeCompleteA001DtoRequest appReq = new IfaDistributionReceiveMethodChangeCompleteA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaDistributionReceiveMethodChangeCompleteA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaDistributionReceiveMethodChangeCompleteService",
                "initializeA001", new TypeReference<DataList<IfaDistributionReceiveMethodChangeCompleteA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaDistributionReceiveMethodChangeCompleteA001ApiResponse> apiRes = new DataList<IfaDistributionReceiveMethodChangeCompleteA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

