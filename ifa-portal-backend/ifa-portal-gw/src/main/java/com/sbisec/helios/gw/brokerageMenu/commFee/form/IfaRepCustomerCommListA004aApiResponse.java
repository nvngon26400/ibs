package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;

import lombok.Data;

/**
 * 担当顧客別手数料一覧CSV出力APIレスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListA004aApiResponse {
    
    /** 担当顧客別手数料リスト. */
    private List<IfaRepCustomerCommListApiResponseListModel> repCustomerCommList;

}
