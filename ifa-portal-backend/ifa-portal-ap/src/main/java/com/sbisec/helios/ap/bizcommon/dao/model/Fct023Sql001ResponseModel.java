package com.sbisec.helios.ap.bizcommon.dao.model;

import java.math.BigDecimal;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 共通関数ResponseModel：FCT023.SQL001
 *
 * @author 陳
 */
@Data
public class Fct023Sql001ResponseModel extends ModelBase {

    // ファンド正式名
    private String fundOfficalName;

    // 基準価額単位
    private BigDecimal basePriceUnit;

    // 販売最低口数
    private BigDecimal numberOfSalesMinimum;

    // 販売最小単位金額(2回目以降)
    private BigDecimal salesMinimunUnitPriceAfter2nd;

    // 販売単位口数
    private BigDecimal numberOfSalesUnit;

    // 販売売買単位金額(2回目以降)
    private BigDecimal salesTradingUnitPriceAfter2nd;

    // 解約売買単位口数
    private BigDecimal numberOfCancelTradingUnit;

    // 解約売買単位金額(2回目以降)
    private BigDecimal cancelTradingUnitPriceAfter2nd;

    // 注文締切時間
    private String orderDeadlineTime;

    // 売却方法
    private String saleMethod;

    // ファンドタイプ
    private String fundType;

    // 再投資区分
    private String reinvestmentDivison;

    // 特殊区分
    private String specialType;

}
