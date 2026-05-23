package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

@Data
public class IfaSuggestionBoxCommonSql002ResponseModel {

    /** 総件数. */
    private int totalCount;

    /** 皆様からの要望No. */
    private String sbcNo;

    /** 更新日. */
    private String updateDate;

    /** 登録日. */
    private String createDate;

    /** タイトル. */
    private String title;

    /** カテゴリ. */
    private String category;

    /** ステータス. */
    private String status;

    /** 要望内容. */
    private String suggestion;

}
