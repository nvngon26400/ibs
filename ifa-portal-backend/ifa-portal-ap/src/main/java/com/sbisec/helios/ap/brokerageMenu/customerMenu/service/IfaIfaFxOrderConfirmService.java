package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001bDtoResponse;
import com.sbisec.helios.ap.service.Service;

public interface IfaIfaFxOrderConfirmService extends Service{
    
    DataList<IfaIfaFxOrderConfirmA001aDtoResponse> orderPlacementA001a(IfaIfaFxOrderConfirmA001aDtoRequest dtoReq)
            throws Exception;
    
    DataList<IfaIfaFxOrderConfirmA001bDtoResponse> orderPlacementA001b(IfaIfaFxOrderConfirmA001bDtoRequest dtoReq)
            throws Exception;
}
