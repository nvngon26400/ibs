package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

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
public class IfaInfoRegisterViewerSettingA009ApiResponse {
    
    /** 仲介業者リスト. */
    private List<IfaInfoRegisterViewerSettingA009ApiResponseBroker> brokerList;
    
}
