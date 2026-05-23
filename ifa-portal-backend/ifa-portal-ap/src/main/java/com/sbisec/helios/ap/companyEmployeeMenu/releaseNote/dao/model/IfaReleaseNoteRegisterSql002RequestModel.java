package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model;

import lombok.Data;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */

@Data
public class IfaReleaseNoteRegisterSql002RequestModel {

    /** 機能ID. */
    private String functionId;

    /** カテゴリID. */
    private String categoryId;
}
