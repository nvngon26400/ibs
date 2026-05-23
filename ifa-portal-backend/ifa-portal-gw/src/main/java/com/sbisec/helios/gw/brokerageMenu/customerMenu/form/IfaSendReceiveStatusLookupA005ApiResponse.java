package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA005docDeficencyDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA005docRequestAddInfoDtoResponse;

import lombok.Data;

@Data
public class IfaSendReceiveStatusLookupA005ApiResponse {
    /** 書類請求付加情報詳細 */
    private List<IfaSendReceiveStatusLookupA005docRequestAddInfoDtoResponse> docRequestAddInfo;
    /** 書類不備付加情報リスト */
    private List<IfaSendReceiveStatusLookupA005docDeficencyDtoResponse> selectDocDeficiencyInfoList;

}
