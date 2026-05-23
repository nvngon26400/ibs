package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_010201-02_1
 * 画面名：分配金受取方法変更入力
 * 2023/11/28 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaDistributionReceiveMethodChangeInputA001RequestDto {

    /** 銘柄コード.回数. */
    private String times;

    /** 銘柄コード.号1. */
    private String issue1;

    /** 銘柄コード.号2. */
    private String issue2;

    /** 預り区分（全角半角）. */
    private String depositType;

}
