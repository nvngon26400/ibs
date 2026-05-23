package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import java.util.List;

import lombok.Data;

/**
 * IPOPO銘柄一覧 A001レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaIpoPoBrandListA001ApiResponse {
    
    /** IPO/PO銘柄一覧 */
    List<IfaIpoPoBrandListA001IpoPoBrandListApiResponse> IpoPoBrandList;

}