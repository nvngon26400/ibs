package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyConfirmA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyConfirmA001ApiResponse;

/**
 * 画面ID：SUB0204_01-02_2
 * 画面名：BB申込確認
 *
 * @author BASE李
 * 
 2024/02/29 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_01-02_1", screenNumber = "", ignoreCheck = true)
public class IfaBbApplyConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyConfirmApplicationRegistrationA001
     * アクションID：A001
     * アクション名：申込登録
     * APIリクエスト：IfaBbApplyConfirmA001ApiRequest
     * Apiレスポンス：IfaBbApplyConfirmA001ApiResponse
     * Dtoリクエスト：IfaBbApplyConfirmA001RequestDto
     * Dtoレスポンス：IfaBbApplyConfirmA001ResponseDto
     *
     * @param apiReq IfaBbApplyConfirmA001ApiRequest
     * @return apiRes
     * @exception Exception システムエラー
     */
    @RequestMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyConfirmApplicationRegistrationA001", method = { RequestMethod.POST })
    public String applicationRegistrationA001(@RequestBody IfaBbApplyConfirmA001ApiRequest apiReq)throws Exception {

        IfaBbApplyConfirmA001RequestDto appReq = new IfaBbApplyConfirmA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyConfirmService",
                "applicationRegistrationA001", new TypeReference<DataList<IfaBbApplyConfirmA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyConfirmA001ApiResponse> apiRes = new DataList<IfaBbApplyConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
}

