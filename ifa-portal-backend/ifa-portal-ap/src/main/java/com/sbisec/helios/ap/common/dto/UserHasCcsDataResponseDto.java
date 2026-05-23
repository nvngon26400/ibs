package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * CCSログイン情報チェック
 *
 * @author SCSK 矢口
 */
@Data
public class UserHasCcsDataResponseDto {
    
    /** CCSパスワード設定有無フラグ */
    private String hasCcsData;
    
}
