package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.extapi.ApiConnectionException;
import com.sbibits.earth.extapi.ApiIOException;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeCompleteA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDistributionReceiveMethodChangeCompleteA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDistributionReceiveMethodChangeCompleteService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.ReinvestEntryInData;
import jp.co.sbisec.pcenter.dto.yanap.ReinvestEntryOutData;
import jp.co.sbisec.pcenter.dto.yanap.SimReinvestEntryDataI;

/**
 * 画面ID：SUB0202_010201-02_2
 * 画面名：分配金受取方法変更完了
 * @author <author-name>
 *
 * 2023/12/04 新規作成
 */
@Component(value = "cmpIfaDistributionReceiveMethodChangeCompleteService")
public class IfaDistributionReceiveMethodChangeCompleteServiceImpL implements IfaDistributionReceiveMethodChangeCompleteService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDistributionReceiveMethodChangeCompleteServiceImpL.class);
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    /** API未取得エラー */
    public static final String ERROS_DMT_DISTRIBUTEIONS_RECEIPT_NOT_EXIST = "errors.dmt.distributionsReceipt.notExist";
    
    /** 変更前分配金受取方法に'受取'設定 */
    public static final String REINVESTMENT_SUSPENSION_INFORMATION_ACCEPT = "受取";
    
    /** 変更前分配金受取方法に'再投資'設定 */
    public static final String REINVESTMENT_SUSPENSION_INFORMATION_REINVESTMENT = "再投資";
    
    /** 処理区分　'S':参照 */
    private static final String PROCESS_ID = "S";
    
    /** 注文受付経路区分　'1'：コールセンター */
    private static final String CALLCENTER_KBN = "1";
    
    /** 再投資停止指定　'　'：半角スペース */
    private static final String REINVEST_SPACE = " ";
    
    /** 対象件数トータル　右詰め、前ZERO：001 */
    private static final String TOTAL_NUMBER = "001";
    
    /** 預かり区分 ジュニアNISA口座 */
    private static final String DEPOSIT_TYPE_JR_NISA = "7";
    
    /** 預かり区分 特定（特例） */
    private static final String DEPOSIT_TYPE_SPCIFIC = "6";
    
    /** 預かり区分 一般（特例） */
    private static final String DEPOSIT_TYPE_GENERAL = "5";
    
    /** 再投資停止情報 受付 */
    private static final String REINVEST_ACCEPT = "1";
    
    /** 再投資停止情報 再投資 */
    private static final String REINVEST_REINVESTMENT = "2";
    
    /** 分配金受取方法 '-' */
    private static final String REINVEST_HYPHEN = "-";
    
    /** 変更受付メッセージ */
    private static final String CANGE_MESSAGE = "分配金受取方法変更を受け付けました。";
    
    /** 桁数フォーマットによる"7桁" */
    private static final int SEVEN = 7;
    
    /** 桁数フォーマットによる"前0埋め" */
    private static final String ZERO = "0";
    
    /** 初期値設定のための空文字" */
    private static final String KARAMOZI = "";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDistributionReceiveMethodChangeCompleteA001DtoRequest
     * Dto レスポンス：IfaDistributionReceiveMethodChangeCompleteA001DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDistributionReceiveMethodChangeCompleteA001DtoResponse> initializeA001(IfaDistributionReceiveMethodChangeCompleteA001DtoRequest dtoReq)
            throws Exception {
        DataList<IfaDistributionReceiveMethodChangeCompleteA001DtoResponse> dtoRes = new DataList<IfaDistributionReceiveMethodChangeCompleteA001DtoResponse>();
        List<IfaDistributionReceiveMethodChangeCompleteA001DtoResponse> resMainList = new ArrayList<>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaDistributionReceiveMethodChangeCompleteServiceImplL.initializeA001");
        
        // 顧客共通情報
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        //①API001取得
        ReinvestEntryInData input = new ReinvestEntryInData();
        SimReinvestEntryDataI dataI = new SimReinvestEntryDataI();
        List<SimReinvestEntryDataI> dataIList = new ArrayList<SimReinvestEntryDataI>(); 
        ReinvestEntryOutData result = new ReinvestEntryOutData();
        input.setButenCd(cc.getButenCode());
        
        String kozaNo = KARAMOZI;
        if((dtoReq.getDepositType()).equals(DEPOSIT_TYPE_JR_NISA) || (dtoReq.getDepositType()).equals(DEPOSIT_TYPE_SPCIFIC) || 
                dtoReq.getDepositType().equals(DEPOSIT_TYPE_GENERAL)) {
            kozaNo = cc.getAccountNumber();
        } else {
            kozaNo = cc.getAccountNumber();
        }
        kozaNo = StringUtils.leftPad(kozaNo, SEVEN, ZERO);
        input.setKozaNo(kozaNo);
        
        input.setProcessId(PROCESS_ID);
        input.setCallcenterKbn(CALLCENTER_KBN);
        input.setTotalNumber(TOTAL_NUMBER);
        dataI.setKaisu(dtoReq.getTimes());
        dataI.setGou(dtoReq.getIssue1() + dtoReq.getIssue2());
        dataI.setReinvest(REINVEST_SPACE);
        dataIList.add(dataI);
        input.setSimReinvestEntryDataI(dataIList);
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        try {
            result = apiWrapper.reinvestEntry(input);
            //①'エラーの場合、メッセージを表示
        } catch (ApiIOException | ApiConnectionException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{}, {}, {}",
                        "IfaDistributionReceiveMethodChangeCompleteServiceImpL",
                        "InitializeA001",
                        e
                        );
            } 
            String message = IfaCommonUtil.getMessage(ERROS_DMT_DISTRIBUTEIONS_RECEIPT_NOT_EXIST);
            dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL,
                    ERROS_DMT_DISTRIBUTEIONS_RECEIPT_NOT_EXIST, message);
            return dtoRes;
        }
        apiErrorUtil.checkApiResponse(result.getShubetu(), result.getCode(), result.getMessage());
        if (apiErrorUtil.isFatal()) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERROS_DMT_DISTRIBUTEIONS_RECEIPT_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERROS_DMT_DISTRIBUTEIONS_RECEIPT_NOT_EXIST));
        }
        
      //②メッセージに'分配金受取方法変更を受け付けました。'を設定する。
        IfaDistributionReceiveMethodChangeCompleteA001DtoResponse ifaDistributionReceiveMethodChangeCompleteA001DtoResponse = 
                new IfaDistributionReceiveMethodChangeCompleteA001DtoResponse();
        ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setInfoMsg(CANGE_MESSAGE);
        
        //③変更前分配金受取方法への値設定
        if((result.getSimReinvestEntryDataO().get(0).getBeforeReinvest()).equals(REINVEST_ACCEPT)) {
            ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setMethod(REINVESTMENT_SUSPENSION_INFORMATION_ACCEPT);
        } else if ((result.getSimReinvestEntryDataO().get(0).getBeforeReinvest()).equals(REINVEST_REINVESTMENT)) { 
            ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setMethod(REINVESTMENT_SUSPENSION_INFORMATION_REINVESTMENT);
        } else {
            ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setMethod(REINVEST_HYPHEN);
        }
        
        //④変更後分配金受取方法への値設定
        if((result.getSimReinvestEntryDataO().get(0).getReinvest()).equals(REINVEST_ACCEPT)) {  
            ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setAfterChangeDistributionReceiveMethod(REINVESTMENT_SUSPENSION_INFORMATION_ACCEPT);
        } else if ((result.getSimReinvestEntryDataO().get(0).getReinvest()).equals(REINVEST_REINVESTMENT)) {
            ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setAfterChangeDistributionReceiveMethod(REINVESTMENT_SUSPENSION_INFORMATION_REINVESTMENT);
        } else {
            ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setAfterChangeDistributionReceiveMethod(REINVEST_HYPHEN);
        }
        
        //レスポンス値設定
        ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setFundName(dtoReq.getFundName());
        ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setUnitVolume(dtoReq.getUnitVolume());
        ifaDistributionReceiveMethodChangeCompleteA001DtoResponse.setSellingVolume(dtoReq.getSellingVolume());
        
        resMainList.add(ifaDistributionReceiveMethodChangeCompleteA001DtoResponse);
        dtoRes = apiErrorUtil.createDataList(resMainList, null);
        
        return dtoRes;
    }
    
}
