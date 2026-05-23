package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA003ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactCorrectA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactCorrectA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactCorrectA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactCorrectA003ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * コントローラー
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0106-07", screenNumber = "")
public class IfaContactCorrectController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaContactCorrectInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaContactCorrectA001ApiRequest
     * Apiレスポンス：IfaContactCorrectA001ApiResponse
     * Dtoリクエスト：IfaContactCorrectA001DtoRequest
     * Dtoレスポンス：IfaContactCorrectA001DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactCorrectInitializeA001")
    public String initializeA001(@RequestBody IfaContactCorrectA001ApiRequest x_apiReq) throws Exception {

        IfaContactCorrectA001RequestDto p_reqDto = new IfaContactCorrectA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(x_apiReq, p_reqDto); 
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaContactCorrectA001ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaContactCorrectService",
                    "initializeA001", 
                    new TypeReference<DataList<IfaContactCorrectA001ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaContactCorrectA001ApiResponse> p_apiRes = new DataList<IfaContactCorrectA001ApiResponse>();
        BeanUtils.copyProperties(p_resDto, p_apiRes);

        return jc.toString(p_apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaContactCorrectUpdateA003
     * アクションID：A003
     * アクション名：更新ボタン押下
     * APIリクエスト：IfaContactCorrectA003ApiRequest
     * Apiレスポンス：IfaContactCorrectA003ApiResponse
     * Dtoリクエスト：IfaContactCorrectA003DtoRequest
     * Dtoレスポンス：IfaContactCorrectA003DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactCorrectUpdateA003")
    public String updateA003(@RequestBody IfaContactCorrectA003ApiRequest x_apiReq) throws Exception {

        IfaContactCorrectA003RequestDto p_reqDto = new IfaContactCorrectA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(x_apiReq, p_reqDto);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaContactCorrectA003ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaContactCorrectService",
                    "updateA003", 
                    new TypeReference<DataList<IfaContactCorrectA003ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaContactCorrectA003ApiResponse> p_apiRes = new DataList<IfaContactCorrectA003ApiResponse>();
        BeanUtils.copyProperties(p_resDto, p_apiRes);

        return jc.toString(p_apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
