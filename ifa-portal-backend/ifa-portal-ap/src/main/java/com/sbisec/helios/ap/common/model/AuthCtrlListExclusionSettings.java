package com.sbisec.helios.ap.common.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 除外メニュー設定
 *
 * @author SCSK
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthCtrlListExclusionSettings extends ModelBase {
    
    /* serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** 除外メニューリスト */
    private List<ExclusionItem> exclusionList;
    
    /**
     * 除外メニュー設定値
     *
     * @author SCSK
     */
    @Data
    public static class ExclusionItem {
        
        /** キー */
        private String key;
        
        /** 除外メニュー */
        private List<String> exclusionMenu;
        
        /** 条件1 */
        private List<Integer> condition1;
        
        /** 条件2 */
        private List<String> condition2;
    }
}
