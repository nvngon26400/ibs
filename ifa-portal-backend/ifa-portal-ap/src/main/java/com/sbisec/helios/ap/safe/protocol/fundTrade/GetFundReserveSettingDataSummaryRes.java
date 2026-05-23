package com.sbisec.helios.ap.safe.protocol.fundTrade;

import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingSummaryApiOut;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetFundReserveSettingDataSummaryRes {

    private ReserveSettingSummaryApiOut reserveSettingSummaryApiOut;

}
