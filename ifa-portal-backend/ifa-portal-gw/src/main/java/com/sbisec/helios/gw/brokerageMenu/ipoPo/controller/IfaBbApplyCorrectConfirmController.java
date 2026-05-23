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
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectConfirmA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectConfirmA001ApiResponse;

/**
 * 画面ID：SUB0204_02-02_2
 * 画面名：BB申込訂正確認
 *
 * @author <author-name>
 2024/04/23 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_02-02_2", screenNumber = "")
public class IfaBbApplyCorrectConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyCorrectConfirmApplicationCorrectionA001
     * アクションID：A001
     * アクション名：申込訂正
     * APIリクエスト：IfaBbApplyCorrectConfirmA001ApiRequest
     * Apiレスポンス：IfaBbApplyCorrectConfirmA001ApiResponse
     * Dtoリクエスト：IfaBbApplyCorrectConfirmA001RequestDto
     * Dtoレスポンス：IfaBbApplyCorrectConfirmA001ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyCorrectConfirmApplicationCorrectionA001")
    public String applicationCorrectionA001(@RequestBody IfaBbApplyCorrectConfirmA001ApiRequest apiReq) throws Exception {

        IfaBbApplyCorrectConfirmA001RequestDto appReq = new IfaBbApplyCorrectConfirmA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyCorrectConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyCorrectConfirmService",
                "applicationCorrectionA001", new TypeReference<DataList<IfaBbApplyCorrectConfirmA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyCorrectConfirmA001ApiResponse> apiRes = new DataList<IfaBbApplyCorrectConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

