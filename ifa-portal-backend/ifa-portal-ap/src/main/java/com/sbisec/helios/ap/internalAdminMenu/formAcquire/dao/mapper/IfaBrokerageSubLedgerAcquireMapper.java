package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql001ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql002ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql003ResponseModel;



import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql005RequestModel;



import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql004RequestModel;



/**
 * 
 * 画面ID：SUB0402_01-01
 * 画面名：仲介業補助簿取得
 *
 * @author <author-name>
 2024/06/21 新規作成
 */
@Mapper
public interface IfaBrokerageSubLedgerAcquireMapper {
    
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
    public List<IfaBrokerageSubLedgerAcquireSql001ResponseModel> selectIfaBrokerageSubLedgerAcquireSql001(
        @Param("req") IfaBrokerageSubLedgerAcquireSql001RequestModel req
        ) throws Exception;
    
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
    public List<IfaBrokerageSubLedgerAcquireSql002ResponseModel> selectIfaBrokerageSubLedgerAcquireSql002(
        @Param("req") IfaBrokerageSubLedgerAcquireSql002RequestModel req
        ) throws Exception;
    
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
    public List<IfaBrokerageSubLedgerAcquireSql003ResponseModel> selectIfaBrokerageSubLedgerAcquireSql003(
        @Param("req") IfaBrokerageSubLedgerAcquireSql003RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql005
     * SQL名：仲介業補助簿一時テーブル登録
     * SQLタイプ：insert
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaBrokerageSubLedgerAcquireSql005(
        @Param("req")  IfaBrokerageSubLedgerAcquireSql005RequestModel req
        ) throws Exception;
    
    
    
    /**
     * SQLID：Sql004
     * SQL名：仲介業補助簿一時テーブル削除
     * SQLタイプ：delete
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaBrokerageSubLedgerAcquireSql004(
        @Param("req")  IfaBrokerageSubLedgerAcquireSql004RequestModel req
        ) throws Exception;
    
}
