package com.sbisec.helios.ap.common.dto;

import java.util.List;

import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchResponseModel;

import lombok.Data;

/**
 * 住所検索レスポンス
 *
 * @author xin.huang
 */
@Data
public class IfaAddressSearchResponseDto {

    /** 住所 */
    private  List<IfaAddressSearchResponseModel> addressList;
}
