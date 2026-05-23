package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 画面ID：SUB0512-01
 * 画面名：リリースノート(社員用)
 * 2025/11/06 新規作成
 *
 * @author 大連 葉
 */
@Data
@JsonSerialize
public class IfaReleaseNoteEmployeeA001ApiRequest {

    /** 表示対象年 */
    private String displayYear;
}
