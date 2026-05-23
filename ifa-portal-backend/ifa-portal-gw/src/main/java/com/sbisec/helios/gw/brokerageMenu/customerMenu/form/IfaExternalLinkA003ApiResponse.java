package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbisec.helios.ap.common.dto.NewMainSiteParamResponseDto;
import lombok.Data;

/**
 * 外部リンク
 * 2025/07/29 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaExternalLinkA003ApiResponse {

    /** 新メインサイト用パラメータ生成 */
    private List<NewMainSiteParamResponseDto> newMainSiteParamList;

    /** POSTリクエスト */
    private Map<String, String> postRequest = new HashMap<String, String>();

    /** リンクURL */
    private String linkUrl;
}
