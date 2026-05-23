package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

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
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferInputA001ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA002ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferInputA002ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA003ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferInputA003ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferInputA004ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferInputA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignMarginDepositTransferInputA004ApiResponse;

/**
 * 画面ID：SUB0202_0304-01_1
 * 画面名：米株信用保証金振替入力
 *
 * @author SCSK
 *     2023/12/04 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0304-01_1", screenNumber = "")
public class IfaForeignMarginDepositTransferInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignMarginDepositTransferInputA001ApiRequest
     * Apiレスポンス：IfaForeignMarginDepositTransferInputA001ApiResponse
     * Dtoリクエスト：IfaForeignMarginDepositTransferInputA001RequestDto
     * Dtoレスポンス：IfaForeignMarginDepositTransferInputA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferInputInitializeA001")
    public String initializeA001(@RequestBody IfaForeignMarginDepositTransferInputA001ApiRequest apiReq) throws Exception {

        IfaForeignMarginDepositTransferInputA001RequestDto appReq = new IfaForeignMarginDepositTransferInputA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // 顧客共通情報をサービスで使用できるように設定
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginDepositTransferInputA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaForeignMarginDepositTransferInputService",
                "initializeA001", new TypeReference<DataList<IfaForeignMarginDepositTransferInputA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginDepositTransferInputA001ApiResponse> apiRes = new DataList<IfaForeignMarginDepositTransferInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferInputAccountSelectionA002
     * アクションID：A002
     * アクション名：口座選択
     * APIリクエスト：IfaForeignMarginDepositTransferInputA002ApiRequest
     * Apiレスポンス：IfaForeignMarginDepositTransferInputA002ApiResponse
     * Dtoリクエスト：IfaForeignMarginDepositTransferInputA002RequestDto
     * Dtoレスポンス：IfaForeignMarginDepositTransferInputA002ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 口座選択処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferInputAccountSelectionA002")
    public String accountSelectionA002(@RequestBody IfaForeignMarginDepositTransferInputA002ApiRequest apiReq) throws Exception {

        IfaForeignMarginDepositTransferInputA002RequestDto appReq = new IfaForeignMarginDepositTransferInputA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報をサービスで使用できるように設定
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginDepositTransferInputA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaForeignMarginDepositTransferInputService",
                "accountSelectionA002", new TypeReference<DataList<IfaForeignMarginDepositTransferInputA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginDepositTransferInputA002ApiResponse> apiRes = new DataList<IfaForeignMarginDepositTransferInputA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferInputDisplayResultA003
     * アクションID：A003
     * アクション名：結果を表示
     * APIリクエスト：IfaForeignMarginDepositTransferInputA003ApiRequest
     * Apiレスポンス：IfaForeignMarginDepositTransferInputA003ApiResponse
     * Dtoリクエスト：IfaForeignMarginDepositTransferInputA003RequestDto
     * Dtoレスポンス：IfaForeignMarginDepositTransferInputA003ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 結果表示処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferInputDisplayResultA003")
    public String displayResultA003(@RequestBody IfaForeignMarginDepositTransferInputA003ApiRequest apiReq) throws Exception {

        IfaForeignMarginDepositTransferInputA003RequestDto appReq = new IfaForeignMarginDepositTransferInputA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        // 顧客共通情報をサービスで使用できるように設定
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginDepositTransferInputA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaForeignMarginDepositTransferInputService",
                "displayResultA003", new TypeReference<DataList<IfaForeignMarginDepositTransferInputA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginDepositTransferInputA003ApiResponse> apiRes = new DataList<IfaForeignMarginDepositTransferInputA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferInputTransferConfirmA004
     * アクションID：A004
     * アクション名：振替確認
     * APIリクエスト：IfaForeignMarginDepositTransferInputA004ApiRequest
     * Apiレスポンス：IfaForeignMarginDepositTransferInputA004ApiResponse
     * Dtoリクエスト：IfaForeignMarginDepositTransferInputA004DtoRequest
     * Dtoレスポンス：IfaForeignMarginDepositTransferInputA004DtoResponse
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 振替確認処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginDepositTransferInputTransferConfirmA004")
    public String transferConfirmA004(@RequestBody IfaForeignMarginDepositTransferInputA004ApiRequest apiReq) throws Exception {

        IfaForeignMarginDepositTransferInputA004RequestDto appReq = new IfaForeignMarginDepositTransferInputA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // 顧客共通情報をサービスで使用できるように設定
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaForeignMarginDepositTransferInputA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaForeignMarginDepositTransferInputService",
                "transferConfirmA004", new TypeReference<DataList<IfaForeignMarginDepositTransferInputA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginDepositTransferInputA004ApiResponse> apiRes = new DataList<IfaForeignMarginDepositTransferInputA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

