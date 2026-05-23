package com.sbisec.helios.ap.releaseNote.dao;

import org.apache.ibatis.annotations.Param;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.releaseNote.dao.model.IfaReleaseNoteDetailSql002ResponseModel;

/**
 * 画面ID：SUB00-07_2
 * 画面名：リリースノート詳細
 * 2025/11/04 新規作成
 *
 * @author 大連 葉
 */
public interface IfaReleaseNoteDetailDao {

    /**
     * SQLID：Sql001
     * SQL名：リリースノートファイルディレクトリ取得
     * SQLタイプ：select
     * レスポンスクラス：String
     *
     * @return ファイルディレクトリ
     * @exception Exception ファイルディレクトリ取得処理で例外が発生した場合
     */
    public String selectIfaReleaseNoteFileDirectorySql001() throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：リリースノート詳細ファイル取得
     * SQLタイプ：select
     * リクエストクラス：String
     * レスポンスクラス：IfaReleaseNoteDetailSql002ResponseModel
     *
     * @param releaseNoteNo リリースノートNo
     * @return レスポンスパラメタ
     * @exception Exception ファイルディレクトリ取得処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteDetailSql002ResponseModel> selectIfaReleaseNoteDetailSql002(String releaseNoteNo) throws Exception;
}
