package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactDetailA001ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactDetailA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactDetailA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * コントローラー
 * 画面ID:SUB0202_0106-06
 * 画面名:接触履歴詳細
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0106-06", screenNumber = "")
public class IfaContactDetailController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/IfaContactDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaContactDetailA001ApiRequest
     * Apiレスポンス：IfaContactDetailA001ApiResponse
     * Dtoリクエスト：IfaContactDetailA001DtoRequest
     * Dtoレスポンス：IfaContactDetailA001DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactDetailInitializeA001")
    public String initializeA001(@RequestBody IfaContactDetailA001ApiRequest x_apiReq) throws Exception {

        IfaContactDetailA001RequestDto p_reqDto = new IfaContactDetailA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(x_apiReq, p_reqDto);
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaContactDetailA001ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaContactDetailService",
                    "initializeA001", 
                    new TypeReference<DataList<IfaContactDetailA001ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaContactDetailA001ApiResponse> p_apiRes = new DataList<IfaContactDetailA001ApiResponse>();
        BeanUtils.copyProperties(p_resDto, p_apiRes);

        return jc.toString(p_apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
