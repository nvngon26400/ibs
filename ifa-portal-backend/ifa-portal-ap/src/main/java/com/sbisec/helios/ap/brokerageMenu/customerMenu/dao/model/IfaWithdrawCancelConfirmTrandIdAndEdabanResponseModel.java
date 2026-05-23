package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 出金入力A001レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawCancelConfirmTrandIdAndEdabanResponseModel {

    /** トランザクションID */
    private String tranId;

    /** 枝番 */
    private String edaban;
}
