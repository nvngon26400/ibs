package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputX001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactInputX001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputA007ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputA007ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputA008ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputA008ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputX001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactInputX001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * コントローラー
 * 画面ID：SUB0202_0106-03
 * 画面名：接触履歴入力
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0106-03", screenNumber = "")
public class IfaContactInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaContactInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaContactInputA001ApiRequest
     * Apiレスポンス：IfaContactInputA001ApiResponse
     * Dtoリクエスト：IfaContactInputA001DtoRequest
     * Dtoレスポンス：IfaContactInputA001DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactInputInitializeA001")
    public String initializeA001(@RequestBody IfaContactInputA001ApiRequest x_apiReq) throws Exception {

        IfaContactInputA001RequestDto p_reqDto = new IfaContactInputA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(x_apiReq, p_reqDto);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaContactInputA001ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaContactInputService",
                    "initializeA001", 
                    new TypeReference<DataList<IfaContactInputA001ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaContactInputA001ApiResponse> p_apiRes = new DataList<IfaContactInputA001ApiResponse>();
        BeanUtils.copyProperties(p_resDto, p_apiRes);
        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/jointSubscript/ifaContactInputInitializeX001
     * アクションID：X001
     * アクション名：追加入力/管理項目修正
     * APIリクエスト：IfaContactInputX001ApiRequest
     * Apiレスポンス：IfaContactInputX001ApiResponse
     * Dtoリクエスト：IfaContactInputX001DtoRequest
     * Dtoレスポンス：IfaContactInputX001DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactInputInitializeX001")
    public String initializeX001(@RequestBody IfaContactInputX001ApiRequest x_apiReq) throws Exception {

        IfaContactInputX001RequestDto p_reqDto = new IfaContactInputX001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(x_apiReq, p_reqDto);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaContactInputX001ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaContactInputService",
                    "initializeX001", 
                    new TypeReference<DataList<IfaContactInputX001ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaContactInputX001ApiResponse> p_apiRes = new DataList<IfaContactInputX001ApiResponse>();
        BeanUtils.copyProperties(p_resDto, p_apiRes);
        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaContactInputConfirmA002
     * アクションID：A002
     * アクション名：確認
     * APIリクエスト：IfaContactInputA002ApiRequest
     * Apiレスポンス：IfaContactInputA002ApiResponse
     * Dtoリクエスト：IfaContactInputA002DtoRequest
     * Dtoレスポンス：IfaContactInputA002DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactInputConfirmA002")
    public String confirmA002(@RequestBody IfaContactInputA002ApiRequest x_apiReq) throws Exception {

        IfaContactInputA002RequestDto p_reqDto = new IfaContactInputA002RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(x_apiReq, p_reqDto);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaContactInputA002ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaContactInputService",
                    "confirmA002", 
                    new TypeReference<DataList<IfaContactInputA002ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaContactInputA002ApiResponse> p_apiRes = new DataList<IfaContactInputA002ApiResponse>();
        BeanUtils.copyProperties(p_resDto, p_apiRes);
        return jc.toString(p_apiRes);
    }
    
    @PostMapping("/brokerageMenu/customerMenu/ifaContactInputCategoryChangeA007")
    public String categoryChangeA007(@RequestBody IfaContactInputA007ApiRequest x_apiReq) throws Exception {
        IfaContactInputA007RequestDto p_reqDto = new IfaContactInputA007RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(x_apiReq, p_reqDto);
        // サービスを呼出し
        DataList<IfaContactInputA007ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaContactInputService",
                    "categoryChangeA007", 
                    new TypeReference<DataList<IfaContactInputA007ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaContactInputA007ApiResponse> p_apiRes = new DataList<IfaContactInputA007ApiResponse>();
        BeanUtils.copyProperties(p_resDto, p_apiRes);
        return jc.toString(p_apiRes);
    }
    
    @PostMapping("/brokerageMenu/customerMenu/ifaContactInputCategoryChangeA008")
    public String categoryChangeA008(@RequestBody IfaContactInputA008ApiRequest x_apiReq) throws Exception {
        IfaContactInputA008RequestDto p_reqDto = new IfaContactInputA008RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(x_apiReq, p_reqDto);
        // サービスを呼出し
        DataList<IfaContactInputA008ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaContactInputService",
                    "categoryChangeA008", 
                    new TypeReference<DataList<IfaContactInputA008ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaContactInputA008ApiResponse> p_apiRes = new DataList<IfaContactInputA008ApiResponse>();
        BeanUtils.copyProperties(p_resDto, p_apiRes);
        return jc.toString(p_apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}
