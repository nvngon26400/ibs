package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

@Data
public class IfaSuggestionBoxCommonDetailSql001ResponseModel {

    /** タイトル. */
    private String title;

    /** 登録日. */
    private String createDate;

    /** ステータス. */
    private String status;

    /** 要望内容. */
    private String suggestion;

    /** 添付ファイル1. */
    private String file1;

    /** 添付ファイル2. */
    private String file2;

    /** 添付ファイル3. */
    private String file3;


    /** 回答一覧.回答No. */
    private String sbcaNo;

    /** 回答一覧.回答登録日. */
    private String answerDate;

    /** 回答一覧.回答更新日. */
    private String answerUpdateDate;

    /** 回答一覧.回答内容. */
    private String answer;

}
