package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * A001 レスポンスDTO
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioA001ResponseDto {

    /** 資産状況サマリリスト */
    private List<IfaPortfolioA001PortfolioSummaryResponseDto> portfolioSummaryList;

    /** 資産状況サマリ評価額合計 */
    private String portfolioSummaryValuationTotal;

    /** 資産状況サマリ評価損益合計 */
    private String portfolioSummaryProfitAndLossTotal;

    /** 保有商品一覧_国内株式リスト */
    private List<IfaPortfolioA001HoldingSecurityDomesticStockResponseDto> holdingSecurityDomesticStock;

    /** 国内株式評価損益合計 */
    private String domesticStockProfitAndLossTotal;

    /** 保有商品一覧_国内債券リスト */
    private List<IfaPortfolioA001HoldingSecurityListDomesticClaimResponseDto> holdingSecurityListDomesticClaim;

    /** 国内債券評価損益合計 */
    private String domesticClaimProfitAndLossTotal;

    /** 保有商品一覧_投資信託リスト */
    private List<IfaPortfolioA001HoldingSecurityListMutualFundResponseDto> holdingSecurityListMutualFund;

    /** 投資信託評価損益合計 */
    private String mutualFundProfitAndLossTotal;

    /** 保有商品一覧_外国債券(円建)リスト */
    private List<IfaPortfolioA001HoldingSecurityListForeignClaimYenBaseResponseDto> holdingSecurityListForeignClaimYenBase;

    /** 外国債券(円建)評価損益合計 */
    private String foreignClaimYenBaseProfitAndLossTotal;

    /** 保有商品一覧_外国株式リスト */
    private List<IfaPortfolioA001HoldingSecurityListForeignStockResponseDto> holdingSecurityListForeignStock;

    /** 外国株式評価損益合計 */
    private String foreignStockProfitAndLossTotal;

    /** 保有商品一覧_外貨建MMFリスト */
    private List<IfaPortfolioA001HoldingSecurityListForeignMmfResponseDto> holdingSecurityListForeignMmf;

    /** 外貨建MMF評価損益合計 */
    private String foreignMmfProfitAndLossTotal;

    /** 保有商品一覧_STリスト */
    private List<IfaPortfolioA001HoldingSecurityListSecurityTokenResponseDto> holdingSecurityListSecurityToken;

    /** ST評価損益合計 */
    private String securityTokenProfitAndLossTotal;

    /** 保有商品一覧_外国債券(外貨建)リスト */
    private List<IfaPortfolioA001HoldingSecurityListForeignClaimForeignResponseDto> holdingSecurityListForeignClaimForeign;

    /** 外国債券(外貨建)評価損益合計 */
    private String foreignClaimForeignProfitAndLossTotal;

    /** 保有商品一覧_外国債券(外貨建仕組債)リスト */
    private List<IfaPortfolioA001HoldingSecurityListForeignClaimForeignStructuredBondResponseDto> holdingSecurityListForeignClaimForeignStructuredBond;

    /** 外国債券(外貨建仕組債)評価損益合計 */
    private String foreignClaimForeignStructuredBondProfitAndLossTotal;

    /** 保有商品一覧_現金リスト */
    private List<IfaPortfolioA001HoldingSecurityListCashResponseDto> holdingSecurityListCash;

    /** 保有商品一覧_SBIラップ口座現金 */
    private List<IfaPortfolioA001HoldingSecurityListSbiRapAccountCashResponseDto> holdingSecurityListSbiRapAccountCash;

    /** 保有商品一覧_外貨預金リスト */
    private List<IfaPortfolioA001HoldingSecurityListForeignDepositResponseDto> holdingSecurityListForeignDeposit;

    /** 現金評価損益合計 */
    private String cashProfitAndLossTotal;

    /** 維持率（国内信用）（数値(小数)） */
    private String domesticMarginActualGrntRate;

    /** 保有商品一覧_信用建玉リスト */
    private List<IfaPortfolioA001HoldingSecurityListMarginPositionResponseDto> holdingSecurityListMarginPosition;

    /** 信用建玉評価損益合計 */
    private String marginPositionProfitAndLossTotal;

    /** 維持率（米株信用）（数値(小数)） */
    private String americaMarginActualGrntRate;

    /** 保有商品一覧_米株信用建玉リスト */
    private List<IfaPortfolioA001HoldingSecurityListUsStockMarginPositionResponseDto> holdingSecurityListUsStockMarginPositionList;

    /** 米株信用建玉評価損益合計 */
    private String usStockMarginPositionProfitAndLossTotal;

    /** 保有商品一覧_投資信託トータルリターンリスト */
    private List<IfaPortfolioA001HoldingSecurityListMutualFundTotalReturnResponseDto> holdingSecurityListMutualFundTotalReturnList;

    /** 投資信託トータルリターン評価金額「円」 */
    private String mutualFundTotalReturnValuationTotal;

    /** 投資信託トータルリターン売却金額「円」 */
    private String sellPriceTotal;

    /** 投資信託トータルリターン分配金額「円」 */
    private String dividendTotal;

    /** 投資信託トータルリターン買付金額「円」合計 */
    private String buyPriceTotal;

    /** 投資信託トータルリターントータルリターン「円」合計 */
    private String totalReturnYenTotal;

    /** 投資信託トータルリターントータルリターン「率」 */
    private String totalReturnRateTotal;

    /** ジョブステータス */
    private String jobStatus;
}
