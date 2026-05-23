package com.sbisec.helios.ap.common.composite.dto;

import java.util.List;

import lombok.Data;

/**
*
* @author SCSK
*
*/
@Data
public class IfaNoticeInfoA002DtoResponse {

    /** 注意情報一覧. */
    private List<IfaNoticeInfoA002DtoResponseNoticeInfoList> noticeInfoList;

    /** 取引制限一覧. */
    private List<IfaNoticeInfoA002DtoResponseTradeRestrictionList> tradeRestrictionList;

}
