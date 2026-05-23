package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderCancelConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticMutualFundOrderCancelConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import org.apache.commons.beanutils.BeanUtils;


/**
 * 画面ID：SUB0202_0401-04_1
 * 画面名：国内投信注文取消確認
 *
 * @author SCSK
 *
 *     2023/11/24 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0401-04_1", screenNumber = "")
public class IfaDomesticMutualFundOrderCancelConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticMutualFundOrderCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaDomesticMutualFundOrderCancelConfirmA001ApiRequest apiReq)throws Exception {

        IfaDomesticMutualFundOrderCancelConfirmA001RequestDto appReq = new IfaDomesticMutualFundOrderCancelConfirmA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDomesticMutualFundOrderCancelConfirmService",
                "initializeA001", new TypeReference<DataList<IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticMutualFundOrderCancelConfirmA001ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderCancelConfirmA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderCancelConfirmOrderCancellationA002
     * アクションID：A002
     * アクション名：注文取消
     * APIリクエスト：IfaDomesticMutualFundOrderCancelConfirmA002ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderCancelConfirmA002ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderCancelConfirmA002RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundOrderCancelConfirmOrderCancellationA002")
    public String orderCancellationA002(@RequestBody IfaDomesticMutualFundOrderCancelConfirmA002ApiRequest apiReq)throws Exception {

        IfaDomesticMutualFundOrderCancelConfirmA002RequestDto appReq = new IfaDomesticMutualFundOrderCancelConfirmA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDomesticMutualFundOrderCancelConfirmService",
                "orderCancellationA002a", new TypeReference<DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto>>() {
                }, appReq);
        

        if (appRes.getErrorLevel() == ErrorLevel.SUCCESS.getId()) {
            appReq.setIfaOrderNo(appRes.getDataList().get(0).getIfaOrderNumber());
            appReq.setIfaOrderSubNo(appRes.getDataList().get(0).getIfaOrderSubNumber());
            appRes = ApiRequestUtil.invoke("cmpIfaDomesticMutualFundOrderCancelConfirmService",
                    "orderCancellationA002b", new TypeReference<DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto>>() {
                    }, appReq);
        }
        
        // appRes と apiRes のプロパティが異なるときBeanUtils.copyPropertiesを使ってコピーできない
        // レスポンス用のDataList
        DataList<IfaDomesticMutualFundOrderCancelConfirmA002ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderCancelConfirmA002ApiResponse>();
        // DataListの内容をコピーするためのオブジェクト
        IfaDomesticMutualFundOrderCancelConfirmA002ApiResponse apiResData = new IfaDomesticMutualFundOrderCancelConfirmA002ApiResponse();
        List<IfaDomesticMutualFundOrderCancelConfirmA002ApiResponse> apiResDataList = new ArrayList<IfaDomesticMutualFundOrderCancelConfirmA002ApiResponse>();
        // DataListの内容だけをコピー
        if (appRes.getDataList().size() > 0) {
            BeanUtils.copyProperties(apiResData, appRes.getDataList().get(0));
            apiResDataList.add(apiResData);
        }
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        // DataListの内容をコピー
        apiRes.setDataList(apiResDataList);
        
        return jc.toString(apiRes);

    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

