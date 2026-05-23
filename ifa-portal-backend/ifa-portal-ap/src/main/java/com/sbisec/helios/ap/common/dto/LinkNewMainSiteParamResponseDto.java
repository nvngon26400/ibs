package com.sbisec.helios.ap.common.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新メインサイトのDTO
 *
 * @author 大連 葉
 */
@Data
public class LinkNewMainSiteParamResponseDto {
    /** 新メインサイト用パラメータ生成 */
    private List<NewMainSiteParamResponseDto> newMainSiteParamList;

    /** POSTリクエスト */
    private Map<String, String> postRequest = new HashMap<String, String>();

    /** リンクURL */
    private String linkUrl;
}
