package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;





/**
 * 
 * 画面ID：SUB0602-01
 * 画面名：ポータルお知らせ（管理者用）照会
 *
 * @author <author-name>
 2024/06/06 新規作成
 */
@Mapper
public interface IfaPortalNotificationManagerLookupMapper {
    
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
    public List<IfaPortalNotificationManagerLookupSql001ResponseModel> selectIfaPortalNotificationManagerLookupSql001(
        @Param("req") IfaPortalNotificationManagerLookupSql001RequestModel req
        ) throws Exception;
    
    
    
    /**
     * SQLID：Sql002
     * SQL名：お知らせ情報削除
     * SQLタイプ：delete
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaPortalNotificationManagerLookupSql002(
        @Param("req")  IfaPortalNotificationManagerLookupSql002RequestModel req
        ) throws Exception;
}
