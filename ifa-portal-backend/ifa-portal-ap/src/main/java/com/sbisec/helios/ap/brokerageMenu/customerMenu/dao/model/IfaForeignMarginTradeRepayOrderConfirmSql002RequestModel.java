package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

/**
 * Sql002のリクエスト
 *
 * @author SCSK
 */
@Data
public class IfaForeignMarginTradeRepayOrderConfirmSql002RequestModel {
    
    /** IFA注文番号. */
    private String ifaOrderNo;

    /** ティッカーコード. */
    private String tickerCode;

    /** 仕法区分. */
    private String methodType;

    /** 返済期限区分. */
    private String repayPeriodType;

    /** 売買区分. */
    private String tradeKbn;

    /** 預り区分. */
    private String depositType;

    /** 建日. */
    private String openTradeDate;

    /** 建単価. */
    private String positionPrice;

    /** 建数量. */
    private String positionQuantity;

    /** 返済数量. */
    private String repayQuantity;

    /** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

    /** 返済指定区分. */
    private String repaymentMethodType;

    /** 返済順序区分. */
    private String requestType;

    /** 作成者. */
    private String createUser;

    /** 更新者. */
    private String updateUser;
    
    /**
     * DBへのマッピング
     * IFA注文番号 String -> NUMBER
     *
     * @return Long フォーマットされたデータ
     */
    public Long getIfaOrderNo() {
        return Long.parseLong(ifaOrderNo);
    }
    
    /**
     * DBへのマッピング
     * 建単価 String -> NUMBER(小数含め)
     *
     * @return BigDecimal フォーマットされたデータ
     */
    public BigDecimal getPositionPrice() {
        return new BigDecimal(positionPrice);
    }
    
    /**
     * DBへのマッピング
     * 建数量 String -> NUMBER
     *
     * @return Long フォーマットされたデータ
     */
    public Long getPositionQuantity() {
        // テーブル定義書より、建数量はnullを設定することが可能
        if (ObjectUtils.isEmpty(positionQuantity)) {
            return null;
        }
        return Long.parseLong(positionQuantity);
    }
    
    /**
     * DBへのマッピング
     * 返済数量　String -> NUMBER
     *
     * @return Long フォーマットされたデータ
     */
    public Long getRepayQuantity() {
        return Long.parseLong(repayQuantity);
    }
    
    /**
     * DBへのマッピング
     * 口座番号　String -> NUMBER
     *
     * @return Long フォーマットされたデータ
     */
    public Long getAccountNumber() {
        return Long.parseLong(accountNumber);
    }
    
}
