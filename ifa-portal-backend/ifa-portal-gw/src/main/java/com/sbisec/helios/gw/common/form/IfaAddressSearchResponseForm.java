package com.sbisec.helios.gw.common.form;

import java.util.List;

import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchResponseModel;

import lombok.Data;

/**
 * 住所検索レスポンス
 *
 * @author xin.huang
 */
@Data
public class IfaAddressSearchResponseForm {

    /** 住所 */
    private  List<IfaAddressSearchResponseModel> addressList;
}
