package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaShortPositionDividendAdjustDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaShortPositionDividendAdjustDetailA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaShortPositionDividendAdjustDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaShortPositionDividendAdjustDetailA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_010302-02
 * 画面名：売建配当調整金明細
 * 2024/06/21 新規作成
 *
 * @author SCSK 笹倉 秀行
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010302-02", screenNumber = "")
public class IfaShortPositionDividendAdjustDetailController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaShortPositionDividendAdjustDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaShortPositionDividendAdjustDetailA001ApiRequest
     * Apiレスポンス：IfaShortPositionDividendAdjustDetailA001ApiResponse
     * Dtoリクエスト：IfaShortPositionDividendAdjustDetailA001RequestDto
     * Dtoレスポンス：IfaShortPositionDividendAdjustDetailA001ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaShortPositionDividendAdjustDetailInitializeA001")
    public String initializeA001(@RequestBody IfaShortPositionDividendAdjustDetailA001ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaShortPositionDividendAdjustDetailA001RequestDto appReq = new IfaShortPositionDividendAdjustDetailA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaShortPositionDividendAdjustDetailA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaShortPositionDividendAdjustDetailService", "initializeA001",
                new TypeReference<DataList<IfaShortPositionDividendAdjustDetailA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaShortPositionDividendAdjustDetailA001ApiResponse> apiRes = new DataList<IfaShortPositionDividendAdjustDetailA001ApiResponse>();
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaShortPositionDividendAdjustDetailA001ApiResponse>>() {
                    }));
        }
        return jc.toString(apiRes);
    }
}
