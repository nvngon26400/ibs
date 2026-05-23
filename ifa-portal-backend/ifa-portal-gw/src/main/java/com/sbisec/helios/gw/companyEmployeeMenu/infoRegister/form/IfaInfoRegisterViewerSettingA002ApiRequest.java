package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-04
 * 画面名：情報登録閲覧者設定
 *
 * @author SCSK 矢口
 2024/05/24 新規作成
 */
@Data
public class IfaInfoRegisterViewerSettingA002ApiRequest {
    
    /** お知らせID（数字）. */
    @NotEmpty(message = "お知らせID")
    @Pattern(regexp = "0-9", message = "お知らせID")
    @Size(max = 10, message = "お知らせID")
    private String notificationId;
    
    /** 参照範囲（数字）. */
    @NotEmpty(message = "参照範囲")
    @Pattern(regexp = "0-9", message = "参照範囲")
    @Size(min = 1, max = 1, message = "参照範囲")
    private String corReferenceCondition;
    
    /** お知らせ参照権限リスト（全角半角）. */
    private List<String> notificationReferenceAuthorityList;
    
    /** 個別担当者リスト. */
    private List<IfaInfoRegisterViewerSettingApiResponseIndividualRep> individualRepList;
    
}
