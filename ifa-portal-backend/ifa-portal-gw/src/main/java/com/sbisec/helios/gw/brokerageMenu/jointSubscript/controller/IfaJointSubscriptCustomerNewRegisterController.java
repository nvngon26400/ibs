package com.sbisec.helios.gw.brokerageMenu.jointSubscript.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerNewRegisterA004ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerNewRegisterA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerNewRegisterA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerNewRegisterA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerNewRegisterA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerNewRegisterA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerNewRegisterA004ApiResponse;

/**
 * コントローラー
 * 画面ID：SUB0206_01-02
 * 画面名：共同募集 顧客新規登録
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0206_01-02", screenNumber = "")
public class IfaJointSubscriptCustomerNewRegisterController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerNewRegisterInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaJointSubscriptCustomerNewRegisterA001ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerNewRegisterA001ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerNewRegisterA001DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerNewRegisterA001DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerNewRegisterInitializeA001")
    public String initializeA001(@RequestBody IfaJointSubscriptCustomerNewRegisterA001ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerNewRegisterA001RequestDto p_reqDto = new IfaJointSubscriptCustomerNewRegisterA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerNewRegisterA001ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerNewRegisterService",
                    "initializeA001", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerNewRegisterA001ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerNewRegisterA001ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerNewRegisterA001ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerNewRegisterConfirmRegisterA002
     * アクションID：A002
     * アクション名：登録確認(新規登録入力)
     * APIリクエスト：IfaJointSubscriptCustomerNewRegisterA002ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerNewRegisterA002ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerNewRegisterA002DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerNewRegisterA002DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerNewRegisterConfirmRegisterA002")
    public String confirmNewRegisterA002(@RequestBody IfaJointSubscriptCustomerNewRegisterA002ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerNewRegisterA002RequestDto p_reqDto = new IfaJointSubscriptCustomerNewRegisterA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerNewRegisterA002ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerNewRegisterService",
                    "confirmRegisterA002", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerNewRegisterA002ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerNewRegisterA002ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerNewRegisterA002ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerNewRegisterExecuteRegisterA004
     * アクションID：A004
     * アクション名：登録(新規登録確認)
     * APIリクエスト：IfaJointSubscriptCustomerNewRegisterA004ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerNewRegisterA004ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerNewRegisterA004DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerNewRegisterA004DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerNewRegisterExecuteRegisterA004")
    public String executeRegisterA004(@RequestBody IfaJointSubscriptCustomerNewRegisterA004ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerNewRegisterA004RequestDto p_reqDto = new IfaJointSubscriptCustomerNewRegisterA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerNewRegisterA004ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerNewRegisterService",
                    "executeRegisterA004", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerNewRegisterA004ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerNewRegisterA004ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerNewRegisterA004ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }

}
