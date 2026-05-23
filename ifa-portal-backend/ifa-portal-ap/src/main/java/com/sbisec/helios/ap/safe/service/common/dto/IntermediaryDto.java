package com.sbisec.helios.ap.safe.service.common.dto;


/**
 * 仲介情報Dto
 */
public class IntermediaryDto {

    /** 仲介是非 */
    private boolean intermediarable;

    /** 仲介業者コード */
    private String mediationCode;

    /**
     * @return the intermediarable
     */
    public boolean isIntermediarable() {
        return intermediarable;
    }

    /**
     * @return the mediationCode
     */
    public String getMediationCode() {
        return mediationCode;
    }

    /**
     * @param intermediarable the intermediarable to set
     */
    public void setIntermediarable(final boolean intermediarable) {
        this.intermediarable = intermediarable;
    }

    /**
     * @param mediationCode the mediationCode to set
     */
    public void setMediationCode(final String mediationCode) {
        this.mediationCode = mediationCode;
    }
}
