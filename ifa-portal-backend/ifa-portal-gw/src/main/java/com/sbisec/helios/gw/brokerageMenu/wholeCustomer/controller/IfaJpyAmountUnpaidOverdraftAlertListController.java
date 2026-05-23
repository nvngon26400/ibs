package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA004ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaJpyAmountUnpaidOverdraftAlertListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaJpyAmountUnpaidOverdraftAlertListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaJpyAmountUnpaidOverdraftAlertListA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaJpyAmountUnpaidOverdraftAlertListA004ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020301_01-01
 * 画面名：円貨未入金・赤残アラート一覧
 *
 * @author BASE李
 2024/05/23 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020301_01-01", screenNumber = "")
public class IfaJpyAmountUnpaidOverdraftAlertListController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaJpyAmountUnpaidOverdraftAlertListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaJpyAmountUnpaidOverdraftAlertListA002ApiRequest
     * Apiレスポンス：IfaJpyAmountUnpaidOverdraftAlertListA002ApiResponse
     * Dtoリクエスト：IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto
     * Dtoレスポンス：IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaJpyAmountUnpaidOverdraftAlertListDisplayA002")
    public String displayA002(@RequestBody IfaJpyAmountUnpaidOverdraftAlertListA002ApiRequest apiReq) throws Exception {

        IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto appReq = new IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaJpyAmountUnpaidOverdraftAlertListService",
                "displayA002", new TypeReference<DataList<IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaJpyAmountUnpaidOverdraftAlertListA002ApiResponse> apiRes = new DataList<IfaJpyAmountUnpaidOverdraftAlertListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaJpyAmountUnpaidOverdraftAlertListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaJpyAmountUnpaidOverdraftAlertListA002ApiRequest
     * Apiレスポンス：IfaJpyAmountUnpaidOverdraftAlertListA002ApiResponse
     * Dtoリクエスト：IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto
     * Dtoレスポンス：IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaJpyAmountUnpaidOverdraftAlertListTransitionA004")
    public String transitionA004(@RequestBody IfaJpyAmountUnpaidOverdraftAlertListA004ApiRequest apiReq) throws Exception {

        IfaJpyAmountUnpaidOverdraftAlertListA004RequestDto appReq = new IfaJpyAmountUnpaidOverdraftAlertListA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaJpyAmountUnpaidOverdraftAlertListA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaJpyAmountUnpaidOverdraftAlertListService",
                "transitionA004", new TypeReference<DataList<IfaJpyAmountUnpaidOverdraftAlertListA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaJpyAmountUnpaidOverdraftAlertListA004ApiResponse> apiRes = new DataList<>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

