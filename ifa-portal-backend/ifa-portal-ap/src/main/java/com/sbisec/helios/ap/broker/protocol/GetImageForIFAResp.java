package com.sbisec.helios.ap.broker.protocol;

public class GetImageForIFAResp {
    
    public GetImageForIFAResp() {
    
    }
    
    // 帳票コード
    private String reportCode;
    
    //　帳票グループ名
    private String reportGroup;
    
    // 帳票名
    private String reportName;
    
    // 受入基準日
    private String acceptDate;
    
    // システム登録日
    private String registerDate;
    
    // 帳票ファイル
    private String reportFile;
    
    // 帳票ファイルの幅
    private String width;
    
    // 帳票ファイルの高さ
    private String height;
    
    // エラーコード
    private String errorCode;
    
    // エラーメッセージ
    private String errorMessage;
    
    public String getReportCode() {
        
        return reportCode;
    }
    
    public void setReportCode(String reportCode) {
        
        this.reportCode = reportCode;
    }
    
    public String getReportGroup() {
        
        return reportGroup;
    }
    
    public void setReportGroup(String reportGroup) {
        
        this.reportGroup = reportGroup;
    }
    
    public String getReportName() {
        
        return reportName;
    }
    
    public void setReportName(String reportName) {
        
        this.reportName = reportName;
    }
    
    public String getAcceptDate() {
        
        return acceptDate;
    }
    
    public void setAcceptDate(String acceptDate) {
        
        this.acceptDate = acceptDate;
    }
    
    public String getRegisterDate() {
        
        return registerDate;
    }
    
    public void setRegisterDate(String registerDate) {
        
        this.registerDate = registerDate;
    }
    
    public String getReportFile() {
        
        return reportFile;
    }
    
    public void setReportFile(String reportFile) {
        
        this.reportFile = reportFile;
    }
    
    public String getWidth() {
        
        return width;
    }
    
    public void setWidth(String width) {
        
        this.width = width;
    }
    
    public String getHeight() {
        
        return height;
    }
    
    public void setHeight(String height) {
        
        this.height = height;
    }
    
    public String getErrorCode() {
        
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        
        this.errorCode = errorCode;
    }
    
    public String getErrorMessage() {
        
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        
        this.errorMessage = errorMessage;
    }
}
