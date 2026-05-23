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
public class IfaInfoRegisterViewerSettingSql003ResponseModel {
    
    /** お知らせID（数字）. */
    private String notificationId;
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** 登録日時. */
    private String registerDayTime;
    
    /** 登録者（全角半角）. */
    private String createBy;
    
    /** 更新日時. */
    private String updateTime;
    
    /** 更新者. */
    private String updateUser;
    
}
