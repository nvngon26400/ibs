package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Data
public class IfaInfoUpdateA008bApiRequest {
    
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
    @NotEmpty(message = "お知らせID")
    @Pattern(regexp = "0-9", message = "お知らせID")
    @Size(max = 10, message = "お知らせID")
    private String notificationId;

    /** 閲覧者. */
    @NotEmpty(message = "閲覧者")
    private String viewerSetting;

    /** タイトル（全角半角）. */
    @NotEmpty(message = "タイトル")
    @Size(max = 255, message = "タイトル")
    private String title;

    /** カテゴリIDリスト. */
    @NotEmpty(message = "カテゴリIDリスト")
    private List<CategoryId> categoryIdList;

    /** 内容. */
    private String contents;

    /** URL. */
    @Size(max = 500, message = "URL")
    private String url;

    /** URL（コメント）（全角半角）. */
    @Size(max = 255, message = "URL（コメント）")
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
    @NotEmpty(message = "ディスクレーマー")
    @Size(min = 1, max = 1, message = "ディスクレーマー")
    private String disclaimer;

    /** 既読管理（半角英数字）. */
    @NotEmpty(message = "既読管理")
    @Size(min = 1, max = 1, message = "既読管理")
    private String readManage;

    /** 既読フラグ（数字）. */
    @Digits(integer = 1, fraction = 0, message = "既読フラグ")
    @Pattern(regexp = "0-9", message = "既読フラグ")
    @Size(max = 1, message = "既読フラグ")
    private String t5nReadFlg;

    /** お知らせ参照権限リスト（全角半角）. */
    private List<String> notificationReferenceAuthorityList;

    /** 個別担当者リスト（全角半角）. */
    private List<String> individualRepList;

    /** ファイルディレクトリ. */
    private String fileDirectory;

}
