package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.util.List;
import lombok.Data;

/**
 * 顧客選択 SQL001 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectSql001RequestModel {

    /** 権限コード. */
    private String privId;

    /** ユーザID. */
    private String userId;

    /** 仲介業者営業員リスト.
     */
    private List<BrokerCharge> brokerChargeList;

    /** 部店. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名（全角半角）. */
    private String customerName;

    /** お気に入り. */
    private String favorite;

    /** 取引制限有無. */
    private String tradeRestrictionSelect;

    /** 顧客コード（数字）. */
    private String customerId;

    /**
     * 仲介業者営業員
     */
    @Data
    public static class BrokerCharge {
    
        /** 仲介業者営業員リスト.仲介業者コード. */
        private String brokerCode;

        /** 仲介業者営業員リスト.営業員コード. */
        private String empCode;

    }
}
