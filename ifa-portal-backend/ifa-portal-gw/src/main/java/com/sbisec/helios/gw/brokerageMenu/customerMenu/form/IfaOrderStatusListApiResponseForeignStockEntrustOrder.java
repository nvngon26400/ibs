package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0202_0104-01
 * 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *          2023/10/16 新規作成
 */

@Data
public class IfaOrderStatusListApiResponseForeignStockEntrustOrder {
    
    /** 国籍コード（全角半角）. */
    private String countryCd;
    
    /** 外国株式委託注文リスト. */
    private List<IfaOrderStatusListApiResponseFsEntrustOrderStatus> fsEntrustOrderStatusList;
    
    /** 外株委託注文件数（算出）. */
    private String fsEntrustOrderStatusCount;
    
}
