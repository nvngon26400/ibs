package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

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
public class IfaInfoUpdateA002ApiResponse {

    /** お知らせカテゴリリスト. */
    private List<IfaInfoUpdateA002ApiResponseNotificationCategoryList> notificationCategoryList;

    /** 資料種別1. */
    private String documentKindInput1;
    
    /** 更新日時. */
    private String updateTime;

    /** タイトル（全角半角）. */
    private String title;

    /** 登録者（全角半角）. */
    private String createBy;

    /** 更新者. */
    private String updateUser;

    /** 登録日時. */
    private String registerDayTime;

    /** 内容. */
    private String contents;
	
    /** 添付ファイル名1. */
    private String attachFileName1;

    /** 添付ファイル名2. */
    private String attachFileName2;

    /** 添付ファイル名3. */
    private String attachFileName3;

    /** 添付ファイルコメント1. */
    private String attachFileComment1;

    /** 添付ファイルコメント2. */
    private String attachFileComment2;

    /** 添付ファイルコメント3. */
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

}
