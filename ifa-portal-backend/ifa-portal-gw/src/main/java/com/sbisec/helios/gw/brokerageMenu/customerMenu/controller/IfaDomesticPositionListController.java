package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticPositionListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticPositionListA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticPositionListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticPositionListA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_010202-01
 * 画面名：国内建玉一覧
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010202-01", screenNumber = "")
public class IfaDomesticPositionListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticPositionListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticPositionListA001ApiRequest
     * Apiレスポンス：IfaDomesticPositionListA001ApiResponse
     * Dtoリクエスト：IfaDomesticPositionListA001RequestDto
     * Dtoレスポンス：IfaDomesticPositionListA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticPositionListInitializeA001")
    public String initializeA001(@RequestBody IfaDomesticPositionListA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaDomesticPositionListA001RequestDto appReq = new IfaDomesticPositionListA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDomesticPositionListA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDomesticPositionListService", "initializeA001",
                new TypeReference<DataList<IfaDomesticPositionListA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticPositionListA001ApiResponse> apiRes = new DataList<IfaDomesticPositionListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
