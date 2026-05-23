package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model;

import lombok.Data;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/10/30
 */

@Data
public class IfaReleaseNoteUpdateSql002RequestModel {

    /** 機能ID. */
    private String functionId;

    /** カテゴリID. */
    private String categoryId;

}
