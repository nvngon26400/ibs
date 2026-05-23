package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao;

import java.util.List;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteUpdateSql005RequestModel;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */

public interface IfaReleaseNoteUpdateDao {

    /**
     * SQLID:SQL001
     * SQL名：リリースノート詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql001RequestModel
     * レスポンスクラス：IfaReleaseNoteUpdateSql001ResponseModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    public List<IfaReleaseNoteUpdateSql001ResponseModel> selectIfaReleaseNoteUpdateSql001(
        IfaReleaseNoteUpdateSql001RequestModel req) throws Exception;

    /**
     * SQLID:SQL002
     * SQL名：リリースノートファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql002RequestModel
     * レスポンスクラス：IfaReleaseNoteUpdateSql002ResponseModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    public IfaReleaseNoteUpdateSql002ResponseModel selectIfaReleaseNoteUpdateSql002(
        IfaReleaseNoteUpdateSql002RequestModel req) throws Exception;

    /**
     * SQLID:SQL003
     * SQL名：リリースノート内容削除
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql003RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    public int deleteIfaReleaseNoteUpdateSql003(IfaReleaseNoteUpdateSql003RequestModel req) throws Exception;

    /**
     * SQLID:SQL004
     * SQL名：リリースノート内容登録
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql004RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    public int insertIfaReleaseNoteUpdateSql004(List<IfaReleaseNoteUpdateSql004RequestModel> req) throws Exception;

    /**
     * SQLID:SQL005
     * SQL名：リリースノート更新
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteUpdateSql005RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    public int updateIfaReleaseNoteUpdateSql005(IfaReleaseNoteUpdateSql005RequestModel req) throws Exception;

}
