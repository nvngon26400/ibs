package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0501_01-04
 * 画面名：情報登録閲覧者設定
 *
 * @author SCSK 矢口
 2024/05/24 新規作成
 */
@Data
public class IfaInfoRegisterViewerSettingA002DtoResponse {
    
    /** 閲覧者. */
    private String viewerSetting;
    
    /** お知らせ参照権限リスト（全角半角）. */
    private List<String> notificationReferenceAuthorityList;
    
    /** 登録済お知らせ参照権限リスト（全角半角）. */
    private List<String> registeredNotificationReferenceAuthorityList;
    
    /** 個別担当者リスト. */
    private List<IfaInfoRegisterViewerSettingDtoResponseIndividualRep> individualRepList;
    
    /** 登録済個別担当者リスト. */
    private List<IfaInfoRegisterViewerSettingDtoResponseIndividualRep> registeredIndividualRepList;
    
}
