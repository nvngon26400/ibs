package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model;

import lombok.Data;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/10/30
 */

@Data
public class IfaReleaseNoteUpdateSql004RequestModel {

    /** リリースノートNo. */
    private String releaseNoteNo;

    /** リリースノート内容No. */
    private String releaseNoteContentNo;

    /** メニュー名. */
    private String menuName;

    /** 画面名. */
    private String screenName;

    /** 内容. */
    private String content;

    /** 作成日時. */
    private String createTime;

    /** 作成者. */
    private String createUser;

    /** 更新日時. */
    private String updateTime;

    /** 更新者. */
    private String updateUser;

}
