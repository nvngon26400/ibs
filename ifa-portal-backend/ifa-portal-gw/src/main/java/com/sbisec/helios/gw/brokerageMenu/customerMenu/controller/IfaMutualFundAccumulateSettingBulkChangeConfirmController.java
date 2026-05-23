package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBulkChangeConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingBulkChangeConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0403-05_2
 * 画面名：投信積立設定一括変更確認
 *
 * @author nicksen.li
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0403-05_2", screenNumber = "")
public class IfaMutualFundAccumulateSettingBulkChangeConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBulkChangeRegisterA001
     * アクションID：A001
     * アクション名：設定一括変更登録
     * APIリクエスト：IfaMutualFundAccumulateSettingBulkChangeConfirmA002ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingBulkChangeConfirmA002ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingBulkChangeConfirmA002RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingBulkChangeConfirmA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 設定変更登録の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingBulkChangeRegisterA001")
    public String settingBulkChangeRegisterA001(@RequestBody IfaMutualFundAccumulateSettingBulkChangeConfirmA001ApiRequest apiReq)
            throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestDto appReq = new IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingBulkChangeConfirmService", "settingBulkChangeRegisterA001",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ResponseDto>>() {
                }, appReq);

        DataList<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingBulkChangeConfirmA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        if (appRes.getDataList().size() != 0) {
            appRes.getDataList().get(0).setRequestContents(appReq);
        }

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }

}
