package com.sbisec.helios.gw.extapi.servicenow.form.common;

import com.sbisec.helios.gw.extapi.servicenow.validator.ServiceNowMenuId;

import lombok.Data;

/**
 * メニュー
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowMenu {
    
    /** メニューコード. */
    @ServiceNowMenuId
    private String menuId;
}
