package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaDomesticMarginPositionDueDateAlertDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginPositionDueDateAlertSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginPositionDueDateAlertSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMarginPositionDueDateAlertA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMarginPositionDueDateAlertA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaDomesticMarginPositionDueDateAlertService;
import com.sbisec.helios.ap.common.dto.PreviousBusinessDateDtoRequest;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.SystemDateService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020301_02-01
 * 画面名：国内信用建玉期日アラート一覧
 *
 * @author BASE 李
 2024/06/19 新規作成
 */
@Component(value = "cmpIfaDomesticMarginPositionDueDateAlertService")
public class IfaDomesticMarginPositionDueDateAlertServiceImpL implements IfaDomesticMarginPositionDueDateAlertService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaDomesticMarginPositionDueDateAlertServiceImpL.class);
    
    @Autowired
    private IfaDomesticMarginPositionDueDateAlertDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct007 fct007;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    @Autowired
    private SystemDateService systemDateService;
    
    private static final String ERRORS_SELECTED_PARAM = "取引コース";
    
    private static final String QUERY_SIZE_LIMIT = "5000";
    
    private static final String CALENDAR_TYPE_0 = "0";
    
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
     * Dto リクエスト：IfaDomesticMarginPositionDueDateAlertA002RequestDto
     * Dto レスポンス：IfaDomesticMarginPositionDueDateAlertA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMarginPositionDueDateAlertA002ResponseDto> displayA002(
            IfaDomesticMarginPositionDueDateAlertA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaDomesticMarginPositionDueDateAlertA002ResponseDto> dtoRes = new DataList<IfaDomesticMarginPositionDueDateAlertA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMarginPositionDueDateAlertServiceImplL.displayA002");
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
        IfaDomesticMarginPositionDueDateAlertSql001RequestModel ifaDomesticMarginPositionDueDateAlertSql001RequestModel = new IfaDomesticMarginPositionDueDateAlertSql001RequestModel();
        ifaDomesticMarginPositionDueDateAlertSql001RequestModel.setBrokerChargeList(new ArrayList<BrokerCharge>());
        if (!PRI_ID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            List<BrokerCharge> brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            } else {
                ifaDomesticMarginPositionDueDateAlertSql001RequestModel.setBrokerChargeList(brokerChargeList);
            }
        }
        // ③ IFA顧客アラートを取得する。
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYYMMDD);
        Integer codeDays = dao.selectIfaDomesticMarginPositionDueDateAlertSql005();
        if (codeDays == 0) {
            ifaDomesticMarginPositionDueDateAlertSql001RequestModel
                    .setDesignatedDate(dao.selectIfaDomesticMarginPositionDueDateAlertSql006());
        } else {
            InputFct007Dto fct007InData = new InputFct007Dto();
            fct007InData.setStandardDate(ifaDateUtil.getCurrentDate());
            fct007InData.setCalendarType(CALENDAR_TYPE_0);
            fct007InData.setDay(codeDays);
            // ④ 指定日を取得する
            OutputFct007Dto fct007OutData = fct007.getData(fct007InData);
            ifaDomesticMarginPositionDueDateAlertSql001RequestModel
                    .setDesignatedDate(sdf.format(fct007OutData.getDesignatedDate()));
        }
        BeanUtils.copyProperties(ifaDomesticMarginPositionDueDateAlertSql001RequestModel, dtoReq);
        ifaDomesticMarginPositionDueDateAlertSql001RequestModel.setPrivId(privId);
        ifaDomesticMarginPositionDueDateAlertSql001RequestModel
                .setDateYmd(systemDateService.getPreviousBusinessDate(new PreviousBusinessDateDtoRequest()));
        // ⑤ 決済期日データリスト情報を取得する。
        DataList<IfaDomesticMarginPositionDueDateAlertSql001ResponseModel> sql001Res = dao
                .selectIfaDomesticMarginPositionDueDateAlertSql001(
                        ifaDomesticMarginPositionDueDateAlertSql001RequestModel);
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
        for (IfaDomesticMarginPositionDueDateAlertSql001ResponseModel sql001ResModel : sql001Res.getDataList()) {
            IfaDomesticMarginPositionDueDateAlertA002ResponseDto a002ResDto = new IfaDomesticMarginPositionDueDateAlertA002ResponseDto();
            BeanUtils.copyProperties(a002ResDto, sql001ResModel);
            if (StringUtils.compare(sql001ResModel.getDisplayLastTradeDate(), sql001ResModel.getSbiLastTradeDate(),
                    false) > 0) {
                a002ResDto.setLastTradeDate(sql001ResModel.getSbiLastTradeDate());
            } else {
                a002ResDto.setLastTradeDate(sql001ResModel.getDisplayLastTradeDate());
            }
            dtoRes.getDataList().add(a002ResDto);
        }
        return dtoRes;
    }
    
}
