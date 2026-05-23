package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内投信注文取消確認 A001 リクエストパラメータ
 *
 * @author SCSK
 *
 *     2023/11/24 新規作成
 */
@Data
public class IfaDomesticMutualFundOrderCancelConfirmA001RequestDto {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
}
