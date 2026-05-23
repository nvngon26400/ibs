package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto.CancelTargetAccumulateSettingDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto.IfaMutualFundAccumulateSettingCancelListDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto.IfaMutualFundAccumulateSettingCancelConfirmA003RequesDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto.IfaMutualFundAccumulateSettingFinshListDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmSql001ListDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingCancelConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingDBProcessService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.AccumulateCourse;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveGetReserveSettingForBulkUpdateReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveGetReserveSettingForBulkUpdateRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingReleaseReceptReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingReleaseReceptRes;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingReleaseIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingReleaseOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingReleaseReceptApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingReleaseReceptApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingData;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataForBulkUpdate;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListForBulkUpdateApiIn;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil.IfaPaymentMethod;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum;


/**
 * 画面ID：SUB0202_0403-04_1
 * 画面名：投信積立設定解除確認
 *
 * @author WJL
 *     2025/04/11 新規作成
 */
@Component(value = "cmpIfaMutualFundAccumulateSettingCancelConfirmService")
public class IfaMutualFundAccumulateSettingCancelConfirmServiceImpL
        implements IfaMutualFundAccumulateSettingCancelConfirmService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.class);
    

    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private CodeListService codeListService;
    
	@Autowired
    private SafeFundTradeService safeFundTradeService;

    @Autowired
    private IfaMutualFundAccumulateSettingDBProcessService ifaMutualFundAccumulateSettingDBProcessService;

    @Autowired
    private SafeCommonService safeCommonService;
    
    /** 設定解除エラー */
    private static final String SETTING_EXECUTION_CANCEL = "errors.cmn.settingExecutionCancel.failed";
    
    /** 設定解除データなしエラー */
    private static final String SETTING_EXECUTION_CANCEL_NO_DATA = "errors.cmn.settingExecutionCanceled";  
    
    /** SQL001更新 エラー */
    private static final String WARNINGS_CMN_RESERVESETTINGEXECUTIONCANCEL_COMPLETED = "warnings.fnd.ReserveSettingExecutionCancel.completed";
  

    /** CCSID未登録 エラー */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** 預り区分（投信） */
    private static final String RESERVE_TRADE_DEPOSIT_TYPE = "RESERVE_TRADE_DEPOSIT_TYPE";
    
    /** 決済方法（投信積立） */
    private static final String FUND_RESERVE_PAYMENT_METHOD = "FUND_RESERVE_PAYMENT_METHOD";
    
    /** NISA枠ぎりぎり買付区分（投信積立）） */
    private static final String FUND_RESERVE_NISA_BARELY_BUYING_KBN = "FUND_RESERVE_NISA_BARELY_BUYING_KBN";
    
    /** NISA枠超過時買付区分（投信積立） */
    private static final String FUND_RESERVE_TAX_SHIFT_KBN = "FUND_RESERVE_TAX_SHIFT_KBN";
    
    /** 権限チェックエラー  */
    private static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 権限チェックエラー値 */
    private static final String AUTH_ERROR_VALUE = "0";
    
    /** 表示パターン */
    private static final String DISPLAY_PATTERN_1 = "1";
    private static final String DISPLAY_PATTERN_3 = "3";

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dtoリクエスト：IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto> initializeA001(
            IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto dtoReq) throws Exception {
    	
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.initializeA001");
        }
        
        DataList<IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto>();
        List<IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto>();
        IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto resDto = new IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto();
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        String butenCode = Optional.ofNullable(cc.getButenCode()).orElse("");
        String accountNumber = Optional.ofNullable(cc.getAccountNumber()).orElse("");
        
        // 口座番号: 部店コード + "-" + 口座番号
        resDto.setAccountNumber(butenCode + "-"+ StringUtils.leftPad(accountNumber, 7, "0"));
        // 個人・法人アイコン
        resDto.setCorporationKbn(Optional.ofNullable(cc.getCorporationType()).orElse(""));
        // 顧客名: 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
        resDto.setCustomerName(Optional.ofNullable(cc.getCustomerNameKanji()).orElse("") + "("+ Optional.ofNullable(cc.getCustomerNameKana()).orElse("") +")");
        
        // ① 共通関数FCT001の呼び出し
        //     利用者の口座に対する権限チェックを行う。
        //   パラメタ：部店コード   ⇒ 顧客共通情報.部店コード
        //                      口座番号       ⇒ 顧客共通情報.口座番号
        // 権限あり（対象顧客参照権限有無＝"1"）：次の処理へ。
        // 権限なし（対象顧客参照権限有無＝"0"）：権限なしエラー(errors.butenAccountNotExist)を返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(butenCode);
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(
                            IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        
        // ② API001（積立設定一覧取得（一括変更用））を呼び出す。 
        // 積立設定一覧情報を取得する。
        GetFundReserveGetReserveSettingForBulkUpdateReq getFundReserveGetReserveSettingForBulkUpdateReq = new GetFundReserveGetReserveSettingForBulkUpdateReq();
        getFundReserveGetReserveSettingForBulkUpdateReq.getHeader().setToken(SafeApiUtil.getToken(butenCode, accountNumber));  // トークン
        ReserveSettingDataListForBulkUpdateApiIn reserveSettingDataListForBulkUpdateApiIn = new ReserveSettingDataListForBulkUpdateApiIn();
        
        List<ReserveSettingDataForBulkUpdate> reserveSettingDataForBulkUpdateList = new ArrayList<>();
        for(CancelTargetAccumulateSettingDetail cancelTargetAccumulateSettingDetail :dtoReq.getCancelTargetAccumulateSettingList()) {
       	 ReserveSettingDataForBulkUpdate reserveSettingDataForBulkUpdate = new ReserveSettingDataForBulkUpdate();
            reserveSettingDataForBulkUpdate.setFundCode(cancelTargetAccumulateSettingDetail.getFundCode()); // 協会コード
            reserveSettingDataForBulkUpdate.setPaymentMethod(IfaPaymentMethod.getSafeEnumByIfaValue(cancelTargetAccumulateSettingDetail.getPaymentMethod()));  //決済方法
            reserveSettingDataForBulkUpdate.setAccountType(IfaReserveTradeTypesEnum.getSafeEnumByIfaValue(cancelTargetAccumulateSettingDetail.getAccountType())); //預り区分
            reserveSettingDataForBulkUpdateList.add(reserveSettingDataForBulkUpdate);
        }
       
        reserveSettingDataListForBulkUpdateApiIn.setReserveOrderList(reserveSettingDataForBulkUpdateList);

        getFundReserveGetReserveSettingForBulkUpdateReq.setParameter(reserveSettingDataListForBulkUpdateApiIn);
        
        GetFundReserveGetReserveSettingForBulkUpdateRes getFundReserveGetReserveSettingForBulkUpdate = new GetFundReserveGetReserveSettingForBulkUpdateRes();
        
		try {

			getFundReserveGetReserveSettingForBulkUpdate = safeFundTradeService
					.getReserveSettingForBulkUpdate(getFundReserveGetReserveSettingForBulkUpdateReq);

		} catch (Exception e) {
			// 取得エラーを返却する
			return safeCommonService.checkSafeBussinessException(resDtoList, e, SETTING_EXECUTION_CANCEL);
		}

		ReserveSettingDataListApiOut reserveSettingDataListApiOut = getFundReserveGetReserveSettingForBulkUpdate
				.getReserveSettingDataListApiOut();
		List<ReserveSettingData> reserveOrderList = reserveSettingDataListApiOut.getReserveOrderList();
		if (reserveSettingDataListApiOut != null && !CollectionUtils.isEmpty(reserveOrderList)
				&& reserveOrderList.size() >= 1) {
			resDto.setSettingCancelConfirmList(createA001ResponseDataDetail(reserveOrderList, dtoReq));
		} else {
			dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
					IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.SETTING_EXECUTION_CANCEL_NO_DATA,
					IfaCommonUtil.getMessage(
							IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.SETTING_EXECUTION_CANCEL_NO_DATA));
			return dtoRes;
		}

		resDtoList.add(resDto);

		// ③ レスポンスを返す。
		return safeCommonService.checkSafeBussinessWarning(resDtoList, reserveSettingDataListApiOut);

    }
    
    /**
     * アクションID：A003
     * アクション名：accumulateSettingCancelA003
     * Dtoリクエスト：IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto
     * Dtoレスポンス：IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto
     * model 1 リクエスト：IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto> accumulateSettingCancelA003(
            IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingCancelConfirmServiceImplL.accumulateSettingCancelA003");      
        }
        
        DataList<IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto>();
        IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto resDto = new IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto();
        List<IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto>();
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        // ユーザ共通情報の取得
        UserAccount userInfo = IfaCommonUtil.getUserAccount();

        String butenCode = Optional.ofNullable(cc.getButenCode()).orElse("");
        String accountNumber = Optional.ofNullable(cc.getAccountNumber()).orElse("");
        
        // 口座番号: 部店コード + "-" + 口座番号
        resDto.setAccountNumber(butenCode + "-"+ StringUtils.leftPad(accountNumber, 7, "0"));
        // 個人・法人アイコン
        resDto.setCorporationKbn(Optional.ofNullable(cc.getCorporationType()).orElse(""));
        // 顧客名: 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
        resDto.setCustomerName(Optional.ofNullable(cc.getCustomerNameKanji()).orElse("") + "("+ Optional.ofNullable(cc.getCustomerNameKana()).orElse("") +")");
        
        // ① 共通関数FCT001の呼び出し
        //     利用者の口座に対する権限チェックを行う。
        //   パラメタ：部店コード   ⇒ 顧客共通情報.部店コード
        //                      口座番号       ⇒ 顧客共通情報.口座番号
        // 権限あり（対象顧客参照権限有無＝"1"）：次の処理へ。
        // 権限なし（対象顧客参照権限有無＝""0""）：権限なしエラー(errors.butenAccountNotExist)を返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(butenCode);
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(
                            IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        
        

        // ②  ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(userInfo.getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
            IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.ERRORS_CMN_CCSID_UNREGISTERED));
        }

        // ③  設定解除を行う。
        IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel insSql001Req = new IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel();
        
        PostFundReserveSettingReleaseReceptReq postFundReserveSettingReleaseReceptReq = new PostFundReserveSettingReleaseReceptReq();
    	postFundReserveSettingReleaseReceptReq.getHeader().setToken(SafeApiUtil.getToken(butenCode, accountNumber));  // トークン
    	FundReserveSettingReleaseReceptApiIn fundReserveSettingReleaseReceptApiIn = new FundReserveSettingReleaseReceptApiIn();
        
        List<FundReserveSettingReleaseIn> fundReserveSettingReleaseInList = new ArrayList<>();

        for(IfaMutualFundAccumulateSettingCancelConfirmA003RequesDetail accumulateSettingCancelConfirmA003RequesDetail :dtoReq.getCancelConfirmA003RequestList()) {
        	FundReserveSettingReleaseIn fundReserveSettingReleaseIn = new FundReserveSettingReleaseIn();
           	fundReserveSettingReleaseIn.setFundCode(accumulateSettingCancelConfirmA003RequesDetail.getFundCode()); // 協会コード
           	fundReserveSettingReleaseIn.setPaymentMethod(IfaPaymentMethod.getSafeEnumByIfaValue(accumulateSettingCancelConfirmA003RequesDetail.getPaymentMethod()));  //決済方法
           	fundReserveSettingReleaseIn.setAccountType(IfaReserveTradeTypesEnum.getSafeEnumByIfaValue(accumulateSettingCancelConfirmA003RequesDetail.getAccountType())); //預り区分
           	fundReserveSettingReleaseInList.add(fundReserveSettingReleaseIn);
        }
       
        fundReserveSettingReleaseReceptApiIn.setReserveOrderList(fundReserveSettingReleaseInList);
        
        // ユーザ共通情報.CCSログイン用ID
        fundReserveSettingReleaseReceptApiIn.setOperatorId(userInfo.getCcsUserId());
        
        fundReserveSettingReleaseReceptApiIn.setRouteType("BRANCH_OFFICE"); //受付経路区分 ”2"　(BRANCH_OFFICE) ”0"　	”2"'：支店

        postFundReserveSettingReleaseReceptReq.setParameter(fundReserveSettingReleaseReceptApiIn);
        
        PostFundReserveSettingReleaseReceptRes postFundReserveSettingReleaseReceptRes = new PostFundReserveSettingReleaseReceptRes();
      
		try {

			postFundReserveSettingReleaseReceptRes = safeFundTradeService
					.reserveReleaseRecept(postFundReserveSettingReleaseReceptReq);

		} catch (Exception e) {
			// 取得エラーを返却する
			return safeCommonService.checkSafeBussinessException(resDtoList, e, SETTING_EXECUTION_CANCEL);
		}
		
	   FundReserveSettingReleaseReceptApiOut fundReserveSettingReleaseReceptApiOut = postFundReserveSettingReleaseReceptRes.getFundReserveSettingReleaseReceptApiOut();
	   List<FundReserveSettingReleaseOut> reserveOrderList = fundReserveSettingReleaseReceptApiOut.getReserveOrderList();
	   
	   if (reserveOrderList != null && !CollectionUtils.isEmpty(reserveOrderList)) {
			insSql001Req.setSql001DetailList(getSql001Data(reserveOrderList, dtoReq, cc, userInfo));
			resDto.setSettingCancelConfirmList(createA003ResponseDataDetail(reserveOrderList, dtoReq));
		}
       
	   dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes,fundReserveSettingReleaseReceptApiOut);

       // 設定解除受付データの格納
       try {
           ifaMutualFundAccumulateSettingDBProcessService.cancelConfirmSql001(insSql001Req);
       } catch(Exception e) {
           // DB更新エラー：API正常の場合、DB更新エラー（warnings.fnd.ReserveSettingExecutionCancel.completed）を表示する。
           // 【メッセージ】
           // 積立設定解除後の受付データを登録できませんでした。積立設定解除は完了しています。
           String sqlWarningMsg =  IfaCommonUtil.getMessage(IfaMutualFundAccumulateSettingCancelConfirmServiceImpL.WARNINGS_CMN_RESERVESETTINGEXECUTIONCANCEL_COMPLETED);
           if(dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            dtoRes.setMessage(dtoRes.getMessage()+ "<sep>" + sqlWarningMsg );
           } else {
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            dtoRes.setMessage(sqlWarningMsg);
           }
       }

       // DB登録OK：次の処理へ。
       resDtoList.add(resDto);
       
       // ③ レスポンスを返す。
       if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
           if (resDtoList != null) {
               dtoRes.setDataList(resDtoList);
           }
           dtoRes.setTotalSize(dtoRes.getDataList().size());
           dtoRes.setMaxRownum(dtoRes.getDataList().size());
           dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
           return dtoRes;
       }
       dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
       return dtoRes;
    }


    /**
     * A001のレスポンスデータの明細リストを作成する
     *
     * @param reserveOrderList 積立設定一覧取得（一括変更用）から取得した積立設定リスト
     * @param dtoReq  リクエストパラメータ
     * @return 投信積立設定解除一覧の明細
    
     */
    private List<IfaMutualFundAccumulateSettingCancelListDetail> createA001ResponseDataDetail(List<ReserveSettingData> reserveOrderList,IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto dtoReq) {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingCancelConfirmServiceImplL.createA001ResponseDataDetail");
        }
    	
        List<IfaMutualFundAccumulateSettingCancelListDetail> detailList = new ArrayList<IfaMutualFundAccumulateSettingCancelListDetail>();
        
        
        for (ReserveSettingData reserveSettingData : reserveOrderList) {
        	String fundCode = Optional.ofNullable(reserveSettingData.getFundCode()).orElse("");
        	String mfkaisu = "";
        	String mfgo = "";
        	
        	for (CancelTargetAccumulateSettingDetail cancelTargetAccumulateSettingDetail : dtoReq.getCancelTargetAccumulateSettingList()) {
        		if(fundCode.equals(cancelTargetAccumulateSettingDetail.getFundCode())) {
        			mfkaisu = cancelTargetAccumulateSettingDetail.getMfkaisu();
        			mfgo = cancelTargetAccumulateSettingDetail.getMfgo();
        			break;
        		}
        		
        	}
        	
            IfaMutualFundAccumulateSettingCancelListDetail detail = new IfaMutualFundAccumulateSettingCancelListDetail();
            // 明細.協会コード
            detail.setFundCode(fundCode);
            
            // ファンドコード（回数）
            detail.setMfkaisu(mfkaisu);
            
            // ファンドコード（号）
            detail.setMfgo(mfgo);
            
            // 明細.銘柄コード
            detail.setBrandCode(detail.getMfkaisu()+ "." + detail.getMfgo());
            
            // 明細.銘柄名
            detail.setFundName(Optional.ofNullable(reserveSettingData.getFundName()).orElse(""));
                
            // 明細.預り区分
            // 取得パターン:2,表示パターン:3
            if (reserveSettingData.getAccountType() != null) {
                String ifaAccountTypeCode = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(reserveSettingData.getAccountType());
                detail.setAccountType(ifaAccountTypeCode);
                detail.setAccountTypeName(codeListService.getValue(RESERVE_TRADE_DEPOSIT_TYPE, ifaAccountTypeCode, DISPLAY_PATTERN_3));
            } else {
                detail.setAccountType("");
            }

            // 明細.決済方法
            // 取得パターン:1,表示パターン:1
            if (reserveSettingData.getPaymentMethod() != null) {
                String ifaPaymentMethod = SafeType2IfaTypeUtil.IfaPaymentMethod.getIfaValueBySafeEnum(reserveSettingData.getPaymentMethod());
                detail.setPaymentMethod(ifaPaymentMethod);
                detail.setPaymentMethodName(codeListService.getValue(FUND_RESERVE_PAYMENT_METHOD, ifaPaymentMethod, DISPLAY_PATTERN_1));
            } else {
                detail.setPaymentMethodName("");
            }
            
            // 明細リスト.積立コース
            String courseType = reserveSettingData.getCourseType();
            if (courseType != null) {

                // ■積立コース＝毎日の場合
                // "毎日"
                if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_DAY.getSafeEnum().equals(courseType)) {
                    detail.setCourseType(AccumulateCourse.getEveryDayCourse());
                } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_WEEK.getSafeEnum().equals(courseType)) {
                    // ■積立コース＝毎週の場合
                    // ”毎週”+"（"+申込設定日(毎週用)+"）"

                    String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                            .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveWeek());

                    detail.setCourseType(AccumulateCourse.getEveryWeekCourse(ifaSettingReserveWeek));
                } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_MONTH.getSafeEnum().equals(courseType)) {
                    // ■積立コース＝毎月の場合
                    // ”毎月”+"（"+申込設定日(毎月、隔月用)+"）日"
                    // ※積立コースが毎月、隔月かつ３１日の場合：設定日は"月末"

                    detail.setCourseType(
                            AccumulateCourse.getEveryMonthCourse(reserveSettingData.getSettingReserveDay()));
                } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.BIMONTHLY.getSafeEnum().equals(courseType)) {
                    // ■積立コース＝隔月の場合
                    // 積立奇偶月設定区分+"（"+申込設定日(毎月、隔月用)+"）日"
                    // ※積立コースが毎月、隔月かつ３１日の場合：設定日は"月末"
                    String ifaBimonthlyCourse = SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                            .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveBimonthly());

                    if (ifaBimonthlyCourse != null) {
                        detail.setCourseType(AccumulateCourse.getBimonthlyCourse(ifaBimonthlyCourse,
                                reserveSettingData.getSettingReserveDay()));
                    } else {
                        detail.setCourseType("");
                    }
                } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.MULTIPLE_DAYS.getSafeEnum().equals(courseType)) {
                    // ■積立コース＝複数日の場合
                    // ”複数日”+"（"+申込設定日(複数日用)+"）日"

                    detail.setCourseType(
                            AccumulateCourse.getMultipleDaysCourse(reserveSettingData.getSettingReserveMultiDay()));
                }
            }
            
            // 明細.設定金額
            detail.setSettingAmount(Optional.ofNullable(reserveSettingData.getSettingAmount())
                    .orElse(new BigDecimal(0)).toPlainString()); 
            
            // 明細.ボーナス月の設定
            /*
             * ■決済方法＝ボーナス設定金額、ボーナス1設定月、ボーナス1設定日＝値ありの場合
			　* 上段：ボーナス設定金額＋”円”
			　* 下段：　
			　* 　■ボーナス2設定月、ボーナス2設定日＝値ありの場合
			　* 　　”(”+ボーナス１設定月+"/"+ボーナス１設定日＋
			　* 　　　”、”+ボーナス2設定月+"/"+ボーナス2設定日＋")"
			 *  　■上記以外
			　* 　　”(”+ボーナス１設定月+"/"+ボーナス１設定日+")"
			 *  　　 ※設定日が31の場合：MM/月末
			 * ■上記以外
			　*  "-"
             */
            // 明細リスト.ボーナス設定
            // ■決済方法＝ボーナス設定金額、ボーナス1設定月、ボーナス1設定日＝値ありの場合
            if (reserveSettingData.getSettingBonusAmount() != null
                    && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus1Month())
                    && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus1Day())) {
                // 上段：ボーナス設定金額＋”円”
                detail.setSettingBonusAmount(Optional.ofNullable(reserveSettingData.getSettingBonusAmount())
                        .orElse(new BigDecimal(0)).toPlainString());
                // 下段：
                // ■ボーナス2設定月、ボーナス2設定日＝値ありの場合
                // ”(”+ボーナス１設定月+"/"+ボーナス１設定日＋”、”+ボーナス2設定月+"/"+ボーナス2設定日＋")"
                if (StringUtils.isNotEmpty(reserveSettingData.getSettingBonus2Month())
                        && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus2Day())) {
                    detail.setSettingBonusMonthDay("(" + reserveSettingData.getSettingBonus1Month() + "/"
                            + ("31".equals(reserveSettingData.getSettingBonus1Day()) ? "月末"
                                    : reserveSettingData.getSettingBonus1Day())
                            + "," + reserveSettingData.getSettingBonus2Month() + "/"
                            + ("31".equals(reserveSettingData.getSettingBonus2Day()) ? "月末"
                                    : reserveSettingData.getSettingBonus2Day())
                            + ")");
                } else {
                    // ■上記以外
                    // ”(”+ボーナス１設定月+"/"+ボーナス１設定日+")"
                    detail.setSettingBonusMonthDay("(" + reserveSettingData.getSettingBonus1Month() + "/"
                            + ("31".equals(reserveSettingData.getSettingBonus1Day()) ? "月末"
                                    : reserveSettingData.getSettingBonus1Day())
                            + ")");
                }
                // ※設定日が31の場合：MM/月末
            } else {
                // ■上記以外
                detail.setSettingBonusMonthDay("-");
            }
            
            // 明細.NISA枠ぎりぎり注文
            /**
             * 設定する、設定しない、-
			 * ＠取得パターン:1
			 * ＠表示パターン:1
             */    
            detail.setNisaBarelyBuyingType(codeListService.getValue(
            		FUND_RESERVE_NISA_BARELY_BUYING_KBN, 
            		IfaNisaBuyableTypeEnum.getIfaValueBySafeEnum(reserveSettingData.getNisaBarelyBuyingType()),
            		DISPLAY_PATTERN_1));
            
            // 明細.課税枠シフト注文
            /**
             * 設定する、設定しない、-
			 * ＠取得パターン:1
			 * ＠表示パターン:1
             */
            detail.setTaxShiftType(codeListService.getValue(
            		FUND_RESERVE_TAX_SHIFT_KBN, 
            		IfaNisaExcessBuyableTypeEnum.getIfaValueBySafeEnum(reserveSettingData.getTaxShiftType()),
            		DISPLAY_PATTERN_1));
            
            // 明細.1ヵ月あたりの積立概算
            detail.setOneMonthSumAmount(Optional.ofNullable(reserveSettingData.getOneMonthSumAmount())
                    .orElse(new BigDecimal(0)).toPlainString()); 
            
            // 明細.次回発注予定日
            /*
             * ■次回発注予定日＝値なしの場合
			　* "----/--/--"
			 * ■上記以外 次回発注予定日
             */
            if (StringUtils.isNotEmpty(reserveSettingData.getNextReserveDate())) {
				detail.setNextReserveDate(LocalDate
						.parse(reserveSettingData.getNextReserveDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD))
						.format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
			} else {
				detail.setNextReserveDate("----/--/--");
			}

            detailList.add(detail);
        }
        
        return detailList;
    }
    
    /**
     * A003のSQl001 リクエストデータのリストを作成する
     *
     * @param reserveOrderList 積立設定一括解除から取得した解除対象積立設定リスト
     * @param dtoReq  リクエストパラメータ
     * @param cc 顧客情報
     * @param userInfo ユーザ共通情報
     * @return SQl001 リクエストデータのリスト
    
     */
    private List<IfaMutualFundAccumulateSettingCancelConfirmSql001ListDetail> getSql001Data(List<FundReserveSettingReleaseOut> fundReserveSettingReleaseOutList,
    		IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto dtoReq ,CustomerCommon cc , UserAccount userInfo) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingCancelConfirmServiceImplL.getSql001Data");
        }
    	
        List<IfaMutualFundAccumulateSettingCancelConfirmSql001ListDetail> detailList = new ArrayList<IfaMutualFundAccumulateSettingCancelConfirmSql001ListDetail>();
        
        for (FundReserveSettingReleaseOut fundReserveSettingReleaseOut : fundReserveSettingReleaseOutList) {
        	
        	IfaMutualFundAccumulateSettingCancelConfirmSql001ListDetail detail = new IfaMutualFundAccumulateSettingCancelConfirmSql001ListDetail();

            // 部店コード
            detail.setButenCode(Optional.ofNullable(cc.getButenCode()).orElse(""));
            
            // 口座番号
            detail.setAccountNumber(Optional.ofNullable(cc.getAccountNumber()).orElse(""));
            
        	String fundCode = Optional.ofNullable(fundReserveSettingReleaseOut.getFundCode()).orElse("");
        	String mfkaisu = "";
        	String mfgo = "";
        	
        	for (IfaMutualFundAccumulateSettingCancelConfirmA003RequesDetail requesDetail : dtoReq.getCancelConfirmA003RequestList()) {
        		if(fundCode.equals(requesDetail.getFundCode())) {
        			mfkaisu = requesDetail.getMfkaisu();
        			mfgo = requesDetail.getMfgo();
        			break;
        		}
        		
        	}

            // ファンドコード（回数）
            detail.setMfkaisu(mfkaisu);
            
            // ファンドコード（号）
            detail.setMfgo(mfgo);
            
            // 明細.預り区分
            if (fundReserveSettingReleaseOut.getAccountType() != null) {
                String ifaAccountTypeCode = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(fundReserveSettingReleaseOut.getAccountType());
                detail.setAccountType(ifaAccountTypeCode);
            } else {
                detail.setAccountType("");
            }

            // ボーナス設定有無
			if (new BigDecimal(1).compareTo(Optional.ofNullable(fundReserveSettingReleaseOut.getSettingBonusAmount())
					.orElse(new BigDecimal(0))) < 0) {
				detail.setSettingBonus("1");
			} else {
				detail.setSettingBonus("0");
			}

            // 積立日付
            detail.setSettingReserveDay(fundReserveSettingReleaseOut.getSettingReserveDay());
            
            // ボーナス１設定月
            detail.setSettingBonus1Month(fundReserveSettingReleaseOut.getSettingBonus1Month());
            
            // ボーナス１設定日
            detail.setSettingBonus1Day(fundReserveSettingReleaseOut.getSettingBonus1Day());
            
            // ボーナス２設定月
            detail.setSettingBonus2Month(fundReserveSettingReleaseOut.getSettingBonus2Month());
            
            // ボーナス２設定日
            detail.setSettingBonus2Day(fundReserveSettingReleaseOut.getSettingBonus2Day());
            
            // 買付予定日
            detail.setPlanDate(fundReserveSettingReleaseOut.getPlanDate());

            // ボーナス１買付予定日
            detail.setBonusPlanDate1(fundReserveSettingReleaseOut.getBonusPlanDate1());
            
            // ボーナス２買付予定日
            detail.setBonusPlanDate2(fundReserveSettingReleaseOut.getBonusPlanDate2());

            // 次回買付日
            detail.setNextReserveDate(fundReserveSettingReleaseOut.getNextReserveDate());

            // 積立複数日設定
            detail.setSettingReserveMultiDay(fundReserveSettingReleaseOut.getSettingReserveMultiDay());    
  
            // NISA枠ぎりぎり買付区分
            detail.setNisaBarelyBuyingType(SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum.getIfaValueBySafeEnum(
            		fundReserveSettingReleaseOut.getNisaBarelyBuyingType()));
            // NISA枠超過時買付区分
            detail.setTaxShiftType(SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum.getIfaValueBySafeEnum(
            		fundReserveSettingReleaseOut.getTaxShiftType()));
            // コース区分
            String courseTypeForDB = SafeType2IfaTypeUtil.IfaCourseTypeEnum.getIfaValueBySafeEnum(
                    fundReserveSettingReleaseOut.getCourseType());
            detail.setCourseType("6".equals(courseTypeForDB) ? "5" : courseTypeForDB);
            // 積立隔月設定
            detail.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.getIfaValueBySafeEnum(
            		fundReserveSettingReleaseOut.getSettingReserveBimonthly()));
            // 積立毎週設定
            detail.setSettingReserveWeek(SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum.getIfaValueBySafeEnum(
            		fundReserveSettingReleaseOut.getSettingReserveWeek()));

            // 設定金額
            detail.setSettingAmount(fundReserveSettingReleaseOut.getSettingAmount().toPlainString());
            
            // 1ヵ月あたりの設定金額（概算）
            if(fundReserveSettingReleaseOut.getOneMonthSumAmount() !=null) {
                detail.setOneMonthSumAmount(fundReserveSettingReleaseOut.getOneMonthSumAmount().toPlainString()); 
            }
            
            // ボーナス設定金額
            if(fundReserveSettingReleaseOut.getSettingBonusAmount() !=null) {
                detail.setSettingBonusAmount(fundReserveSettingReleaseOut.getSettingBonusAmount().toPlainString()); 
            }

            //オペレータＩＤ ユーザ共通情報.CCSログイン用ID
            detail.setOperatorId(userInfo.getCcsUserId());
            
            // 仲介業者コード 顧客共通情報.仲介業者コード
            detail.setBrokerCode(cc.getBrokerCode());
            
            // 仲介業者営業員コード 顧客共通情報.仲介業者営業員コード
            detail.setIntermediaryEmpCd(cc.getBrokerChargeCode());
            
            //作成者 ユーザ共通情報.ユーザーID
            detail.setCreateUser(userInfo.getUserId());
            
            //更新者 ユーザ共通情報.ユーザーID
            detail.setUpdateUser(userInfo.getUserId());
            
            detailList.add(detail);
        }
        
        return detailList;
    }
    
    /**
     * A003のレスポンスデータの明細リストを作成する
     *
     * @param reserveOrderList API002レスポンスの解除対象積立設定リスト
     * @param dtoReq  リクエストパラメータ
     * @return 投信積立設定解除完了の明細  
    
     */
    private List<IfaMutualFundAccumulateSettingFinshListDetail> createA003ResponseDataDetail(List<FundReserveSettingReleaseOut> reserveOrderList,IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto dtoReq) {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingCancelConfirmServiceImplL.createA003ResponseDataDetail");
        }
    	
        List<IfaMutualFundAccumulateSettingFinshListDetail> detailList = new ArrayList<IfaMutualFundAccumulateSettingFinshListDetail>();
        
        
        for (FundReserveSettingReleaseOut reserveSettingData : reserveOrderList) {
        	String fundCode = Optional.ofNullable(reserveSettingData.getFundCode()).orElse("");
        	String mfkaisu = "";
        	String mfgo = "";
        	
        	for (IfaMutualFundAccumulateSettingCancelConfirmA003RequesDetail cancelTargetAccumulateSettingDetail : dtoReq.getCancelConfirmA003RequestList()) {
        		if(fundCode.equals(cancelTargetAccumulateSettingDetail.getFundCode())) {
        			mfkaisu = cancelTargetAccumulateSettingDetail.getMfkaisu();
        			mfgo = cancelTargetAccumulateSettingDetail.getMfgo();
        			break;
        		}
        		
        	}
        	
        	IfaMutualFundAccumulateSettingFinshListDetail detail = new IfaMutualFundAccumulateSettingFinshListDetail();
            // 明細.協会コード
            detail.setFundCode(fundCode);
            
            // ファンドコード（回数）
            detail.setMfkaisu(mfkaisu);
            
            // ファンドコード（号）
            detail.setMfgo(mfgo);
            
            // 明細.銘柄コード
            detail.setBrandCode(detail.getMfkaisu()+ "." + detail.getMfgo());
            
            // 明細.銘柄名
            detail.setFundName(Optional.ofNullable(reserveSettingData.getFundName()).orElse(""));
                
            // 明細.預り区分
            // 取得パターン:2,表示パターン:3
            if (reserveSettingData.getAccountType() != null) {
                String ifaAccountTypeCode = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(reserveSettingData.getAccountType());
                detail.setAccountType(ifaAccountTypeCode);
                detail.setAccountTypeName(codeListService.getValue(RESERVE_TRADE_DEPOSIT_TYPE, ifaAccountTypeCode, DISPLAY_PATTERN_3));
            } else {
                detail.setAccountType("");
            }
            
            // 明細.決済方法
            // 取得パターン:1,表示パターン:1
            if (reserveSettingData.getPaymentMethod() != null) {
                String ifaPaymentMethod = SafeType2IfaTypeUtil.IfaPaymentMethod.getIfaValueBySafeEnum(reserveSettingData.getPaymentMethod());
                detail.setPaymentMethod(ifaPaymentMethod);
                detail.setPaymentMethodName(codeListService.getValue(FUND_RESERVE_PAYMENT_METHOD, ifaPaymentMethod, DISPLAY_PATTERN_1));
            } else {
                detail.setPaymentMethodName("");
            }
            
         // 明細リスト.積立コース
            String courseType = reserveSettingData.getCourseType();
            if (courseType != null) {

                // ■積立コース＝毎日の場合
                // "毎日"
                if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_DAY.getSafeEnum().equals(courseType)) {
                    detail.setCourseType(AccumulateCourse.getEveryDayCourse());
                } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_WEEK.getSafeEnum().equals(courseType)) {
                    // ■積立コース＝毎週の場合
                    // ”毎週”+"（"+申込設定日(毎週用)+"）"

                    String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                            .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveWeek());

                    detail.setCourseType(AccumulateCourse.getEveryWeekCourse(ifaSettingReserveWeek));
                } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_MONTH.getSafeEnum().equals(courseType)) {
                    // ■積立コース＝毎月の場合
                    // ”毎月”+"（"+申込設定日(毎月、隔月用)+"）日"
                    // ※積立コースが毎月、隔月かつ３１日の場合：設定日は"月末"

                    detail.setCourseType(
                            AccumulateCourse.getEveryMonthCourse(reserveSettingData.getSettingReserveDay()));
                } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.BIMONTHLY.getSafeEnum().equals(courseType)) {
                    // ■積立コース＝隔月の場合
                    // 積立奇偶月設定区分+"（"+申込設定日(毎月、隔月用)+"）日"
                    // ※積立コースが毎月、隔月かつ３１日の場合：設定日は"月末"
                    String ifaBimonthlyCourse = SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                            .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveBimonthly());

                    if (ifaBimonthlyCourse != null) {
                        detail.setCourseType(AccumulateCourse.getBimonthlyCourse(ifaBimonthlyCourse,
                                reserveSettingData.getSettingReserveDay()));
                    } else {
                        detail.setCourseType("");
                    }
                } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.MULTIPLE_DAYS.getSafeEnum().equals(courseType)) {
                    // ■積立コース＝複数日の場合
                    // ”複数日”+"（"+申込設定日(複数日用)+"）日"

                    detail.setCourseType(
                            AccumulateCourse.getMultipleDaysCourse(reserveSettingData.getSettingReserveMultiDay()));
                }
            }

            // 明細.設定金額
            detail.setSettingAmount(Optional.ofNullable(reserveSettingData.getSettingAmount())
                    .orElse(new BigDecimal(0)).toPlainString()); 
            
            // 明細.ボーナス月の設定
            /*
             * ■決済方法＝ボーナス設定金額、ボーナス1設定月、ボーナス1設定日＝値ありの場合
			　* 上段：ボーナス設定金額＋”円”
			　* 下段：　
			　* 　■ボーナス2設定月、ボーナス2設定日＝値ありの場合
			　* 　　”(”+ボーナス１設定月+"/"+ボーナス１設定日＋
			　* 　　　”、”+ボーナス2設定月+"/"+ボーナス2設定日＋")"
			 *  　■上記以外
			　* 　　”(”+ボーナス１設定月+"/"+ボーナス１設定日+")"
			 *  　　 ※設定日が31の場合：MM/月末
			 * ■上記以外
			　*  "-"
             */
            // 明細リスト.ボーナス設定
            // ■決済方法＝ボーナス設定金額、ボーナス1設定月、ボーナス1設定日＝値ありの場合
            if (reserveSettingData.getSettingBonusAmount() != null
                    && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus1Month())
                    && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus1Day())) {
                // 上段：ボーナス設定金額＋”円”
                detail.setSettingBonusAmount(Optional.ofNullable(reserveSettingData.getSettingBonusAmount())
                        .orElse(new BigDecimal(0)).toPlainString());
                // 下段：
                // ■ボーナス2設定月、ボーナス2設定日＝値ありの場合
                // ”(”+ボーナス１設定月+"/"+ボーナス１設定日＋”、”+ボーナス2設定月+"/"+ボーナス2設定日＋")"
                if (StringUtils.isNotEmpty(reserveSettingData.getSettingBonus2Month())
                        && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus2Day())) {
                    detail.setSettingBonusMonthDay("(" + reserveSettingData.getSettingBonus1Month() + "/"
                            + ("31".equals(reserveSettingData.getSettingBonus1Day()) ? "月末"
                                    : reserveSettingData.getSettingBonus1Day())
                            + "," + reserveSettingData.getSettingBonus2Month() + "/"
                            + ("31".equals(reserveSettingData.getSettingBonus2Day()) ? "月末"
                                    : reserveSettingData.getSettingBonus2Day())
                            + ")");
                } else {
                    // ■上記以外
                    // ”(”+ボーナス１設定月+"/"+ボーナス１設定日+")"
                    detail.setSettingBonusMonthDay("(" + reserveSettingData.getSettingBonus1Month() + "/"
                            + ("31".equals(reserveSettingData.getSettingBonus1Day()) ? "月末"
                                    : reserveSettingData.getSettingBonus1Day())
                            + ")");
                }
                // ※設定日が31の場合：MM/月末
            } else {
                // ■上記以外
                detail.setSettingBonusMonthDay("-");
            }
            
            // 明細.NISA枠ぎりぎり注文
            /**
             * 設定する、設定しない、-
			 * ＠取得パターン:1
			 * ＠表示パターン:1
             */    
            detail.setNisaBarelyBuyingType(codeListService.getValue(
            		FUND_RESERVE_NISA_BARELY_BUYING_KBN, 
            		IfaNisaBuyableTypeEnum.getIfaValueBySafeEnum(reserveSettingData.getNisaBarelyBuyingType()),
            		DISPLAY_PATTERN_1));
            
            // 明細.課税枠シフト注文
            /**
             * 設定する、設定しない、-
			 * ＠取得パターン:1
			 * ＠表示パターン:1
             */
            detail.setTaxShiftType(codeListService.getValue(
                    FUND_RESERVE_TAX_SHIFT_KBN, 
            		IfaNisaExcessBuyableTypeEnum.getIfaValueBySafeEnum(reserveSettingData.getTaxShiftType()),
            		DISPLAY_PATTERN_1));
            
            // 明細.1ヵ月あたりの積立概算
            detail.setOneMonthSumAmount(Optional.ofNullable(reserveSettingData.getOneMonthSumAmount())
                    .orElse(new BigDecimal(0)).toPlainString()); 
            
            // 明細.次回発注予定日
            /*
             * ■次回発注予定日＝値なしの場合
			　* "----/--/--"
			 * ■上記以外 次回発注予定日
             */
			if (StringUtils.isNotEmpty(reserveSettingData.getNextReserveDate())) {
				detail.setNextReserveDate(LocalDate
						.parse(reserveSettingData.getNextReserveDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD))
						.format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
			} else {
				detail.setNextReserveDate("----/--/--");
			}
  
            detailList.add(detail);
        }
        
        return detailList;
    }

}
