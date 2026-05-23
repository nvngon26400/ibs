package com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA004ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaForeignBondBuyAmountInputListA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaForeignBondBuyAmountInputListA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaForeignBondBuyAmountInputListA004ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaForeignBondBuyAmountInputListA004ApiResponse;

/**
 * 画面ID：SUB0504_04-01 
 * 画面名：外債買付代金入力一覧
 * 2026/04/14 新規作成
 *
 * @author CuongHD
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0504_04-01", screenNumber = "")
public class IfaForeignBondBuyAmountInputListController extends BaseController
{
    
    JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/companyEmployeeMenu/tradeDataManage/ifaForeignBondBuyAmountInputListInitializeA001
     * アクションID：A001 
     * アクション名：初期化
     * APIリクエスト：IfaForeignBondBuyAmountInputListA001ApiRequest
     * Apiレスポンス：IfaForeignBondBuyAmountInputListA001ApiResponse
     * Dtoリクエスト：IfaForeignBondBuyAmountInputListA001RequestDto
     * Dtoレスポンス：IfaForeignBondBuyAmountInputListA001ApiResponse
     * 
     * @param apiReq {@code IfaForeignBondBuyAmountInputListA001ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/companyEmployeeMenu/tradeDataManage/ifaForeignBondBuyAmountInputListInitializeA001")
    public String initializeA001(@RequestBody IfaForeignBondBuyAmountInputListA001ApiRequest apiReq) throws Exception {
        
        IfaForeignBondBuyAmountInputListA001RequestDto appReq = new IfaForeignBondBuyAmountInputListA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaForeignBondBuyAmountInputListA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignBondBuyAmountInputListService", "initializeA001",
                new TypeReference<DataList<IfaForeignBondBuyAmountInputListA001ResponseDto>>()
                {
                }, appReq);
        DataList<IfaForeignBondBuyAmountInputListA001ApiResponse> apiRes = new DataList<IfaForeignBondBuyAmountInputListA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/tradeDataManage/ifaForeignBondBuyAmountInputListDeleteA004
     * アクションID：A004 
     * アクション名：削除
     * APIリクエスト：IfaForeignBondBuyAmountInputListA004ApiRequest
     * Apiレスポンス：IfaForeignBondBuyAmountInputListA001ApiResponse
     * Dtoリクエスト：IfaForeignBondBuyAmountInputListA004RequestDto
     * Dtoレスポンス：IfaForeignBondBuyAmountInputListA004ResponseDto
     * 
     * @param apiReq {@code IfaForeignBondBuyAmountInputListA004ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/companyEmployeeMenu/tradeDataManage/ifaForeignBondBuyAmountInputListDeleteA004")
    public String deleteA004(@RequestBody IfaForeignBondBuyAmountInputListA004ApiRequest apiReq) throws Exception {
        
        IfaForeignBondBuyAmountInputListA004RequestDto appReq = new IfaForeignBondBuyAmountInputListA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaForeignBondBuyAmountInputListA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignBondBuyAmountInputListService", "deleteA004",
                new TypeReference<DataList<IfaForeignBondBuyAmountInputListA004ResponseDto>>()
                {
                }, appReq);
        DataList<IfaForeignBondBuyAmountInputListA004ApiResponse> apiRes = new DataList<IfaForeignBondBuyAmountInputListA004ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
}
