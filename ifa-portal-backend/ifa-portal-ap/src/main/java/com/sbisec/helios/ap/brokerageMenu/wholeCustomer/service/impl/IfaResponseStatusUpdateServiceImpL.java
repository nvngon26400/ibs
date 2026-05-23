package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaResponseStatusUpdateDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaResponseStatusUpdateService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020301_03-01_1
 * 画面名：対応状況更新
 *
 * @author
 2024/05/28 新規作成
 */
@Component(value = "cmpIfaResponseStatusUpdateService")
public class IfaResponseStatusUpdateServiceImpL implements IfaResponseStatusUpdateService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaResponseStatusUpdateServiceImpL.class);
    
    @Autowired
    private IfaResponseStatusUpdateDao dao;
    
    @Autowired
    private Fct001 fct001;
    
    /** 権限あり */
    private static final String AUTH_VALUE = "1";
    
    /** 権限チェックエラー  */
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    /** SQL001取得エラー  */
    private static final String ERRORS_CMN_INFORMATION_NOTFOUND = "errors.cmn.information.notfound";
    
    /** 対応状況情報ステータスチェックエラー  */
    private static final String ERRORS_EXCLUSIVE = "errors.exclusive";

    /** 対応状況更新完了メッセージ */
    private static final String INFO_UPDATE_COMPLETED = "info.updateCompleted";
    
    /** SQL001.ステータス '1':承認済 */
    private static final String APPROVE = "1";
    
    /** 対応状況  */
    private static final String RESPONSESTATUS = "対応状況";
    
    /** 日付入力値チェックエラー（以前）  */
    private static final String ERRORS_DATE_SPECIFYAFTERREFERENCEDATE = "errors.date.specifyAfterReferenceDate";
    
    /** 日付入力値チェックエラー（より前）  */
    private static final String ERRORS_DATE_SPECIFYSAMEDATEORAFTERDATE = "errors.date.specifySameDateOrAfterDate";
    
    /** 対応完了確認日更新フラグ '1':更新対象  */
    private static final String UPDATEAPPLICABLE = "1";
    
    /** 対応完了確認日更新フラグ '0':更新対象外  */
    private static final String UPDATENOTAPPLICABLE = "0";
    
    /** 顧客対応日  */
    private static final String CUSTOMERRESPONSEDATE = "顧客対応日";
    
    /** 基準日  */
    private static final String STANDARDDATE = "基準日";
    
    /** 対応完了確認日  */
    private static final String RESPONSEFINISHCONFIRMDATE = "対応完了確認日";
    
    /** YYYY/MM/DD */
    private static final String SEPARATED_YYYYMMDD = "yyyy/MM/dd";

    /** ユーザ共通情報.権限コード　*/
    public enum PrivId {
        
        /**　SBI証券本店 */
        SBIMAINSTORE("1"),
        /**　SBI証券支店 */
        SBIBRANCH("2"),
        /**　仲介業者 - 内部管理責任者 */
        BROKER("3"),
        /** 仲介業者 - 支店 - 内部管理責任者 */
        BROKERBRANCH("6");
        
        private String key;
        
        PrivId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaResponseStatusUpdateA001RequestDto
     * Dto レスポンス：IfaResponseStatusUpdateA001ResponseDto
     * model リクエスト：IfaResponseStatusUpdateSQL001RequestModel
     * model レスポンス：IfaResponseStatusUpdateSQL001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaResponseStatusUpdateA001ResponseDto> initializeA001(IfaResponseStatusUpdateA001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaResponseStatusUpdateA001ResponseDto> dtoRes = new DataList<IfaResponseStatusUpdateA001ResponseDto>();
        List<IfaResponseStatusUpdateA001ResponseDto> resDtoList = new ArrayList<IfaResponseStatusUpdateA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaResponseStatusUpdateServiceImplL.initializeA001");
        
        
        // リクエスト.部店コードとリクエスト.口座番号を変数化
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        
        //①   利用者の口座に対する権限チェックを行う。
        InputFct001Dto fct001InData = new InputFct001Dto();
        // 部店コード
        fct001InData.setButenCode(butenCode);
        // 口座番号
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        // FCT001.対象顧客参照権限有無 ≠ '1':権限あり の場合
        // エラーメッセージを表示し、処理を終了する。
        if (!(StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(), AUTH_VALUE))) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                            new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        // FCT001.対象顧客参照権限有無 = '1':権限あり の場合
        // 次の処理へ
        
        //②   対応状況情報を取得する。
        IfaResponseStatusUpdateSql001RequestModel selSql001Req = new IfaResponseStatusUpdateSql001RequestModel();
        // 下落率区分
        selSql001Req.setDeclineRateKbn(dtoReq.getDeclineRateKbn());
        // 部店コード
        selSql001Req.setButenCode(dtoReq.getButenCode());
        // 口座番号
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        // 投信協会コード
        selSql001Req.setInvestmentTrustAssociationCode(dtoReq.getInvestmentTrustAssociationCode());
        // 基準日（To）
        selSql001Req.setStandardDateTo(dtoReq.getStandardDateTo());
        
        DataList<IfaResponseStatusUpdateSql001ResponseModel> selSql001Res = dao
                .selectIfaResponseStatusUpdateSql001(selSql001Req);
        //　SQL001の取得件数が1件以外の場合、エラーメッセージを表示し、処理を終了する。
        if (selSql001Res.getDataList().size() != 1) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_NOTFOUND));
            return dtoRes;
        }
        //　SQL001の取得件数が1件の場合、次の処理へ。
        
        // ユーザ共通情報の取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        //③   ユーザ共通情報.権限コードが以下のいずれかでもない場合、対応状況情報のステータスをチェックする。
        //　　　'1':SBI証券本店
        //　　　'2':SBI証券支店
        //　　　'3':仲介業者 - 内部管理責任者
        //　　　'6':仲介業者 - 支店 - 内部管理責任者
        if (!((StringUtils.equals(ua.getPrivId(), PrivId.SBIMAINSTORE.key))
                || StringUtils.equals(ua.getPrivId(), PrivId.SBIBRANCH.key)
                || StringUtils.equals(ua.getPrivId(), PrivId.BROKER.key)
                || StringUtils.equals(ua.getPrivId(), PrivId.BROKERBRANCH.key))) {
            // SQL001.ステータス が '1':承認済 の場合、エラーメッセージを表示し、処理を終了する。
            if (StringUtils.equals(selSql001Res.getDataList().get(0).getStatusKbn(), APPROVE)) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_EXCLUSIVE,
                        IfaCommonUtil.getMessage(ERRORS_EXCLUSIVE, new String[] { RESPONSESTATUS }));
                return dtoRes;
            }
            // 上記以外の場合、次の処理へ。
        }
        
        // レスポンスパラメータセット
        IfaResponseStatusUpdateA001ResponseDto resDto = new IfaResponseStatusUpdateA001ResponseDto();
        // 下落率区分
        resDto.setDeclineRateKbn(selSql001Res.getDataList().get(0).getDeclineRateKbn());
        // 部店コード
        resDto.setButenCode(selSql001Res.getDataList().get(0).getButenCode());
        // 口座番号
        resDto.setAccountNumber(selSql001Res.getDataList().get(0).getAccountNumber());
        // 投信協会コード
        resDto.setInvestmentTrustAssociationCode(selSql001Res.getDataList().get(0).getToushinKyoukaiCode());
        // 基準日
        resDto.setStandardDate(selSql001Res.getDataList().get(0).getBaseDateTo());
        // 投信銘柄名
        resDto.setMutualFundBrandName(selSql001Res.getDataList().get(0).getToushinBrandName());
        // 基準価額
        resDto.setPrice(selSql001Res.getDataList().get(0).getBasePriceTo());
        // 前日比
        resDto.setDiff(selSql001Res.getDataList().get(0).getZenjitsuHi());
        // 下落率
        resDto.setRateOfDecline(selSql001Res.getDataList().get(0).getDeclineRate());
        // 顧客名
        resDto.setCustomerName(selSql001Res.getDataList().get(0).getNameKanji());
        // ステータス区分
        resDto.setStatusClassification(selSql001Res.getDataList().get(0).getStatusKbn());
        // 対応方法区分
        resDto.setResponseMethodClassification(selSql001Res.getDataList().get(0).getMethodsKbn());
        // その他内容区分
        resDto.setOtherContentsClassification(selSql001Res.getDataList().get(0).getOtherContentsKbn());
        // その他詳細
        resDto.setOtherDetail(selSql001Res.getDataList().get(0).getOtherDetails());
        // 顧客対応日
        resDto.setCustomerResponseDate(selSql001Res.getDataList().get(0).getCustomerSupportDate());
        
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A006
     * アクション名：対応状況更新確認OK
     * Dto リクエスト：IfaResponseStatusUpdateA006RequestDto
     * Dto レスポンス：IfaResponseStatusUpdateA006ResponseDto
     * model リクエスト：IfaResponseStatusUpdateSQL001RequestModel
     * model レスポンス：IfaResponseStatusUpdateSQL001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaResponseStatusUpdateA006ResponseDto> responseStatusUpdateConfirmOkA006(
            IfaResponseStatusUpdateA006RequestDto dtoReq) throws Exception {
        
        DataList<IfaResponseStatusUpdateA006ResponseDto> dtoRes = new DataList<IfaResponseStatusUpdateA006ResponseDto>();
        List<IfaResponseStatusUpdateA006ResponseDto> resDtoList = new ArrayList<IfaResponseStatusUpdateA006ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaResponseStatusUpdateServiceImplL.responseStatusUpdateConfirmOkA006");
        
        // リクエスト.部店コードとリクエスト.口座番号を変数化
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        
        //①   利用者の口座に対する権限チェックを行う。
        InputFct001Dto fct001InData = new InputFct001Dto();
        // 部店コード
        fct001InData.setButenCode(butenCode);
        // 口座番号
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        // FCT001.対象顧客参照権限有無 ≠ '1':権限あり の場合
        // エラーメッセージを表示し、処理を終了する。
        if (!(StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(), AUTH_VALUE))) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                            new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        // FCT001.対象顧客参照権限有無 = '1':権限あり の場合
        // 次の処理へ
        
        //②   リクエスト.顧客対応日の入力値チェックを行う。
        //　リクエスト.顧客対応日
        String customerResponseDate = dtoReq.getCustomerResponseDate();
        //　リクエスト.基準日（To）
        String standardDateTo = dtoReq.getStandardDateTo();
        //　リクエスト.顧客対応日に値がある場合のみ、リクエスト.顧客対応日 が リクエスト.基準日（To）以前 の場合、メッセージを表示し、処理を終了する。
        if (!Strings.isNullOrEmpty(customerResponseDate)) {
            if (standardDateTo.equals(customerResponseDate) ||
                !DateUtil.isValidFromTo(standardDateTo, customerResponseDate, SEPARATED_YYYYMMDD, SEPARATED_YYYYMMDD)
                ) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_DATE_SPECIFYAFTERREFERENCEDATE,
                        IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFYAFTERREFERENCEDATE,
                                new String[] { CUSTOMERRESPONSEDATE, STANDARDDATE }));
                return dtoRes;
            }
        }
        //　上記以外の場合、次の処理へ。
        
        //　リクエスト.対応完了確認日
        String responseFinishConfirmDate = dtoReq.getResponseFinishConfirmDate();
        //③   リクエスト.対応完了確認日に値がある場合のみ、リクエスト.対応完了確認日の入力値チェックを行う。
        if (!(Strings.isNullOrEmpty(responseFinishConfirmDate))) {
            //　リクエスト.対応完了確認日 が リクエスト.基準日（To）以前 の場合、メッセージを表示し、処理を終了する。
            if (standardDateTo.equals(responseFinishConfirmDate) ||
                !DateUtil.isValidFromTo(standardDateTo, responseFinishConfirmDate, SEPARATED_YYYYMMDD, SEPARATED_YYYYMMDD)
                ) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        ERRORS_DATE_SPECIFYAFTERREFERENCEDATE,
                        IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFYAFTERREFERENCEDATE,
                                new String[] { RESPONSEFINISHCONFIRMDATE, STANDARDDATE }));
                return dtoRes;
            //　リクエスト.顧客対応日に値がある場合かつ、リクエスト.対応完了確認日 が リクエスト.顧客対応日 より前 の場合、メッセージを表示し、処理を終了する。
            } else if (!Strings.isNullOrEmpty(customerResponseDate) && !DateUtil.isValidFromTo(customerResponseDate, responseFinishConfirmDate, SEPARATED_YYYYMMDD, SEPARATED_YYYYMMDD)) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        ERRORS_DATE_SPECIFYSAMEDATEORAFTERDATE,
                        IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFYSAMEDATEORAFTERDATE,
                                new String[] { RESPONSEFINISHCONFIRMDATE, CUSTOMERRESPONSEDATE }));
                return dtoRes;
            }
        }
        //　上記以外の場合、次の処理へ。
        
        //②   対応状況情報を取得する。
        IfaResponseStatusUpdateSql001RequestModel selSql001Req = new IfaResponseStatusUpdateSql001RequestModel();
        // 下落率区分
        selSql001Req.setDeclineRateKbn(dtoReq.getDeclineRateKbn());
        // 部店コード
        selSql001Req.setButenCode(dtoReq.getButenCode());
        // 口座番号
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        // 投信協会コード
        selSql001Req.setInvestmentTrustAssociationCode(dtoReq.getInvestmentTrustAssociationCode());
        // 基準日（To）
        selSql001Req.setStandardDateTo(dtoReq.getStandardDateTo());
        
        DataList<IfaResponseStatusUpdateSql001ResponseModel> selSql001Res = dao
                .selectIfaResponseStatusUpdateSql001(selSql001Req);
        //④　SQL001の取得件数が1件以外の場合、エラーメッセージを表示し、処理を終了する。
        if (selSql001Res.getDataList().size() != 1) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_NOTFOUND));
            return dtoRes;
        }
        //　SQL001の取得件数が1件の場合、次の処理へ。
        
        // ユーザ共通情報の取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        //⑤   ユーザ共通情報.権限コードが以下のいずれかでもない場合、対応状況情報のステータスをチェックする。
        //　　　'1':SBI証券本店
        //　　　'2':SBI証券支店
        //　　　'3':仲介業者 - 内部管理責任者
        //　　　'6':仲介業者 - 支店 - 内部管理責任者
        if (!((StringUtils.equals(ua.getPrivId(), PrivId.SBIMAINSTORE.key))
                || StringUtils.equals(ua.getPrivId(), PrivId.SBIBRANCH.key)
                || StringUtils.equals(ua.getPrivId(), PrivId.BROKER.key)
                || StringUtils.equals(ua.getPrivId(), PrivId.BROKERBRANCH.key))) {
            // SQL001.ステータス が '1':承認済 の場合、エラーメッセージを表示し、処理を終了する。
            if (StringUtils.equals(selSql001Res.getDataList().get(0).getStatusKbn(), APPROVE)) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_EXCLUSIVE,
                        IfaCommonUtil.getMessage(ERRORS_EXCLUSIVE, new String[] { RESPONSESTATUS }));
                return dtoRes;
            }
            // 上記以外の場合、次の処理へ。
        }
        
        IfaResponseStatusUpdateSql002RequestModel updSql002Req = new IfaResponseStatusUpdateSql002RequestModel();
        //⑥   対応完了確認日更新フラグ を算出する。
        //　ユーザ共通情報.権限コードが以下のいずれか、かつリクエスト.対応完了確認日の入力がある場合、対応完了確認日更新フラグ に '1':更新対象 をセットする。
        //  '1':SBI証券本店
        //  '2':SBI証券支店
        //  '3':仲介業者 - 内部管理責任者
        //  '6':仲介業者 - 支店 - 内部管理責任者
        if (((StringUtils.equals(ua.getPrivId(), PrivId.SBIMAINSTORE.key))
                || StringUtils.equals(ua.getPrivId(), PrivId.SBIBRANCH.key)
                || StringUtils.equals(ua.getPrivId(), PrivId.BROKER.key)
                || StringUtils.equals(ua.getPrivId(), PrivId.BROKERBRANCH.key))
                && !Strings.isNullOrEmpty(responseFinishConfirmDate)){
            updSql002Req.setResponseCompleteConfirmUpdateFlag(UPDATEAPPLICABLE);
            //　上記以外の場合、対応完了確認日更新フラグ に '0':更新対象外 をセットする。
        } else {
            updSql002Req.setResponseCompleteConfirmUpdateFlag(UPDATENOTAPPLICABLE);
        }
        
        //⑦    対応状況情報で更新する。
        // 対応方法区分
        updSql002Req.setResponseMethodClassification(dtoReq.getResponseMethodClassification());
        // その他内容区分
        updSql002Req.setOtherContentsClassification(dtoReq.getOtherContentsClassification());
        // その他詳細
        updSql002Req.setOtherDetail(dtoReq.getOtherDetail());
        // 顧客対応日
        updSql002Req.setCustomerResponseDate(dtoReq.getCustomerResponseDate());
        // 対応完了確認日
        updSql002Req.setResponseFinishConfirmDate(dtoReq.getResponseFinishConfirmDate());
        // 更新ユーザ
        updSql002Req.setUpdateUser(ua.getUserId());
        // 下落率区分
        updSql002Req.setDeclineRateKbn(dtoReq.getDeclineRateKbn());
        // 部店コード（半角英数字）
        updSql002Req.setButenCode(dtoReq.getButenCode());
        // 口座番号（数字）
        updSql002Req.setAccountNumber(dtoReq.getAccountNumber());
        // 投信協会コード（半角英数字）
        updSql002Req.setInvestmentTrustAssociationCode(dtoReq.getInvestmentTrustAssociationCode());
        // 基準日（To）
        updSql002Req.setStandardDateTo(dtoReq.getStandardDateTo());
        dao.updateIfaResponseStatusUpdateSql002(updSql002Req);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.INFO, INFO_UPDATE_COMPLETED,
                IfaCommonUtil.getMessage(INFO_UPDATE_COMPLETED, new String[]{"対応状況"}));
        return dtoRes;
    }
}
