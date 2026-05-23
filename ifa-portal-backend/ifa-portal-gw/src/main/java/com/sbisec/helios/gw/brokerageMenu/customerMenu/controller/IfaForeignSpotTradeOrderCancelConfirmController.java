package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderCancelConfirmA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaForeignSpotTradeOrderCancelConfirmA010ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0301-03_1
 * 画面名：外国現物取引注文取消確認
 * 2024/03/29 新規作成
 *
 * @author 宇田川達弥
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0301-03_1", screenNumber = "")
public class IfaForeignSpotTradeOrderCancelConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaForeignSpotTradeOrderCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaForeignSpotTradeOrderCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto
     *
     * @param apiReq APIリクエスト
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaForeignSpotTradeOrderCancelConfirmA001ApiRequest apiReq)
            throws Exception {
        
        IfaForeignSpotTradeOrderCancelConfirmA001RequestDto appReq = new IfaForeignSpotTradeOrderCancelConfirmA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        final DataList<IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderCancelConfirmService", "initializeA001",
                new TypeReference<DataList<IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto>>() {
                }, appReq);
        
        // 共通項目をコピー
        DataList<IfaForeignSpotTradeOrderCancelConfirmA001ApiResponse> apiRes = new DataList<>();
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaForeignSpotTradeOrderCancelConfirmA001ApiResponse>>() {
                    }));
        }
        
        // 処理結果を返却する
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderCancelConfirmOrderCancellationA010
     * アクションID：A010
     * アクション名：注文取消
     * APIリクエスト：IfaForeignSpotTradeOrderCancelConfirmA010ApiRequest
     * Apiレスポンス：IfaForeignSpotTradeOrderCancelConfirmA010ApiResponse
     * Dtoリクエスト(発注前)：IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto
     * Dtoリクエスト(発注後)：IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto
     * Dtoレスポンス(発注前)：IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto
     * Dtoレスポンス(発注後)：IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto
     *
     * @param apiReq APIリクエスト
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaForeignSpotTradeOrderCancelConfirmOrderCancellationA010")
    public String orderCancellationA010(@RequestBody IfaForeignSpotTradeOrderCancelConfirmA010ApiRequest apiReq)
            throws Exception {
        
        IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto appReqA = new IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqA, apiReq);
        
        // 取消発注前の処理(A010a)を呼び出す
        final DataList<IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto> appResA = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderCancelConfirmService", "orderCancellationA010a",
                new TypeReference<DataList<IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto>>() {
                }, appReqA);
        
        // 取消発注前の処理が成功していない場合、その結果を返却する
        DataList<IfaForeignSpotTradeOrderCancelConfirmA010ApiResponse> apiRes = new DataList<IfaForeignSpotTradeOrderCancelConfirmA010ApiResponse>();
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            BeanUtils.copyProperties(apiRes, appResA);
            return jc.toString(apiRes);
        }
        
        // A010aのレスポンス項目をA010bのリクエスト項目へコピーする
        IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto appReqB = new IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto();
        
        appReqB.setRequest(appReqA);
        BeanUtils.copyProperties(appReqB, appResA.getDataList().get(0));
        
        // 取消発注の本処理(A010b)を呼び出す
        DataList<IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto> appResB = ApiRequestUtil.invoke(
                "cmpIfaForeignSpotTradeOrderCancelConfirmService", "orderCancellationA010b",
                new TypeReference<DataList<IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto>>() {
                }, appReqB);
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appResB.getErrorLevel());
        apiRes.setMessage(appResB.getMessage());
        apiRes.setRequestedTime(appResB.getRequestedTime());
        apiRes.setMaxRownum(appResB.getMaxRownum());
        apiRes.setTitle(appResB.getTitle());
        apiRes.setTotalSize(appResB.getTotalSize());
        apiRes.setReturnCode(appResB.getReturnCode());
        
        // Dtoレスポンス(発注後)のDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appResB.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appResB.getDataList()),
                    new TypeReference<List<IfaForeignSpotTradeOrderCancelConfirmA010ApiResponse>>() {
                    }));
        }
        
        // 取消発注後の処理結果を返却する
        return jc.toString(apiRes);
    }
}
