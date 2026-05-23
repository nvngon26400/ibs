package com.sbisec.helios.gw.common.form;

import java.util.List;

import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchResponseModel;

import lombok.Data;

/**
 * 投信銘柄検索レスポンス
 *
 * @author xin.huang
 */
@Data
public class IfaBrandNameSearchResponseForm {

    /** 銘柄詳細 */
    private List<IfaBrandNameSearchResponseModel> brandInfo;
}
