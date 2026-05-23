package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundDetailInfoA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundDetailInfoA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundDetailInfoA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundDetailInfoA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0401-03
 * 画面名：投信詳細情報
 *
 * @author SCSK
 *
 *     2024/04/15 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0401-03", screenNumber = "")
public class IfaMutualFundDetailInfoController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundDetailInfoInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMutualFundDetailInfoA001ApiRequest
     * Apiレスポンス：IfaMutualFundDetailInfoA001ApiResponse
     * Dtoリクエスト：IfaMutualFundDetailInfoA001DtoRequest
     * Dtoレスポンス：IfaMutualFundDetailInfoA001DtoResponse
     *
     * @param apiReq API要求
     * @return JSON文字列
     * @exception Exception システム例外
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMutualFundDetailInfoInitializeA001")
    public String initializeA001(@RequestBody @Validated IfaMutualFundDetailInfoA001ApiRequest apiReq)
            throws Exception {
        
        IfaMutualFundDetailInfoA001RequestDto appReq = new IfaMutualFundDetailInfoA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaMutualFundDetailInfoA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundDetailInfoService", "initializeA001",
                new TypeReference<DataList<IfaMutualFundDetailInfoA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaMutualFundDetailInfoA001ApiResponse> apiRes = new DataList<IfaMutualFundDetailInfoA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
