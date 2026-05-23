package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

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
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA001ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDistributionReceiveMethodChangeInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDistributionReceiveMethodChangeInputA001ApiResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeInputA002ResponseDto;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDistributionReceiveMethodChangeInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaDistributionReceiveMethodChangeInputA002ApiResponse;

/**
 * 画面ID：SUB0202_010201-02_1
 * 画面名：分配金受取方法変更入力
 * 2023/11/28 新規作成
 *
 * @author SCSK 江口
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010201-02_1", screenNumber = "")
public class IfaDistributionReceiveMethodChangeInputController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDistributionReceiveMethodChangeInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaDistributionReceiveMethodChangeInputA001ApiRequest
     * Apiレスポンス：IfaDistributionReceiveMethodChangeInputA001ApiResponse
     * Dtoリクエスト：IfaDistributionReceiveMethodChangeInputA001RequestDto
     * Dtoレスポンス：IfaDistributionReceiveMethodChangeInputA001ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return 画面の初期化に必要な情報。
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDistributionReceiveMethodChangeInputInitializeA001")
    public String initializeA001(@RequestBody IfaDistributionReceiveMethodChangeInputA001ApiRequest apiReq) throws Exception {

        // 顧客共通情報をリクエストスコープにセットする
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaDistributionReceiveMethodChangeInputA001RequestDto appReq = new IfaDistributionReceiveMethodChangeInputA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaDistributionReceiveMethodChangeInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDistributionReceiveMethodChangeInputService",
                "initializeA001",
                new TypeReference<DataList<IfaDistributionReceiveMethodChangeInputA001ResponseDto>>() { },
                appReq
        );
        
        // サービスからのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaDistributionReceiveMethodChangeInputA001ApiResponse> apiRes
                = new DataList<IfaDistributionReceiveMethodChangeInputA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaDistributionReceiveMethodChangeInputRegisterChangeA002
     * アクションID：A002
     * アクション名：登録・変更
     * APIリクエスト：IfaDistributionReceiveMethodChangeInputA002ApiRequest
     * Apiレスポンス：IfaDistributionReceiveMethodChangeInputA002ApiResponse
     * Dtoリクエスト：IfaDistributionReceiveMethodChangeInputA002RequestDto
     * Dtoレスポンス：IfaDistributionReceiveMethodChangeInputA002ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return 登録・変更に成功したか否か
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDistributionReceiveMethodChangeInputRegisterChangeA002")
    public String registerChangeA002(@RequestBody IfaDistributionReceiveMethodChangeInputA002ApiRequest apiReq) throws Exception {

        // 顧客共通情報をリクエストスコープにセットする
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaDistributionReceiveMethodChangeInputA002RequestDto appReq = new IfaDistributionReceiveMethodChangeInputA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaDistributionReceiveMethodChangeInputA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDistributionReceiveMethodChangeInputService",
                "registerChangeA002",
                new TypeReference<DataList<IfaDistributionReceiveMethodChangeInputA002ResponseDto>>() { },
                appReq
        );

        // サービスからのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaDistributionReceiveMethodChangeInputA002ApiResponse> apiRes
                = new DataList<IfaDistributionReceiveMethodChangeInputA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

