package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Data
public class IfaInfoUpdateA008bDtoRequest {
    
    /**
     *  カテゴリIDリスト
     *
     */
    @Data
    public static class CategoryId {
        
        /** カテゴリIDリスト.カテゴリID. */
        private String categoryId;
        
        /** カテゴリIDリスト.必須フラグ. */
        private String requiredFlag;
        
    }

    /** お知らせID（数字）. */
    private String notificationId;

    /** 閲覧者. */
    private String viewerSetting;

    /** タイトル（全角半角）. */
    private String title;

    /** カテゴリIDリスト. */
    private List<CategoryId> categoryIdList;

    /** 内容. */
    private String contents;

    /** URL. */
    private String url;

    /** URL（コメント）（全角半角）. */
    private String urlComment;

    /** 登録ファイル1（ファイル名）. */
    private String registerFileName1;

    /** 登録ファイル1（削除）. */
    private String fileDelete1;

    /** 登録ファイル1（コメント）. */
    private String fileComment1;

    /** 登録ファイル2（ファイル名）. */
    private String registerFileName2;

    /** 登録ファイル2（削除）. */
    private String fileDelete2;

    /** 登録ファイル2（コメント）. */
    private String fileComment2;

    /** 登録ファイル3（ファイル名）. */
    private String registerFileName3;

    /** 登録ファイル3（削除）. */
    private String fileDelete3;

    /** 登録ファイル3（コメント）. */
    private String fileComment3;

    /** ディスクレーマー（半角英数字）. */
    private String disclaimer;

    /** 既読管理（半角英数字）. */
    private String readManage;

    /** 既読フラグ（数字）. */
    private String t5nReadFlg;

    /** お知らせ参照権限リスト（全角半角）. */
    private List<String> notificationReferenceAuthorityList;

    /** 個別担当者リスト（全角半角）. */
    private List<String> individualRepList;

}
