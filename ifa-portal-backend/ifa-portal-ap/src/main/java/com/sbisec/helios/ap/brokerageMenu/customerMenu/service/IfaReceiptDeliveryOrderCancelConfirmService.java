package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-09_1
 * 画面名：現引現渡注文取消確認
 *
 * @author SCSK
 2024/05/21 新規作成
 */
public interface IfaReceiptDeliveryOrderCancelConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaReceiptDeliveryOrderCancelConfirmA001RequestDto
     * Dto レスポンス：IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto
     * model リクエスト：IfaReceiptDeliveryOrderCancelConfirmA001RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderCancelConfirmA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaReceiptDeliveryOrderCancelConfirmA001ResponseDto> initializeA001(
            IfaReceiptDeliveryOrderCancelConfirmA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002a
     * アクション名：注文発注
     * Dto リクエスト：IfaReceiptDeliveryOrderCancelConfirmA002RequestDto
     * Dto レスポンス：IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto
     * model リクエスト：IfaReceiptDeliveryOrderCancelConfirmA002RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderCancelConfirmA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> orderPlacementA002a(
            IfaReceiptDeliveryOrderCancelConfirmA002RequestDto dtoReq,
            IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel sql002Req)
            throws Exception;

    /**
     * アクションID：A002b
     * アクション名：注文発注
     * Dto リクエスト：IfaReceiptDeliveryOrderCancelConfirmA002RequestDto
     * Dto レスポンス：IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto
     * model リクエスト：IfaReceiptDeliveryOrderCancelConfirmA002RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderCancelConfirmA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto> orderPlacementA002b(
            IfaReceiptDeliveryOrderCancelConfirmA002RequestDto dtoReq)
            throws Exception;
}
