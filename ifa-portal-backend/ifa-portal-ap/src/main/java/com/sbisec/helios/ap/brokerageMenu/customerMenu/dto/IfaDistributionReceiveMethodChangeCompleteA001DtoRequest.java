package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaDistributionReceiveMethodChangeCompleteA001DtoRequest {

    /** ファンド名（全角半角）. */
    private String fundName;

    /** 保有口数（数値(整数)）. */
    private String unitVolume;

    /** 売却注文中（数値(整数)）. */
    private String sellingVolume;

    /** ファンドコード・回数（4桁）. */
    private String times;

    /** ファンドコード・号1. */
    private String issue1;

    /** ファンドコード・号2. */
    private String issue2;

    /** 預り区分（全角半角）. */
    private String depositType;

}
