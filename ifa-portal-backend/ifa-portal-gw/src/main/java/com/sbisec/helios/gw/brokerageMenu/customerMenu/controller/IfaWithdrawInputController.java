package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaWithdrawInputA004ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawInputA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawInputA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaWithdrawInputA004ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0601-01 画面名：出金入力
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0601-01", screenNumber = "")
public class IfaWithdrawInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaWithdrawInputInitializeA001
     * アクションID：A001
     * アクション名：初期化 
     * APIリクエスト：IfaWithdrawInputA001ApiRequest
     * APIレスポンス：IfaWithdrawInputA001ApiResponse
     * Dtoリクエスト：IfaWithdrawInputA001RequestDto
     * Dtoレスポンス：IfaWithdrawInputA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaWithdrawInputInitializeA001")
    public String initializeA001(@RequestBody IfaWithdrawInputA001ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaWithdrawInputA001RequestDto appReq = new IfaWithdrawInputA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaWithdrawInputA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaWithdrawInputService",
                "initializeA001", new TypeReference<DataList<IfaWithdrawInputA001ResponseDto>>() {
                }, appReq);
        DataList<IfaWithdrawInputA001ApiResponse> apiRes = new DataList<IfaWithdrawInputA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaWithdrawInputSelectAcBalanceA002
     * アクションID：A002
     * アクション名：出金可能額を取得
     * APIリクエスト：IfaWithdrawInputA002ApiRequest
     * Apiレスポンス：IfaWithdrawInputA002ApiResponse
     * Dtoリクエスト：IfaWithdrawInputA002RequestDto
     * Dtoレスポンス：IfaWithdrawInputA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 出金可能額を取得の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaWithdrawInputSelectAcBalanceA002")
    public String selectAcBalanceA002(@RequestBody IfaWithdrawInputA002ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaWithdrawInputA002RequestDto appReq = new IfaWithdrawInputA002RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaWithdrawInputA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaWithdrawInputService",
                "selectAcBalanceA002", new TypeReference<DataList<IfaWithdrawInputA002ResponseDto>>() {
                }, appReq);
        DataList<IfaWithdrawInputA002ApiResponse> apiRes = new DataList<IfaWithdrawInputA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaWithdrawInputExecuteConfirmCheckA003
     * アクションID：A003
     * アクション名：出金確認
     * APIリクエスト：IfaWithdrawInputA003ApiRequest
     * Apiレスポンス：IfaWithdrawInputA003ApiResponse
     * Dtoリクエスト：IfaWithdrawInputA003RequestDto
     * Dtoレスポンス：IfaWithdrawInputA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 出金確認の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaWithdrawInputExecuteConfirmCheckA003")
    public String executeConfirmCheckA003(@RequestBody IfaWithdrawInputA003ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaWithdrawInputA003RequestDto appReq = new IfaWithdrawInputA003RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaWithdrawInputA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaWithdrawInputService",
                "executeConfirmCheckA003", new TypeReference<DataList<IfaWithdrawInputA003ResponseDto>>() {
                }, appReq);
        DataList<IfaWithdrawInputA003ApiResponse> apiRes = new DataList<IfaWithdrawInputA003ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaWithdrawInputCancelConfirmCheckA004
     * アクションID：A004
     * アクション名：出金取消確認
     * APIリクエスト：IfaWithdrawInputA004ApiRequest
     * Apiレスポンス：IfaWithdrawInputA004ApiResponse
     * Dtoレスポンス：IfaWithdrawInputA004ResponseDto
     * Dtoレスポンス：IfaWithdrawInputA004ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 出金取消確認の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaWithdrawInputCancelConfirmCheckA004")
    public String cancelConfirmCheckA004(@RequestBody IfaWithdrawInputA004ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaWithdrawInputA004RequestDto appReq = new IfaWithdrawInputA004RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaWithdrawInputA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaWithdrawInputService",
                "cancelConfirmCheckA004", new TypeReference<DataList<IfaWithdrawInputA004ResponseDto>>() {
                }, appReq);
        DataList<IfaWithdrawInputA004ApiResponse> apiRes = new DataList<IfaWithdrawInputA004ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}
