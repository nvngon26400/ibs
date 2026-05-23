package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 仲介業者コードと名を取得する用レスポンスモデル
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageSql006ResponseModel {

    /** 仲介業者コード(数字) */
    private String brokerCode;

    /** 仲介業者名(全角半角) */
    private String brokerName;

}
