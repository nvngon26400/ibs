package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSellUnableDetailA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSellUnableDetailA001DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSellUnableDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSellUnableDetailA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0202_010201-03
 * 画面名：売却不可明細
 * @author <author-name>
 *
 * 2024/04/05 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010201-03", screenNumber = "")
public class IfaSellUnableDetailController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaSellUnableDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSellUnableDetailA001ApiRequest
     * Apiレスポンス：IfaSellUnableDetailA001ApiResponse
     * Dtoリクエスト：IfaSellUnableDetailA001DtoRequest
     * Dtoレスポンス：IfaSellUnableDetailA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping(value="/brokerageMenu/customerMenu/ifaSellUnableDetailInitializeA001")
    public String initializeA001(@RequestBody IfaSellUnableDetailA001ApiRequest apiReq)throws Exception
    {

        IfaSellUnableDetailA001DtoRequest appReq = new IfaSellUnableDetailA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSellUnableDetailA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaSellUnableDetailService",
                "initializeA001", new TypeReference<DataList<IfaSellUnableDetailA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaSellUnableDetailA001ApiResponse> apiRes = new DataList<IfaSellUnableDetailA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}