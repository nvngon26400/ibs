package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;





/**
 * 
 * 画面ID：SUB0602-04_1
 * 画面名：ポータルお知らせ更新
 *
 * @author <author-name>
 2024/06/13 新規作成
 */
@Mapper
public interface IfaPortalNotificationUpdateMapper {
    
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
    public List<IfaPortalNotificationUpdateSql001ResponseModel> selectIfaPortalNotificationUpdateSql001(
        @Param("req") IfaPortalNotificationUpdateSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：ポータルお知らせ更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaPortalNotificationUpdateSql002(
        @Param("req")  IfaPortalNotificationUpdateSql002RequestModel req
        ) throws Exception;
}
