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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA003ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBuyingPowerDomesticA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBuyingPowerDomesticA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBuyingPowerDomesticA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBuyingPowerDomesticA003ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0202_010301-01
 * 画面名：買付余力（国内）

 * @author 松田
    2024/01/10 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010301-01", screenNumber = "")
public class IfaBuyingPowerDomesticController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaBuyingPowerDomesticInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaBuyingPowerDomesticA001ApiRequest
     * Apiレスポンス：IfaBuyingPowerDomesticA001ApiResponse
     * Dtoリクエスト：IfaBuyingPowerDomesticA001DtoRequest
     * Dtoレスポンス：IfaBuyingPowerDomesticA001DtoResponse

     * @param apiReq {@code IfaBuyingPowerDomesticA001ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @RequestMapping(value = "/brokerageMenu/customerMenu/ifaBuyingPowerDomesticInitializeA001", method = {
            RequestMethod.POST })
    public String initializeA001(@RequestBody IfaBuyingPowerDomesticA001ApiRequest apiReq) throws Exception {
        
        IfaBuyingPowerDomesticA001RequestDto appReq = new IfaBuyingPowerDomesticA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaBuyingPowerDomesticA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaBuyingPowerDomesticService", "initializeA001",
                new TypeReference<DataList<IfaBuyingPowerDomesticA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBuyingPowerDomesticA001ApiResponse> apiRes = new DataList<IfaBuyingPowerDomesticA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaBuyingPowerDomesticReSearchA003
     * アクションID：A003
     * アクション名：再検索
     * APIリクエスト：IfaBuyingPowerDomesticA003ApiRequest
     * Apiレスポンス：IfaBuyingPowerDomesticA003ApiResponse
     * Dtoリクエスト：IfaBuyingPowerDomesticA003DtoRequest
     * Dtoレスポンス：IfaBuyingPowerDomesticA003DtoResponse

     * @param apiReq {@code IfaBuyingPowerDomesticA003ApiRequest}
     * @return {@code String}
     * @exception Exception 再建策処理で例外が発生した場合
     */
    @RequestMapping(value = "/brokerageMenu/customerMenu/ifaBuyingPowerDomesticReSearchA003", method = {RequestMethod.POST})
    public String reSearchA003(@RequestBody IfaBuyingPowerDomesticA003ApiRequest apiReq)throws Exception {

        IfaBuyingPowerDomesticA003RequestDto appReq = new IfaBuyingPowerDomesticA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBuyingPowerDomesticA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBuyingPowerDomesticService",
                "reSearchA003", new TypeReference<DataList<IfaBuyingPowerDomesticA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBuyingPowerDomesticA003ApiResponse> apiRes = new DataList<IfaBuyingPowerDomesticA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

