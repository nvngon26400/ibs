package com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.IfaPortalNotificationNewRegisterDao;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dao.mapper.IfaPortalNotificationNewRegisterMapper;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.model.IfaPortalNotificationNewRegisterSql001RequestModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * 画面ID：SUB0602-02_1
 * 画面名：ポータルお知らせ新規登録
 *
 * @author BASE 李
 2024/06/11 新規作成
 */
@Component
public class IfaPortalNotificationNewRegisterDaoImpL extends RowSelectableDao implements IfaPortalNotificationNewRegisterDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPortalNotificationNewRegisterDaoImpL.class);
    @Autowired
    private IfaPortalNotificationNewRegisterMapper mapper;
    
    
    
	
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
            throws Exception {

        return mapper.insertIfaPortalNotificationNewRegisterSql001(req);
    }
    
    
}
