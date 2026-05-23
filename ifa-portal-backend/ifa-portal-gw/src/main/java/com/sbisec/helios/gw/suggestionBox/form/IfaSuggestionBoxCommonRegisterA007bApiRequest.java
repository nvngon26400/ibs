package com.sbisec.helios.gw.suggestionBox.form;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望新規登録
 *
 2025/06/26 新規作成
 */

@Data
public class IfaSuggestionBoxCommonRegisterA007bApiRequest {

    /** タイトル */
    @NotEmpty(message = "タイトル")
    private String title;

    /** カテゴリ */
    @NotEmpty(message = "カテゴリ")
    private String category;

    /** ステータス */
    @NotEmpty(message = "ステータス")
    private String status;

    /** 要望内容 */
    @NotEmpty(message = "要望内容")
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
        
    	@NotEmpty(message = "回答一覧.回答確定フラグ")
        /** 回答一覧.回答確定フラグ. */
        private String answerConfirmFlg;

    	@NotEmpty(message = "回答一覧.回答No")
        /** 回答一覧.回答No. */
        private String answerNo;
        
        /** 回答一覧.回答登録日. */
        private String answerRegisterDate;
        
        /** 回答一覧.回答内容. */
        private String answerContents;
        
    }
}

