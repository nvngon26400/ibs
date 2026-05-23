package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
@Data
public class IfaSubscriptPeriodMasterRegisterInputSql003RequestModel {

    /** 銘柄コード（半角英数字） */
    private String brandCode12;

    /** ブックビルディング申込期間（開始）（全角半角） */
    private String bbPresentationFrom;

}
