package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.util.List;
import java.io.Serializable;
import com.sbisec.helios.ap.athena.enums.CurrencyCode;
import lombok.Data;

/**
 * 外貨信用保証金残高(通貨別) Dto.

 * @author SCSK 矢口
    2023/12/1 新規作成
 */
@Data
public class MarginCurrencyCashBalance implements Serializable {
    
    private static final long serialVersionUID = -573297345742107706L;
    
    public MarginCurrencyCashBalance() {
    
    }
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 外貨信用保証金残高スケジュールリスト */
    private List<ForeignMarginScheduleCashBalance> foreignMarginScheduleCashBalances;
    
}
