package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import javax.validation.constraints.NotEmpty;
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
public class IfaInfoRegisterViewerSettingA016ApiRequest {
    
    /** 担当者（検索）（全角半角）. */
    @NotEmpty(message = "担当者（検索）")
    @Size(max = 80, message = "担当者（検索）")
    private String repSearch;
    
}
