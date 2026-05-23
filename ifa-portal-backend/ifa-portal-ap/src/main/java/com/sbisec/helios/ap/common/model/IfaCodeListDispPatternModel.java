package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 区分定義表示パターンテーブルの値を保持するModel
 *
 * @author 河口
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaCodeListDispPatternModel extends ModelBase {
    
    /** シリアルID */
    private static final long serialVersionUID = 1L;
    
    /** 区分値 */
    private String key;
    
    /** 区分値名称 */
    private String value;
}
