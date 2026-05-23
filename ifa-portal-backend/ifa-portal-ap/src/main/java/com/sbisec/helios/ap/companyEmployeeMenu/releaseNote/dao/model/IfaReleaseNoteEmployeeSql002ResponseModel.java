package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0512-01
 * リリースノート(社員用) SQL002（リリースノート表示対象年取得） レスポンスパラメタ
 * 2025/12/18 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaReleaseNoteEmployeeSql002ResponseModel {
    /** 最小年数 */
    private String minYear;

    /** 最大年数 */
    private String maxYear;
}
