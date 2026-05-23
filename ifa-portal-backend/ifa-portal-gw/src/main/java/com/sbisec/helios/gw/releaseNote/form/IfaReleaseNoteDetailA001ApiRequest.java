package com.sbisec.helios.gw.releaseNote.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 画面ID：SUB00-07_2
 * 画面名：リリースノート詳細
 * 2025/11/04 新規作成
 *
 * @author 大連 葉
 */
@Data
@JsonSerialize
public class IfaReleaseNoteDetailA001ApiRequest {

    /** リリースノートNo */
    private String releaseNoteNo;
}
