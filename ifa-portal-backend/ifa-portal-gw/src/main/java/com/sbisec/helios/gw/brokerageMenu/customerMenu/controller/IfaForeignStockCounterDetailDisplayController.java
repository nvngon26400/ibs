package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterDetailDisplayA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterDetailDisplayA001DtoResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterDetailDisplayA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignStockCounterDetailDisplayA001ApiResponse;

/**
 * 画面ID：SUB0202_0104-03
 * 画面名：外国株式店頭詳細表示

 * @author 大崎
　　　2024/03/19 新規作成
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0104-03", screenNumber = "")
public class IfaForeignStockCounterDetailDisplayController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignStockCounterDetailDisplayInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignStockCounterDetailDisplayA001ApiRequest
     * Apiレスポンス：IfaForeignStockCounterDetailDisplayA001ApiResponse
     * Dtoリクエスト：IfaForeignStockCounterDetailDisplayA001DtoRequest
     * Dtoレスポンス：IfaForeignStockCounterDetailDisplayA001DtoResponse

     * @param apiReq リクエスト
     * @return appRes
     * @exception Exception SQLExceptionなど 
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignStockCounterDetailDisplayInitializeA001")
    public String initializeA001(@RequestBody IfaForeignStockCounterDetailDisplayA001ApiRequest apiReq)throws Exception {

        IfaForeignStockCounterDetailDisplayA001DtoRequest appReq = new IfaForeignStockCounterDetailDisplayA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaForeignStockCounterDetailDisplayA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaForeignStockCounterDetailDisplayService",
                "initializeA001", new TypeReference<DataList<IfaForeignStockCounterDetailDisplayA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaForeignStockCounterDetailDisplayA001ApiResponse> apiRes = new DataList<IfaForeignStockCounterDetailDisplayA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

