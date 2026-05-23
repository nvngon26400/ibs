package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA010ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-05_1
 * 画面名：信用一括返済入力
 * 2024/04/15 新規作成
 *
 * @author 池亀　緑
 */
public interface IfaMarginMassRepayInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginMassRepayInputA001RequestDto
     * Dto レスポンス：IfaMarginMassRepayInputA001ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginMassRepayInputA001ResponseDto> initializeA001(IfaMarginMassRepayInputA001RequestDto dtoReq)
            throws Exception;
    
    /**
    * アクションID：A003
    * アクション名：返済順序
    
    * Dto リクエスト：IfaMarginMassRepayInputA003RequestDto
    * Dto レスポンス：IfaMarginMassRepayInputA003ResponseDto
    *
    * @param dtoReq リクエストDto
    * @return レスポンスDto
    * @exception Exception 例外が発生した場合
    */
    public DataList<IfaMarginMassRepayInputA003ResponseDto> repaymentOrderA003(
            IfaMarginMassRepayInputA003RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：初期化（戻り）
     * Dto リクエスト：IfaMarginMassRepayInputA010RequestDto
     * Dto レスポンス：IfaMarginMassRepayInputA010ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginMassRepayInputA010ResponseDto> initializeReturnA010(
            IfaMarginMassRepayInputA010RequestDto dtoReq) throws Exception;
}
