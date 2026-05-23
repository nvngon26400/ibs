package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCurrencyDealtListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCurrencyDealtListSql001ResponseModel;






/**
 * 画面ID：SUB0202_0502-01 画面名：取扱通貨一覧
 * 
 *
 * @author 池亀緑
 *
 *         2023/08/23 新規作成
 */
public interface IfaCurrencyDealtListDao {
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCurrencyDealtListSql001RequestModel
     * レスポンスクラス：IfaCurrencyDealtListSql001ResponseModel
     *
     * @param req {@code IfaCurrencyDealtListSql001RequestModel }
     * @return {@code DataList <IfaCurrencyDealtListSql001ResponseModel>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCurrencyDealtListSql001ResponseModel> selectIfaCurrencyDealtListSql001(IfaCurrencyDealtListSql001RequestModel req)
            throws Exception;
    
    
    
    
}
