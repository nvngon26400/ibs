package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.IfaPortalNotificationManagerLookupDao;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.mapper.IfaPortalNotificationManagerLookupMapper;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationManagerLookupSql002RequestModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





/**
 * 画面ID：SUB0602-01
 * 画面名：ポータルお知らせ（管理者用）照会
 *
 * @author BASE 李
 2024/06/06 新規作成
 */
@Component
public class IfaPortalNotificationManagerLookupDaoImpL extends RowSelectableDao implements IfaPortalNotificationManagerLookupDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPortalNotificationManagerLookupDaoImpL.class);
    @Autowired
    private IfaPortalNotificationManagerLookupMapper mapper;
    
	
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
            throws Exception {
        
        DataList<IfaPortalNotificationManagerLookupSql001ResponseModel> res = new DataList<IfaPortalNotificationManagerLookupSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortalNotificationManagerLookupSql001(req));
        return res;
    }
    
    
    
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
            throws Exception {

        return mapper.deleteIfaPortalNotificationManagerLookupSql002(req);
    }
}
