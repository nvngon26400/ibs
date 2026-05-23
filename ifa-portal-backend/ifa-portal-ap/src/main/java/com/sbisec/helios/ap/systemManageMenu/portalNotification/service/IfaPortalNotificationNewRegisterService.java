package com.sbisec.helios.ap.systemManageMenu.portalNotification.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationNewRegisterA007RequestDto;
import com.sbisec.helios.ap.systemManageMenu.portalNotification.dto.IfaPortalNotificationNewRegisterA007ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0602-02_1
 * 画面名：ポータルお知らせ新規登録
 *
 * @author BASE 李
 2024/06/11 新規作成
 */
public interface IfaPortalNotificationNewRegisterService extends Service {

    /**
     * アクションID：A007
     * アクション名：ポータルお知らせ新規登録OK
     * Dto リクエスト：IfaPortalNotificationNewRegisterA007RequestDto
     * Dto レスポンス：IfaPortalNotificationNewRegisterA007ResponseDto
     * model リクエスト：IfaPortalNotificationNewRegisterA007RequestModel
     * model レスポンス：IfaPortalNotificationNewRegisterA007ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPortalNotificationNewRegisterA007ResponseDto> portalNoticeNewRegistrationOkA007(IfaPortalNotificationNewRegisterA007RequestDto dtoReq)
            throws Exception;

}
