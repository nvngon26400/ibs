package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegisterInfoDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegisterInfoDtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRegisterInfoApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaRegisterInfoApiResponse;


/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連苗
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0701-01", screenNumber = "")
public class IfaRegisterInfoController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaRegisterInfoA00InitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaRegisterInfoApiRequest
     * Apiレスポンス：IfaRegisterInfoApiResponse
     * Dtoリクエスト：IfaRegisterInfoDtoRequest
     * Dtoレスポンス：IfaRegisterInfoDtoResponse
     * 
     * @param apiReq {@code IfaRegisterInfoApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    
    @PostMapping("/brokerageMenu/customerMenu/ifaRegisterInfoA00InitializeA001")
    public String initializeA001(@RequestBody IfaRegisterInfoApiRequest apiReq) throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaRegisterInfoDtoRequest appReq = new IfaRegisterInfoDtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaRegisterInfoDtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaRegisterInfoService", "initializeA001",
                new TypeReference<DataList<IfaRegisterInfoDtoResponse>>() {
                }, appReq);
        
        DataList<IfaRegisterInfoApiResponse> apiRes = new DataList<IfaRegisterInfoApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

