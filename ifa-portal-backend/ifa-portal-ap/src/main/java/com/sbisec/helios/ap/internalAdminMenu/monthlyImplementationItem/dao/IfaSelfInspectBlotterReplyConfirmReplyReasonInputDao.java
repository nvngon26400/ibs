package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao;

import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel;

/**
 * 画面ID：SUB0401_02-03
 * 画面名：自己点検記録簿回答確認・回答理由入力
 *
 * @author SCSK丹波
 2024/06/05 新規作成
 */
public interface IfaSelfInspectBlotterReplyConfirmReplyReasonInputDao {
    
    /**
     * SQLID：Sql001
     * SQL名：自己点検確認テーブル更新
     * SQLタイプ：update
     * リクエストクラス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001(
            IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel req) throws Exception;
    
}
