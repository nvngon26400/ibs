package com.sbisec.helios.ap.brokerageMenu.customerList.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListMarginSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListMarginSql001ResponseModel;





/**
 * 
 * 画面ID：SUB0201_02-01
 * 画面名：顧客一覧・信用
 * @author <author-name>
 *
 * 2024/01/11 新規作成
 */
@Mapper
public interface IfaCustomerListMarginMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：顧客検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListMarginSql001RequestModel
     * レスポンスクラス：IfaCustomerListMarginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerListMarginSql001ResponseModel> selectIfaCustomerListMarginSql001(
        @Param("req") IfaCustomerListMarginSql001RequestModel req
        ) throws Exception;
    
    
    
    
}
