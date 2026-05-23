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
public class IfaNoticeInfoSql002ResponseModel {

    /** 取引制限内容. */
    private String restRaint;
    
    /** 取引制限実行日時. */
    private String restRaintDate;

}
