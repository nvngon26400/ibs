package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA016RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA016ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-04_1
 * 画面名：信用返済注文入力
 * 2024/04/08 新規作成
 *
 * @author 池亀　緑
 */
public interface IfaMarginRepayOrderInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginRepayOrderInputA001RequestDto
     * Dto レスポンス：IfaMarginRepayOrderInputA001ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderInputA001ResponseDto> initializeA001(
            IfaMarginRepayOrderInputA001RequestDto dtoReq) throws Exception;
    
    /**
    * アクションID：A002
    * アクション名：市場選択
    
    * Dto リクエスト：IfaMarginRepayOrderInputA002RequestDto
    * Dto レスポンス：IfaMarginRepayOrderInputA002ResponseDto
    *
    * @param dtoReq リクエストDto
    * @return レスポンスDto
    * @exception Exception 例外が発生した場合
    */
    public DataList<IfaMarginRepayOrderInputA002ResponseDto> marketSelectionA002(
            IfaMarginRepayOrderInputA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaMarginRepayOrderInputA004RequestDto
     * Dto レスポンス：IfaMarginRepayOrderInputA004ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderInputA004ResponseDto> updateA004(IfaMarginRepayOrderInputA004RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A016
     * アクション名：注文確認
     * Dto リクエスト：IfaMarginRepayOrderInputA016RequestDto
     * Dto レスポンス：IfaMarginRepayOrderInputA016ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginRepayOrderInputA016ResponseDto> orderConfirmA016(
            IfaMarginRepayOrderInputA016RequestDto dtoReq) throws Exception;
}
