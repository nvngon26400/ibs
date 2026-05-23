package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
@Data
public class IfaTrustFeeA002ApiRequest {
    
    /** 仲介業者コード */
    @Pattern(regexp = "[a-zA-Z0-9\\,]*", message = "仲介業者コード")
    @Size(max = 49, message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者除外 */
    @Size(min = 1, max = 1, message = "仲介業者除外")
    private String chkBrokerCodeExclude;
    
    /** 支店コード */
    @Pattern(regexp = "0-9", message = "支店コード")
    @Size(max = 3, message = "支店コード")
    private String branchCode;
    
    /** 営業員コード */
    @Size(min = 4, max = 4, message = "営業員コード")
    private String empCode;
    
    /** 部店コード */
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号 */
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 顧客名(漢字/カナ) */
    @Size(max = 72, message = "顧客名(漢字/カナ)")
    private String customerNameKanjiKana;
    
    /** 顧客名(漢字/カナ)_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース */
    @NotEmpty(message = "取引コース")
    private List<IfaTrustFeeMultiSelectApiRequest> course;
    
    /** 集計単位(日次/月次累計). */
    @NotEmpty(message = "集計単位(日次/月次累計)")
    private String dailyMonthlyCountingUnitTotal;
    
    /** 集計単位(明細/顧客/通貨毎). */
    @NotEmpty(message = "集計単位(明細/顧客/通貨毎)")
    private String detailCustomerCurrencyCountingUnit;
    
    /** 期間指定 */
    @NotEmpty(message = "期間指定")
    private List<String> period;

    /** 証券種別. */
    @NotEmpty(message = "証券種別")
    private List<IfaTrustFeeMultiSelectApiRequest> securityClass;
    
    /** 銘柄コード */
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
}
