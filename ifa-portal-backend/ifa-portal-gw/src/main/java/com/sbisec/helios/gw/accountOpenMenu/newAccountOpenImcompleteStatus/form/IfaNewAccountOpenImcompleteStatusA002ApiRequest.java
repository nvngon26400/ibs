package com.sbisec.helios.gw.accountOpenMenu.newAccountOpenImcompleteStatus.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * 画面ID：SUB020305-01
 * 画面名：新規口座開設不備状況

 * @author 大崎辰弥
    2024/03/01 新規作成
 */

@Data
public class IfaNewAccountOpenImcompleteStatusA002ApiRequest {

    /** 仲介業者コード（数字）. */
    @Size(max = 49)
    @Pattern(regexp = "[a-zA-Z0-9\\,]*")
    private String brokerCode;

    /** 仲介業者除外フラグ. */
    private String chkBrokerCodeExclude;

    /** 営業員コード（半角英数字）. */
    @Pattern(regexp = "(^[a-zA-Z0-9]{4}$)|(^$)", message = "営業員コード")
    private String empCode;

    /** 発送予定日From. */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @NotEmpty(message = "発送予定日From")
    @Size(min = 10, max = 10, message = "発送予定日From")
    private String dispatchScheduleDateFrom;

    /** 発送予定日To. */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @NotEmpty(message = "発送予定日To")
    @Size(min = 10, max = 10, message = "発送予定日To")
    private String dispatchScheduleDateTo;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;
}
