package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0513_01-01
 * 画面名：共同募集契約マスタ
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
@Data
public class IfaJointContractMasterSql003RequestModel {
    
    /** 仲介業者コード */
    private String brokerCode;

    /** 共募契約 */
    private String contractStatus;

}