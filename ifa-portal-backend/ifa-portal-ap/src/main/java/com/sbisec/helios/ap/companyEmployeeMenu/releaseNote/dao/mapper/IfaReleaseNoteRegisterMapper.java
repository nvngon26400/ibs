package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteRegisterSql004RequestModel;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */

@Mapper
public interface IfaReleaseNoteRegisterMapper {

    /**
     * SQLID:SQL001
     * SQL名：リリースノートNo取得
     * SQLタイプ：select
     * レスポンスクラス：IfaReleaseNoteRegisterSql001ResponseModel
     * 
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    public IfaReleaseNoteRegisterSql001ResponseModel selectIfaReleaseNoteRegisterSql001() throws Exception;

    /**
     * SQLID:SQL002
     * SQL名：リリースノートファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaReleaseNoteRegisterSql002RequestModel
     * レスポンスクラス：IfaReleaseNoteRegisterSql002ResponseModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    public IfaReleaseNoteRegisterSql002ResponseModel selectIfaReleaseNoteRegisterSql002(
        @Param("req") IfaReleaseNoteRegisterSql002RequestModel req) throws Exception;

    /**
     * SQLID:SQL003
     * SQL名：リリースノート登録
     * SQLタイプ：insert
     * リクエストクラス：IfaReleaseNoteRegisterSql003RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    public int insertIfaReleaseNoteRegisterSql003(@Param("req") IfaReleaseNoteRegisterSql003RequestModel req)
        throws Exception;

    /**
     * SQLID:SQL004
     * SQL名：リリースノート内容登録
     * SQLタイプ：insert
     * リクエストクラス：IfaReleaseNoteRegisterSql003RequestModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception システムエラー
     */
    public int insertIfaReleaseNoteRegisterSql004(@Param("req") List<IfaReleaseNoteRegisterSql004RequestModel> req)
        throws Exception;

}
