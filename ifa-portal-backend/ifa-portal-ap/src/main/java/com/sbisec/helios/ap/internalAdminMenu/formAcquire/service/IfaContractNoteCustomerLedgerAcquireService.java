package com.sbisec.helios.ap.internalAdminMenu.formAcquire.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0402_02-01
 * 画面名：取引日記帳・顧客勘定元帳取得
 *
 * @author BASE 李
 2024/05/08 新規作成
 */
public interface IfaContractNoteCustomerLedgerAcquireService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto レスポンス：IfaContractNoteCustomerLedgerAcquireA001ResponseDto
     * model レスポンス：IfaContractNoteCustomerLedgerAcquireA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaContractNoteCustomerLedgerAcquireA001ResponseDto> initializeA001(
            IfaContractNoteCustomerLedgerAcquireA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaContractNoteCustomerLedgerAcquireA002RequestDto
     * Dto レスポンス：IfaContractNoteCustomerLedgerAcquireA002ResponseDto
     * model リクエスト：IfaContractNoteCustomerLedgerAcquireA002RequestModel
     * model レスポンス：IfaContractNoteCustomerLedgerAcquireA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaContractNoteCustomerLedgerAcquireA002ResponseDto> displayA002(
            IfaContractNoteCustomerLedgerAcquireA002RequestDto dtoReq) throws Exception;
    
}
