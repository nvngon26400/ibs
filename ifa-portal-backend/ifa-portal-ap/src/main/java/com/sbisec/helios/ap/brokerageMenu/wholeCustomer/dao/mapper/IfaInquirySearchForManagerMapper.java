package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql007ResponseModel;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/08/15
 */

@Mapper
public interface IfaInquirySearchForManagerMapper {

    /**
     * SQLID：Sql001
     * SQL名：メッセージ取得取得
     * SQLタイプ：select
     * レスポンスクラス：IfaInquirySearchForManagerSql001ResponseModel
     * 
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public IfaInquirySearchForManagerSql001ResponseModel selectIfaInquirySearchForManagerSql001() throws Exception;
    
    /**
     * SQLID:Sql002
     * SQL名:カテゴリ（大）リスト取得
     * SQLタイプ:select
     * レスポンスクラス:IfaContactInputSql001ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaInquirySearchForManagerSql002ResponseModel> selectIfaInquirySearchForManagerSql002() throws Exception;
    
    /**
     * SQLID:Sql003
     * SQL名:カテゴリ（中）リスト取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql003RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql003ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaInquirySearchForManagerSql003ResponseModel> selectIfaInquirySearchForManagerSql003(
        @Param("req") IfaInquirySearchForManagerSql003RequestModel req) throws Exception;

    /**
     * SQLID:Sql004
     * SQL名:カテゴリ（小）リスト取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql004RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql004ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaInquirySearchForManagerSql004ResponseModel> selectIfaInquirySearchForManagerSql004(
            @Param("req") IfaInquirySearchForManagerSql004RequestModel req) throws Exception;
    
    /**
     * SQLID:Sql005
     * SQL名:接触履歴（入力）リスト取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql005RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql005ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaInquirySearchForManagerSql005ResponseModel> selectIfaInquirySearchForManagerSql005(
        @Param("req") IfaInquirySearchForManagerSql005RequestModel req) throws Exception;
    
    /**
     * SQLID:Sql006
     * SQL名:明細件数取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql006RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql006ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public IfaInquirySearchForManagerSql006ResponseModel selectIfaInquirySearchForManagerSql006(
        @Param("req") IfaInquirySearchForManagerSql006RequestModel req) throws Exception;
    
    /**
     * SQLID:Sql007
     * SQL名:明細件数取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql007RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql007ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public IfaInquirySearchForManagerSql007ResponseModel selectIfaInquirySearchForManagerSql007(
        @Param("req") IfaInquirySearchForManagerSql007RequestModel req) throws Exception;
    
}
