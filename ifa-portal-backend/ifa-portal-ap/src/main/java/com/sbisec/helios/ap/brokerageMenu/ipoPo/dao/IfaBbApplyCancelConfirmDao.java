package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCancelConfirmSql015RequestModel;


/**
 * 画面ID：SUB0204_02-03_2
 * 画面名：BB申込取消確認
 *
 * @author BASE李
 2024/05/14 新規作成
 *
 */
public interface IfaBbApplyCancelConfirmDao {
    
    
    
    
    
    /**
     * SQLID：Sql001
     * SQL名：BB申込情報取消
     * SQLタイプ：delete
     * リクエストクラス：IfaBbApplyCancelConfirmSql001RequestModel
     * レスポンスクラス：IfaBbApplyCancelConfirmSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaBbApplyCancelConfirmSql001(IfaBbApplyCancelConfirmSql001RequestModel req)
            throws Exception;
    
    
    /**
     * SQLID：Sql015
     * SQL名：BB申込情報登録
     * SQLタイプ：delete
     * リクエストクラス：IfaBbApplyCancelConfirmSql015RequestModel
     * レスポンスクラス：IfaBbApplyCancelConfirmSql015ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaBbApplyCancelConfirmSql015(IfaBbApplyCancelConfirmSql015RequestModel req)
            throws Exception;
}
