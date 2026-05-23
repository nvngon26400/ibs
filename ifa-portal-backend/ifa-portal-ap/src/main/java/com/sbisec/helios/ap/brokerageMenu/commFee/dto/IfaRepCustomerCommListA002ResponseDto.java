package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import java.util.List;

import lombok.Data;

/**
 * 担当顧客別手数料一覧表示レスポンスDto.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListA002ResponseDto {
    
    /** 担当顧客別手数料リスト. */
    private List<IfaRepCustomerCommListModel> repCustomerCommList;
    
}
