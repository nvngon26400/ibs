package com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.IfaPayNotificationDocDownloadDao;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.mapper.IfaPayNotificationDocDownloadMapper;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql004RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
@Component
public class IfaPayNotificationDocDownloadDaoImpL extends RowSelectableDao implements IfaPayNotificationDocDownloadDao {
    
    @Autowired
    private IfaPayNotificationDocDownloadMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：仲介業者支払通知書格納先ディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaPayNotificationDocDownloadSql001RequestModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPayNotificationDocDownloadSql001ResponseModel> selectIfaPayNotificationDocDownloadSql001()
            throws Exception {
        
        DataList<IfaPayNotificationDocDownloadSql001ResponseModel> res = new DataList<IfaPayNotificationDocDownloadSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaPayNotificationDocDownloadSql001());
        return res;
    }
    
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
            IfaPayNotificationDocDownloadSql002RequestModel req) throws Exception {
        
        DataList<IfaPayNotificationDocDownloadSql002ResponseModel> res = new DataList<IfaPayNotificationDocDownloadSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaPayNotificationDocDownloadSql002(req));
        return res;
    }
    
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
            IfaPayNotificationDocDownloadSql003RequestModel req) throws Exception {
        
        DataList<IfaPayNotificationDocDownloadSql003ResponseModel> res = new DataList<IfaPayNotificationDocDownloadSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaPayNotificationDocDownloadSql003(req));
        return res;
    }
    
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
            throws Exception {
        
        return mapper.updateIfaPayNotificationDocDownloadSql004(req);
    }
    
}
