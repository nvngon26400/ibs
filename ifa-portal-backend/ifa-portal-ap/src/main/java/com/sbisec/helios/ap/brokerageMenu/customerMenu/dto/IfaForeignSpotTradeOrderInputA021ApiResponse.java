package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import com.sbisec.helios.ap.common.dto.NewMainSiteParamResponseDto;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外国現物取引注文入力 外部リンク
 * 2026/02/09 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaForeignSpotTradeOrderInputA021ApiResponse {

    /** 新メインサイト用パラメータ生成 */
    private List<NewMainSiteParamResponseDto> newMainSiteParamList;

    /** POSTリクエスト */
    private Map<String, String> postRequest = new HashMap<String, String>();

    /** リンクURL */
    private String linkUrl;
}
