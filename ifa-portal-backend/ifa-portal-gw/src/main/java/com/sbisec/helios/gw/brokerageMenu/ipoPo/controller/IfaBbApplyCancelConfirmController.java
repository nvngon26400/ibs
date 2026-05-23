package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCancelConfirmA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCancelConfirmA001ApiResponse;

/**
 * 画面ID：SUB0204_02-03_2
 * 画面名：BB申込取消確認
 *
 * @author BASE李
 2024/05/14 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_02-03_2", screenNumber = "")
public class IfaBbApplyCancelConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyCancelConfirmApplicationCancellationA001
     * アクションID：A001
     * アクション名：申込取消
     * APIリクエスト：IfaBbApplyCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaBbApplyCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaBbApplyCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaBbApplyCancelConfirmA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyCancelConfirmApplicationCancellationA001")
    public String applicationCancellationA001(@RequestBody IfaBbApplyCancelConfirmA001ApiRequest apiReq) throws Exception {

        IfaBbApplyCancelConfirmA001RequestDto appReq = new IfaBbApplyCancelConfirmA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyCancelConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyCancelConfirmService",
                "applicationCancellationA001", new TypeReference<DataList<IfaBbApplyCancelConfirmA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyCancelConfirmA001ApiResponse> apiRes = new DataList<IfaBbApplyCancelConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

