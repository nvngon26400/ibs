package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-05_1
 * 画面名：信用一括返済入力
 * 2024/04/15 新規作成
 *
 * @author SCSK 池亀緑
 */
@Data
public class IfaMarginMassRepayInputA003ResponseDto {
    
    /** 建玉件数 */
    private String positionCount;
    
    /** 建玉件数（東証） */
    private String positionCountTokyoSecurity;
    
    /** 建玉件数（PTS） */
    private String positionCountPts;
    
    /** 東証明細.建玉金額合計 */
    private String tokyoSecurityDetailTotalPrice;
    
    /** 東証明細.評価額合計（前日） */
    private String tokyoSecurityDetailValuationTotalPreviousDay;
    
    /** 東証明細.評価額合計（リアル） */
    private String tokyoSecurityDetailValuationTotalReal;
    
    /** 東証明細.諸経費合計 */
    private String tokyoSecurityDetailCostTotalYen;
    
    /** 東証明細.評価損益合計（前日） */
    private String tokyoSecurityDetailDomesticPositionValuationTotalPreviousDay;
    
    /** 東証明細.評価損益合計（リアル） */
    private String tokyoSecurityDetailDomesticPositionValuationTotalReal;
    
    /** PTS明細.建玉金額合計 */
    private String ptsDetailTotalPrice;
    
    /** PTS明細.評価額合計（前日） */
    private String ptsDetailValuationTotalPreviousDay;
    
    /** PTS明細.評価額合計（リアル） */
    private String ptsDetailValuationTotalReal;
    
    /** PTS明細.諸経費合計 */
    private String ptsDetailCostTotalYen;
    
    /** PTS明細.評価損益合計（前日） */
    private String ptsDetailDomesticPositionValuationTotalPreviousDay;
    
    /** PTS明細.評価損益合計（リアル） */
    private String ptsDetailDomesticPositionValuationTotalReal;
    
    /** 注文可能株数 */
    private String orderAbleStockQuantity;
    
    /** 建玉明細 */
    private List<IfaMarginMassRepayInputDtoPositionDetail> positionDetailList;
}