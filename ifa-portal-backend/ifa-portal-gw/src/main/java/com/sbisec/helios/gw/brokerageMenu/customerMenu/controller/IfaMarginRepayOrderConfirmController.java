package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-04_2
 * 画面名：信用返済注文確認
 * 2024/04/04 新規作成
 *
 * @author 宇田川達弥
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-04_2", screenNumber = "")
public class IfaMarginRepayOrderConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderConfirmOrderPlacementA001
     * アクションID：A001
     * アクション名：注文発注
     * APIリクエスト：IfaMarginRepayOrderConfirmA001ApiRequest
     * Apiレスポンス：IfaMarginRepayOrderConfirmA001ApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderConfirmA001aRequestDto
     * Dtoリクエスト：IfaMarginRepayOrderConfirmA001bRequestDto
     * Dtoレスポンス：IfaMarginRepayOrderConfirmA001aResponseDto
     * Dtoレスポンス：IfaMarginRepayOrderConfirmA001bResponseDto
     *
     * @param apiReq APIリクエスト
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @RequestMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderConfirmOrderPlacementA001", method = {
            RequestMethod.POST })
    public String orderPlacementA001(@RequestBody IfaMarginRepayOrderConfirmA001ApiRequest apiReq) throws Exception {
        
        final IfaMarginRepayOrderConfirmA001aRequestDto appReqA = new IfaMarginRepayOrderConfirmA001aRequestDto();
        
        // 発注前の注文内容登録
        BeanUtils.copyProperties(appReqA, apiReq);
        
        final DataList<IfaMarginRepayOrderConfirmA001aResponseDto> appResA = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderConfirmService", "orderPlacementA001a",
                new TypeReference<DataList<IfaMarginRepayOrderConfirmA001aResponseDto>>() {
                }, appReqA);
        
        if (appResA.getErrorLevel() < ErrorLevel.SUCCESS.getId()) {
            // 発注前の注文登録処理に失敗した場合、結果を返却する
            return jc.toString(appResA);
        }
        
        // 注文発注の本処理
        final IfaMarginRepayOrderConfirmA001bRequestDto appReqB = new IfaMarginRepayOrderConfirmA001bRequestDto();
        appReqB.setIfaOrderNo(appResA.getDataList().get(0).getIfaOrderNo());
        appReqB.setRequest(appReqA);
        appReqB.setWarningMessages(appResA.getDataList().get(0).getWarningMessages());
        final DataList<IfaMarginRepayOrderConfirmA001bResponseDto> appResB = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderConfirmService", "orderPlacementA001b",
                new TypeReference<DataList<IfaMarginRepayOrderConfirmA001bResponseDto>>() {
                }, appReqB);
        
        // 結果を返却する
        final DataList<IfaMarginRepayOrderConfirmA001ApiResponse> apiRes = new DataList<IfaMarginRepayOrderConfirmA001ApiResponse>();
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appResB.getErrorLevel());
        apiRes.setMessage(appResB.getMessage());
        apiRes.setRequestedTime(appResB.getRequestedTime());
        apiRes.setMaxRownum(appResB.getMaxRownum());
        apiRes.setTitle(appResB.getTitle());
        apiRes.setTotalSize(appResB.getTotalSize());
        apiRes.setReturnCode(appResB.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appResB.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appResB.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderConfirmA001ApiResponse>>() {
                    }));
        }
        
        return jc.toString(apiRes);
    }
    
}
