package com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA001DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA001DtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA002DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA002DtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA003aDtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA003aDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
public interface IfaPayNotificationDocDownloadService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaPayNotificationDocDownloadA001DtoRequest
     * DTO レスポンス：IfaPayNotificationDocDownloadA001DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPayNotificationDocDownloadA001DtoResponse> initializeA001(
            IfaPayNotificationDocDownloadA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaPayNotificationDocDownloadA002DtoRequest
     * DTO レスポンス：IfaPayNotificationDocDownloadA002DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPayNotificationDocDownloadA002DtoResponse> displayA002(
            IfaPayNotificationDocDownloadA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A003a
     * アクション名：ダウンロード
     * DTO リクエスト：IfaPayNotificationDocDownloadA003aDtoRequest
     * DTO レスポンス：IfaPayNotificationDocDownloadA003aDtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPayNotificationDocDownloadA003aDtoResponse> pdfDownloadA003a(
            IfaPayNotificationDocDownloadA003aDtoRequest dtoReq) throws Exception;
}
