package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form;

import lombok.Data;

/**
 * SUB0512 リリースノート
 *
 * @author SBI大連 夏
 * @date 2025/11/10
 */

@Data
public class IfaContentCheckItem {

    /** リリースノート内容No. */
    private String releaseNoteContentNo;

    /** メニュー名メッセージ. */
    private String menuNameErrorMessage;

    /** 画面名メッセージ. */
    private String screenNameErrorMessage;

    /** 内容メッセージ. */
    private String ContentErrorMessage;

}
