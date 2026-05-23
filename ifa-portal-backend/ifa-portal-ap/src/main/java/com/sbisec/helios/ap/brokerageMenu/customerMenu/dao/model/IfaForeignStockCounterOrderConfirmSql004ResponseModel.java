package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderConfirmSql004ResponseModel {

    /** 名称(上限約定金額) */
    private BigDecimal upperLimitContractAmount;

}
