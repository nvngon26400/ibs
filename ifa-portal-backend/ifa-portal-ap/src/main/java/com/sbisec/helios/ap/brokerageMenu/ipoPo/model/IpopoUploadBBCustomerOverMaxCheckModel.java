package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoUploadBBCustomerOverMaxCheckModel extends ModelBase {
    // 裁量配分可能余回数
    private int maybeSairyouCount;
    // 本年の年間裁量配分割当回数
    private int sairyouAlloCount;
    // 移管前本年の年間裁量配分割当回数
    private int oldSairyouAlloCount;
    // 移管前部店
    private String oldBranchNo;
    // 移管前口座番号
    private String oldAccountNo;
    // 本年の年間裁量配分割当上限数
    private int maxSairyouAllo;
    // 本年の年間裁量配分割当回数（移管前後）合計
    private int sairyouAlloCountTotal;

    public int getMaybeSairyouCount() {
        return maybeSairyouCount;
    }

    public void setMaybeSairyouCount(int maybeSairyouCount) {
        this.maybeSairyouCount = maybeSairyouCount;
    }

    public int getSairyouAlloCount() {
        return sairyouAlloCount;
    }

    public void setSairyouAlloCount(int sairyouAlloCount) {
        this.sairyouAlloCount = sairyouAlloCount;
    }

    public int getOldSairyouAlloCount() {
        return oldSairyouAlloCount;
    }

    public void setOldSairyouAlloCount(int oldSairyouAlloCount) {
        this.oldSairyouAlloCount = oldSairyouAlloCount;
    }

    public String getOldBranchNo() {
        return oldBranchNo;
    }

    public void setOldBranchNo(String oldBranchNo) {
        this.oldBranchNo = oldBranchNo;
    }

    public String getOldAccountNo() {
        return oldAccountNo;
    }

    public void setOldAccountNo(String oldAccountNo) {
        this.oldAccountNo = oldAccountNo;
    }

    public int getMaxSairyouAllo() {
        return maxSairyouAllo;
    }

    public void setMaxSairyouAllo(int maxSairyouAllo) {
        this.maxSairyouAllo = maxSairyouAllo;
    }

    public int getSairyouAlloCountTotal() {
        return sairyouAlloCountTotal;
    }

    public void setSairyouAlloCountTotal(int sairyouAlloCountTotal) {
        this.sairyouAlloCountTotal = sairyouAlloCountTotal;
    }

}
