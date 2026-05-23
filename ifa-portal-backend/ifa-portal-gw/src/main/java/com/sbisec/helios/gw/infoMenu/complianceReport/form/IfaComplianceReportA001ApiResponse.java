package com.sbisec.helios.gw.infoMenu.complianceReport.form;

import java.util.List;

import lombok.Data;

/**
 * コンプライアンス通信A001リスポンス
 *
 * @author SCSK
 * 
 */
@Data
public class IfaComplianceReportA001ApiResponse {
    
    /** ファイルディレクトリ. */
    private String fileDir;
    
    /** 通信年月. */
    private String corCommunicationDate;
    
    /**コンプライアンス通信リスト. */
    private List<IfaComplianceReportA001SlctYmListApiResponse> slctYmList;
    
    /** 表示年月. */
    private String slctYm;
    
    /** タイトル. */
    private String corTitle;
    
    /** 概要. */
    private String corBrief;
    
    /** コンテンツファイル名. */
    private String corContentsFileName;
    
    /** ファイル名１. */
    private String corFileName1;
    
    /** ファイル名２. */
    private String corFileName2;
    
    /** ファイル名３. */
    private String corFileName3;
    
    /** ファイルのコメント1 */
    private String corFileDesc1;
    
    /** ファイルのコメント2 */
    private String corFileDesc2;
    
    /** ファイルのコメント3 */
    private String corFileDesc3;
    
    private String corContents;
    
    /** 確認フラグ. */
    private String confirmFlg;
    
    /** 閲覧要否フラグ_個別. */
    private String corBrowseFlagInd;
    
    /**　閲覧要否フラグ_一括. */
    private String corBrowseFlagBul;
    
    /** 確認ボタン. */
    private String confirmBtn;
    
}
