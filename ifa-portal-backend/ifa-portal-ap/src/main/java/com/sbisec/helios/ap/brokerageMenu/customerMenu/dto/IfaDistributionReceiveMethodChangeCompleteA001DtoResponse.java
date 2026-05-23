package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaDistributionReceiveMethodChangeCompleteA001DtoResponse {

    /** ファンド名（全角半角）. */
    private String fundName;

    /** 保有口数（数値(整数)）. */
    private String unitVolume;

    /** 売却注文中（数値(整数)）. */
    private String sellingVolume;

    /** 変更前分配金受取方法（全角半角）. */
    private String method;

    /** 変更後分配金受取方法（全角半角）. */
    private String afterChangeDistributionReceiveMethod;

    /** メッセージ. */
    private String infoMsg;

}
