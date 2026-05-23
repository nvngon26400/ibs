package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao;


import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql004ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql007RequestModel;


/**
 * 画面ID：SUB0501_01-05
 * 画面名：お知らせ閲覧状況照会
 *
 * @author <author-name>
 2024/06/13 新規作成
 *
 */
public interface IfaNotificationViewStatusLookupDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：お知らせ閲覧状況の情報取得（全担当者）
     * SQLタイプ：select
     * リクエストクラス：IfaNotificationViewStatusLookupSql001RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupSql001ResponseModel> selectIfaNotificationViewStatusLookupSql001(IfaNotificationViewStatusLookupSql001RequestModel req)
            throws Exception;
    
	
    /**
     * SQLID：Sql002
     * SQL名：お知らせ閲覧状況の情報取得（権限担当者）
     * SQLタイプ：select
     * リクエストクラス：IfaNotificationViewStatusLookupSql002RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql002ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupSql002ResponseModel> selectIfaNotificationViewStatusLookupSql002(IfaNotificationViewStatusLookupSql002RequestModel req)
            throws Exception;
    
	
    /**
     * SQLID：Sql003
     * SQL名：お知らせ閲覧状況の情報取得（個別担当者）
     * SQLタイプ：select
     * リクエストクラス：IfaNotificationViewStatusLookupSql003RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql003ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupSql003ResponseModel> selectIfaNotificationViewStatusLookupSql003(IfaNotificationViewStatusLookupSql003RequestModel req)
            throws Exception;
    
	
    /**
     * SQLID：Sql004
     * SQL名：ログイン者に紐づく既読情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaNotificationViewStatusLookupSql004RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql004ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupSql004ResponseModel> selectIfaNotificationViewStatusLookupSql004(IfaNotificationViewStatusLookupSql004RequestModel req)
            throws Exception;
    
    
    /**
     * SQLID：Sql005
     * SQL名：既読情報の追加
     * SQLタイプ：insert
     * リクエストクラス：IfaNotificationViewStatusLookupSql005RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaNotificationViewStatusLookupSql005(IfaNotificationViewStatusLookupSql005RequestModel req)
            throws Exception;
    
    
    
    /**
     * SQLID：Sql006
     * SQL名：既読情報の更新
     * SQLタイプ：update
     * リクエストクラス：IfaNotificationViewStatusLookupSql006RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql006ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaNotificationViewStatusLookupSql006(IfaNotificationViewStatusLookupSql006RequestModel req)
            throws Exception;
    
    
	
    /**
     * SQLID：Sql007
     * SQL名：既読情報の削除
     * SQLタイプ：delete
     * リクエストクラス：IfaNotificationViewStatusLookupSql007RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql007ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaNotificationViewStatusLookupSql007(IfaNotificationViewStatusLookupSql007RequestModel req)
            throws Exception;
    
}
