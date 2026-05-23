package com.sbisec.helios.gw.common.composite.form;

import lombok.Data;

@Data
public class IfaCommonSearchA001ApiResponse {

    /** 仲介業者コード自動表示フラグ. */
    private String brokerCodeAutoDispFlag;

    /** 支店コード自動表示フラグ. */
    private String branchCodeAutoDispFlag;

    /** 営業員コード自動表示フラグ. */
    private String empCodeAutoDispFlag;

}
