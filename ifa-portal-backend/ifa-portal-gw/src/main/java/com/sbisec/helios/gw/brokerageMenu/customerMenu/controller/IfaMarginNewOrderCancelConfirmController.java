package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002bResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCancelConfirmA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCancelConfirmA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCancelConfirmA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginNewOrderCancelConfirmA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-03_1
 * 画面名：信用新規注文取消確認
 * 2024/04/17 新規作成
 *
 * @author 宇田川達弥
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-03_1", screenNumber = "")
public class IfaMarginNewOrderCancelConfirmController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderCancelConfirmInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMarginNewOrderCancelConfirmA001ApiRequest
     * Apiレスポンス：IfaMarginNewOrderCancelConfirmA001ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaMarginNewOrderCancelConfirmA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginNewOrderCancelConfirmInitializeA001")
    public String initializeA001(@RequestBody IfaMarginNewOrderCancelConfirmA001ApiRequest apiReq) throws Exception {
        
        final IfaMarginNewOrderCancelConfirmA001RequestDto appReq = new IfaMarginNewOrderCancelConfirmA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        final DataList<IfaMarginNewOrderCancelConfirmA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderCancelConfirmService", "initializeA001",
                new TypeReference<DataList<IfaMarginNewOrderCancelConfirmA001ResponseDto>>() {
                }, appReq);
        
        // 処理結果を返却する
        final DataList<IfaMarginNewOrderCancelConfirmA001ApiResponse> apiRes = new DataList<IfaMarginNewOrderCancelConfirmA001ApiResponse>();

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
                    new TypeReference<List<IfaMarginNewOrderCancelConfirmA001ApiResponse>>() {
                    }));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginNewOrderCancelConfirmOrderPlacementA002
     * アクションID：A002
     * アクション名：注文発注
     * APIリクエスト：IfaMarginNewOrderCancelConfirmA002ApiRequest
     * Apiレスポンス：IfaMarginNewOrderCancelConfirmA002ApiResponse
     * Dtoリクエスト：IfaMarginNewOrderCancelConfirmA002aRequestDto
     * Dtoリクエスト：IfaMarginNewOrderCancelConfirmA002bRequestDto
     * Dtoレスポンス：IfaMarginNewOrderCancelConfirmA002aResponseDto
     * Dtoレスポンス：IfaMarginNewOrderCancelConfirmA002bResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginNewOrderCancelConfirmOrderPlacementA002")
    public String orderPlacementA002(@RequestBody IfaMarginNewOrderCancelConfirmA002ApiRequest apiReq)
            throws Exception {
        
        final IfaMarginNewOrderCancelConfirmA002aRequestDto appReqA = new IfaMarginNewOrderCancelConfirmA002aRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReqA, apiReq);

        // 受注日時の形式を変換
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(apiReq.getOrderDayTime(), inputFormatter);
        // 変換後の受注日を設定
        appReqA.setOrderDayTime(dateTime.format(outputFormatter));

        // 取消発注前の処理を呼び出す
        final DataList<IfaMarginNewOrderCancelConfirmA002aResponseDto> appResA = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderCancelConfirmService", "orderPlacementA002a",
                new TypeReference<DataList<IfaMarginNewOrderCancelConfirmA002aResponseDto>>() {
                }, appReqA);
        
        // 取消発注前の処理が成功していない場合、その結果を返却する
        final DataList<IfaMarginNewOrderCancelConfirmA002ApiResponse> apiRes = new DataList<IfaMarginNewOrderCancelConfirmA002ApiResponse>();
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            BeanUtils.copyProperties(apiRes, appResA);
            return jc.toString(apiRes);
        }
        
        // リクエスト内容と取消発注前の処理結果を設定する
        final IfaMarginNewOrderCancelConfirmA002bRequestDto appReqB = new IfaMarginNewOrderCancelConfirmA002bRequestDto();
        
        appReqB.setRequest(appReqA);
        BeanUtils.copyProperties(appReqB, appResA.getDataList().get(0));
        
        // 取消発注の本処理を呼び出す
        final DataList<IfaMarginNewOrderCancelConfirmA002bResponseDto> appResB = ApiRequestUtil.invoke(
                "cmpIfaMarginNewOrderCancelConfirmService", "orderPlacementA002b",
                new TypeReference<DataList<IfaMarginNewOrderCancelConfirmA002bResponseDto>>() {
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
                    new TypeReference<List<IfaMarginNewOrderCancelConfirmA002ApiResponse>>() {
                    }));
        }
        
        // 取消発注後の処理結果を返却する
        return jc.toString(apiRes);
    }
}
