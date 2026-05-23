package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaDistributionReceiveMethodChangeCompleteA001ApiResponse {

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
