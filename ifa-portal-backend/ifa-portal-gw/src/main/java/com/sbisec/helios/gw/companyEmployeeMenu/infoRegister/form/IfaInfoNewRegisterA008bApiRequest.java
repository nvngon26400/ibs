package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0505_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 *
 */
@Data
public class IfaInfoNewRegisterA008bApiRequest {
    
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
    @NotEmpty(message = "閲覧者")
    private String viewerSetting;
    
    /** タイトル（全角半角）. */
    @NotEmpty(message = "タイトル")
    @Size(max = 127, message = "タイトル")
    private String title;
    
    /** カテゴリIDリスト. */
    @NotEmpty(message = "カテゴリIDリスト")
    private List<CategoryId> categoryIdList;
    
    /** 内容. */
    @Size(max = 2000, message = "内容")
    private String content;
    
    /** URL. */
    @Size(max = 500, message = "URL")
    private String url;
    
    /** URL（コメント）（全角半角）. */
    @Size(max = 127, message = "URL（コメント）")
    private String urlComment;
    
    /** 登録ファイル1. */
    private String registerFile1;
    
    /** 登録ファイル1（コメント）. */
    @Size(max = 127, message = "登録ファイル1（コメント）")
    private String fileComment1;
    
    /** 登録ファイル2. */
    private String registerFile2;
    
    /** 登録ファイル2（コメント）. */
    @Size(max = 127, message = "登録ファイル2（コメント）")
    private String fileComment2;
    
    /** 登録ファイル3. */
    private String registerFile3;
    
    /** 登録ファイル3（コメント）. */
    @Size(max = 127, message = "登録ファイル3（コメント）")
    private String fileComment3;
    
    /** ディスクレーマー（半角英数字）. */
    @NotEmpty(message = "ディスクレーマー")
    @Size(min = 1, max = 1, message = "ディスクレーマー")
    private String disclaimer;
    
    /** 既読管理（半角英数字）. */
    @NotEmpty(message = "既読管理")
    @Size(min = 1, max = 1, message = "既読管理")
    private String readManage;
    
    /** 件名（送信しない／送信する）. */
    @NotEmpty(message = "件名（送信しない／送信する）")
    private String subjectSendFlag;
    
    /** 件名（全角半角）. */
    @Size(max = 255, message = "件名")
    private String subject;
    
    /** お知らせ参照権限リスト（全角半角）. */
    private List<String> notificationReferenceAuthorityList;
    
    /** 個別担当者リスト（全角半角）. */
    private List<String> individualRepList;
    
}
