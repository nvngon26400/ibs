package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB020303-01
 * 画面名：顧客振込先金融機関口座

 * @author 大崎 辰弥
    2023/10/27 新規作成
 */

@Data
public class IfaCustomerDestinationBankAccountA002DtoResponse {

    /** 顧客振込先金融機関口座リスト. */
    private List<IfaCustomerDestinationBankAccountDtoResponseCustomerDestinationBankAccount> customerDestinationBankAccountList;

}
