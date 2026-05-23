package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;



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
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMutualFundOrderOtherInfoA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMutualFundOrderOtherInfoA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDomesticMutualFundOrderOtherInfoA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDomesticMutualFundOrderOtherInfoA001ApiResponse;

/**
 * 画面ID：SUB020302_0101-03
 * 画面名：コンプラ項目詳細
 *
 * @author BASE 丁
 2024/06/20 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0101-03", screenNumber = "")
public class IfaDomesticMutualFundOrderOtherInfoController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaDomesticMutualFundOrderOtherInfoInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDomesticMutualFundOrderOtherInfoA001ApiRequest
     * Apiレスポンス：IfaDomesticMutualFundOrderOtherInfoA001ApiResponse
     * Dtoリクエスト：IfaDomesticMutualFundOrderOtherInfoA001RequestDto
     * Dtoレスポンス：IfaDomesticMutualFundOrderOtherInfoA001ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaDomesticMutualFundOrderOtherInfoInitializeA001")
    public String initializeA001(@RequestBody IfaDomesticMutualFundOrderOtherInfoA001ApiRequest apiReq) throws Exception {

        IfaDomesticMutualFundOrderOtherInfoA001RequestDto appReq = new IfaDomesticMutualFundOrderOtherInfoA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaDomesticMutualFundOrderOtherInfoA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDomesticMutualFundOrderOtherInfoService",
                "initializeA001", new TypeReference<DataList<IfaDomesticMutualFundOrderOtherInfoA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticMutualFundOrderOtherInfoA001ApiResponse> apiRes = new DataList<IfaDomesticMutualFundOrderOtherInfoA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

