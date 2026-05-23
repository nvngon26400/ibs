package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0202_0106-01
 * 画面名：接触履歴
 * 
 * @author 趙韫慧
 * 2025/04/01 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0106-01", screenNumber = "")
public class IfaContactController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaContactInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaContactA001ApiRequest
     * Apiレスポンス：IfaContactA001ApiResponse
     * Dtoリクエスト：IfaContactA001RequestDto
     * Dtoレスポンス：IfaContactA001ResponseDto
     * 
     * @param apiReq APIリクエス
     * @return 接触履歴初期化情報
     * @exception Exception 異常
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactInitializeA001")
    public String initializeA001(@RequestBody IfaContactA001ApiRequest apiReq) throws Exception {

        IfaContactA001RequestDto appReq = new IfaContactA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaContactA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaContactService", "initializeA001",
                new TypeReference<DataList<IfaContactA001ResponseDto>>() {
                }, appReq);

        DataList<IfaContactA001ApiResponse> apiRes = new DataList<IfaContactA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaContactBigClassChangeA002
     * アクションID：A002
     * アクション名：大分類変更
     * APIリクエスト：IfaContactA002ApiRequest
     * Apiレスポンス：IfaContactA002ApiResponse
     * Dtoリクエスト：IfaContactA002RequestDto
     * Dtoレスポンス：IfaContactA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes 接触履歴情報(大分類別)
     * @throws Exception 異常
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactBigClassChangeA002")
    public String bigClassChangeA002(@RequestBody IfaContactA002ApiRequest apiReq) throws Exception {

        IfaContactA002RequestDto appReq = new IfaContactA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaContactA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaContactService", "bigClassChangeA002",
                new TypeReference<DataList<IfaContactA002ResponseDto>>() {
                }, appReq);

        DataList<IfaContactA002ApiResponse> apiRes = new DataList<IfaContactA002ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
