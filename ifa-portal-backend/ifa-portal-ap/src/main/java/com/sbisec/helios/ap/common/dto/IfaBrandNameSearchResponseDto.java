package com.sbisec.helios.ap.common.dto;

import java.util.List;

import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchResponseModel;

import lombok.Data;

/**
 * 投信銘柄検索レスポンス
 *
 * @author xin.huang
 */
@Data
public class IfaBrandNameSearchResponseDto {

    /** 銘柄詳細 */
    private List<IfaBrandNameSearchResponseModel> brandInfo;
}
