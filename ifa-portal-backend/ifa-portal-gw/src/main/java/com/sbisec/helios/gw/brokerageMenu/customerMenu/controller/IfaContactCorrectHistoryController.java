package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectHistoryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectHistoryA001ResponseDto;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactCorrectHistoryA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactCorrectHistoryA001ApiResponse;

/**
 * 画面ID:SUB0202_0106-08
 * 画面名:接触履歴（入力）修正履歴
 *
 * @author SBI大連 夏
 * @date   2025/08/14
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0106-08", screenNumber = "")
public class IfaContactCorrectHistoryController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaContactCorrectHistoryInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaContactCorrectHistoryA001ApiRequest
     * Apiレスポンス：IfaContactCorrectHistoryA001ApiResponse
     * Dtoリクエスト：IfaContactCorrectHistoryA001DtoRequest
     * Dtoレスポンス：IfaContactCorrectHistoryA001DtoResponse
     *
     * @param x_apiReq リクエストパラメータ
     * @return レスポンスデータ(json)
     * @exception Exception 例外
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaContactCorrectHistoryInitializeA001")
    public String initializeA001(@RequestBody IfaContactCorrectHistoryA001ApiRequest apiReq) throws Exception {
        
        IfaContactCorrectHistoryA001RequestDto reqDto = new IfaContactCorrectHistoryA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(apiReq, reqDto);
        // サービスを呼出し
        DataList<IfaContactCorrectHistoryA001ResponseDto> resDto = ApiRequestUtil.invoke(
                "cmpIfaContactCorrectHistoryService", 
                "initializeA001", 
                new TypeReference<DataList<IfaContactCorrectHistoryA001ResponseDto>>() {}, 
                reqDto
            );
        // レスポンスデータ戻す
        DataList<IfaContactCorrectHistoryA001ApiResponse> apiRes = new DataList<IfaContactCorrectHistoryA001ApiResponse>();
        BeanUtils.copyProperties(resDto, apiRes);
        return jc.toString(apiRes);
    }
    
}
