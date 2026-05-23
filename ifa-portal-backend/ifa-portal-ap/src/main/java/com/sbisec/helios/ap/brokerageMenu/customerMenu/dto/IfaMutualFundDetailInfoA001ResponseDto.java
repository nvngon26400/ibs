package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 投信詳細情報レスポンスDTO
 *
 * @author SCSK
 *
 */
@Data
public class IfaMutualFundDetailInfoA001ResponseDto {
    
    // 基準価格単位
    private static final String DEFAULT_BPU = "10000";
    
    // 解約手数料
    private static final String DEFAULT_FREE_CONTENT = "なし";
    
    // 償還優遇の適用
    private static final String DEFAULT_PREFERENTIAL_REDEMPTION_APPLICATION = "あり\n※買付手数料無料の場合は適用されません。";
    
    /**
     * 休場日アイテム
     *
     * @author SCSK
     *
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClosedDayItem {
        
        /** 休場日 */
        private String closedDay;
        
        /** 営業日フラグ */
        private String businessDayFlag;
    }
    
    /** 更新日時. */
    private String updateTime;
    
    /** ファンド正式名 */
    private String fundOfficalName;
    
    /** 基準価額日付. */
    private String priceDate;
    
    /** 基準価額（数値(整数)）. */
    private String price;
    
    /** 基準価額単位（数値(整数)）. */
    private String basePriceUnit = DEFAULT_BPU;
    
    /** 前日比（数値(小数)）. */
    private String diff;
    
    /** 前日比率. */
    private String ratio;
    
    /** 純資産（数値(整数)）. */
    private String junshisan;
    
    /** 52週高値（数値(整数)）. */
    private String w52Takane;
    
    /** 52週高値日付（全角半角）. */
    private String w52Takanedate;
    
    /** 52週安値（数値(整数)）. */
    private String w52Yasune;
    
    /** 52週安値日付（全角半角）. */
    private String w52Yasunedate;
    
    /** 協会コード（全角半角）. */
    private String kyoukaiCd;
    
    /** 当月休場日リスト. */
    private List<ClosedDayItem> thisMonthClosedDayList;
    
    /** 翌月休場日リスト. */
    private List<ClosedDayItem> nextMonthClosedDayList;
    
    /** 締切日（直近）. */
    private String deadlineDateRecent;
    
    /** 締切日（次回）. */
    private String deadlineDateNext;
    
    /** 締切時間. */
    private String deadlines;
    
    /** 当社からのお知らせ更新日. */
    private String shrineNotificationUpdateDate;
    
    /** 当社からのお知らせコンテンツ. */
    private String shrineNotificationContents;
    
    /** 委託会社からのお知らせ更新日. */
    private String outsourcingCompanyNotificationUpdateDate;
    
    /** 委託会社からのお知らせコンテンツ. */
    private String outsourcingCompanyNotificationContents;
    
    /** ウエルスアドバイザー社のコメント更新日. */
    private String wealthAdvisorCommentUpdateDate;
    
    /** ウエルスアドバイザー社のコメントコンテンツ. */
    private String wealthAdvisorCommentContents;
    
    /** 取引コース（全角半角）. */
    private String course;
    
    /** 定期売却. */
    private String fundTypeName;
    
    /** 運用方針（全角半角）. */
    private String operationPolicy;
    
    /** 買付単位. */
    private String buyUnitWord;
    
    /** 売却単位. */
    private String sellSharesWord;
    
    /** 当初一口当り元本. */
    private String individualPrincipal;
    
    /** 買付手数料（税込）左. */
    private String buyCommLeft;
    
    /** 買付手数料（税込）右. */
    private String buyCommRight;
    
    /** 買付手数料（税込）(NISA)左. */
    private String buyCommNisaLeft;
    
    /** 買付手数料（税込）(NISA)右. */
    private String buyCommNisaRight;
    
    /** 信託報酬(税込)/年. */
    private String trustFeeAmount;
    
    /** 信託財産留保額（全角半角）. */
    private String partialRedemptionCharge;
    
    /** 解約手数料（税込）. */
    private String feeContent = DEFAULT_FREE_CONTENT;
    
    /** 償還優遇の適用（全角半角）. */
    private String preferentialRedemptionApplication = DEFAULT_PREFERENTIAL_REDEMPTION_APPLICATION;
    
    /** 当社締切時間. */
    private String shrineDeadlines;
    
    /** 約定日. */
    private String tradeDate;
    
    /** 受渡日. */
    private String settlementDate;
    
    /** 決算日. */
    private String settleLastDay;
    
    /** 分配金. */
    private String dividendHandling;
    
    /** 分配金受取方法. */
    private String distributionReceiveMethodWord;
    
    /** 設定日. */
    private String settingDate;
    
    /** 償還日. */
    private String redemptionDate;
    
}
