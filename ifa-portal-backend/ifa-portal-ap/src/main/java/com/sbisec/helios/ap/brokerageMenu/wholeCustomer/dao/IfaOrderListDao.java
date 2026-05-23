package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql008ResponseModel;





/**
 * 画面ID：SUB020302_0101-01
 * 画面名：注文一覧
 *
 * @author BASE李
 * 
 2024/03/30 新規作成
 *
 */
public interface IfaOrderListDao {
    
    /**
     * SQLID：Sql001
     * SQL名：国内株式一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql001RequestModel
     * レスポンスクラス：IfaOrderListSql001ResponseModel
     *
     * @param req sql001RequestModel
     * @return res sql001抽出項目
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql001ResponseModel> selectIfaOrderListSql001(IfaOrderListSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：国内投資信託一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql002RequestModel
     * レスポンスクラス：IfaOrderListSql002ResponseModel
     *
     * @param req sql002RequestModel
     * @return res sql002抽出項目
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql002ResponseModel> selectIfaOrderListSql002(IfaOrderListSql002RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：募集注文一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql003RequestModel
     * レスポンスクラス：IfaOrderListSql003ResponseModel
     *
     * @param req sql003RequestModel
     * @return res sql003抽出項目
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql003ResponseModel> selectIfaOrderListSql003(IfaOrderListSql003RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：外国株式（委託注文）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql004RequestModel
     * レスポンスクラス：IfaOrderListSql004ResponseModel
     *
     * @param req sql004RequestModel
     * @return res sql004抽出項目
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql004ResponseModel> selectIfaOrderListSql004(IfaOrderListSql004RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：外国株式（店頭注文）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql005RequestModel
     * レスポンスクラス：IfaOrderListSql005ResponseModel
     *
     * @param req sql005RequestModel
     * @return res sql005抽出項目
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql005ResponseModel> selectIfaOrderListSql005(IfaOrderListSql005RequestModel req)
            throws Exception;
    
    
    /**
     * SQLID：Sql007
     * SQL名：注文一覧コメント取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql007RequestModel
     * レスポンスクラス：IfaOrderListSql007ResponseModel
     *
     * @return res sql007抽出項目
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql007ResponseModel> selectIfaOrderListSql007()
            throws Exception;
    
    /**
     * SQLID：Sql008
     * SQL名：国内投資信託（定期積立）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql008RequestModel
     * レスポンスクラス：IfaOrderListSql008ResponseModel
     *
     * @param req sql008RequestModel
     * @return res sql008抽出項目
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql008ResponseModel> selectIfaOrderListSql008(IfaOrderListSql008RequestModel req)
            throws Exception;
}
