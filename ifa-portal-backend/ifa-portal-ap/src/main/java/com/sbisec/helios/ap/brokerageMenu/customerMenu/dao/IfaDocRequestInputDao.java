package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestInputSql012ResponseModel;

/**
 * 画面ID：SUB0202_0704-01 画面名：書類請求入力
 * 
 * @author xin.huang
 *
 */
public interface IfaDocRequestInputDao {

    /**
     * SQLID：Sql001
     * SQL名：書類分類リストを取得する
     * SQLタイプ：select 
     * リクエスト：IfaDocRequestInputSql001RequestModel
     * レスポンス：IfaDocRequestInputSql001ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql001RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql001ResponseModel>}
     * @throws Exception書類分類リストを取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql001ResponseModel> selectIfaDocRequestInputSql001(
            IfaDocRequestInputSql001RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：書類請求一覧表示対象データを取得する
     * SQLタイプ：select 
     * リクエスト：IfaDocRequestInputSql002RequestModel
     * レスポンス：IfaDocRequestInputSql002ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql002RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql002ResponseModel>}
     * @throws Exception 書類請求一覧表示対象データを取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql002ResponseModel> selectIfaDocRequestInputSql002(
            IfaDocRequestInputSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：書類リストを取得する
     * SQLタイプ：select 
     * リクエスト：IfaDocRequestInputSql003RequestModel
     * レスポンス：IfaDocRequestInputSql003ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql003RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql003ResponseModel>}
     * @throws Exception 書類リストを取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql003ResponseModel> selectIfaDocRequestInputSql003(
            IfaDocRequestInputSql003RequestModel req) throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：書類データを取得する
     * SQLタイプ：select 
     * リクエスト：IfaDocRequestInputSql004RequestModel
     * レスポンス：IfaDocRequestInputSql004ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql004RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql004ResponseModel>}
     * @throws Exception 書類データを取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql004ResponseModel> selectIfaDocRequestInputSql004(
            IfaDocRequestInputSql004RequestModel req) throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：投信銘柄名取得する
     * SQLタイプ：select 
     * リクエスト：IfaDocRequestInputSql006RequestModel
     * レスポンス：IfaDocRequestInputSql006ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql006RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql006ResponseModel>}
     * @throws Exception 投信銘柄名取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql006ResponseModel> selectIfaDocRequestInputSql006(
            IfaDocRequestInputSql006RequestModel req) throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：書類請求データ取得する
     * SQLタイプ：select 
     * リクエスト：IfaDocRequestInputSql007RequestModel
     * レスポンス：IfaDocRequestInputSql007ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql007RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql007ResponseModel>}
     * @throws Exception 書類請求データ取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql007ResponseModel> selectIfaDocRequestInputSql007(
            IfaDocRequestInputSql007RequestModel req) throws Exception;

    /**
     * SQLID：Sql008
     * SQL名：MP仲介業者判定
     * SQLタイプ：select 
     * リクエスト：IfaDocRequestInputSql008RequestModel
     * レスポンス：IfaDocRequestInputSql008ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql008RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql008ResponseModel>}
     * @throws Exception MP仲介業者判定処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql008ResponseModel> selectIfaDocRequestInputSql008(
            IfaDocRequestInputSql008RequestModel req) throws Exception;

    /**
     * SQLID：Sql009
     * SQL名：交付区分の表示設定を取得する
     * SQLタイプ：select 
     * リクエスト：IfaDocRequestInputSql009RequestModel
     * レスポンス：IfaDocRequestInputSql009ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql009RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql009ResponseModel>}
     * @throws Exception 交付区分の表示設定を取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql009ResponseModel> selectIfaDocRequestInputSql009(
            IfaDocRequestInputSql009RequestModel req) throws Exception;

    /**
     * SQLID：Sql010
     * SQL名：未閲読目論見書件数取得
     * SQLタイプ：select
     * リクエスト：IfaDocRequestInputSql010RequestModel
     * レスポンス：IfaDocRequestInputSql010ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql010RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql010ResponseModel>}
     * @throws Exception 未閲読目論見書件数取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql010ResponseModel> selectIfaDocRequestInputSql010(
            IfaDocRequestInputSql010RequestModel req) throws Exception;

    /**
     * SQLID：Sql011
     * SQL名：外貨建MMF銘柄名取得
     * SQLタイプ：select
     * リクエスト：IfaDocRequestInputSql011RequestModel
     * レスポンス：IfaDocRequestInputSql011ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql011RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql011ResponseModel>}
     * @throws Exception 外貨建MMF銘柄名取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql011ResponseModel> selectIfaDocRequestInputSql011(
            IfaDocRequestInputSql011RequestModel req) throws Exception;

    /**
     * SQLID：Sql012
     * SQL名：BM交付データ取得
     * SQLタイプ：select
     * リクエスト：IfaDocRequestInputSql012RequestModel
     * レスポンス：IfaDocRequestInputSql012ResponseModel
     *
     * @param req {@code IfaDocRequestInputSql012RequestModel }
     * @return {@code DataList <IfaDocRequestInputSql012ResponseModel>}
     * @throws Exception BM交付データ取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestInputSql012ResponseModel> selectIfaDocRequestInputSql012(
            IfaDocRequestInputSql012RequestModel req) throws Exception;
}
