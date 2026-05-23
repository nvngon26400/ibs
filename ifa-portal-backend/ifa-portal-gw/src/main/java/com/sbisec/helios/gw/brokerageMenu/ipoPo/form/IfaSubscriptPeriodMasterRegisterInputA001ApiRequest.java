package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import lombok.Data;

/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
@Data
public class IfaSubscriptPeriodMasterRegisterInputA001ApiRequest {

    /** 銘柄コード（半角英数字） */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode12;

    /** ブックビルディング申込期間（開始）（全角半角） */
    @NotEmpty(message = "ブックビルディング申込期間（開始）")
    @Size(min = 19, max = 19, message = "ブックビルディング申込期間（開始）")
    private String bbPresentationFrom;

}
