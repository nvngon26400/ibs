package com.sbisec.helios.gw.common.brokerMaintenance.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignPositionDetailX001RequestDto;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignPositionDetailX001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.brokerMaintenance.form.IfaForeignPositionDetailX001ApiRequest;
import com.sbisec.helios.gw.common.brokerMaintenance.form.IfaForeignPositionDetailX001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB07-06
 * 画面名：建玉詳細(米株)
 * @author 松田
 *
 * 2023/11/24 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB07-06", screenNumber = "")
public class IfaForeignPositionDetailController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/common/brokerMaintenance/ifaForeignPositionDetailInitializeX001
     * アクションID：X001
     * アクション名：初期化
     * APIリクエスト：IfaForeignPositionDetailX001ApiRequest
     * Apiレスポンス：IfaForeignPositionDetailX001ApiResponse
     * Dtoリクエスト：IfaForeignPositionDetailX001DtoRequest
     * Dtoレスポンス：IfaForeignPositionDetailX001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/common/brokerMaintenance/ifaForeignPositionDetailInitializeX001")
    public String initializeX001(@RequestBody IfaForeignPositionDetailX001ApiRequest apiReq) throws Exception {
        
        IfaForeignPositionDetailX001RequestDto appReq = new IfaForeignPositionDetailX001RequestDto();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignPositionDetailX001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignPositionDetailService", "initializeX001",
                new TypeReference<DataList<IfaForeignPositionDetailX001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignPositionDetailX001ApiResponse> apiRes = new DataList<IfaForeignPositionDetailX001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
