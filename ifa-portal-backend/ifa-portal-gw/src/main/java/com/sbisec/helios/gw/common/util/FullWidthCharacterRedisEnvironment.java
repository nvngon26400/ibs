package com.sbisec.helios.gw.common.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 全角チェックルール設定
 *
 * @author SCSK
 *
 */
@Data
public class FullWidthCharacterRedisEnvironment {
    
    /** 全角チェックルールリスト */
    @JsonProperty(value = "fullWidthCharacterRules")
    private List<FullwidthCharacterRoles> fullWidthCharacterRules;
    
    /**
     * 全角チェックルール
     *
     * @author SCSK
     *
     */
    @Data
    public static class FullwidthCharacterRoles {
        
        /** 画面ID */
        @JsonProperty(value = "screenId")
        private String screenId;
        
        /** フィールドID */
        @JsonProperty(value = "fieldId")
        private String fieldId;
        
        /** SJIS無効文字検知時のメッセージコード */
        @JsonProperty(value = "utf8StrCheckMassageCode")
        private String utf8StrCheckMassageCode;
        
        /** プレースホルダ用固定文言１ */
        @JsonProperty(value = "utf8StrCheckPlaceholder1")
        private String utf8StrCheckPlaceholder1;
        
        /** プレースホルダ用固定文言２ */
        @JsonProperty(value = "utf8StrCheckPlaceholder2")
        private String utf8StrCheckPlaceholder2;
        
        /** EUC-JPレングス超過検知時のメッセージコード　*/
        @JsonProperty(value = "lengthCheckMessageCode")
        private String lengthCheckMessageCode;
        
        /** プレースホルダ用固定文言 */
        @JsonProperty(value = "lengthCheckPlaceholder")
        private String lengthCheckPlaceholder;
        
        /** 許容文字数１ */
        @JsonProperty(value = "length1")
        private String length1;
        
        /** 許容文字数２ */
        @JsonProperty(value = "length2")
        private String length2;
    }
    
    /**
     * 指定されたscreenIdとfieldIdに一致する全角チェックルールを取得する
     *
     * @param screenId 画面ID
     * @param fieldId フィールドID
     * @return 一致する全角チェックルール、見つからない場合はnull
     */
    @JsonIgnore
    public FullwidthCharacterRoles getFullwidthCharacterRole(String screenId, String fieldId) {
        
        if (fullWidthCharacterRules == null) {
            return null;
        }
        
        return fullWidthCharacterRules.stream()
                .filter(rule -> rule.getScreenId().equals(screenId) && rule.getFieldId().equals(fieldId)).findFirst()
                .orElse(null);
    }
}
