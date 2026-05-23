package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql002RequestModel;





/**
 * 画面ID：SUB0602-01
 * 画面名：ポータルお知らせ（管理者用）照会
 *
 * @author <author-name>
 2024/06/06 新規作成
 *
 */
public interface IfaPortalNotificationManagerLookupDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：お知らせ情報一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortalNotificationManagerLookupSql001RequestModel
     * レスポンスクラス：IfaPortalNotificationManagerLookupSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationManagerLookupSql001ResponseModel> selectIfaPortalNotificationManagerLookupSql001(IfaPortalNotificationManagerLookupSql001RequestModel req)
            throws Exception;
    
    
    
    /**
     * SQLID：Sql002
     * SQL名：お知らせ情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaPortalNotificationManagerLookupSql002RequestModel
     * レスポンスクラス：IfaPortalNotificationManagerLookupSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaPortalNotificationManagerLookupSql002(IfaPortalNotificationManagerLookupSql002RequestModel req)
            throws Exception;
}
