package com.sbisec.helios.gw.common.form;

import java.util.List;

import lombok.Data;

@Data
public class IfapExtApiAccessControl {
    
    /** API識別コード */
    private String ifapExtApiSystemId;
    
    /** ホワイトリスト */
    private List<String> ifapExtApiWhiteList;   
    
}
