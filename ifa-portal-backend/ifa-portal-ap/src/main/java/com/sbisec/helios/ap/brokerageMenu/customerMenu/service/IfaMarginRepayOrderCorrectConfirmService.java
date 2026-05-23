package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectConfirmA001bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-06_2
 * 画面名：信用返済注文訂正確認
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉秀行
 */
public interface IfaMarginRepayOrderCorrectConfirmService extends Service {
    
    /**
     * アクションID：A001a
     * アクション名：訂正発注
     * Dto リクエスト：IfaMarginRepayOrderCorrectConfirmA001aRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectConfirmA001aResponseDto
     *
     * @param dtoReq 訂正発注前の注文内容登録処理リクエスト
     * @return 訂正発注前の注文内容登録処理レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderCorrectConfirmA001aResponseDto> correctionOrderA001a(
            IfaMarginRepayOrderCorrectConfirmA001aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A001b
     * アクション名：訂正発注
     * Dto リクエスト：IfaMarginRepayOrderCorrectConfirmA001bRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectConfirmA001bResponseDto
     *
     * @param dtoReq 訂正発注リクエスト
     * @return 訂正発注レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderCorrectConfirmA001bResponseDto> correctionOrderA001b(
            IfaMarginRepayOrderCorrectConfirmA001bRequestDto dtoReq) throws Exception;
}
