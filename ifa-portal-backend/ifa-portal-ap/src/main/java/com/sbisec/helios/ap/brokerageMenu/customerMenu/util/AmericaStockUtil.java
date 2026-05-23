package com.sbisec.helios.ap.brokerageMenu.customerMenu.util;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.ApiStatusModel;
import com.sbisec.helios.ap.common.enums.ErrorLevel;

public class AmericaStockUtil<T> {
    
    /**
     * 業務異常の処理
     * @param dataList
     * @param exception
     * @return httpStatusCode
     */
    public static <T> DataList<T> getBussinessException(DataList<T> dataList, Exception exception) {
        
        if (exception instanceof AthenaBusinessException) {
            String errorCode = ((AthenaBusinessException) exception).getErrorCode();
            String message = ((AthenaBusinessException) exception).getMessage();
            dataList.setTitle(errorCode);
            dataList.setMessage(message);
        }
        dataList.setErrorLevel(ErrorLevel.FATAL.getId());
        dataList.setReturnCode(AmericaStockReturnCode.ATHENA_API_ERROR_CODE);
        return dataList;
    }
    
    /**
     * 業務異常時、APIのステータス取得
     * @param e
     * @return
     */
    public static ApiStatusModel getApiStatusInfo(Exception exception) {
        
        ApiStatusModel apiStatusModel = new ApiStatusModel();
        Integer httpStatusCode = AmericaStockReturnCode.HttpStatusCode.HTTP_408_CODE;
        apiStatusModel.setApiStatusCode(httpStatusCode);
        if (exception instanceof AthenaBusinessException) {
            apiStatusModel.setApiErrorCode(((AthenaBusinessException) exception).getErrorCode());
            apiStatusModel.setApiStatusCode(((AthenaBusinessException) exception).getStatusCode());
            apiStatusModel.setApiMessage(((AthenaBusinessException) exception).getMessage());
        }
        return apiStatusModel;
    }
    
}
