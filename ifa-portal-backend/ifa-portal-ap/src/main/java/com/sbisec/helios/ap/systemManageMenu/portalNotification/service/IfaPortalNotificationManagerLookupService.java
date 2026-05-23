package com.sbisec.helios.ap.systemManageMenu.portalNotification.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA008RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationManagerLookupA008ResponseDto;

/**
 * 画面ID：SUB0602-01
 * 画面名：ポータルお知らせ（管理者用）照会
 *
 * @author BASE 李
 2024/06/06 新規作成
 */
public interface IfaPortalNotificationManagerLookupService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaPortalNotificationManagerLookupA002RequestDto
     * Dto レスポンス：IfaPortalNotificationManagerLookupA002ResponseDto
     * model リクエスト：IfaPortalNotificationManagerLookupA002RequestModel
     * model レスポンス：IfaPortalNotificationManagerLookupA002ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationManagerLookupA002ResponseDto> displayA002(IfaPortalNotificationManagerLookupA002RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A008
     * アクション名：ポータルお知らせ削除確認OK
     * Dto リクエスト：IfaPortalNotificationManagerLookupA008RequestDto
     * Dto レスポンス：IfaPortalNotificationManagerLookupA008ResponseDto
     * model リクエスト：IfaPortalNotificationManagerLookupA008RequestModel
     * model レスポンス：IfaPortalNotificationManagerLookupA008ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationManagerLookupA008ResponseDto> portalNoticeDeletionConfirmOkA008(IfaPortalNotificationManagerLookupA008RequestDto dtoReq)
            throws Exception;
}
