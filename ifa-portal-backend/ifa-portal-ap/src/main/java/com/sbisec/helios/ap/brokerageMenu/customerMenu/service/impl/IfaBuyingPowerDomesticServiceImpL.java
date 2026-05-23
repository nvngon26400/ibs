package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct039;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct039Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct039Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticCommonDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaBuyingPowerDomesticService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.JrIsaContractType;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.ApiWrapperUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;

import lombok.Data;

/**
 * 画面ID：SUB0202_010301-01
 * 画面名：買付余力（国内）

 * @author 松田
    2024/01/10 新規作成
 */
@Component(value = "cmpIfaBuyingPowerDomesticService")
public class IfaBuyingPowerDomesticServiceImpL implements IfaBuyingPowerDomesticService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBuyingPowerDomesticServiceImpL.class);
    
    /**
     * FCT001 利用者顧客参照権限チェック
     */
    @Autowired
    private Fct001 fct001;
    
    /**
     * FCT039 ポイント照会
     */
    @Autowired
    private Fct039 fct039;

    /**
     * API呼び出しクラス
     */
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private ApiWrapperUtil apiWrapperUtil;
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    // ポイント情報を取得できませんでした。
    private static final String WARNIGNS_REFERPOINT_SYSTEMERROR = "warnings.cmn.referPoint.systemError";

    /** 払出制限解除フラグ:払出制限中 */
    private static final String WITHDRAWAL_RESTRICTION_CANCEL_FLAG_TRUE = "1";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    /** 一般総合口座:一般総合口座以外 */
    private static final String GENERAL_ACCOUNT_OTHER = "0";
    
    /** 一般総合口座:一般総合口座 */
    private static final String GENERAL_ACCOUNT_GENERAL = "1";
    
    /** ジュニア総合口座:ジュニア総合口座以外 */
    private static final String JUNIOR_ACCOUNT_OTHER = "0";
    
    /** ジュニア総合口座:ジュニア総合口座 */
    private static final String JUNIOR_ACCOUNT_JUNIOR = "1";
    
    /** ジュニアNISA口座:ジュニア総合口座以外 */
    private static final String JUNIOR_NISA_ACCOUNT_OTHER = "0";
    
    /** ジュニアNISA口座:ジュニア総合口座 */
    private static final String JUNIOR_NISA_ACCOUNT_NISA = "1";
    
    /** .預り金自動スイープ申込区分*/
    private static final String API001_RES_AUTO_SWEEP_KBN_SPACE = " ";
    
    /** SBIハイブリッドフラグ:SBIハイブリッドではない */
    private static final String SBI_HYBRID_FLAG_NOT_HYBRID = "0";
    
    /** SBIハイブリッドフラグ:SBIハイブリッド */
    private static final String SBI_HYBRID_FLAG_HYBRID = "1";
    
    /** 口座区分:一般総合口座 */
    private static final String ACCOUNT_TYPE_GENERAL = " ";
    
    /** 口座区分:ジュニアNISA口座 */
    private static final String ACCOUNT_TYPE_JR_NISA = "1";
    
    /** 口座区分:全て */
    private static final String ACCOUNT_TYPE_ALL = "2";
    
    /**
     * エラー時のメッセージ格納クラス

     * @author SCSK
     *
     */
    @Data
    private class ErrorResponseDto {
        
        /** エラー判定 */
        private boolean errorFlg = false;
        
        /** エラーメッセージID */
        private String errorMessageId;
        
        /** エラーメッセージ */
        private String errorMessage;
        
    }
    
    /**
     * APIレスポンス配下オブジェクト存在チェック結果Dto

     * @author SCSK
     *
     */
    @Data
    private class CheckExistsApiResponseDto {
        
        /** API結果で保持している各オブジェクトの存在有無を確認する */
        private boolean existsT0 = false;
        private boolean existsT1 = false;
        private boolean existsT2 = false;
        private boolean existsT3 = false;
        private boolean existsT4 = false;
        private boolean existsT5 = false;
        private boolean existsT0Jr = false;
        private boolean existsT1Jr = false;
        private boolean existsT2Jr = false;
        private boolean existsT3Jr = false;
        private boolean existsT4Jr = false;
        private boolean existsT5Jr = false;
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBuyingPowerDomesticA001DtoRequest
     * Dto レスポンス：IfaBuyingPowerDomesticA001DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaBuyingPowerDomesticA001ResponseDto> initializeA001(IfaBuyingPowerDomesticA001RequestDto dtoReq)
            throws Exception {
        
        List<IfaBuyingPowerDomesticA001ResponseDto> resDto = new ArrayList<IfaBuyingPowerDomesticA001ResponseDto>();
        IfaBuyingPowerDomesticCommonDtoResponse response = new IfaBuyingPowerDomesticCommonDtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBuyingPowerDomesticServiceImplL.initializeA001");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        ErrorResponseDto errorDto = new ErrorResponseDto();
        // 利用者の口座に対する権限チェックを行う
        // FCT001
        errorDto = callFct001(cc);
        if (errorDto.isErrorFlg()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorDto.getErrorMessageId(),
                    errorDto.getErrorMessage());
        }
        
        // 画面表示用のフラグ設定
        // 顧客共通情報.ジュニアISA契約区分＝1かつ顧客共通情報.払出制限解除フラグ＝1（払出制限中）の場合
        if (StringUtils.equals(cc.getJrIsaContractType(), JrIsaContractType.CONTRACT.getId()) && StringUtils
                .equals(cc.getWithdrawalRestrictionCancelFlag(), WITHDRAWAL_RESTRICTION_CANCEL_FLAG_TRUE)) {
            // 一般総合口座フラグ
            response.setGeneralAccountFlag(GENERAL_ACCOUNT_OTHER);
            // ジュニア総合口座フラグ
            response.setJrGeneralAccountFlag(JUNIOR_ACCOUNT_JUNIOR);
            // ジュニアNISA口座フラグ
            response.setJrNisageneralAccountFlag(JUNIOR_NISA_ACCOUNT_NISA);
        } else {
            // 一般総合口座フラグ
            response.setGeneralAccountFlag(GENERAL_ACCOUNT_GENERAL);
            // ジュニア総合口座フラグ
            response.setJrGeneralAccountFlag(JUNIOR_ACCOUNT_OTHER);
            // ジュニアNISA口座フラグ
            response.setJrNisageneralAccountFlag(JUNIOR_NISA_ACCOUNT_OTHER);
        }
        // API結果
        QueryAccountBalanceOutData api001OutData = new QueryAccountBalanceOutData();
        
        // API001呼び出し
        api001OutData = queryAccountBalance(cc);
        // API処理結果確認
        apiErrorUtil.checkApiResponse(api001OutData.getShubetu(), api001OutData.getCode(), api001OutData.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        // 共通処理
        errorDto = getResponseColumns(cc, response, api001OutData);
        
        if (errorDto.errorFlg) {
            // 画面メッセージ
            apiErrorUtil.addPomApiError(errorDto.getErrorMessageId(), errorDto.getErrorMessage());
        }        

        IfaBuyingPowerDomesticA001ResponseDto a001Response = new IfaBuyingPowerDomesticA001ResponseDto();
        BeanUtils.copyProperties(a001Response, response);
        resDto.add(a001Response);
        
        return apiErrorUtil.createDataList(resDto, null);
    }
    
    /**
     * アクションID：A003
     * アクション名：再検索
     * Dto リクエスト：IfaBuyingPowerDomesticA003DtoRequest
     * Dto レスポンス：IfaBuyingPowerDomesticA003DtoResponse
     * model リクエスト：IfaFaqSql002RequestModel
     * model レスポンス：IfaFaqSql002ResponseModel

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws exception 再検索処理で例外が発生した場合
     */
    public DataList<IfaBuyingPowerDomesticA003ResponseDto> reSearchA003(IfaBuyingPowerDomesticA003RequestDto dtoReq)
            throws Exception {
        
        IfaBuyingPowerDomesticCommonDtoResponse response = new IfaBuyingPowerDomesticCommonDtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBuyingPowerDomesticServiceImplL.reSearchA003");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 画面表示用のフラグ設定
        // 一般総合口座フラグ
        if (StringUtils.equals(ACCOUNT_TYPE_GENERAL, dtoReq.getAccountTypeRadio())) {
            response.setGeneralAccountFlag(GENERAL_ACCOUNT_GENERAL);
        } else {
            response.setGeneralAccountFlag(GENERAL_ACCOUNT_OTHER);
        }
        // ジュニア総合口座フラグ
        if (StringUtils.equals(ACCOUNT_TYPE_GENERAL, dtoReq.getAccountTypeRadio()) 
                || StringUtils.equals(ACCOUNT_TYPE_ALL, dtoReq.getAccountTypeRadio())) {
            response.setJrGeneralAccountFlag(JUNIOR_ACCOUNT_JUNIOR);
        } else {
            response.setJrGeneralAccountFlag(JUNIOR_ACCOUNT_OTHER);
        }
        
        // ジュニアNISA口座
        if (StringUtils.equals(ACCOUNT_TYPE_JR_NISA, dtoReq.getAccountTypeRadio()) 
                || StringUtils.equals(ACCOUNT_TYPE_ALL, dtoReq.getAccountTypeRadio())) {
            response.setJrNisageneralAccountFlag(JUNIOR_NISA_ACCOUNT_NISA);
        } else {
            response.setJrNisageneralAccountFlag(JUNIOR_NISA_ACCOUNT_OTHER);
        }
        
        // API結果
        QueryAccountBalanceOutData api001OutData = new QueryAccountBalanceOutData();
        
        // API001呼び出し
        api001OutData = queryAccountBalance(cc);
        // API処理結果確認
        apiErrorUtil.checkApiResponse(api001OutData.getShubetu(), api001OutData.getCode(), api001OutData.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        List<IfaBuyingPowerDomesticA003ResponseDto> resDto = new ArrayList<IfaBuyingPowerDomesticA003ResponseDto>();

        // 共通処理
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto = getResponseColumns(cc, response, api001OutData);

        if (errorDto.errorFlg) {
            // 画面メッセージ
             apiErrorUtil.addPomApiError(errorDto.getErrorMessageId(), errorDto.getErrorMessage());
        }      
        
        IfaBuyingPowerDomesticA003ResponseDto a003Response = new IfaBuyingPowerDomesticA003ResponseDto();
        BeanUtils.copyProperties(a003Response, response);
        resDto.add(a003Response);
        
        return apiErrorUtil.createDataList(resDto, null);
    }
    
    /**
     * A001、A003共通処理

     * @param cc 顧客共通情報
     * @param response レスポンス
     * @return ErrorResponseDto エラー時のメッセージ
     * @throws Exception エラー
     */
    private ErrorResponseDto getResponseColumns(CustomerCommon cc, IfaBuyingPowerDomesticCommonDtoResponse response,
            QueryAccountBalanceOutData api001OutData)
            throws Exception {
        // 画面項目編集
        if (checkNotNukllObjects(api001OutData)) {
            // 各オブジェクトの設定をチェック
            CheckExistsApiResponseDto checkDto = checkResponse(api001OutData);
            // 単純設定の項目
            editResponseColumnsDirects(response, api001OutData, checkDto);
            
            // 買付余力一覧
            if (StringUtils.equals(api001OutData.getAutoSweepKbn(), API001_RES_AUTO_SWEEP_KBN_SPACE)) {
                editResponseColumnsNotHybrid(response, api001OutData, checkDto);
            } else {
                editResponseColumnsHybrid(response, api001OutData, checkDto);
            }
        }

        // ポイントを取得する。(FCT039)
        return editResponseColumnsFct039(cc.getButenCode(), cc.getAccountNumber(), cc.getBrokerCode(), response);
    }


    /**
     * API結果の各オブジェクトが存在するかチェックする.

     * @param api001OutData API001処理結果
     */
    private CheckExistsApiResponseDto checkResponse(QueryAccountBalanceOutData api001OutData) {
        
        CheckExistsApiResponseDto checkDto = new CheckExistsApiResponseDto();
        
        checkDto.setExistsT0(checkNotNukllObjects(api001OutData.getT0()));
        checkDto.setExistsT1(checkNotNukllObjects(api001OutData.getT1()));
        checkDto.setExistsT2(checkNotNukllObjects(api001OutData.getT2()));
        checkDto.setExistsT3(checkNotNukllObjects(api001OutData.getT3()));
        checkDto.setExistsT4(checkNotNukllObjects(api001OutData.getT4()));
        checkDto.setExistsT5(checkNotNukllObjects(api001OutData.getT5()));
        checkDto.setExistsT0Jr(checkNotNukllObjects(api001OutData.getT0Jr()));
        checkDto.setExistsT1Jr(checkNotNukllObjects(api001OutData.getT1Jr()));
        checkDto.setExistsT2Jr(checkNotNukllObjects(api001OutData.getT2Jr()));
        checkDto.setExistsT3Jr(checkNotNukllObjects(api001OutData.getT3Jr()));
        checkDto.setExistsT4Jr(checkNotNukllObjects(api001OutData.getT4Jr()));
        checkDto.setExistsT5Jr(checkNotNukllObjects(api001OutData.getT5Jr()));
        
        return checkDto;
    }

    /**
     * SBIハイブリッド未契約時の各項目設定

     * @param response 返却Dto
     * @param api001OutData API001処理結果
     */
    private void editResponseColumnsNotHybrid(IfaBuyingPowerDomesticCommonDtoResponse response,
            QueryAccountBalanceOutData api001OutData, CheckExistsApiResponseDto checkDto) {
        
        // SBIハイブリッドフラグの設定
        response.setSbiHybridFlag(SBI_HYBRID_FLAG_NOT_HYBRID);
        if (checkDto.isExistsT0()) {
            /*1営業日後（預り金(MRF含む)）*/
            response.setAfterOnedayDepositIncludingMrf(api001OutData.getT0().getAccountTotal());
        }
        if (checkDto.isExistsT1()) {
            /*2営業日後（預り金(MRF含む)）*/
            response.setAfterTwodaysDepositIncludingMrf(api001OutData.getT1().getAccountTotal());
        }
        if (checkDto.isExistsT2()) {
            /*3営業日後（預り金(MRF含む)）*/
            response.setAfterThreedaysDepositIncludingMrf(api001OutData.getT2().getAccountTotal());
        }
        if (checkDto.isExistsT3()) {
            /*4営業日後（預り金(MRF含む)）*/
            response.setAfterFourdaysDepositIncludingMrf(api001OutData.getT3().getAccountTotal());
        }
        if (checkDto.isExistsT4()) {
            /*5営業日後（預り金(MRF含む)）*/
            response.setAfterFivedaysDepositIncludingMrf(api001OutData.getT4().getAccountTotal());
        }
        if (checkDto.isExistsT0Jr()) {
            /*1営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterOnedayJrNisaDepositIncludingMrf(api001OutData.getT0Jr().getAccountTotalJrnisa());
        }
        
        if (checkDto.isExistsT1Jr()) {
            /*2営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterTwodaysJrNisaDepositIncludingMrf(api001OutData.getT1Jr().getAccountTotalJrnisa());
        }
        if (checkDto.isExistsT2Jr()) {
            /*3営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterThreedaysJrNisaDepositIncludingMrf(api001OutData.getT2Jr().getAccountTotalJrnisa());
        }
        if (checkDto.isExistsT3Jr()) {
            
            /*4営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterFourdaysJrNisaDepositIncludingMrf(api001OutData.getT3Jr().getAccountTotalJrnisa());
        }
        if (checkDto.isExistsT4Jr()) {
            /*5営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterFivedaysJrNisaDepositIncludingMrf(api001OutData.getT4Jr().getAccountTotalJrnisa());
        }
    }
    
    /**
     * SBIハイブリッド契約時の各項目設定

     * @param response 返却Dto
     * @param api001OutData API001処理結果
     */
    private void editResponseColumnsHybrid(IfaBuyingPowerDomesticCommonDtoResponse response,
            QueryAccountBalanceOutData api001OutData, CheckExistsApiResponseDto checkDto) {
        
        // SBIハイブリッドフラグの設定
        response.setSbiHybridFlag(SBI_HYBRID_FLAG_HYBRID);
        if (checkDto.isExistsT0()) {
            /*1営業日後（預り金(MRF含む)）*/
            response.setAfterOnedayDepositIncludingMrf(api001OutData.getT0().getSettleEtBalance());
        }
        if (checkDto.isExistsT1()) {
            /*2営業日後（預り金(MRF含む)）*/
            response.setAfterTwodaysDepositIncludingMrf(api001OutData.getT1().getSettleEtBalance());
        }
        if (checkDto.isExistsT2()) {
            /*3営業日後（預り金(MRF含む)）*/
            response.setAfterThreedaysDepositIncludingMrf(api001OutData.getT2().getSettleEtBalance());
        }
        if (checkDto.isExistsT3()) {
            /*4営業日後（預り金(MRF含む)）*/
            response.setAfterFourdaysDepositIncludingMrf(api001OutData.getT3().getSettleEtBalance());
        }
        if (checkDto.isExistsT4()) {
            /*5営業日後（預り金(MRF含む)）*/
            response.setAfterFivedaysDepositIncludingMrf(api001OutData.getT4().getSettleEtBalance());
        }
        if (checkDto.isExistsT0Jr()) {
            /*1営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterOnedayJrNisaDepositIncludingMrf(api001OutData.getT0Jr().getSettleEtBalanceJrnisa());
        }
        if (checkDto.isExistsT1Jr()) {
            /*2営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterTwodaysJrNisaDepositIncludingMrf(api001OutData.getT1Jr().getSettleEtBalanceJrnisa());
        }
        if (checkDto.isExistsT2Jr()) {
            /*3営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterThreedaysJrNisaDepositIncludingMrf(api001OutData.getT2Jr().getSettleEtBalanceJrnisa());
        }
        if (checkDto.isExistsT3Jr()) {
            /*4営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterFourdaysJrNisaDepositIncludingMrf(api001OutData.getT3Jr().getSettleEtBalanceJrnisa());
        }
        if (checkDto.isExistsT4Jr()) {
            /*5営業日後(JrNISA)（預り金(MRF含む)）*/
            response.setAfterFivedaysJrNisaDepositIncludingMrf(api001OutData.getT4Jr().getSettleEtBalanceJrnisa());
        }
    }
    
    /**
     * FCT039設定

     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param brokerCode 仲介業者コード
     * @return ErrorResponseDto エラー時のメッセージ
     */
    private ErrorResponseDto editResponseColumnsFct039(String butenCode, String accountNumber,
            String brokerCode, IfaBuyingPowerDomesticCommonDtoResponse response) throws Exception {
        
        InputFct039Dto inputFct039Dto = new InputFct039Dto();
        inputFct039Dto.setButenCode(butenCode);
        inputFct039Dto.setAccountNumber(accountNumber);
        inputFct039Dto.setBrokerCode(brokerCode);
        
        OutputFct039Dto outputFct039Dto = fct039.getData(inputFct039Dto);

        // ポイント.ポイント種別
        response.setPointShubetsu(outputFct039Dto.getPointType());
    
        // ポイント.ポイント数
        response.setPointNumber(Objects.toString(outputFct039Dto.getPointNumber(), null));

        // ポイント.うち期間固定ポイント
        response.setFixedTermPoint(Objects.toString(outputFct039Dto.getRestrictPointSum(), null));

        // ポイント.最短有効期限
        response.setPointShortLimit(outputFct039Dto.getExpiredDate());

        // ポイント.ポイント表示エリア表示可否
        response.setPointDisplayAreaAvailability(outputFct039Dto.getPointDisplayAreaAvailability());

        // ポイント.ポイント名表示可否
        response.setPointNameDisplayAvailability(outputFct039Dto.getPointNameDisplayAvailability());

        // ポイント.ポイント数表示可否
        response.setPointNumberDisplayAvailability(outputFct039Dto.getPointNumberDisplayAvailability());

        // ポイント.うち期間固定ポイント表示可否
        response.setFixedTermPointDisplayAvailability(outputFct039Dto.getFixedTermPointDisplayAvailability());

        // ポイント.最短有効期限表示可否
        response.setPointShortLimitDisplayAvailability(outputFct039Dto.getPointShortLimitDisplayAvailability());

        // 画面メッセージ
        ErrorResponseDto errorDto = new ErrorResponseDto();
        if (!StringUtil.isNullOrEmpty(outputFct039Dto.getScreenMessage())) {
            errorDto.setErrorFlg(true);
            errorDto.setErrorMessage(outputFct039Dto.getScreenMessage());
            errorDto.setErrorMessageId(WARNIGNS_REFERPOINT_SYSTEMERROR);
        }

        return errorDto;
    }
    
    /**
     * FCT001チェック

     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return チェック結果
     */
    private ErrorResponseDto callFct001(CustomerCommon cc) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        
        OutputFct001Dto output = fct001.doCheck(input);
        ErrorResponseDto result = new ErrorResponseDto();
        boolean resultCheck = false;
        if (output == null) {
            resultCheck = true;
        } else {
            resultCheck = StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId());
        }
        
        if (resultCheck) {
            result.setErrorFlg(true);
            result.setErrorMessageId(ERRORS_BUTENACCOUNTNOTEXIST);
            result.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                    new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        
        return result;
    }
    
    /**
     * API001呼び出し処理

     * @param dtoReq A001リクエストDTO
     * @return API出力結果
     */
    private QueryAccountBalanceOutData queryAccountBalance(CustomerCommon cc) throws Exception {
        
        QueryAccountBalanceIn input = new QueryAccountBalanceIn();
        QueryAccountBalanceInData inData = new QueryAccountBalanceInData();
        // 部店コード
        inData.setButenCd(cc.getButenCode());
        // 口座番号
        inData.setKozaNo(createApiRequestAccountNo(cc.getAccountNumber()));
        
        input.setIndata(inData);
        
        return apiWrapper.queryAccountBalance(input);
    }
    
    /**
     * レスポンス項目設定（単純設定）

     * @param response 返却Dto
     * @param api001OutData API001処理結果
     * @param checkDto API001返却Dtoチェック結果
     */
    private void editResponseColumnsDirects(IfaBuyingPowerDomesticCommonDtoResponse response,
            QueryAccountBalanceOutData api001OutData, CheckExistsApiResponseDto checkDto) {
        
        // MRF・預り金・信用保証金
        response.setTotalOfMrfDpositMargin(api001OutData.getMrf());
        // 入金額
        response.setDepositAmount(api001OutData.getCashReceipt());
        // 合計額
        response.setTotalAmount(api001OutData.getTotalBalance());
        // ジュニアNISA口座(MRF・預り金・信用保証金)
        response.setNISATotalOfMrfDpositMargin(api001OutData.getMrfJrnisa());
        // ジュニアNISA口座(入金額)
        response.setNISADepositAmount(api001OutData.getCashReceiptJrnisa());
        // ジュニアNISA口座(合計額)
        response.setNISATotalAmount(api001OutData.getTotalBalanceJrnisa());
        // 当日（預り金(MRF含む)）
        response.setTodayDepositIncludingMrf(api001OutData.getMrf());
        // 当日（保証金現金）
        response.setTodayMargin(api001OutData.getSecurityCash());
        // 当日（預り金）
        response.setTodayYenDeposit(api001OutData.getKeepCash());
        // 当日(JrNISA)（預り金(MRF含む)）
        response.setJrNisaDepositIncludingMrf(api001OutData.getMrfJrnisa());
        
        // 各オブジェクト配下の設定値を格納
        if (checkDto.isExistsT0()) {
            Long tempUnsettledBuyTotal = Long.valueOf(api001OutData.getT0().getUnsettledBuyTotal()) * -1;
            Long tempUnexecPayBgLossTotal = Long.valueOf(api001OutData.getT0().getUnexecPayBgLossTotal()) * -1;
            Long tempOpenBuyOrderTotal = Long.valueOf(api001OutData.getT0().getOpenBuyOrderTotal()) * -1;
            Long tempCashPaymentTotal = Long.valueOf(api001OutData.getT0().getCashPaymentTotal()) * -1;
            Long tempDaytradeTotal = Long.valueOf(api001OutData.getT0().getDaytradeTotal()) * -1;
            Long tempSettlementTotal = Long.valueOf(api001OutData.getT0().getSettlementTotal()) * -1;

            // 当日（受渡日）
            response.setTodaySettlementDate(api001OutData.getT0().getSettlementDateT());
            // 当日（入金額）
            response.setTodayDepositAmount(api001OutData.getT0().getCashReceiptTotal());
            // 当日（支払額）
            response.setTodayPayment(tempUnsettledBuyTotal.toString());
            // 当日（未約定信用決済損）
            response.setTodayUncontractedCreditSettlementLoss(tempUnexecPayBgLossTotal.toString());
            // 当日（未約定買注文額）
            response.setTodayUncontractedPurchaseOrderAmount(tempOpenBuyOrderTotal.toString());
            // 当日（出金・振替指示額）
            response.setTodayWithdrawalTransferInstructions(tempCashPaymentTotal.toString());
            // 当日（受取額）
            response.setTodayAmountReceived(api001OutData.getT0().getUnsettledSellTotal());
            // 当日（受取額（日計り分））
            response.setTodayDayTrading(tempDaytradeTotal.toString());
            // 当日(SBIハイブリッド預金精算額)
            response.setTodaySbiHybridDepositSettlementAmount(api001OutData.getT0().getSettlementBkTotal());
            // 当日（残高合計額）
            response.setTodayTotalBalance(api001OutData.getT0().getAccountTotal());
            // 当日(残高（当社）)
            response.setTodayOurCompanyTotalBalance(api001OutData.getT0().getSettleEtBalance());
            // 当日(残高(SBIハイブリッド預金))
            response.setTodaySbiHybridDepositSettlementTotalBalance(api001OutData.getT0().getSettleBkBalance());
            // 当日(SBIハイブリッド預金拘束額)
            response.setTodaySbiHybridDepositSettlementRestrictedAmount(
                    api001OutData.getT0().getSettleBkBalanceCashHold());
            // 当日(残高(保証金現金))
            response.setTodayMarginTotalBalance(api001OutData.getT0().getSecurityCashTotal());
            // 当日(残高(預り金))
            response.setTodayDpositTotalBalance(api001OutData.getT0().getKeepCashTotal());
            // 当日（必要精算額、必要保証金額）
            response.setTodayRequiredSettlementAmountYen(tempSettlementTotal.toString());
            // 当日（買付余力）
            response.setTodayYenBuyingPowerGeneralAccount(api001OutData.getT0().getBuyingPowerTotal());
            // １営業日後（保証金現金）
            response.setAfterOnedayMargin(api001OutData.getT0().getSecurityCashTotal());
            // １営業日後（預り金）
            response.setAfterOnedayYenDeposit(api001OutData.getT0().getKeepCashTotal());
        }
        if (checkDto.isExistsT1()) {
            
            Long tempUnsettledBuyTotal = Long.valueOf(api001OutData.getT1().getUnsettledBuyTotal()) * -1;
            Long tempUnexecPayBgLossTotal = Long.valueOf(api001OutData.getT1().getUnexecPayBgLossTotal()) * -1;
            Long tempOpenBuyOrderTotal = Long.valueOf(api001OutData.getT1().getOpenBuyOrderTotal()) * -1;
            Long tempCashPaymentTotal = Long.valueOf(api001OutData.getT1().getCashPaymentTotal()) * -1;
            Long tempDaytradeTotal = Long.valueOf(api001OutData.getT1().getDaytradeTotal()) * -1;
            Long tempSettlementTotal = Long.valueOf(api001OutData.getT1().getSettlementTotal()) * -1;

            // １営業日後（受渡日）
            response.setAfterOnedaySettlementDate(api001OutData.getT1().getSettlementDateT());
            // １営業日後（入金額）
            response.setAfterOnedayDepositAmount(api001OutData.getT1().getCashReceiptTotal());
            // １営業日後（支払額）
            response.setAfterOnedayPayment(tempUnsettledBuyTotal.toString());
            // １営業日後（未約定信用決済損）
            response.setAfterOnedayUncontractedCreditSettlementLoss(tempUnexecPayBgLossTotal.toString());
            // １営業日後（未約定買注文額）
            response.setAfterOnedayUncontractedPurchaseOrderAmount(tempOpenBuyOrderTotal.toString());
            // １営業日後（出金・振替指示額）
            response.setAfterOnedayWithdrawalTransferInstructions(tempCashPaymentTotal.toString());
            // １営業日後（受取額）
            response.setAfterOnedayAmountReceived(api001OutData.getT1().getUnsettledSellTotal());
            // １営業日後（受取額（日計り分））
            response.setAfterOnedayDayTrading(tempDaytradeTotal.toString());
            // １営業日後（SBIハイブリッド預金精算額)
            response.setAfterOnedaySbiHybridDepositSettlementAmount(api001OutData.getT1().getSettlementBkTotal());
            // １営業日後（残高合計額）
            response.setAfterOnedayTotalBalance(api001OutData.getT1().getAccountTotal());
            // １営業日後（残高（当社）)
            response.setAfterOnedayOurCompanyTotalBalance(api001OutData.getT1().getSettleEtBalance());
            // １営業日後（残高(SBIハイブリッド預金))
            response.setAfterOnedaySbiHybridDepositSettlementTotalBalance(api001OutData.getT1().getSettleBkBalance());
            // １営業日後（SBIハイブリッド預金拘束額)
            response.setAfterOnedaySbiHybridDepositSettlementRestrictedAmount(
                    api001OutData.getT1().getSettleBkBalanceCashHold());
            // １営業日後（残高(保証金現金))
            response.setAfterOnedayMarginTotalBalance(api001OutData.getT1().getSecurityCashTotal());
            // １営業日後（残高(預り金))
            response.setAfterOnedayDpositTotalBalance(api001OutData.getT1().getKeepCashTotal());
            // １営業日後（必要精算額、必要保証金額）
            response.setAfterOnedayRequiredSettlementAmountYen(tempSettlementTotal.toString());
            // １営業日後（買付余力）
            response.setAfterOnedayYenBuyingPowerGeneralAccount(api001OutData.getT1().getBuyingPowerTotal());
            // ２営業日後（保証金現金）
            response.setAfterTwodaysMargin(api001OutData.getT1().getSecurityCashTotal());
            // ２営業日後（預り金）
            response.setAfterTwodaysYenDeposit(api001OutData.getT1().getKeepCashTotal());
        }
        if (checkDto.isExistsT2()) {
            Long tempUnsettledBuyTotal = Long.valueOf(api001OutData.getT2().getUnsettledBuyTotal()) * -1;
            Long tempUnexecPayBgLossTotal = Long.valueOf(api001OutData.getT2().getUnexecPayBgLossTotal()) * -1;
            Long tempOpenBuyOrderTotal = Long.valueOf(api001OutData.getT2().getOpenBuyOrderTotal()) * -1;
            Long tempCashPaymentTotal = Long.valueOf(api001OutData.getT2().getCashPaymentTotal()) * -1;
            Long tempDaytradeTotal = Long.valueOf(api001OutData.getT2().getDaytradeTotal()) * -1;
            Long tempSettlementTotal = Long.valueOf(api001OutData.getT2().getSettlementTotal()) * -1;

            // 買付余力一覧.受渡日(T+2)
            response.setSettlementDateT2(api001OutData.getT2().getSettlementDateT());
            // 買付余力一覧.買付余力(T+2)
            response.setYenBuyingPowerGeneralAccountT2(api001OutData.getT2().getBuyingPowerTotal());
            // ２営業日後（受渡日）
            response.setAfterTwodaysSettlementDate(api001OutData.getT2().getSettlementDateT());
            // ２営業日後（入金額）
            response.setAfterTwodaysDepositAmount(api001OutData.getT2().getCashReceiptTotal());
            // ２営業日後（支払額）
            response.setAfterTwodaysPayment(tempUnsettledBuyTotal.toString());
            // ２営業日後（未約定信用決済損）
            response.setAfterTwodaysUncontractedCreditSettlementLoss(tempUnexecPayBgLossTotal.toString());
            // ２営業日後（未約定買注文額）
            response.setAfterTwodaysUncontractedPurchaseOrderAmount(tempOpenBuyOrderTotal.toString());
            // ２営業日後（出金・振替指示額）
            response.setAfterTwodaysWithdrawalTransferInstructions(tempCashPaymentTotal.toString());
            // ２営業日後（受取額）
            response.setAfterTwodaysAmountReceived(api001OutData.getT2().getUnsettledSellTotal());
            // ２営業日後（受取額（日計り分））
            response.setAfterTwodaysDayTrading(tempDaytradeTotal.toString());
            // ２営業日後（SBIハイブリッド預金精算額)
            response.setAfterTwodaysSbiHybridDepositSettlementAmount(api001OutData.getT2().getSettlementBkTotal());
            // ２営業日後（残高合計額）
            response.setAfterTwodaysTotalBalance(api001OutData.getT2().getAccountTotal());
            // ２営業日後（残高（当社）)
            response.setAfterTwodaysOurCompanyTotalBalance(api001OutData.getT2().getSettleEtBalance());
            // ２営業日後（残高(SBIハイブリッド預金))
            response.setAfterTwodaysSbiHybridDepositSettlementTotalBalance(api001OutData.getT2().getSettleBkBalance());
            // ２営業日後（SBIハイブリッド預金拘束額)
            response.setAfterTwodaysSbiHybridDepositSettlementRestrictedAmount(
                    api001OutData.getT2().getSettleBkBalanceCashHold());
            // ２営業日後（残高(保証金現金))
            response.setAfterTwodaysMarginTotalBalance(api001OutData.getT2().getSecurityCashTotal());
            // ２営業日後（残高(預り金))
            response.setAfterTwodaysDpositTotalBalance(api001OutData.getT2().getKeepCashTotal());
            // ２営業日後（必要精算額、必要保証金額）
            response.setAfterTwodaysRequiredSettlementAmountYen(tempSettlementTotal.toString());
            // ２営業日後（買付余力）
            response.setAfterTwodaysYenBuyingPowerGeneralAccount(api001OutData.getT2().getBuyingPowerTotal());
            // ３営業日後（保証金現金）
            response.setAfterThreedaysMargin(api001OutData.getT2().getSecurityCashTotal());
            // ３営業日後（預り金）
            response.setAfterThreedaysYenDeposit(api001OutData.getT2().getKeepCashTotal());
            
        }
        if (checkDto.isExistsT3()) {
            Long tempUnsettledBuyTotal = Long.valueOf(api001OutData.getT3().getUnsettledBuyTotal()) * -1;
            Long tempUnexecPayBgLossTotal = Long.valueOf(api001OutData.getT3().getUnexecPayBgLossTotal()) * -1;
            Long tempOpenBuyOrderTotal = Long.valueOf(api001OutData.getT3().getOpenBuyOrderTotal()) * -1;
            Long tempCashPaymentTotal = Long.valueOf(api001OutData.getT3().getCashPaymentTotal()) * -1;
            Long tempDaytradeTotal = Long.valueOf(api001OutData.getT3().getDaytradeTotal()) * -1;
            Long tempSettlementTotal = Long.valueOf(api001OutData.getT3().getSettlementTotal()) * -1;

            // 買付余力一覧.受渡日(T+3)
            response.setSettlementDateT3(api001OutData.getT3().getSettlementDateT());
            // 買付余力一覧.買付余力(T+3)
            response.setYenBuyingPowerGeneralAccountT3(api001OutData.getT3().getBuyingPowerTotal());
            // ３営業日後（受渡日）
            response.setAfterThreedaysSettlementDate(api001OutData.getT3().getSettlementDateT());
            // ３営業日後（入金額）
            response.setAfterThreedaysDepositAmount(api001OutData.getT3().getCashReceiptTotal());
            // ３営業日後（支払額）
            response.setAfterThreedaysPayment(tempUnsettledBuyTotal.toString());
            // ３営業日後（未約定信用決済損）
            response.setAfterThreedaysUncontractedCreditSettlementLoss(tempUnexecPayBgLossTotal.toString());
            // ３営業日後（未約定買注文額）
            response.setAfterThreedaysUncontractedPurchaseOrderAmount(tempOpenBuyOrderTotal.toString());
            // ３営業日後（出金・振替指示額）
            response.setAfterThreedaysWithdrawalTransferInstructions(tempCashPaymentTotal.toString());
            // ３営業日後（受取額）
            response.setAfterThreedaysAmountReceived(api001OutData.getT3().getUnsettledSellTotal());
            // ３営業日後（受取額（日計り分））
            response.setAfterThreedaysDayTrading(tempDaytradeTotal.toString());
            // ３営業日後（SBIハイブリッド預金精算額)
            response.setAfterThreedaysSbiHybridDepositSettlementAmount(api001OutData.getT3().getSettlementBkTotal());
            // ３営業日後（残高合計額）
            response.setAfterThreedaysTotalBalance(api001OutData.getT3().getAccountTotal());
            // ３営業日後（残高（当社）)
            response.setAfterThreedaysOurCompanyTotalBalance(api001OutData.getT3().getSettleEtBalance());
            // ３営業日後（残高(SBIハイブリッド預金))
            response.setAfterThreedaysSbiHybridDepositSettlementTotalBalance(
                    api001OutData.getT3().getSettleBkBalance());
            // ３営業日後（SBIハイブリッド預金拘束額)
            response.setAfterThreedaysSbiHybridDepositSettlementRestrictedAmount(
                    api001OutData.getT3().getSettleBkBalanceCashHold());
            // ３営業日後（残高(保証金現金))
            response.setAfterThreedaysMarginTotalBalance(api001OutData.getT3().getSecurityCashTotal());
            // ３営業日後（残高(預り金))
            response.setAfterThreedaysDpositTotalBalance(api001OutData.getT3().getKeepCashTotal());
            // ３営業日後（必要精算額、必要保証金額）
            response.setAfterThreedaysRequiredSettlementAmountYen(tempSettlementTotal.toString());
            // ３営業日後（買付余力）
            response.setAfterThreedaysYenBuyingPowerGeneralAccount(api001OutData.getT3().getBuyingPowerTotal());
            // ４営業日後（保証金現金）
            response.setAfterFourdaysMargin(api001OutData.getT3().getSecurityCashTotal());
            // ４営業日後（預り金）
            response.setAfterFourdaysYenDeposit(api001OutData.getT3().getKeepCashTotal());
        }
        if (checkDto.isExistsT4()) {
            Long tempUnsettledBuyTotal = Long.valueOf(api001OutData.getT4().getUnsettledBuyTotal()) * -1;
            Long tempUnexecPayBgLossTotal = Long.valueOf(api001OutData.getT4().getUnexecPayBgLossTotal()) * -1;
            Long tempOpenBuyOrderTotal = Long.valueOf(api001OutData.getT4().getOpenBuyOrderTotal()) * -1;
            Long tempCashPaymentTotal = Long.valueOf(api001OutData.getT4().getCashPaymentTotal()) * -1;
            Long tempDaytradeTotal = Long.valueOf(api001OutData.getT4().getDaytradeTotal()) * -1;
            Long tempSettlementTotal = Long.valueOf(api001OutData.getT4().getSettlementTotal()) * -1;

            // 買付余力一覧.受渡日(T+4)
            response.setSettlementDateT4(api001OutData.getT4().getSettlementDateT());
            // 買付余力一覧.買付余力(T+4)
            response.setYenBuyingPowerGeneralAccountT4(api001OutData.getT4().getBuyingPowerTotal());
            // ４営業日後（受渡日）
            response.setAfterFourdaysSettlementDate(api001OutData.getT4().getSettlementDateT());
            // ４営業日後（入金額）
            response.setAfterFourdaysDepositAmount(api001OutData.getT4().getCashReceiptTotal());
            // ４営業日後（支払額）
            response.setAfterFourdaysPayment(tempUnsettledBuyTotal.toString());
            // ４営業日後（未約定信用決済損）
            response.setAfterFourdaysUncontractedCreditSettlementLoss(tempUnexecPayBgLossTotal.toString());
            // ４営業日後（未約定買注文額）
            response.setAfterFourdaysUncontractedPurchaseOrderAmount(tempOpenBuyOrderTotal.toString());
            // ４営業日後（出金・振替指示額）
            response.setAfterFourdaysWithdrawalTransferInstructions(tempCashPaymentTotal.toString());
            // ４営業日後（受取額）
            response.setAfterFourdaysAmountReceived(api001OutData.getT4().getUnsettledSellTotal());
            // ４営業日後（受取額（日計り分））
            response.setAfterFourdaysDayTrading(tempDaytradeTotal.toString());
            // ４営業日後（SBIハイブリッド預金精算額)
            response.setAfterFourdaysSbiHybridDepositSettlementAmount(api001OutData.getT4().getSettlementBkTotal());
            // ４営業日後（残高合計額）
            response.setAfterFourdaysTotalBalance(api001OutData.getT4().getAccountTotal());
            // ４営業日後（残高（当社）)
            response.setAfterFourdaysOurCompanyTotalBalance(api001OutData.getT4().getSettleEtBalance());
            // ４営業日後（残高(SBIハイブリッド預金))
            response.setAfterFourdaysSbiHybridDepositSettlementTotalBalance(api001OutData.getT4().getSettleBkBalance());
            // ４営業日後（SBIハイブリッド預金拘束額)
            response.setAfterFourdaysSbiHybridDepositSettlementRestrictedAmount(
                    api001OutData.getT4().getSettleBkBalanceCashHold());
            // ４営業日後（残高(保証金現金))
            response.setAfterFourdaysMarginTotalBalance(api001OutData.getT4().getSecurityCashTotal());
            // ４営業日後（残高(預り金))
            response.setAfterFourdaysDpositTotalBalance(api001OutData.getT4().getKeepCashTotal());
            // ４営業日後（必要精算額、必要保証金額）
            response.setAfterFourdaysRequiredSettlementAmountYen(tempSettlementTotal.toString());
            // ４営業日後（買付余力）
            response.setAfterFourdaysYenBuyingPowerGeneralAccount(api001OutData.getT4().getBuyingPowerTotal());
            // ５営業日後（保証金現金）
            response.setAfterFivedaysMargin(api001OutData.getT4().getSecurityCashTotal());
            // ５営業日後（預り金）
            response.setAfterFivedaysYenDeposit(api001OutData.getT4().getKeepCashTotal());
        }
        if (checkDto.isExistsT5()) {
            Long tempUnsettledBuyTotal = Long.valueOf(api001OutData.getT5().getUnsettledBuyTotal()) * -1;
            Long tempUnexecPayBgLossTotal = Long.valueOf(api001OutData.getT5().getUnexecPayBgLossTotal()) * -1;
            Long tempOpenBuyOrderTotal = Long.valueOf(api001OutData.getT5().getOpenBuyOrderTotal()) * -1;
            Long tempCashPaymentTotal = Long.valueOf(api001OutData.getT5().getCashPaymentTotal()) * -1;
            Long tempDaytradeTotal = Long.valueOf(api001OutData.getT5().getDaytradeTotal()) * -1;
            Long tempSettlementTotal = Long.valueOf(api001OutData.getT5().getSettlementTotal()) * -1;

            // ５営業日後（受渡日）
            response.setAfterFivedaysSettlementDate(api001OutData.getT5().getSettlementDateT());
            // ５営業日後（入金額）
            response.setAfterFivedaysDepositAmount(api001OutData.getT5().getCashReceiptTotal());
            // ５営業日後（支払額）
            response.setAfterFivedaysPayment(tempUnsettledBuyTotal.toString());
            // ５営業日後（未約定信用決済損）
            response.setAfterFivedaysUncontractedCreditSettlementLoss(tempUnexecPayBgLossTotal.toString());
            // ５営業日後（未約定買注文額）
            response.setAfterFivedaysUncontractedPurchaseOrderAmount(tempOpenBuyOrderTotal.toString());
            // ５営業日後（出金・振替指示額）
            response.setAfterFivedaysWithdrawalTransferInstructions(tempCashPaymentTotal.toString());
            // ５営業日後（受取額）
            response.setAfterFivedaysAmountReceived(api001OutData.getT5().getUnsettledSellTotal());
            // ５営業日後（受取額（日計り分））
            response.setAfterFivedaysDayTrading(tempDaytradeTotal.toString());
            // ５営業日後（SBIハイブリッド預金精算額)
            response.setAfterFivedaysSbiHybridDepositSettlementAmount(api001OutData.getT5().getSettlementBkTotal());
            // ５営業日後（残高合計額）
            response.setAfterFivedaysTotalBalance(api001OutData.getT5().getAccountTotal());
            // ５営業日後（残高（当社）)
            response.setAfterFivedaysOurCompanyTotalBalance(api001OutData.getT5().getSettleEtBalance());
            // ５営業日後（残高(SBIハイブリッド預金))
            response.setAfterFivedaysSbiHybridDepositSettlementTotalBalance(api001OutData.getT5().getSettleBkBalance());
            // ５営業日後（SBIハイブリッド預金拘束額)
            response.setAfterFivedaysSbiHybridDepositSettlementRestrictedAmount(
                    api001OutData.getT5().getSettleBkBalanceCashHold());
            // ５営業日後（残高(保証金現金))
            response.setAfterFivedaysMarginTotalBalance(api001OutData.getT5().getSecurityCashTotal());
            // ５営業日後（残高(預り金))
            response.setAfterFivedaysDpositTotalBalance(api001OutData.getT5().getKeepCashTotal());
            // ５営業日後（必要精算額、必要保証金額）
            response.setAfterFivedaysRequiredSettlementAmountYen(tempSettlementTotal.toString());
            // ５営業日後（買付余力）
            response.setAfterFivedaysYenBuyingPowerGeneralAccount(api001OutData.getT5().getBuyingPowerTotal());
        }
        if (checkDto.isExistsT0Jr()) {
            Long tempUnsettledBuyTotalJrnisa = Long.valueOf(api001OutData.getT0Jr().getUnsettledBuyTotalJrnisa()) * -1;
            Long tempOpenBuyOrderTotalJrnisa = Long.valueOf(api001OutData.getT0Jr().getOpenBuyOrderTotalJrnisa()) * -1;
            Long tempCashPaymentTotalJrnisa = Long.valueOf(api001OutData.getT0Jr().getCashPaymentTotalJrnisa()) * -1;
            Long tempDaytradeTotalJrnisa = Long.valueOf(api001OutData.getT0Jr().getDaytradeTotalJrnisa()) * -1;
            Long tempSettlementTotalJrnisa = Long.valueOf(api001OutData.getT0Jr().getSettlementTotalJrnisa()) * -1;

            // 当日(JrNISA)（受渡日）
            response.setJrNisaSettlementDate(api001OutData.getT0Jr().getSettlementDateTJrnisa());
            // 当日(JrNISA)（入金額）
            response.setJrNisaDepositAmount(api001OutData.getT0Jr().getCashReceiptTotalJrnisa());
            // 当日(JrNISA)（支払額）
            response.setJrNisaPayment(tempUnsettledBuyTotalJrnisa.toString());
            // 当日(JrNISA)（未約定買注文額）
            response.setJrNisaUncontractedPurchaseOrderAmount(tempOpenBuyOrderTotalJrnisa.toString());
            // 当日(JrNISA)（出金・振替指示額）
            response.setJrNisaWithdrawalTransferInstructions(tempCashPaymentTotalJrnisa.toString());
            // 当日(JrNISA)（受取額）
            response.setJrNisaAmountReceived(api001OutData.getT0Jr().getUnsettledSellTotalJrnisa());
            // 当日(JrNISA)（受取額（日計り分））
            response.setJrNisaDayTrading(tempDaytradeTotalJrnisa.toString());
            // 当日(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）)
            response.setJrNisaPlannedTransferAmount(api001OutData.getT0Jr().getTransJrnisaAmount());
            // 当日(JrNISA)(残高合計額)
            response.setJrNisaTotalBalance(api001OutData.getT0Jr().getAccountTotalJrnisa());
            // 当日(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）)
            response.setJrNisaTransferableAmount(api001OutData.getT0Jr().getTransJrnisaAmountLimit());
            // 当日(JrNISA)（必要精算額、必要保証金額）
            response.setJrNisaRequiredSettlementAmountYen(tempSettlementTotalJrnisa.toString());
            // 当日(JrNISA)（買付余力）
            response.setJrNisaYenBuyingPowerGeneralAccount(api001OutData.getT0Jr().getBuyingPowerTotalJrnisa());
        }
        if (checkDto.isExistsT1Jr()) {
            Long tempUnsettledBuyTotalJrnisa = Long.valueOf(api001OutData.getT1Jr().getUnsettledBuyTotalJrnisa()) * -1;
            Long tempOpenBuyOrderTotalJrnisa = Long.valueOf(api001OutData.getT1Jr().getOpenBuyOrderTotalJrnisa()) * -1;
            Long tempCashPaymentTotalJrnisa = Long.valueOf(api001OutData.getT1Jr().getCashPaymentTotalJrnisa()) * -1;
            Long tempDaytradeTotalJrnisa = Long.valueOf(api001OutData.getT1Jr().getDaytradeTotalJrnisa()) * -1;
            Long tempSettlementTotalJrnisa = Long.valueOf(api001OutData.getT1Jr().getSettlementTotalJrnisa()) * -1;

            // １営業日後(JrNISA)（受渡日）
            response.setAfterOnedayJrNisaSettlementDate(api001OutData.getT1Jr().getSettlementDateTJrnisa());
            // １営業日後(JrNISA)（入金額）
            response.setAfterOnedayJrNisaDepositAmount(api001OutData.getT1Jr().getCashReceiptTotalJrnisa());
            // １営業日後(JrNISA)（支払額）
            response.setAfterOnedayJrNisaPayment(tempUnsettledBuyTotalJrnisa.toString());
            // １営業日後(JrNISA)（未約定買注文額）
            response.setAfterOnedayJrNisaUncontractedPurchaseOrderAmount(
                    tempOpenBuyOrderTotalJrnisa.toString());
            // １営業日後(JrNISA)（出金・振替指示額）
            response.setAfterOnedayJrNisaWithdrawalTransferInstructions(
                    tempCashPaymentTotalJrnisa.toString());
            // １営業日後(JrNISA)（受取額）
            response.setAfterOnedayJrNisaAmountReceived(api001OutData.getT1Jr().getUnsettledSellTotalJrnisa());
            // １営業日後(JrNISA)（受取額（日計り分））
            response.setAfterOnedayJrNisaDayTrading(tempDaytradeTotalJrnisa.toString());
            // １営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）)
            response.setAfterOnedayJrNisaPlannedTransferAmount(api001OutData.getT1Jr().getTransJrnisaAmount());
            // １営業日後(JrNISA)（残高合計額）
            response.setAfterOnedayJrNisaTotalBalance(api001OutData.getT1Jr().getAccountTotalJrnisa());
            // １営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）)
            response.setAfterOnedayJrNisaTransferableAmount(api001OutData.getT1Jr().getTransJrnisaAmountLimit());
            // １営業日後(JrNISA)（必要精算額、必要保証金額）
            response.setAfterOnedayJrNisaRequiredSettlementAmountYen(
                    tempSettlementTotalJrnisa.toString());
            // １営業日後(JrNISA)（買付余力）
            response.setAfterOnedayJrNisaYenBuyingPowerGeneralAccount(
                    api001OutData.getT1Jr().getBuyingPowerTotalJrnisa());
        }
        if (checkDto.isExistsT2Jr()) {
            Long tempUnsettledBuyTotalJrnisa = Long.valueOf(api001OutData.getT2Jr().getUnsettledBuyTotalJrnisa()) * -1;
            Long tempOpenBuyOrderTotalJrnisa = Long.valueOf(api001OutData.getT2Jr().getOpenBuyOrderTotalJrnisa()) * -1;
            Long tempCashPaymentTotalJrnisa = Long.valueOf(api001OutData.getT2Jr().getCashPaymentTotalJrnisa()) * -1;
            Long tempDaytradeTotalJrnisa = Long.valueOf(api001OutData.getT2Jr().getDaytradeTotalJrnisa()) * -1;
            Long tempSettlementTotalJrnisa = Long.valueOf(api001OutData.getT2Jr().getSettlementTotalJrnisa()) * -1;


            // 買付余力一覧.買付余力（ジュニアNISA口座(T+2)
            response.setYenBuyingPowerJrNisaT2(api001OutData.getT2Jr().getBuyingPowerTotalJrnisa());
            // ２営業日後(JrNISA)（受渡日）
            response.setAfterTwodaysJrNisaSettlementDate(api001OutData.getT2Jr().getSettlementDateTJrnisa());
            // ２営業日後(JrNISA)（入金額）
            response.setAfterTwodaysJrNisaDepositAmount(api001OutData.getT2Jr().getCashReceiptTotalJrnisa());
            // ２営業日後(JrNISA)（支払額）
            response.setAfterTwodaysJrNisaPayment(tempUnsettledBuyTotalJrnisa.toString());
            // ２営業日後(JrNISA)（未約定買注文額）
            response.setAfterTwodaysJrNisaUncontractedPurchaseOrderAmount(
                    tempOpenBuyOrderTotalJrnisa.toString());
            // ２営業日後(JrNISA)（出金・振替指示額）
            response.setAfterTwodaysJrNisaWithdrawalTransferInstructions(
                    tempCashPaymentTotalJrnisa.toString());
            // ２営業日後(JrNISA)（受取額）
            response.setAfterTwodaysJrNisaAmountReceived(api001OutData.getT2Jr().getUnsettledSellTotalJrnisa());
            // ２営業日後(JrNISA)（受取額（日計り分））
            response.setAfterTwodaysJrNisaDayTrading(tempDaytradeTotalJrnisa.toString());
            // ２営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）)
            response.setAfterTwodaysJrNisaPlannedTransferAmount(api001OutData.getT2Jr().getTransJrnisaAmount());
            // ２営業日後(JrNISA)（残高合計額）
            response.setAfterTwodaysJrNisaTotalBalance(api001OutData.getT2Jr().getAccountTotalJrnisa());
            // ２営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）)
            response.setAfterTwodaysJrNisaTransferableAmount(api001OutData.getT2Jr().getTransJrnisaAmountLimit());
            // ２営業日後(JrNISA)（必要精算額、必要保証金額）
            response.setAfterTwodaysJrNisaRequiredSettlementAmountYen(
                    tempSettlementTotalJrnisa.toString());
            // ２営業日後(JrNISA)（買付余力）
            response.setAfterTwodaysJrNisaYenBuyingPowerGeneralAccount(
                    api001OutData.getT2Jr().getBuyingPowerTotalJrnisa());
        }
        if (checkDto.isExistsT3Jr()) {
            Long tempUnsettledBuyTotalJrnisa = Long.valueOf(api001OutData.getT3Jr().getUnsettledBuyTotalJrnisa()) * -1;
            Long tempOpenBuyOrderTotalJrnisa = Long.valueOf(api001OutData.getT3Jr().getOpenBuyOrderTotalJrnisa()) * -1;
            Long tempCashPaymentTotalJrnisa = Long.valueOf(api001OutData.getT3Jr().getCashPaymentTotalJrnisa()) * -1;
            Long tempDaytradeTotalJrnisa = Long.valueOf(api001OutData.getT3Jr().getDaytradeTotalJrnisa()) * -1;
            Long tempSettlementTotalJrnisa = Long.valueOf(api001OutData.getT3Jr().getSettlementTotalJrnisa()) * -1;

            // 買付余力一覧.買付余力（ジュニアNISA口座(T+3)
            response.setYenBuyingPowerJrNisaT3(api001OutData.getT3Jr().getBuyingPowerTotalJrnisa());
            // ３営業日後(JrNISA)（受渡日）
            response.setAfterThreedaysJrNisaSettlementDate(api001OutData.getT3Jr().getSettlementDateTJrnisa());
            // ３営業日後(JrNISA)（入金額）
            response.setAfterThreedaysJrNisaDepositAmount(api001OutData.getT3Jr().getCashReceiptTotalJrnisa());
            // ３営業日後(JrNISA)（支払額）
            response.setAfterThreedaysJrNisaPayment(tempUnsettledBuyTotalJrnisa.toString());
            // ３営業日後(JrNISA)（未約定買注文額）
            response.setAfterThreedaysJrNisaUncontractedPurchaseOrderAmount(
                    tempOpenBuyOrderTotalJrnisa.toString());
            // ３営業日後(JrNISA)（出金・振替指示額）
            response.setAfterThreedaysJrNisaWithdrawalTransferInstructions(
                    tempCashPaymentTotalJrnisa.toString());
            // ３営業日後(JrNISA)（受取額）
            response.setAfterThreedaysJrNisaAmountReceived(api001OutData.getT3Jr().getUnsettledSellTotalJrnisa());
            // ３営業日後(JrNISA)（受取額（日計り分））
            response.setAfterThreedaysJrNisaDayTrading(tempDaytradeTotalJrnisa.toString());
            // ３営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）)
            response.setAfterThreedaysJrNisaPlannedTransferAmount(api001OutData.getT3Jr().getTransJrnisaAmount());
            // ３営業日後(JrNISA)（残高合計額）
            response.setAfterThreedaysJrNisaTotalBalance(api001OutData.getT3Jr().getAccountTotalJrnisa());
            // ３営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）)
            response.setAfterThreedaysJrNisaTransferableAmount(api001OutData.getT3Jr().getTransJrnisaAmountLimit());
            // ３営業日後(JrNISA)（必要精算額、必要保証金額）
            response.setAfterThreedaysJrNisaRequiredSettlementAmountYen(
                    tempSettlementTotalJrnisa.toString());
            // ３営業日後(JrNISA)（買付余力）
            response.setAfterThreedaysJrNisaYenBuyingPowerGeneralAccount(
                    api001OutData.getT3Jr().getBuyingPowerTotalJrnisa());
        }
        if (checkDto.isExistsT4Jr()) {
            Long tempUnsettledBuyTotalJrnisa = Long.valueOf(api001OutData.getT4Jr().getUnsettledBuyTotalJrnisa()) * -1;
            Long tempOpenBuyOrderTotalJrnisa = Long.valueOf(api001OutData.getT4Jr().getOpenBuyOrderTotalJrnisa()) * -1;
            Long tempCashPaymentTotalJrnisa = Long.valueOf(api001OutData.getT4Jr().getCashPaymentTotalJrnisa()) * -1;
            Long tempDaytradeTotalJrnisa = Long.valueOf(api001OutData.getT4Jr().getDaytradeTotalJrnisa()) * -1;
            Long tempSettlementTotalJrnisa = Long.valueOf(api001OutData.getT4Jr().getSettlementTotalJrnisa()) * -1;

            // 買付余力一覧.買付余力（ジュニアNISA口座(T+4)
            response.setYenBuyingPowerJrNisaT4(api001OutData.getT4Jr().getBuyingPowerTotalJrnisa());
            // ４営業日後(JrNISA)（受渡日）
            response.setAfterFourdaysJrNisaSettlementDate(api001OutData.getT4Jr().getSettlementDateTJrnisa());
            // ４営業日後(JrNISA)（入金額）
            response.setAfterFourdaysJrNisaDepositAmount(api001OutData.getT4Jr().getCashReceiptTotalJrnisa());
            // ４営業日後(JrNISA)（支払額）
            response.setAfterFourdaysJrNisaPayment(tempUnsettledBuyTotalJrnisa.toString());
            // ４営業日後(JrNISA)（未約定買注文額）
            response.setAfterFourdaysJrNisaUncontractedPurchaseOrderAmount(
                    tempOpenBuyOrderTotalJrnisa.toString());
            // ４営業日後(JrNISA)（出金・振替指示額）
            response.setAfterFourdaysJrNisaWithdrawalTransferInstructions(
                    tempCashPaymentTotalJrnisa.toString());
            // ４営業日後(JrNISA)（受取額）
            response.setAfterFourdaysJrNisaAmountReceived(api001OutData.getT4Jr().getUnsettledSellTotalJrnisa());
            // ４営業日後(JrNISA)（受取額（日計り分））
            response.setAfterFourdaysJrNisaDayTrading(tempDaytradeTotalJrnisa.toString());
            // ４営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）)
            response.setAfterFourdaysJrNisaPlannedTransferAmount(api001OutData.getT4Jr().getTransJrnisaAmount());
            // ４営業日後(JrNISA)（残高合計額）
            response.setAfterFourdaysJrNisaTotalBalance(api001OutData.getT4Jr().getAccountTotalJrnisa());
            // ４営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）)
            response.setAfterFourdaysJrNisaTransferableAmount(api001OutData.getT4Jr().getTransJrnisaAmountLimit());
            // ４営業日後(JrNISA)（必要精算額、必要保証金額）
            response.setAfterFourdaysJrNisaRequiredSettlementAmountYen(
                    tempSettlementTotalJrnisa.toString());
            // ４営業日後(JrNISA)（買付余力）
            response.setAfterFourdaysJrNisaYenBuyingPowerGeneralAccount(
                    api001OutData.getT4Jr().getBuyingPowerTotalJrnisa());
            
        }
        if (checkDto.isExistsT5Jr()) {
            Long tempUnsettledBuyTotalJrnisa = Long.valueOf(api001OutData.getT5Jr().getUnsettledBuyTotalJrnisa()) * -1;
            Long tempOpenBuyOrderTotalJrnisa = Long.valueOf(api001OutData.getT5Jr().getOpenBuyOrderTotalJrnisa()) * -1;
            Long tempCashPaymentTotalJrnisa = Long.valueOf(api001OutData.getT5Jr().getCashPaymentTotalJrnisa()) * -1;
            Long tempDaytradeTotalJrnisa = Long.valueOf(api001OutData.getT5Jr().getDaytradeTotalJrnisa()) * -1;
            Long tempSettlementTotalJrnisa = Long.valueOf(api001OutData.getT5Jr().getSettlementTotalJrnisa()) * -1;

            // ５営業日後(JrNISA)（受渡日）
            response.setAfterFivedaysJrNisaSettlementDate(api001OutData.getT5Jr().getSettlementDateTJrnisa());
            // ５営業日後(JrNISA)（入金額）
            response.setAfterFivedaysJrNisaDepositAmount(api001OutData.getT5Jr().getCashReceiptTotalJrnisa());
            // ５営業日後(JrNISA)（支払額）
            response.setAfterFivedaysJrNisaPayment(tempUnsettledBuyTotalJrnisa.toString());
            // ５営業日後(JrNISA)（未約定買注文額）
            response.setAfterFivedaysJrNisaUncontractedPurchaseOrderAmount(
                    tempOpenBuyOrderTotalJrnisa.toString());
            // ５営業日後(JrNISA)（出金・振替指示額）
            response.setAfterFivedaysJrNisaWithdrawalTransferInstructions(
                    tempCashPaymentTotalJrnisa.toString());
            // ５営業日後(JrNISA)（受取額）
            response.setAfterFivedaysJrNisaAmountReceived(api001OutData.getT5Jr().getUnsettledSellTotalJrnisa());
            // ５営業日後(JrNISA)（受取額（日計り分））
            response.setAfterFivedaysJrNisaDayTrading(tempDaytradeTotalJrnisa.toString());
            // ５営業日後(JrNISA)(ジュニアNISA振替予定額（総合口座→ジュニアNISA口座）)
            response.setAfterFivedaysJrNisaPlannedTransferAmount(api001OutData.getT5Jr().getTransJrnisaAmount());
            // ５営業日後(JrNISA)（残高合計額）
            response.setAfterFivedaysJrNisaTotalBalance(api001OutData.getT5Jr().getAccountTotalJrnisa());
            // ５営業日後(JrNISA)(ジュニアNISA振替可能額（総合口座→ジュニアNISA口座）)
            response.setAfterFivedaysJrNisaTransferableAmount(api001OutData.getT5Jr().getTransJrnisaAmountLimit());
            // ５営業日後(JrNISA)（必要精算額、必要保証金額）
            response.setAfterFivedaysJrNisaRequiredSettlementAmountYen(
                    tempSettlementTotalJrnisa.toString());
            // ５営業日後(JrNISA)（買付余力）
            response.setAfterFivedaysJrNisaYenBuyingPowerGeneralAccount(
                    api001OutData.getT5Jr().getBuyingPowerTotalJrnisa());

        }
    }
    
    /**
     * オブジェクトの存在チェック.

     * @param target Nullチェック対象
     * @return true:存在する　false:存在しない
     */
    private static boolean checkNotNukllObjects(Object target) {
        
        return !Objects.isNull(target);
    }
    
    /**
     * APIリクエスト項目：口座番号設定値作成

     * @param accountNo 口座番号
     * @return 0埋めの口座番号
     */
    private static String createApiRequestAccountNo(String accountNo) {
        
        return String.format("%7s", accountNo).replace(" ", "0");
    }
}
