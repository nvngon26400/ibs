package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBulkChangeInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBulkChangeInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBulkChangeInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBulkChangeInputA005ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0403-05_1
 * 画面名：投信積立設定一括変更入力
 *
 * @author nicksen.li
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0403-05_1", screenNumber = "")
public class IfaMutualFundAccumulateSettingBulkChangeInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBulkChangeInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMutualFundAccumulateSettingBulkChangeInputA001ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingBulkChangeInputA001ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingBulkChangeInputA001RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBulkChangeInputInitializeA001")
    public String initializeA001(@RequestBody IfaMutualFundAccumulateSettingBulkChangeInputA001ApiRequest apiReq) throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaMutualFundAccumulateSettingBulkChangeInputA001RequestDto appReq = new IfaMutualFundAccumulateSettingBulkChangeInputA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingBulkChangeInputService", "initializeA001",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto>>() {
                }, appReq);

        DataList<IfaMutualFundAccumulateSettingBulkChangeInputA001ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingBulkChangeInputA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBulkChangeInputConfirmA005
     * アクションID：A005
     * アクション名：設定変更確認
     * APIリクエスト：IfaMutualFundAccumulateSettingBulkChangeInputA005ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingBulkChangeInputA005ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingBulkChangeInputA005RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 設定変更確認の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBulkChangeInputConfirmA005")
    public String confirmA005(@RequestBody IfaMutualFundAccumulateSettingBulkChangeInputA005ApiRequest apiReq)
            throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaMutualFundAccumulateSettingBulkChangeInputA005RequestDto appReq = new IfaMutualFundAccumulateSettingBulkChangeInputA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingBulkChangeInputService", "settingChangeConfirmA005",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto>>() {
                }, appReq);

        DataList<IfaMutualFundAccumulateSettingBulkChangeInputA005ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingBulkChangeInputA005ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
