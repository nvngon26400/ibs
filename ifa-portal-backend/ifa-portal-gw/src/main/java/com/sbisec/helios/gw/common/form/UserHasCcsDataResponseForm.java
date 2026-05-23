package com.sbisec.helios.gw.common.form;

import lombok.Data;

/**
 * CCSログイン情報チェック
 *
 * @author SCSK 矢口
 */
@Data
public class UserHasCcsDataResponseForm {
    
    /** CCSパスワード設定有無フラグ */
    private String hasCcsData;
    
}
