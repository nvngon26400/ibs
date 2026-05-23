package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingInputA010ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingInputA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingInputA010ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0403-02_1
 * 画面名：投信積立設定入力
 *
 * @author nicksen.li
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0403-02_1", screenNumber = "")
public class IfaMutualFundAccumulateSettingInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMutualFundAccumulateSettingInputA001ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingInputA001ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingInputA001RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingInputA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingInputInitializeA001")
    public String initializeA001(@RequestBody IfaMutualFundAccumulateSettingInputA001ApiRequest apiReq)
            throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaMutualFundAccumulateSettingInputA001RequestDto appReq = new IfaMutualFundAccumulateSettingInputA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMutualFundAccumulateSettingInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingInputService", "initializeA001",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingInputA001ResponseDto>>() {
                }, appReq);

        DataList<IfaMutualFundAccumulateSettingInputA001ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingInputA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingInputConfirmA010
     * アクションID：A010
     * アクション名：設定確認
     * APIリクエスト：IfaMutualFundAccumulateSettingInputA010ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingInputA010ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingInputA010RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingInputA010ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingInputConfirmA010")
    public String confirmA010(@RequestBody IfaMutualFundAccumulateSettingInputA010ApiRequest apiReq)
            throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaMutualFundAccumulateSettingInputA010RequestDto appReq = new IfaMutualFundAccumulateSettingInputA010RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMutualFundAccumulateSettingInputA010ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingInputService", "settingConfirmA010",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingInputA010ResponseDto>>() {
                }, appReq);

        DataList<IfaMutualFundAccumulateSettingInputA010ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingInputA010ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
