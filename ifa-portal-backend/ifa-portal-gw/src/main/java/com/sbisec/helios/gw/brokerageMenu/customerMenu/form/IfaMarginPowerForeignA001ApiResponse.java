package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

/**
 * 信用余力(米株) A001 レスポンスパラメータ
 * 
 * @author SCSK 矢口
 *
 */
@Data
public class IfaMarginPowerForeignA001ApiResponse {
    
    /** 追加保証金リスト. */
    private List<IfaMarginPowerForeignA001ApiResponseAddDeposit> addDeposit;
    
    /** 預り金不足リスト. */
    private List<IfaMarginPowerForeignA001ApiResponseDepositDeficient> depositDeficient;
    
    /** 新規建不足リスト. */
    private List<IfaMarginPowerForeignA001ApiResponseNewPositionDeficient> newPositionDeficient;
    
    /** 信用取引リスト. */
    private List<IfaMarginPowerForeignA001ApiResponseMarginTrade> marginTrade;
    
    /** 余力情報_信用建余力. */
    private String foreignNewBuildingCapacity;
    
    /** 余力情報_委託保証金率. */
    private String marginDepositRate8;
    
    /** 余力情報_リアル委託保証金率. */
    private String realTimeMarginDepositRate;
    
    /** 余力情報_米ドル保証金. */
    private String usdSecurityDeposit;
    
    /** 余力情報_代用有価証券. */
    private String substituteSecurities;
    
    /** 委託保証金率リスト. */
    private List<IfaMarginPowerForeignA001ApiResponseEntrustDepositTransition> entrustDepositTransition;
    
    /** 追証フラグ. */
    private String marginCallFlag;
    
    /** 預り金不足フラグ. */
    private String depositDeficientFlag;
    
    /** 新規建不足フラグ. */
    private String newPositionDeficientFlag;
    
}
