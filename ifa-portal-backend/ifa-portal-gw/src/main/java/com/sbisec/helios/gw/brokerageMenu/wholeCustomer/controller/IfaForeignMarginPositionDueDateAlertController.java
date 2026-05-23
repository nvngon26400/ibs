package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;


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
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginPositionDueDateAlertA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginPositionDueDateAlertA002ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaForeignMarginPositionDueDateAlertA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaForeignMarginPositionDueDateAlertA002ApiResponse;

/**
 * 画面ID：SUB020301_02-02
 * 画面名：米株信用建玉期日アラート一覧
 *
 * @author <author-name>
 2024/06/21 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020301_02-02", screenNumber = "")
public class IfaForeignMarginPositionDueDateAlertController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaForeignMarginPositionDueDateAlertDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaForeignMarginPositionDueDateAlertA002ApiRequest
     * Apiレスポンス：IfaForeignMarginPositionDueDateAlertA002ApiResponse
     * Dtoリクエスト：IfaForeignMarginPositionDueDateAlertA002RequestDto
     * Dtoレスポンス：IfaForeignMarginPositionDueDateAlertA002ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaForeignMarginPositionDueDateAlertDisplayA002")
    public String displayA002(@RequestBody IfaForeignMarginPositionDueDateAlertA002ApiRequest apiReq) throws Exception {

        IfaForeignMarginPositionDueDateAlertA002RequestDto appReq = new IfaForeignMarginPositionDueDateAlertA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaForeignMarginPositionDueDateAlertA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaForeignMarginPositionDueDateAlertService",
                "displayA002", new TypeReference<DataList<IfaForeignMarginPositionDueDateAlertA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginPositionDueDateAlertA002ApiResponse> apiRes = new DataList<IfaForeignMarginPositionDueDateAlertA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

