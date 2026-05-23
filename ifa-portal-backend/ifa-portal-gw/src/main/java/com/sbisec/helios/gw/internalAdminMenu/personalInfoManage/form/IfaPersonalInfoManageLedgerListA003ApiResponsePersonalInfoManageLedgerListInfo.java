package com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form;

import lombok.Data;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎辰弥
    2023/12/19 新規作成
 */

@Data
public class IfaPersonalInfoManageLedgerListA003ApiResponsePersonalInfoManageLedgerListInfo {

    /** 処理日時. */
    private String processDayTime;
    
    /** 名前. */
    private String name;
    
    /** 住所（全角半角）. */
    private String adress;
    
    /** TEL. */
    private String tel;
    
    /** 勤務先. */
    private String office;
    
    /** 生年月日. */
    private String corBirthFlg;
    
    /** 性別. */
    private String gender;
    
    /** 資産. */
    private String assets;
    
    /** 職業. */
    private String profession;
    
    /** 部店口座. */
    private String butenAccount;
    
    /** Ｅメール. */
    private String email;
    
    /** 出金口座. */
    private String withdrawAccount;
    
    /** 書類名・ファイル名（全角半角）. */
    private String docNameFileName;
    
    /** 取得データ顧客数（数値(整数)）. */
    private String acquireDataCustomerCount;
    
    /** 処理内容（全角半角）. */
    private String processContent;
    
    /** 個人情報取得者（全角半角）. */
    private String personalInfoAcquire;
    
    /** ログインID（英数字記号A(+-_./@*#%)）. */
    private String loginId;
    
    /** 担当者名（全角半角）. */
    private String chargeName;
    
    /** 保管/送付媒体. */
    private String storageSendingMedium;
    
    /** 預託先. */
    private String depositDestinations;
    
    /** 提供先. */
    private String destination;
    
    /** 保管場所. */
    private String storageSpace;
    
    /** 保管期間（全角半角）. */
    private String preservePeriod;
    
    /** 廃棄方法. */
    private String disposeMethod;
    
    /** 破棄しない. */
    private String notDispose;
    
    /** 廃棄した年月日. */
    private String disposeDateYmd;
    
    /** 摘要(預託先詳細)（全角半角）. */
    private String corDepositOutline;
    
    /** 摘要(提供先詳細)（全角半角）. */
    private String corDonationOutline;
    
    /** 摘要(保管場所詳細)（全角半角）. */
    private String corDepositoryOutline;
    
    /** 個人情報管理ID（全角半角）. */
    private String personalInfoManageId;

}
