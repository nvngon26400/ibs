package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCurrencyDealtListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCurrencyDealtListSql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_0502-01 画面名：取扱通貨一覧
 * 
 *
 * @author 池亀緑
 *
 *         2023/08/23 新規作成
 */
@Mapper
public interface IfaCurrencyDealtListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCurrencyDealtListSql001RequestModel
     * レスポンスクラス：IfaCurrencyDealtListSql001ResponseModel
     *
     * @param req {@code IfaCurrencyDealtListSql001RequestModel }
     * @return {@code IfaCurrencyDealtListSql001ResponseModel}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public IfaCurrencyDealtListSql001ResponseModel selectIfaCurrencyDealtListSql001(
            @Param("req") IfaCurrencyDealtListSql001RequestModel req) throws Exception;
    
}
