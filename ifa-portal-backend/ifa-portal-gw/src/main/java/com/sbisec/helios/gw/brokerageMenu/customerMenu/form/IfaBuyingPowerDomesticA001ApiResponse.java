package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 買付余力（国内） A001Apiレスポンス

 * @author 松田
 *
 */
@Data
public class IfaBuyingPowerDomesticA001ApiResponse {

    /** 買付余力一覧.受渡日(T+2) */
    private String settlementDateT2;
    
    /** 買付余力一覧.受渡日(T+3) */
    private String settlementDateT3;
    
    /** 買付余力一覧.受渡日(T+4) */
    private String settlementDateT4;
    
    /** 買付余力一覧.買付余力(T+2) */
    private String yenBuyingPowerGeneralAccountT2;
    
    /** 買付余力一覧.買付余力(T+3) */
    private String yenBuyingPowerGeneralAccountT3;
    
    /** 買付余力一覧.買付余力(T+4) */
    private String yenBuyingPowerGeneralAccountT4;
    
    /** 買付余力一覧.買付余力（ジュニアNISA口座(T+2) */
    private String yenBuyingPowerJrNisaT2;
    
    /** 買付余力一覧.買付余力（ジュニアNISA口座(T+3) */
    private String yenBuyingPowerJrNisaT3;
    
    /** 買付余力一覧.買付余力（ジュニアNISA口座(T+4) */
    private String yenBuyingPowerJrNisaT4;
    
    /** MRF・預り金・信用保証金 */
    private String totalOfMrfDpositMargin;
    
    /** 入金額 */
    private String depositAmount;
    
    /** 合計額 */
    private String totalAmount;
    
    /** ジュニアNISA口座(MRF・預り金・信用保証金) */
    private String NISATotalOfMrfDpositMargin;
    
    /** ジュニアNISA口座(入金額) */
    private String NISADepositAmount;
    
    /** ジュニアNISA口座(合計額) */
    private String NISATotalAmount;
    
    /** ポイント.ポイント種別 */
    private String pointShubetsu;
    
    /** ポイント.ポイント数 */
    private String pointNumber;
    
    /** ポイント.うち期間固定ポイント */
    private String fixedTermPoint;
    
    /** ポイント.最短有効期限 */
    private String pointShortLimit;
    
    /** ポイント.ポイント表示エリア表示可否 */
    private String pointDisplayAreaAvailability;
    
    /** ポイント.ポイント名表示可否 */
    private String pointNameDisplayAvailability;
    
    /** ポイント.ポイント数表示可否 */
    private String pointNumberDisplayAvailability;
    
    /** ポイント.うち期間固定ポイント表示可否 */
    private String fixedTermPointDisplayAvailability;
    
    /** ポイント.最短有効期限表示可否 */
    private String pointShortLimitDisplayAvailability;
    
    /** 当日（受渡日） */
    private String todaySettlementDate;
    
    /** 当日（預り金(MRF含む)） */
    private String todayDepositIncludingMrf;
    
    /** 当日（保証金現金） */
    private String todayMargin;
    
    /** 当日（預り金） */
    private String todayYenDeposit;
    
    /** 当日（入金額） */
    private String todayDepositAmount;
    
    /** 当日（支払額） */
    private String todayPayment;
    
    /** 当日（未約定信用決済損） */
    private String todayUncontractedCreditSettlementLoss;
    
    /** 当日（未約定買注文額） */
    private String todayUncontractedPurchaseOrderAmount;
    
    /** 当日（出金・振替指示額） */
    private String todayWithdrawalTransferInstructions;
    
    /** 当日（受取額） */
    private String todayAmountReceived;
    
    /** 当日（受取額（日計り分）） */
    private String todayDayTrading;
    
    /** 当日(SBIハイブリッド預金精算額) */
    private String todaySbiHybridDepositSettlementAmount;
    
    /** 当日（残高合計額） */
    private String todayTotalBalance;
    
    /** 当日(残高（当社）) */
    private String todayOurCompanyTotalBalance;
    
    /** 当日(残高(SBIハイブリッド預金)) */
    private String todaySbiHybridDepositSettlementTotalBalance;
    
    /** 当日(SBIハイブリッド預金拘束額) */
    private String todaySbiHybridDepositSettlementRestrictedAmount;
    
    /** 当日(残高(保証金現金)) */
    private String todayMarginTotalBalance;
    
    /** 当日(残高(預り金)) */
    private String todayDpositTotalBalance;
    
    /** 当日（必要精算額、必要保証金額） */
    private String todayRequiredSettlementAmountYen;
    
    /** 当日（買付余力） */
    private String todayYenBuyingPowerGeneralAccount;
    
    /** １営業日後（受渡日） */
    private String afterOnedaySettlementDate;
    
    /** １営業日後（預り金(MRF含む)） */
    private String afterOnedayDepositIncludingMrf;
    
    /** １営業日後（保証金現金） */
    private String afterOnedayMargin;
    
    /** １営業日後（預り金） */
    private String afterOnedayYenDeposit;
    
    /** １営業日後（入金額） */
    private String afterOnedayDepositAmount;
    
    /** １営業日後（支払額） */
    private String afterOnedayPayment;
    
    /** １営業日後（未約定信用決済損） */
    private String afterOnedayUncontractedCreditSettlementLoss;
    
    /** １営業日後（未約定買注文額） */
    private String afterOnedayUncontractedPurchaseOrderAmount;
    
    /** １営業日後（出金・振替指示額） */
    private String afterOnedayWithdrawalTransferInstructions;
    
    /** １営業日後（受取額） */
    private String afterOnedayAmountReceived;
    
    /** １営業日後（受取額（日計り分）） */
    private String afterOnedayDayTrading;
    
    /** １営業日後（
    SBIハイブリッド預金精算額) */
    private String afterOnedaySbiHybridDepositSettlementAmount;
    
    /** １営業日後（残高合計額） */
    private String afterOnedayTotalBalance;
    
    /** １営業日後（残高（当社）) */
    private String afterOnedayOurCompanyTotalBalance;
    
    /** １営業日後（残高(SBIハイブリッド預金)) */
    private String afterOnedaySbiHybridDepositSettlementTotalBalance;
    
    /** １営業日後（SBIハイブリッド預金拘束額) */
    private String afterOnedaySbiHybridDepositSettlementRestrictedAmount;
    
    /** １営業日後（残高(保証金現金)) */
    private String afterOnedayMarginTotalBalance;
    
    /** １営業日後（残高(預り金)) */
    private String afterOnedayDpositTotalBalance;
    
    /** １営業日後（必要精算額、必要保証金額） */
    private String afterOnedayRequiredSettlementAmountYen;
    
    /** １営業日後（買付余力） */
    private String afterOnedayYenBuyingPowerGeneralAccount;
    
    /** ２営業日後（受渡日） */
    private String afterTwodaysSettlementDate;
    
    /** ２営業日後（預り金(MRF含む)） */
    private String afterTwodaysDepositIncludingMrf;
    
    /** ２営業日後（保証金現金） */
    private String afterTwodaysMargin;
    
    /** ２営業日後（預り金） */
    private String afterTwodaysYenDeposit;
    
    /** ２営業日後（入金額） */
    private String afterTwodaysDepositAmount;
    
    /** ２営業日後（支払額） */
    private String afterTwodaysPayment;
    
    /** ２営業日後（未約定信用決済損） */
    private String afterTwodaysUncontractedCreditSettlementLoss;
    
    /** ２営業日後（未約定買注文額） */
    private String afterTwodaysUncontractedPurchaseOrderAmount;
    
    /** ２営業日後（出金・振替指示額） */
    private String afterTwodaysWithdrawalTransferInstructions;
    
    /** ２営業日後（受取額） */
    private String afterTwodaysAmountReceived;
    
    /** ２営業日後（受取額（日計り分）） */
    private String afterTwodaysDayTrading;
    
    /** ２営業日後（SBIハイブリッド預金精算額) */
    private String afterTwodaysSbiHybridDepositSettlementAmount;
    
    /** ２営業日後（残高合計額） */
    private String afterTwodaysTotalBalance;
    
    /** ２営業日後（残高（当社）) */
    private String afterTwodaysOurCompanyTotalBalance;
    
    /** ２営業日後（残高(SBIハイブリッド預金)) */
    private String afterTwodaysSbiHybridDepositSettlementTotalBalance;
    
    /** ２営業日後（SBIハイブリッド預金拘束額) */
    private String afterTwodaysSbiHybridDepositSettlementRestrictedAmount;
    
    /** ２営業日後（残高(保証金現金)) */
    private String afterTwodaysMarginTotalBalance;
    
    /** ２営業日後（残高(預り金)) */
    private String afterTwodaysDpositTotalBalance;
    
    /** ２営業日後（必要精算額、必要保証金額） */
    private String afterTwodaysRequiredSettlementAmountYen;
    
    /** ２営業日後（買付余力） */
    private String afterTwodaysYenBuyingPowerGeneralAccount;
    
    /** ３営業日後（受渡日） */
    private String afterThreedaysSettlementDate;
    
    /** ３営業日後（預り金(MRF含む)） */
    private String afterThreedaysDepositIncludingMrf;
    
    /** ３営業日後（保証金現金） */
    private String afterThreedaysMargin;
    
    /** ３営業日後（預り金） */
    private String afterThreedaysYenDeposit;
    
    /** ３営業日後（入金額） */
    private String afterThreedaysDepositAmount;
    
    /** ３営業日後（支払額） */
    private String afterThreedaysPayment;
    
    /** ３営業日後（未約定信用決済損） */
    private String afterThreedaysUncontractedCreditSettlementLoss;
    
    /** ３営業日後（未約定買注文額） */
    private String afterThreedaysUncontractedPurchaseOrderAmount;
    
    /** ３営業日後（出金・振替指示額） */
    private String afterThreedaysWithdrawalTransferInstructions;
    
    /** ３営業日後（受取額） */
    private String afterThreedaysAmountReceived;
    
    /** ３営業日後（受取額（日計り分）） */
    private String afterThreedaysDayTrading;
    
    /** ３営業日後（SBIハイブリッド預金精算額) */
    private String afterThreedaysSbiHybridDepositSettlementAmount;
    
    /** ３営業日後（残高合計額） */
    private String afterThreedaysTotalBalance;
    
    /** ３営業日後（残高（当社）) */
    private String afterThreedaysOurCompanyTotalBalance;
    
    /** ３営業日後（残高(SBIハイブリッド預金)) */
    private String afterThreedaysSbiHybridDepositSettlementTotalBalance;
    
    /** ３営業日後（SBIハイブリッド預金拘束額) */
    private String afterThreedaysSbiHybridDepositSettlementRestrictedAmount;
    
    /** ３営業日後（残高(保証金現金)) */
    private String afterThreedaysMarginTotalBalance;
    
    /** ３営業日後（残高(預り金)) */
    private String afterThreedaysDpositTotalBalance;
    
    /** ３営業日後（必要精算額、必要保証金額） */
    private String afterThreedaysRequiredSettlementAmountYen;
    
    /** ３営業日後（買付余力） */
    private String afterThreedaysYenBuyingPowerGeneralAccount;
    
    /** ４営業日後（受渡日） */
    private String afterFourdaysSettlementDate;
    
    /** ４営業日後（預り金(MRF含む)） */
    private String afterFourdaysDepositIncludingMrf;
    
    /** ４営業日後（保証金現金） */
    private String afterFourdaysMargin;
    
    /** ４営業日後（預り金） */
    private String afterFourdaysYenDeposit;
    
    /** ４営業日後（入金額） */
    private String afterFourdaysDepositAmount;
    
    /** ４営業日後（支払額） */
    private String afterFourdaysPayment;
    
    /** ４営業日後（未約定信用決済損） */
    private String afterFourdaysUncontractedCreditSettlementLoss;
    
    /** ４営業日後（未約定買注文額） */
    private String afterFourdaysUncontractedPurchaseOrderAmount;
    
    /** ４営業日後（出金・振替指示額） */
    private String afterFourdaysWithdrawalTransferInstructions;
    
    /** ４営業日後（受取額） */
    private String afterFourdaysAmountReceived;
    
    /** ４営業日後（受取額（日計り分）） */
    private String afterFourdaysDayTrading;
    
    /** ４営業日後（SBIハイブリッド預金精算額) */
    private String afterFourdaysSbiHybridDepositSettlementAmount;
    
    /** ４営業日後（残高合計額） */
    private String afterFourdaysTotalBalance;
    
    /** ４営業日後（残高（当社）) */
    private String afterFourdaysOurCompanyTotalBalance;
    
    /** ４営業日後（残高(SBIハイブリッド預金)) */
    private String afterFourdaysSbiHybridDepositSettlementTotalBalance;
    
    /** ４営業日後（SBIハイブリッド預金拘束額) */
    private String afterFourdaysSbiHybridDepositSettlementRestrictedAmount;
    
    /** ４営業日後（残高(保証金現金)) */
    private String afterFourdaysMarginTotalBalance;
    
    /** ４営業日後（残高(預り金)) */
    private String afterFourdaysDpositTotalBalance;
    
    /** ４営業日後（必要精算額、必要保証金額） */
    private String afterFourdaysRequiredSettlementAmountYen;
    
    /** ４営業日後（買付余力） */
    private String afterFourdaysYenBuyingPowerGeneralAccount;
    
    /** ５営業日後（受渡日） */
    private String afterFivedaysSettlementDate;
    
    /** ５営業日後（預り金(MRF含む)） */
    private String afterFivedaysDepositIncludingMrf;
    
    /** ５営業日後（保証金現金） */
    private String afterFivedaysMargin;
    
    /** ５営業日後（預り金） */
    private String afterFivedaysYenDeposit;
    
    /** ５営業日後（入金額） */
    private String afterFivedaysDepositAmount;
    
    /** ５営業日後（支払額） */
    private String afterFivedaysPayment;
    
    /** ５営業日後（未約定信用決済損） */
    private String afterFivedaysUncontractedCreditSettlementLoss;
    
    /** ５営業日後（未約定買注文額） */
    private String afterFivedaysUncontractedPurchaseOrderAmount;
    
    /** ５営業日後（出金・振替指示額） */
    private String afterFivedaysWithdrawalTransferInstructions;
    
    /** ５営業日後（受取額） */
    private String afterFivedaysAmountReceived;
    
    /** ５営業日後（受取額（日計り分）） */
    private String afterFivedaysDayTrading;
    
    /** ５営業日後（SBIハイブリッド預金精算額) */
    private String afterFivedaysSbiHybridDepositSettlementAmount;
    
    /** ５営業日後（残高合計額） */
    private String afterFivedaysTotalBalance;
    
    /** ５営業日後（残高（当社）) */
    private String afterFivedaysOurCompanyTotalBalance;
    
    /** ５営業日後（残高(SBIハイブリッド預金)) */
    private String afterFivedaysSbiHybridDepositSettlementTotalBalance;
    
    /** ５営業日後（SBIハイブリッド預金拘束額) */
    private String afterFivedaysSbiHybridDepositSettlementRestrictedAmount;
    
    /** ５営業日後（残高(保証金現金)) */
    private String afterFivedaysMarginTotalBalance;
    
    /** ５営業日後（残高(預り金)) */
    private String afterFivedaysDpositTotalBalance;
    
    /** ５営業日後（必要精算額、必要保証金額） */
    private String afterFivedaysRequiredSettlementAmountYen;
    
    /** ５営業日後（買付余力） */
    private String afterFivedaysYenBuyingPowerGeneralAccount;
    
    /** 当日(JrNISA)（受渡日） */
    private String jrNisaSettlementDate;
    
    /** 当日(JrNISA)（預り金(MRF含む)） */
    private String jrNisaDepositIncludingMrf;
    
    /** 当日(JrNISA)（入金額） */
    private String jrNisaDepositAmount;
    
    /** 当日(JrNISA)（支払額） */
    private String jrNisaPayment;
    
    /** 当日(JrNISA)（未約定買注文額） */
    private String jrNisaUncontractedPurchaseOrderAmount;
    
    /** 当日(JrNISA)（出金・振替指示額） */
    private String jrNisaWithdrawalTransferInstructions;
    
    /** 当日(JrNISA)（受取額） */
    private String jrNisaAmountReceived;
    
    /** 当日(JrNISA)（受取額（日計り分）） */
    private String jrNisaDayTrading;
    
    /** 当日(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）) */
    private String jrNisaPlannedTransferAmount;
    
    /** 当日(JrNISA)(残高合計額) */
    private String jrNisaTotalBalance;
    
    /** 当日(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）) */
    private String jrNisaTransferableAmount;
    
    /** 当日(JrNISA)（必要精算額、必要保証金額） */
    private String jrNisaRequiredSettlementAmountYen;
    
    /** 当日(JrNISA)（買付余力） */
    private String jrNisaYenBuyingPowerGeneralAccount;
    
    /** １営業日後(JrNISA)（受渡日） */
    private String afterOnedayJrNisaSettlementDate;
    
    /** １営業日後(JrNISA)（預り金(MRF含む)） */
    private String afterOnedayJrNisaDepositIncludingMrf;
    
    /** １営業日後(JrNISA)（入金額） */
    private String afterOnedayJrNisaDepositAmount;
    
    /** １営業日後(JrNISA)（支払額） */
    private String afterOnedayJrNisaPayment;
    
    /** １営業日後(JrNISA)（未約定買注文額） */
    private String afterOnedayJrNisaUncontractedPurchaseOrderAmount;
    
    /** １営業日後(JrNISA)（出金・振替指示額） */
    private String afterOnedayJrNisaWithdrawalTransferInstructions;
    
    /** １営業日後(JrNISA)（受取額） */
    private String afterOnedayJrNisaAmountReceived;
    
    /** １営業日後(JrNISA)（受取額（日計り分）） */
    private String afterOnedayJrNisaDayTrading;
    
    /** １営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）) */
    private String afterOnedayJrNisaPlannedTransferAmount;
    
    /** １営業日後(JrNISA)（残高合計額） */
    private String afterOnedayJrNisaTotalBalance;
    
    /** １営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）) */
    private String afterOnedayJrNisaTransferableAmount;
    
    /** １営業日後(JrNISA)（必要精算額、必要保証金額） */
    private String afterOnedayJrNisaRequiredSettlementAmountYen;
    
    /** １営業日後(JrNISA)（買付余力） */
    private String afterOnedayJrNisaYenBuyingPowerGeneralAccount;
    
    /** ２営業日後(JrNISA)（受渡日） */
    private String afterTwodaysJrNisaSettlementDate;
    
    /** ２営業日後(JrNISA)（預り金(MRF含む)） */
    private String afterTwodaysJrNisaDepositIncludingMrf;
    
    /** ２営業日後(JrNISA)（入金額） */
    private String afterTwodaysJrNisaDepositAmount;
    
    /** ２営業日後(JrNISA)（支払額） */
    private String afterTwodaysJrNisaPayment;
    
    /** ２営業日後(JrNISA)（未約定買注文額） */
    private String afterTwodaysJrNisaUncontractedPurchaseOrderAmount;
    
    /** ２営業日後(JrNISA)（出金・振替指示額） */
    private String afterTwodaysJrNisaWithdrawalTransferInstructions;
    
    /** ２営業日後(JrNISA)（受取額） */
    private String afterTwodaysJrNisaAmountReceived;
    
    /** ２営業日後(JrNISA)（受取額（日計り分）） */
    private String afterTwodaysJrNisaDayTrading;
    
    /** ２営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）) */
    private String afterTwodaysJrNisaPlannedTransferAmount;
    
    /** ２営業日後(JrNISA)（残高合計額） */
    private String afterTwodaysJrNisaTotalBalance;
    
    /** ２営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）) */
    private String afterTwodaysJrNisaTransferableAmount;
    
    /** ２営業日後(JrNISA)（必要精算額、必要保証金額） */
    private String afterTwodaysJrNisaRequiredSettlementAmountYen;
    
    /** ２営業日後(JrNISA)（買付余力） */
    private String afterTwodaysJrNisaYenBuyingPowerGeneralAccount;
    
    /** ３営業日後(JrNISA)（受渡日） */
    private String afterThreedaysJrNisaSettlementDate;
    
    /** ３営業日後(JrNISA)（預り金(MRF含む)） */
    private String afterThreedaysJrNisaDepositIncludingMrf;
    
    /** ３営業日後(JrNISA)（入金額） */
    private String afterThreedaysJrNisaDepositAmount;
    
    /** ３営業日後(JrNISA)（支払額） */
    private String afterThreedaysJrNisaPayment;
    
    /** ３営業日後(JrNISA)（未約定買注文額） */
    private String afterThreedaysJrNisaUncontractedPurchaseOrderAmount;
    
    /** ３営業日後(JrNISA)（出金・振替指示額） */
    private String afterThreedaysJrNisaWithdrawalTransferInstructions;
    
    /** ３営業日後(JrNISA)（受取額） */
    private String afterThreedaysJrNisaAmountReceived;
    
    /** ３営業日後(JrNISA)（受取額（日計り分）） */
    private String afterThreedaysJrNisaDayTrading;
    
    /** ３営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）) */
    private String afterThreedaysJrNisaPlannedTransferAmount;
    
    /** ３営業日後(JrNISA)（残高合計額） */
    private String afterThreedaysJrNisaTotalBalance;
    
    /** ３営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）) */
    private String afterThreedaysJrNisaTransferableAmount;
    
    /** ３営業日後(JrNISA)（必要精算額、必要保証金額） */
    private String afterThreedaysJrNisaRequiredSettlementAmountYen;
    
    /** ３営業日後(JrNISA)（買付余力） */
    private String afterThreedaysJrNisaYenBuyingPowerGeneralAccount;
    
    /** ４営業日後(JrNISA)（受渡日） */
    private String afterFourdaysJrNisaSettlementDate;
    
    /** ４営業日後(JrNISA)（預り金(MRF含む)） */
    private String afterFourdaysJrNisaDepositIncludingMrf;
    
    /** ４営業日後(JrNISA)（入金額） */
    private String afterFourdaysJrNisaDepositAmount;
    
    /** ４営業日後(JrNISA)（支払額） */
    private String afterFourdaysJrNisaPayment;
    
    /** ４営業日後(JrNISA)（未約定買注文額） */
    private String afterFourdaysJrNisaUncontractedPurchaseOrderAmount;
    
    /** ４営業日後(JrNISA)（出金・振替指示額） */
    private String afterFourdaysJrNisaWithdrawalTransferInstructions;
    
    /** ４営業日後(JrNISA)（受取額） */
    private String afterFourdaysJrNisaAmountReceived;
    
    /** ４営業日後(JrNISA)（受取額（日計り分）） */
    private String afterFourdaysJrNisaDayTrading;
    
    /** ４営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）) */
    private String afterFourdaysJrNisaPlannedTransferAmount;
    
    /** ４営業日後(JrNISA)（残高合計額） */
    private String afterFourdaysJrNisaTotalBalance;
    
    /** ４営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）) */
    private String afterFourdaysJrNisaTransferableAmount;
    
    /** ４営業日後(JrNISA)（必要精算額、必要保証金額） */
    private String afterFourdaysJrNisaRequiredSettlementAmountYen;
    
    /** ４営業日後(JrNISA)（買付余力） */
    private String afterFourdaysJrNisaYenBuyingPowerGeneralAccount;
    
    /** ５営業日後(JrNISA)（受渡日） */
    private String afterFivedaysJrNisaSettlementDate;
    
    /** ５営業日後(JrNISA)（預り金(MRF含む)） */
    private String afterFivedaysJrNisaDepositIncludingMrf;
    
    /** ５営業日後(JrNISA)（入金額） */
    private String afterFivedaysJrNisaDepositAmount;
    
    /** ５営業日後(JrNISA)（支払額） */
    private String afterFivedaysJrNisaPayment;
    
    /** ５営業日後(JrNISA)（未約定買注文額） */
    private String afterFivedaysJrNisaUncontractedPurchaseOrderAmount;
    
    /** ５営業日後(JrNISA)（出金・振替指示額） */
    private String afterFivedaysJrNisaWithdrawalTransferInstructions;
    
    /** ５営業日後(JrNISA)（受取額） */
    private String afterFivedaysJrNisaAmountReceived;
    
    /** ５営業日後(JrNISA)（受取額（日計り分）） */
    private String afterFivedaysJrNisaDayTrading;
    
    /** ５営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）) */
    private String afterFivedaysJrNisaPlannedTransferAmount;
    
    /** ５営業日後(JrNISA)（残高合計額） */
    private String afterFivedaysJrNisaTotalBalance;
    
    /** ５営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）) */
    private String afterFivedaysJrNisaTransferableAmount;
    
    /** ５営業日後(JrNISA)（必要精算額、必要保証金額） */
    private String afterFivedaysJrNisaRequiredSettlementAmountYen;
    
    /** ５営業日後(JrNISA)（買付余力） */
    private String afterFivedaysJrNisaYenBuyingPowerGeneralAccount;
    
    /** 一般総合口座フラグ */
    private String generalAccountFlag;
    
    /** ジュニア総合口座フラグ */
    private String jrGeneralAccountFlag;
    
    /** ジュニアNISA口座フラグ */
    private String jrNisageneralAccountFlag;
    
    /** SBIハイブリッドフラグ */
    private String sbiHybridFlag;

}
