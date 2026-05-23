package com.sbisec.helios.ap.common.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * リンクURLサービスのDTO
 *
 * @author 河口
 *
 */
@Data
public class LinkUrlDto {
    
    /** リンク表示名 */
    private String dispName;
    
    /** 外部リンクフラグ */
    private String extLinkFlag;
    
    /** POSTリクエスト */
    private Map<String, String> postRequest = new HashMap<String, String>();
    
    /** リンクURL */
    private String linkUrl;
}
