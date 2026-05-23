package com.sbisec.helios.gw.brokerageMenu.commFee.controller;

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
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaOtherFeeDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaOtherFeeDetailA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaOtherFeeDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaOtherFeeDetailA001ApiResponse;

/**
 * 画面ID：SUB020502-02
 * 画面名：その他報酬詳細
 *
 * @author <author-name>
 2024/06/19 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020502-02", screenNumber = "")
public class IfaOtherFeeDetailController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/brokerageMenu/commFee/ifaOtherFeeDetailInitialDisplayA001
     * アクションID：A001
     * アクション名：初期表示
     * APIリクエスト：IfaOtherFeeDetailA001ApiRequest
     * Apiレスポンス：IfaOtherFeeDetailA001ApiResponse
     * Dtoリクエスト：IfaOtherFeeDetailA001RequestDto
     * Dtoレスポンス：IfaOtherFeeDetailA001ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/commFee/ifaOtherFeeDetailInitialDisplayA001")
    public String initialDisplayA001(@RequestBody IfaOtherFeeDetailA001ApiRequest apiReq) throws Exception {

        IfaOtherFeeDetailA001RequestDto appReq = new IfaOtherFeeDetailA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaOtherFeeDetailA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaOtherFeeDetailService",
                "initialDisplayA001", new TypeReference<DataList<IfaOtherFeeDetailA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaOtherFeeDetailA001ApiResponse> apiRes = new DataList<IfaOtherFeeDetailA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

