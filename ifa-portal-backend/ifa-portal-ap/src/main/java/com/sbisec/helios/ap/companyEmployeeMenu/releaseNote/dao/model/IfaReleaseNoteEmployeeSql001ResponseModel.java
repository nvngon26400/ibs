package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0512-01
 * リリースノート(社員用) SQL001（リリースノート一覧取得） レスポンスパラメタ
 * 2025/11/06 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaReleaseNoteEmployeeSql001ResponseModel {

    /** タイトル */
    private String title;

    /** 画面表示開始日 */
    private String displayedDate;

    /** 更新日時 */
    private String updateTime;

    /** 詳細ファイル有無 */
    private String detailedFile;

    /** リリースノートNo */
    private String releaseNoteNo;

    /** メニュー名 */
    private String menuName;

    /** 画面名 */
    private String screenName;

    /** 内容 */
    private String content;

    /** リリースノート内容No */
    private String releaseNoteCoNo;
}
