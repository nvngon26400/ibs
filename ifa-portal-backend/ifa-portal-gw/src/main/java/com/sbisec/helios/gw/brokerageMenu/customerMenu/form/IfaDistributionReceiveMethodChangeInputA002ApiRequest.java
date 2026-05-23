package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

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
public class IfaDistributionReceiveMethodChangeInputA002ApiRequest {

    /** ファンドコード・回数（4桁）. */
    @NotEmpty(message = "ファンドコード・回数（4桁）")
    private String times;

    /** ファンドコード・号1. */
    @NotEmpty(message = "ファンドコード・号1")
    private String issue1;

    /** ファンドコード・号2. */
    @NotEmpty(message = "ファンドコード・号2")
    private String issue2;

    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;

    /** 変更後分配金受取方法 */
    @NotEmpty(message = "変更後分配金受取方法")
    private String afterChangeDistributionReceiveMethodList;

}
