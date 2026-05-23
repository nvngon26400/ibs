package com.sbisec.helios.ap.releaseNote.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.releaseNote.dao.model.IfaReleaseNoteDetailSql002ResponseModel;

/**
 * 画面ID：SUB00-07_2
 * 画面名：リリースノート詳細
 * 2025/11/04 新規作成
 *
 * @author 大連 葉
 */
@Mapper
public interface IfaReleaseNoteDetailMapper {

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
    public List<IfaReleaseNoteDetailSql002ResponseModel> selectIfaReleaseNoteDetailSql002(@Param("releaseNoteNo") String releaseNoteNo) throws Exception;
}
