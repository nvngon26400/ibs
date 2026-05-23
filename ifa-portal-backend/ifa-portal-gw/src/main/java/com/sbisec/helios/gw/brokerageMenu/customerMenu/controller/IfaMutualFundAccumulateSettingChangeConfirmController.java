package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingChangeConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMutualFundAccumulateSettingChangeConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0403-03_2
 * 画面名：投信積立設定変更確認
 *
 * @author nicksen.li
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0403-03_2", screenNumber = "")
public class IfaMutualFundAccumulateSettingChangeConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaMutualFundAccumulateSettingChangeRegisterA002
     * アクションID：A002
     * アクション名：設定変更登録
     * APIリクエスト：IfaMutualFundAccumulateSettingChangeConfirmA002ApiRequest
     * Apiレスポンス：IfaMutualFundAccumulateSettingChangeConfirmA002ApiResponse
     * Dtoリクエスト：IfaMutualFundAccumulateSettingChangeConfirmA002RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 設定変更登録の際、例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/IfaMutualFundAccumulateSettingChangeRegisterA002")
    public String settingChangeRegisterA002(@RequestBody IfaMutualFundAccumulateSettingChangeConfirmA002ApiRequest apiReq)
            throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        IfaMutualFundAccumulateSettingChangeConfirmA002RequestDto appReq = new IfaMutualFundAccumulateSettingChangeConfirmA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundAccumulateSettingChangeConfirmService", "settingChangeRegisterA002",
                new TypeReference<DataList<IfaMutualFundAccumulateSettingChangeConfirmA002ResponseDto>>() {
                }, appReq);

        DataList<IfaMutualFundAccumulateSettingChangeConfirmA002ApiResponse> apiRes = new DataList<IfaMutualFundAccumulateSettingChangeConfirmA002ApiResponse>();

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
