package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/2/6 新規作成
 *
 * @author SCSK 江口
 * 
 */
@Data
public class IfaSubscriptInputA001ApiRequest {
    
    /** 銘柄コード（半角英数字） */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 部店コード（半角英数字） */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字） */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "[0-9]+", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** ブックビルディング申込期間（開始）（全角半角） */
    @NotEmpty(message = "ブックビルディング申込期間（開始）")
    @Size(max = 8, message = "ブックビルディング申込期間（開始）")
    private String bookBuildingPresentationFrom;
    
    /** 明細番号（全角半角） */
    @NotEmpty(message = "明細番号")
    @Size(max = 3, message = "明細番号")
    private String detailNumber;
    
}
