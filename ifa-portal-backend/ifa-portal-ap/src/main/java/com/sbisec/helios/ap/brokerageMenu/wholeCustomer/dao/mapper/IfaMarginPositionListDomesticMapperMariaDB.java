package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListDomesticSql002ResponseModel;
import com.sbisec.helios.ap.common.annotation.dao.MariadbMapper;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * 画面ID：SUB020302_0302-01
 * 画面名：信用建玉一覧（国内）
 * @author <author-name>
 *
 * 2023/09/07 新規作成
 */
@MariadbMapper
public interface IfaMarginPositionListDomesticMapperMariaDB {
    
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
    public List<IfaMarginPositionListDomesticSql002ResponseModel> selectIfaMarginPositionListDomesticSql002(
            @Param("req") IfaMarginPositionListDomesticSql002RequestModel req) throws Exception;
    
}
