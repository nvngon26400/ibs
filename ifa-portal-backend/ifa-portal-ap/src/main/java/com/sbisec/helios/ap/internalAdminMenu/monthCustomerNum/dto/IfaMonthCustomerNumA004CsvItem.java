package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaMonthCustomerNumA004CsvItem extends ModelBase {

    private static final long serialVersionUID = 6651543506903761494L;
    
    /** 対象年月 */
    private String dateYm;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 月末口座数 */
    private String accountNum;
}
