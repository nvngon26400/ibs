package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql001ResponseModel;


/**
 * 
 * 画面ID：SUB020302_0102-01
 * 画面名：国内株当日約定一覧
 * @author <author-name>
 *
 * 2023/11/21 新規作成
 */
@Mapper
public interface IfaTodayTradeListMapper {
    
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
    public List<IfaTodayTradeListSql001ResponseModel> selectIfaTodayTradeListSql001(
        @Param("req") IfaTodayTradeListSql001RequestModel req
        ) throws Exception;
    

}
