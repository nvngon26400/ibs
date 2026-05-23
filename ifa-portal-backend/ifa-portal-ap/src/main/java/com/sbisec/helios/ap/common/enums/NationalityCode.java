package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 国籍コード
 *
 * @author SCSK
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum NationalityCode {
    
    US("US", "アメリカ合衆国(米国)"), CN("HK", "香港"), KR("KR", "大韓民国"), RU("RU", "ロシア連邦"), VN("VN", "ベトナム社会主義共和国"), ID("ID",
            "インドネシア共和国"), SG("SG", "シンガポール共和国"), TH("TH", "タイ王国"), MY("MY", "マレーシア"), OT("99", "その他") // 99は媒介可否チェックおよび取引制限チェックで使用
    ;
    
    private final String id;
    
    private final String label;
    
    private NationalityCode(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static NationalityCode valueOfId(String id) {
        
        //if (id == null) return null;
        
        NationalityCode[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
    
}
