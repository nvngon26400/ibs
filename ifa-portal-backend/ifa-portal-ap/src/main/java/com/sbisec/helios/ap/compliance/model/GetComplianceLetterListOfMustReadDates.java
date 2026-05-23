package com.sbisec.helios.ap.compliance.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class GetComplianceLetterListOfMustReadDates extends ModelBase {

    private String communicationDate;
    private String lecId;
    private String honbanFlg;
    private String confirmationFlg;
    private String browseFlg;
    
    public String getCommunicationDate() {
        return communicationDate;
    }
    public void setCommunicationDate(String communicationDate) {
        this.communicationDate = communicationDate;
    }
    public String getLecId() {
        return lecId;
    }
    public void setLecId(String lecId) {
        this.lecId = lecId;
    }
    public String getHonbanFlg() {
        
        return honbanFlg;
    }
    public void setHonbanFlg(String honbanFlg) {
        
        this.honbanFlg = honbanFlg;
    }
    public String getConfirmationFlg() {
        
        return confirmationFlg;
    }
    public void setConfirmationFlg(String confirmationFlg) {
        
        this.confirmationFlg = confirmationFlg;
    }
    public String getBrowseFlg() {
        
        return browseFlg;
    }
    public void setBrowseFlg(String browseFlg) {
        
        this.browseFlg = browseFlg;
    }
}
