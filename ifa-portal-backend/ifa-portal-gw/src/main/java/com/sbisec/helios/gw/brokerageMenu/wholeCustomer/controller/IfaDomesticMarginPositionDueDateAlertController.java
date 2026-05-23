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
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMarginPositionDueDateAlertA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMarginPositionDueDateAlertA002ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDomesticMarginPositionDueDateAlertA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDomesticMarginPositionDueDateAlertA002ApiResponse;

/**
 * 画面ID：SUB020301_02-01
 * 画面名：国内信用建玉期日アラート一覧
 *
 * @author BASE 李
 2024/06/19 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020301_02-01", screenNumber = "")
public class IfaDomesticMarginPositionDueDateAlertController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaDomesticMarginPositionDueDateAlertDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaDomesticMarginPositionDueDateAlertA002ApiRequest
     * Apiレスポンス：IfaDomesticMarginPositionDueDateAlertA002ApiResponse
     * Dtoリクエスト：IfaDomesticMarginPositionDueDateAlertA002RequestDto
     * Dtoレスポンス：IfaDomesticMarginPositionDueDateAlertA002ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaDomesticMarginPositionDueDateAlertDisplayA002")
    public String displayA002(@RequestBody IfaDomesticMarginPositionDueDateAlertA002ApiRequest apiReq) throws Exception {

        IfaDomesticMarginPositionDueDateAlertA002RequestDto appReq = new IfaDomesticMarginPositionDueDateAlertA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaDomesticMarginPositionDueDateAlertA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDomesticMarginPositionDueDateAlertService",
                "displayA002", new TypeReference<DataList<IfaDomesticMarginPositionDueDateAlertA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDomesticMarginPositionDueDateAlertA002ApiResponse> apiRes = new DataList<IfaDomesticMarginPositionDueDateAlertA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

