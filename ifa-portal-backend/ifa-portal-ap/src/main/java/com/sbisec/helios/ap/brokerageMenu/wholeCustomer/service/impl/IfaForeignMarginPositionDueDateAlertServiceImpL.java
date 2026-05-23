package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaForeignMarginPositionDueDateAlertDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginPositionDueDateAlertSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginPositionDueDateAlertSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginPositionDueDateAlertA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginPositionDueDateAlertA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaForeignMarginPositionDueDateAlertService;
import com.sbisec.helios.ap.common.dto.PreviousBusinessDateDtoRequest;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.SystemDateService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020301_02-02
 * 画面名：米株信用建玉期日アラート一覧
 *
 * @author BASE 李
 2024/06/21 新規作成
 */
@Component(value = "cmpIfaForeignMarginPositionDueDateAlertService")
public class IfaForeignMarginPositionDueDateAlertServiceImpL implements IfaForeignMarginPositionDueDateAlertService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignMarginPositionDueDateAlertServiceImpL.class);
    
    @Autowired
    private IfaForeignMarginPositionDueDateAlertDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct007 fct007;
    
    @Autowired
    private SystemDateService systemDateService;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    private static final String CALENDAR_TYPE_0 = "0";
    
    private static final String ERRORS_SELECTED_PARAM = "取引コース";
    
    private static final String QUERY_SIZE_LIMIT = "5000";
    
    /** ユーザ権限コード '1':SBI証券本店 */
    private static final String PRI_ID_1 = "1";
    
    private enum MessageId {
        
        ERRORS_SELECTED("errors.selected"), ERRORS_CMN_IFAAGENTCODES_NOTEXIST(
                "errors.cmn.ifaAgentCodes.notExist"), ERRORS_DATALIST_NOTFOUND(
                        "errors.dataList.notfound"), WARNINGS_DATALIST_OVERMAXROWNUM(
                                "warnings.dataList.overMaxRownum"),;
        
        private String key;
        
        private MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaForeignMarginPositionDueDateAlertA002RequestDto
     * Dto レスポンス：IfaForeignMarginPositionDueDateAlertA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignMarginPositionDueDateAlertA002ResponseDto> displayA002(
            IfaForeignMarginPositionDueDateAlertA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignMarginPositionDueDateAlertA002ResponseDto> dtoRes = new DataList<IfaForeignMarginPositionDueDateAlertA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginPositionDueDateAlertServiceImplL.displayA002");
        }
        // ① リクエスト.取引コースのチェックを行う。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] { ERRORS_SELECTED_PARAM }));
        }
        
        // ② ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        IfaForeignMarginPositionDueDateAlertSql001RequestModel ifaForeignMarginPositionDueDateAlertSql001RequestModel = new IfaForeignMarginPositionDueDateAlertSql001RequestModel();
        ifaForeignMarginPositionDueDateAlertSql001RequestModel.setBrokerChargeList(new ArrayList<BrokerCharge>());
        if (!PRI_ID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            List<BrokerCharge> brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            } else {
                ifaForeignMarginPositionDueDateAlertSql001RequestModel.setBrokerChargeList(brokerChargeList);
            }
        }
        BeanUtils.copyProperties(ifaForeignMarginPositionDueDateAlertSql001RequestModel, dtoReq);
        ifaForeignMarginPositionDueDateAlertSql001RequestModel.setPrivId(privId);
        ifaForeignMarginPositionDueDateAlertSql001RequestModel
                .setDateYmd(systemDateService.getPreviousBusinessDate(new PreviousBusinessDateDtoRequest()));
        // IFA顧客アラートを取得する
        Integer codeDays = dao.selectIfaForeignMarginPositionDueDateAlertSql005();
        // 指定日に設定する
        if (codeDays == 0) {
            ifaForeignMarginPositionDueDateAlertSql001RequestModel
                    .setDisplayLastTradeDate(dao.selectIfaForeignMarginPositionDueDateAlertSql006());
        } else {
            ifaForeignMarginPositionDueDateAlertSql001RequestModel
                    .setDisplayLastTradeDate(getFct007Date(ifaDateUtil.getCurrentDate(), CALENDAR_TYPE_0, codeDays));
        }
        // ③ 決済期日データリスト情報を取得する。
        DataList<IfaForeignMarginPositionDueDateAlertSql001ResponseModel> sql001Res = dao
                .selectIfaForeignMarginPositionDueDateAlertSql001(
                        ifaForeignMarginPositionDueDateAlertSql001RequestModel);
        if (sql001Res.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                    MessageId.ERRORS_DATALIST_NOTFOUND.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
        }
        if (sql001Res.get(0).getTotalCount() > 5000) {
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            dtoRes.setMessage(IfaCommonUtil.getMessage(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key,
                    new String[] { QUERY_SIZE_LIMIT, String.valueOf(sql001Res.get(0).getTotalCount()) }));
            dtoRes.setReturnCode(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key);
        }
        for (IfaForeignMarginPositionDueDateAlertSql001ResponseModel sql001ResModel : sql001Res.getDataList()) {
            IfaForeignMarginPositionDueDateAlertA002ResponseDto a002ResDto = new IfaForeignMarginPositionDueDateAlertA002ResponseDto();
            BeanUtils.copyProperties(a002ResDto, sql001ResModel);
            dtoRes.getDataList().add(a002ResDto);
        }
        return dtoRes;
    }
    
    private String getFct007Date(Date standardDate, String calendarType, Integer day) throws Exception {
        
        var fct007InData = new InputFct007Dto();
        fct007InData.setStandardDate(standardDate);
        fct007InData.setCalendarType(calendarType);
        fct007InData.setDay(day);
        var sdf = new SimpleDateFormat(DateUtil.YYYYMMDD);
        return sdf.format(fct007.getData(fct007InData).getDesignatedDate());
    }
    
}
