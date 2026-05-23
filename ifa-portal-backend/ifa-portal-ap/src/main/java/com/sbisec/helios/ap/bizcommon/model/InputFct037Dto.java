package com.sbisec.helios.ap.bizcommon.model;

import java.math.BigDecimal;
import java.util.Date;
import java.text.DecimalFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 共通関数DTO：FCT037

 * @author SCSK
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class InputFct037Dto extends BaseOutputDto {

    /** 注文訂正ステータス */
    private String orderCorrectStatus;

    /** 自動注文種別 */
    private String autoOrderClass;

    /** RBE注文種別 */
    private String rbeChumonShubetsu;

    /** RBE注文ステータス */
    private String rbeOrderStatus;

    /** 指成区分 */
    private String sashinariKbn;

    /** 指値 */
    private String sashine;

    public void setSashine(String sashine) {
        this.sashine = formatNumber(sashine);
    }

    /** 直近トリガ発動ゾーン */
    private String latestTriggerZone;

    /** 直近トリガ値段 */
    private String latestTriggerNedan;

    public void setLatestTriggerNedan(String latestTriggerNedan) {
        this.latestTriggerNedan = formatNumber(latestTriggerNedan);
    }

    /** 直近OCO指成区分 */
    private String latestOcoLimitMarketType;

    /** 直近OCO値段 */
    private String latestOcoLimitlimitPrice;

    public void setLatestOcoLimitlimitPrice(String latestOcoLimitlimitPrice) {
        this.latestOcoLimitlimitPrice = formatNumber(latestOcoLimitlimitPrice);
    }

    /** DONE RBE注文種別 */
    private String doneRbeOrderClass;

    /** DONE 指成区分 */
    private String doneLimitMarketType;

    /** DONE 指値 */
    private String doneLimitPrice;

    public void setDoneLimitPrice(String doneLimitPrice) {
        this.doneLimitPrice = formatNumber(doneLimitPrice);
    }

    /** DONE トリガ発動ゾーン */
    private String doneTriggerZone;

    /** DONE トリガ値段 */
    private String doneTriggerNedan;

    public void setDoneTriggerNedan(String doneTriggerNedan) {
        this.doneTriggerNedan = formatNumber(doneTriggerNedan);
    }

    /** DONE OCO指成区分 */
    private String doneOcoLimitMarketType;

    /** DONE OCO指値 */
    private String doneOcoLimitPrice;

    public void setDoneOcoLimitPrice(String doneOcoLimitPrice) {
        this.doneOcoLimitPrice = formatNumber(doneOcoLimitPrice);
    }

    /** DONE 有効期限 */
    private String doneExpirationDate;


    /**
     * @param num フォーマット対象の数値
     * @return numが数値型ならカンマ区切りに変換したnum、そうでなければnumを返す。
    */
    private String formatNumber(String num) {
        try {
            // 数値をBigDecimal型に変換
            BigDecimal bdNum = new BigDecimal(num);

            // フォーマッタを設定
            DecimalFormat df = new DecimalFormat("#,###.##");
            int scale = bdNum.scale();
            df.setMaximumFractionDigits(scale);

            // BigDecimalからString型にフォーマット
            String formattedNum = df.format(bdNum);
            return formattedNum;

        } catch(NumberFormatException | NullPointerException e) {
            return num;
        }
    }

}
