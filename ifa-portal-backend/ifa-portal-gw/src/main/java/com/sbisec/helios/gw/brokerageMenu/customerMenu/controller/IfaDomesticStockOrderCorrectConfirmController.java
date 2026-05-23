package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001bDtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCorrectConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDomesticStockOrderCorrectConfirmA001ApiResponse;

/**
 * 画面ID：SUB0202_0208-03_2
 * 画面名：国内株式注文訂正確認
 * 
 * @author SCSK 矢口
 *
 *         2024/04/16 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0208-03_2", screenNumber = "")
public class IfaDomesticStockOrderCorrectConfirmController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaDomesticStockOrderCorrectConfirmOrderPlacementA001
     * アクションID：A001
     * アクション名：注文発注
     * APIリクエスト：IfaDomesticStockOrderCorrectConfirmA001ApiRequest
     * Apiレスポンス：IfaDomesticStockOrderCorrectConfirmA001ApiResponse
     * Dtoリクエスト：IfaDomesticStockOrderCorrectConfirmA001DtoRequest
     * Dtoレスポンス：IfaDomesticStockOrderCorrectConfirmA001DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaDomesticStockOrderCorrectConfirmOrderPlacementA001")
    public String orderPlacementA001(@RequestBody IfaDomesticStockOrderCorrectConfirmA001ApiRequest apiReq)
            throws Exception {

        // A001a
        IfaDomesticStockOrderCorrectConfirmA001aDtoRequest appReqA = new IfaDomesticStockOrderCorrectConfirmA001aDtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqA, apiReq);

        DataList<IfaDomesticStockOrderCorrectConfirmA001aDtoResponse> appResA = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderCorrectConfirmService",
                "orderPlacementA001a",
                new TypeReference<DataList<IfaDomesticStockOrderCorrectConfirmA001aDtoResponse>>() {
                },
                appReqA);

        DataList<IfaDomesticStockOrderCorrectConfirmA001ApiResponse> apiRes = new DataList<IfaDomesticStockOrderCorrectConfirmA001ApiResponse>();
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            // エラーの場合は返却
            BeanUtils.copyProperties(apiRes, appResA);
            return jc.toString(apiRes);
        }

        // A001b
        IfaDomesticStockOrderCorrectConfirmA001bDtoRequest appReqB = new IfaDomesticStockOrderCorrectConfirmA001bDtoRequest();

        // IFA注文番号
        appReqB.setIfaOrderNo(appResA.getDataList().get(0).getIfaOrderNo());
        // IFA注文サブ番号
        appReqB.setIfaOrderSubNo(appResA.getDataList().get(0).getIfaOrderSubNo());

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqB, apiReq);

        DataList<IfaDomesticStockOrderCorrectConfirmA001bDtoResponse> appResB = ApiRequestUtil.invoke(
                "cmpIfaDomesticStockOrderCorrectConfirmService",
                "orderPlacementA001b",
                new TypeReference<DataList<IfaDomesticStockOrderCorrectConfirmA001bDtoResponse>>() {
                },
                appReqB);

        BeanUtils.copyProperties(apiRes, appResB);
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
