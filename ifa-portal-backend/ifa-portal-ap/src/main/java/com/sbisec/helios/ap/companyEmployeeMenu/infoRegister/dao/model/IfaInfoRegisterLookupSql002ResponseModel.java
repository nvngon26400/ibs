package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Data
public class IfaInfoRegisterLookupSql002ResponseModel {
    
    /** カテゴリ名（全角半角）. */
    private String t9nName;
    
    /** 更新日時. */
    private String updateTime;
    
    /** お知らせID（数字）. */
    private String notificationId;
    
    /** タイトル（全角半角）. */
    private String title;
    
    /** 重要度（数字）. */
    private String t4nImportance;
    
    /** 登録者（全角半角）. */
    private String createBy;
    
    /** 更新者. */
    private String updateUser;
    
    /** 登録日時. */
    private String registerDayTime;
    
    /** 内容. */
    private String contents;
    
    /** 添付ファイル１. */
    private String attachFile1;
    
    /** 添付ファイル２. */
    private String attachFile2;
    
    /** 添付ファイル３. */
    private String attachFile3;
    
    /** 添付ファイルコメント１. */
    private String attachFileComment1;
    
    /** 添付ファイルコメント２. */
    private String attachFileComment2;
    
    /** 添付ファイルコメント３. */
    private String attachFileComment3;
    
    /** URL（英数字記号B(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<')）. */
    private String url;
    
    /** URLコメント. */
    private String urlComment;
    
    /** 参照範囲（数字）. */
    private String corReferenceCondition;
    
    /** ディスクレーマー（半角英数字）. */
    private String disclaimer;
    
    /** 既読管理フラグ（数字）. */
    private String corReadFlg;

    /** ユーザー名. */
    private String userName;
}
