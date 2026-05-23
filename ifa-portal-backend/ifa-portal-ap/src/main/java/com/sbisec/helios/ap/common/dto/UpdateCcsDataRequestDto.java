package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * CCSログイン情報登録
 *
 * @author SCSK 矢口
 */
@Data
public class UpdateCcsDataRequestDto {
    
    /** CCSパスワード */
    private String ccsUserPw;
    
}
