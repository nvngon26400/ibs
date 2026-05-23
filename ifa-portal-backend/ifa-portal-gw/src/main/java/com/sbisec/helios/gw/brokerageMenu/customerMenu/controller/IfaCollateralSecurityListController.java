package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA004ResponseDto;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCollateralSecurityListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCollateralSecurityListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCollateralSecurityListA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCollateralSecurityListA004ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_010204-01
 * 画面名：代用有価証券一覧
 *
 * @author SCSK
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010204-01", screenNumber = "")
public class IfaCollateralSecurityListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCollateralSecurityListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCollateralSecurityListA001ApiRequest
     * Apiレスポンス：IfaCollateralSecurityListA001ApiResponse
     * Dtoリクエスト：IfaCollateralSecurityListA001DtoRequest
     * Dtoレスポンス：IfaCollateralSecurityListA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    
    @PostMapping("/brokerageMenu/customerMenu/ifaCollateralSecurityListInitializeA001")
    public String initialA001(@RequestBody IfaCollateralSecurityListA001ApiRequest apiReq) throws Exception {
        
        IfaCollateralSecurityListA001RequestDto appReq = new IfaCollateralSecurityListA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaCollateralSecurityListA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "ifaCollateralSecurityListService", "initialA001",
                new TypeReference<DataList<IfaCollateralSecurityListA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaCollateralSecurityListA001ApiResponse> apiRes = new DataList<IfaCollateralSecurityListA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCollateralSecurityListUpdateA004
     * アクションID：A004
     * アクション名：更新
     * APIリクエスト：IfaCollateralSecurityListA004ApiRequest
     * Apiレスポンス：IfaCollateralSecurityListA004ApiResponse
     * Dtoリクエスト：IfaCollateralSecurityListA004DtoRequest
     * Dtoレスポンス：IfaCollateralSecurityListA004DtoResponse
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception 更新処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCollateralSecurityListUpdateA004")
    public String updateA004(@RequestBody IfaCollateralSecurityListA004ApiRequest apiReq) throws Exception {
        
        IfaCollateralSecurityListA004RequestDto appReq = new IfaCollateralSecurityListA004RequestDto();
        //IfaUtils.copyBean(appReq, apiReq);
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaCollateralSecurityListA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "ifaCollateralSecurityListService", "updateA004",
                new TypeReference<DataList<IfaCollateralSecurityListA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaCollateralSecurityListA004ApiResponse> apiRes = new DataList<IfaCollateralSecurityListA004ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
