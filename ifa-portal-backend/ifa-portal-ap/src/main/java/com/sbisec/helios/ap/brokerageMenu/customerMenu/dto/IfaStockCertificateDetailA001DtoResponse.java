package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;
@Data
public class IfaStockCertificateDetailA001DtoResponse {
    /** 株券明細詳細リスト */
    private List<IfaStockCertificateDetailA001DetailDtoResponse> stockDetailList;
}
