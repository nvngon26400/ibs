package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeTrendSearchSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeTrendSearchSql002ResponseModel;

/**
 * 取引動向検索
 * 2025/04/10 新規作成
 *
 * @author 大連 苗
 */
public interface IfaTradeTrendSearchDao {
    
    /**
     * SQLID：Sql002
     * SQL名：取引動向検索一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTradeTrendSearchSql002RequestModel
     * レスポンスクラス：IfaTradeTrendSearchSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeTrendSearchSql002ResponseModel> selectIfaTradeTrendSearchSql002(
            IfaTradeTrendSearchSql002RequestModel req) throws Exception;

}
