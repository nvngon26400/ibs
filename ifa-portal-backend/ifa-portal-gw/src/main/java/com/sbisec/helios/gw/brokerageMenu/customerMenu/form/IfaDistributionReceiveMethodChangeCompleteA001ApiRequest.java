package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class IfaDistributionReceiveMethodChangeCompleteA001ApiRequest {

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

}
