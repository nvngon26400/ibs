package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql005ResponseModel;

/**
 * 画面ID：SUB0402_02-01
 * 画面名：取引日記帳・顧客勘定元帳取得
 *
 * @author SCSK
 2024/05/08 新規作成
 *
 */
public interface IfaContractNoteCustomerLedgerAcquireDao {
    
    /**
     * SQLID：Sql001
     * SQL名：法定帳簿ファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaContractNoteCustomerLedgerAcquireSql001RequestModel
     * レスポンスクラス：IfaContractNoteCustomerLedgerAcquireSql001ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaContractNoteCustomerLedgerAcquireSql001ResponseModel> selectIfaContractNoteCustomerLedgerAcquireSql001()
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：法定帳簿一時テーブル取得
     * SQLタイプ：select
     * リクエストクラス：IfaContractNoteCustomerLedgerAcquireSql002RequestModel
     * レスポンスクラス：IfaContractNoteCustomerLedgerAcquireSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaContractNoteCustomerLedgerAcquireSql002ResponseModel> selectIfaContractNoteCustomerLedgerAcquireSql002(
            IfaContractNoteCustomerLedgerAcquireSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：法定帳簿一時テーブル登録
     * SQLタイプ：select
     * リクエストクラス：IfaContractNoteCustomerLedgerAcquireSql004RequestModel
     * レスポンスクラス：IfaContractNoteCustomerLedgerAcquireSql004ResponseModel
     *
     * @param req リクエスト
     * @exception exception システムエラー
     */
    public void insertIfaContractNoteCustomerLedgerAcquireSql004(
            IfaContractNoteCustomerLedgerAcquireSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：法定帳簿一時テーブル削除
     * SQLタイプ：delete
     * リクエストクラス：IfaContractNoteCustomerLedgerAcquireSql003RequestModel
     * レスポンスクラス：IfaContractNoteCustomerLedgerAcquireSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaContractNoteCustomerLedgerAcquireSql003(
            IfaContractNoteCustomerLedgerAcquireSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：帳票種別リスト取得
     * SQLタイプ：select
     * レスポンスクラス：IfaContractNoteCustomerLedgerAcquireSql001ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaContractNoteCustomerLedgerAcquireSql005ResponseModel> selectIfaContractNoteCustomerLedgerAcquireSql005()
            throws Exception;
    
}
