package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDepositBalanceDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDepositBalanceDetailA001ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA004ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDepositBalanceDetailA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDepositBalanceDetailA004ApiResponse;

/**
 * 画面ID：SUB0202_010201-04
 * 画面名：預り残高詳細
 * @author <author-name>
 *
 * 2023/11/29 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010201-04", screenNumber = "")
public class IfaDepositBalanceDetailController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaDepositBalanceDetailUpdateA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDepositBalanceDetailA001ApiRequest
     * Apiレスポンス：IfaDepositBalanceDetailA001ApiResponse
     * Dtoリクエスト：IfaDepositBalanceDetailA001DtoRequest
     * Dtoレスポンス：IfaDepositBalanceDetailA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/brokerageMenu/customerMenu/ifaDepositBalanceDetailInitializeA001",method={RequestMethod.POST})
    public String initializeA001(@RequestBody IfaDepositBalanceDetailA001ApiRequest apiReq)throws Exception
    {

        IfaDepositBalanceDetailA001RequestDto appReq = new IfaDepositBalanceDetailA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaDepositBalanceDetailA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDepositBalanceDetailService",
                "initializeA001", new TypeReference<DataList<IfaDepositBalanceDetailA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDepositBalanceDetailA001ApiResponse> apiRes = new DataList<IfaDepositBalanceDetailA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaDepositBalanceDetailUpdateA004
     * アクションID：A004
     * アクション名：更新
     * APIリクエスト：IfaDepositBalanceDetailA004ApiRequest
     * Apiレスポンス：IfaDepositBalanceDetailA004ApiResponse
     * Dtoリクエスト：IfaDepositBalanceDetailA004DtoRequest
     * Dtoレスポンス：IfaDepositBalanceDetailA004DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @RequestMapping(value="/brokerageMenu/customerMenu/ifaDepositBalanceDetailUpdateA004",method={RequestMethod.POST})
    public String updateA004(@RequestBody IfaDepositBalanceDetailA004ApiRequest apiReq)throws Exception
    {

        IfaDepositBalanceDetailA004RequestDto appReq = new IfaDepositBalanceDetailA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaDepositBalanceDetailA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDepositBalanceDetailService",
                "updateA004", new TypeReference<DataList<IfaDepositBalanceDetailA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDepositBalanceDetailA004ApiResponse> apiRes = new DataList<IfaDepositBalanceDetailA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

