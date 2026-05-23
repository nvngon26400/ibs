package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * 信用余力(米株) A001 レスポンスパラメータ

 * @author SCSK 矢口
 *
 */
@Data
public class IfaMarginPowerForeignA001DtoResponse {
    
    /** 追加保証金リスト. */
    private List<IfaMarginPowerForeignA001DtoResponseAddDeposit> addDeposit;
    
    /** 預り金不足リスト. */
    private List<IfaMarginPowerForeignA001DtoResponseDepositDeficient> depositDeficient;
    
    /** 新規建不足リスト. */
    private List<IfaMarginPowerForeignA001DtoResponseNewPositionDeficient> newPositionDeficient;
    
    /** 信用取引リスト. */
    private List<IfaMarginPowerForeignA001DtoResponseMarginTrade> marginTrade;
    
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
    private List<IfaMarginPowerForeignA001DtoResponseEntrustDepositTransition> entrustDepositTransition;
    
    /** 追証フラグ. */
    private String marginCallFlag;
    
    /** 預り金不足フラグ. */
    private String depositDeficientFlag;
    
    /** 新規建不足フラグ. */
    private String newPositionDeficientFlag;
    
}
