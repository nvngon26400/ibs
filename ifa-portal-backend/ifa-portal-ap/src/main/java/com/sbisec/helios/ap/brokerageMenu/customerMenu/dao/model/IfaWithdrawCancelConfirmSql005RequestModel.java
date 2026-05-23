package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 出金取消履歴Req005
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawCancelConfirmSql005RequestModel {

    /** トランザクションID */
    private String tranId;

    /** 枝番 */
    private String edaban;

    /** 計上日 */
    private String keijoubi;

    /** 更新者 */
    private String updateUser;

}
