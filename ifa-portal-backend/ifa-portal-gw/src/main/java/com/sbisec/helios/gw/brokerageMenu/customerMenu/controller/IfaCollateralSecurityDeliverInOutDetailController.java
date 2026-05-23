package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityDeliverInOutDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityDeliverInOutDetailA001ResponseDto;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCollateralSecurityDeliverInOutDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCollateralSecurityDeliverInOutDetailA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_010204-02
 * 画面名：代用有価証券入出庫-個別詳細
 *
 * @author SCSK
 * 2023/10/25 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010204-02", screenNumber = "")
public class IfaCollateralSecurityDeliverInOutDetailController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaCollateralSecurityDeliverInOutDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCollateralSecurityDeliverInOutDetailA001ApiRequest
     * Apiレスポンス：IfaCollateralSecurityDeliverInOutDetailA001ApiResponse
     * Dtoリクエスト：IfaCollateralSecurityDeliverInOutDetailA001RequestDto
     * Dtoレスポンス：IfaCollateralSecurityDeliverInOutDetailA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCollateralSecurityDeliverInOutDetailInitializeA001")
    public String initializeA001(@RequestBody IfaCollateralSecurityDeliverInOutDetailA001ApiRequest apiReq)
            throws Exception {
        
        IfaCollateralSecurityDeliverInOutDetailA001RequestDto appReq = new IfaCollateralSecurityDeliverInOutDetailA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "ifaCollateralSecurityDeliverInOutDetailService", "initializeA001",
                new TypeReference<DataList<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaCollateralSecurityDeliverInOutDetailA001ApiResponse> apiRes = new DataList<IfaCollateralSecurityDeliverInOutDetailA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
