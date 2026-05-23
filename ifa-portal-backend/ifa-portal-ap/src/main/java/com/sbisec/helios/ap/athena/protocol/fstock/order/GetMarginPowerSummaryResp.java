package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.DeficitBalanceDetail;
import com.sbisec.helios.ap.athena.protocol.account.dto.InitialMarginShortfallDetail;
import com.sbisec.helios.ap.athena.protocol.account.dto.PowerMarginCallDetail;

import lombok.Data;

/**
 * 余力サービス - 外国株式信用建余力サマリ取得API Response.

 * @author SCSK 矢口
    2023/12/1 移植
 */
@Data
public class GetMarginPowerSummaryResp {
    
    public GetMarginPowerSummaryResp() {
    
    }
    
    /** 信用建余力 */
    private String marginBuyingPower;
    
    /** 出金指示可能額 */
    private String marginWithdrawable;
    
    /** 出庫余力 */
    private String collateralWithdrawable;
    
    /** 追証ステータス */
    private String marginCallStatus;
    
    /** 追証アラートステータスリスト */
    private List<String> remainingPowerAlertStatus;
    
    /** 直近値洗い区分 */
    private String markToMarketStatus;
    
    /** 追証ワーニング */
    private String marginCallWarning;
    
    /** 委託保証金現金 */
    private String marginCash;
    
    /** 代用有価証券評価額合計 */
    private String totalCollateralValue;
    
    /** 実質保証金 */
    private String actualMargin;
    
    /** 建代金合計 */
    private String totalPositionAmount;
    
    /** 評価損益合計 */
    private String totalUnrealizedProfitLoss;
    
    /** 支払諸経費等合計 */
    private String totalExpenses;
    
    /** 決済損益合計 */
    private String totalClosedProfitLoss;
    
    /** 預託率 */
    private String depositRate;
    
    /** 余力追証明細 */
    private List<PowerMarginCallDetail> marginCallDetails;
    
    /** 新規建不足明細 */
    private List<InitialMarginShortfallDetail> initialMarginShortfallDetails;
    
    /** 預り金不足明細 */
    private List<DeficitBalanceDetail> deficitBalanceDetails;
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 最低委託保証金 */
    private String minRequiredMargin;
    
}
