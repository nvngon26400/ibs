package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSelfInspectItemManageA002ApiResponse {

    /** 業者区分リスト */
    private List<BrokerType> brokerTypeList;


    /**
     * A001APIレスポンス.業者区分クラス
     *
     * @author SCSK 江口
     */
    @Data
    public static class BrokerType {

        /** 業者区分（全角半角） */
        private String brokerType;

        /** 区分名称 */
        private String classificationName;

        /** 登録状況（全角半角） */
        private String registerStatus;

    }

}
