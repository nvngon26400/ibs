package com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form;

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
public class IfaJointContractMasterNewRegisterA005ResponseApi {
        /** 仲介業者コード */
        private String brokerCode;

        /** 仲介業者名 */
        private String brokerName;

        /** 共募契約 */
        private String contractStatus;
}