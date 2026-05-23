package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.IfaBrokerageSubLedgerAcquireDao;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.mapper.IfaBrokerageSubLedgerAcquireMapper;

import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql001ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql002ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql003ResponseModel;



import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql005RequestModel;


import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql004RequestModel;


/**
 * 画面ID：SUB0402_01-01
 * 画面名：仲介業補助簿取得
 *
 * @author BASE 丁
 2024/06/21 新規作成
 */
@Component
public class IfaBrokerageSubLedgerAcquireDaoImpL extends RowSelectableDao implements IfaBrokerageSubLedgerAcquireDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrokerageSubLedgerAcquireDaoImpL.class);
    @Autowired
    private IfaBrokerageSubLedgerAcquireMapper mapper;
    
	
    /**
     * SQLID：Sql001
     * SQL名：仲介業補助簿ファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaBrokerageSubLedgerAcquireSql001RequestModel
     * レスポンスクラス：IfaBrokerageSubLedgerAcquireSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerageSubLedgerAcquireSql001ResponseModel> selectIfaBrokerageSubLedgerAcquireSql001(IfaBrokerageSubLedgerAcquireSql001RequestModel req)
            throws Exception {
        
        DataList<IfaBrokerageSubLedgerAcquireSql001ResponseModel> res = new DataList<IfaBrokerageSubLedgerAcquireSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrokerageSubLedgerAcquireSql001(req));
        return res;
    }
    
	
    /**
     * SQLID：Sql002
     * SQL名：仲介業補助簿情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBrokerageSubLedgerAcquireSql002RequestModel
     * レスポンスクラス：IfaBrokerageSubLedgerAcquireSql002ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerageSubLedgerAcquireSql002ResponseModel> selectIfaBrokerageSubLedgerAcquireSql002(IfaBrokerageSubLedgerAcquireSql002RequestModel req)
            throws Exception {
        
        DataList<IfaBrokerageSubLedgerAcquireSql002ResponseModel> res = new DataList<IfaBrokerageSubLedgerAcquireSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrokerageSubLedgerAcquireSql002(req));
        return res;
    }
    
	
    /**
     * SQLID：Sql003
     * SQL名：仲介業補助簿一時テーブル取得
     * SQLタイプ：select
     * リクエストクラス：IfaBrokerageSubLedgerAcquireSql003RequestModel
     * レスポンスクラス：IfaBrokerageSubLedgerAcquireSql003ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerageSubLedgerAcquireSql003ResponseModel> selectIfaBrokerageSubLedgerAcquireSql003(IfaBrokerageSubLedgerAcquireSql003RequestModel req)
            throws Exception {
        
        DataList<IfaBrokerageSubLedgerAcquireSql003ResponseModel> res = new DataList<IfaBrokerageSubLedgerAcquireSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrokerageSubLedgerAcquireSql003(req));
        return res;
    }
    
    
    
	
    /**
     * SQLID：Sql005
     * SQL名：仲介業補助簿一時テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：IfaBrokerageSubLedgerAcquireSql005RequestModel
     * レスポンスクラス：IfaBrokerageSubLedgerAcquireSql005ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaBrokerageSubLedgerAcquireSql005(IfaBrokerageSubLedgerAcquireSql005RequestModel req)
            throws Exception {

        return mapper.insertIfaBrokerageSubLedgerAcquireSql005(req);
    }
    
    
	
    /**
     * SQLID：Sql004
     * SQL名：仲介業補助簿一時テーブル削除
     * SQLタイプ：delete
     * リクエストクラス：IfaBrokerageSubLedgerAcquireSql004RequestModel
     * レスポンスクラス：IfaBrokerageSubLedgerAcquireSql004ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaBrokerageSubLedgerAcquireSql004(IfaBrokerageSubLedgerAcquireSql004RequestModel req)
            throws Exception {

        return mapper.deleteIfaBrokerageSubLedgerAcquireSql004(req);
    }
    
}
