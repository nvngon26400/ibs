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
public class IfaNoticeInfoSql001ResponseModel {

    /** 分類（全角半角）. */
    private String cautionKind;
    
    /** メッセージ. */
    private String memo;
    
    /** 発生日. */
    private String accuralDate;
    
    /** 取扱者ID. */
    private String dealtPerson;
    
    /** 削除日時. */
    private String deleteTime;

}
