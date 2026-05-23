package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactAcceptDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactAcceptDetailA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactAcceptDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactAcceptDetailA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0202_0106-02
 * 画面名：接触履歴受付詳細
 * 
 * @author 趙韫慧
 * 2025/04/22 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0106-02", screenNumber = "")
public class IfaContactAcceptDetailController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaContactAcceptDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaContactAcceptDetailA001ApiRequest
     * Apiレスポンス：IfaContactAcceptDetailA001ApiResponse
     * Dtoリクエスト：IfaContactAcceptDetailA001RequestDto
     * Dtoレスポンス：IfaContactAcceptDetailA001ResponseDto
     * 
     * @param apiReq APIリクエス
     * @return 接触履歴受付詳細初期化情報
     * @exception Exception 異常
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactAcceptDetailInitializeA001")
    public String initializeA001(@RequestBody IfaContactAcceptDetailA001ApiRequest apiReq) throws Exception {

        IfaContactAcceptDetailA001RequestDto appReq = new IfaContactAcceptDetailA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaContactAcceptDetailA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaContactAcceptDetailService", "initializeA001",
                new TypeReference<DataList<IfaContactAcceptDetailA001ResponseDto>>() {
                }, appReq);

        DataList<IfaContactAcceptDetailA001ApiResponse> apiRes = new DataList<IfaContactAcceptDetailA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
