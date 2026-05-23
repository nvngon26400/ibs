package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA002aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA002aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA002bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA002bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCancelConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderCancelConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-07_1
 * 画面名：信用返済注文取消確認
 * 2024/05/27 新規作成
 *
 * @author 宇田川達弥
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-07_1", screenNumber = "")
public class IfaMarginRepayOrderCancelConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMarginRepayOrderCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaMarginRepayOrderCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaMarginRepayOrderCancelConfirmA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaMarginRepayOrderCancelConfirmA001ApiRequest apiReq) throws Exception {
        
        final IfaMarginRepayOrderCancelConfirmA001RequestDto appReq = new IfaMarginRepayOrderCancelConfirmA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        final DataList<IfaMarginRepayOrderCancelConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderCancelConfirmService", "initializeA001",
                new TypeReference<DataList<IfaMarginRepayOrderCancelConfirmA001ResponseDto>>() {
                }, appReq);
        
        // 処理結果を返却する
        final DataList<IfaMarginRepayOrderCancelConfirmA001ApiResponse> apiRes = new DataList<IfaMarginRepayOrderCancelConfirmA001ApiResponse>();
        
        // 共通項目をコピー
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
                    new TypeReference<List<IfaMarginRepayOrderCancelConfirmA001ApiResponse>>() {
                    }));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderCancelConfirmOrderPlacementA002
     * アクションID：A002
     * アクション名：注文発注
     * APIリクエスト：IfaMarginRepayOrderCancelConfirmA002ApiRequest
     * APIレスポンス：IfaMarginRepayOrderCancelConfirmA002ApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderCancelConfirmA002aRequestDto
     * Dtoレスポンス：IfaMarginRepayOrderCancelConfirmA002aResponseDto
     * Dtoリクエスト：IfaMarginRepayOrderCancelConfirmA002bRequestDto
     * Dtoレスポンス：IfaMarginRepayOrderCancelConfirmA002bResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderCancelConfirmOrderPlacementA002")
    public String orderPlacementA002(@RequestBody IfaMarginRepayOrderCancelConfirmA002ApiRequest apiReq)
            throws Exception {
        
        final IfaMarginRepayOrderCancelConfirmA002aRequestDto appReqA = new IfaMarginRepayOrderCancelConfirmA002aRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqA, apiReq);
        
        // 取消発注前の処理を呼び出す
        final DataList<IfaMarginRepayOrderCancelConfirmA002aResponseDto> appResA = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderCancelConfirmService", "orderPlacementA002a",
                new TypeReference<DataList<IfaMarginRepayOrderCancelConfirmA002aResponseDto>>() {
                }, appReqA);
        
        // 取消発注前の処理が成功していない場合、その結果を返却する
        final DataList<IfaMarginRepayOrderCancelConfirmA002ApiResponse> apiRes = new DataList<IfaMarginRepayOrderCancelConfirmA002ApiResponse>();
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            BeanUtils.copyProperties(apiRes, appResA);
            return jc.toString(apiRes);
        }
        
        // リクエスト内容と取消発注前の処理結果を設定する
        final IfaMarginRepayOrderCancelConfirmA002bRequestDto appReqB = new IfaMarginRepayOrderCancelConfirmA002bRequestDto();
        
        appReqB.setRequest(appReqA);
        BeanUtils.copyProperties(appReqB, appResA.getDataList().get(0));
        
        // 取消発注の本処理を呼び出す
        final DataList<IfaMarginRepayOrderCancelConfirmA002bResponseDto> appResB = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderCancelConfirmService", "orderPlacementA002b",
                new TypeReference<DataList<IfaMarginRepayOrderCancelConfirmA002bResponseDto>>() {
                }, appReqB);
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appResB.getErrorLevel());
        apiRes.setMessage(appResB.getMessage());
        apiRes.setRequestedTime(appResB.getRequestedTime());
        apiRes.setMaxRownum(appResB.getMaxRownum());
        apiRes.setTitle(appResB.getTitle());
        apiRes.setTotalSize(appResB.getTotalSize());
        apiRes.setReturnCode(appResB.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appResB.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appResB.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderCancelConfirmA002ApiResponse>>() {
                    }));
        }
        
        // 取消発注後の処理結果を返却する
        return jc.toString(apiRes);
    }
}
