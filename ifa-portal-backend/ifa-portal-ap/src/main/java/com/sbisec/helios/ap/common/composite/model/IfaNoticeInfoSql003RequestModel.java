package com.sbisec.helios.ap.common.composite.model;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：CC016
 * 画面名：注意情報
 *
 * @author SCSK
 2024/06/20 新規作成
 */
@Data
public class IfaNoticeInfoSql003RequestModel {

    /** 制限内容マップ.制限番号. */
    private List<String> limitCode;
    
}
