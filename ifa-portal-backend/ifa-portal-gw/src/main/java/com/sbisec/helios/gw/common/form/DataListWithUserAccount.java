package com.sbisec.helios.gw.common.form;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DataList（ユーザ共通情報追加）<br />
 * ユーザ共通情報の更新内容をGWに連携したい場合、利用すうる
 *
 * @author 河口
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataListWithUserAccount<T> extends DataList<T> {
    
    /*　serialVersionUID　*/
    private static final long serialVersionUID = 1L;
    
    /* ユーザ共通情報 */
    private UserAccount userAccount;
}
