package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql001ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql002ResponseModel;

/**
 * 画面ID：SUB020302_0102-01
 * 画面名：国内株当日約定一覧
 * @author <author-name>
 * 
 * 2023/11/21 新規作成
 *
 */
public interface IfaTodayTradeListDao {
    
    /**
     * SQLID：Sql001
     * SQL名：顧客口座情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaTodayTradeListSql001RequestModel
     * レスポンスクラス：IfaTodayTradeListSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaTodayTradeListSql001ResponseModel> selectIfaTodayTradeListSql001(IfaTodayTradeListSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：銘柄コード存在チェック
     * SQLタイプ：select
     * リクエストクラス：IfaTodayTradeListSql002RequestModel
     * レスポンスクラス：IfaTodayTradeListSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaTodayTradeListSql002ResponseModel> selectIfaTodayTradeListSql002(IfaTodayTradeListSql002RequestModel req)
            throws Exception;
    
    
    
    
}
