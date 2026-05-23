package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerForeignA002DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBuyingPowerForeignA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBuyingPowerForeignA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBuyingPowerForeignA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBuyingPowerForeignA002ApiResponse;

/**
 * 画面ID：SUB0202_010303-01
 * 画面名：買付余力(外国)

 * @author SCSK渡辺
    2023/10/10 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010303-01", screenNumber = "")
public class IfaBuyingPowerForeignController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaBuyingPowerForeignInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaBuyingPowerForeignA001ApiRequest
     * Apiレスポンス：IfaBuyingPowerForeignA001ApiResponse
     * Dtoリクエスト：IfaBuyingPowerForeignA001DtoRequest
     * Dtoレスポンス：IfaBuyingPowerForeignA001DtoResponse
     * @param apiReq {@code IfaBuyingPowerForeignA001ApiRequest}
     * @return {@code String}
     */

    @PostMapping("/brokerageMenu/customerMenu/ifaBuyingPowerForeignInitializeA001")
    public String initializeA001(@RequestBody IfaBuyingPowerForeignA001ApiRequest apiReq)throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaBuyingPowerForeignA001DtoRequest appReq = new IfaBuyingPowerForeignA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBuyingPowerForeignA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaBuyingPowerForeignService",
                "initializeA001", new TypeReference<DataList<IfaBuyingPowerForeignA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaBuyingPowerForeignA001ApiResponse> apiRes = new DataList<IfaBuyingPowerForeignA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaBuyingPowerForeignReSearchA002
     * アクションID：A002
     * アクション名：再検索
     * APIリクエスト：IfaBuyingPowerForeign002ApiRequest
     * Apiレスポンス：IfaBuyingPowerForeignA001ApiResponse
     * Dtoリクエスト：IfaBuyingPowerForeignA002DtoRequest
     * Dtoレスポンス：IfaBuyingPowerForeignA002DtoResponse
     * @param apiReq {@code IfaBuyingPowerForeignA002ApiRequest}
     * @return {@code String}
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaBuyingPowerForeignReSearchA002")
    public String reSearchA002(@RequestBody IfaBuyingPowerForeignA002ApiRequest apiReq)throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaBuyingPowerForeignA001DtoRequest appReq = new IfaBuyingPowerForeignA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBuyingPowerForeignA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaBuyingPowerForeignService",
                "reSearchA002", new TypeReference<DataList<IfaBuyingPowerForeignA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaBuyingPowerForeignA002ApiResponse> apiRes = new DataList<IfaBuyingPowerForeignA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

