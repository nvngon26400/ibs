package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql011ResponseModel;

/**
 * 画面ID：SUB0202_010201-01
 * 画面名：保有商品一覧
 *
 * @author SCSK
 *     2023/10/17 新規作成
 *
 */
public interface IfaHoldingSecurityListDao {
    
    /**
     * SQLID：Sql002
     * SQL名：基準価額単位取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql002RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 基準価額単位取得時に例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListSql002ResponseModel> selectIfaHoldingSecurityListSql002(IfaHoldingSecurityListSql002RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：優先市場取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql005RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql005ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 優先市場取得時に例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListSql005ResponseModel> selectIfaHoldingSecurityListSql005(IfaHoldingSecurityListSql005RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：ISA買付可能判定区分取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql006RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql006ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 優先市場取得時に例外が発生した場合
     */
    public IfaHoldingSecurityListSql006ResponseModel selectIfaHoldingSecurityListSql006(
            IfaHoldingSecurityListSql006RequestModel req) throws Exception;
    
    public IfaHoldingSecurityListSql007ResponseModel selectIfaHoldingSecurityListSql007(
            IfaHoldingSecurityListSql007RequestModel req) throws Exception;
    
    public IfaHoldingSecurityListSql008ResponseModel selectIfaHoldingSecurityListSql008(
            IfaHoldingSecurityListSql008RequestModel req) throws Exception;
    
    public DataList<IfaHoldingSecurityListSql009ResponseModel> selectIfaHoldingSecurityListSql009(
            IfaHoldingSecurityListSql009RequestModel req) throws Exception;
    
    public DataList<IfaHoldingSecurityListSql010ResponseModel> selectIfaHoldingSecurityListSql010(
            IfaHoldingSecurityListSql010RequestModel req) throws Exception;

    public DataList<IfaHoldingSecurityListSql011ResponseModel> selectIfaHoldingSecurityListSql011(
            IfaHoldingSecurityListSql011RequestModel req) throws Exception;
}
