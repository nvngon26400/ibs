package com.sbisec.helios.ap.wholePortal.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaCustomerAlertCsvOutputCustomerAlertNotification extends ModelBase {
    
    /** シリアルバージョンID */
    private static final long serialVersionUID = -6610674958156783748L;
    
    /** アラートカテゴリ. */
    private String alertCategory;
    
    /** アラートタイトル. */
    private String alertTitle;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 支店コード. */
    private String branchCode;
    
    /** 支店名. */
    private String branchName;
    
    /** 営業員コード. */
    private String empCode;
    
    /** 営業員名. */
    private String brokerChargeName;
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** コース名. */
    private String courseName;
    
    /** 顧客名. */
    private String customerName;
}
