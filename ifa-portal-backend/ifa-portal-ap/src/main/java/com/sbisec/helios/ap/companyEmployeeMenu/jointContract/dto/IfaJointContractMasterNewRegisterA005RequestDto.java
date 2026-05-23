package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto;

import lombok.Data;

/**
 * 画面ID：SUB0513_01-02
 * 画面名：共同募集契約マスタ登録
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
@Data
public class IfaJointContractMasterNewRegisterA005RequestDto {
    
        /** 仲介業者コード */
        private String brokerCode;

        /** 共募契約 */
        private String jointContract;
}