package com.sbisec.helios.gw.brokerageMenu.controller;

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
import com.sbisec.helios.ap.dto.IfaIfaCurrencyDealtListA001ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.form.IfaIfaCurrencyDealtListA001ApiRequest;
import com.sbisec.helios.gw.form.IfaIfaCurrencyDealtListA001ApiResponse;

/**
 * 画面ID：SUB0202_0503-01
 * 画面名：【IFA】取扱通貨一覧
 *　2023/11/09 新規作成
 *
 * @author 松尾
 * 
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0503-01", screenNumber = "")
public class IfaIfaCurrencyDealtListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     *
    
     * アクセス：/brokerageMenu/customerMenu/ifaIfaCurrencyDealtListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaIfaCurrencyDealtListA001ApiRequest
     * Apiレスポンス：IfaIfaCurrencyDealtListA001ApiResponse
     * Dtoリクエスト：IfaIfaCurrencyDealtListA001DtoRequest
     * Dtoレスポンス：IfaIfaCurrencyDealtListA001DtoResponse
     *
     * @param apiReq リクエストAPI
     * @return apiRes レスポンスAPI
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaIfaCurrencyDealtListInitializeA001")
    public String initializeA001(@RequestBody IfaIfaCurrencyDealtListA001ApiRequest apiReq) throws Exception {
        
        IfaIfaCurrencyDealtListA001ApiRequest appReq = new IfaIfaCurrencyDealtListA001ApiRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaIfaCurrencyDealtListA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaIfaCurrencyDealtListService", "initializeA001",
                new TypeReference<DataList<IfaIfaCurrencyDealtListA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaIfaCurrencyDealtListA001ApiResponse> apiRes = new DataList<IfaIfaCurrencyDealtListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
