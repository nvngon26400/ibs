package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sbisec.helios.ap.common.exception.IfaRuntimeException;

import lombok.Data;

/**
 * Sql003のリクエスト
 *
 * @author SCSK
 */
@Data
public class IfaForeignMarginTradeRepayOrderConfirmSql003RequestModel {
    
    /** 対象外エラー */
    private static final String ERROR_UNTARGETED = "対象外エラー";
    
    private static final String DATE_FORMAT_YYYYMMDDHHMMSS_1 = "yyyy-MM-dd hh:mm:ss";
    
    /** IFA注文番号. */
    private String ifaOrderNo;

    /** IFA注文サブ番号. */
    private String ifaOrderSubNo;
    
    /** 受付注文番号. */
    private String acceptOrderNo;

    /** 受付注文サブ番号. */
    private String acceptOrderSubNo;

    /** 注文日. */
    private String orderDate;

    /** 注文時間. */
    private String orderTime;

    /** 注文期限日. */
    private String orderDeadline;

    /** APIエラーコード. */
    private String apiErrorCode;

    /** APIステータスコード. */
    private String apiStatusCode;

    /** APIメッセージ. */
    private String apiMsg;

    /** 更新者. */
    private String updateUser;
    
    /**
     * DBへのマッピング
     *　IFA注文番号　String -> NUMBER
     *
     * @return Long フォーマットされたデータ
     */
    public Long getIfaOrderNo() {
        if(null == ifaOrderNo) {
            return null;
        }
        return Long.parseLong(ifaOrderNo);
    }
    
    /**
     * DBへのマッピング
     * IFA注文サブ番号　String -> NUMBER
     *
     * @return Long フォーマットされたデータ
     */
    public Long getIfaOrderSubNo() {
        if(null == ifaOrderSubNo) {
            return null;
        }
        return Long.parseLong(ifaOrderSubNo);
    }
    
    /**
     * DBへのマッピング
     * 注文時間　String -> DATE
     *
     * @return Date フォーマットされたデータ
     */
    public Date getOrderTime() {
        if(null == orderTime) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS_1);
        try {
            return dateFormat.parse(orderTime);
        } catch (ParseException e) {
            throw new IfaRuntimeException(ERROR_UNTARGETED);
        }
    }
    
    /**
     * DBへのマッピング
     * APIステータスコード　String -> NUMBER
     *
     * @return Integer フォーマットされたデータ
     */
    public Integer getApiStatusCode() {
        if(null == apiStatusCode) {
            return null;
        }
        return Integer.parseInt(apiStatusCode);
    }
}
