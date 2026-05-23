package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderInputSql010ResponseModel;


/**
 * 画面ID：SUB0202_0302-02_1
 * 画面名：外国株式店頭注文入力
 * 2024/05/07 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaForeignStockCounterOrderInputDao {

    /**
     * SQLID：Sql001
     * SQL名：項目チェック情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignStockCounterOrderInputSql001RequestModel
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql001ResponseModel
     *
     * @param req パラメータ
     * @return 項目チェック情報
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql001ResponseModel> selectIfaForeignStockCounterOrderInputSql001(
            IfaForeignStockCounterOrderInputSql001RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：営業日チェック情報取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql002ResponseModel
     *
     * @return 営業日チェック情報
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql002ResponseModel> selectIfaForeignStockCounterOrderInputSql002() throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：店頭注文新規登録初期情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignStockCounterOrderInputSql003RequestModel
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql003ResponseModel
     *
     * @param req パラメータ
     * @return 店頭注文新規登録初期情報
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql003ResponseModel> selectIfaForeignStockCounterOrderInputSql003(
            IfaForeignStockCounterOrderInputSql003RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：店頭取引販売上限数量取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignStockCounterOrderInputSql004RequestModel
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql004ResponseModel
     *
     * @param req パラメータ
     * @return 店頭取引販売上限数量
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql004ResponseModel> selectIfaForeignStockCounterOrderInputSql004(
            IfaForeignStockCounterOrderInputSql004RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：注文入力の使い方URL取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql005ResponseModel
     *
     * @return 注文入力の使い方URL
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql005ResponseModel> selectIfaForeignStockCounterOrderInputSql005() throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：チャートURL取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql006ResponseModel
     *
     * @return チャートURL
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql006ResponseModel> selectIfaForeignStockCounterOrderInputSql006() throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：株価参照URL取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql007ResponseModel
     *
     * @return 株価参照URL
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql007ResponseModel> selectIfaForeignStockCounterOrderInputSql007() throws Exception;

    /**
     * SQLID：Sql008
     * SQL名：ワーニング約定金額取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql008ResponseModel
     *
     * @return ワーニング約定金額
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql008ResponseModel> selectIfaForeignStockCounterOrderInputSql008() throws Exception;

    /**
     * SQLID：Sql009
     * SQL名：上限約定金額取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql009ResponseModel
     *
     * @return 上限約定金額
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql009ResponseModel> selectIfaForeignStockCounterOrderInputSql009() throws Exception;

    /**
     * SQLID：Sql010
     * SQL名：上限注文数量取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderInputSql010ResponseModel
     *
     * @return 上限注文数量
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputSql010ResponseModel> selectIfaForeignStockCounterOrderInputSql010() throws Exception;

}
