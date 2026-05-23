package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBrandPositionListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBrandPositionListA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBrandPositionListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaBrandPositionListA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_010202-03
 * 画面名：銘柄別建玉一覧
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010202-03", screenNumber = "")
public class IfaBrandPositionListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaBrandPositionListA001ApiRequest
     * Apiレスポンス：IfaBrandPositionListA001ApiResponse
     * Dtoリクエスト：IfaBrandPositionListA001RequestDto
     * Dtoレスポンス：IfaBrandPositionListA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaBrandPositionListInitializeA001")
    public String initializeA001(@RequestBody IfaBrandPositionListA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaBrandPositionListA001RequestDto appReq = new IfaBrandPositionListA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaBrandPositionListA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBrandPositionListService",
                "initializeA001", new TypeReference<DataList<IfaBrandPositionListA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBrandPositionListA001ApiResponse> apiRes = new DataList<IfaBrandPositionListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
