package com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
@Data
public class IfaPayNotificationDocDownloadA002ApiRequest {
    
    /** 対象年月From */
    @DateTimeFormat(pattern = "yyyy/MM")
    @JsonFormat(pattern = "yyyy/MM")
    @NotEmpty(message = "対象年月From")
    private String targetDateYmFrom;
    
    /** 対象年月To */
    @DateTimeFormat(pattern = "yyyy/MM")
    @JsonFormat(pattern = "yyyy/MM")
    @NotEmpty(message = "対象年月To")
    private String targetDateYmTo;
    
    /** 仲介業者コード */
    @Pattern(regexp="[a-zA-Z0-9\\,]*", message = "仲介業者コード")
    @Size(max = 49, message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者除外 */
    @Size(min = 1, max = 1, message = "仲介業者除外")
    private String chkBrokerCodeExclude;
    
}
