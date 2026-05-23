
package com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class ModifyEmailAddressForCertifyModel extends ModelBase {

    private String userId;
    private String branchName;
    private String brokerName;
    private String mailAddress;
    private String userNm;
    private int totalRow;
    private String updateBy;

    public String getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    public String getUserNm() {
        return userNm;
    }
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
    public int getTotalRow() {
        return totalRow;
    }
    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String getBrokerName() {
        return brokerName;
    }
    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}

