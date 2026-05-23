package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import java.util.List;

import lombok.Data;

/**
 * 担当顧客別手数料CSV出力レスポンスDto.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListA004aResponseDto {
    
    /** 担当顧客別手数料リスト. */
    private List<IfaRepCustomerCommListModel> repCustomerCommList;
    
}
