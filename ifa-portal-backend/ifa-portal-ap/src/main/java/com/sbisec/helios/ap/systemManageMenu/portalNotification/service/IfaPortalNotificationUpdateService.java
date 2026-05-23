package com.sbisec.helios.ap.systemManageMenu.portalNotification.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA004RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA004ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA006RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA006ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA007RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationUpdateA007ResponseDto;

/**
 * 画面ID：SUB0602-04_1
 * 画面名：ポータルお知らせ更新
 *
 * @author 松尾
 2024/06/13 新規作成
 */
public interface IfaPortalNotificationUpdateService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaPortalNotificationUpdateA001RequestDto
     * Dto レスポンス：IfaPortalNotificationUpdateA001ResponseDto
     * model リクエスト：IfaPortalNotificationUpdateA001RequestModel
     * model レスポンス：IfaPortalNotificationUpdateA001ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationUpdateA001ResponseDto> initializeA001(IfaPortalNotificationUpdateA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A004
     * アクション名：リセット
     * Dto リクエスト：IfaPortalNotificationUpdateA004RequestDto
     * Dto レスポンス：IfaPortalNotificationUpdateA004ResponseDto
     * model リクエスト：IfaPortalNotificationUpdateA004RequestModel
     * model レスポンス：IfaPortalNotificationUpdateA004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationUpdateA004ResponseDto> resetA004(IfaPortalNotificationUpdateA004RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A006
     * アクション名：更新
     * Dto リクエスト：IfaPortalNotificationUpdateA006RequestDto
     * Dto レスポンス：IfaPortalNotificationUpdateA006ResponseDto
     * model リクエスト：IfaPortalNotificationUpdateA006RequestModel
     * model レスポンス：IfaPortalNotificationUpdateA006ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationUpdateA006ResponseDto> updateA006(IfaPortalNotificationUpdateA006RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A007
     * アクション名：ポータルお知らせ更新確認OK
     * Dto リクエスト：IfaPortalNotificationUpdateA007RequestDto
     * Dto レスポンス：IfaPortalNotificationUpdateA007ResponseDto
     * model リクエスト：IfaPortalNotificationUpdateA007RequestModel
     * model レスポンス：IfaPortalNotificationUpdateA007ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationUpdateA007ResponseDto> portalNoticeUpdateConfirmOkA007(IfaPortalNotificationUpdateA007RequestDto dtoReq)
            throws Exception;
}
