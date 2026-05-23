package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginDepositTransferConfirmA001bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0304-01_2
 * 画面名：米株信用保証金振替確認
 *
 * @author
 */
public interface IfaForeignMarginDepositTransferConfirmService extends Service {
    
    /**
     * アクションID：A001a
     * アクション名：振替指示
     * Dto リクエスト：IfaForeignMarginDepositTransferConfirmA001aRequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferConfirmA001aResponseDto
     * model リクエスト：IfaForeignMarginDepositTransferConfirmSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferConfirmA001aResponseDto> transferInstructionA001a(
            IfaForeignMarginDepositTransferConfirmA001aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A001b
     * アクション名：振替指示
     * Dto リクエスト：IfaForeignMarginDepositTransferConfirmA001bRequestDto
     * Dto レスポンス：IfaForeignMarginDepositTransferConfirmA001bResponseDto
     * model リクエスト：IfaForeignMarginDepositTransferConfirmSql002RequestModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaForeignMarginDepositTransferConfirmA001bResponseDto> transferInstructionA001b(
            IfaForeignMarginDepositTransferConfirmA001bRequestDto dtoReq) throws Exception;
}
