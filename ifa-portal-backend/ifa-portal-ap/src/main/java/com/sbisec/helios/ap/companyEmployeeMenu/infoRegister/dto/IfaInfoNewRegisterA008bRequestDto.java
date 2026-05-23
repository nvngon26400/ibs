package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import java.util.List;

import lombok.Data;

/**
* 画面ID：SUB0501_01-02_1
* 画面名：情報新規登録A008b要求
*
* @author SCSK
*/
@Data
public class IfaInfoNewRegisterA008bRequestDto {
    
    /**
     * カテゴリID
     *
     * @author SCSK
     *
     */
    @Data
    public static class CategoryId {
        
        /** カテゴリIDリスト.カテゴリID. */
        private String categoryId;
        
        /** カテゴリIDリスト.必須フラグ. */
        private String requiredFlag;

        /** カテゴリIDリスト.カテゴリ名. */
        private String categoryName;
        
    }
    
    /** 閲覧者. */
    private String viewerSetting;
    
    /** タイトル（全角半角）. */
    private String title;
    
    /** カテゴリIDリスト. */
    private List<CategoryId> categoryIdList;
    
    /** 内容. */
    private String content;
    
    /** URL. */
    private String url;
    
    /** URL（コメント）（全角半角）. */
    private String urlComment;
    
    /** 登録ファイル1. */
    private String registerFile1;
    
    /** 登録ファイル1（コメント）. */
    private String fileComment1;
    
    /** 登録ファイル2. */
    private String registerFile2;
    
    /** 登録ファイル2（コメント）. */
    private String fileComment2;
    
    /** 登録ファイル3. */
    private String registerFile3;
    
    /** 登録ファイル3（コメント）. */
    private String fileComment3;
    
    /** ディスクレーマー（半角英数字）. */
    private String disclaimer;
    
    /** 既読管理（半角英数字）. */
    private String readManage;
    
    /** 件名（送信しない／送信する）. */
    private String subjectSendFlag;
    
    /** 件名（全角半角）. */
    private String subject;
    
    /** お知らせ参照権限リスト（全角半角）. */
    private List<String> notificationReferenceAuthorityList;
    
    /** 個別担当者リスト（全角半角）. */
    private List<String> individualRepList;
    
}
