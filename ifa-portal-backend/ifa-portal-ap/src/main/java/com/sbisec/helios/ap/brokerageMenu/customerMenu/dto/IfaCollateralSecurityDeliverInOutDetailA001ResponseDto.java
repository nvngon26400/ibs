package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 初期化 レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaCollateralSecurityDeliverInOutDetailA001ResponseDto {

    /** 受渡日. */
    private String settlementDate;

    /** 表示基準日（受渡日）. */
    private String displayBaseDate;

    /** 入出庫区分. */
    private String deliverInOutClassification;

    /** 代用評価金額合計. */
    private String collateralValuationTotal;

    /** 代用有価証券明細部リスト. */
    private List<Detail> detailList;

    /**
     * 代用有価証券明細部情報
     *
     * @author SCSK
     */
    @Data
    public static class Detail {
        /** 商品分類（全角半角）. */
        private String securityClass;

        /** 銘柄コード（半角英数字）. */
        private String brandCode;

        /** 銘柄名（全角半角）. */
        private String brandName;

        /** 預り区分（全角半角）. */
        private String depositType;

        /** 残高数量（数値(整数)）. */
        private String contPosition;

        /** 評価単価. */
        private String valuationPrice;

        /** 代用掛目（数値(整数)）. */
        private String collateralAssessment;

        /** 代用評価額. */
        private String collateralValuation;
    }
}
