package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputA010ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderCorrectInputResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-06_1
 * 画面名：信用返済注文訂正入力
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉秀行
 */
public interface IfaMarginRepayOrderCorrectInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginRepayOrderCorrectInputRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectInputResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderCorrectInputResponseDto> initializeA001(
            IfaMarginRepayOrderCorrectInputRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：
     * アクション名：更新
     * Dto リクエスト：IfaMarginRepayOrderCorrectInputRequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectInputResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderCorrectInputResponseDto> updateA004(
            IfaMarginRepayOrderCorrectInputRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：訂正確認
     * Dto リクエスト：IfaMarginRepayOrderCorrectInputA010RequestDto
     * Dto レスポンス：IfaMarginRepayOrderCorrectInputA010ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderCorrectInputA010ResponseDto> correctionConfirmA010(
            IfaMarginRepayOrderCorrectInputA010RequestDto dtoReq) throws Exception;
}
