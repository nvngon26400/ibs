package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;

import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA001ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA002ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA002ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA003ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA003ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA004ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA004ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA005ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA005ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA006ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA006ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA006ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA007ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA007ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputConfirmA007ApiResponse;

/**
 * 画面ID：SUB0204_02-04_2
 * 画面名：募集入力確認
 * 2024/04/15 新規作成
 *
 * @author SCSK 濱田
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_02-04_2", screenNumber = "")
public class IfaSubscriptInputConfirmController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    


    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderPlacementBrokerA001
     * アクションID：A001
     * アクション名：注文（仲介業者新規）
     * APIリクエスト：IfaSubscriptInputConfirmA001ApiRequest
     * Apiレスポンス：IfaSubscriptInputConfirmA001ApiResponse
     * Dtoリクエスト：IfaSubscriptInputConfirmA001DtoRequest
     * Dtoレスポンス：IfaSubscriptInputConfirmA001DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception Exception <description>
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderPlacementBrokerA001")
    public String orderPlacementBrokerA001(@RequestBody IfaSubscriptInputConfirmA001ApiRequest apiReq)throws Exception
    {

        // コントローラへのリクエストをｒサービスへのリクエストへコピー
        IfaSubscriptInputConfirmA001RequestDto appReq = new IfaSubscriptInputConfirmA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSubscriptInputConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSubscriptInputConfirmService",
                "orderPlacementBrokerA001",
                new TypeReference<DataList<IfaSubscriptInputConfirmA001ResponseDto>>() {},
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptInputConfirmA001ApiResponse> apiRes = new DataList<IfaSubscriptInputConfirmA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderPlacementManagerA002
     * アクションID：A002
     * アクション名：更新（管理者新規）
     * APIリクエスト：IfaSubscriptInputConfirmA002ApiRequest
     * Apiレスポンス：IfaSubscriptInputConfirmA002ApiResponse
     * Dtoリクエスト：IfaSubscriptInputConfirmA002DtoRequest
     * Dtoレスポンス：IfaSubscriptInputConfirmA002DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception Exception <description>
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderPlacementManagerA002")
    public String orderPlacementManagerA002(@RequestBody IfaSubscriptInputConfirmA002ApiRequest apiReq)throws Exception
    {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptInputConfirmA002RequestDto appReq = new IfaSubscriptInputConfirmA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSubscriptInputConfirmA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSubscriptInputConfirmService",
                "orderPlacementManagerA002",
                new TypeReference<DataList<IfaSubscriptInputConfirmA002ResponseDto>>() {},
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptInputConfirmA002ApiResponse> apiRes = new DataList<IfaSubscriptInputConfirmA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderUpdateMnagerA003
     * アクションID：A003
     * アクション名：更新（管理者更新）
     * APIリクエスト：IfaSubscriptInputConfirmA003ApiRequest
     * Apiレスポンス：IfaSubscriptInputConfirmA003ApiResponse
     * Dtoリクエスト：IfaSubscriptInputConfirmA003DtoRequest
     * Dtoレスポンス：IfaSubscriptInputConfirmA003DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception Exception <description>
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderUpdateMnagerA003")
    public String orderUpdateMnagerA003(@RequestBody IfaSubscriptInputConfirmA003ApiRequest apiReq)throws Exception
    {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptInputConfirmA003RequestDto appReq = new IfaSubscriptInputConfirmA003RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSubscriptInputConfirmA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSubscriptInputConfirmService",
                "orderUpdateMnagerA003",
                new TypeReference<DataList<IfaSubscriptInputConfirmA003ResponseDto>>() {},
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptInputConfirmA003ApiResponse> apiRes = new DataList<IfaSubscriptInputConfirmA003ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderCorrectionManagerA004
     * アクションID：A004
     * アクション名：更新（管理者訂正）
     * APIリクエスト：IfaSubscriptInputConfirmA004ApiRequest
     * Apiレスポンス：IfaSubscriptInputConfirmA004ApiResponse
     * Dtoリクエスト：IfaSubscriptInputConfirmA004DtoRequest
     * Dtoレスポンス：IfaSubscriptInputConfirmA004DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception Exception <description>
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderCorrectionManagerA004")
    public String orderCorrectionManagerA004(@RequestBody IfaSubscriptInputConfirmA004ApiRequest apiReq)throws Exception
    {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptInputConfirmA004RequestDto appReq = new IfaSubscriptInputConfirmA004RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSubscriptInputConfirmA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSubscriptInputConfirmService",
                "orderCorrectionManagerA004",
                new TypeReference<DataList<IfaSubscriptInputConfirmA004ResponseDto>>() {},
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptInputConfirmA004ApiResponse> apiRes = new DataList<IfaSubscriptInputConfirmA004ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderUpdateBrokerA005
     * アクションID：A005
     * アクション名：訂正（仲介業者更新）
     * APIリクエスト：IfaSubscriptInputConfirmA005ApiRequest
     * Apiレスポンス：IfaSubscriptInputConfirmA005ApiResponse
     * Dtoリクエスト：IfaSubscriptInputConfirmA005DtoRequest
     * Dtoレスポンス：IfaSubscriptInputConfirmA005DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception Exception <description>
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderUpdateBrokerA005")
    public String orderCorrectionManagerA005(@RequestBody IfaSubscriptInputConfirmA005ApiRequest apiReq)throws Exception
    {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptInputConfirmA005RequestDto appReq = new IfaSubscriptInputConfirmA005RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSubscriptInputConfirmA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSubscriptInputConfirmService",
                "orderUpdateBrokerA005",
                new TypeReference<DataList<IfaSubscriptInputConfirmA005ResponseDto>>() {},
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptInputConfirmA005ApiResponse> apiRes = new DataList<IfaSubscriptInputConfirmA005ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderCorrectionBrokerA006
     * アクションID：A006
     * アクション名：訂正（仲介業者訂正）
     * APIリクエスト：IfaSubscriptInputConfirmA006ApiRequest
     * Apiレスポンス：IfaSubscriptInputConfirmA006ApiResponse
     * Dtoリクエスト：IfaSubscriptInputConfirmA006DtoRequest
     * Dtoレスポンス：IfaSubscriptInputConfirmA006DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception Exception <description>
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderCorrectionBrokerA006")
    public String orderCorrectionManagerA006(@RequestBody IfaSubscriptInputConfirmA006ApiRequest apiReq)throws Exception
    {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptInputConfirmA006RequestDto appReq = new IfaSubscriptInputConfirmA006RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaSubscriptInputConfirmA006ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSubscriptInputConfirmService",
                "orderCorrectionBrokerA006",
                new TypeReference<DataList<IfaSubscriptInputConfirmA006ResponseDto>>() {},
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptInputConfirmA006ApiResponse> apiRes = new DataList<IfaSubscriptInputConfirmA006ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // 画面にレスポンスを返却
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderCancellationA007
     * アクションID：A007
     * アクション名：取消
     * APIリクエスト：IfaSubscriptInputConfirmA007ApiRequest
     * Apiレスポンス：IfaSubscriptInputConfirmA007ApiResponse
     * Dtoリクエスト：IfaSubscriptInputConfirmA007DtoRequest
     * Dtoレスポンス：IfaSubscriptInputConfirmA007DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception Exception <description>
     */
    @PostMapping(value="/brokerageMenu/ipoPo/ifaSubscriptInputConfirmOrderCancellationA007")
    public String orderCancellationA007(@RequestBody IfaSubscriptInputConfirmA007ApiRequest apiReq)throws Exception
    {

        IfaSubscriptInputConfirmA007RequestDto appReq = new IfaSubscriptInputConfirmA007RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSubscriptInputConfirmA007ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSubscriptInputConfirmService",
                "orderCancellationA007",
                new TypeReference<DataList<IfaSubscriptInputConfirmA007ResponseDto>>() {},
                appReq
        );

        DataList<IfaSubscriptInputConfirmA007ApiResponse> apiRes = new DataList<IfaSubscriptInputConfirmA007ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

