package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

/**
 * 保証金振替予定情報 Dto.
 * 
 * @author shuchen.xin
 * @date 02/22/2022
 */
public class MarginTransferExpectation implements Serializable {
    
    private static final long serialVersionUID = 7309038933609771999L;
    
    public MarginTransferExpectation() {
    
    }
    
    // 保証金振替予定日
    private String settlementDate;
    
    // 保証金引出予定額
    private String estimatedPayout;
    
    // 保証金受入予定額
    private String estimatedReceipt;
    
    // 確定フラグ
    private Boolean realized;
    
    /**
     * @return 保証金振替予定日
     */
    public String getSettlementDate() {
        
        return settlementDate;
    }
    
    /**
     * @param settlementDate 保証金振替予定日
     */
    public void setSettlementDate(String settlementDate) {
        
        this.settlementDate = settlementDate;
    }
    
    /**
     * @return 保証金引出予定額
     */
    public String getEstimatedPayout() {
        
        return estimatedPayout;
    }
    
    /**
     * @param estimatedPayout 保証金引出予定額
     */
    public void setEstimatedPayout(String estimatedPayout) {
        
        this.estimatedPayout = estimatedPayout;
    }
    
    /**
     * @return 保証金受入予定額
     */
    public String getEstimatedReceipt() {
        
        return estimatedReceipt;
    }
    
    /**
     * @param estimatedReceipt 保証金受入予定額
     */
    public void setEstimatedReceipt(String estimatedReceipt) {
        
        this.estimatedReceipt = estimatedReceipt;
    }
    
    /**
     * @return 確定フラグ
     */
    public Boolean getRealized() {
        
        return realized;
    }
    
    /**
     * @param realized 確定フラグ
     */
    public void setRealized(Boolean realized) {
        
        this.realized = realized;
    }
}
