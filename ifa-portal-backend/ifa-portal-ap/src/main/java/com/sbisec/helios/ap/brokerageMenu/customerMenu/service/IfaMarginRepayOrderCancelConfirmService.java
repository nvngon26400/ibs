package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA002aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA002aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA002bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCancelConfirmA002bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-07_1
 * 画面名：信用返済注文取消確認
 * 2024/05/27 新規作成
 *
 * @author 宇田川達弥
 */
public interface IfaMarginRepayOrderCancelConfirmService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginRepayOrderCancelConfirmA001RequestDto
     * Dto レスポンス：IfaMarginRepayOrderCancelConfirmA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMarginRepayOrderCancelConfirmA001ResponseDto> initializeA001(
            IfaMarginRepayOrderCancelConfirmA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginRepayOrderCancelConfirmA002aRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCancelConfirmA002aResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMarginRepayOrderCancelConfirmA002aResponseDto> orderPlacementA002a(
            IfaMarginRepayOrderCancelConfirmA002aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginRepayOrderCancelConfirmA002bRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCancelConfirmA002bResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMarginRepayOrderCancelConfirmA002bResponseDto> orderPlacementA002b(
            IfaMarginRepayOrderCancelConfirmA002bRequestDto dtoReq) throws Exception;
    
}
