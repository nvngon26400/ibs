package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

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
public class IfaInfoRegisterViewerSettingSql006RequestModel {
    
    /** 仲介業者選択. */
    private List<String> brokerSelect;
    
    /** 担当者（検索）（全角半角）. */
    private String repSearch;
    
}
