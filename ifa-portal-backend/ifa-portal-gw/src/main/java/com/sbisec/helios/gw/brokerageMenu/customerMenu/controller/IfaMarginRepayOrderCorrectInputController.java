package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputA010ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCorrectInputA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCorrectInputA010ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCorrectInputApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCorrectInputApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-06_1
 * 画面名：信用返済注文入力
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉 秀行
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-06_1", screenNumber = "")
public class IfaMarginRepayOrderCorrectInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderCorrectInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMarginRepayOrderCorrectInputApiRequest
     * Apiレスポンス：IfaMarginRepayOrderCorrectInputApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderCorrectInputRequestDto
     * Dtoレスポンス：IfaMarginRepayOrderCorrectInputResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderCorrectInputInitializeA001")
    public String initializeA001(@RequestBody IfaMarginRepayOrderCorrectInputApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaMarginRepayOrderCorrectInputRequestDto appReq = new IfaMarginRepayOrderCorrectInputRequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaMarginRepayOrderCorrectInputResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderCorrectInputService", "initializeA001",
                new TypeReference<DataList<IfaMarginRepayOrderCorrectInputResponseDto>>() {
                }, appReq);
        
        DataList<IfaMarginRepayOrderCorrectInputApiResponse> apiRes = new DataList<IfaMarginRepayOrderCorrectInputApiResponse>();
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderCorrectInputApiResponse>>() {
                    }));
        }
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderCorrectInputUpdateA004
     * アクションID：A004
     * アクション名：更新
     * APIリクエスト：IfaMarginRepayOrderCorrectInputApiRequest
     * Apiレスポンス：IfaMarginRepayOrderCorrectInputApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderCorrectInputRequestDto
     * Dtoレスポンス：IfaMarginRepayOrderCorrectInputResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderCorrectInputUpdateA004")
    public String updateA004(@RequestBody IfaMarginRepayOrderCorrectInputApiRequest apiReq) throws Exception {
        
        IfaMarginRepayOrderCorrectInputRequestDto appReq = new IfaMarginRepayOrderCorrectInputRequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginRepayOrderCorrectInputResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderCorrectInputService", "updateA004",
                new TypeReference<DataList<IfaMarginRepayOrderCorrectInputResponseDto>>() {
                }, appReq);
        
        DataList<IfaMarginRepayOrderCorrectInputApiResponse> apiRes = new DataList<IfaMarginRepayOrderCorrectInputApiResponse>();
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderCorrectInputApiResponse>>() {
                    }));
        }
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderCorrectInputCorrectionConfirmA010
     * アクションID：A010
     * アクション名：訂正確認
     * APIリクエスト：IfaMarginRepayOrderCorrectInputA010ApiRequest
     * Apiレスポンス：IfaMarginRepayOrderCorrectInputA010ApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderCorrectInputA010RequestDto
     * Dtoレスポンス：IfaMarginRepayOrderCorrectInputA010ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderCorrectInputCorrectionConfirmA010")
    public String correctionConfirmA010(@RequestBody IfaMarginRepayOrderCorrectInputA010ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaMarginRepayOrderCorrectInputA010RequestDto appReq = new IfaMarginRepayOrderCorrectInputA010RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaMarginRepayOrderCorrectInputA010ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderCorrectInputService", "correctionConfirmA010",
                new TypeReference<DataList<IfaMarginRepayOrderCorrectInputA010ResponseDto>>() {
                }, appReq);
        
        DataList<IfaMarginRepayOrderCorrectInputA010ApiResponse> apiRes = new DataList<IfaMarginRepayOrderCorrectInputA010ApiResponse>();
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderCorrectInputA010ApiResponse>>() {
                    }));
        }
        return jc.toString(apiRes);
    }
}
