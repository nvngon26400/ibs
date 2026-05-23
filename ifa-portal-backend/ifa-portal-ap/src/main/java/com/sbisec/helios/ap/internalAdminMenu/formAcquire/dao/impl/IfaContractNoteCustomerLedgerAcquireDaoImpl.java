package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.IfaContractNoteCustomerLedgerAcquireDao;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.mapper.IfaContractNoteCustomerLedgerAcquireMapper;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql005ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0402_02-01
 * 画面名：取引日記帳・顧客勘定元帳取得
 *
 * @author SCSK
 2024/05/08 新規作成
 */
@Component
public class IfaContractNoteCustomerLedgerAcquireDaoImpl extends RowSelectableDao
        implements IfaContractNoteCustomerLedgerAcquireDao {
    
    @Autowired
    private IfaContractNoteCustomerLedgerAcquireMapper mapper;
    
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
    @Override
    public DataList<IfaContractNoteCustomerLedgerAcquireSql001ResponseModel> selectIfaContractNoteCustomerLedgerAcquireSql001()
            throws Exception {
        
        DataList<IfaContractNoteCustomerLedgerAcquireSql001ResponseModel> res = new DataList<IfaContractNoteCustomerLedgerAcquireSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaContractNoteCustomerLedgerAcquireSql001());
        return res;
    }
    
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
            IfaContractNoteCustomerLedgerAcquireSql002RequestModel req) throws Exception {
        
        DataList<IfaContractNoteCustomerLedgerAcquireSql002ResponseModel> res = new DataList<IfaContractNoteCustomerLedgerAcquireSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaContractNoteCustomerLedgerAcquireSql002(req));
        return res;
    }
    
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
            IfaContractNoteCustomerLedgerAcquireSql004RequestModel req) throws Exception {
        
        mapper.insertIfaContractNoteCustomerLedgerAcquireSql004(req.getUserId(), req.getItems());
    }
    
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
            IfaContractNoteCustomerLedgerAcquireSql003RequestModel req) throws Exception {
        
        return mapper.deleteIfaContractNoteCustomerLedgerAcquireSql003(req);
    }
    
    /**
     * SQLID：Sql005
     * SQL名：帳票種別リスト取得
     * SQLタイプ：select
     * レスポンスクラス：IfaContractNoteCustomerLedgerAcquireSql001ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaContractNoteCustomerLedgerAcquireSql005ResponseModel> selectIfaContractNoteCustomerLedgerAcquireSql005()
            throws Exception {
        
        DataList<IfaContractNoteCustomerLedgerAcquireSql005ResponseModel> res = new DataList<IfaContractNoteCustomerLedgerAcquireSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaContractNoteCustomerLedgerAcquireSql005());
        return res;
    }
    
}
