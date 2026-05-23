package com.sbisec.helios.gw.releaseNote.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 画面ID：SUB00-07_1
 * 画面名：リリースノート
 * 2025/10/27 新規作成
 *
 * @author 大連 葉
 */
@Data
@JsonSerialize
public class IfaReleaseNoteA001ApiRequest {

    /** 表示対象年 */
    private String displayYear;
}
