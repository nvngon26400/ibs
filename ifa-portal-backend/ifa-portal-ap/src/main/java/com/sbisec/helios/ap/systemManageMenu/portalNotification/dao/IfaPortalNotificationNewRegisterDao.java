package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao;

import com.sbisec.helios.ap.systemManageMenu.portalNotification.model.IfaPortalNotificationNewRegisterSql001RequestModel;



/**
 * 画面ID：SUB0602-02_1
 * 画面名：ポータルお知らせ新規登録
 *
 * @author <author-name>
 2024/06/11 新規作成
 *
 */
public interface IfaPortalNotificationNewRegisterDao {
    
    
    
	
    /**
     * SQLID：Sql001
     * SQL名：お知らせ情報登録
     * SQLタイプ：insert
     * リクエストクラス：IfaPortalNotificationNewRegisterSql001RequestModel
     * レスポンスクラス：IfaPortalNotificationNewRegisterSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaPortalNotificationNewRegisterSql001(IfaPortalNotificationNewRegisterSql001RequestModel req)
            throws Exception;
    
    
}
