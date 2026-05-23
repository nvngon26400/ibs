package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql013ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql015ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql016RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql016ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql017RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql017ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql018RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql018ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql019RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql019ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql020RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql020ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql021ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql022ResponseModel;

/**
 * 画面ID：SUB0202_0106-01
 * 画面名：接触履歴
 * 
 * @author 趙韫慧
 * 2025/03/27 新規作成
 */
@Mapper
public interface IfaContactMapper {

    /**
     * SQLID：Sql002
     * SQL名：国内株式現物注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql002RequestModel
     * レスポンスクラス：IfaContactSql002ResponseModel
     * 
     * @param req リクエスト
     * @return 国内株式現物注文履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql002ResponseModel> selectIfaContactSql002(@Param("req") IfaContactSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：国内株式信用新規(返済)注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql003RequestModel
     * レスポンスクラス：IfaContactSql003ResponseModel
     * 
     * @param req リクエスト
     * @return 国内株式信用新規(返済)注文履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql003ResponseModel> selectIfaContactSql003(@Param("req") IfaContactSql003RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：問合せ接触履歴取得(当日まで)
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql004RequestModel
     * レスポンスクラス：IfaContactSql004ResponseModel
     * 
     * @param req リクエスト
     * @return 問合せ接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql004ResponseModel> selectIfaContactSql004(@Param("req") IfaContactSql004RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：書類請求接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql005RequestModel
     * レスポンスクラス：IfaContactSql005ResponseModel
     * 
     * @param req リクエスト
     * @return 書類請求接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql005ResponseModel> selectIfaContactSql005(@Param("req") IfaContactSql005RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：出金接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql006RequestModel
     * レスポンスクラス：IfaContactSql006ResponseModel
     * 
     * @param req リクエスト
     * @return 出金接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql006ResponseModel> selectIfaContactSql006(@Param("req") IfaContactSql006RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：出金取消接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql007RequestModel
     * レスポンスクラス：IfaContactSql007ResponseModel
     * 
     * @param req リクエスト
     * @return 出金接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql007ResponseModel> selectIfaContactSql007(@Param("req") IfaContactSql007RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql008
     * SQL名：国内注文接触履歴見出
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql008RequestModel
     * レスポンスクラス：IfaContactSql008ResponseModel
     * 
     * @param req リクエスト
     * @return 国内注文接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql008ResponseModel> selectIfaContactSql008(@Param("req") IfaContactSql008RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql011
     * SQL名：外株委託注文接触履歴取得(当日まで)
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql011RequestModel
     * レスポンスクラス：IfaContactSql011ResponseModel
     * 
     * @param req リクエスト
     * @return 外株委託注文接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql011ResponseModel> selectIfaContactSql011(@Param("req") IfaContactSql011RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql012
     * SQL名：外株店頭注文接触履歴取得(当日まで)
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql012RequestModel
     * レスポンスクラス：IfaContactSql012ResponseModel
     * 
     * @param req リクエスト
     * @return 外株店頭注文接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql012ResponseModel> selectIfaContactSql012(@Param("req") IfaContactSql012RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql013
     * SQL名：為替取引注文接触履歴取得(当日まで)
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql013RequestModel
     * レスポンスクラス：IfaContactSql013ResponseModel
     * 
     * @param req リクエスト
     * @return 為替取引注文接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql013ResponseModel> selectIfaContactSql013(@Param("req") IfaContactSql013RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql015
     * SQL名：国内株式現引現渡注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql015RequestModel
     * レスポンスクラス：IfaContactSql015ResponseModel
     * 
     * @param req リクエスト
     * @return 国内株式現引現渡注文接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql015ResponseModel> selectIfaContactSql015(@Param("req") IfaContactSql015RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql016
     * SQL名：国内投信(一般)注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql016RequestModel
     * レスポンスクラス：IfaContactSql016ResponseModel
     * 
     * @param req リクエスト
     * @return 国内投信(一般)注文接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql016ResponseModel> selectIfaContactSql016(@Param("req") IfaContactSql016RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql017
     * SQL名：国内投信(累投)注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql017RequestModel
     * レスポンスクラス：IfaContactSql017ResponseModel
     * 
     * @param req リクエスト
     * @return 国内投信(累投)注文接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql017ResponseModel> selectIfaContactSql017(@Param("req") IfaContactSql017RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql018
     * SQL名：その他注文その他余力拘束取消の接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql018RequestModel
     * レスポンスクラス：IfaContactSql018ResponseModel
     * 
     * @param req リクエスト
     * @return その他注文その他余力拘束取消の当日接触履歴情報
     * @exception Exception 異常
     */
    public List<IfaContactSql018ResponseModel> selectIfaContactSql018(@Param("req") IfaContactSql018RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql019
     * SQL名：IFA出金接触履歴比較
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql019RequestModel
     * レスポンスクラス：IfaContactSql019ResponseModel
     * 
     * @param req リクエスト
     * @return IFA出金接触履歴比較結果
     * @exception Exception 異常
     */
    public List<IfaContactSql019ResponseModel> selectIfaContactSql019(@Param("req") IfaContactSql019RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql020
     * SQL名：IFA出金取消接触履歴比較
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql020RequestModel
     * レスポンスクラス：IfaContactSql020ResponseModel
     * 
     * @param req リクエスト
     * @return IFA出金取消接触履歴比較
     * @exception Exception 異常
     */
    public List<IfaContactSql020ResponseModel> selectIfaContactSql020(@Param("req") IfaContactSql020RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql021
     * SQL名：同一のIFA注文番号内で最大情報取得
     * SQLタイプ：select
     * レスポンスクラス：IfaContactSql021ResponseModel
     * 
     * @return 最大情報
     * @exception Exception 異常
     */
    public List<IfaContactSql021ResponseModel> selectIfaContactSql021() throws Exception;

    /**
     * SQLID：Sql022
     * SQL名：同一のトランザクションID内で最大情報取得
     * SQLタイプ：select
     * レスポンスクラス：IfaContactSql022ResponseModel
     * 
     * @return 最大情報
     * @exception Exception 異常
     */
    public List<IfaContactSql022ResponseModel> selectIfaContactSql022() throws Exception;
}
