package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql002RequestModel;





/**
 * 画面ID：SUB0602-04_1
 * 画面名：ポータルお知らせ更新
 *
 * @author <author-name>
 2024/06/13 新規作成
 *
 */
public interface IfaPortalNotificationUpdateDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：ポータルお知らせ取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortalNotificationUpdateSql001RequestModel
     * レスポンスクラス：IfaPortalNotificationUpdateSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationUpdateSql001ResponseModel> selectIfaPortalNotificationUpdateSql001(IfaPortalNotificationUpdateSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：ポータルお知らせ更新
     * SQLタイプ：update
     * リクエストクラス：IfaPortalNotificationUpdateSql002RequestModel
     * レスポンスクラス：IfaPortalNotificationUpdateSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaPortalNotificationUpdateSql002(IfaPortalNotificationUpdateSql002RequestModel req)
            throws Exception;
}
