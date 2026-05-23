package com.sbisec.helios.ap.suggestionBox.dto;

import lombok.Data;
import java.util.List;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 *
 2025/06/23 新規作成
 */


@Data
public class IfaSuggestionBoxCommonRegisterA007bRequestDto {

    /** タイトル */
    private String title;

    /** カテゴリ */
    private String category;

    /** ステータス */
    private String status;

    /** 要望内容 */
    private String suggestion;

    /** 皆様からの要望No */
    private String sbcNo;

    /** 添付ファイル1（リネーム後ファイル名） */
    private String registerFileName1;

    /** 添付ファイル2（リネーム後ファイル名） */
    private String registerFileName2;

    /** 添付ファイル3（リネーム後ファイル名） */
    private String registerFileName3;

    /** 回答一覧. */
    private List<answerList> answerList;

    /**
     * 回答一覧
     *
     * @author SCSK
     *
     */
    @Data
    public static class answerList {
        
        /** 回答一覧.回答確定フラグ. */
        private String answerConfirmFlg;

        /** 回答一覧.回答No. */
        private String answerNo;
        
        /** 回答一覧.回答登録日. */
        private String answerRegisterDate;
        
        /** 回答一覧.回答内容. */
        private String answerContents;
        
    }

}
