package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql012ResponseModel;

/**
 * 画面ID：SUB0202_0106-02
 * 画面名：接触履歴受付詳細
 *
 * @author 趙韫慧
 * 2025/04/22 新規作成
 */
@Mapper
public interface IfaContactAcceptDetailMapper {

    /**
     * SQLID：Sql002
     * SQL名：国内注文履歴詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql002RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql002ResponseModel
     * 
     * @param req リクエスト
     * @return 国内株式注文履歴詳細情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql002ResponseModel> selectIfaContactAcceptDetailSql002(
            @Param("req") IfaContactAcceptDetailSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：国内投信注文履歴詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql003RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql003ResponseModel
     * 
     * @param req リクエスト
     * @return 国内投信注文履歴詳細情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql003ResponseModel> selectIfaContactAcceptDetailSql003(
            @Param("req") IfaContactAcceptDetailSql003RequestModel req) throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：その他余力拘束履歴詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql004RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql004ResponseModel
     * 
     * @param req リクエスト
     * @return その他余力拘束履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql004ResponseModel> selectIfaContactAcceptDetailSql004(
            @Param("req") IfaContactAcceptDetailSql004RequestModel req) throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：外株委託注文接触履歴受付詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql005RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql005ResponseModel
     * 
     * @param req リクエスト
     * @return 外株委託注文接触履歴受付詳細情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql005ResponseModel> selectIfaContactAcceptDetailSql005(
            @Param("req") IfaContactAcceptDetailSql005RequestModel req) throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：外株店頭注文接触履歴受付詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql006RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql006ResponseModel
     * 
     * @param req リクエスト
     * @return 外株店頭注文接触履歴受付詳細情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql006ResponseModel> selectIfaContactAcceptDetailSql006(
            @Param("req") IfaContactAcceptDetailSql006RequestModel req) throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：為替取引接触履歴受付詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql007RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql007ResponseModel
     * 
     * @param req リクエスト
     * @return 為替取引接触履歴受付詳細情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql007ResponseModel> selectIfaContactAcceptDetailSql007(
            @Param("req") IfaContactAcceptDetailSql007RequestModel req) throws Exception;

    /**
     * SQLID：Sql008
     * SQL名：同一のIFA注文番号内1番目レコードの注文種別区分情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql008RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql008ResponseModel
     * 
     * @return 同一のIFA注文番号内1番目レコードの注文種別区分情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql008ResponseModel> selectIfaContactAcceptDetailSql008() throws Exception;

    /**
     * SQLID：Sql009
     * SQL名：同一のEC受注番号内1番目レコードの注文状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql009RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql009ResponseModel
     * 
     * @return 外株委託注文全期間接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql009ResponseModel> selectIfaContactAcceptDetailSql009() throws Exception;

    /**
     * SQLID：Sql010
     * SQL名： 同一のEC受注番号内1番目レコードの注文状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql010RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql010ResponseModel
     * 
     * @return 同一のEC受注番号内1番目レコードの注文状況情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql010ResponseModel> selectIfaContactAcceptDetailSql010() throws Exception;

    /**
     * SQLID：Sql011
     * SQL名：同一のEC受注番号内1番目レコードの注文状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql011RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql011ResponseModel
     * 
     * @return 同一のEC受注番号内1番目レコードの注文状況情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql011ResponseModel> selectIfaContactAcceptDetailSql011() throws Exception;

    /**
     * SQLID：Sql012
     * SQL名：同一のトランザクションID内1番目レコードの取消区分情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql012RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql012ResponseModel
     * 
     * @return 同一のトランザクションID内1番目レコードの取消区分情報
     * @exception Exception 異常
     */
    public List<IfaContactAcceptDetailSql012ResponseModel> selectIfaContactAcceptDetailSql012() throws Exception;
}
