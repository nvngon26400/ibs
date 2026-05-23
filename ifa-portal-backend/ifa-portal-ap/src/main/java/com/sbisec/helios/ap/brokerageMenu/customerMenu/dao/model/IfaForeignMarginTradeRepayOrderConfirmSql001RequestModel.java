package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;

import com.sbisec.helios.ap.common.exception.IfaRuntimeException;

import lombok.Data;

import org.springframework.util.ObjectUtils;

/**
 * Sql001のリクエスト
 *
 * @author SCSK
 */
@Data
public class IfaForeignMarginTradeRepayOrderConfirmSql001RequestModel {
    
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

    /** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

    /** ティッカーコード. */
    private String tickerCode;

    /** 銘柄名. */
    private String brandName;

    /** 市場コード. */
    private String marketCode;

    /** 仕法区分. */
    private String methodType;

    /** 売買区分. */
    private String tradeKbn;

    /** 注文数量. */
    private String orderQuantity;

    /** 価格条件区分. */
    private String priceConditionsType;

    /** 指値. */
    private String sashine;

    /** 発火条件価格. */
    private String stopOrderPrice;

    /** 通貨コード. */
    private String currencyCode;

    /** 決済区分. */
    private String settlementType;

    /** 預り区分. */
    private String depositType;

    /** 勧誘区分. */
    private String kanyuKbn;

    /** 受注方法区分. */
    private String jutyuKbn;

    /** ワーニング申請取引. */
    private String warningApplyTrade;

    /** 重要事項説明区分. */
    private String importantMatterTypeClass;

    /** 乗換え勧誘(ETF)区分. */
    private String solicitationEtfType;

    /** 英文開示銘柄明区分. */
    private String engPubType;

    /** 取引前確認区分. */
    private String beforeTradeConfirmType;

    /** 注文種別区分. */
    private String securityClassType;

    /** 注文日. */
    private String orderDate;

    /** 注文時間. */
    private String orderTime;

    /** 国内約定日. */
    private String domesticTradeDate;

    /** 注文期限日. */
    private String orderDeadline;

    /** 仲介業者コード. */
    private String brokerCode;

    /** 仲介業者営業員コード. */
    private String brokerChargeCode;

    /** APIエラーコード. */
    private String apiErrorCode;

    /** APIステータスコード. */
    private String apiStatusCode;

    /** APIメッセージ. */
    private String apiMsg;

    /** 作成者. */
    private String createUser;

    /** 更新者. */
    private String updateUser;
    
    /**
     * DBへのマッピング
     *　IFA注文番号　String -> NUMBER
     *
     * @return Long フォーマットされたデータ
     */
    public Long getIfaOrderNo() {
        return Long.parseLong(ifaOrderNo);
    }
    
    /**
     * DBへのマッピング
     * IFA注文サブ番号　String -> NUMBER
     *
     * @return Long フォーマットされたデータ
     */
    public Long getIfaOrderSubNo() {
        return Long.parseLong(ifaOrderSubNo);
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
    
    /**
     * DBへのマッピング
     * 注文数量　String -> NUMBER
     *
     * @return Long フォーマットされたデータ
     */
    public Long getOrderQuantity() {
        return Long.parseLong(orderQuantity);
    }
    
    /**
     * DBへのマッピング
     * 指値　String -> NUMBER(小数含め)
     *
     * @return BigDecimal フォーマットされたデータ
     */
    public BigDecimal getSashine() {
        // テーブル定義書より、指値はnullを設定することが可能
        if (ObjectUtils.isEmpty(sashine)) {
            return null;
        }
        return new BigDecimal(sashine);
    }
    
    /**
     * DBへのマッピング
     * 発火条件価格　String -> NUMBER(小数含め)
     *
     * @return BigDecimal フォーマットされたデータ
     */
    public BigDecimal getStopOrderPrice() {
        // テーブル定義書より、発火条件価格はnullを設定することが可能
        if (ObjectUtils.isEmpty(stopOrderPrice)) {
            return null;
        }
        return new BigDecimal(stopOrderPrice);
    }
    
    /**
     * DBへのマッピング
     * 注文時間　String -> DATE
     *
     * @return Date フォーマットされたデータ
     */
    public Date getOrderTime() {
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
        // テーブル定義書より、APIステータスコードはnullを設定することが可能
        if (ObjectUtils.isEmpty(apiStatusCode)) {
            return null;
        }
        return Integer.parseInt(apiStatusCode);
    }

}
