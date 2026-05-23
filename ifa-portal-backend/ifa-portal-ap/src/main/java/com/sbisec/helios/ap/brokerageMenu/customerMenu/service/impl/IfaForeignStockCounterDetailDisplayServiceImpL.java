package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignStockCounterDetailDisplayDao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterDetailDisplayA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterDetailDisplayA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignStockCounterDetailDisplayService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterDetailDisplaySql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterDetailDisplaySql001RequestModel;
import org.apache.commons.beanutils.BeanUtils;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;

/**
 * 画面ID：SUB0202_0104-03
 * 画面名：外国株式店頭詳細表示

 * @author 大崎
　　　2024/03/19 新規作成
 */

@Component(value = "cmpIfaForeignStockCounterDetailDisplayService")
public class IfaForeignStockCounterDetailDisplayServiceImpL implements IfaForeignStockCounterDetailDisplayService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignStockCounterDetailDisplayServiceImpL.class);

    /** 権限チェック値 「権限なし」 */
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG_0 = "0";

    /** 権限チェックエラー */
    public static final String ERRORS_BUTEN_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    @Autowired
    private IfaForeignStockCounterDetailDisplayDao dao;

    @Autowired
    private Fct001 fct001;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignStockCounterDetailDisplayA001DtoRequest
     * Dto レスポンス：IfaForeignStockCounterDetailDisplayA001DtoResponse
     * model リクエスト：IfaForeignStockCounterDetailDisplaySql001RequestModel
     * model レスポンス：IfaForeignStockCounterDetailDisplaySql001ResponseModel

     * @param dtoReq リクエスト
     * @return IfaForeignStockCounterDetailDisplayA001DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaForeignStockCounterDetailDisplayA001DtoResponse> initializeA001(IfaForeignStockCounterDetailDisplayA001DtoRequest dtoReq)
            throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignStockCounterDetailDisplayServiceImplL.initializeA001");
        }
            
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
       
        // 利用者の口座に対する権限チェック
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(butenCode);
        inputFct001Dto.setAccountNumber(accountNumber);
        
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        // 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す
        if (TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXISTS, IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                    new String[] { butenCode, accountNumber }));
        }
        
        // SQL001 外国株式店頭注文詳細表示情報取得
        IfaForeignStockCounterDetailDisplaySql001RequestModel selSql001Req = new IfaForeignStockCounterDetailDisplaySql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);

        DataList<IfaForeignStockCounterDetailDisplaySql001ResponseModel> selSql001Res = dao.selectIfaForeignStockCounterDetailDisplaySql001(selSql001Req);
        List<IfaForeignStockCounterDetailDisplayA001DtoResponse> responseList = new ArrayList<IfaForeignStockCounterDetailDisplayA001DtoResponse>();
        IfaForeignStockCounterDetailDisplayA001DtoResponse response = new IfaForeignStockCounterDetailDisplayA001DtoResponse();
        // 1件取得
        BeanUtils.copyProperties(response, selSql001Res.getDataList().get(0));
        responseList.add(response);
        
        DataList<IfaForeignStockCounterDetailDisplayA001DtoResponse> dtoRes = new DataList<IfaForeignStockCounterDetailDisplayA001DtoResponse>();
        dtoRes = IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);

        return dtoRes;
    }
}
