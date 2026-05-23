package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/2/6 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSubscriptInputA001RequestDto {
    
    /** 銘柄コード（半角英数字） */
    private String brandCode;
    
    /** 部店コード（半角英数字） */
    private String butenCode;
    
    /** 口座番号（数字） */
    private String accountNumber;
    
    /** ブックビルディング申込期間（開始）（全角半角） */
    private String bookBuildingPresentationFrom;
    
    /** 明細番号（全角半角） */
    private String detailNumber;
    
}
