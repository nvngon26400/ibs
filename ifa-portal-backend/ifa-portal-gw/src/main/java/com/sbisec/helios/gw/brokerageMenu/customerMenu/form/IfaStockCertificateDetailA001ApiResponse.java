package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockCertificateDetailA001DetailDtoResponse;

import lombok.Data;
@Data
public class IfaStockCertificateDetailA001ApiResponse {
    /** 株券明細詳細リスト */
    private List<IfaStockCertificateDetailA001DetailDtoResponse> stockDetailList;
}
