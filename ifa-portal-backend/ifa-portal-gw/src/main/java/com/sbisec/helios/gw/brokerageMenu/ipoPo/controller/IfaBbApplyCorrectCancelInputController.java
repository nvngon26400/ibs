package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA005ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectCancelInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectCancelInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectCancelInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectCancelInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectCancelInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectCancelInputA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectCancelInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCorrectCancelInputA005ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0204_02-02_1
 * 画面名：BB申込訂正・取消入力
 * @author <author-name>
 *
 * 2024/04/15 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_02-02_1", screenNumber = "")
public class IfaBbApplyCorrectCancelInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyCorrectCancelInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaBbApplyCorrectCancelInputA001ApiRequest
     * Apiレスポンス：IfaBbApplyCorrectCancelInputA001ApiResponse
     * Dtoリクエスト：IfaBbApplyCorrectCancelInputA001RequestDto
     * Dtoレスポンス：IfaBbApplyCorrectCancelInputA001ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyCorrectCancelInputInitializeA001")
    public String initializeA001(@RequestBody IfaBbApplyCorrectCancelInputA001ApiRequest apiReq) throws Exception {

        IfaBbApplyCorrectCancelInputA001RequestDto appReq = new IfaBbApplyCorrectCancelInputA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyCorrectCancelInputA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyCorrectCancelInputService",
                "initializeA001", new TypeReference<DataList<IfaBbApplyCorrectCancelInputA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyCorrectCancelInputA001ApiResponse> apiRes = new DataList<IfaBbApplyCorrectCancelInputA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyCorrectCancelInputCorrectA002
     * アクションID：A002
     * アクション名：訂正
     * APIリクエスト：IfaBbApplyCorrectCancelInputA002ApiRequest
     * Apiレスポンス：IfaBbApplyCorrectCancelInputA002ApiResponse
     * Dtoリクエスト：IfaBbApplyCorrectCancelInputA002RequestDto
     * Dtoレスポンス：IfaBbApplyCorrectCancelInputA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyCorrectCancelInputCorrectA002")
    public String correctA002(@RequestBody IfaBbApplyCorrectCancelInputA002ApiRequest apiReq) throws Exception {

        IfaBbApplyCorrectCancelInputA002RequestDto appReq = new IfaBbApplyCorrectCancelInputA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyCorrectCancelInputA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyCorrectCancelInputService",
                "correctA002", new TypeReference<DataList<IfaBbApplyCorrectCancelInputA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyCorrectCancelInputA002ApiResponse> apiRes = new DataList<IfaBbApplyCorrectCancelInputA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyCorrectCancelInputCancelA003
     * アクションID：A003
     * アクション名：取消
     * APIリクエスト：IfaBbApplyCorrectCancelInputA003ApiRequest
     * Apiレスポンス：IfaBbApplyCorrectCancelInputA003ApiResponse
     * Dtoリクエスト：IfaBbApplyCorrectCancelInputA003RequestDto
     * Dtoレスポンス：IfaBbApplyCorrectCancelInputA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyCorrectCancelInputCancelA003")
    public String cancelA003(@RequestBody IfaBbApplyCorrectCancelInputA003ApiRequest apiReq) throws Exception {

        IfaBbApplyCorrectCancelInputA003RequestDto appReq = new IfaBbApplyCorrectCancelInputA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyCorrectCancelInputA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyCorrectCancelInputService",
                "cancelA003", new TypeReference<DataList<IfaBbApplyCorrectCancelInputA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyCorrectCancelInputA003ApiResponse> apiRes = new DataList<IfaBbApplyCorrectCancelInputA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaBbApplyCorrectCancelInputBackInitializeA005
     * アクションID：A005
     * アクション名：戻り初期化
     * APIリクエスト：IfaBbApplyCorrectCancelInputA005ApiRequest
     * Apiレスポンス：IfaBbApplyCorrectCancelInputA005ApiResponse
     * Dtoリクエスト：IfaBbApplyCorrectCancelInputA005RequestDto
     * Dtoレスポンス：IfaBbApplyCorrectCancelInputA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyCorrectCancelInputBackInitializeA005")
    public String backInitializeA005(@RequestBody IfaBbApplyCorrectCancelInputA005ApiRequest apiReq) throws Exception {

        IfaBbApplyCorrectCancelInputA005RequestDto appReq = new IfaBbApplyCorrectCancelInputA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBbApplyCorrectCancelInputA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBbApplyCorrectCancelInputService",
                "backInitializeA005", new TypeReference<DataList<IfaBbApplyCorrectCancelInputA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBbApplyCorrectCancelInputA005ApiResponse> apiRes = new DataList<IfaBbApplyCorrectCancelInputA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }


    @Override
    protected String getFirstViewName() {
        return null;
    }
}

