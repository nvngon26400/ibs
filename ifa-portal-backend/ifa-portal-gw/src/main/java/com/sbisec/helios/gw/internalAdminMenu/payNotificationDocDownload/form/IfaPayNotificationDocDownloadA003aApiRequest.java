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
public class IfaPayNotificationDocDownloadA003aApiRequest {
    
    /** 年月 */
    @DateTimeFormat(pattern = "yyyy/MM")
    @JsonFormat(pattern = "yyyy/MM")
    @NotEmpty(message = "年月")
    @Size(min = 7, max = 7, message = "年月")
    private String dateYm;
    
    /** 仲介業者コード */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
    /** バージョン番号. */
    @NotEmpty(message = "バージョン番号")
    @Pattern(regexp = "[0-9]+", message = "バージョン番号")
    @Size(max = 2, message = "バージョン番号")
    private String versionNumber;
    
    /** ファイルディレクトリ */
    @NotEmpty(message = "ファイルディレクトリ")
    @Size(min = 1, max = 255, message = "ファイルディレクトリ")
    private String fileDirectory;
    
    /** 前回検索時の対象年月From. */
    @NotEmpty(message = "前回検索時の対象年月From")
    private String beforeSearchTargetDateYmFrom;
    
    /** 前回検索時の対象年月To. */
    @NotEmpty(message = "前回検索時の対象年月To")
    private String beforeSearchTargetDateYmTo;
    
    /** 前回検索時の仲介業者コード. */
    private String beforeSearchBrokerCode;
    
    /** 前回検索時の仲介業者除外. */
    private String beforeSearchChkBrokerCodeExclude;
    
}
