package com.sbisec.helios.gw.common.brokerMaintenance.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaDomesticPositionDetailX001DtoRequest;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaDomesticPositionDetailX001DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.brokerMaintenance.form.IfaDomesticPositionDetailX001ApiRequest;
import com.sbisec.helios.gw.common.brokerMaintenance.form.IfaDomesticPositionDetailX001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB07-05
 * 画面名：建玉詳細(国内)
 * @author <author-name>
 *
 * 2023/08/14 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB07-05", screenNumber = "")
public class IfaDomesticPositionDetailController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/common/brokerMaintenance/IfaDomesticPositionDetailInitializeA001
     * アクションID：X001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticPositionDetailX001ApiRequest
     * Apiレスポンス：IfaDomesticPositionDetailX001ApiResponse
     * Dtoリクエスト：IfaDomesticPositionDetailX001DtoRequest
     * Dtoレスポンス：IfaDomesticPositionDetailX001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/common/brokerMaintenance/ifaDomesticPositionDetailInitializeX001")
    public String initializeX001(@RequestBody IfaDomesticPositionDetailX001ApiRequest apiReq) throws Exception {
        
        IfaDomesticPositionDetailX001DtoRequest appReq = new IfaDomesticPositionDetailX001DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticPositionDetailX001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticPositionDetailService", "initializeX001",
                new TypeReference<DataList<IfaDomesticPositionDetailX001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaDomesticPositionDetailX001ApiResponse> apiRes = new DataList<IfaDomesticPositionDetailX001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
