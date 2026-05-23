package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA005ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA006RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA006ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0501_01-05
 * 画面名：お知らせ閲覧状況照会
 *
 * @author BASE 李
 2024/06/13 新規作成
 */
public interface IfaNotificationViewStatusLookupService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaNotificationViewStatusLookupA001RequestDto
     * Dto レスポンス：IfaNotificationViewStatusLookupA001ResponseDto
     * model リクエスト：IfaNotificationViewStatusLookupA001RequestModel
     * model レスポンス：IfaNotificationViewStatusLookupA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupA001ResponseDto> initializeA001(IfaNotificationViewStatusLookupA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A003
     * アクション名：代理開封OK
     * Dto リクエスト：IfaNotificationViewStatusLookupA003RequestDto
     * Dto レスポンス：IfaNotificationViewStatusLookupA003ResponseDto
     * model リクエスト：IfaNotificationViewStatusLookupA003RequestModel
     * model レスポンス：IfaNotificationViewStatusLookupA003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupA003ResponseDto> proxyOpeningOkA003(IfaNotificationViewStatusLookupA003RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A005
     * アクション名：削除OK
     * Dto リクエスト：IfaNotificationViewStatusLookupA005RequestDto
     * Dto レスポンス：IfaNotificationViewStatusLookupA005ResponseDto
     * model リクエスト：IfaNotificationViewStatusLookupA005RequestModel
     * model レスポンス：IfaNotificationViewStatusLookupA005ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupA005ResponseDto> deleteOkA005(IfaNotificationViewStatusLookupA005RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dto リクエスト：IfaNotificationViewStatusLookupA006RequestDto
     * Dto レスポンス：IfaNotificationViewStatusLookupA006ResponseDto
     * model リクエスト：IfaNotificationViewStatusLookupSql001RequestModel、IfaNotificationViewStatusLookupSql002RequestModel、IfaNotificationViewStatusLookupSql003RequestModel
     * model レスポンス：IfaNotificationViewStatusLookupSql001ResponseModel、IfaNotificationViewStatusLookupSql002ResponseModel、IfaNotificationViewStatusLookupSql003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupA006ResponseDto> csvDownloadA006a(IfaNotificationViewStatusLookupA006RequestDto dtoReq,
            String fwSessionId) throws Exception;
}
