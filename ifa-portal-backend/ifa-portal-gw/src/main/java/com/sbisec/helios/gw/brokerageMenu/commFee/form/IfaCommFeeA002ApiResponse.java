package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaCommFeeA002ApiResponse {

    /** 表示内容 */
    private String displayContent;

    /** 手数料報酬リスト */
    private List<IfaCommFeeA002CommFeeApiResponse> commFeeList;

    /** 現株ポイント参照可能リスト */
    private List<IfaCommFeeA002SpotStockPointReferenceAbleListApiResponse> spotStockPointReferenceAbleList;

    /** SBIラップ管理報酬表示制御リスト */
    private List<IfaCommFeeA002SbiRapManageFeeDisplayControlApiResponse> sbiRapManageFeeDisplayControlList;

}
