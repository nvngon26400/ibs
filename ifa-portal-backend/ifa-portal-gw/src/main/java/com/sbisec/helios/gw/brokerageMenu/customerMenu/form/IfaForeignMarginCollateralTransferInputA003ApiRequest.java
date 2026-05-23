package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 米株信用代用振替入力　A003　リクエスト

 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputA003ApiRequest {
    
    /** 代用有価証券振替区分. */
    @NotEmpty(message = "代用有価証券振替区分")
    private String transferClassification;
    
    /** 代用有価証券振替情報リスト. */
    @NotEmpty(message = "代用有価証券振替情報リスト")
    private List<IfaForeignMarginCollateralTransferInputInfo> collateralSecurityTransferInfoList;
    
}
