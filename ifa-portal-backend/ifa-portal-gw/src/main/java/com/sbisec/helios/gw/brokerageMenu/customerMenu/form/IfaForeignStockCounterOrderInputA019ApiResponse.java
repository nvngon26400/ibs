package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.sbisec.helios.ap.common.dto.NewMainSiteParamResponseDto;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外国株式店頭注文入力
 * 2026/02/09 新規作成
 *
 * @author 大連 趙
 */
@Data
public class IfaForeignStockCounterOrderInputA019ApiResponse {

    /** 新メインサイト用パラメータ生成 */
    private List<NewMainSiteParamResponseDto> newMainSiteParamList;

    /** POSTリクエスト */
    private Map<String, String> postRequest = new HashMap<String, String>();

    /** リンクURL */
    private String linkUrl;
}
