package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;


import java.util.Collections;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaBbApplyCorrectConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql013ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql014RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql014ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IfaBbApplyCorrectConfirmService;
import com.sbisec.helios.ap.common.annotation.dao.EtintraTransactional;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0204_02-02_2
 * 画面名：BB申込訂正確認
 *
 * @author BASE 李
 2024/04/23 新規作成
 */
@Component(value = "cmpIfaBbApplyCorrectConfirmService")
public class IfaBbApplyCorrectConfirmServiceImpL implements IfaBbApplyCorrectConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBbApplyCorrectConfirmServiceImpL.class);

    @Autowired
    private IfaBbApplyCorrectConfirmDao dao;
    

    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct006 fct006;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    /** 権限なし */
    private static final String NO_AUTHORIZED = "0";
    
    /** 取引停止フラグ＝"1 */
    private static final String TRADE_SUSPEND_FLAG_1 = "1";
    
    /** IPO */
    private static final String IPO_PO_KBN_IPO = "1";
    
    /** PO */
    private static final String IPO_PO_KBN_PO = "2";
    
    /** 成行あり */
    private static final String MARKET_ORDER_ON = "1";
    /** 成行なし */
    private static final String MARKET_ORDER_OFF = "0";
    
    /** 発行価格区分 価格表示 */
    private static final String ISSUE_PRICE_TYPE_PRICE = "1";
    /** 発行価格区分 ディスカウント */
    private static final String ISSUE_PRICE_TYPE_DISCOUNT = "2";
    
    /** FCT003パラメータ 証券金銭種別  国内*/
    private static final String PRODUCT_CD_DOMESTIC = "01";
    /** FCT003パラメータ 取引種別 BB申込*/
    private static final String TRADE_CD_DOMESTIC = "9";
    
    /** FCT006パラメータ */
    private static final String BR_DOMESTIC_FGN_IND = "0";
    private static final String BR_BRAND_IND = "1 ";
    private static final String COMPLA_CHECK_KIND = "1";
    
    /** FCT006.判定結果 */
    private static final String FCT006_JUDGEMENT_RESULT_0 = "0";
    private static final String FCT006_JUDGEMENT_RESULT_1 = "1";
    private static final String FCT006_JUDGEMENT_RESULT_2 = "2";
    
    /** 金融資産区分 */
    private static final String FINANCIAL_ASSETS_TYPE_06 = "06";
    private static final String FINANCIAL_ASSETS_TYPE_07 = "07";
    private static final String FINANCIAL_ASSETS_TYPE_08 = "08";
    private static final String FINANCIAL_ASSETS_TYPE_99 = "99";
    
    /** 法人区分 個人 */
    private static final String CORPORATION_TYPE_KOJIN = "0";
    
    /** 取得した件数が0件 */
    private static final String RESULT_COUNT_0 = "0";
    
    /** 緊急入力停止エラーメッセージパラメータ　"BB申込" */
    private static final String URGENT_STOP_ERROR_MESSAGE = "BB訂正";

    /** 緊急入力停止ON */
    private static final String URGENT_STOP_ON = "1";
    
    /** 電子交付のみフラグがON */
    private static final String EDELIVONLYFLAG_ON = "1";
    /** FCT006結果 */
    private static final String INVITATION_CHECK_ON = "1";
    
    /** 裁量配分割当回数5回以上チェックボックスがON */
    private static final String DISCRETION_ALLOCATE_TIMES_OVER_FIVE_ON = "1";
    /** 裁量配分割当回数5回以上チェックボックスがOFF */
    private static final String DISCRETION_ALLOCATE_TIMES_OVER_FIVE_OFF = "0";
    /** 金融資産3000万円未満チェックボックスがON */
    private static final String FINANCIAL_ASSETLESS_THAN_THIRTY_MILLION_YEN_DISCRETION_APPLY_ON = "1";
    /** 金融資産3000万円未満チェックボックスがOFF */
    private static final String FINANCIAL_ASSETLESS_THAN_THIRTY_MILLION_YEN_DISCRETION_APPLY_OFF = "0";
    
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    private static final String MSG_DISPLAY_TARGET_TRADE_PARRTEN = "7";
    /** errors.insertDataExist  エラーメッセージパラメータ*/
    private static final String ERRORS_INSERTDATAEXIST_MESSAGE_PARAM = "BB申込情報";
    
    /** 裁量配分可否No.5の×の場合エラーメッセージ　パラメータ */
    private static final String ERROR_MESSAGE_NO_5 = "裁量希望の場合、価格の最大値";
    /** 裁量配分可否No.16の×の場合エラーメッセージ　パラメータ */
    private static final String ERROR_MESSAGE_NO_16 = "裁量希望の場合、ディスカウント率の最小値";
    /** 裁量配分可否他の×の場合エラーメッセージ */
    private static final String[] ERROR_MESSAGE_OTHER = {"年間裁量配分割当回数", "上限回数"};
    /** 申込単位外チェックエラーメッセージ　パラメータ */
    private static final String CHECK_UNIT_ERROR_MESSAGE_QUANTITY = "数量";
    private static final String CHECK_UNIT_ERROR_MESSAGE_DISCRETION_QUANTITY = "裁量希望数量";
    
    private static final String ERRORS_SECTIONID_PARAM = "セクションID";
    
    /** 裁量申込場合、裁量希望数量の上限値チェックエラーメッセージパラメータ　パラメータ */
    private static final String DISCRETION_QUANTITY_UPPER_LIMIT_0 = "裁量希望数量";
    private static final String DISCRETION_QUANTITY_UPPER_LIMIT_1 = "上限数量";
    private static final String DISCRETION_QUANTITY_UPPER_LIMIT_2 = "上限数量";
    
    /** 裁量申込場合、数量、裁量希望数量の整合性チェックエラーメッセージパラメータ　パラメータ。 */
    private static final String ERRORS_TRADERANGE_0 = "裁量希望数量";
    private static final String ERRORS_TRADERANGE_1 = "数量";
    
    private static final String SQL001_RESULT_0_ERROR_MESSAGE = "BB訂正";
    
    /**
     * エラーメッセージId
     *
     * @author BASE李
     *
     */
    private enum MessageId {
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        ERRORS_BRANDCODENOTEXIST("errors.brandCodeNotExist"),
        ERRORS_APPLYTIME("errors.applyTime"),
        ERRORS_URGENTSTOPCHECK("errors.urgentStopCheck"),
        ERRORS_ONLYEDELIVAGREEMENTCHECK("errors.onlyEdelivAgreementCheck"),
        ERRORS_INSERTDATAEXIST("errors.insertDataExist"),
        ERRORS_IPOOVERLIMIT("errors.ipoOverLimit"),
        ERRORS_SELECTED("errors.selected"),
        ERRORS_UNITCHECK("errors.unitCheck"),
        ERRORS_CMN_INFORMATION_OCCURS_2("errors.cmn.information.occurs#2"),
        ERRORS_SECTIONID("errors.sectionId"),
        ERRORS_IPOTRADERANGE("errors.ipoTradeRange"),
        ERRORS_TRADERANGE("errors.tradeRange"),
        ERRORS_EXCLUSIVE("errors.exclusive")
        ;
        private String key;
        
        MessageId(String key) {
            this.key = key;
        }
    }
    
    

    /**
     * アクションID：A001
     * アクション名：申込訂正
     * Dto リクエスト：IfaBbApplyCorrectConfirmA001RequestDto
     * Dto レスポンス：IfaBbApplyCorrectConfirmA001ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @EtintraTransactional
    public DataList<IfaBbApplyCorrectConfirmA001ResponseDto> applicationCorrectionA001(IfaBbApplyCorrectConfirmA001RequestDto dtoReq)
            throws Exception {
        DataList<IfaBbApplyCorrectConfirmA001ResponseDto> dtoRes = new DataList<IfaBbApplyCorrectConfirmA001ResponseDto>();
        IfaBbApplyCorrectConfirmA001ResponseDto ifaBbApplyCorrectConfirmA001ResponseDto = new IfaBbApplyCorrectConfirmA001ResponseDto();
        dtoRes.getDataList().add(ifaBbApplyCorrectConfirmA001ResponseDto);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyCorrectConfirmServiceImpL.applicationCorrectionA001");
        }
        String butenCodeReq = dtoReq.getButenCode();
        String accountNumberReq = dtoReq.getAccountNumber();
        // 1 利用者の口座に対する権限チェックを行う。
        // 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(butenCodeReq);
        inputFct001Dto.setAccountNumber(accountNumberReq);
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] {butenCodeReq, accountNumberReq}));
            return dtoRes;
        }
        // 取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。
        if (TRADE_SUSPEND_FLAG_1.equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key));
            return dtoRes;
        }
        // 2　取引コース媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setAccountNumber(accountNumberReq);
        inputFct003Dto.setButenCode(butenCodeReq);
        inputFct003Dto.setProductCd(PRODUCT_CD_DOMESTIC);
        inputFct003Dto.setTradeCd(TRADE_CD_DOMESTIC);
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        boolean mediateFlag = outputFct003Dto.getMediateProprietyList().stream()
                .anyMatch(mediatePropriety -> mediatePropriety.getMediatePropriety().equals(MediateAbleTradeFlag.NASHI.getId())); 
        if (mediateFlag) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, 
                            new String[] {codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_PARRTEN)})
                );
            return dtoRes;
        }
        // 3　コンプラランクチェックを行う。
        InputFct006Dto inputFct006Dto = new InputFct006Dto();
        inputFct006Dto.setAccountNumber(dtoReq.getAccountNumber());
        inputFct006Dto.setButenCode(dtoReq.getButenCode());
        inputFct006Dto.setBrandCode1(dtoReq.getBrandCode());
        inputFct006Dto.setInvitationType(dtoReq.getKanyuKbn());
        inputFct006Dto.setBrDomesticFgnInd(BR_DOMESTIC_FGN_IND);
        inputFct006Dto.setBrBrandInd(BR_BRAND_IND);
        inputFct006Dto.setComplaCheckKind(COMPLA_CHECK_KIND);
        inputFct006Dto.setOrderMethod(dtoReq.getReceiveMethod());
        OutputFct006Dto outputFct006Dto = fct006.doCheck(inputFct006Dto);
        if (FCT006_JUDGEMENT_RESULT_0.equals(outputFct006Dto.getJudgementResult())) {
            // 次へ処理
        } else if (FCT006_JUDGEMENT_RESULT_1.equals(outputFct006Dto.getJudgementResult())) {
            // FCT006.判定結果=1
            
            if (!INVITATION_CHECK_ON.equals(dtoReq.getInvitationCheck())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                        ErrorLevel.FATAL, MessageId.ERRORS_CMN_INFORMATION_OCCURS_2.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_INFORMATION_OCCURS_2.key)
                    );
                return dtoRes;
            }
            
            
        } else if (FCT006_JUDGEMENT_RESULT_2.equals(outputFct006Dto.getJudgementResult())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, outputFct006Dto.getMessageId(),
                    IfaCommonUtil.getMessage(outputFct006Dto.getMessageId())
                );
            return dtoRes;
        } else {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, outputFct006Dto.getMessageId(),
                    IfaCommonUtil.getMessage(outputFct006Dto.getMessageId())
                );
            return dtoRes;
        }
        
        // ④ BB訂正固有チェック
        //  入力した銘柄コード存在件数を取得し、入力銘柄存在チェックを行う。
        IfaBbApplyCorrectConfirmSql009RequestModel ifaBbApplyCorrectConfirmSql009RequestModel = new IfaBbApplyCorrectConfirmSql009RequestModel();
        ifaBbApplyCorrectConfirmSql009RequestModel.setBrandCode(dtoReq.getBrandCode());
        IfaBbApplyCorrectConfirmSql009ResponseModel ifaBbApplyCorrectConfirmSql009ResponseModel = dao.selectIfaBbApplyCorrectConfirmSql009(ifaBbApplyCorrectConfirmSql009RequestModel).get(0);        
        // 取得した件数が0件の場合、エラーメッセージを表示し、処理を終了する。
        if (RESULT_COUNT_0.equals(ifaBbApplyCorrectConfirmSql009ResponseModel.getCount())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_BRANDCODENOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BRANDCODENOTEXIST.key,
                            new String[] {"", dtoReq.getBrandCode()})
                );
            return dtoRes;
        }
        // 申込期間内銘柄情報を取得し、申込期間外チェックを行う。
        IfaBbApplyCorrectConfirmSql001RequestModel ifaBbApplyCorrectConfirmSql001RequestModel = new IfaBbApplyCorrectConfirmSql001RequestModel();
        ifaBbApplyCorrectConfirmSql001RequestModel.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaBbApplyCorrectConfirmSql001ResponseModel> sql001Res = dao.selectIfaBbApplyCorrectConfirmSql001(ifaBbApplyCorrectConfirmSql001RequestModel);    
        // 取得した件数が0件の場合、エラーメッセージを表示し、処理を終了する。
        if (sql001Res.size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_APPLYTIME.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_APPLYTIME.key,
                            new String[] {SQL001_RESULT_0_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        // 緊急入力停止がONの場合、エラーメッセージを表示し、処理を終了する。
        IfaBbApplyCorrectConfirmSql001ResponseModel sql001ResModel = sql001Res.get(0);
        if (URGENT_STOP_ON.equals(sql001ResModel.getUrgentStop())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_URGENTSTOPCHECK.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_URGENTSTOPCHECK.key,
                            new String[] {URGENT_STOP_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        
        // 顧客情報を取得し、口座存在チェックを行う。
        IfaBbApplyCorrectConfirmSql003RequestModel ifaBbApplyCorrectConfirmSql003RequestModel = new IfaBbApplyCorrectConfirmSql003RequestModel();
        ifaBbApplyCorrectConfirmSql003RequestModel.setAccountNumber(accountNumberReq);
        ifaBbApplyCorrectConfirmSql003RequestModel.setButenCode(butenCodeReq);
        DataList<IfaBbApplyCorrectConfirmSql003ResponseModel> sql003Res = 
                dao.selectIfaBbApplyCorrectConfirmSql003(ifaBbApplyCorrectConfirmSql003RequestModel);
        if (sql003Res.size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] {butenCodeReq, accountNumberReq})
                );
            return dtoRes;
        }
        IfaBbApplyCorrectConfirmSql003ResponseModel sql003ResModel = sql003Res.get(0);
        // 電子交付同意書チェックを行う。
        if (EDELIVONLYFLAG_ON.equals(sql001ResModel.getEdelivOnlyFlag()) 
                && ObjectUtils.isEmpty(sql003ResModel.getConsentDate())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_ONLYEDELIVAGREEMENTCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ONLYEDELIVAGREEMENTCHECK.key)
                );
            return dtoRes;
        }
        
        // 裁量申込の場合、「別紙　裁量配分可能チェック」に従って、裁量配分可能チェックを行う。
        String discretionAlloCount = null;
        if (!ObjectUtils.isEmpty(dtoReq.getDiscretionQuantity())) {
            
            // 年間裁量配分割当回数情報を取得する。
            IfaBbApplyCorrectConfirmSql004RequestModel sql004ReqModel = new IfaBbApplyCorrectConfirmSql004RequestModel();
            BeanUtils.copyProperties(sql004ReqModel, dtoReq);
            DataList<IfaBbApplyCorrectConfirmSql004ResponseModel> sql004Res = dao.selectIfaBbApplyCorrectConfirmSql004(sql004ReqModel);
            // 裁量配分割当回数(未抽選)情報を取得する。
            IfaBbApplyCorrectConfirmSql005RequestModel sql005ReqModel = new IfaBbApplyCorrectConfirmSql005RequestModel();
            BeanUtils.copyProperties(sql005ReqModel, dtoReq);
            DataList<IfaBbApplyCorrectConfirmSql005ResponseModel> sql005Res = dao.selectIfaBbApplyCorrectConfirmSql005(sql005ReqModel);
            IfaBbApplyCorrectConfirmSql005ResponseModel sql005ResModel = sql005Res.get(0);    
            // 移管前の裁量配分割当回数(未抽選)を取得する。
            IfaBbApplyCorrectConfirmSql006ResponseModel sql006ResModel;
            IfaBbApplyCorrectConfirmSql004ResponseModel sql004ResModel = sql004Res.get(0);
            if (!ObjectUtils.isEmpty(sql004ResModel.getOldAccountNumber()) 
                    && ObjectUtils.isEmpty(sql004ResModel.getOldButen())) {
                IfaBbApplyCorrectConfirmSql006RequestModel sql006ReqModel = new IfaBbApplyCorrectConfirmSql006RequestModel();
                BeanUtils.copyProperties(sql006ReqModel, sql004ResModel);
                DataList<IfaBbApplyCorrectConfirmSql006ResponseModel> sql006Res = dao.selectIfaBbApplyCorrectConfirmSql006(sql006ReqModel);
                sql006ResModel = sql006Res.get(0);
            } else {
                sql006ResModel = new IfaBbApplyCorrectConfirmSql006ResponseModel();
                sql006ResModel.setCount("0");
            }
            IfaBbApplyCorrectConfirmSql012RequestModel ifaBbApplyCorrectConfirmSql012RequestModel = new IfaBbApplyCorrectConfirmSql012RequestModel();
            BeanUtils.copyProperties(ifaBbApplyCorrectConfirmSql012RequestModel, dtoReq);
            DataList<IfaBbApplyCorrectConfirmSql012ResponseModel> sql012Res = dao.selectIfaBbApplyCorrectConfirmSql012(ifaBbApplyCorrectConfirmSql012RequestModel);
            IfaBbApplyCorrectConfirmSql012ResponseModel sql012ResModel = sql012Res.get(0);
            discretionAlloCount = getDiscretionAlloCount(sql004ResModel, sql005ResModel, sql006ResModel);
            DataList<IfaBbApplyCorrectConfirmA001ResponseDto> checkResult = scretionAllocateAbleCheck(sql001ResModel, sql004ResModel, sql005ResModel, sql006ResModel, sql012ResModel,
                    dtoReq.getMarketOrder(), dtoReq.getPrice(), dtoReq.getDiscountRate());
            if (checkResult != null) {
                return checkResult;
            }
        }
        // 数量の申込単位外チェックを行う。
        String unit = sql001ResModel.getUnit();
        String sellBuyUnitType = sql001ResModel.getSellBuyUnitType();
        if (Long.parseLong(dtoReq.getQuantity()) % Long.parseLong(unit) != 0 || Long.parseLong(dtoReq.getQuantity()) == 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_UNITCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_UNITCHECK.key,
                            new String[] {CHECK_UNIT_ERROR_MESSAGE_QUANTITY, unit, sellBuyUnitType})
                );
            return dtoRes;
        }
        
        // 裁量申込の場合、裁量希望数量の申込単位外チェック
        if (!ObjectUtils.isEmpty(dtoReq.getDiscretionQuantity())) {
            if (Long.parseLong(dtoReq.getDiscretionQuantity()) % Long.parseLong(unit) != 0 || Long.parseLong(dtoReq.getDiscretionQuantity()) == 0) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                        ErrorLevel.FATAL, MessageId.ERRORS_UNITCHECK.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_UNITCHECK.key,
                                new String[] {CHECK_UNIT_ERROR_MESSAGE_DISCRETION_QUANTITY, unit, sellBuyUnitType})
                    );
                return dtoRes;
            }
            // 裁量申込場合、裁量希望数量の上限値チェックを行う。
            IfaBbApplyCorrectConfirmSql013RequestModel ifaBbApplyCorrectConfirmSql013RequestModel = new IfaBbApplyCorrectConfirmSql013RequestModel();
            BeanUtils.copyProperties(ifaBbApplyCorrectConfirmSql013RequestModel, dtoReq);
            DataList<IfaBbApplyCorrectConfirmSql013ResponseModel> sql013Res = dao.selectIfaBbApplyCorrectConfirmSql013(ifaBbApplyCorrectConfirmSql013RequestModel);
            IfaBbApplyCorrectConfirmSql013ResponseModel sql013ResponseModel = sql013Res.get(0);
            if (Long.parseLong(dtoReq.getDiscretionQuantity()) > Long.parseLong(sql013ResponseModel.getUpperLimit())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                        ErrorLevel.FATAL, MessageId.ERRORS_IPOTRADERANGE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_IPOTRADERANGE.key,
                                new String[] {DISCRETION_QUANTITY_UPPER_LIMIT_0, DISCRETION_QUANTITY_UPPER_LIMIT_1,
                                        DISCRETION_QUANTITY_UPPER_LIMIT_2, sql013ResponseModel.getUpperLimit()})
                    );
                return dtoRes;
            }
            // 裁量申込場合、数量、裁量希望数量の整合性チェックを行う。
            if (Long.parseLong(dtoReq.getQuantity()) < Long.parseLong(dtoReq.getDiscretionQuantity())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                        ErrorLevel.FATAL, MessageId.ERRORS_TRADERANGE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_TRADERANGE.key,
                                new String[] {ERRORS_TRADERANGE_0, ERRORS_TRADERANGE_1})
                    );
                return dtoRes;
            }
            // 裁量申込場合、裁量配分割当回数5回以上のワーニングチェックを行う。
            if (IPO_PO_KBN_IPO.equals(sql001ResModel.getIpoPoKbn()) && Long.parseLong(discretionAlloCount) >= 5) {
                if (DISCRETION_ALLOCATE_TIMES_OVER_FIVE_ON.equals(dtoReq.getDiscretionAllocateTimesOverFive())) {
                    // 次の処理へ。
                } else if (ObjectUtils.isEmpty(dtoReq.getDiscretionAllocateTimesOverFive()) || DISCRETION_ALLOCATE_TIMES_OVER_FIVE_OFF.equals(dtoReq.getDiscretionAllocateTimesOverFive())) {
                    dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                            ErrorLevel.FATAL, MessageId.ERRORS_CMN_INFORMATION_OCCURS_2.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_INFORMATION_OCCURS_2.key)
                        );
                    return dtoRes;
                }
            }
            // 裁量申込場合、個人の場合は金融資産チェックを行う。
            String corporationType = sql003Res.get(0).getCorporationType();
            String financialAssetsType = sql003Res.get(0).getFinancialAssetsType();
            if (CORPORATION_TYPE_KOJIN.equals(corporationType) 
                    && (!FINANCIAL_ASSETS_TYPE_06.equals(financialAssetsType) 
                            && !FINANCIAL_ASSETS_TYPE_07.equals(financialAssetsType) 
                            && !FINANCIAL_ASSETS_TYPE_08.equals(financialAssetsType) 
                            && !FINANCIAL_ASSETS_TYPE_99.equals(financialAssetsType))
            ) { 
                if (FINANCIAL_ASSETLESS_THAN_THIRTY_MILLION_YEN_DISCRETION_APPLY_ON.equals(dtoReq.getFinancialAssetLessThanThirtyMillionYenDiscretionApply())) {
                 // 次の処理へ。
                } else if (ObjectUtils.isEmpty(dtoReq.getFinancialAssetLessThanThirtyMillionYenDiscretionApply()) 
                        || FINANCIAL_ASSETLESS_THAN_THIRTY_MILLION_YEN_DISCRETION_APPLY_OFF.equals(dtoReq.getFinancialAssetLessThanThirtyMillionYenDiscretionApply())) {
                    dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                            ErrorLevel.FATAL, MessageId.ERRORS_CMN_INFORMATION_OCCURS_2.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_INFORMATION_OCCURS_2.key)
                        );
                    return dtoRes;
                }
                
            }
        }
        // セクション情報の登録済みチェックを行う。
        IfaBbApplyCorrectConfirmSql014RequestModel ifaBbApplyCorrectConfirmSql014RequestModel = new IfaBbApplyCorrectConfirmSql014RequestModel();
        ifaBbApplyCorrectConfirmSql014RequestModel.setButenCode(butenCodeReq);
        ifaBbApplyCorrectConfirmSql014RequestModel.setAccountNumber(accountNumberReq);
        DataList<IfaBbApplyCorrectConfirmSql014ResponseModel> sql014Res = dao.selectIfaBbApplyCorrectConfirmSql014(ifaBbApplyCorrectConfirmSql014RequestModel);
        if (sql014Res.size() == 0 || sql014Res.get(0) == null) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_SECTIONID.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SECTIONID.key, new String[] {ERRORS_SECTIONID_PARAM})
                );
            return dtoRes;
        }
        
        
        
        //⑤ BB申込訂正情報をDBに登録する。
        IfaBbApplyCorrectConfirmSql015RequestModel ifaBbApplyCorrectConfirmSql015RequestModel = new IfaBbApplyCorrectConfirmSql015RequestModel();
        BeanUtils.copyProperties(ifaBbApplyCorrectConfirmSql015RequestModel, dtoReq);
        ifaBbApplyCorrectConfirmSql015RequestModel.setSectionId(sql014Res.get(0).getSectionId());
        ifaBbApplyCorrectConfirmSql015RequestModel.setBranchName(sql014Res.get(0).getBranchName());
        ifaBbApplyCorrectConfirmSql015RequestModel.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        ifaBbApplyCorrectConfirmSql015RequestModel.setSysDate(ifaDateUtil.getCurrentDate());
        int result = dao.updateIfaBbApplyCorrectConfirmSql015a(ifaBbApplyCorrectConfirmSql015RequestModel);
        if (result != 0) {
            if (!ObjectUtils.isEmpty(dtoReq.getDiscretionQuantityBeforeCorrect())
                    && ObjectUtils.isEmpty(dtoReq.getDiscretionQuantity())) {
                dao.deleteIfaBbApplyCorrectConfirmSql015b(ifaBbApplyCorrectConfirmSql015RequestModel);
            } else if (!ObjectUtils.isEmpty(dtoReq.getDiscretionQuantityBeforeCorrect())
                    && !ObjectUtils.isEmpty(dtoReq.getDiscretionQuantity())) {
                dao.updateIfaBbApplyCorrectConfirmSql015c(ifaBbApplyCorrectConfirmSql015RequestModel);
            } else if (ObjectUtils.isEmpty(dtoReq.getDiscretionQuantityBeforeCorrect())
                    && !ObjectUtils.isEmpty(dtoReq.getDiscretionQuantity())) {
                dao.insertIfaBbApplyCorrectConfirmSql015d(ifaBbApplyCorrectConfirmSql015RequestModel);
            }
        } else {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_EXCLUSIVE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_EXCLUSIVE.key, new String[] {ERRORS_INSERTDATAEXIST_MESSAGE_PARAM})
                );
        }
        
        BeanUtils.copyProperties(ifaBbApplyCorrectConfirmA001ResponseDto, dtoReq);;
        return dtoRes;
    }

    /**
     * 本年の年間裁量配分割当回数情報を取得する
     *
     * @param sql004ResModel sql004のレスポンス
     * @param sql005ResModel sql005のレスポンス
     * @param sql006ResModel sql006のレスポンス
     * @return 本年の年間裁量配分割当回数情報
     */
    private String getDiscretionAlloCount(IfaBbApplyCorrectConfirmSql004ResponseModel sql004ResModel,
            IfaBbApplyCorrectConfirmSql005ResponseModel sql005ResModel,
            IfaBbApplyCorrectConfirmSql006ResponseModel sql006ResModel) {
        
        long discretionAlloCount = Long.parseLong(sql004ResModel.getDiscretionAlloCount());
        long beforeTransferDiscretionAlloCount =  Long.parseLong(sql004ResModel.getBeforeTransferDiscretionAlloCount());
        
        long sql005Count = Long.parseLong(sql005ResModel.getCount()); 
        long sql006Count = Long.parseLong(sql006ResModel.getCount());  
        return String.valueOf(discretionAlloCount + beforeTransferDiscretionAlloCount + sql005Count + sql006Count);
    }
    

    /**
     * 本年の年間裁量配分可能回数を取得する
     *
     * @param sql004ResModel sql004のレスポンス
     * @return 本年の年間裁量配分可能回数
     */
    private String getDiscretionAlloUpperLimit(IfaBbApplyCorrectConfirmSql004ResponseModel sql004ResModel) {
        return sql004ResModel.getDiscretionAlloUpperLimit();
    }
    
    
    /**
     * チェック用「裁量配分可能回数」を計算する。
     *
     * @param sql004ResModel sql004のレスポンス
     * @param sql005ResModel sql005のレスポンス
     * @param sql006ResModel sql006のレスポンス
     * @return 裁量配分可能回数
     */
    private String getDiscretionAllocateAbleTimes(IfaBbApplyCorrectConfirmSql004ResponseModel sql004ResModel,
            IfaBbApplyCorrectConfirmSql005ResponseModel sql005ResModel,
            IfaBbApplyCorrectConfirmSql006ResponseModel sql006ResModel) {
        return String.valueOf(Long.parseLong(getDiscretionAlloUpperLimit(sql004ResModel)) 
                - Long.parseLong(getDiscretionAlloCount(sql004ResModel, sql005ResModel, sql006ResModel)));
    }
    
    
    /**
     * 裁量申込の場合、「別紙　裁量配分可能チェック」に従って、裁量配分可能チェックを行う。
     *
     * @param sql001ResModel sql001のレスポンス
     * @param sql004ResModel sql004のレスポンス
     * @param sql005ResModel sql005のレスポンス
     * @param sql006ResModel sql006のレスポンス
     * @param sql012ResModel sql0012のレスポンス
     * @param marketOrder 成行
     * @param price 価格
     * @param discountRate ディスカウント率
     * @return 裁量配分可能チェックの結果
     */
    private DataList<IfaBbApplyCorrectConfirmA001ResponseDto> scretionAllocateAbleCheck(IfaBbApplyCorrectConfirmSql001ResponseModel sql001ResModel,
            IfaBbApplyCorrectConfirmSql004ResponseModel sql004ResModel,
            IfaBbApplyCorrectConfirmSql005ResponseModel sql005ResModel,
            IfaBbApplyCorrectConfirmSql006ResponseModel sql006ResModel,
            IfaBbApplyCorrectConfirmSql012ResponseModel sql012ResModel,
            String marketOrder,
            String price,
            String discountRate) {
        // IPO/PO区分
        String ipoPoKbn = sql001ResModel.getIpoPoKbn();
        // 発行価格区分
        String issuePriceType = sql001ResModel.getIssuePriceType();
        // 価格/ディスカウント率　価格表示（終了）
        String finishPriceDisplay = sql001ResModel.getFinishPriceDisplay();
        // 価格/ディスカウント率　ディスカウント率（開始）
        String startDiscountRate = sql001ResModel.getStartDiscountRate();     
        // 裁量配分可能回
        String discretionAllocateAbleTimes = getDiscretionAllocateAbleTimes(sql004ResModel, sql005ResModel, sql006ResModel);
        // 裁量配分あるかフラグ
        boolean discretionAllocate;
        if (Long.parseLong(sql012ResModel.getCount()) >= 1) {
            discretionAllocate = false;
        } else {
            discretionAllocate = true;
        }
        // ■裁量配分可否
        if (IPO_PO_KBN_IPO.equals(ipoPoKbn)) {
            if (MARKET_ORDER_ON.equals(marketOrder)) {
                // 以外の×の場合は、エラーメッセージを表示し、処理を終了する。
                if (Long.parseLong(discretionAllocateAbleTimes) <= 0 && discretionAllocate) {
                    return IfaCommonUtil.createDataList(Collections.emptyList(),
                            ErrorLevel.FATAL, MessageId.ERRORS_IPOOVERLIMIT.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_IPOOVERLIMIT.key,
                                    ERROR_MESSAGE_OTHER)
                        );
                }
            } else if (MARKET_ORDER_OFF.equals(marketOrder)) {
                if (ISSUE_PRICE_TYPE_PRICE.equals(issuePriceType)) {
                    // No.5の×の場合は、エラーメッセージを表示し、処理を終了する。
                    if (Long.parseLong(price) < Long.parseLong(finishPriceDisplay)) {
                        return IfaCommonUtil.createDataList(Collections.emptyList(),
                                ErrorLevel.FATAL, MessageId.ERRORS_SELECTED.key,
                                IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key,
                                        new String[] { ERROR_MESSAGE_NO_5 })
                            );
                    } else if (Long.parseLong(price) == Long.parseLong(finishPriceDisplay)) {
                        // 以外の×の場合は、エラーメッセージを表示し、処理を終了する。
                        if (Long.parseLong(discretionAllocateAbleTimes) <= 0 && discretionAllocate) {
                            return IfaCommonUtil.createDataList(Collections.emptyList(),
                                    ErrorLevel.FATAL, MessageId.ERRORS_IPOOVERLIMIT.key,
                                    IfaCommonUtil.getMessage(MessageId.ERRORS_IPOOVERLIMIT.key,
                                            ERROR_MESSAGE_OTHER)
                                );
                        }
                    }
                } else if (ISSUE_PRICE_TYPE_DISCOUNT.equals(issuePriceType)) {
                    // 以外の×の場合は、エラーメッセージを表示し、処理を終了する。
                    if (Long.parseLong(discretionAllocateAbleTimes) <= 0 && discretionAllocate) {
                        return IfaCommonUtil.createDataList(Collections.emptyList(),
                                ErrorLevel.FATAL, MessageId.ERRORS_IPOOVERLIMIT.key,
                                IfaCommonUtil.getMessage(MessageId.ERRORS_IPOOVERLIMIT.key,
                                        ERROR_MESSAGE_OTHER)
                            );
                    }
                }
            }
        } else if (IPO_PO_KBN_PO.equals(ipoPoKbn)) {
            if (MARKET_ORDER_OFF.equals(marketOrder) && ISSUE_PRICE_TYPE_DISCOUNT.equals(issuePriceType)) {
                // No.16の×の場合は、エラーメッセージを表示し、処理を終了する。
                if (Float.parseFloat(discountRate) > Float.parseFloat(startDiscountRate)) {
                    return IfaCommonUtil.createDataList(Collections.emptyList(),
                            ErrorLevel.FATAL, MessageId.ERRORS_SELECTED.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key,
                                    new String[] { ERROR_MESSAGE_NO_16 })
                          );
                }
            }
        }
        return null;
    }

}
