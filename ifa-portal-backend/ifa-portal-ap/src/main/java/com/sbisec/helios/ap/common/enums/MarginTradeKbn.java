package com.sbisec.helios.ap.common.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 信用取引区分
 * 
 * @author SCSK
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum MarginTradeKbn {
    
    INSTITUTIONAL_CREDIT("6", " ", " ", "6ヶ月"),
    REGULAR_CREDIT("9", " ", " ", "無期限"),
    LONG_TERM_STOCK_AVAILABLE("C", " ", " ", "無期限"), 
    ONE_DAY_NORMAL("D", " ", " ", "日計り"), 
    ONE_DAY_PREMIUM("E", " "," ", "日計りH"),
    SHORT_TERM_YEAR("A", "Y", "#0", "%02d年"),
    SHORT_TERM_MONTH("A", "M", "#0", "%02dヶ月"),
    SHORT_TERM_DAY("A", "D", "#0", "%02d日"),
    LONG_TERM_YEAR("B", "Y", "#0", "%02d年"),
    LONG_TERM_MONTH("B", "M", "#0", "%02dヶ月"),
    LONG_TERM_DAY("B", "D", "#0", "%02d日"),
    LONG_TERM_UNLIMITED("B", " ", "  ", "無期限"),
    NOT_SPECIFIED(" ", " ", " ", "-");
    
    private final String paymentLimit;
    private final String paymentKbn;
    private final String dateCount;
    private final String marginTradeKbn;
    
    private MarginTradeKbn(String paymentLimit, String paymentKbn, String dateCount, String marginTradeKbn) {
        
        this.paymentLimit = paymentLimit;
        this.paymentKbn = paymentKbn;
        this.dateCount = dateCount;
        this.marginTradeKbn = marginTradeKbn;
    }
    
    public String getPaymentLimit() {
        return paymentLimit;
    }

    public String getPaymentKbn() {
        return paymentKbn;
    }

    public String getDateCount() {
        return dateCount;
    }

    public String getMarginTradeKbn() {
        return marginTradeKbn;
    }
    
    public static MarginTradeKbn valueOfKbn(String paymentLimit, String ippanMgPaymentKbn) {
        
        MarginTradeKbn[] enums = values();
        String[] skipPaymentKbnCheckArray = {
                INSTITUTIONAL_CREDIT.getPaymentLimit(),
                REGULAR_CREDIT.getPaymentLimit(),
                LONG_TERM_STOCK_AVAILABLE.getPaymentLimit(),
                ONE_DAY_NORMAL.getPaymentLimit(),
                ONE_DAY_PREMIUM.getPaymentLimit()
        };

        if (paymentLimit.isEmpty() || paymentLimit.isBlank()) {
            return MarginTradeKbn.NOT_SPECIFIED;
        }

        for (int i = 0; i < enums.length; i++) {
            
            if (StringUtils.equals(enums[i].getPaymentLimit(), paymentLimit)) {
                if (
                        StringUtils.equalsAny(paymentLimit, skipPaymentKbnCheckArray)
                        || StringUtils.equals(enums[i].getPaymentKbn(), ippanMgPaymentKbn)
                )

                return enums[i];
            }

                
        }
        
        return null;
    }
}
