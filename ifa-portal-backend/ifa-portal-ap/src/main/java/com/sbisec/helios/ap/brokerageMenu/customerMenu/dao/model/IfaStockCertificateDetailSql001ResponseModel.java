package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;



import lombok.Data;

@Data
public class IfaStockCertificateDetailSql001ResponseModel {
    /** 総数 */
    private int totalCount;
    /** 銘柄コード */
    private String brandCode;
    /** 銘柄名  */
    private String brandName;
    /** 券種 */
    private String stockCoupon;
    /** 記号 */
    private String stockMark;
    /** 売却可能日 */
    private String stockNumber;
    /** 受付日 */
    private String acceptDate;
    /** 返却日 */
    private String returnDate;
    /** 返却理由 */
    private String returnReason;
}
