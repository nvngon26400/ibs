package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.IfaPortalNotificationUpdateDao;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.mapper.IfaPortalNotificationUpdateMapper;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.model.IfaPortalNotificationUpdateSql002RequestModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





/**
 * 画面ID：SUB0602-04_1
 * 画面名：ポータルお知らせ更新
 *
 * @author BASE 李
 2024/06/13 新規作成
 */
@Component
public class IfaPortalNotificationUpdateDaoImpL extends RowSelectableDao implements IfaPortalNotificationUpdateDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPortalNotificationUpdateDaoImpL.class);
    @Autowired
    private IfaPortalNotificationUpdateMapper mapper;
    
	
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
            throws Exception {
        
        DataList<IfaPortalNotificationUpdateSql001ResponseModel> res = new DataList<IfaPortalNotificationUpdateSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortalNotificationUpdateSql001(req));
        return res;
    }
    
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
            throws Exception {

        return mapper.updateIfaPortalNotificationUpdateSql002(req);
    }
}
