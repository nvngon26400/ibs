package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002ResponseModel;

/**
 * 画面ID：SUB020302_0302-01
 * 画面名：信用建玉一覧（国内）
 * @author <author-name>
 * 
 * 2023/09/07 新規作成
 *
 */
public interface IfaMarginPositionListDomesticDao {
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaMarginPositionListDomesticSql001RequestModel
     * レスポンスクラス：IfaMarginPositionListDomesticSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginPositionListDomesticSql001ResponseModel> selectIfaMarginPositionListDomesticSql001(
            IfaMarginPositionListDomesticSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaMarginPositionListDomesticSql002RequestModel
     * レスポンスクラス：IfaMarginPositionListDomesticSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginPositionListDomesticSql002ResponseModel> selectIfaMarginPositionListDomesticSql002(
            IfaMarginPositionListDomesticSql002RequestModel req) throws Exception;
    
}
