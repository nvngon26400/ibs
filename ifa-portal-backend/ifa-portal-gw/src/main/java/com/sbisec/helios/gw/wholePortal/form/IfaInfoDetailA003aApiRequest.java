package com.sbisec.helios.gw.wholePortal.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaInfoDetailA003aApiRequest {

    /** ファイルディレクトリ */
    @NotEmpty(message = "ファイルディレクトリ")
    @Size(min = 255, max = 255, message = "ファイルディレクトリ")
    private String fileDirectory;

    /** 添付ファイル */
    @NotEmpty(message = "添付ファイル")
    private String attachFile;

    /** ディスクレーマー */
    @NotEmpty(message = "ディスクレーマー")
    @Size(min = 1, max = 1, message = "ディスクレーマー")
    private String disclaimer;

}
