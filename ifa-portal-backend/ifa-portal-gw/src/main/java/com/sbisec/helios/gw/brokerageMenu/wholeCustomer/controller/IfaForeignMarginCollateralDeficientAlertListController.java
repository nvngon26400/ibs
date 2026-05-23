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
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginCollateralDeficientAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginCollateralDeficientAlertListA002ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaForeignMarginCollateralDeficientAlertListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaForeignMarginCollateralDeficientAlertListA002ApiResponse;

/**
 * 画面ID：SUB020301_01-04
 * 画面名：米株信用担保不足アラート一覧
 *
 * @author <author-name>
 2024/06/17 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020301_01-04", screenNumber = "")
public class IfaForeignMarginCollateralDeficientAlertListController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaForeignMarginCollateralDeficientAlertListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaForeignMarginCollateralDeficientAlertListA002ApiRequest
     * Apiレスポンス：IfaForeignMarginCollateralDeficientAlertListA002ApiResponse
     * Dtoリクエスト：IfaForeignMarginCollateralDeficientAlertListA002RequestDto
     * Dtoレスポンス：IfaForeignMarginCollateralDeficientAlertListA002ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaForeignMarginCollateralDeficientAlertListDisplayA002")
    public String displayA002(@RequestBody IfaForeignMarginCollateralDeficientAlertListA002ApiRequest apiReq) throws Exception {

        IfaForeignMarginCollateralDeficientAlertListA002RequestDto appReq = new IfaForeignMarginCollateralDeficientAlertListA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaForeignMarginCollateralDeficientAlertListA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaForeignMarginCollateralDeficientAlertListService",
                "displayA002", new TypeReference<DataList<IfaForeignMarginCollateralDeficientAlertListA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignMarginCollateralDeficientAlertListA002ApiResponse> apiRes = new DataList<IfaForeignMarginCollateralDeficientAlertListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

