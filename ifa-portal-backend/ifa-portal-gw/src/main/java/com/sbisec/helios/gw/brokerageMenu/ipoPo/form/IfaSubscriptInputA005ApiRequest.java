package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/5/10 新規作成
 *
 * @author SCSK丹波
 */
@Data
public class IfaSubscriptInputA005ApiRequest {
    
    /** 銘柄コード（BB）. */
    private String brandCode;
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** ブックビルディング申込期間（開始）. */
    private String bookBuildingPresentationFrom;
    
    /** 明細番号. */
    private String detailNumber;
    
    /** 抽選結果. */
    private String lotteryResult;
    
    /** 当選株数. */
    private String bbQuantityAlloc;
    
    /** 注文状況. */
    private String orderStatus;
    
    /** 数量. */
    private String quantity;
    
    /** 預り区分. */
    private String depositType;
    
    /** 勧誘区分. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String jutyuKbn;
    
    /** 目論見書の交付方法. */
    private String mokuromiKoufuKbn;
    
    /** 重要事項の説明. */
    private String importantMatterType;
    
    /** 備考. */
    private String bbRemark;
    
}
