package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteEmployeeSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteEmployeeSql002ResponseModel;

/**
 * 画面ID：SUB0512-01
 * 画面名：リリースノート(社員用)
 * 2025/11/06 新規作成
 *
 * @author 大連 葉
 */
@Mapper
public interface IfaReleaseNoteEmployeeMapper {

    /**
     * SQLID：Sql001
     * SQL名：リリースノート一覧取得
     * SQLタイプ：select
     * リクエストクラス：String
     * レスポンスクラス：IfaReleaseNoteEmployeeSql001ResponseModel
     *
     * @param displayYear 画面表示開始日
     * @return レスポンスパラメタ
     * @exception Exception 一覧取得処理で例外が発生した場合
     */
    public List<IfaReleaseNoteEmployeeSql001ResponseModel> selectIfaReleaseNoteEmployeeSql001(@Param("displayYear") String displayYear) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：リリースノート表示対象年取得
     * SQLタイプ：select
     * レスポンスクラス：IfaReleaseNoteEmployeeSql002ResponseModel
     *
     * @return レスポンスパラメタ
     * @exception Exception 一覧取得処理で例外が発生した場合
     */
    public List<IfaReleaseNoteEmployeeSql002ResponseModel> selectIfaReleaseNoteDisplayYearSql002() throws Exception;
}
