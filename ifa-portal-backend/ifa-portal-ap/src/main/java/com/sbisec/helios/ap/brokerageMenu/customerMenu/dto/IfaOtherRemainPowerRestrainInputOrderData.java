package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0110-01_1
 * 画面名：その他余力拘束注文入力
 *
 * @author 大連 えん
 *
 *          2025/02/28 新規作成
 */

@Data
public class IfaOtherRemainPowerRestrainInputOrderData {
    
    /** その他余力拘束状況リクエスト.EC受注番号 */
    private String orderNo;
    
    /** その他余力拘束状況リクエスト.口座区分 */
    private String accountType;
    
    /** その他余力拘束状況リクエスト.拘束区分 */
    private String restrainType;
    
    /** その他余力拘束状況リクエスト.金額（買付余力） */
    private String netAmount;
    
    /** その他余力拘束状況リクエスト.金額（NISA成長投資枠） */
    private String isaSeityoLimitAmount;
    
    /** その他余力拘束状況リクエスト.金額（NISAつみたて投資枠） */
    private String isaTsumitateLimitAmount;
    
    /** その他余力拘束状況リクエスト.拘束開始日 */
    private String restrainDateFrom;
    
    /** その他余力拘束状況リクエスト.拘束期限 */
    private String restrainDateTo;
    
    /**TODO その他余力拘束状況リクエスト.拘束理由 */
    private String restrainReason;
    
    /** その他余力拘束状況リクエスト.受注日時 */
    private String acceptDateTime;
    
    /** その他余力拘束状況リクエスト.発注日 */
    private String orderDate;
    
    /** その他余力拘束状況リクエスト.商品区分 */
    private String secId;
    
    /** その他余力拘束状況リクエスト.受付経路区分 */
    private String callcenterId;
    
    /** その他余力拘束状況リクエスト.有効区分 */
    private String validId;
    
    /** その他余力拘束状況リクエスト.取消区分 */
    private String orderId;
    
    /** その他余力拘束状況リクエスト.取消区分(DB) */
    private String torikeshiKbn;
    
}
