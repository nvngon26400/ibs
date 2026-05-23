package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;


import lombok.Data;

/**
 * 担当顧客別手数料一覧表示APIレスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListA002ApiResponse {
    
    /** 担当顧客別手数料リスト. */
    private List<IfaRepCustomerCommListApiResponseListModel> repCustomerCommList;
}
