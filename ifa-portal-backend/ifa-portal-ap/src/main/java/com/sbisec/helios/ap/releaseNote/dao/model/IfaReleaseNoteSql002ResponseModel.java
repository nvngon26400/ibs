package com.sbisec.helios.ap.releaseNote.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00-07_2
 * リリースノート詳細 SQL002（リリースノート詳細ファイル取得） レスポンスパラメタ
 * 2025/10/24 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaReleaseNoteSql002ResponseModel {

    /** タイトル */
    private String title;

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
