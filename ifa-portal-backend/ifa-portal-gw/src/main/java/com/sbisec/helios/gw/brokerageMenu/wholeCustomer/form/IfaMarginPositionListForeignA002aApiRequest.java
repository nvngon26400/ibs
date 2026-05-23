package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002aDtoRequestCourseSelected;

import lombok.Data;

@Data
public class IfaMarginPositionListForeignA002aApiRequest {
    
    /** 仲介業者コード（数字）. */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp = "0-9", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
    /** 営業員コード（半角英数字）. */
    @NotEmpty(message = "営業員コード")
    @Size(min = 4, max = 4, message = "営業員コード")
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 顧客名（漢字／カナ）（全角半角）. */
    @NotEmpty(message = "顧客名（漢字／カナ）")
    @Size(max = 72, message = "顧客名（漢字／カナ）")
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件. */
    @NotEmpty(message = "顧客名（漢字／カナ）_条件")
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース（全角半角）. */
    @NotEmpty(message = "取引コース")
    private List<IfaMarginPositionListForeignA002aDtoRequestCourseSelected> course;
    
    /** ティッカー（全角半角）. */
    @NotEmpty(message = "ティッカー")
    @Size(max = 10, message = "ティッカー")
    private String ticker;
    
}
