package com.sbisec.helios.gw.brokerageMenu.jointSubscript.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerCorrectA004ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerCorrectA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerCorrectA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerCorrectA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerCorrectA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerCorrectA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptCustomerCorrectA004ApiResponse;

/**
 * コントローラー
 * 画面ID：SUB0206_01-03
 * 画面名：共同募集 顧客修正
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0206_01-03", screenNumber = "")
public class IfaJointSubscriptCustomerCorrectController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerCorrectInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaJointSubscriptCustomerCorrectA001ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerCorrectA001ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerCorrectA001DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerCorrectA001DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerCorrectInitializeA001")
    public String initializeA001(@RequestBody IfaJointSubscriptCustomerCorrectA001ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerCorrectA001RequestDto p_reqDto = new IfaJointSubscriptCustomerCorrectA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerCorrectA001ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerCorrectService",
                    "initializeA001", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerCorrectA001ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerCorrectA001ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerCorrectA001ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerCorrectConfirmCorrectA002
     * アクションID：A002
     * アクション名：修正(修正入力)
     * APIリクエスト：IfaJointSubscriptCustomerCorrectA002ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerCorrectA002ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerCorrectA002DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerCorrectA002DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerCorrectConfirmCorrectA002")
    public String confirmCorrectA002(@RequestBody IfaJointSubscriptCustomerCorrectA002ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerCorrectA002RequestDto p_reqDto = new IfaJointSubscriptCustomerCorrectA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerCorrectA002ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerCorrectService",
                    "confirmCorrectA002", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerCorrectA002ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerCorrectA002ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerCorrectA002ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerCorrectExecuteCorrectA004
     * アクションID：A004
     * アクション名：承認確認
     * APIリクエスト：IfaJointSubscriptCustomerCorrectA004ApiRequest
     * Apiレスポンス：IfaJointSubscriptCustomerCorrectA004ApiResponse
     * Dtoリクエスト：IfaJointSubscriptCustomerCorrectA004DtoRequest
     * Dtoレスポンス：IfaJointSubscriptCustomerCorrectA004DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/jointSubscript/ifaJointSubscriptCustomerCorrectExecuteCorrectA004")
    public String executeCorrectA004(@RequestBody IfaJointSubscriptCustomerCorrectA004ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptCustomerCorrectA004RequestDto p_reqDto = new IfaJointSubscriptCustomerCorrectA004RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_reqDto, x_apiReq);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaJointSubscriptCustomerCorrectA004ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaJointSubscriptCustomerCorrectService",
                    "executeCorrectA004", 
                    new TypeReference<DataList<IfaJointSubscriptCustomerCorrectA004ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaJointSubscriptCustomerCorrectA004ApiResponse> p_apiRes = new DataList<IfaJointSubscriptCustomerCorrectA004ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_resDto);

        return jc.toString(p_apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }

}
