package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.InitialMarginShortfallDetail;
import com.sbisec.helios.ap.athena.protocol.account.dto.MarginTransition;
import com.sbisec.helios.ap.athena.protocol.account.dto.PowerMarginCallDetail;

import lombok.Data;

/**
 * Description:余力サービス 外国株式信用建余力詳細取得API Response

 * @author SCSK 矢口
    2023/12/1 新規作成
 */
@Data
public class GetMarginPowerDetailResp {
    
    public GetMarginPowerDetailResp() {
        
    }
    
    /** 信用建余力 */
    private String marginBuyingPower;
    
    /** 委託保証金率（当日基準） */
    private String depositRateWithSameDayStandard;
    
    /** 出金指示可能額 */
    private String marginWithdrawable;
    
    /** 出庫余力 */
    private String collateralWithdrawable;
    
    /** 追証ステータス */
    private String marginCallStatus;
    
    /** 直近値洗い区分 */
    private String markToMarketStatus;
    
    /** 追証ワーニング */
    private String marginCallWarning;
    
    /** 委託保証金現金 */
    private String marginCash;
    
    /** 代用有価証券評価額合 */
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
    
    /** 委託保証金率の推移情報 */
    private List<MarginTransition> marginTransitions;
    
    /** 未決済建代金合計 */
    private String totalPositionAmountBefore;
    
    /** 建玉必要保証金率 */
    private String positionMarginRate;
    
    /** 返却必要保証金率 */
    private String withdrawableMarginRate;
    
    /** 建玉限度額 */
    private String positionLimit;
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 最低委託保証金 */
    private String minRequiredMargin;
    
}
