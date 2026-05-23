package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import lombok.Data;

/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaCommFeeA002CommFeeResponseDto {

    /** 年月日 */
    private String dateYmd;

    /** 年月 */
    private String dateYm;

    /** 仲介業者コード（数字） */
    private String brokerCode;

    /** 仲介業者名（全角半角） */
    private String brokerName;

    /** 営業員コード（半角英数字） */
    private String empCode;

    /** 営業員名（全角半角） */
    private String brokerChargeName;

    /** 扱者コード（半角英数字） */
    private String dealerNumber;

    /** 取引コース（全角半角） */
    private String course;

    /** 国内株式 現物（数値(小数)） */
    private String domesticStockSpot;

    /** 国内株式 信用（数値(小数)） */
    private String domesticStockMargin;

    /** 国内株式 IPO・PO（数値(小数)） */
    private String domesticStockIpoPo;

    /** 国内CB（数値(小数)） */
    private String domesticCb;

    /** 国内投信 販売（数値(小数)） */
    private String domesticMutualFundSales;

    /** 国内投信 信報（数値(小数)） */
    private String domesticMutualFundTrustFee;

    /** 外国投信 販売（数値(小数)） */
    private String foreignMutualFundSales;

    /** 外国投信 信報（数値(小数)） */
    private String foreignMutualFundTrustFee;

    /** 外国投信 信報（その他）（数値(小数)） */
    private String foreignMutualFundOtherTrustFee;

    /** 先物・OP（数値(小数)） */
    private String futuresOptions;

    /** 国内債券（数値(小数)） */
    private String domesticBond;

    /** 外国債券（円建）（数値(小数)） */
    private String jpyForeignBond;

    /** 外国債券（外貨建）（数値(小数)） */
    private String foreignBond;

    /** 外国株式（数値(小数)） */
    private String foreignStock;

    /** 米株信用（数値(小数)） */
    private String americaStockMargin;

    /** ST（数値(小数)） */
    private String st;

    /** ST信報（数値(小数)） */
    private String stSintaku;

    /** 外貨建MMF 信報（数値(小数)） */
    private String foreignMmfTrustFee;

    /** 為替取引（数値(整数)） */
    private String fxTrade;

    /** 投信（SBIラップ）マイレージ（数値(小数)） */
    private String mutualFundSbiWrapMileage;

    /** 投信マイレージ（数値(小数)） */
    private String mutualFundMileage;

    /** 現株ポイント（数値(小数)） */
    private String spotStockPoint;

    /** SBIラップ管理報酬(ネット) */
    private String sbiRap01;

    /** SBIラップ管理報酬(店頭) */
    private String sbiRap02;

    /** SBIラップ管理報酬(匠) */
    private String sbiRap03;

    /** SBIラップ管理報酬(x投資) */
    private String sbiRap04;

    /** SBIラップコース5 */
    private String sbiRap05;

    /** SBIラップコース6 */
    private String sbiRap06;

    /** SBIラップコース7 */
    private String sbiRap07;

    /** SBIラップコース8 */
    private String sbiRap08;

    /** SBIラップコース9 */
    private String sbiRap09;

    /** SBIラップコース10 */
    private String sbiRap10;

    /** SBIラップコース11 */
    private String sbiRap11;

    /** SBIラップコース12 */
    private String sbiRap12;

    /** SBIラップコース13 */
    private String sbiRap13;

    /** SBIラップコース14 */
    private String sbiRap14;

    /** SBIラップコース15 */
    private String sbiRap15;

    /** SBIラップコース16 */
    private String sbiRap16;

    /** SBIラップコース17 */
    private String sbiRap17;

    /** 小計（数値(小数)） */
    private String rewardSubtotal;

    /** その他1 */
    private String other1;

    /** その他2 */
    private String other2;

    /** その他3 */
    private String other3;

    /** その他4 */
    private String other4;

    /** その他5 */
    private String other5;

    /** その他6 */
    private String other6;

    /** 手数料合計（数値(小数)） */
    private String commTotal;

    /** 合計（数値(整数)） */
    private String total;

    /** 消費税（数値(整数)） */
    private String consumptionTax;

    /** 支払報酬額（数値(小数)） */
    private String paymentFeeAmount;

    /** 支店コード（数字） */
    private String branchCode;

    /** 支店名（全角半角） */
    private String branchName;

}
