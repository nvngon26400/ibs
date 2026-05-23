package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.text.Transliterator;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaCustomerSelectDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectX003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectX003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaCustomerSelectService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_00-01
 * 画面名：顧客選択
 *
 * @author SCSK
 */
@Component(value = "ifaCustomerSelectService")
public class IfaCustomerSelectServiceImpL implements IfaCustomerSelectService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerSelectServiceImpL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private IfaCustomerSelectDao dao;
    
    /** 権限チェックエラー  */
    private static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 権限チェックエラー値 */
    private static final String AUTH_ERROR_VALUE = "0";
    
    /** 参照可能な仲介業者コード／営業員コードが存在しない */
    private static final String ERRORS_NO_EXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です */
    private static final String ERRORS_NO_FOUND = "errors.dataList.notfound";
    
    /** 検索結果が{0}件を超過しています */
    private static final String WARNINGS_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 5,000 */
    private static final String WARNINGS_OVER_MAX_5000 = "5,000";
    
    /** 失敗しました */
    private static final String ERRORS_PROCESSING_FAILD = "errors.processingFailed";
    
    /** お気に入り登録状況の更新 */
    private static final String UPDATE_FAVO_REG_STATUS = "お気に入り登録状況の更新";
    
    /** ""値 */
    private static final String VALUE_EMPTY = "";
    
    /** 顧客名 条件リスト */
    private enum CustomerNameConditionList {

        // と等しい
        EQUAL_TO("1"),
        // で始まる
        STARTS_WITH("2"),
        // を含む
        INCLUDING("3");

        private final String value;

        CustomerNameConditionList(final String value) {

            this.value = value;
        }

        private String getValue() {

            return value;
        }
    }
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCustomerSelectA001RequestDto
     * Dto レスポンス：IfaCustomerSelectA001ResponseDto
     * model リクエスト：IfaCustomerSelectA001RequestModel
     * model レスポンス：IfaCustomerSelectA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCustomerSelectA001ResponseDto> initializeA001(IfaCustomerSelectA001RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerSelectServiceImplL.initializeA001");
        }
        List<IfaCustomerSelectA001ResponseDto> resDtoList = new ArrayList<IfaCustomerSelectA001ResponseDto>();
        
        // ①  ユーザ共通情報.権限コードが1（SBI証券本店）以外の場合、共通関数FCT030を呼び出し、利用者顧客参照範囲の仲介業者コード、営業員コードを取得する。
        //ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        OutputFct030Dto fct030Dto = null;
        if (StringUtils.equals(userAccount.getPrivId(), "1") == false) {
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            // FCT030.仲介業者営業員リストの件数が1件以上の場合：次の処理へ。
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            if (fct030Dto.getBrokerChargeList().size() == 0) {
                DataList<IfaCustomerSelectA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList,
                        ErrorLevel.FATAL, IfaCustomerSelectServiceImpL.ERRORS_NO_EXIST,
                        IfaCommonUtil.getMessage(IfaCustomerSelectServiceImpL.ERRORS_NO_EXIST));
                return dtoRes;
            }
        }
        
        // 
        // ②  SQL001の呼び出し
        // 利用者の顧客参照範囲の中で画面項目初期値の状態の検索条件にあった顧客の一覧を取得する。
        List<IfaCustomerSelectSql001RequestModel.BrokerCharge> brokerChargeList = new ArrayList<IfaCustomerSelectSql001RequestModel.BrokerCharge>();
        if (fct030Dto != null) {
            IfaCustomerSelectSql001RequestModel.BrokerCharge brokerCharge = null;
            for (OutputFct030Dto.BrokerCharge fct030brokerCharge : fct030Dto.getBrokerChargeList()) {
                brokerCharge = new IfaCustomerSelectSql001RequestModel.BrokerCharge();
                brokerCharge.setBrokerCode(fct030brokerCharge.getBrokerCode());
                brokerCharge.setEmpCode(fct030brokerCharge.getEmpCode());
                brokerChargeList.add(brokerCharge);
            }
        }
        
        IfaCustomerSelectSql001RequestModel selSql001Req = new IfaCustomerSelectSql001RequestModel();
        selSql001Req.setBrokerChargeList(brokerChargeList);
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setUserId(userAccount.getUserId());
        selSql001Req.setFavorite("true");
        
        DataList<IfaCustomerSelectSql001ResponseModel> selSql001Res = dao.selectIfaCustomerSelectSql001(selSql001Req);
        
        ErrorLevel rtnLevel = ErrorLevel.SUCCESS;
        String rtnMessageId = "";
        String rtnMessage = "";
        if (selSql001Res.getDataList().size() == 0) {
            //   取得件数0件：該当データなしメッセージを返す。
            DataList<IfaCustomerSelectA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList,
                    ErrorLevel.INFO, IfaCustomerSelectServiceImpL.ERRORS_NO_FOUND,
                    IfaCommonUtil.getMessage(IfaCustomerSelectServiceImpL.ERRORS_NO_FOUND));
            return dtoRes;
            
        } else if (Integer.parseInt(selSql001Res.getDataList().get(0).getSearchCount()) > 5000) {
            //   取得件数5000件超：5000件メッセージを設定する。
            rtnLevel = ErrorLevel.WARNING;
            rtnMessageId = IfaCustomerSelectServiceImpL.WARNINGS_OVER_MAX_ROWNUM;
            rtnMessage = IfaCommonUtil.getMessage(IfaCustomerSelectServiceImpL.WARNINGS_OVER_MAX_ROWNUM,
                    new String[] { IfaCustomerSelectServiceImpL.WARNINGS_OVER_MAX_5000,
                            selSql001Res.getDataList().get(0).getSearchCount() });
        }
        
        //   取得件数1件～5000件：次の処理へ。
        List<IfaCustomerSelectA001ResponseDto.Customer> coustomerInfoList = new ArrayList<IfaCustomerSelectA001ResponseDto.Customer>();
        IfaCustomerSelectA001ResponseDto.Customer customerInfo = null;
        for (IfaCustomerSelectSql001ResponseModel sql001Model : selSql001Res.getDataList()) {
            customerInfo = new IfaCustomerSelectA001ResponseDto.Customer();
            customerInfo.setButenCode(sql001Model.getButenCode());
            customerInfo.setAccountNumber(sql001Model.getAccountNumber());
            
            // SQL001.C.注意情報エラー件数＞0、または、SQL001.D.部店コードがNULLでない の条件に該当する場合
            //   レスポンス.取引制限有無に「1:取引制限あり」を設定
            // 上記以外の場合
            //   レスポンス.取引制限有無に「0:取引制限なし」を設定する。
            if ((sql001Model.getNoticeInfoErrorCount() != null
                            && Integer.parseInt(sql001Model.getNoticeInfoErrorCount()) > 0)
                || sql001Model.getTradeRestrictButenCode() != null) {
                customerInfo.setTradeRestrictionSelect("1");
            } else {
                customerInfo.setTradeRestrictionSelect("0");
            }
            customerInfo.setCustomerNameKanji(sql001Model.getCustomerNameKanji());
            customerInfo.setCustomerNameKana(sql001Model.getCustomerNameKana());
            customerInfo.setOpenAcctDate((sql001Model.getOpenAcctDate() == null) ? sql001Model.getOpenAcctDate()
                    : DateFormatUtil.convertDateString(sql001Model.getOpenAcctDate()));
            customerInfo.setFavoRegStatus(sql001Model.getFavoRegStatus());
            customerInfo.setCustomerCode(sql001Model.getCustomerCode());
            
            coustomerInfoList.add(customerInfo);
        }
        
        IfaCustomerSelectA001ResponseDto resDto = new IfaCustomerSelectA001ResponseDto();
        resDto.setCustomerList(coustomerInfoList);
        
        resDtoList.add(resDto);
        DataList<IfaCustomerSelectA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList, rtnLevel,
                rtnMessageId, rtnMessage);
        return dtoRes;
    }
    
    /**
     * アクションID：X003
     * アクション名：検索
     * Dto リクエスト：IfaCustomerSelectX003RequestDto
     * Dto レスポンス：IfaCustomerSelectX003ResponseDto
     * model リクエスト：IfaCustomerSelectX003RequestModel
     * model レスポンス：IfaCustomerSelectX003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 検索処理で例外が発生した場合
     */
    public DataList<IfaCustomerSelectX003ResponseDto> searchX003(IfaCustomerSelectX003RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerSelectServiceImplL.searchX003");
        }
        List<IfaCustomerSelectX003ResponseDto> resDtoList = new ArrayList<IfaCustomerSelectX003ResponseDto>();
        
        //ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // ①  ユーザ共通情報.権限コードが1（SBI証券本店）以外の場合、共通関数FCT030を呼び出し、利用者顧客参照範囲の仲介業者コード、営業員コードを取得する。
        OutputFct030Dto fct030Dto = null;
        if (StringUtils.equals(userAccount.getPrivId(), "1") == false) {
            
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            // FCT030.仲介業者営業員リストの件数が1件以上の場合：次の処理へ。
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            if (fct030Dto.getBrokerChargeList().size() == 0) {
                DataList<IfaCustomerSelectX003ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList,
                        ErrorLevel.FATAL, IfaCustomerSelectServiceImpL.ERRORS_NO_EXIST,
                        IfaCommonUtil.getMessage(IfaCustomerSelectServiceImpL.ERRORS_NO_EXIST));
                return dtoRes;
            }
        }
        
        // ②  SQL001の呼び出し
        List<IfaCustomerSelectSql001RequestModel.BrokerCharge> brokerChargeList = new ArrayList<IfaCustomerSelectSql001RequestModel.BrokerCharge>();
        if (fct030Dto != null) {
            // 利用者の顧客参照範囲の中で画面項目初期値の状態の検索条件にあった顧客の一覧を取得する。
            IfaCustomerSelectSql001RequestModel.BrokerCharge brokerCharge = null;
            for (OutputFct030Dto.BrokerCharge fct030brokerCharge : fct030Dto.getBrokerChargeList()) {
                brokerCharge = new IfaCustomerSelectSql001RequestModel.BrokerCharge();
                brokerCharge.setBrokerCode(fct030brokerCharge.getBrokerCode());
                brokerCharge.setEmpCode(fct030brokerCharge.getEmpCode());
                brokerChargeList.add(brokerCharge);
            }
        }
        
        IfaCustomerSelectSql001RequestModel selSql001Req = new IfaCustomerSelectSql001RequestModel();
        selSql001Req.setBrokerChargeList(brokerChargeList);
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setUserId(userAccount.getUserId());
        selSql001Req.setButenCode(dtoReq.getButenCode());
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        selSql001Req.setFavorite(dtoReq.getFavorite());
        selSql001Req.setTradeRestrictionSelect(dtoReq.getTradeRestrictionSelect());
        selSql001Req.setCustomerId(dtoReq.getCustomerId());
		// 顧客名条件による分岐処理
		String searchName = VALUE_EMPTY;
		if (!StringUtils.equals(dtoReq.getCustomerName(), VALUE_EMPTY)) {
			searchName = toFullWidth(dtoReq.getCustomerName());
			// 「で始まる」の場合
			if (StringUtils.equals(dtoReq.getCustomerNameConditionList(),
					CustomerNameConditionList.STARTS_WITH.getValue())) {
				// 前方一致となるように整形
				searchName = String.format("%s%%", searchName);
				// 「と等しい」の場合
			} else if (StringUtils.equals(dtoReq.getCustomerNameConditionList(),
					CustomerNameConditionList.EQUAL_TO.getValue())) {
				// 完全一致となるように整形
				searchName = String.format("%s", searchName);
				// 「を含む」の場合
			} else if (StringUtils.equals(dtoReq.getCustomerNameConditionList(),
					CustomerNameConditionList.INCLUDING.getValue())) {
				// 部分一致となるように整形
				searchName = String.format("%%%s%%", searchName);
			}
			selSql001Req.setCustomerName(searchName);
		}
        
        DataList<IfaCustomerSelectSql001ResponseModel> selSql001Res = dao.selectIfaCustomerSelectSql001(selSql001Req);
        
        ErrorLevel rtnLevel = ErrorLevel.SUCCESS;
        String rtnMessageId = "";
        String rtnMessage = "";
        if (selSql001Res.getDataList().size() == 0) {
            //   取得件数0件：該当データなしメッセージを返す。
            DataList<IfaCustomerSelectX003ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList,
                    ErrorLevel.INFO, IfaCustomerSelectServiceImpL.ERRORS_NO_FOUND,
                    IfaCommonUtil.getMessage(IfaCustomerSelectServiceImpL.ERRORS_NO_FOUND));
            return dtoRes;
            
        } else if (Integer.parseInt(selSql001Res.getDataList().get(0).getSearchCount()) > 5000) {
            //   取得件数5000件超：5000件メッセージを設定する。
            rtnLevel = ErrorLevel.WARNING;
            rtnMessageId = IfaCustomerSelectServiceImpL.WARNINGS_OVER_MAX_ROWNUM;
            rtnMessage = IfaCommonUtil.getMessage(IfaCustomerSelectServiceImpL.WARNINGS_OVER_MAX_ROWNUM,
                    new String[] { IfaCustomerSelectServiceImpL.WARNINGS_OVER_MAX_5000,
                            selSql001Res.getDataList().get(0).getSearchCount() });
        }
        
        //     取得件数1件～5000件：次の処理へ。
        List<IfaCustomerSelectX003ResponseDto.Customer> coustomerInfoList = new ArrayList<IfaCustomerSelectX003ResponseDto.Customer>();
        IfaCustomerSelectX003ResponseDto.Customer customerInfo = null;
        for (IfaCustomerSelectSql001ResponseModel sql001Model : selSql001Res.getDataList()) {
            customerInfo = new IfaCustomerSelectX003ResponseDto.Customer();
            customerInfo.setButenCode(sql001Model.getButenCode());
            customerInfo.setAccountNumber(sql001Model.getAccountNumber());
            
            // SQL001.C.注意情報エラー件数＞0、または、SQL001.D.部店コードがNULLでない の条件に該当する場合
            //   レスポンス.取引制限有無に「1:取引制限あり」を設定
            // 上記以外の場合
            //   レスポンス.取引制限有無に「0:取引制限なし」を設定する。
            if ((sql001Model.getNoticeInfoErrorCount() != null
                            && Integer.parseInt(sql001Model.getNoticeInfoErrorCount()) > 0)
                || sql001Model.getTradeRestrictButenCode() != null) {
                customerInfo.setTradeRestrictionSelect("1");
            } else {
                customerInfo.setTradeRestrictionSelect("0");
            }
            customerInfo.setCustomerNameKanji(sql001Model.getCustomerNameKanji());
            customerInfo.setCustomerNameKana(sql001Model.getCustomerNameKana());
            customerInfo.setOpenAcctDate((sql001Model.getOpenAcctDate() == null) ? sql001Model.getOpenAcctDate()
                    : DateFormatUtil.convertDateString(sql001Model.getOpenAcctDate()));
            customerInfo.setFavoRegStatus(sql001Model.getFavoRegStatus());
            customerInfo.setCustomerCode(sql001Model.getCustomerCode());
            
            coustomerInfoList.add(customerInfo);
        }
        
        IfaCustomerSelectX003ResponseDto resDto = new IfaCustomerSelectX003ResponseDto();
        resDto.setCustomerList(coustomerInfoList);
        
        resDtoList.add(resDto);
        DataList<IfaCustomerSelectX003ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList, rtnLevel,
                rtnMessageId, rtnMessage);
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：お気に入り登録・解除
     * Dto リクエスト：IfaCustomerSelectA004RequestDto
     * Dto レスポンス：IfaCustomerSelectA004ResponseDto
     * model リクエスト：IfaCustomerSelectA004RequestModel
     * model レスポンス：IfaCustomerSelectA004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception お気に入り登録・解除処理で例外が発生した場合
     */
    public DataList<Object> favoriteRegisterUnregisterA004(IfaCustomerSelectA004RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerSelectServiceImplL.favoriteRegisterUnregisterA004");
        }
        List<Object> resDtoList = new ArrayList<Object>();
        
        // ①共通関数FCT001の呼び出し
        // 利用者の口座に対する権限チェックを行う。
        // 権限あり（対象顧客参照権限有無＝"1"）：次の処理へ。
        // 権限なし（対象顧客参照権限有無＝"0"：権限なしエラーを返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(dtoReq.getButenCode());
        fct001InData.setAccountNumber(dtoReq.getAccountNumber());
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaCustomerSelectServiceImpL.AUTH_ERROR_VALUE)) {
            
            DataList<Object> dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaCustomerSelectServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(IfaCustomerSelectServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
            return dtoRes;
        }
        
        //ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // ② SQL002の呼び出し
        // 利用者の対象顧客に対するお気に入り登録状況を登録更新する。
        IfaCustomerSelectSql002RequestModel selSql002Req = new IfaCustomerSelectSql002RequestModel();
        selSql002Req.setUserId(userAccount.getUserId());
        selSql002Req.setButenCode(dtoReq.getButenCode());
        selSql002Req.setAccountNumber(dtoReq.getAccountNumber());
        selSql002Req.setCustomerCode(dtoReq.getCustomerCode());
        selSql002Req.setFavoRegStatus(dtoReq.getFavoRegStatus());
        
        int insertOrUpdateCount = dao.selectIfaCustomerSelectSql002(selSql002Req);
        if (insertOrUpdateCount == 0) {
            // 上記以外：顧客一覧.お気に入り登録状況を切替前の状態に戻し、更新エラーを返す。
            DataList<Object> dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaCustomerSelectServiceImpL.ERRORS_PROCESSING_FAILD,
                    IfaCommonUtil.getMessage(IfaCustomerSelectServiceImpL.ERRORS_PROCESSING_FAILD,
                            new String[] { IfaCustomerSelectServiceImpL.UPDATE_FAVO_REG_STATUS }));
            return dtoRes;
        }
        
        // 更新成功：処理終了。
        DataList<Object> dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：顧客選択
     * Dto リクエスト：IfaCustomerSelectA005DtoRequest
     * Dto レスポンス：IfaCustomerSelectA005DtoResponse
     * model リクエスト：IfaCustomerSelectA005RequestModel
     * model レスポンス：IfaCustomerSelectA005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 顧客選択処理で例外が発生した場合
     */
    public DataList<Object> customerSelectA005(IfaCustomerSelectA005RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerSelectServiceImplL.customerSelectA005");
            
        }
        List<Object> resDtoList = new ArrayList<Object>();
        
        // ①共通関数FCT001の呼び出し
        // 利用者の口座に対する権限チェックを行う。
        // 権限あり（対象顧客参照権限有無＝"1"）：次の処理へ。
        // 権限なし（対象顧客参照権限有無＝"0"：権限なしエラーを返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(dtoReq.getButenCode());
        fct001InData.setAccountNumber(dtoReq.getAccountNumber());
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaCustomerSelectServiceImpL.AUTH_ERROR_VALUE)) {
            
            DataList<Object> dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaCustomerSelectServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(IfaCustomerSelectServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
            return dtoRes;
        }
        
        DataList<Object> dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }
    
    private String toFullWidth(String src) {
        // Unicode normalization
        // (half Kanner full-width kana conversion, full-width alphanumeric symbol - alphanumeric symbol conversion)
        src = Normalizer.normalize(src, Normalizer.Form.NFKC);

        // Half-size special symbol full-width conversion
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < src.length(); i++) {
            sb.append(toFullWidthChar(src.charAt(i)));
        }
        String result = sb.toString();

        // ICU (International Components for Unicode) Halfwidth-Fullwidth
        Transliterator transliterator = Transliterator.getInstance("Halfwidth-Fullwidth");
        result = transliterator.transliterate(result);

        return result;
    }

    
    private char toFullWidthChar(char value){
        if (value == '\'') {        // In the case of single-byte apostrophe
            return '’';
        } else if (value == '\"') { // In the case of single-byte quotation marks
            return '”';
        } else if (value == '`')  { // In the case of half-grave accent em angle quotation marks (start)
            return '‘';
        } else if (value == '\\')  { // In the case of single-byte ¥
            return '￥';
        } else {
            return value;
        }
    }
    
}
