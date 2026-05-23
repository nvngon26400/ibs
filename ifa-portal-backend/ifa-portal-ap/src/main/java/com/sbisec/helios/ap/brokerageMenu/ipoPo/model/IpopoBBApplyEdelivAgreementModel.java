package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoBBApplyEdelivAgreementModel extends ModelBase {
    // 電子交付承諾区分
    private String edelivAgreementKbn;
    // 電子交付承諾日付
    private String edelivAgreementDate;

    public String getEdelivAgreementKbn() {
        return edelivAgreementKbn;
    }

    public void setEdelivAgreementKbn(String edelivAgreementKbn) {
        this.edelivAgreementKbn = edelivAgreementKbn;
    }

    public String getEdelivAgreementDate() {
        return edelivAgreementDate;
    }

    public void setEdelivAgreementDate(String edelivAgreementDate) {
        this.edelivAgreementDate = edelivAgreementDate;
    }
}