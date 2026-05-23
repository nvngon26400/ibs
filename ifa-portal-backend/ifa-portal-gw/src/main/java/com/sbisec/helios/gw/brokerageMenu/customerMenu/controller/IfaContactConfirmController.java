package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactConfirmA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * コントローラー
 * 画面ID:SUB0202_0106-04
 * 画面名:接触履歴入力確認
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0106-04", screenNumber = "")
public class IfaContactConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaContactConfirmInsertA002
     * アクションID：A002
     * アクション名：確認
     * APIリクエスト：IfaContactConfirmA002ApiRequest
     * Apiレスポンス：IfaContactConfirmA002ApiResponse
     * Dtoリクエスト：IfaContactConfirmA002DtoRequest
     * Dtoレスポンス：IfaContactConfirmA002DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactConfirmInsertA002")
    public String insertA002(@RequestBody IfaContactConfirmA002ApiRequest x_apiReq) throws Exception {

        IfaContactConfirmA002RequestDto p_reqDto = new IfaContactConfirmA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(x_apiReq, p_reqDto);
        // 顧客共通情報の取得(redisの更新)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // サービスを呼出し
        DataList<IfaContactConfirmA002ResponseDto> p_resDto = ApiRequestUtil.invoke(
                    "cmpIfaContactConfirmService",
                    "insertA002", 
                    new TypeReference<DataList<IfaContactConfirmA002ResponseDto>>() {},
                    p_reqDto
                );
        // レスポンスデータ戻す
        DataList<IfaContactConfirmA002ApiResponse> p_apiRes = new DataList<IfaContactConfirmA002ApiResponse>();
        BeanUtils.copyProperties(p_resDto, p_apiRes);

        return jc.toString(p_apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
