package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-04_2
 * 画面名：信用返済注文確認
 * 2024/04/04 新規作成
 *
 * @author 宇田川達弥
 */
public interface IfaMarginRepayOrderConfirmService extends Service {
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginRepayOrderConfirmA001aRequestDto
     * Dto レスポンス：IfaMarginRepayOrderConfirmA001aResponseDto
     *
     * @param dtoReq 注文発注前の注文内容登録処理リクエスト
     * @return 注文発注前の注文内容登録処理レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderConfirmA001aResponseDto> orderPlacementA001a(
            IfaMarginRepayOrderConfirmA001aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginRepayOrderConfirmA001bRequestDto
     * Dto レスポンス：IfaMarginRepayOrderConfirmA001bResponseDto
     *
     * @param dtoReq 注文発注リクエスト
     * @return 注文発注レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderConfirmA001bResponseDto> orderPlacementA001b(
            IfaMarginRepayOrderConfirmA001bRequestDto dtoReq) throws Exception;
}
