package com.sbisec.helios.ap.common.composite.model;

import lombok.Data;

/**
 * 画面ID：CC016
 * 画面名：注意情報
 *
 * @author SCSK
 2024/06/20 新規作成
 */
@Data
public class IfaNoticeInfoSql002RequestModel {

    /** 部店コード. */
    private String butenCode;

    /** 口座番号. */
    private String accountNumber;

}
