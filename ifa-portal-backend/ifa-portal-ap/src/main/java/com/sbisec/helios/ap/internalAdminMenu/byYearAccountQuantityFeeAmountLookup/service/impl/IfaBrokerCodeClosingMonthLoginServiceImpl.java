package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.service.impl;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.IfaBrokerCodeClosingMonthLoginDao;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA006RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA006ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.service.IfaBrokerCodeClosingMonthLoginService;

/**
 * 画面ID：SUB0406-01_1
 * 画面名：仲介業者決算月設定
 *
 * @author SBI大連 夏
 * @date   2025/05/27
 */

@Component(value = "cmpIfaBrokerCodeClosingMonthLoginService")
public class IfaBrokerCodeClosingMonthLoginServiceImpl implements IfaBrokerCodeClosingMonthLoginService {

    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrokerCodeClosingMonthLoginServiceImpl.class);
    
    /** 入力した仲介業者コードは存在しません。<br>仲介業者コード: [{0}] 
     *  {0}: (リクエスト.仲介業者コード) 
     */
    private static final String ERRORS_BROKERCODE_NOTEXIST = "errors.brokerCodeNotExist";
    
    /** 選択した仲介業者に関する操作はできません。 */
    private static final String ERRORS_TARGETBROKERCODENOTEXIST = "errors.targetBrokerCodeNotExist";
    
    /**
     * 当決算年度が12ヶ月を超過するため設定できません｡\n
     * 当決算年度終了後に再設定してください｡
     */
    private static final String ERRORS_CLOSINGMONTH_PROCEEDED = "errors.closingMonth.proceeded";
    
    /** {0}が失敗しました。 */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";
    
    /** 更新時のエラーメッセージ */
    private static final String UPDATE_ERROR_MSG_ATTR = "仲介業者決算月登録･更新";
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private IfaBrokerCodeClosingMonthLoginDao dao;
   
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBrokerCodeClosingMonthLoginA001RequestDto
     * Dto レスポンス：IfaBrokerCodeClosingMonthLoginA001ResponseDto
     * model リクエスト：IfaBrokerCodeClosingMonthLoginSql002RequestModel
     * model レスポンス：IfaBrokerCodeClosingMonthLoginSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaBrokerCodeClosingMonthLoginA001ResponseDto> initializeA001(
        IfaBrokerCodeClosingMonthLoginA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaBrokerCodeClosingMonthLoginA001ResponseDto> dtoRes = new DataList<IfaBrokerCodeClosingMonthLoginA001ResponseDto>();
        List<IfaBrokerCodeClosingMonthLoginA001ResponseDto> dtoResList = dtoRes.getDataList();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaBrokerCodeClosingMonthLoginServiceImpl.initializeA001");
        
        // ユーザ共通情報.権限コード = 仲介業者の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (PrivId.B_INNER_MANAGEMENT.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_TARGETBROKERCODENOTEXIST, IfaCommonUtil.getMessage(ERRORS_TARGETBROKERCODENOTEXIST));
            } else if (brokerChargeList.size() == 1) {
                dtoResList = brokerChargeList.stream()
                    .limit(1)
                    .map(broker -> new IfaBrokerCodeClosingMonthLoginA001ResponseDto(broker.getBrokerCode()))
                    .collect(Collectors.toList());
            } else if (brokerChargeList.size() > 1) {
                dtoResList = brokerChargeList.stream()
                    .limit(2)
                    .map(broker -> new IfaBrokerCodeClosingMonthLoginA001ResponseDto(broker.getBrokerCode()))
                    .collect(Collectors.toList());
            }
        }
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }

    
    /**
     * アクションID：A004
     * アクション名：仲介業者コード入力
     * Dto リクエスト：IfaBrokerCodeClosingMonthLoginA004RequestDto
     * Dto レスポンス：IfaBrokerCodeClosingMonthLoginA004ResponseDto
     * model リクエスト：IfaBrokerCodeClosingMonthLoginSql001RequestModel
     * model リクエスト：IfaBrokerCodeClosingMonthLoginSql002RequestModel
     * model レスポンス：IfaBrokerCodeClosingMonthLoginSql001ResponseModel
     * model レスポンス：IfaBrokerCodeClosingMonthLoginSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaBrokerCodeClosingMonthLoginA004ResponseDto> getBrokerNameClosingMonthA004(
        IfaBrokerCodeClosingMonthLoginA004RequestDto dtoReq) throws Exception{
        
        DataList<IfaBrokerCodeClosingMonthLoginA004ResponseDto> dtoRes = new DataList<IfaBrokerCodeClosingMonthLoginA004ResponseDto>();
        List<IfaBrokerCodeClosingMonthLoginA004ResponseDto> dtoResList = dtoRes.getDataList();
        IfaBrokerCodeClosingMonthLoginA004ResponseDto a001Res = new IfaBrokerCodeClosingMonthLoginA004ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBrokerCodeClosingMonthLoginServiceImpl.initializeA001");
        }
            
        // ユーザ共通情報.権限コード = 仲介業者の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (PrivId.B_INNER_MANAGEMENT.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_BROKERCODE_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_BROKERCODE_NOTEXIST, new String[] { dtoReq.getBrokerCode() }));
            }
        }
        if (StringUtils.isNotEmpty(dtoReq.getBrokerCode())) {
            IfaBrokerCodeClosingMonthLoginSql001ResponseModel brokerName = getBrokerName(dtoReq.getBrokerCode(), brokerChargeList);
            IfaBrokerCodeClosingMonthLoginSql002ResponseModel closingMonth = getClosingMonth(dtoReq.getBrokerCode(), brokerChargeList);
            if (brokerName != null && StringUtils.isNotEmpty(brokerName.getBrokerName())) {
                a001Res.setBrokerName(brokerName.getBrokerName());
            } else {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_BROKERCODE_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_BROKERCODE_NOTEXIST, new String[] { dtoReq.getBrokerCode() }));
            }
            if (closingMonth != null && StringUtils.isNotEmpty(closingMonth.getClosingMonth())) {
                a001Res.setClosingMonth(closingMonth.getClosingMonth());
            }
        }
        
        dtoResList.add(a001Res);
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A006
     * アクション名：仲介業者決算月登録･更新
     * Dto リクエスト：IfaBrokerCodeClosingMonthLoginA006RequestDto
     * Dto レスポンス：IfaBrokerCodeClosingMonthLoginA006ResponseDto
     * model リクエスト：IfaBrokerCodeClosingMonthLoginSql003RequestModel
     * model リクエスト：IfaBrokerCodeClosingMonthLoginSql003RequestModel
     * model レスポンス：IfaBrokerCodeClosingMonthLoginSql004ResponseModel
     * model レスポンス：IfaBrokerCodeClosingMonthLoginSql004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaBrokerCodeClosingMonthLoginA006ResponseDto> updateBrokerCodeClosingMonthA006(
        IfaBrokerCodeClosingMonthLoginA006RequestDto dtoReq) throws Exception {
        
        DataList<IfaBrokerCodeClosingMonthLoginA006ResponseDto> dtoRes = new DataList<IfaBrokerCodeClosingMonthLoginA006ResponseDto>();
        List<IfaBrokerCodeClosingMonthLoginA006ResponseDto> dtoResList = dtoRes.getDataList();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBrokerCodeClosingMonthLoginServiceImpl.updateBrokerCodeClosingMonth");
        }
        
        // ユーザ共通情報.権限コード = 仲介業者の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        String userName = IfaCommonUtil.getUserAccount().getMedUsers().getUserId();
        List<BrokerCharge> brokerChargeList = null;
        if (PrivId.B_INNER_MANAGEMENT.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_BROKERCODE_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_BROKERCODE_NOTEXIST, new String[] { dtoReq.getBrokerCode() }));
            }
        }
        
        if (StringUtils.isNotEmpty(dtoReq.getBrokerCode()) && StringUtils.isNotEmpty(dtoReq.getSettingClosingMonth())) {
            // 仲介業者期初年月を取得
            IfaBrokerCodeClosingMonthLoginSql003ResponseModel beginYearMonth = getBeginYearMonth(dtoReq.getBrokerCode(), brokerChargeList);
            if (StringUtils.isNotEmpty(beginYearMonth.getBeginYearMonth())) {
                // 期初年月
                final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(IfaDateUtil.YYYYMM);
                YearMonth yearMonth = YearMonth.parse(beginYearMonth.getBeginYearMonth(), dateFormatter);
                // 期初年月の年
                int year = yearMonth.getYear();
                // 画面設定した決算月
                int month = Integer.parseInt(dtoReq.getSettingClosingMonth());
                // 次回決算年月=期初年+画面設定した決算月
                YearMonth nextClosingYearMonth = YearMonth.of(year, month);
                // 次回決算年月<=期初年月の場合､次回決算年月の年を+1する｡
                if (nextClosingYearMonth.isBefore(yearMonth) || nextClosingYearMonth.equals(yearMonth)) {
                    nextClosingYearMonth = nextClosingYearMonth.plusYears(1);
                }
                // 次回決算年月>=期初年月+1の場合､メッセージを表示して処理を終了する｡
                if (nextClosingYearMonth.isAfter(yearMonth.plusYears(1)) || nextClosingYearMonth.equals(yearMonth.plusYears(1))) {
                    return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CLOSINGMONTH_PROCEEDED, IfaCommonUtil.getMessage(ERRORS_CLOSINGMONTH_PROCEEDED));
                }
            }
            try {
                // 仲介業者決算月を登録･更新する｡
                updateClosingMonth(dtoReq.getBrokerCode(),dtoReq.getSettingClosingMonth(), userName);
            } catch (Exception e) {
                LOGGER.debug("updateBrokerCodeClosingMonthA006 update error" + e.getMessage());
                String errorMessage = IfaCommonUtil.getMessage(ERRORS_PROCESSING_FAILED, new String[] { UPDATE_ERROR_MSG_ATTR });
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_PROCESSING_FAILED, errorMessage); 
            }
        }
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }
    
    
    /**
     * 仲介業者名を取得する｡
     * 
     * @param privId
     * @param brokerCode
     * @param brokerChargeList
     * @return
     * @throws Exception
     */
    private IfaBrokerCodeClosingMonthLoginSql001ResponseModel getBrokerName(String brokerCode, List<BrokerCharge> brokerChargeList) throws Exception {
        
        IfaBrokerCodeClosingMonthLoginSql001RequestModel sqlReq = new IfaBrokerCodeClosingMonthLoginSql001RequestModel();
        sqlReq.setBrokerCode(brokerCode);
        sqlReq.setBrokerChargeList(brokerChargeList);
        
        IfaBrokerCodeClosingMonthLoginSql001ResponseModel sqlRes = new IfaBrokerCodeClosingMonthLoginSql001ResponseModel();
        sqlRes = dao.selectIfaBrokerCodeClosingMonthLoginSql001(sqlReq);
        
        return sqlRes;
    }

    /**
     * 仲介業者決算月を取得する｡
     * 
     * @param privId
     * @param brokerCode
     * @param brokerChargeList
     * @return
     * @throws Exception
     */
    private IfaBrokerCodeClosingMonthLoginSql002ResponseModel getClosingMonth(String brokerCode, List<BrokerCharge> brokerChargeList) throws Exception {
        
        IfaBrokerCodeClosingMonthLoginSql002RequestModel sqlReq = new IfaBrokerCodeClosingMonthLoginSql002RequestModel();
        sqlReq.setBrokerCode(brokerCode);
        sqlReq.setBrokerChargeList(brokerChargeList);
        
        IfaBrokerCodeClosingMonthLoginSql002ResponseModel sqlRes = new IfaBrokerCodeClosingMonthLoginSql002ResponseModel();
        sqlRes = dao.selectIfaBrokerCodeClosingMonthLoginSql002(sqlReq);
        
        return sqlRes;
    }
    
    
    /**
     * 仲介業者期初年月を取得する。
     * 
     * @param privId
     * @param brokerCode
     * @param brokerChargeList
     * @return
     * @throws Exception
     */
    private IfaBrokerCodeClosingMonthLoginSql003ResponseModel getBeginYearMonth(String brokerCode, List<BrokerCharge> brokerChargeList) throws Exception {
        
        IfaBrokerCodeClosingMonthLoginSql003RequestModel sqlReq = new IfaBrokerCodeClosingMonthLoginSql003RequestModel();
        sqlReq.setBrokerChargeList(brokerChargeList);
        sqlReq.setBrokerCode(brokerCode);
        IfaBrokerCodeClosingMonthLoginSql003ResponseModel sqlRes = new IfaBrokerCodeClosingMonthLoginSql003ResponseModel();
        sqlRes = dao.selectIfaBrokerCodeClosingMonthLoginSql003(sqlReq);
        return sqlRes;
    }
    
    /**
     * 仲介業者決算月を登録･更新する｡
     * 
     * @param brokerCode
     * @param settingClosingMonth
     * @param userName
     * @throws Exception
     */
    private void updateClosingMonth(String brokerCode, String settingClosingMonth, String userName) throws Exception {
        
        IfaBrokerCodeClosingMonthLoginSql004RequestModel sqlReq = new IfaBrokerCodeClosingMonthLoginSql004RequestModel();
        sqlReq.setBrokerCode(brokerCode);
        sqlReq.setSettingClosingMonth(settingClosingMonth);
        sqlReq.setUserName(userName);
        dao.updateIfaBrokerCodeClosingMonthLoginSql004(sqlReq);
    }
    
}
