package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
 * 
 * 画面ID：SUB0501_01-05
 * 画面名：お知らせ閲覧状況照会
 *
 * @author <author-name>
 2024/06/13 新規作成
 */
@Mapper
public interface IfaNotificationViewStatusLookupMapper {
    
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
    public List<IfaNotificationViewStatusLookupSql001ResponseModel> selectIfaNotificationViewStatusLookupSql001(
        @Param("req") IfaNotificationViewStatusLookupSql001RequestModel req
        ) throws Exception;
    
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
    public List<IfaNotificationViewStatusLookupSql002ResponseModel> selectIfaNotificationViewStatusLookupSql002(
        @Param("req") IfaNotificationViewStatusLookupSql002RequestModel req
        ) throws Exception;
    
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
    public List<IfaNotificationViewStatusLookupSql003ResponseModel> selectIfaNotificationViewStatusLookupSql003(
        @Param("req") IfaNotificationViewStatusLookupSql003RequestModel req
        ) throws Exception;
    
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
    public List<IfaNotificationViewStatusLookupSql004ResponseModel> selectIfaNotificationViewStatusLookupSql004(
        @Param("req") IfaNotificationViewStatusLookupSql004RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql005
     * SQL名：既読情報の追加
     * SQLタイプ：insert
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaNotificationViewStatusLookupSql005(
        @Param("req")  IfaNotificationViewStatusLookupSql005RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql006
     * SQL名：既読情報の更新
     * SQLタイプ：update
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaNotificationViewStatusLookupSql006(
        @Param("req")  IfaNotificationViewStatusLookupSql006RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql007
     * SQL名：既読情報の削除
     * SQLタイプ：delete
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaNotificationViewStatusLookupSql007(
        @Param("req")  IfaNotificationViewStatusLookupSql007RequestModel req
        ) throws Exception;
    
}
