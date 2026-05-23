package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002ResponseModel;

/**
 * 
 * 画面ID：SUB020302_0302-01
 * 画面名：信用建玉一覧（国内）
 * @author <author-name>
 *
 * 2023/09/07 新規作成
 */
@Mapper
public interface IfaMarginPositionListDomesticMapper {
    
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
    public List<IfaMarginPositionListDomesticSql001ResponseModel> selectIfaMarginPositionListDomesticSql001(
            @Param("req") IfaMarginPositionListDomesticSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaMarginPositionListDomesticSql003RequestModel
     * レスポンスクラス：IfaMarginPositionListDomesticSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    //    public List<IfaMarginPositionListDomesticSql003ResponseModel> selectIfaMarginPositionListDomesticSql003(
    //        @Param("req") IfaMarginPositionListDomesticSql003RequestModel req
    //        ) throws Exception;
    //    
    
}
