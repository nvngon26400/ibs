package com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql004RequestModel;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 *
 */
public interface IfaPayNotificationDocDownloadDao {
    
    /**
     * SQLID：Sql001
     * SQL名：仲介業者支払通知書格納先ディレクトリ取得
     * SQLタイプ：select
     * レスポンスクラス：IfaPayNotificationDocDownloadSql001ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPayNotificationDocDownloadSql001ResponseModel> selectIfaPayNotificationDocDownloadSql001()
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：仲介業者支払通知書情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaPayNotificationDocDownloadSql002RequestModel
     * レスポンスクラス：IfaPayNotificationDocDownloadSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPayNotificationDocDownloadSql002ResponseModel> selectIfaPayNotificationDocDownloadSql002(
            IfaPayNotificationDocDownloadSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：支払通知書ダウンロードファイル名取得
     * SQLタイプ：select
     * リクエストクラス：IfaPayNotificationDocDownloadSql003RequestModel
     * レスポンスクラス：IfaPayNotificationDocDownloadSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPayNotificationDocDownloadSql003ResponseModel> selectIfaPayNotificationDocDownloadSql003(
            IfaPayNotificationDocDownloadSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：仲介業者支払通知書確認日時更新
     * SQLタイプ：update
     * リクエストクラス：IfaPayNotificationDocDownloadSql004RequestModel
     * レスポンスクラス：IfaPayNotificationDocDownloadSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaPayNotificationDocDownloadSql004(IfaPayNotificationDocDownloadSql004RequestModel req)
            throws Exception;
    
}
