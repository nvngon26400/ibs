package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;
import com.sbisec.helios.ap.athena.protocol.account.dto.MarginCurrencyCashBalance;

import lombok.Data;

/**
 * Description:余力サービス 外貨信用保証金残高スケジュール取得API Response

 * @author SCSK 矢口
    2023/12/1 新規作成
 */
@Data
public class ListForeignMarginScheduleCashBalancesResp {
    
    public ListForeignMarginScheduleCashBalancesResp() {
        
    }
    
    /** 外貨信用保証金残高(通貨別) */
    private List<MarginCurrencyCashBalance> marginCurrencyCashBalances;
    
}
