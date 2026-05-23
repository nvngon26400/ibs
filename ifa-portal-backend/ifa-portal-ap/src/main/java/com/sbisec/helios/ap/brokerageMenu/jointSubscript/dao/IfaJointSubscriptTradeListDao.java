package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTradeListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTradeListSql001ResponseModel;



/**
 * 画面ID：SUB0206_02-01
 * 画面名：共同募集　取引検索
 *
 * @author SBIチョウ
   2024/12/12 新規作成
 */
public interface IfaJointSubscriptTradeListDao {
    
    /**
     * SQLID：Sql001
     * SQL名：共同募集　取引一覧情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptTradeListSql001RequestModel
     * レスポンスクラス：IfaJointSubscriptTradeListSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTradeListSql001ResponseModel> selectIfaJointSubscriptTradeListSql001(IfaJointSubscriptTradeListSql001RequestModel req)
            throws Exception;

}
