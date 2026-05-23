package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyInputA005ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyInputA005ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0204_01-02_1
 * 画面名：BB申込入力
 *
 * @author BASE李
 * 
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_01-02_1", screenNumber = "")
public class IfaBbApplyInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    

    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyInputInitializeIpoPoListTransitionA001
     * アクションID：A001
     * アクション名：初期化（IPO/PO一覧遷移）
     * APIリクエスト：IfaBbApplyInputA001ApiRequest
     * Apiレスポンス：IfaBbApplyInputA001ApiResponse
     * Dtoリクエスト：IfaBbApplyInputA001RequestDto
     * Dtoレスポンス：IfaBbApplyInputA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaBbApplyInputInitializeIpoPoListTransitionA001")
    public String initializeIpoPoListTransitionA001(@RequestBody IfaBbApplyInputA001ApiRequest apiReq) throws Exception {

        IfaBbApplyInputA001RequestDto appReq = new IfaBbApplyInputA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyInputA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyInputService",
                "initializeIpoPoListTransitionA001", new TypeReference<DataList<IfaBbApplyInputA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyInputA001ApiResponse> apiRes = new DataList<>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyInputInitializeCustomerListTransitionA002
     * アクションID：A002
     * アクション名：初期化（顧客一覧遷移）
     * APIリクエスト：IfaBbApplyInputA002ApiRequest
     * Apiレスポンス：IfaBbApplyInputA002ApiResponse
     * Dtoリクエスト：IfaBbApplyInputA002RequestDto
     * Dtoレスポンス：IfaBbApplyInputA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaBbApplyInputInitializeCustomerListTransitionA002")
    public String initializeCustomerListTransitionA002(@RequestBody IfaBbApplyInputA002ApiRequest apiReq) throws Exception {

        IfaBbApplyInputA002RequestDto appReq = new IfaBbApplyInputA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyInputA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyInputService",
                "initializeCustomerListTransitionA002", new TypeReference<DataList<IfaBbApplyInputA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyInputA002ApiResponse> apiRes = new DataList<IfaBbApplyInputA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyInputBrandChangeA003
     * アクションID：A003
     * アクション名：銘柄変更
     * APIリクエスト：IfaBbApplyInputA003ApiRequest
     * Apiレスポンス：IfaBbApplyInputA003ApiResponse
     * Dtoリクエスト：IfaBbApplyInputA003RequestDto
     * Dtoレスポンス：IfaBbApplyInputA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaBbApplyInputBrandChangeA003")
    public String brandChangeA003(@RequestBody IfaBbApplyInputA003ApiRequest apiReq) throws Exception {

        IfaBbApplyInputA003RequestDto appReq = new IfaBbApplyInputA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyInputA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyInputService",
                "brandChangeA003", new TypeReference<DataList<IfaBbApplyInputA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyInputA003ApiResponse> apiRes = new DataList<IfaBbApplyInputA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyInputBranchAccountNumberInputA004
     * アクションID：A004
     * アクション名：部店・口座番号入力
     * APIリクエスト：IfaBbApplyInputA004ApiRequest
     * Apiレスポンス：IfaBbApplyInputA004ApiResponse
     * Dtoリクエスト：IfaBbApplyInputA004RequestDto
     * Dtoレスポンス：IfaBbApplyInputA004ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaBbApplyInputBranchAccountNumberInputA004")
    public String branchAccountNumberInputA004(@RequestBody IfaBbApplyInputA004ApiRequest apiReq) throws Exception {

        IfaBbApplyInputA004RequestDto appReq = new IfaBbApplyInputA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyInputA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyInputService",
                "branchAccountNumberInputA004", new TypeReference<DataList<IfaBbApplyInputA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyInputA004ApiResponse> apiRes = new DataList<IfaBbApplyInputA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyInputApplicationConfirmA005
     * アクションID：A005
     * アクション名：申込確認
     * APIリクエスト：IfaBbApplyInputA005ApiRequest
     * Apiレスポンス：IfaBbApplyInputA005ApiResponse
     * Dtoリクエスト：IfaBbApplyInputA005RequestDto
     * Dtoレスポンス：IfaBbApplyInputA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaBbApplyInputApplicationConfirmA005")
    public String applicationConfirmA005(@RequestBody IfaBbApplyInputA005ApiRequest apiReq) throws Exception {

        IfaBbApplyInputA005RequestDto appReq = new IfaBbApplyInputA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyInputA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyInputService",
                "applicationConfirmA005", new TypeReference<DataList<IfaBbApplyInputA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyInputA005ApiResponse> apiRes = new DataList<IfaBbApplyInputA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
}

