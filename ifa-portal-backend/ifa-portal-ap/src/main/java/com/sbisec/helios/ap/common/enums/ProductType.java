package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum ProductType {

	ALL_ITEMS                  (1, "全商品"),
    DOMESTIC_STOCK_GENERAL     (2, "国内株式全般"),
    DOMESTIC_STOCKS_IN_KIND    (3, "国内株式現物"),
    DOMESTIC_STOCK_CREDIT      (4, "国内株式信用"),
    DOMESTIC_INVESTMENT_TRUSTS (5, "国内投信"),
    YEN_DENOMINATED_BOND       (6, "円貨建債券"),
    FOREIGN_EQUITY             (7, "外国株式"),
    FOREIGN_INVESTMENT_TRUSTS  (8, "外国投信"),
    FOREIGN_BOND               (9, "外貨建債券");

    private final int id;
    private final String label;

    private ProductType(int id, String label) {

        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}