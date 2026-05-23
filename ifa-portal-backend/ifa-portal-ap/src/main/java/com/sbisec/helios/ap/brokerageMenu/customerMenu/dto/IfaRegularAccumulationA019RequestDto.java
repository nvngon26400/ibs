package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 保有商品一覧 A019 レスポンス
 *
 * @author Shuchang.Song
 */
@Data
public class IfaRegularAccumulationA019RequestDto {
    
    /** 回数 */
    private String times;

    /** 号1 */
    private String issue1;

    /** 号2 */
    private String issue2;

    /** 協会コード. */
    private int fundCode;
}
