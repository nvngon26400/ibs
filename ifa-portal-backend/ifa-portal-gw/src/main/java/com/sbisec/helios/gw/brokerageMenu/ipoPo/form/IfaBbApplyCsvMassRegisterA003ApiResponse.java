package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import lombok.Data;
import java.util.List;

/**
 * 画面ID：SUB0204_01-03_1
 * 画面名：BB申込(一括登録)
 *
 * @author SCSK
 *
 */
@Data
public class IfaBbApplyCsvMassRegisterA003ApiResponse {
    
    /**BB申込一覧 */
    private List<IfaBbApplyCsvMassRegisterBbApplyListApiResponse> bbApplyList;
       
}