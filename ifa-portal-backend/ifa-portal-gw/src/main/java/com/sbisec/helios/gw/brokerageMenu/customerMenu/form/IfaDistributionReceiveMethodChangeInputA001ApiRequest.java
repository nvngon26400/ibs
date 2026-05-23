package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
public class IfaDistributionReceiveMethodChangeInputA001ApiRequest {

     /** ファンド名（全角半角）. */
    @NotEmpty(message = "ファンド名")
    @Size(max = 80, message = "ファンド名")
    private String fundName;

    /** 保有口数（数値(整数)）. */
    @Digits(integer = 16, fraction = 0, message = "保有口数")
    @NotEmpty(message = "保有口数")
    @Size(max = 21, message = "保有口数")
    private String unitVolume;

    /** 売却注文中（数値(整数)）. */
    @Digits(integer = 16, fraction = 0, message = "売却注文中")
    @NotEmpty(message = "売却注文中")
    @Size(max = 21, message = "売却注文中")
    private String sellingVolume;

    /** 銘柄コード.回数. */
    @NotEmpty(message = "銘柄コード.回数")
    private String times;

    /** 銘柄コード.号1. */
    @NotEmpty(message = "銘柄コード.号1")
    private String issue1;

    /** 銘柄コード.号2. */
    @NotEmpty(message = "銘柄コード.号2")
    private String issue2;

    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;

}
