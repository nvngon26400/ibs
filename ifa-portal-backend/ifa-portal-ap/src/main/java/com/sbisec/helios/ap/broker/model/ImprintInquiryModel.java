package com.sbisec.helios.ap.broker.model;

import com.sbibits.earth.model.ModelBase;

/**
 * 印影照会　
 * 帳票ファイル取得
 *
 * @author SCSK
 *
 */
public class ImprintInquiryModel extends ModelBase {
    
    /**
     * シリアルバージョンID
     */
    private static final long serialVersionUID = 3317777499668423574L;
    
    /* ↓↓↓ 顧客情報 ↓↓↓ */
    // 部店コード
    private String butenCode;
    
    // 口座番号
    private String accountNumber;
    
    // 顧客名（漢字）
    private String nameKanji;
    
    // 顧客名（カナ）
    private String nameKana;
    
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
    
    public String getButenCode() {
        
        return butenCode;
    }
    
    public void setButenCode(String butenCode) {
        
        this.butenCode = butenCode;
    }
    
    public String getAccountNumber() {
        
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        
        this.accountNumber = accountNumber;
    }
    
    public String getNameKanji() {
        
        return nameKanji;
    }
    
    public void setNameKanji(String nameKanji) {
        
        this.nameKanji = nameKanji;
    }
    
    public String getNameKana() {
        
        return nameKana;
    }
    
    public void setNameKana(String nameKana) {
        
        this.nameKana = nameKana;
    }
    
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
