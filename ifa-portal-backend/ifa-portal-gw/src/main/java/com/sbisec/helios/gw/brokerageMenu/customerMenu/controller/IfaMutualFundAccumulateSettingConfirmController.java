package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingConfirmA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingConfirmA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0403-02_2
 * 画面名：投信積立設定確認
 *
 * @author nicksen.li
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0403-02_2", screenNumber = "")
public class IfaMutualFundAccumulateSettingConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingConfirmA001
     * アクションID：A001
     * アクション名：設定登録
     * APIリクエスト：IfaMutualFundAccumulateSettingConfirmA001ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingConfirmA001ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingConfirmA001RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingConfirmA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMutualFundAccumulateSettingConfirmA001")
    public String confirmA001(@RequestBody IfaMutualFundAccumulateSettingConfirmA001ApiRequest apiReq)
            throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaMutualFundAccumulateSettingConfirmA001RequestDto appReq = new IfaMutualFundAccumulateSettingConfirmA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMutualFundAccumulateSettingConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingConfirmService", "confirmA001",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingConfirmA001ResponseDto>>() {
                }, appReq);

        DataList<IfaMutualFundAccumulateSettingConfirmA001ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingConfirmA001ApiResponse>();

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
