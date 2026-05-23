package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCancelConfirmA002bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-03_1
 * 画面名：信用新規注文取消確認
 * 2024/04/17 新規作成
 *
 * @author 宇田川達弥
 */
public interface IfaMarginNewOrderCancelConfirmService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginNewOrderCancelConfirmA001RequestDto
     * Dto レスポンス：IfaMarginNewOrderCancelConfirmA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMarginNewOrderCancelConfirmA001ResponseDto> initializeA001(
            IfaMarginNewOrderCancelConfirmA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002a
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginNewOrderCancelConfirmA002aRequestDto
     * Dto レスポンス：IfaMarginNewOrderCancelConfirmA002aResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMarginNewOrderCancelConfirmA002aResponseDto> orderPlacementA002a(
            IfaMarginNewOrderCancelConfirmA002aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002b
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginNewOrderCancelConfirmA002bRequestDto
     * Dto レスポンス：IfaMarginNewOrderCancelConfirmA002bResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMarginNewOrderCancelConfirmA002bResponseDto> orderPlacementA002b(
            IfaMarginNewOrderCancelConfirmA002bRequestDto dtoReq) throws Exception;
}
