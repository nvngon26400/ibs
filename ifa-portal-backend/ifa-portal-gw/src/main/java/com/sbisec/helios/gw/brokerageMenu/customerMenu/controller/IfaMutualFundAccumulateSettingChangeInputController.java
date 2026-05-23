package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA009RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA009ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingChangeInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingChangeInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingChangeInputA009ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingChangeInputA009ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0403-03_1
 * 画面名：投信積立設定変更入力
 *
 * @author nicksen.li
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0403-03_1", screenNumber = "")
public class IfaMutualFundAccumulateSettingChangeInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaMutualFundAccumulateSettingChangeInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMutualFundAccumulateSettingChangeInputA001ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingChangeInputA001ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingChangeInputA001RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingChangeInputA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaMutualFundAccumulateSettingChangeInputInitializeA001")
    public String initializeA001(@RequestBody IfaMutualFundAccumulateSettingChangeInputA001ApiRequest apiReq) throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaMutualFundAccumulateSettingChangeInputA001RequestDto appReq = new IfaMutualFundAccumulateSettingChangeInputA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMutualFundAccumulateSettingChangeInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingChangeInputService", "initializeA001",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingChangeInputA001ResponseDto>>() {
                }, appReq);

        DataList<IfaMutualFundAccumulateSettingChangeInputA001ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingChangeInputA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaMutualFundAccumulateSettingChangeInputConfirmA009
     * アクションID：A009
     * アクション名：設定変更確認
     * APIリクエスト：IfaMutualFundAccumulateSettingChangeInputA009ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingChangeInputA009ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingChangeInputA009RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingChangeInputA009ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 設定変更確認の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaMutualFundAccumulateSettingChangeInputConfirmA009")
    public String confirmA009(@RequestBody IfaMutualFundAccumulateSettingChangeInputA009ApiRequest apiReq)
            throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaMutualFundAccumulateSettingChangeInputA009RequestDto appReq = new IfaMutualFundAccumulateSettingChangeInputA009RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMutualFundAccumulateSettingChangeInputA009ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingChangeInputService", "settingConfirmA009",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingChangeInputA009ResponseDto>>() {
                }, appReq);

        DataList<IfaMutualFundAccumulateSettingChangeInputA009ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingChangeInputA009ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
