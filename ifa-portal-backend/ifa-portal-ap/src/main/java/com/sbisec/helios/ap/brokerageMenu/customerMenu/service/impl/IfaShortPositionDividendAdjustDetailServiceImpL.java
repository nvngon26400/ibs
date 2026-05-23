package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaShortPositionDividendAdjustDetailDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaShortPositionDividendAdjustDetailSqlModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaShortPositionDividendAdjustDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaShortPositionDividendAdjustDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaShortPositionDividendAdjustDetailA001ResponseDtoShortPositionDividendAdjustDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaShortPositionDividendAdjustDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_010302-02
 * 画面名：売建配当調整金明細
 * 2024/06/21 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Component(value = "cmpIfaShortPositionDividendAdjustDetailService")
public class IfaShortPositionDividendAdjustDetailServiceImpL implements IfaShortPositionDividendAdjustDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaShortPositionDividendAdjustDetailServiceImpL.class);
    
    /** 権限チェックエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOTEXISTS = "errors.butenAccountNotExist";
    
    /** 信用口座未開設エラー */
    private static final String ERRORS_DMS_NOT_OPEN = "errors.dms.domesticMarginAccount.notOpen";
    
    /** 該当情報存在なしエラー */
    private static final String ERRORS_CMN_INFORMATION_NOTFOUND = "errors.cmn.information.notfound";
    
    /** 区分.信用口座(国内) : "1"（信用口座） */
    private static final String ACCOUNT_STATUS_CLOSED = "1";
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct007 fct007;
    
    @Autowired
    private IfaShortPositionDividendAdjustDetailDao dao;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaShortPositionDividendAdjustDetailA001RequestDto
     * Dto レスポンス：IfaShortPositionDividendAdjustDetailA001ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaShortPositionDividendAdjustDetailA001ResponseDto> initializeA001(
            IfaShortPositionDividendAdjustDetailA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaShortPositionDividendAdjustDetailServiceImpL.initializeA001");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inpFct001Dto = new InputFct001Dto();
        inpFct001Dto.setButenCode(cc.getButenCode());
        inpFct001Dto.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto outFct001Dto = fct001.doCheck(inpFct001Dto);
        // 対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outFct001Dto.getTargetCustomerRefAuthFlag())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        
        // ②顧客共通情報の「信用口座区分(国内)」をもとに、信用口座開設状況をチェックを行う。
        if (!ACCOUNT_STATUS_CLOSED.equals(cc.getDomesticMarginAccountType())) {
            // 「未開設」：信用口座未開設エラーを返す。
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_NOT_OPEN,
                    IfaCommonUtil.getMessage(ERRORS_DMS_NOT_OPEN));
        }
        
        List<IfaShortPositionDividendAdjustDetailSqlModel> sqlResultList = dao
                .selectIfaShortPositionDividendAdjustDetailSql00101(cc.getCustomerCode());
        
        List<IfaShortPositionDividendAdjustDetailA001ResponseDtoShortPositionDividendAdjustDetail> detailList = new ArrayList<>();
        for (IfaShortPositionDividendAdjustDetailSqlModel sqlModel : sqlResultList) {
            IfaShortPositionDividendAdjustDetailA001ResponseDtoShortPositionDividendAdjustDetail detail = new IfaShortPositionDividendAdjustDetailA001ResponseDtoShortPositionDividendAdjustDetail();
            detail.setBrandCode(sqlModel.getBrandCd());
            detail.setBrandName(sqlModel.getBrandName());
            if (sqlModel.getUnitAmount().indexOf(".") > -1) {
                String[] amounts = sqlModel.getUnitAmount().split("\\.");
                String decimal = amounts[1];
                if (decimal.length() > 2) {
                    decimal = decimal.substring(0, 2);
                }
                detail.setExpectedDividend(amounts[0] + "円" + decimal + "銭");
            } else {
                detail.setExpectedDividend(sqlModel.getUnitAmount() + "円");
            }
            detail.setStockQuantity(sqlModel.getQuantity());
            detail.setRestraintAmount(sqlModel.getLockAmount());
            detail.setRightLastDate(getRightLastDate(sqlModel.getLostRightDate()));
            detailList.add(detail);
        }
        
        IfaShortPositionDividendAdjustDetailA001ResponseDto resDto = new IfaShortPositionDividendAdjustDetailA001ResponseDto();
        resDto.setButenCode(cc.getButenCode());
        resDto.setAccountNumber(cc.getAccountNumber());
        resDto.setSearchResultCount(String.valueOf(sqlResultList.size()));
        resDto.setDetailList(detailList);
        
        List<IfaShortPositionDividendAdjustDetailA001ResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(resDto);
        return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * 権利付最終日を取得する.
     *
     * @param lostRightDate 拘束解除日
     * @throws ParseException データフォーマット変換エラー
     * @throws Exception システムエラー
     */
    private String getRightLastDate(String lostRightDate) throws ParseException, Exception {
        
        if (StringUtils.isEmpty(lostRightDate)) {
            return StringUtils.EMPTY;
        }
        
        InputFct007Dto inputFct007Dto = new InputFct007Dto();
        inputFct007Dto.setStandardDate(new SimpleDateFormat(DateUtil.YYYYMMDD).parse(lostRightDate));
        inputFct007Dto.setCalendarType("0");
        inputFct007Dto.setDay(-1);
        return new SimpleDateFormat(DateUtil.SEPARATED_YYYYMMDD)
                .format(fct007.getData(inputFct007Dto).getDesignatedDate());
    }
}
