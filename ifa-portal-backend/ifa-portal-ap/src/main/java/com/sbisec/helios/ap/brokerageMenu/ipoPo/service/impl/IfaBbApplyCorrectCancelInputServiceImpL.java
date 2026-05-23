package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;

import java.util.Collections;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct031;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct031Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaBbApplyCorrectCancelInputDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql013ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql014RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql014ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IfaBbApplyCorrectCancelInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;

/**
 * 画面ID：SUB0204_02-02_1
 * 画面名：BB申込訂正・取消入力
 *
 * @author BASE 李
 2024/04/15 新規作成
 */
@Component(value = "cmpIfaBbApplyCorrectCancelInputService")
public class IfaBbApplyCorrectCancelInputServiceImpL implements IfaBbApplyCorrectCancelInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBbApplyCorrectCancelInputServiceImpL.class);
    
    @Autowired
    private IfaBbApplyCorrectCancelInputDao dao;

    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct006 fct006;
    
    @Autowired
    private Fct031 fct031;
    
    @Autowired
    private ApiWrapper apiwrapper;
    
    @Autowired
    private CodeListService codeListService;

    /** 権限なし */
    private static final String NO_AUTHORIZED = "0";
    
    /** 買付余力がない */
    private static final String BUYING_POWER_0 = null;
    /** FCT006.判定結果 */
    private static final String FCT006_JUDGEMENT_RESULT_0 = "0";

    private static final String FCT006_JUDGEMENT_RESULT_1 = "1";

    private static final String FCT006_JUDGEMENT_RESULT_2 = "2";
    
    /** 緊急入力停止ON */
    private static final String URGENT_STOP_ON = "1";
    
    /** 取得した件数が0件 */
    private static final String RESULT_COUNT_0 = "0";
    
    /** 電子交付のみフラグがON */
    private static final String EDELIVONLYFLAG_ON = "1";
    
    /** IPO */
    private static final String IPO_PO_KBN_IPO = "1";
    
    /** PO */
    private static final String IPO_PO_KBN_PO = "2";
    
    /** 法人区分 個人 */
    private static final String CORPORATION_TYPE_KOJIN = "0";
    
    /** 金融資産区分 */
    private static final String FINANCIAL_ASSETS_TYPE_06 = "06";
    private static final String FINANCIAL_ASSETS_TYPE_07 = "07";
    private static final String FINANCIAL_ASSETS_TYPE_08 = "08";
    private static final String FINANCIAL_ASSETS_TYPE_99 = "99";
    
    /** 発行価格区分 価格表示 */
    private static final String ISSUE_PRICE_TYPE_PRICE = "1";
    /** 発行価格区分 ディスカウント */
    private static final String ISSUE_PRICE_TYPE_DISCOUNT = "2";
    
    /** FCT003パラメータ 証券金銭種別  国内*/
    private static final String PRODUCT_CD_DOMESTIC = "01";
    /** FCT003パラメータ 取引種別 BB申込*/
    private static final String TRADE_CD_DOMESTIC = "9";
    
    /** 緊急入力停止エラーメッセージパラメータ　"BB申込" */
    private static final String URGENT_STOP_ERROR_MESSAGE = "BB訂正";
    /** 成行あり */
    private static final String MARKET_ORDER_ON = "1";
    /** 成行なし */
    private static final String MARKET_ORDER_OFF = "0";
    /** 裁量配分可否No.5の×の場合エラーメッセージ　パラメータ */
    private static final String ERROR_MESSAGE_NO_5 = "裁量希望の場合、価格の最大値";
    /** 裁量配分可否No.16の×の場合エラーメッセージ　パラメータ */
    private static final String ERROR_MESSAGE_NO_16 = "裁量希望の場合、ディスカウント率の最小値";
    /** 裁量配分可否他の×の場合エラーメッセージ */
    private static final String[] ERROR_MESSAGE_OTHER = {"年間裁量配分割当回数", "上限回数"};
    
    /** 申込単位外チェックエラーメッセージ　パラメータ */
    private static final String CHECK_UNIT_ERROR_MESSAGE_QUANTITY = "数量";
    private static final String CHECK_UNIT_ERROR_MESSAGE_DISCRETION_QUANTITY = "裁量希望数量";
    
    /** 裁量申込場合、裁量希望数量の上限値チェックエラーメッセージパラメータ　パラメータ */
    private static final String DISCRETION_QUANTITY_UPPER_LIMIT_0 = "裁量希望数量";
    private static final String DISCRETION_QUANTITY_UPPER_LIMIT_1 = "上限数量";
    private static final String DISCRETION_QUANTITY_UPPER_LIMIT_2 = "上限数量";
    
    /** 裁量申込場合、数量、裁量希望数量の整合性チェックエラーメッセージパラメータ　パラメータ。 */
    private static final String ERRORS_TRADERANGE_0 = "裁量希望数量";
    private static final String ERRORS_TRADERANGE_1 = "数量";
    
    /** 裁量申込場合、裁量配分割当回数5回以上のワーニングメッセージパラメータ　パラメータ。 */
    private static final String ERRORS_ABOVELIMIT_0 = "裁量配分割当回数";
    private static final String ERRORS_ABOVELIMIT_1 = "5回";
    
    private static final String CUSTOMER_NAME_KANA_SEPARATE = "　";
    /** 注意情報レベル */
    private static final String NOTICE_INFO_LEVEL_NOTICE_ERROR = "3";
    private static final String NOTICE_INFO_LEVEL_NOTICE = "2";
    private static final String NOTICE_INFO_LEVEL = "1";
    
    /** FCT006パラメータ */
    private static final String BR_DOMESTIC_FGN_IND = "0";
    private static final String BR_BRAND_IND = "1 ";
    private static final String COMPLA_CHECK_KIND = "1";
    
    /** errors.sectionid エラーメッセージパラメータ*/
    private static final String ERRORS_SECTIONID_ERROR_MESSAGE = "価格またはディスカウント率";
    
    private enum MessageId {
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        ERRORS_BRANDCODENOTEXIST("errors.brandCodeNotExist"),
        ERRORS_APPLYTIME("errors.applyTime"),
        ERRORS_URGENTSTOPCHECK("errors.urgentStopCheck"),
        ERRORS_ONLYEDELIVAGREEMENTCHECK("errors.onlyEdelivAgreementCheck"),
        ERRORS_IPOOVERLIMIT("errors.ipoOverLimit"),
        ERRORS_SELECTED("errors.selected"),
        ERRORS_UNITCHECK("errors.unitCheck"),
        ERRORS_IPOTRADERANGE("errors.ipoTradeRange"),
        ERRORS_TRADERANGE("errors.tradeRange"),
        ERRORS_ABOVELIMIT("errors.aboveLimit"),
        ERRORS_FINANCIALASSETSCHECK("errors.financialAssetsCheck"),
        ERRORS_SECTIONID("errors.sectionId");
        
        private String key;
        
        private MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBbApplyCorrectCancelInputA001RequestDto
     * Dto レスポンス：IfaBbApplyCorrectCancelInputA001ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputA001ResponseDto> initializeA001(IfaBbApplyCorrectCancelInputA001RequestDto dtoReq)
            throws Exception {
        DataList<IfaBbApplyCorrectCancelInputA001ResponseDto> dtoRes = new DataList<IfaBbApplyCorrectCancelInputA001ResponseDto>();
        IfaBbApplyCorrectCancelInputA001ResponseDto ifaBbApplyCorrectCancelInputA001ResponseDto = new IfaBbApplyCorrectCancelInputA001ResponseDto();
        dtoRes.getDataList().add(ifaBbApplyCorrectCancelInputA001ResponseDto);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyCorrectCancelInputServiceImplL.initializeA001");
        }
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        // ① 利用者の口座に対する権限チェックを行う。
        OutputFct001Dto outputFct001Dto = fct001Check(butenCode, accountNumber);
        // 権限なし0
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                            ErrorLevel.FATAL, MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, 
                            IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, new String[] { butenCode, accountNumber })
                        );
            return dtoRes;
        }
        // 取引停止口座ではない
        if (TradeSuspendFlag.SUSPEND.getId().equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key)
                );
            return dtoRes;
        }
        //② 取引コース媒介可否チェックを行う。
        OutputFct003Dto outputFct003Dto = fct003Check(butenCode, accountNumber);
        boolean mediateFlag = outputFct003Dto.getMediateProprietyList().stream()
                .anyMatch(mediatePropriety -> mediatePropriety.getMediatePropriety().equals(MediateAbleTradeFlag.NASHI.getId())); 
        if (mediateFlag) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, new String[]{codeListService.getValue("MSG_DISPLAY_TARGET_TRADE", "7")})
                );
            return dtoRes;
        }
        // ③ 申込期間内銘柄情報を取得し、申込期間外チェックを行う。
        IfaBbApplyCorrectCancelInputSql001RequestModel ifaBbApplyCorrectCancelInputSql001RequestModel = new IfaBbApplyCorrectCancelInputSql001RequestModel();
        ifaBbApplyCorrectCancelInputSql001RequestModel.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaBbApplyCorrectCancelInputSql001ResponseModel> sql001Res = dao.selectIfaBbApplyCorrectCancelInputSql001(ifaBbApplyCorrectCancelInputSql001RequestModel);    
        // 取得した件数が0件の場合、エラーメッセージを表示し、処理を終了する。
        if (sql001Res.size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_APPLYTIME.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_APPLYTIME.key,
                            new String[] {URGENT_STOP_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        // 緊急入力停止がONの場合、エラーメッセージを表示し、処理を終了する。
        IfaBbApplyCorrectCancelInputSql001ResponseModel ifaBbApplyCorrectCancelInputSql001ResponseModel = sql001Res.get(0);
        if (URGENT_STOP_ON.equals(ifaBbApplyCorrectCancelInputSql001ResponseModel.getUrgentStop())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_URGENTSTOPCHECK.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_URGENTSTOPCHECK.key,
                            new String[] {URGENT_STOP_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        // 発行価格区分が価格表示
        if (ISSUE_PRICE_TYPE_PRICE.equals(ifaBbApplyCorrectCancelInputSql001ResponseModel.getIssuePriceType()) 
                && (ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getStartPriceDisplay()) 
                        || ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getFinishPriceDisplay()) 
                        || ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getPriceDisplayTick()))) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_SECTIONID.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SECTIONID.key,
                            new String[] {ERRORS_SECTIONID_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        // 発行価格区分がディスカウント率
        if (ISSUE_PRICE_TYPE_DISCOUNT.equals(ifaBbApplyCorrectCancelInputSql001ResponseModel.getIssuePriceType()) 
                && (ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getStartDiscountRate()) 
                        || ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getFinishDiscountRate()) 
                        || ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getDiscountRateTick()))) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_SECTIONID.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SECTIONID.key,
                            new String[] {ERRORS_SECTIONID_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputA001ResponseDto, ifaBbApplyCorrectCancelInputSql001ResponseModel);
        // ④ 投資家属性のプルダウンリストを取得する。
        IfaBbApplyCorrectCancelInputSql002RequestModel selSql002Req = new IfaBbApplyCorrectCancelInputSql002RequestModel();
        BeanUtils.copyProperties(selSql002Req, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql002ResponseModel> selSql002Res = dao.selectIfaBbApplyCorrectCancelInputSql002(selSql002Req);
        List<IfaBbApplyCorrectCancelInputSql002ResponseModel> ifaBbApplyCorrectCancelInputSql002ResponseModelList = selSql002Res.getDataList();
        ifaBbApplyCorrectCancelInputA001ResponseDto.setInvestorAttributePullDownList(ifaBbApplyCorrectCancelInputSql002ResponseModelList);
        // ⑤ 顧客情報を取得する。
        IfaBbApplyCorrectCancelInputSql003RequestModel ifaBbApplyCorrectCancelInputSql003RequestModel = new IfaBbApplyCorrectCancelInputSql003RequestModel();
        ifaBbApplyCorrectCancelInputSql003RequestModel.setAccountNumber(dtoReq.getAccountNumber());
        ifaBbApplyCorrectCancelInputSql003RequestModel.setButenCode(dtoReq.getButenCode());
        DataList<IfaBbApplyCorrectCancelInputSql003ResponseModel> sql003Res = 
                dao.selectIfaBbApplyCorrectCancelInputSql003(ifaBbApplyCorrectCancelInputSql003RequestModel);
        if (sql003Res.size() != 0) {
            IfaBbApplyCorrectCancelInputSql003ResponseModel sql003ResModel = sql003Res.get(0);
            BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputA001ResponseDto, sql003ResModel);
            ifaBbApplyCorrectCancelInputA001ResponseDto.setEdelivAgreementDate(sql003ResModel.getConsentDate());
            ifaBbApplyCorrectCancelInputA001ResponseDto.setCustomerName(sql003ResModel.getCustomerNameKanji());
            ifaBbApplyCorrectCancelInputA001ResponseDto.setCustomerNameKana(sql003ResModel.getCustomerLastNameKana() + CUSTOMER_NAME_KANA_SEPARATE
                    + sql003ResModel.getCustomerFirstNameKana());
            ifaBbApplyCorrectCancelInputA001ResponseDto.setCustomerCode(sql003ResModel.getCustomerCode());
        }
        // ⑥ 年間裁量配分割当回数情報を取得する。
        IfaBbApplyCorrectCancelInputSql004RequestModel sql004ReqModel = new IfaBbApplyCorrectCancelInputSql004RequestModel();
        BeanUtils.copyProperties(sql004ReqModel, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql004ResponseModel> sql004Res = dao.selectIfaBbApplyCorrectCancelInputSql004(sql004ReqModel);
        IfaBbApplyCorrectCancelInputSql004ResponseModel sql004ResModel = sql004Res.get(0);
        
        // ⑦ 裁量配分割当回数(未抽選)情報を取得する。
        IfaBbApplyCorrectCancelInputSql005RequestModel sql005ReqModel = new IfaBbApplyCorrectCancelInputSql005RequestModel();
        BeanUtils.copyProperties(sql005ReqModel, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql005ResponseModel> sql005Res = dao.selectIfaBbApplyCorrectCancelInputSql005(sql005ReqModel);
        IfaBbApplyCorrectCancelInputSql005ResponseModel sql005ResModel = sql005Res.get(0);    
        // ⑧ 移管前の裁量配分割当回数(未抽選)を取得する。
        IfaBbApplyCorrectCancelInputSql006ResponseModel sql006ResModel;
        if (!ObjectUtils.isEmpty(sql004ResModel.getOldAccountNumber()) 
                && !ObjectUtils.isEmpty(sql004ResModel.getOldButen())) {
            IfaBbApplyCorrectCancelInputSql006RequestModel sql006ReqModel = new IfaBbApplyCorrectCancelInputSql006RequestModel();
            BeanUtils.copyProperties(sql006ReqModel, sql004ResModel);
            DataList<IfaBbApplyCorrectCancelInputSql006ResponseModel> sql006Res = dao.selectIfaBbApplyCorrectCancelInputSql006(sql006ReqModel);
            sql006ResModel = sql006Res.get(0);
        } else {
            sql006ResModel = new IfaBbApplyCorrectCancelInputSql006ResponseModel();
            sql006ResModel.setCount("0");
        } 
        // 「本年の年間裁量配分割当回数」と「本年の年間裁量配分可能回数」、チェック用「裁量配分可能回数」を取得する。
        String discretionAlloCount = getDiscretionAlloCount(sql004ResModel, sql005ResModel, sql006ResModel);
        
        ifaBbApplyCorrectCancelInputA001ResponseDto.setDiscretionAllocateAbleTimes(sql004ResModel.getDiscretionAlloUpperLimit());
        ifaBbApplyCorrectCancelInputA001ResponseDto.setDiscretionAlloCount(discretionAlloCount);
        
        // ⑪ 預り資産額を取得する。
        IfaBbApplyCorrectCancelInputSql007RequestModel sql007ReqModel = new IfaBbApplyCorrectCancelInputSql007RequestModel();
        BeanUtils.copyProperties(sql007ReqModel, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql007ResponseModel> sql007Res = dao.selectIfaBbApplyCorrectCancelInputSql007(sql007ReqModel);
        ifaBbApplyCorrectCancelInputA001ResponseDto.setDepositAssetAmount(sql007Res.get(0).getDepositAssetAmount());
        
        // ⑫ 買付余力を取得する。
        QueryAccountBalanceIn queryAccountBalanceIn = new QueryAccountBalanceIn();
        QueryAccountBalanceInData queryAccountBalanceInData = new QueryAccountBalanceInData();
        queryAccountBalanceInData.setButenCd(butenCode);
        queryAccountBalanceInData.setKozaNo(String.format("%7s", accountNumber).replace(' ', '0'));
        queryAccountBalanceIn.setIndata(queryAccountBalanceInData);
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        try {
            QueryAccountBalanceOutData queryAccountBalanceOutData = apiwrapper.queryAccountBalance(queryAccountBalanceIn);
            if (apiErrorUtil.isError(queryAccountBalanceOutData.getShubetu(),
                    queryAccountBalanceOutData.getCode(), queryAccountBalanceOutData.getMessage())) {
                ifaBbApplyCorrectCancelInputA001ResponseDto.setBuyingPower(BUYING_POWER_0);
            } else {
                ifaBbApplyCorrectCancelInputA001ResponseDto.setBuyingPower(queryAccountBalanceOutData.getT0().getBuyingPowerTotal());
            }
        } catch (Exception e) {
            ifaBbApplyCorrectCancelInputA001ResponseDto.setBuyingPower(BUYING_POWER_0);
        } 
        
        // ⑬ 目論見書情報を取得する。
        
        IfaBbApplyCorrectCancelInputSql008RequestModel ifaBbApplyCorrectCancelInputSql008RequestModel = new IfaBbApplyCorrectCancelInputSql008RequestModel();
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputSql008RequestModel, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql008ResponseModel> sql008Res = dao.selectIfaBbApplyCorrectCancelInputSql008(ifaBbApplyCorrectCancelInputSql008RequestModel);
        if (sql008Res.size() != 0) {
            IfaBbApplyCorrectCancelInputSql008ResponseModel ifaBbApplyCorrectCancelInputSql008ResponseModel = sql008Res.get(0);
            ifaBbApplyCorrectCancelInputA001ResponseDto.setReadTime(ifaBbApplyCorrectCancelInputSql008ResponseModel.getReadTime());
        }
        // ⑭ BB申込内容を取得する。
        IfaBbApplyCorrectCancelInputSql014RequestModel ifaBbApplyCorrectCancelInputSql014RequestModel = new IfaBbApplyCorrectCancelInputSql014RequestModel();
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputSql014RequestModel, dtoReq);
        IfaBbApplyCorrectCancelInputSql014ResponseModel sql014ResponseModel = dao.selectIfaBbApplyCorrectCancelInputSql014(ifaBbApplyCorrectCancelInputSql014RequestModel).get(0);
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputA001ResponseDto, sql014ResponseModel);
        ifaBbApplyCorrectCancelInputA001ResponseDto.setButenCode(butenCode);
        ifaBbApplyCorrectCancelInputA001ResponseDto.setAccountNumber(accountNumber);
        if (apiErrorUtil.isFatal()) {
            return dtoRes;
        } else {
            return apiErrorUtil.createDataList(dtoRes.getDataList(), null);
        }
    }
    

    /**
     * アクションID：A002
     * アクション名：訂正
     * Dto リクエスト：IfaBbApplyCorrectCancelInputA002RequestDto
     * Dto レスポンス：IfaBbApplyCorrectCancelInputA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputA002ResponseDto> correctA002(IfaBbApplyCorrectCancelInputA002RequestDto dtoReq)
            throws Exception {
        DataList<IfaBbApplyCorrectCancelInputA002ResponseDto> dtoRes = new DataList<IfaBbApplyCorrectCancelInputA002ResponseDto>();
        IfaBbApplyCorrectCancelInputA002ResponseDto ifaBbApplyCorrectCancelInputA002ResponseDto = new IfaBbApplyCorrectCancelInputA002ResponseDto();
        dtoRes.getDataList().add(ifaBbApplyCorrectCancelInputA002ResponseDto);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyCorrectCancelInputServiceImplL.correctA002");
        }
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputA002ResponseDto, dtoReq);
        // ① 利用者の口座に対する権限チェックを行う。
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        // ① 利用者の口座に対する権限チェックを行う。
        OutputFct001Dto outputFct001Dto = fct001Check(butenCode, accountNumber);
        // 権限なし0
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                            ErrorLevel.FATAL, MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, 
                            IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, new String[] { butenCode, accountNumber })
                        );
            return dtoRes;
        }
        // 取引停止口座ではない
        if (TradeSuspendFlag.SUSPEND.getId().equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key)
                );
            return dtoRes;
        }
        //② 取引コース媒介可否チェックを行う。
        OutputFct003Dto outputFct003Dto = fct003Check(butenCode, accountNumber);
        boolean mediateFlag = outputFct003Dto.getMediateProprietyList().stream()
                .anyMatch(mediatePropriety -> mediatePropriety.getMediatePropriety().equals(MediateAbleTradeFlag.NASHI.getId())); 
        if (mediateFlag) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, new String[]{codeListService.getValue("MSG_DISPLAY_TARGET_TRADE", "7")})
                );
            return dtoRes;
        }
        // ③ コンプラランクチェックを行う。
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
            ifaBbApplyCorrectCancelInputA002ResponseDto.setComplianceRankCheckMsg(IfaCommonUtil.getMessage(outputFct006Dto.getMessageId()));
            ifaBbApplyCorrectCancelInputA002ResponseDto.setComplianceRankCheckChkBoxLabel(outputFct006Dto.getChkBoxLabel());
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
        // 入力した銘柄コード存在件数を取得し、入力銘柄存在チェックを行う。
        IfaBbApplyCorrectCancelInputSql009RequestModel ifaBbApplyCorrectCancelInputSql009RequestModel = new IfaBbApplyCorrectCancelInputSql009RequestModel();
        ifaBbApplyCorrectCancelInputSql009RequestModel.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaBbApplyCorrectCancelInputSql009ResponseModel> sql009Res = 
                dao.selectIfaBbApplyCorrectCancelInputSql009(ifaBbApplyCorrectCancelInputSql009RequestModel);
        if (RESULT_COUNT_0.equals(sql009Res.get(0).getCount())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_BRANDCODENOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BRANDCODENOTEXIST.key,
                            new String[] {"", dtoReq.getBrandCode()})
                );
            return dtoRes;
        }
        // 申込期間内銘柄情報を取得し、申込期間外チェックを行う。
        IfaBbApplyCorrectCancelInputSql001RequestModel ifaBbApplyCorrectCancelInputSql001RequestModel = new IfaBbApplyCorrectCancelInputSql001RequestModel();
        ifaBbApplyCorrectCancelInputSql001RequestModel.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaBbApplyCorrectCancelInputSql001ResponseModel> sql001Res = 
                dao.selectIfaBbApplyCorrectCancelInputSql001(ifaBbApplyCorrectCancelInputSql001RequestModel);
        if (sql001Res.size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_APPLYTIME.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_APPLYTIME.key,
                            new String[] {URGENT_STOP_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        IfaBbApplyCorrectCancelInputSql001ResponseModel sql001ResModel = sql001Res.get(0);
        if (URGENT_STOP_ON.equals(sql001ResModel.getUrgentStop())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_URGENTSTOPCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_URGENTSTOPCHECK.key,
                            new String[] {URGENT_STOP_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        ifaBbApplyCorrectCancelInputA002ResponseDto.setBrandName(sql001ResModel.getBrand());
        ifaBbApplyCorrectCancelInputA002ResponseDto.setSellBuyUnitType(sql001ResModel.getSellBuyUnitType());
        ifaBbApplyCorrectCancelInputA002ResponseDto.setIssuePriceType(sql001ResModel.getIssuePriceType());
        // 顧客情報を取得し、口座存在チェックを行う。
        IfaBbApplyCorrectCancelInputSql003RequestModel ifaBbApplyCorrectCancelInputSql003RequestModel = new IfaBbApplyCorrectCancelInputSql003RequestModel();
        ifaBbApplyCorrectCancelInputSql003RequestModel.setAccountNumber(dtoReq.getAccountNumber());
        ifaBbApplyCorrectCancelInputSql003RequestModel.setButenCode(dtoReq.getButenCode());
        DataList<IfaBbApplyCorrectCancelInputSql003ResponseModel> sql003Res = 
                dao.selectIfaBbApplyCorrectCancelInputSql003(ifaBbApplyCorrectCancelInputSql003RequestModel);
        if (sql003Res.size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] {dtoReq.getButenCode(), dtoReq.getAccountNumber()})
                );
            return dtoRes;
        }
        IfaBbApplyCorrectCancelInputSql003ResponseModel sql003ResModel = sql003Res.get(0);
        ifaBbApplyCorrectCancelInputA002ResponseDto.setCustomerNameKana(sql003ResModel.getCustomerLastNameKana() + CUSTOMER_NAME_KANA_SEPARATE
                + sql003ResModel.getCustomerFirstNameKana());
        ifaBbApplyCorrectCancelInputA002ResponseDto.setCustomerNameKanji(sql003ResModel.getCustomerNameKanji());
        // ifaBbApplyCorrectCancelInputA002ResponseDto.setCorporationType(sql003ResModel.getCorporationType());
        ifaBbApplyCorrectCancelInputA002ResponseDto.setCustomerCode(sql003ResModel.getCustomerCode());
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
            IfaBbApplyCorrectCancelInputSql004RequestModel sql004ReqModel = new IfaBbApplyCorrectCancelInputSql004RequestModel();
            BeanUtils.copyProperties(sql004ReqModel, dtoReq);
            DataList<IfaBbApplyCorrectCancelInputSql004ResponseModel> sql004Res = dao.selectIfaBbApplyCorrectCancelInputSql004(sql004ReqModel);
            IfaBbApplyCorrectCancelInputSql004ResponseModel sql004ResModel = sql004Res.get(0);
            // 裁量配分割当回数(未抽選)情報を取得する。
            IfaBbApplyCorrectCancelInputSql005RequestModel sql005ReqModel = new IfaBbApplyCorrectCancelInputSql005RequestModel();
            BeanUtils.copyProperties(sql005ReqModel, dtoReq);
            DataList<IfaBbApplyCorrectCancelInputSql005ResponseModel> sql005Res = dao.selectIfaBbApplyCorrectCancelInputSql005(sql005ReqModel);
            IfaBbApplyCorrectCancelInputSql005ResponseModel sql005ResModel = sql005Res.get(0);    
            // 移管前の裁量配分割当回数(未抽選)を取得する。
            IfaBbApplyCorrectCancelInputSql006ResponseModel sql006ResModel;
            if (!ObjectUtils.isEmpty(sql004ResModel.getOldAccountNumber()) 
                    && !ObjectUtils.isEmpty(sql004ResModel.getOldButen())) {
                IfaBbApplyCorrectCancelInputSql006RequestModel sql006ReqModel = new IfaBbApplyCorrectCancelInputSql006RequestModel();
                BeanUtils.copyProperties(sql006ReqModel, sql004ResModel);
                DataList<IfaBbApplyCorrectCancelInputSql006ResponseModel> sql006Res = dao.selectIfaBbApplyCorrectCancelInputSql006(sql006ReqModel);
                sql006ResModel = sql006Res.get(0);
            } else {
                sql006ResModel = new IfaBbApplyCorrectCancelInputSql006ResponseModel();
                sql006ResModel.setCount("0");
            }
            IfaBbApplyCorrectCancelInputSql012RequestModel ifaBbApplyCorrectCancelInputSql012RequestModel = new IfaBbApplyCorrectCancelInputSql012RequestModel();
            BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputSql012RequestModel, dtoReq);
            DataList<IfaBbApplyCorrectCancelInputSql012ResponseModel> sql012Res = dao.selectIfaBbApplyCorrectCancelInputSql012(ifaBbApplyCorrectCancelInputSql012RequestModel);
            IfaBbApplyCorrectCancelInputSql012ResponseModel sql012ResModel = sql012Res.get(0);
            
            discretionAlloCount = getDiscretionAlloCount(sql004ResModel, sql005ResModel, sql006ResModel);
            DataList<IfaBbApplyCorrectCancelInputA002ResponseDto> checkResult = scretionAllocateAbleCheck(sql001ResModel, sql004ResModel, sql005ResModel, sql006ResModel, sql012ResModel,
                    dtoReq.getMarketOrder(), dtoReq.getPrice(), dtoReq.getDiscountRate());
            if (checkResult != null) {
                return checkResult;
            }
        }
        // 数量の申込単位外チェックを行う。
        String unit = sql001ResModel.getUnit();
        String sellBuyUnitType = sql001ResModel.getSellBuyUnitType();
        if (Long.parseLong(dtoReq.getQuantity()) % Long.parseLong(unit) != 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_UNITCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_UNITCHECK.key,
                            new String[] {CHECK_UNIT_ERROR_MESSAGE_QUANTITY, unit, sellBuyUnitType})
                );
            return dtoRes;
        }
        // 裁量申込の場合、裁量希望数量の申込単位外チェック
        if (!ObjectUtils.isEmpty(dtoReq.getDiscretionQuantity())
                && Long.parseLong(dtoReq.getDiscretionQuantity()) % Long.parseLong(unit) != 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_UNITCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_UNITCHECK.key,
                            new String[] {CHECK_UNIT_ERROR_MESSAGE_DISCRETION_QUANTITY, unit, sellBuyUnitType})
                );
            return dtoRes;
        }
        
        if (!ObjectUtils.isEmpty(dtoReq.getDiscretionQuantity())) {
            // 裁量申込場合、裁量希望数量の上限値チェックを行う。
            IfaBbApplyCorrectCancelInputSql013RequestModel ifaBbApplyCorrectCancelInputSql013RequestModel = new IfaBbApplyCorrectCancelInputSql013RequestModel();
            BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputSql013RequestModel, dtoReq);
            DataList<IfaBbApplyCorrectCancelInputSql013ResponseModel> sql013Res = dao.selectIfaBbApplyCorrectCancelInputSql013(ifaBbApplyCorrectCancelInputSql013RequestModel);
            IfaBbApplyCorrectCancelInputSql013ResponseModel sql013ResponseModel = sql013Res.get(0);
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
                ifaBbApplyCorrectCancelInputA002ResponseDto
                .setDiscretionAllocateTimesOverFiveWarningMsg(IfaCommonUtil.getMessage(MessageId.ERRORS_ABOVELIMIT.key, new String[] {ERRORS_ABOVELIMIT_0, ERRORS_ABOVELIMIT_1})); 
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
                ifaBbApplyCorrectCancelInputA002ResponseDto
                .setFinancialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg(IfaCommonUtil.getMessage(MessageId.ERRORS_FINANCIALASSETSCHECK.key)); 
            }
        }
        
        // 顧客の注意情報レベルを取得する。
        InputFct031Dto inputFct031Dto = new InputFct031Dto();
        inputFct031Dto.setAccountNumber(accountNumber);
        inputFct031Dto.setButenCode(butenCode);
        OutputFct031Dto outputFct031Dto = fct031.getData(inputFct031Dto);
        if (outputFct031Dto.getNoteInfoErrorCount() == null) {
            outputFct031Dto.setNoteInfoErrorCount(0);
        }
        if (outputFct031Dto.getUnreadImportantNoticeTransactionLimitErrorNumber() == null) {
            outputFct031Dto.setUnreadImportantNoticeTransactionLimitErrorNumber(0);
        }
        if (outputFct031Dto.getNoteInfoCount() == null) {
            outputFct031Dto.setNoteInfoCount(0);
        }
        if (outputFct031Dto.getUnreadImportantNoticeTransactionLimitNumber() == null) {
            outputFct031Dto.setUnreadImportantNoticeTransactionLimitNumber(0);
        }
        if (outputFct031Dto.getNoteInfoErrorCount() > 0 
                || outputFct031Dto.getUnreadImportantNoticeTransactionLimitErrorNumber() > 0) {
            ifaBbApplyCorrectCancelInputA002ResponseDto.setNoticeInfoLevel(NOTICE_INFO_LEVEL_NOTICE_ERROR);
        } else if (outputFct031Dto.getNoteInfoCount() > 0 
                || outputFct031Dto.getUnreadImportantNoticeTransactionLimitNumber() > 0) {
            ifaBbApplyCorrectCancelInputA002ResponseDto.setNoticeInfoLevel(NOTICE_INFO_LEVEL_NOTICE);
        } else {
            ifaBbApplyCorrectCancelInputA002ResponseDto.setNoticeInfoLevel(NOTICE_INFO_LEVEL);
        }
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：取消
     * Dto リクエスト：IfaBbApplyCorrectCancelInputA003RequestDto
     * Dto レスポンス：IfaBbApplyCorrectCancelInputA003ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputA003ResponseDto> cancelA003(IfaBbApplyCorrectCancelInputA003RequestDto dtoReq)
            throws Exception {
        DataList<IfaBbApplyCorrectCancelInputA003ResponseDto> dtoRes = new DataList<IfaBbApplyCorrectCancelInputA003ResponseDto>();
        IfaBbApplyCorrectCancelInputA003ResponseDto ifaBbApplyCorrectCancelInputA003ResponseDto = new IfaBbApplyCorrectCancelInputA003ResponseDto();
        dtoRes.getDataList().add(ifaBbApplyCorrectCancelInputA003ResponseDto);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyCorrectCancelInputServiceImplL.cancelA003");
        }
        // ① 利用者の口座に対する権限チェックを行う。
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        // ① 利用者の口座に対する権限チェックを行う。
        OutputFct001Dto outputFct001Dto = fct001Check(butenCode, accountNumber);
        // 権限なし0
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                            ErrorLevel.FATAL, MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, 
                            IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, new String[] { butenCode, accountNumber })
                        );
            return dtoRes;
        }
        // 取引停止口座ではない
        if (TradeSuspendFlag.SUSPEND.getId().equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key)
                );
            return dtoRes;
        }
        //② 取引コース媒介可否チェックを行う。
        OutputFct003Dto outputFct003Dto = fct003Check(butenCode, accountNumber);
        boolean mediateFlag = outputFct003Dto.getMediateProprietyList().stream()
                .anyMatch(mediatePropriety -> mediatePropriety.getMediatePropriety().equals(MediateAbleTradeFlag.NASHI.getId())); 
        if (mediateFlag) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, new String[]{codeListService.getValue("MSG_DISPLAY_TARGET_TRADE", "7")})
                );
            return dtoRes;
        }
        // ③ 顧客の注意情報レベルを取得する。
        InputFct031Dto inputFct031Dto = new InputFct031Dto();
        inputFct031Dto.setAccountNumber(accountNumber);
        inputFct031Dto.setButenCode(butenCode);
        OutputFct031Dto outputFct031Dto = fct031.getData(inputFct031Dto);
        if (outputFct031Dto.getNoteInfoErrorCount() == null) {
            outputFct031Dto.setNoteInfoErrorCount(0);
        }
        if (outputFct031Dto.getUnreadImportantNoticeTransactionLimitErrorNumber() == null) {
            outputFct031Dto.setUnreadImportantNoticeTransactionLimitErrorNumber(0);
        }
        if (outputFct031Dto.getNoteInfoCount() == null) {
            outputFct031Dto.setNoteInfoCount(0);
        }
        if (outputFct031Dto.getUnreadImportantNoticeTransactionLimitNumber() == null) {
            outputFct031Dto.setUnreadImportantNoticeTransactionLimitNumber(0);
        }
        if (outputFct031Dto.getNoteInfoErrorCount() > 0 
                || outputFct031Dto.getUnreadImportantNoticeTransactionLimitErrorNumber() > 0) {
            ifaBbApplyCorrectCancelInputA003ResponseDto.setNoticeInfoLevel(NOTICE_INFO_LEVEL_NOTICE_ERROR);
        } else if (outputFct031Dto.getNoteInfoCount() > 0 
                || outputFct031Dto.getUnreadImportantNoticeTransactionLimitNumber() > 0) {
            ifaBbApplyCorrectCancelInputA003ResponseDto.setNoticeInfoLevel(NOTICE_INFO_LEVEL_NOTICE);
        } else {
            ifaBbApplyCorrectCancelInputA003ResponseDto.setNoticeInfoLevel(NOTICE_INFO_LEVEL);
        }
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputA003ResponseDto, dtoReq);
        ifaBbApplyCorrectCancelInputA003ResponseDto.setQuantity(dtoReq.getQuantityBeforeCorrect());
        ifaBbApplyCorrectCancelInputA003ResponseDto.setMarketOrder(dtoReq.getMarketOrderBeforeCorrect());
        ifaBbApplyCorrectCancelInputA003ResponseDto.setPrice(dtoReq.getPriceBeforeCorrect());
        ifaBbApplyCorrectCancelInputA003ResponseDto.setDiscountRate(dtoReq.getDiscountRateBeforeCorrect());
        ifaBbApplyCorrectCancelInputA003ResponseDto.setInvestorAttributeValue(dtoReq.getInvestorAttributeValueBeforeCorrect());
        ifaBbApplyCorrectCancelInputA003ResponseDto.setInvestorAttributeName(dtoReq.getInvestorAttributeNameBeforeCorrect());
        ifaBbApplyCorrectCancelInputA003ResponseDto.setBbRemark(dtoReq.getBbRemarkBeforeCorrect());
        ifaBbApplyCorrectCancelInputA003ResponseDto.setDiscretionQuantity(dtoReq.getDiscretionQuantityBeforeCorrect());
        ifaBbApplyCorrectCancelInputA003ResponseDto.setSelectReason(dtoReq.getSelectReasonBeforeCorrect());
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：戻り初期化
     * Dto リクエスト：IfaBbApplyCorrectCancelInputA005RequestDto
     * Dto レスポンス：IfaBbApplyCorrectCancelInputA005ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputA005ResponseDto> backInitializeA005(IfaBbApplyCorrectCancelInputA005RequestDto dtoReq)
            throws Exception {
        DataList<IfaBbApplyCorrectCancelInputA005ResponseDto> dtoRes = new DataList<IfaBbApplyCorrectCancelInputA005ResponseDto>();
        IfaBbApplyCorrectCancelInputA005ResponseDto ifaBbApplyCorrectCancelInputA005ResponseDto = new IfaBbApplyCorrectCancelInputA005ResponseDto();
        dtoRes.getDataList().add(ifaBbApplyCorrectCancelInputA005ResponseDto);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyCorrectCancelInputServiceImplL.backInitializeA005");
        }
        
        // ① A001と同じ処理を行う。
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        // ① 利用者の口座に対する権限チェックを行う。
        OutputFct001Dto outputFct001Dto = fct001Check(butenCode, accountNumber);
        // 権限なし0
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                            ErrorLevel.FATAL, MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, 
                            IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, new String[] { butenCode, accountNumber })
                        );
            return dtoRes;
        }
        // 取引停止口座ではない
        if (TradeSuspendFlag.SUSPEND.getId().equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key)
                );
            return dtoRes;
        }
        //② 取引コース媒介可否チェックを行う。
        OutputFct003Dto outputFct003Dto = fct003Check(butenCode, accountNumber);
        boolean mediateFlag = outputFct003Dto.getMediateProprietyList().stream()
                .anyMatch(mediatePropriety -> mediatePropriety.getMediatePropriety().equals(MediateAbleTradeFlag.NASHI.getId())); 
        if (mediateFlag) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, new String[]{codeListService.getValue("MSG_DISPLAY_TARGET_TRADE", "7")})
                );
            return dtoRes;
        }
        // ③ 申込期間内銘柄情報を取得し、申込期間外チェックを行う。
        IfaBbApplyCorrectCancelInputSql001RequestModel ifaBbApplyCorrectCancelInputSql001RequestModel = new IfaBbApplyCorrectCancelInputSql001RequestModel();
        ifaBbApplyCorrectCancelInputSql001RequestModel.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaBbApplyCorrectCancelInputSql001ResponseModel> sql001Res = dao.selectIfaBbApplyCorrectCancelInputSql001(ifaBbApplyCorrectCancelInputSql001RequestModel);    
        // 取得した件数が0件の場合、エラーメッセージを表示し、処理を終了する。
        if (sql001Res.size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_APPLYTIME.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_APPLYTIME.key,
                            new String[] {URGENT_STOP_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        // 緊急入力停止がONの場合、エラーメッセージを表示し、処理を終了する。
        IfaBbApplyCorrectCancelInputSql001ResponseModel ifaBbApplyCorrectCancelInputSql001ResponseModel = sql001Res.get(0);
        if (URGENT_STOP_ON.equals(ifaBbApplyCorrectCancelInputSql001ResponseModel.getUrgentStop())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_URGENTSTOPCHECK.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_URGENTSTOPCHECK.key,
                            new String[] {URGENT_STOP_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        // 発行価格区分が価格表示
        if (ISSUE_PRICE_TYPE_PRICE.equals(ifaBbApplyCorrectCancelInputSql001ResponseModel.getIssuePriceType()) 
                && (ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getStartPriceDisplay()) 
                        || ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getFinishPriceDisplay()) 
                        || ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getPriceDisplayTick()))) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_SECTIONID.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SECTIONID.key,
                            new String[] {ERRORS_SECTIONID_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        // 発行価格区分がディスカウント率
        if (ISSUE_PRICE_TYPE_DISCOUNT.equals(ifaBbApplyCorrectCancelInputSql001ResponseModel.getIssuePriceType()) 
                && (ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getStartDiscountRate()) 
                        || ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getFinishDiscountRate()) 
                        || ObjectUtils.isEmpty(ifaBbApplyCorrectCancelInputSql001ResponseModel.getDiscountRateTick()))) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_SECTIONID.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SECTIONID.key,
                            new String[] {ERRORS_SECTIONID_ERROR_MESSAGE})
                );
            return dtoRes;
        }
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputA005ResponseDto, ifaBbApplyCorrectCancelInputSql001ResponseModel);
        // ④ 投資家属性のプルダウンリストを取得する。
        IfaBbApplyCorrectCancelInputSql002RequestModel selSql002Req = new IfaBbApplyCorrectCancelInputSql002RequestModel();
        BeanUtils.copyProperties(selSql002Req, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql002ResponseModel> selSql002Res = dao.selectIfaBbApplyCorrectCancelInputSql002(selSql002Req);
        List<IfaBbApplyCorrectCancelInputSql002ResponseModel> ifaBbApplyCorrectCancelInputSql002ResponseModelList = selSql002Res.getDataList();
        ifaBbApplyCorrectCancelInputA005ResponseDto.setInvestorAttributePullDownList(ifaBbApplyCorrectCancelInputSql002ResponseModelList);
        // ⑤ 顧客情報を取得する。
        IfaBbApplyCorrectCancelInputSql003RequestModel ifaBbApplyCorrectCancelInputSql003RequestModel = new IfaBbApplyCorrectCancelInputSql003RequestModel();
        ifaBbApplyCorrectCancelInputSql003RequestModel.setAccountNumber(dtoReq.getAccountNumber());
        ifaBbApplyCorrectCancelInputSql003RequestModel.setButenCode(dtoReq.getButenCode());
        DataList<IfaBbApplyCorrectCancelInputSql003ResponseModel> sql003Res = 
                dao.selectIfaBbApplyCorrectCancelInputSql003(ifaBbApplyCorrectCancelInputSql003RequestModel);
        if (sql003Res.size() != 0) {
            IfaBbApplyCorrectCancelInputSql003ResponseModel sql003ResModel = sql003Res.get(0);
            BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputA005ResponseDto, sql003ResModel);
            ifaBbApplyCorrectCancelInputA005ResponseDto.setEdelivAgreementDate(sql003ResModel.getConsentDate());
            ifaBbApplyCorrectCancelInputA005ResponseDto.setCustomerName(sql003ResModel.getCustomerNameKanji());
            ifaBbApplyCorrectCancelInputA005ResponseDto.setCustomerCode(sql003ResModel.getCustomerCode());
        }
        // ⑥ 年間裁量配分割当回数情報を取得する。
        IfaBbApplyCorrectCancelInputSql004RequestModel sql004ReqModel = new IfaBbApplyCorrectCancelInputSql004RequestModel();
        BeanUtils.copyProperties(sql004ReqModel, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql004ResponseModel> sql004Res = dao.selectIfaBbApplyCorrectCancelInputSql004(sql004ReqModel);
        IfaBbApplyCorrectCancelInputSql004ResponseModel sql004ResModel = sql004Res.get(0);
        
        // ⑦ 裁量配分割当回数(未抽選)情報を取得する。
        IfaBbApplyCorrectCancelInputSql005RequestModel sql005ReqModel = new IfaBbApplyCorrectCancelInputSql005RequestModel();
        BeanUtils.copyProperties(sql005ReqModel, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql005ResponseModel> sql005Res = dao.selectIfaBbApplyCorrectCancelInputSql005(sql005ReqModel);
        IfaBbApplyCorrectCancelInputSql005ResponseModel sql005ResModel = sql005Res.get(0);    
        // ⑧ 移管前の裁量配分割当回数(未抽選)を取得する。
        IfaBbApplyCorrectCancelInputSql006ResponseModel sql006ResModel;
        if (!ObjectUtils.isEmpty(sql004ResModel.getOldAccountNumber()) 
                && !ObjectUtils.isEmpty(sql004ResModel.getOldButen())) {
            IfaBbApplyCorrectCancelInputSql006RequestModel sql006ReqModel = new IfaBbApplyCorrectCancelInputSql006RequestModel();
            BeanUtils.copyProperties(sql006ReqModel, sql004ResModel);
            DataList<IfaBbApplyCorrectCancelInputSql006ResponseModel> sql006Res = dao.selectIfaBbApplyCorrectCancelInputSql006(sql006ReqModel);
            sql006ResModel = sql006Res.get(0);
        } else {
            sql006ResModel = new IfaBbApplyCorrectCancelInputSql006ResponseModel();
            sql006ResModel.setCount("0");
        } 
        // 「本年の年間裁量配分割当回数」と「本年の年間裁量配分可能回数」、チェック用「裁量配分可能回数」を取得する。
        String discretionAlloCount = getDiscretionAlloCount(sql004ResModel, sql005ResModel, sql006ResModel);
        
        ifaBbApplyCorrectCancelInputA005ResponseDto.setDiscretionAllocateAbleTimes(sql004ResModel.getDiscretionAlloUpperLimit());
        ifaBbApplyCorrectCancelInputA005ResponseDto.setDiscretionAlloCount(discretionAlloCount);
        
        // ⑪ 預り資産額を取得する。
        IfaBbApplyCorrectCancelInputSql007RequestModel sql007ReqModel = new IfaBbApplyCorrectCancelInputSql007RequestModel();
        BeanUtils.copyProperties(sql007ReqModel, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql007ResponseModel> sql007Res = dao.selectIfaBbApplyCorrectCancelInputSql007(sql007ReqModel);
        ifaBbApplyCorrectCancelInputA005ResponseDto.setDepositAssetAmount(sql007Res.get(0).getDepositAssetAmount());
        
        // ⑫ 買付余力を取得する。
        QueryAccountBalanceIn queryAccountBalanceIn = new QueryAccountBalanceIn();
        QueryAccountBalanceInData queryAccountBalanceInData = new QueryAccountBalanceInData();
        queryAccountBalanceInData.setButenCd(butenCode);
        queryAccountBalanceInData.setKozaNo(String.format("%7s", accountNumber).replace(' ', '0'));
        queryAccountBalanceIn.setIndata(queryAccountBalanceInData);
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        try {
            QueryAccountBalanceOutData queryAccountBalanceOutData = apiwrapper.queryAccountBalance(queryAccountBalanceIn);
            if (apiErrorUtil.isError(queryAccountBalanceOutData.getShubetu(),
                    queryAccountBalanceOutData.getCode(), queryAccountBalanceOutData.getMessage())) {
                ifaBbApplyCorrectCancelInputA005ResponseDto.setBuyingPower(BUYING_POWER_0);
            } else {
                ifaBbApplyCorrectCancelInputA005ResponseDto.setBuyingPower(queryAccountBalanceOutData.getT0().getBuyingPowerTotal());
            }
        } catch (Exception e) {
            ifaBbApplyCorrectCancelInputA005ResponseDto.setBuyingPower(BUYING_POWER_0);
        } 
        
        // ⑬ 目論見書情報を取得する。
        
        IfaBbApplyCorrectCancelInputSql008RequestModel ifaBbApplyCorrectCancelInputSql008RequestModel = new IfaBbApplyCorrectCancelInputSql008RequestModel();
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputSql008RequestModel, dtoReq);
        DataList<IfaBbApplyCorrectCancelInputSql008ResponseModel> sql008Res = dao.selectIfaBbApplyCorrectCancelInputSql008(ifaBbApplyCorrectCancelInputSql008RequestModel);
        if (sql008Res.size() != 0) {
            IfaBbApplyCorrectCancelInputSql008ResponseModel ifaBbApplyCorrectCancelInputSql008ResponseModel = sql008Res.get(0);
            ifaBbApplyCorrectCancelInputA005ResponseDto.setReadTime(ifaBbApplyCorrectCancelInputSql008ResponseModel.getReadTime());
        }
        // ⑭ BB申込内容を取得する。
        IfaBbApplyCorrectCancelInputSql014RequestModel ifaBbApplyCorrectCancelInputSql014RequestModel = new IfaBbApplyCorrectCancelInputSql014RequestModel();
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputSql014RequestModel, dtoReq);
        IfaBbApplyCorrectCancelInputSql014ResponseModel sql014ResponseModel = dao.selectIfaBbApplyCorrectCancelInputSql014(ifaBbApplyCorrectCancelInputSql014RequestModel).get(0);
        ifaBbApplyCorrectCancelInputA005ResponseDto.setQuantityBeforeCorrect(sql014ResponseModel.getQuantity());
        ifaBbApplyCorrectCancelInputA005ResponseDto.setMarketOrderBeforeCorrect(sql014ResponseModel.getMarketOrder());
        ifaBbApplyCorrectCancelInputA005ResponseDto.setPriceDiscountRateBeforeCorrect(sql014ResponseModel.getPriceDiscountRate());
        ifaBbApplyCorrectCancelInputA005ResponseDto.setInvestorAttributeValueBeforeCorrect(sql014ResponseModel.getInvestorAttributeValue());
        ifaBbApplyCorrectCancelInputA005ResponseDto.setInvestorAttributeNameBeforeCorrect(sql014ResponseModel.getInvestorAttributeName());
        ifaBbApplyCorrectCancelInputA005ResponseDto.setBbRemarkBeforeCorrect(sql014ResponseModel.getBbRemark());
        ifaBbApplyCorrectCancelInputA005ResponseDto.setDiscretionQuantityBeforeCorrect(sql014ResponseModel.getDiscretionQuantity());
        ifaBbApplyCorrectCancelInputA005ResponseDto.setSelectReasonBeforeCorrect(sql014ResponseModel.getSelectReason());
        ifaBbApplyCorrectCancelInputA005ResponseDto.setBbCreateDate(sql014ResponseModel.getBbCreateDate());
        BeanUtils.copyProperties(ifaBbApplyCorrectCancelInputA005ResponseDto, dtoReq);
        if (apiErrorUtil.isFatal()) {
            return dtoRes;
        } else {
            return apiErrorUtil.createDataList(dtoRes.getDataList(), null);
        }
    }


    /**
     * FCT001Check
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return OutputFct001Dto
     */
    private OutputFct001Dto fct001Check(String butenCode, String accountNumber) {
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(butenCode);
        inputFct001Dto.setAccountNumber(accountNumber);
        return fct001.doCheck(inputFct001Dto);
    }
    /**
     * FCT003Check
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return OutputFct003Dto
     */
    
    private OutputFct003Dto fct003Check(String butenCode, String accountNumber) {
        
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setAccountNumber(accountNumber);
        inputFct003Dto.setButenCode(butenCode);
        inputFct003Dto.setProductCd(PRODUCT_CD_DOMESTIC);
        inputFct003Dto.setTradeCd(TRADE_CD_DOMESTIC);
        return fct003.doCheck(inputFct003Dto);
    }
    
    
    /**
     * 本年の年間裁量配分割当回数情報を取得する
     *
     * @param sql004ResModel sql004のレスポンス
     * @param sql005ResModel sql005のレスポンス
     * @param sql006ResModel sql006のレスポンス
     * @return 本年の年間裁量配分割当回数情報
     */
    private String getDiscretionAlloCount(IfaBbApplyCorrectCancelInputSql004ResponseModel sql004ResModel,
            IfaBbApplyCorrectCancelInputSql005ResponseModel sql005ResModel,
            IfaBbApplyCorrectCancelInputSql006ResponseModel sql006ResModel) {
        
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
    private String getDiscretionAlloUpperLimit(IfaBbApplyCorrectCancelInputSql004ResponseModel sql004ResModel) {
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
    private String getDiscretionAllocateAbleTimes(IfaBbApplyCorrectCancelInputSql004ResponseModel sql004ResModel,
            IfaBbApplyCorrectCancelInputSql005ResponseModel sql005ResModel,
            IfaBbApplyCorrectCancelInputSql006ResponseModel sql006ResModel) {
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
    private DataList<IfaBbApplyCorrectCancelInputA002ResponseDto> scretionAllocateAbleCheck(IfaBbApplyCorrectCancelInputSql001ResponseModel sql001ResModel,
            IfaBbApplyCorrectCancelInputSql004ResponseModel sql004ResModel,
            IfaBbApplyCorrectCancelInputSql005ResponseModel sql005ResModel,
            IfaBbApplyCorrectCancelInputSql006ResponseModel sql006ResModel,
            IfaBbApplyCorrectCancelInputSql012ResponseModel sql012ResModel,
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
