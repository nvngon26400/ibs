package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 投信積立設定済銘柄一覧SQL001要求
 *
 * @author nicksen.li
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaMutualFundAccumulateSettingBrandListSql001RequestModel {
    /** 協会コード リスト. */
    private List<String> fundCodeList;
}
