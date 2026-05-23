package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * 初期化 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCollateralSecurityDeliverInOutDetailA001ApiRequest {

    /** 受渡日. */
    @DateTimeFormat(pattern="yy/MM/dd")
    @JsonFormat(pattern="yy/MM/dd")
    @NotEmpty(message = "受渡日")
    @Size(min = 10, max = 10, message = "受渡日")
    private String settlementDate;

    /** 表示基準日（受渡日）. */
    @NotEmpty(message = "表示基準日（受渡日）")
    private String displayBaseDate;

    /** 入出庫区分. */
    @NotEmpty(message = "入出庫区分")
    private String deliverInOutClassification;


}
