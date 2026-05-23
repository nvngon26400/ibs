package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * お気に入り登録・解除 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectA004ApiRequest {
    
    /** 部店. */
    @NotEmpty(message = "部店")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** お気に入り登録状況（数字）. */
    @NotEmpty(message = "お気に入り登録状況")
    @Pattern(regexp = "0-9", message = "お気に入り登録状況")
    private String favoRegStatus;
    
    /** 顧客コード（数字）. */
    @Digits(integer = 9, fraction = 0, message = "顧客コード")
    @NotEmpty(message = "顧客コード")
    @Pattern(regexp = "0-9", message = "顧客コード")
    private String customerCode;
    
}
