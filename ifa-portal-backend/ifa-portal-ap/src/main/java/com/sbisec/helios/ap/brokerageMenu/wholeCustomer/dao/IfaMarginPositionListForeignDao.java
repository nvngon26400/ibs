package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListForeignSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListForeignSql001ResponseModel;





/**
 * 画面ID：SUB020302_0303-01
 * 画面名：信用建玉一覧（米株）
 * @author <author-name>
 * 
 * 2023/11/30 新規作成
 *
 */
public interface IfaMarginPositionListForeignDao {
    
    /**
     * SQLID：Sql001
     * SQL名：顧客口座情報検索
     * SQLタイプ：select
     * リクエストクラス：IfaMarginPositionListForeignSql001RequestModel
     * レスポンスクラス：IfaMarginPositionListForeignSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginPositionListForeignSql001ResponseModel> selectIfaMarginPositionListForeignSql001(IfaMarginPositionListForeignSql001RequestModel req)
            throws Exception;
    
    
    
    
}
