package com.sbisec.helios.ap.releaseNote.dto;

import java.util.List;

import com.sbisec.helios.ap.releaseNote.dao.model.IfaReleaseNoteSql002ResponseModel;

import lombok.Data;

/**
 * 画面ID：SUB00-07_1
 * 画面名：リリースノート
 * 2025/10/27 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaReleaseNoteA001ResponseDto {

    /** 次回表示フラグ */
    private String nextDispFlg;

    /** リリースノート内容 */
    private List<IfaReleaseNoteSql002ResponseModel> ifaReleaseNoteList;

    /** 最小年数 */
    private String minYear;
}
