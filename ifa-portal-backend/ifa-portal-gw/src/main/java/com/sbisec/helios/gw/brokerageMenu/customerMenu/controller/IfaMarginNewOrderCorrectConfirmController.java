package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectConfirmA001bDtoRequest;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCorrectConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCorrectConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-02_2
 * 画面名：信用新規注文訂正確認
 * @author SCSK
 *
 * 2024/04/16 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-02_2", screenNumber = "")
public class IfaMarginNewOrderCorrectConfirmController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderCorrectConfirmCorrectionOrderA001
     * アクションID：A001
     * アクション名：訂正発注
     * APIリクエスト：IfaMarginNewOrderCorrectConfirmA001ApiRequest
     * Apiレスポンス：IfaMarginNewOrderCorrectConfirmA001ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderCorrectConfirmA001DtoRequest
     * Dtoレスポンス：IfaMarginNewOrderCorrectConfirmA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginNewOrderCorrectConfirmCorrectionOrderA001")
    public String correctionOrderA001(@RequestBody IfaMarginNewOrderCorrectConfirmA001ApiRequest apiReq)throws Exception
    {

        IfaMarginNewOrderCorrectConfirmA001bDtoRequest appReqb = new IfaMarginNewOrderCorrectConfirmA001bDtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqb, apiReq);

        DataList<?> appResb = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderCorrectConfirmService", "correctionOrderA001b",
                new TypeReference<DataList<?>>() {
                }, appReqb);
        
        DataList<IfaMarginNewOrderCorrectConfirmA001ApiResponse> apiRes = new DataList<IfaMarginNewOrderCorrectConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appResb);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

