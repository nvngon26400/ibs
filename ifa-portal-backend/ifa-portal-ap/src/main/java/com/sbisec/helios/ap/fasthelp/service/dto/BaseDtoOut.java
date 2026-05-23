package com.sbisec.helios.ap.fasthelp.service.dto;

import lombok.Data;

/**
 * OutputのDtoベース
 */
@Data
public abstract class BaseDtoOut{

    /**
     * コール番号
     */
    private String call_id;
    /**
     * FastHelp履歴連携結果
     */
    private String rtn_cd;
    /**
     * 問合せNO
     */
    private String rtn_toiawase_no;
    /**
     * 回答NO
     */
    private String rtn_kaitou_no;

}
