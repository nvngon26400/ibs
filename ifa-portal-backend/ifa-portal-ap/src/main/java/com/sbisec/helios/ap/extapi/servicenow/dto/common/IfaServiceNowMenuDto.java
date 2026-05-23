package com.sbisec.helios.ap.extapi.servicenow.dto.common;

import lombok.Data;

/**
 * メニュー情報 Dto
 *
 * @author SCSK
 * 
 */
@Data
public class IfaServiceNowMenuDto {
    
    /** メニュー情報権限フラグ. */
    private String menuInfoPrivFlag;

    /** メニューコード. */
    private String menuId;

    /** メニュータイトル. */
    private String menuTitle;
}
