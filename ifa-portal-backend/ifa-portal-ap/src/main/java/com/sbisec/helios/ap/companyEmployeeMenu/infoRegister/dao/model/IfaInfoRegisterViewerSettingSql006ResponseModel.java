package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-04
 * 画面名：情報登録閲覧者設定
 *
 * @author SCSK 矢口
 2024/05/24 新規作成
 */
@Data
public class IfaInfoRegisterViewerSettingSql006ResponseModel {
    
    /** ユーザーID（全角半角）. */
    private String userId;
    
    /** ユーザー名. */
    private String userName;
    
    /** パスワード（英数字記号B(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<')）. */
    private String password;
    
    /** 最後パスワード更新日時. */
    private String lastpwuptime;
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** SBI証券支店コード（数字）. */
    private String sbiSecurityCode;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
    /** 仲介業者担当者コード（数字）. */
    private String employeeId;
    
    /** 仲介業者担当者名（全角半角）. */
    private String employeeName;
    
    /** 管理者フラグ（全角）. */
    private String governorFlag;
    
    /** ログイン状況. */
    private String loginStatus;
    
    /** パートナーサイトログイン用ID. */
    private String partnerUserId;
    
    /** パートナーサイトログイン用パスワード. */
    private String partnerUserPw;
    
    /** CCSログイン用ID. */
    private String ccsUserId;
    
    /** CCSログイン用パスワード. */
    private String ccsUserPw;
    
    /** 作成者. */
    private String createUser;
    
    /** 作成日時. */
    private String createTime;
    
    /** 更新者. */
    private String updateUser;
    
    /** 更新日時. */
    private String updateTime;
    
    /** メールアドレス（メールアドレス）. */
    private String mailAddress;
    
}
