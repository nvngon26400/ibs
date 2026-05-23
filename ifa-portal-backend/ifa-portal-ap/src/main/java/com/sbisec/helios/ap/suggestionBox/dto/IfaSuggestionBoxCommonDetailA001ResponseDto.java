package com.sbisec.helios.ap.suggestionBox.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB00_02-06_1
 * 画面名：皆様からの要望詳細
 *
 * @author SCSK
 2025/06/19 新規作成
 */

@Data
public class IfaSuggestionBoxCommonDetailA001ResponseDto {
    
    /** タイトル. */
    private String title;
    
    /** 登録日. */
    private String registerDate;
    
    /** ステータス. */
    private String status;
    
    /** 要望内容. */
    private String suggestion;
    
    /** 添付ファイル1. */
    private String attachFile1;
    
    /** 添付ファイル2. */
    private String attachFile2;
    
    /** 添付ファイル3. */
    private String attachFile3;
    
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
        
        /** 回答一覧.回答No. */
        private String answerNo;
        
        /** 回答一覧.回答登録日. */
        private String answerRegisterDate;

        /** 回答一覧.回答更新日. */
        private String answerUpdateDate;
        
        /** 回答一覧.回答内容. */
        private String answerContents;
        
    }
    
}
