package com.sbisec.helios.ap.athena.protocol.fstock.order;

/**
 * Description 外国株式取引サービス - 外国株式現地営業日取得API（日数指定版） Response.
 * 
 * @author yunhui.zhao
 * @date 02/14/2022
 */
public class GetOffsetBusinessDateResp {
    
    public GetOffsetBusinessDateResp() {
    
    }
    
    // 営業日(yyyy-MM-dd)
    private String businessDate;
    
    /**
     * @return businessDate 営業日(yyyy-MM-dd)
     */
    public String getBusinessDate() {
        
        return businessDate;
    }
    
    public void setBusinessDate(String businessDate) {
        
        this.businessDate = businessDate;
    }
}
