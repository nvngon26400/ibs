package com.sbisec.helios.gw.brokerageMenu.customerList.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaImprintInquiryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaImprintInquiryA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaImprintInquiryA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerList.form.IfaImprintInquiryA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0201_01-02
 * 画面名：印影照会
 * @author <author-name>
 *
 * 2024/03/22 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0201_01-02", screenNumber = "")
public class IfaImprintInquiryController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * 
     * アクセス：/brokerageMenu/customerList/ifaImprintInquiryInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaImprintInquiryA001ApiRequest
     * Apiレスポンス：IfaImprintInquiryA001ApiResponse
     * Dtoリクエスト：IfaImprintInquiryA001DtoRequest
     * Dtoレスポンス：IfaImprintInquiryA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerList/ifaImprintInquiryInitializeA001")
    public String initializeA001(@RequestBody IfaImprintInquiryA001ApiRequest apiReq) throws Exception {

        IfaImprintInquiryA001RequestDto appReq = new IfaImprintInquiryA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaImprintInquiryA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaImprintInquiryService",
                "initializeA001", new TypeReference<DataList<IfaImprintInquiryA001ResponseDto>>() {
                }, appReq);

        DataList<IfaImprintInquiryA001ApiResponse> apiRes = new DataList<IfaImprintInquiryA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {

        return null;
    }
}
