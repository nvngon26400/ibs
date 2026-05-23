package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import java.util.Date;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-05
 * 画面名：STARアップロードファイル出力一覧
 *
 * @author SCSK
 * 
 */
@Data
public class IfaStarUploadFileOutputListA001FileOutputListApiResponse {
    
    // 銘柄コード
    private String bbProductCode;
    
    // 銘柄名
    private String bbProductName;
    
    // 仲介業者コード
    private String brokerCode;
    
    // 仲介業者名
    private String brokerName;
    
    // 支店コード
    private String branchCode;
    
    // 支店名
    private String branchName;
    
    // 営業員コード
    private String intermediaryEmpCd;
    
    // 営業員名
    private String brokerChargeName;
    
    // 部店
    private String butenCode;
    
    // 口座番号
    private String accountNumber;
    
    // 顧客名（漢字）
    private String nameKanji;
    
    // 顧客名（カナ）
    private String nameKana;
    
    // 投資家属性
    private String bbInvestorAttName;
    
    // BB申込株数
    private String bbQuantity;
    
    // 申込価格
    private String bbPrice;
    
    // 裁量配分希望数量
    private String quantitySairyou;

    // 当選株数
    private String bbQuantityAlloc;
    
    // 抽選結果
    private String lotteryResult;

    // 注文状況
    private String orderStatus;
    
    // 注文株数
    private String orderCount;
    
    // 預り区分
    private String depositType;
    
    // 申込日時
    private String bbCreateDate;
    
    // 申込者
    private String bbCreateUserName;   
    
    // セクション名
    private String bbCreateSectionName;    
 
    // 備考
    private String bbRemark;
    
    // 更新日
    private Date bbUpdateDate;
}