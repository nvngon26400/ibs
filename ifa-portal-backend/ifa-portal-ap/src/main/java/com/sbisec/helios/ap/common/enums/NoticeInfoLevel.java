package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注意情報レベル
 * 
 * @author 松田
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum NoticeInfoLevel {
    
    NOTHING("1", "注意情報なし")
    , EXIST_ERROR_NOTHING("2", "注意情報あり（エラーなし）")
    , EXIST_ERROR_EXIST("3", "注意情報あり（エラーあり）");
    
    private final String id;
    
    private final String label;
    
    private NoticeInfoLevel(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static NoticeInfoLevel valueOfId(String id) {
        
        NoticeInfoLevel[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
