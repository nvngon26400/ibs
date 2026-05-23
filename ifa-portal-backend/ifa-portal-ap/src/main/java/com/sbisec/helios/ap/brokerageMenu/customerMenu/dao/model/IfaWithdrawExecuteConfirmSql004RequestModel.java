package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 出金確認履歴Req004
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawExecuteConfirmSql004RequestModel {

    /** トランザクションID */
    private String tranId;

    /** 枝番 */
    private String edaban;

    /** 更新者 */
    private String updateUser;

}
