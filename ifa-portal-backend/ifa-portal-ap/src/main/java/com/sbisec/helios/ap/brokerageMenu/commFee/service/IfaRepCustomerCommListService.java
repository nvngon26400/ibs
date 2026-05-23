package com.sbisec.helios.ap.brokerageMenu.commFee.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA004aResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020501-01
 * 画面名：担当顧客別手数料一覧
 * 2024/06/10 新規作成
 *
 * @author
 */
public interface IfaRepCustomerCommListService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaRepCustomerCommListA001RequestDto
     * Dto レスポンス：IfaRepCustomerCommListA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaRepCustomerCommListA001ResponseDto> initializeA001(IfaRepCustomerCommListA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaRepCustomerCommListA002RequestDto
     * Dto レスポンス：IfaRepCustomerCommListA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaRepCustomerCommListA002ResponseDto> displayA002(IfaRepCustomerCommListA002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaRepCustomerCommListA004aRequestDto
     * Dto レスポンス：IfaRepCustomerCommListA004aResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaRepCustomerCommListA004aResponseDto> csvOutputA004(IfaRepCustomerCommListA004aRequestDto dtoReq, String fwSessionId)
            throws Exception;
    
}
