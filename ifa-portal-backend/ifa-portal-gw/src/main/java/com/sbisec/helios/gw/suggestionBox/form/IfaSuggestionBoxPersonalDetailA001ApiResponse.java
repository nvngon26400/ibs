package com.sbisec.helios.gw.suggestionBox.form;

import java.util.List;

import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailA001ResponseDto.answerList;

import lombok.Data;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項詳細
 * 
 * @author SCSK神木
 * 2025/06/23 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailA001ApiResponse {

    /** 要望No. */
    private String sbpNo;
    
    /** タイトル. */
    private String title;
    
    /** カテゴリ. */
    private String category;
    
    /** ステータス. */
    private String status;
    
    /** 要望内容. */
    private String suggestion;
    
    /** 登録済添付ファイル1. */
    private String registeredAttachFile1;
    
    /** 登録済添付ファイル2. */
    private String registeredAttachFile2;
        
    /** 登録済添付ファイル3. */
    private String registeredAttachFile3;
    
    /** 目安箱未読件数. */
    private int sugBoxUnreadItems;
    
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
        
        /** 回答一覧.回答内容. */
        private String answerContents;
        
        /** 回答一覧.作成日時. */
        private String createTime;

        /** 回答一覧.更新日時. */
        private String answerUpdateTime;
        
    }
}
