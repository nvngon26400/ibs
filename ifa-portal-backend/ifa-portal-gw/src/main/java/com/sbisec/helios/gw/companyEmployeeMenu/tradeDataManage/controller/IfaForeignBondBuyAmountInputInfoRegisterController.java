package com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.controller;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaForeignBondBuyAmountInputInfoRegisterA003ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaForeignBondBuyAmountInputInfoRegisterA003ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaForeignBondBuyAmountInputInfoRegisterA005ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaForeignBondBuyAmountInputInfoRegisterA005ApiResponse;

/**
 * 画面ID：SUB0504_04-02_1 
 * 画面名：外債買付代金入力情報登録 
 * 2026/04/14 新規作成
 *
 * @author CuongHD
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0504_04-02_1", screenNumber = "")
public class IfaForeignBondBuyAmountInputInfoRegisterController extends BaseController
{
    
    JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/companyEmployeeMenu/tradeDataManage/ifaForeignBondBuyAmountInputInfoRegisterNameKanjiInputA003
     * アクションID：A003 アクション名： 外債買付代金入力情報登録 APIリクエスト：IfaBbApplyInputA004ApiRequest
     * Apiレスポンス：IfaForeignBondBuyAmountInputInfoRegisterA003ApiResponse
     * Dtoリクエスト：IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto
     * Dtoレスポンス：IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping("/companyEmployeeMenu/tradeDataManage/ifaForeignBondBuyAmountInputInfoRegisterNameKanjiInputA003")
    public String nameKanjiInputA003(@RequestBody @Valid IfaForeignBondBuyAmountInputInfoRegisterA003ApiRequest apiReq)
            throws Exception {
        
        IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto appReq = new IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignBondBuyAmountInputInfoRegisterService", "nameKanjiInputA003",
                new TypeReference<DataList<IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto>>()
                {
                }, appReq);
        
        DataList<IfaForeignBondBuyAmountInputInfoRegisterA003ApiResponse> apiRes = new DataList<IfaForeignBondBuyAmountInputInfoRegisterA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/tradeDataManage/ifaForeignBondBuyAmountInputInfoRegisterInsertA005
     * アクションID：A005 アクション名：外債買付代金情報登録
     * APIリクエスト：IfaForeignBondBuyAmountInputInfoRegisterA005ApiRequest
     * Apiレスポンス：IfaForeignBondBuyAmountInputInfoRegisterA003ApiResponse
     * Dtoリクエスト：IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto
     * Dtoレスポンス：IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception BM交付登録の際、例外が発生した場合
     */
    @PostMapping("/companyEmployeeMenu/tradeDataManage/ifaForeignBondBuyAmountInputInfoRegisterInsertA005")
    public String insertA005(@RequestBody @Valid IfaForeignBondBuyAmountInputInfoRegisterA005ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto appReq = new IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignBondBuyAmountInputInfoRegisterService", "insertA005",
                new TypeReference<DataList<IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto>>()
                {
                }, appReq);
        
        DataList<IfaForeignBondBuyAmountInputInfoRegisterA005ApiResponse> apiRes = new DataList<IfaForeignBondBuyAmountInputInfoRegisterA005ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
}
