package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingCancelConfirmA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingCancelConfirmA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import org.apache.commons.beanutils.BeanUtils;


/**
 * 画面ID：SUB0202_0403-04_1
 * 画面名：投信積立設定解除確認
 *
 * @author WJL
 *
 *     2025/04/10 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0403-04_1", screenNumber = "")
public class IfaMutualFundAccumulateSettingCancelConfirm extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMutualFundAccumulateSettingCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaMutualFundAccumulateSettingCancelConfirmA001ApiRequest apiReq)throws Exception {

        IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto appReq = new IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaMutualFundAccumulateSettingCancelConfirmService",
                "initializeA001", new TypeReference<DataList<IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaMutualFundAccumulateSettingCancelConfirmA001ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingCancelConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaMutualFundAccumulateSettingCancelConfirmCancelA003
     * アクションID：A003
     * アクション名：設定解除
     * APIリクエスト：IfaMutualFundAccumulateSettingCancelConfirmA003ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingCancelConfirmA003ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaMutualFundAccumulateSettingCancelConfirmCancelA003")
    public String accumulateSettingCancelA003(@RequestBody IfaMutualFundAccumulateSettingCancelConfirmA003ApiRequest apiReq)throws Exception {

        IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto appReq = new IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaMutualFundAccumulateSettingCancelConfirmService",
                "accumulateSettingCancelA003", new TypeReference<DataList<IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaMutualFundAccumulateSettingCancelConfirmA003ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingCancelConfirmA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);

    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

