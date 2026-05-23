package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA006DtoRequest;

import lombok.Data;

/**
 * SUB0202_0212-08_1_現引現渡注文入力
 *
 * @author 池亀緑
 */
@Data
public class IfaReceiptDeliveryOrderInputA006ApiResponse {
    
    /** リクエスト内容 */
    private IfaReceiptDeliveryOrderInputA006DtoRequest req;

    /** 残高数量 */
    private String contPosition;
    
    /** 返済注文済未出来数量 */
    private String unactualQuantity;

    /** 特定建玉区分 */
    private String tokuteiContractId;

    /** 約定金額 */
    private String contractAmount;

    /** 手数料 */
    private String charge;

    /** 消費税 */
    private String consumptionTax;

    /** 譲渡益税 */
    private String yieldTax;

    /** 精算金額 */
    private String settlementAmount;

    /** 約定予定日 */
    private String contractDate;

    /** 受渡予定日 */
    private String deliveryDate;

    /** 受注日 */
    private String orderDate;

    /** 受注時刻 */
    private String orderTime;

    /** 種別 */
    private String shubetu;

    /** エラーコード */
    private String code;

    /** エラーメッセージ */
    private String errMessage;

    /** 注意情報アラート */
    private String noticeInfoAlert;

    /** お知らせアラート */
    private String noticeAlert;

    /** 内部者確認メッセージ */
    private String insiderConfirmMsg;

    /** 取引注意情報（銘柄）メッセージ */
    private String tradeNoticeInfoBrandMsg;

    /** 銘柄名 */
    private String brandName;

    private List<String> warningList;
}
