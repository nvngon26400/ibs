package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql002ResponseModel;




/**
 * 画面ID：SUB0202_0208-02
 * 画面名：株式詳細情報
 * @author <author-name>
 * 
 * 2023/07/31 新規作成
 *
 */
public interface IfaStockDetailInfoDao {
    
    /**
     * SQLID：Sql001
     * SQL名：指定銘柄銘柄名取得
     * SQLタイプ：select
     * リクエストクラス：IfaStockDetailInfoSql001RequestModel
     * レスポンスクラス：IfaStockDetailInfoSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaStockDetailInfoSql001ResponseModel> selectIfaStockDetailInfoSql001(IfaStockDetailInfoSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：株式詳細情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaStockDetailInfoSql002RequestModel
     * レスポンスクラス：IfaStockDetailInfoSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaStockDetailInfoSql002ResponseModel> selectIfaStockDetailInfoSql002(IfaStockDetailInfoSql002RequestModel req)
            throws Exception;
    
    
    
    
}
