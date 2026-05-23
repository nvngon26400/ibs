package com.sbisec.helios.gw.common.composite.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaNoticeInfoA002ApiResponse {

	/** 注意情報一覧. */
    private List<IfaNoticeInfoA002ApiResponseNoticeInfoList> noticeInfoList;

	/** 取引制限一覧. */
    private List<IfaNoticeInfoA002ApiResponseTradeRestrictionList> tradeRestrictionList;

}
