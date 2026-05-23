package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDeliverInOutDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDeliverInOutDetailSql001ResponseModel;





/**
 * 画面ID：SUB020302_0204-01
 * 画面名：入出庫明細
 * @author <author-name>
 * 
 * 2024/04/03 新規作成
 *
 */
public interface IfaDeliverInOutDetailDao {
    
    /**
     * SQLID：Sql001
     * SQL名：入出庫明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaDeliverInOutDetailSql001RequestModel
     * レスポンスクラス：IfaDeliverInOutDetailSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDeliverInOutDetailSql001ResponseModel> selectIfaDeliverInOutDetailSql001(IfaDeliverInOutDetailSql001RequestModel req)
            throws Exception;
    
    
    
    
}
