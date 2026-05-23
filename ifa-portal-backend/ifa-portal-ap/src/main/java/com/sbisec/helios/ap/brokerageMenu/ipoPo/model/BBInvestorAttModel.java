package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class BBInvestorAttModel extends ModelBase {
    // 銘柄コード
    private String bbProductCode;
    // ブックビルディング申込期間（開始）
    private String bbPresentationFrom;
    // 投資家属性順序
    private Integer bbSeq;
    // 投資家属性名
    private String bbInvestorAttName;

    public String getBbProductCode() {
        return bbProductCode;
    }

    public void setBbProductCode(String bbProductCode) {
        this.bbProductCode = bbProductCode;
    }

    public String getBbPresentationFrom() {
        return bbPresentationFrom;
    }

    public void setBbPresentationFrom(String bbPresentationFrom) {
        this.bbPresentationFrom = bbPresentationFrom;
    }

    public Integer getBbSeq() {
        return bbSeq;
    }

    public void setBbSeq(Integer bbSeq) {
        this.bbSeq = bbSeq;
    }

    public String getBbInvestorAttName() {
        return bbInvestorAttName;
    }

    public void setBbInvestorAttName(String bbInvestorAttName) {
        this.bbInvestorAttName = bbInvestorAttName;
    }
}
