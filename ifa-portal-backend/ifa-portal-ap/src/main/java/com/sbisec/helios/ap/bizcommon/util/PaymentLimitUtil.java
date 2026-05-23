package com.sbisec.helios.ap.bizcommon.util;

import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.IppanMgPaymentKbn;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.PaymentLimitStar;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.PaymentLimitCorrectCancel;
import com.sbisec.helios.ap.common.enums.DomesticStockTradeClass;
/**
 * 弁済期限（算出）_算出
 * 画面定義書_SUB0202_0212-08_1_現引現渡注文入力シート「別紙.弁済期限（算出）_算出方法」参照
 *
 * @author SCSK
 */
@Component
public class PaymentLimitUtil {
    
    /** 新規売買区分="0"（買新規） */
    private static final String OPEN_TRADE_KBN_BUY = "0";
    
    /** 新規売買区分="1"（売新規） */
    private static final String OPEN_TRADE_KBN_SELL = "1";

    /** 弁済期限（表示テキスト）= 6ヶ月 */
    private static final String PAYMENT_LIMIT_SIX_MONTH_TEXT = "6ヶ月";

    /** 弁済期限（表示テキスト）= 無制限 */
    private static final String PAYMENT_LIMIT_UNLIMITED_TEXT = "無期限";

    /** 弁済期限（表示テキスト）= 日計り */
    private static final String PAYMENT_LIMIT_DAY_TRADE_TEXT = "日計り";

    /** 弁済期限（表示テキスト）= 日計りH */
    private static final String PAYMENT_LIMIT_DAY_TRADE_H_TEXT = "日計りH";

    /** 弁済期限（表示テキスト）= 制度 */
    private static final String PAYMENT_LIMIT_INSTITUTIONAL_TEXT = "制度";

    /** 弁済期限（表示テキスト）= 一般 */
    private static final String PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT = "一般";

    /** 弁済期限 = 日計りH */
    private static final String PAYMENT_LIMIT_DAY_TRADE_H = "1";

    /** 済期限年月日 = 'Y' */
    private static final String PAYMENT_LIMIT_KBN_YEAR = "年";
    
    /** 済期限年月日 = 'M' */
    private static final String PAYMENT_LIMIT_KBN_MONTH = "ヶ月";

    /** 済期限年月日 = 'D' */
    private static final String PAYMENT_LIMIT_KBN_DAY = "日";

    /** 一日信用区分 = "1"：日計り */
    private static final String DAILY_CREDIT_KBN_1 = "1";

    /** 一日信用区分 = "2"：日計りH */
    private static final String DAILY_CREDIT_KBN_2 = "2";
    
    /** 一日信用区分 = "△" */
    private static final String DAILY_CREDIT_KBN_OTHER = " ";
    
    /** ハイフン */
    private static final String HYPHEN = "-";
    
    // 取引種別 = 7:現渡
    public static final String TRADE_CODE_7 = "7";
    
    // 取引種別 = 8:現引
    public static final String TRADE_CODE_8 = "8";

    // 注文状況 = 新規（発注）(0)　
    public static final String ORDER_STATUS_0 = "0";

    // 注文状況 = 訂正(1)
    public static final String ORDER_STATUS_1 = "1";
    
    // 注文状況 = 取消(2)
    public static final String ORDER_STATUS_2 = "2";


    /**
     * 弁済期限を算出する.
     *
     * @param paymentLimit API.弁済期限
     * @param openTradeKbn リクエスト.新規売買区分
     * @param ippanMgPaymentKbn API.一般信用売弁済期限年月日区分
     * @param ippanMgPaymentLimit API.一般信用売弁済期限年月日数
     * @param premiumShortSaleCcategory FCT027.プレミアム空売り区分
     * @return 弁済期限（算出）
     */
    public String getPaymentLimit(String paymentLimit, String openTradeKbn, String ippanMgPaymentKbn, String ippanMgPaymentLimit, String premiumShortSaleCcategory) {
        // API.一般信用売弁済期限年月日数の先頭0を削除
        String ippanMgPaymentLimitTmp = ippanMgPaymentLimit;
        if (ippanMgPaymentLimit != null && ippanMgPaymentLimit.startsWith("0") && ippanMgPaymentLimit.length() == 2) {
            ippanMgPaymentLimitTmp = ippanMgPaymentLimit.substring(1);
        }

        // API.弁済期限 = "6"：制度信用
        if (PaymentLimitStar.SIX_MONTH.getCode().equals(paymentLimit)) {
            return PAYMENT_LIMIT_SIX_MONTH_TEXT;
        // API.弁済期限 = "9"：一般信用買い,または一般信用売り(長期制限あり・なし銘柄)
        } else if (PaymentLimitStar.UNLIMITED.getCode().equals(paymentLimit)) {
            // リクエスト.新規売買区分 = '1'：売建
            if (OPEN_TRADE_KBN_SELL.equals(openTradeKbn)) {
                // API.一般信用売弁済期限年月日区分 ≠ △
                if (!IppanMgPaymentKbn.UNLIMITED.getCode().equals(ippanMgPaymentKbn)) {
                    return (ippanMgPaymentLimitTmp == null ? "" : ippanMgPaymentLimitTmp) + this.getPaymentKbnText(ippanMgPaymentKbn);
                // API.一般信用売弁済期限年月日区分 = △
                } else {
                    return PAYMENT_LIMIT_UNLIMITED_TEXT;
                }
            // リクエスト.新規売買区分 = '0'：買建
            } else {
                return PAYMENT_LIMIT_UNLIMITED_TEXT;
            }
        // API.弁済期限 = "A"：一日信用通常銘柄または一日信用プレミアム空売り銘柄
        } else if (PaymentLimitStar.ONEDAY.getCode().equals(paymentLimit)) {
            // FCT027.プレミアム空売り区分 = "1"：日計りH
            if (PAYMENT_LIMIT_DAY_TRADE_H.equals(premiumShortSaleCcategory)) {
                return PAYMENT_LIMIT_DAY_TRADE_H_TEXT;
            // FCT027.プレミアム空売り区分 ≠ "1"：日計りH
            } else {
                return PAYMENT_LIMIT_DAY_TRADE_TEXT;
            }
        // API.弁済期限 = "B"：短期(一般信用売り-短期銘柄)
        } else if (PaymentLimitStar.FIVEDAYS.getCode().equals(paymentLimit)) {
            // API.一般信用売弁済期限年月日区分 ≠ △
            if (!IppanMgPaymentKbn.UNLIMITED.getCode().equals(ippanMgPaymentKbn)) {
                return (ippanMgPaymentLimitTmp == null ? "" : ippanMgPaymentLimitTmp) + this.getPaymentKbnText(ippanMgPaymentKbn);
            // API.一般信用売弁済期限年月日区分 = △
            } else {
                return HYPHEN;
            }
        // API.弁済期限 = "D"：短期(一般信用売り-短期銘柄)
        } else if (PaymentLimitStar.FIFTEENDAYS.getCode().equals(paymentLimit)) {
            // API.一般信用売弁済期限年月日区分 ≠ △
            if (!IppanMgPaymentKbn.UNLIMITED.getCode().equals(ippanMgPaymentKbn)) {
                return (ippanMgPaymentLimitTmp == null ? "" : ippanMgPaymentLimitTmp) + this.getPaymentKbnText(ippanMgPaymentKbn);
            // API.一般信用売弁済期限年月日区分 = △
            } else {
                return HYPHEN;
            }
        // API.弁済期限 = △：指定無し
        } else {
            return HYPHEN;
        }
    }

     /**
     * 注文一覧用の弁済期限を算出する.
     * 
     * @param tradeCd SQL001.取引種別
     * @param orderStatus SQL001.注文状況
     * @param paymentDeadline SQL001.弁済期限
     * @param dateKbn SQL001.年月日区分
     * @param dailyCreditKbn SQL001.一日信用区分
     * @param paymentDeadlineDate SQL001.弁済期限年月日数
     * @return 弁済期限（算出）
     */
    public String getPaymentLimitOrderList(String tradeCd, String orderStatus, String paymentDeadline, String dateKbn, String dailyCreditKbn, String paymentDeadlineDate) {
        
        // SQL001.弁済期限年月日数の先頭0を削除
        String paymentDeadlineDateTmp = paymentDeadlineDate;
        if (paymentDeadlineDate != null && paymentDeadlineDate.startsWith("0") && paymentDeadlineDate.length() == 2) {
            paymentDeadlineDateTmp = paymentDeadlineDate.substring(1);
        }

        // SQL001.取引種別が　現物買付(1)、現物売却(2) の場合
        if (DomesticStockTradeClass.SPOT_BUY.getId().equals(tradeCd) || DomesticStockTradeClass.SPOT_SELL.getId().equals(tradeCd)) {
            return "";
        // SQL001.取引種別が　信用新規買(3)、信用新規売(4)、信用返済買(5)、信用返済売(6)、現渡(7)、現引(8)　の場合　
        } else if (
            DomesticStockTradeClass.SHINYOSHINKI_BUY.getId().equals(tradeCd) || 
            DomesticStockTradeClass.SHINYOSHINKI_SELL.getId().equals(tradeCd) || 
            DomesticStockTradeClass.MARGIN_REPAY_BUY.getId().equals(tradeCd) || 
            DomesticStockTradeClass.MARGIN_REPAY_SELL.getId().equals(tradeCd) || 
            TRADE_CODE_7.equals(tradeCd) || TRADE_CODE_8.equals(tradeCd)
        ) {
            // SQL001.注文状況が、訂正(1)、取消(2)　の場合
            if (ORDER_STATUS_1.equals(orderStatus) || ORDER_STATUS_2.equals(orderStatus)) {
                // SQL001.弁済期限 = "6"：６ヶ月（制度信用）
                if (PaymentLimitCorrectCancel.SIX_MONTH.getCode().equals(paymentDeadline)) {
                    return this.formatText(PAYMENT_LIMIT_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_SIX_MONTH_TEXT);
                // SQL001.弁済期限 = "9"：無期限(一般信用）
                } else if (PaymentLimitCorrectCancel.UNLIMITED.getCode().equals(paymentDeadline)) {
                    return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_UNLIMITED_TEXT);
                // SQL001.弁済期限 = "C"：長期(一般信用売り-長期在庫制限無し銘柄)
                } else if (PaymentLimitCorrectCancel.LONG_NOLIMIT.getCode().equals(paymentDeadline)) {
                    return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_UNLIMITED_TEXT);
                // SQL001.弁済期限 = "D"：１日(一日信用-通常銘柄)
                } else if (PaymentLimitCorrectCancel.ONEDAY_NORMAL.getCode().equals(paymentDeadline)) {
                    return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_DAY_TRADE_TEXT);
                // SQL001.弁済期限 = "E"：１日(一日信用-プレミアム空売り銘柄)
                } else if (PaymentLimitCorrectCancel.ONEDAY_PREMIUM.getCode().equals(paymentDeadline)) {
                    return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_DAY_TRADE_H_TEXT);
                // SQL001.弁済期限 = "A"：短期(一般信用売り-短期銘柄)
                } else if (PaymentLimitCorrectCancel.SHORT.getCode().equals(paymentDeadline)) {
                    // SQL001.年月日区分 ≠ △
                    if (!IppanMgPaymentKbn.UNLIMITED.getCode().equals(dateKbn)) {
                        String date = (paymentDeadlineDateTmp == null ? "" : paymentDeadlineDateTmp) + this.getPaymentKbnText(dateKbn);
                        return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, date);
                    // SQL001.年月日区分 = △
                    } else {
                        return this.formatText(null, null);
                    }
                // SQL001.弁済期限 = "B"：長期(一般信用売り-長期在庫制限有り銘柄)
                } else if (PaymentLimitCorrectCancel.LONG_LIMIT.getCode().equals(paymentDeadline)) {
                    // SQL001.年月日区分 ≠ △
                    if (!IppanMgPaymentKbn.UNLIMITED.getCode().equals(dateKbn)) {
                        String date = (paymentDeadlineDateTmp == null ? "" : paymentDeadlineDateTmp) + this.getPaymentKbnText(dateKbn);
                        return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, date);
                    // SQL001.年月日区分 = △
                    } else {
                        return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_UNLIMITED_TEXT);
                    }
                // SQL001.弁済期限 = △：指定無し
                } else {
                    return this.formatText(null, null);
                }
            // SQL001.注文状況が、新規（発注）(0)　の場合
            } else {
                // SQL001.弁済期限 = "6"：６ヶ月（制度信用）
                if (PaymentLimitStar.SIX_MONTH.getCode().equals(paymentDeadline)) {
                    return this.formatText(PAYMENT_LIMIT_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_SIX_MONTH_TEXT);
                // SQL001.弁済期限 = "9"：一般信用買い,または一般信用売り(長期制限あり・なし銘柄)
                } else if (PaymentLimitStar.UNLIMITED.getCode().equals(paymentDeadline)) {
                    // SQL001.取引種別 = "4"：信用新規売, "5"：信用返済買, "7"：現渡
                    if (
                        DomesticStockTradeClass.SHINYOSHINKI_SELL.getId().equals(tradeCd) || 
                        DomesticStockTradeClass.MARGIN_REPAY_BUY.getId().equals(tradeCd) || 
                        TRADE_CODE_7.equals(tradeCd)
                    ) {
                        // SQL001.年月日区分 ≠ △
                        if (!IppanMgPaymentKbn.UNLIMITED.getCode().equals(dateKbn)) {
                            String date = (paymentDeadlineDateTmp == null ? "" : paymentDeadlineDateTmp) + this.getPaymentKbnText(dateKbn);
                            return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, date);
                        // SQL001.年月日区分 = △
                        } else {
                            return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_UNLIMITED_TEXT);
                        }
                    // SQL001.取引種別 = "4"：信用新規売, "5"：信用返済買, "7"：現渡
                    } else {
                        return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_UNLIMITED_TEXT);
                    }
                // SQL001.弁済期限 = "A"：一日信用通常銘柄または一日信用プレミアム空売り銘柄
                } else if (PaymentLimitStar.ONEDAY.getCode().equals(paymentDeadline)) { 
                    // SQL001.一日信用区分 = "1"：日計りH
                    if (DAILY_CREDIT_KBN_1.equals(dailyCreditKbn)) {
                        return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_DAY_TRADE_TEXT);
                    // SQL001.一日信用区分 = "2"：日計りH
                    } else if (DAILY_CREDIT_KBN_2.equals(dailyCreditKbn)) {
                        return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, PAYMENT_LIMIT_DAY_TRADE_H_TEXT);
                    // SQL001.一日信用区分 = "△"
                    } else {
                        return this.formatText(null, null);
                    }
                // SQL001.弁済期限 = "B"：短期(一般信用売り-短期銘柄), "D"：短期(一般信用売り-短期銘柄)
                } else if (
                    PaymentLimitStar.FIVEDAYS.getCode().equals(paymentDeadline) ||
                    PaymentLimitStar.FIFTEENDAYS.getCode().equals(paymentDeadline)
                ) {
                    // SQL001.年月日区分 ≠ △
                    if (!IppanMgPaymentKbn.UNLIMITED.getCode().equals(dateKbn)) {
                        String date = (paymentDeadlineDateTmp == null ? "" : paymentDeadlineDateTmp) + this.getPaymentKbnText(dateKbn);
                        return this.formatText(PAYMENT_LIMIT_NON_INSTITUTIONAL_TEXT, date);
                    // SQL001.年月日区分 = △
                    } else {
                        return this.formatText(null, null);
                    }
                } else {
                    return this.formatText(null, null);
                }
            }
        }
        // SQL001.取引種別が上記以外
        return "";
    }

    /**
     * 返済期限年月日区分の表示テキストを取得
     *
     * @param ippanMgPaymentKbn API.一般信用売弁済期限年月日区分
     * @return 返済期限年月日区分の表示テキスト
     */
    private String getPaymentKbnText(String ippanMgPaymentKbn) {
        if (IppanMgPaymentKbn.YEAR.getCode().equals(ippanMgPaymentKbn)) {
            return PAYMENT_LIMIT_KBN_YEAR;
        } else if (IppanMgPaymentKbn.MONTH.getCode().equals(ippanMgPaymentKbn)) {
            return PAYMENT_LIMIT_KBN_MONTH;
        } else if (IppanMgPaymentKbn.DAY.getCode().equals(ippanMgPaymentKbn)) {
            return PAYMENT_LIMIT_KBN_DAY;
        }

        return "";
    }

    private String formatText(String left, String right) {
        if (left != null && right != null) {
            return "(" + left + "/" + right + ")";
        } else {
            return "(-)";
        }
    }
}
