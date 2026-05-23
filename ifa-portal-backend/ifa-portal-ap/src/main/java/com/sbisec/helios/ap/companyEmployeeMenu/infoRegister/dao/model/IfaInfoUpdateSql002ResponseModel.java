package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.Data;
/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Data
public class IfaInfoUpdateSql002ResponseModel {

    /** お知らせID（数字）. */
    private String notificationId;

    /** タイトル（全角半角）. */
    private String title;

    /** 内容. */
    private String contents;

    /** 登録日時. */
    private String registerDayTime;

    /** 登録時の会員数（数字）. */
    private String t4nMemNum;

    /** 添付ファイル１. */
    private String attachFileName1;

    /** 添付ファイル２. */
    private String attachFileName2;

    /** 添付ファイル３. */
    private String attachFileName3;

    /** 資料種別１. */
    private String documentKindInput1;

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

    /** 登録者（全角半角）. */
    private String createBy;

    /** 更新日時. */
    private String updateTime;

    /** 更新者. */
    private String updateUser;

}
