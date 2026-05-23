package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto;

import lombok.Data;

/**
 * SUB0512 リリースノート
 *
 * @author SBI大連 夏
 * @date 2025/11/10
 */

@Data
public class ContentItem {

    /** リリースノート内容No. */
    private String releaseNoteContentNo;

    /** メニュー名. */
    private String menuName;

    /** 画面名. */
    private String screenName;

    /** 内容. */
    private String content;

}
