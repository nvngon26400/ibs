package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文入力注文確認レスポンス市場情報
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA013ResponseDtoMarketInfo {

    /** 市場略名. */
    private String marketAbbreviation;

    /** 国コード（全角半角）. */
    private String countryCode;

    /** 市場コード（全角半角）. */
    private String marketCode;

    /** タイムゾーン略名. */
    private String timeZoneAbbreviation;

}
