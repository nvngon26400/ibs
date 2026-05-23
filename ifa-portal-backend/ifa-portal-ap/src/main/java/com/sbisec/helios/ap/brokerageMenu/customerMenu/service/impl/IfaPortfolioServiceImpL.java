package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaPortfolioDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql013ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql014RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql014ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql015ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql017RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql017ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql018RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql018ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql019RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql019ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityDomesticStockResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListCashResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListDomesticClaimResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListForeignClaimForeignResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListForeignClaimForeignStructuredBondResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListForeignClaimYenBaseResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListForeignDepositResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListForeignMmfResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListForeignStockResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListMarginPositionResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListMutualFundResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListMutualFundTotalReturnResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListSbiRapAccountCashResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListSecurityTokenResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001HoldingSecurityListUsStockMarginPositionResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001PortfolioSummaryResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPortfolioA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaPortfolioService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.RtnCdEnum;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * 2023/12/26 新規作成
 *
 * @author SCSK 江口
 */
@Component(value = "cmpIfaPortfolioService")
public class IfaPortfolioServiceImpL implements IfaPortfolioService {
    
    @Autowired
    private IfaPortfolioDao dao;
    
    @Autowired
    private Fct001 fct001;
    
    /** 顧客に対する権限なしエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    /** 区分.対象顧客参照権限有無.権限あり */
    private static final String TARGET_CUSTOMER_REFERENCE_AUTHORITY_FLAG_AUTHORIZED = "1";
    
    /** 証券種別コード（商品分類コード）.国内債券(M_CODE.CODE_1) */
    private static final String SECURITY_CLASS_CODE_DOMESTIC_CLAIN = "03";
    
    /** 証券種別コード（商品分類コード）.外国債券(円建)(M_CODE.CODE_1) */
    private static final String SECURITY_CLASS_CODE_FOREIGN_CLAIN_DOMESTIC = "04";
    
    /** 証券種別コード（商品分類コード）.外国債券(外貨建)(M_CODE.CODE_1) */
    private static final String SECURITY_CLASS_CODE_FOREIGN_CLAIN_FOREIGN = "07";

    /** 仕組債区分.仕組債以外 */
    private static final String STRUCTUREED_BOND_CLASSIFICATION_NOT_STRUCTUREED_BOND = "  ";
    
    /** 顧客共通情報.信用口座区分（国内）.信用口座 */
    private static final String DOMESTIC_MARGIN_ACCOUNT_TYPE_MARGIN_ACCOUNT = "1";
    
    /** 顧客共通情報.信用口座区分（外国）.信用口座 */
    private static final String FOREIGN_MARGIN_ACCOUNT_TYPE_MARGIN_ACCOUNT = "1";
    
    /** 保有商品一覧_投資信託トータルリターンリスト.保有状況.解約済 */
    private static final String HOLDING_STATUS_CANCELLED = "0";
    
    /** 保有商品一覧_投資信託トータルリターンリスト.保有状況.保有中 */
    private static final String HOLDING_STATUS_HOLDING = "1";
    
    /** 証券種別コード・ラップ口座 */
    private static final String SECURITY_CLASS_CODE_WRAP = "SBIラップ口座現金";

    /** 利払日： 年1回 */
    private static final String INTERSET_PAYMENT_DATE_TIME_PER_YEAR_1 = "年1回：";

    /** 利払日： 年2回 */
    private static final String INTERSET_PAYMENT_DATE_TIME_PER_YEAR_2 = "年2回：";
    
    /** 利払日： 年3回 */
    private static final String INTERSET_PAYMENT_DATE_TIME_PER_YEAR_3 = "年3回(4ヶ月毎)：";
    
    /** 利払日： 年4回(四半期1回) */
    private static final String INTERSET_PAYMENT_DATE_TIME_PER_YEAR_4 = "年4回(四半期1回)：";

    /** 利払日： 年6回(隔月) */
    private static final String INTERSET_PAYMENT_DATE_TIME_PER_YEAR_6 = "年6回(隔月)：";

    /** 利払日： 年6回(偶数月) */
    private static final String INTERSET_PAYMENT_DATE_TIME_PER_YEAR_6_EVEN = "年6回(偶数月)：";

    /** 利払日： 年6回(奇数月) */
    private static final String INTERSET_PAYMENT_DATE_TIME_PER_YEAR_6_ODD = "年6回(奇数月)：";

    /** 利払日： "年12回(毎月) */
    private static final String INTERSET_PAYMENT_DATE_TIME_PER_YEAR_12 = "年12回(毎月)：";

    /** T_BALANCE_PREV更新ジョブ 稼働中 */
    private static final String JOB_STATUS_RUNNING = "1";

    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPortfolioServiceImpL.class);
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaPortfolioA001RequestDto
     * Dto レスポンス：IfaPortfolioA001ResponseDto
     * model リクエスト：IfaPortfolioSql019RequestModel
     * model レスポンス：IfaPortfolioSql019ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 受取方法変更入力画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaPortfolioA001ResponseDto> initializeA001(IfaPortfolioA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPortfolioServiceImplL.initializeA001");
        }
        
        // レスポンスの内容を格納する変数
        IfaPortfolioA001ResponseDto data = new IfaPortfolioA001ResponseDto();
        
        /* ====================================================================== */
        /* T_BALANCE_PREV更新ジョブのステータスを取得する                            */
        /* ====================================================================== */

        String jobStatus = dao.selectIfaPortfolioSql020();
        data.setJobStatus(jobStatus);

        // ジョブステータスが"1"（稼働中）の場合、処理を終了する
        if (StringUtils.equals(jobStatus, JOB_STATUS_RUNNING)) {
            DataList<IfaPortfolioA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(Arrays.asList(data),
                    ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(), "");
            
            return dtoRes;
        }
        
        /* ====================================================================== */
        /* 利用者の口座に対する権限有無を確認(FCT001を呼び出し)                     */
        /* ====================================================================== */
        
        // FCT001リクエストDTOの作成
        String butenCode = IfaCommonUtil.getCustomerCommon().getButenCode();
        String accountNumber = IfaCommonUtil.getCustomerCommon().getAccountNumber();
        
        InputFct001Dto fct001InputDto = new InputFct001Dto();
        fct001InputDto.setButenCode(butenCode);
        fct001InputDto.setAccountNumber(accountNumber);
        
        // FCT001リクエストの実行
        OutputFct001Dto fct001OutputDto = fct001.doCheck(fct001InputDto);
        
        // 利用者の口座に対する権限を持っていない場合エラーレスポンスを返す
        if (fct001OutputDto == null || !StringUtils.equals(fct001OutputDto.getTargetCustomerRefAuthFlag(),
                TARGET_CUSTOMER_REFERENCE_AUTHORITY_FLAG_AUTHORIZED)) {
            
            DataList<IfaPortfolioA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXIST, IfaCommonUtil
                            .getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST, new String[] { butenCode, accountNumber }));
            
            return dtoRes;
        }
        
        /* ====================================================================== */
        /* 資産状況サマリ                                                         */
        /* ====================================================================== */
        
        // SQL001を実行
        IfaPortfolioSql001RequestModel selSql001Req = new IfaPortfolioSql001RequestModel();
        selSql001Req.setButenCode(butenCode);
        selSql001Req.setAccountNumber(accountNumber);
        List<IfaPortfolioSql001ResponseModel> selSql001Res = convertSummary(
                dao.selectIfaPortfolioSql001(selSql001Req).getDataList());
        
        // 資産状況サマリ評価額合計と資産状況サマリ評価損益合計を算出しdataに格納
        BigDecimal portfolioSummaryValuationTotal = BigDecimal.ZERO;
        
        for (IfaPortfolioSql001ResponseModel record : selSql001Res) {
            
            // 資産状況サマリ評価額合計
            portfolioSummaryValuationTotal = portfolioSummaryValuationTotal.add(new BigDecimal(record.getValuation()));
            
        }
        BigDecimal portfolioSummaryProfitAndLossTotal = selSql001Res.stream() //
                .filter(v -> (Objects.nonNull(v.getProfitAndLoss()))) //
                .map(v -> new BigDecimal(v.getProfitAndLoss())) //
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        if (portfolioSummaryValuationTotal != null) {
            data.setPortfolioSummaryValuationTotal(portfolioSummaryValuationTotal.toPlainString());
        }
        if (portfolioSummaryProfitAndLossTotal != null) {
            data.setPortfolioSummaryProfitAndLossTotal(portfolioSummaryProfitAndLossTotal.toPlainString());
        }
        
        // 資産状況サマリリスト.資産比率を算出 + SQL001の結果をdataに格納
        ArrayList<IfaPortfolioA001PortfolioSummaryResponseDto> portfolioSummaryList = new ArrayList<>();
        for (IfaPortfolioSql001ResponseModel record : selSql001Res) {
            IfaPortfolioA001PortfolioSummaryResponseDto portfolioSummary = new IfaPortfolioA001PortfolioSummaryResponseDto();
            
            // 共通の項目をコピー
            BeanUtils.copyProperties(portfolioSummary, record);
            // 資産比率を算出してレスポンスにセット
            if (record.getValuation() != null && portfolioSummaryValuationTotal != null) {
                
                // ゼロ除算にならない場合、資産比率を計算によって求める
                if (portfolioSummaryValuationTotal.compareTo(BigDecimal.ZERO) != 0) {
                    BigDecimal valuation = new BigDecimal(record.getValuation());
                    BigDecimal byProductAssetsRatio = valuation.multiply(new BigDecimal("100"))
                            .divide(portfolioSummaryValuationTotal, 2, RoundingMode.FLOOR);
                    portfolioSummary.setByProductAssetsRatio(byProductAssetsRatio.toPlainString());
                    
                } else { // ゼロ除算の場合、ゼロをセット
                    portfolioSummary.setByProductAssetsRatio("0.00");
                }
                
                // 資産状況サマリリストに追加
                portfolioSummaryList.add(portfolioSummary);
            }
        }
        
        // 資産サマリリストをdataに格納
        data.setPortfolioSummaryList(portfolioSummaryList);
        
        /* ====================================================================== */
        /* 国内株式                                                                */
        /* ====================================================================== */
        
        // SQL002を実行
        IfaPortfolioSql002RequestModel selSql002Req = new IfaPortfolioSql002RequestModel();
        selSql002Req.setButenCode(butenCode);
        selSql002Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql002ResponseModel> selSql002Res = dao.selectIfaPortfolioSql002(selSql002Req);
        
        // SQL002の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityDomesticStockResponseDto> holdingSecurityDomesticStockList = new ArrayList<>();
        for (IfaPortfolioSql002ResponseModel record : selSql002Res.getDataList()) {
            IfaPortfolioA001HoldingSecurityDomesticStockResponseDto holdingSerucurityDomesticStock = new IfaPortfolioA001HoldingSecurityDomesticStockResponseDto();
            BeanUtils.copyProperties(holdingSerucurityDomesticStock, record);
            
            holdingSecurityDomesticStockList.add(holdingSerucurityDomesticStock);
        }
        data.setHoldingSecurityDomesticStock(holdingSecurityDomesticStockList);
        
        // 国内株式評価損益合計を算出して、dataに格納(1件でもNULLなら、合計もNULL)
        if (holdingSecurityDomesticStockList.stream().allMatch(val -> val.getProfitAndLoss() != null)) {
            BigDecimal domesticStockProfitAndLossTotal = holdingSecurityDomesticStockList.stream()
                    .map(val -> new BigDecimal(val.getProfitAndLoss())).reduce((x, y) -> x.add(y)).orElse(null);
            if (domesticStockProfitAndLossTotal != null) {
                data.setDomesticStockProfitAndLossTotal(domesticStockProfitAndLossTotal.toPlainString());
            }
        }
        
        /* ====================================================================== */
        /* 国内債券                                                                */
        /* ====================================================================== */
        
        // SQL003を実行
        IfaPortfolioSql003RequestModel selSql003Req = new IfaPortfolioSql003RequestModel();
        selSql003Req.setButenCode(butenCode);
        selSql003Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql003ResponseModel> selSql003Res = dao.selectIfaPortfolioSql003(selSql003Req);
        
        // SQL003の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityListDomesticClaimResponseDto> holdingSecurityListDomesticClaimList = new ArrayList<>();
        for (IfaPortfolioSql003ResponseModel record : selSql003Res.getDataList()) {
            IfaPortfolioA001HoldingSecurityListDomesticClaimResponseDto holdingSecurityListDomesticClaim = new IfaPortfolioA001HoldingSecurityListDomesticClaimResponseDto();
            BeanUtils.copyProperties(holdingSecurityListDomesticClaim, record);

            // 利払日
            holdingSecurityListDomesticClaim.setInterestPaymentDate(getInterestPaymentDate(record.getInterestPaymentDate(), record.getCouponPaymentKbn(), false));
            
            // 保有商品一覧_国内債券リスト.評価損益にハイフンを設定する
            holdingSecurityListDomesticClaim.setProfitAndLoss("-");

            holdingSecurityListDomesticClaimList.add(holdingSecurityListDomesticClaim);
        }
        data.setHoldingSecurityListDomesticClaim(holdingSecurityListDomesticClaimList);
        
        // 暫定対応#6298のためコメントアウト
        // 国内債券評価損益合計を算出してdataに格納(1件でもNULLなら、合計もNULL)
        // if (holdingSecurityListDomesticClaimList.stream().allMatch(val -> val.getProfitAndLoss() != null)) {
        //     BigDecimal domesticClaimProfitAndLossTotal = holdingSecurityListDomesticClaimList.stream()
        //             .map(val -> new BigDecimal(val.getProfitAndLoss())).reduce((x, y) -> x.add(y)).orElse(null);
        //     if (domesticClaimProfitAndLossTotal != null) {
        //         data.setDomesticClaimProfitAndLossTotal(domesticClaimProfitAndLossTotal.toPlainString());
        //     }
        // }

        // 保有商品一覧_国内債券リスト.国内債券評価損益合計にハイフンを設定する
        data.setDomesticClaimProfitAndLossTotal("-");
        
        /* ====================================================================== */
        /* 外国債券(円建)                                                          */
        /* ====================================================================== */
        
        // SQL005を実行
        IfaPortfolioSql005RequestModel selSql005Req = new IfaPortfolioSql005RequestModel();
        selSql005Req.setButenCode(butenCode);
        selSql005Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql005ResponseModel> selSql005Res = dao.selectIfaPortfolioSql005(selSql005Req);
        
        // SQL005の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityListForeignClaimYenBaseResponseDto> holdingSecurityListForeignClaimYenBaseList = new ArrayList<>();
        for (IfaPortfolioSql005ResponseModel record : selSql005Res.getDataList()) {
            IfaPortfolioA001HoldingSecurityListForeignClaimYenBaseResponseDto holdingSecurityListForeignClaimYenBase = new IfaPortfolioA001HoldingSecurityListForeignClaimYenBaseResponseDto();
            BeanUtils.copyProperties(holdingSecurityListForeignClaimYenBase, record);
            
            // 利払日
            holdingSecurityListForeignClaimYenBase.setInterestPaymentDate(getInterestPaymentDate(record.getInterestPaymentDate(), record.getCouponPaymentKbn(), true));

            // 保有商品一覧_外国債券(円建)リスト.評価損益にハイフンを設定する
            holdingSecurityListForeignClaimYenBase.setProfitAndLoss("-");
            
            holdingSecurityListForeignClaimYenBaseList.add(holdingSecurityListForeignClaimYenBase);
        }
        data.setHoldingSecurityListForeignClaimYenBase(holdingSecurityListForeignClaimYenBaseList);
        
        // 暫定対応#6298のためコメントアウト
        // 外国債券(円建)評価損益合計を算出してdataに格納(1件でもNULLなら、合計もNULL)
        // if (holdingSecurityListForeignClaimYenBaseList.stream().allMatch(val -> val.getProfitAndLoss() != null)) {
        //     BigDecimal foreignClaimYenBaseProfitAndLossTotal = holdingSecurityListForeignClaimYenBaseList.stream()
        //             .map(val -> new BigDecimal(val.getProfitAndLoss())).reduce((x, y) -> x.add(y)).orElse(null);
        //     if (foreignClaimYenBaseProfitAndLossTotal != null) {
        //         data.setForeignClaimYenBaseProfitAndLossTotal(foreignClaimYenBaseProfitAndLossTotal.toPlainString());
        //     }
        // }

        // 保有商品一覧_外国債券(円建)リスト.評価損益合計にハイフンを設定する
        data.setForeignClaimYenBaseProfitAndLossTotal("-");
        
        /* ====================================================================== */
        /* 外国債券(外貨建)                                                        */
        /* ====================================================================== */
        
        // SQL008を実行
        IfaPortfolioSql008RequestModel selSql008Req = new IfaPortfolioSql008RequestModel();
        selSql008Req.setButenCode(butenCode);
        selSql008Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql008ResponseModel> selSql008Res = dao.selectIfaPortfolioSql008(selSql008Req);
        
        // SQL008の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityListForeignClaimForeignResponseDto> holdingSecurityListForeignClaimForeignList = new ArrayList<>();
        for (IfaPortfolioSql008ResponseModel record : selSql008Res.getDataList()) {
            IfaPortfolioA001HoldingSecurityListForeignClaimForeignResponseDto holdingSecurityListForeignClaimForeign = new IfaPortfolioA001HoldingSecurityListForeignClaimForeignResponseDto();
            BeanUtils.copyProperties(holdingSecurityListForeignClaimForeign, record);
            
            // 保有商品一覧_外国債券(外貨建)リスト.評価損益にハイフンを設定する
            holdingSecurityListForeignClaimForeign.setProfitAndLoss("-");

            holdingSecurityListForeignClaimForeignList.add(holdingSecurityListForeignClaimForeign);
        }
        data.setHoldingSecurityListForeignClaimForeign(holdingSecurityListForeignClaimForeignList);
        
        // 暫定対応#6298のためコメントアウト
        // 外国債券(外貨建て)評価損益合計を算出してdataに格納(1件でもNULLなら、合計もNULL)
        // if (holdingSecurityListForeignClaimForeignList.stream().allMatch(val -> val.getProfitAndLoss() != null)) {
        //     BigDecimal foreignClaimForeignProfitAndLossTotal = holdingSecurityListForeignClaimForeignList.stream()
        //             .map(val -> new BigDecimal(val.getProfitAndLoss())).reduce((x, y) -> x.add(y)).orElse(null);
        //     if (foreignClaimForeignProfitAndLossTotal != null) {
        //         data.setForeignClaimForeignProfitAndLossTotal(foreignClaimForeignProfitAndLossTotal.toPlainString());
        //     }
        // }
        
        // 保有商品一覧_外国債券(外貨建)リスト.評価損益合計にハイフンを設定する
        data.setForeignClaimForeignProfitAndLossTotal("-");
        
        /* ====================================================================== */
        /* 外国債券(外貨建仕組債)                                                   */
        /* ====================================================================== */
        
        // SQL009を実行
        IfaPortfolioSql009RequestModel selSql009Req = new IfaPortfolioSql009RequestModel();
        selSql009Req.setButenCode(butenCode);
        selSql009Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql009ResponseModel> selSql009Res = dao.selectIfaPortfolioSql009(selSql009Req);
        
        // SQL009の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityListForeignClaimForeignStructuredBondResponseDto> holdingSecurityListForeignClaimForeignStructuredBondList = new ArrayList<>();
        for (IfaPortfolioSql009ResponseModel record : selSql009Res.getDataList()) {
            IfaPortfolioA001HoldingSecurityListForeignClaimForeignStructuredBondResponseDto holdingSecurityListForeignClaimForeignStructuredBond = new IfaPortfolioA001HoldingSecurityListForeignClaimForeignStructuredBondResponseDto();
            BeanUtils.copyProperties(holdingSecurityListForeignClaimForeignStructuredBond, record);
            
            // 保有商品一覧_外国債券(外貨建仕組債)リスト.評価損益にハイフンを設定する
            holdingSecurityListForeignClaimForeignStructuredBond.setProfitAndLoss("-");
            
            holdingSecurityListForeignClaimForeignStructuredBondList
                    .add(holdingSecurityListForeignClaimForeignStructuredBond);
        }
        data.setHoldingSecurityListForeignClaimForeignStructuredBond(
                holdingSecurityListForeignClaimForeignStructuredBondList);
        
        // 保有商品一覧_外国債券(外貨建仕組債)リスト.評価損益合計にハイフンを設定する
        data.setForeignClaimForeignStructuredBondProfitAndLossTotal("-");
        
        /* ====================================================================== */
        /* 投資信託、外国株式、外貨建MMF、ST（セキュリティ・トークン）                 */
        /* ====================================================================== */
        
        // SQL019を実行
        IfaPortfolioSql019RequestModel selSql019Req = new IfaPortfolioSql019RequestModel();
        selSql019Req.setButenCode(butenCode);
        selSql019Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql019ResponseModel> selSql019Res = dao.selectIfaPortfolioSql019(selSql019Req);
        
        // SQL019の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityListMutualFundResponseDto> holdingSecurityListMutualFundList = new ArrayList<>();
        ArrayList<IfaPortfolioA001HoldingSecurityListForeignStockResponseDto> holdingSecurityListForeignStockList = new ArrayList<>();
        ArrayList<IfaPortfolioA001HoldingSecurityListForeignMmfResponseDto> holdingSecurityListForeignMmfList = new ArrayList<>();
        ArrayList<IfaPortfolioA001HoldingSecurityListSecurityTokenResponseDto> holdingSecurityListSecurityTokenList = new ArrayList<>();
        
        for (IfaPortfolioSql019ResponseModel record : selSql019Res.getDataList()) {
            if (record.getSecurityClassCode() != null) {
                switch (record.getSecurityClassCode()) {
                case "06": // fall througn
                case "08": // fall througn
                case "09":
                    IfaPortfolioA001HoldingSecurityListMutualFundResponseDto holdingSecurityListMutualFund = new IfaPortfolioA001HoldingSecurityListMutualFundResponseDto();
                    BeanUtils.copyProperties(holdingSecurityListMutualFund, record);
                    
                    holdingSecurityListMutualFundList.add(holdingSecurityListMutualFund);
                    break;
                
                case "15":
                    IfaPortfolioA001HoldingSecurityListForeignStockResponseDto holdingSecurityListForeignStock = new IfaPortfolioA001HoldingSecurityListForeignStockResponseDto();
                    BeanUtils.copyProperties(holdingSecurityListForeignStock, record);
                    holdingSecurityListForeignStock.setMarketValueForeign(record.getPrice());
                    
                    holdingSecurityListForeignStockList.add(holdingSecurityListForeignStock);
                    break;
                
                case "16":
                    IfaPortfolioA001HoldingSecurityListForeignMmfResponseDto holdingSecurityListForeignMmf = new IfaPortfolioA001HoldingSecurityListForeignMmfResponseDto();
                    BeanUtils.copyProperties(holdingSecurityListForeignMmf, record);
                    
                    holdingSecurityListForeignMmfList.add(holdingSecurityListForeignMmf);
                    break;
                
                case "33":
                    IfaPortfolioA001HoldingSecurityListSecurityTokenResponseDto holdingSecurityListSecurityToken = new IfaPortfolioA001HoldingSecurityListSecurityTokenResponseDto();
                    BeanUtils.copyProperties(holdingSecurityListSecurityToken, record);
                    
                    holdingSecurityListSecurityTokenList.add(holdingSecurityListSecurityToken);
                    break;
                
                default:
                    break;
                }
            }
        }
        data.setHoldingSecurityListMutualFund(holdingSecurityListMutualFundList);
        data.setHoldingSecurityListForeignStock(holdingSecurityListForeignStockList);
        data.setHoldingSecurityListForeignMmf(holdingSecurityListForeignMmfList);
        data.setHoldingSecurityListSecurityToken(holdingSecurityListSecurityTokenList);

        // 投資信託評価損益合計を算出してdataに格納(投資信託(国内)の1件でもNULLなら合計もNULL｡投資信託(外国)は評価損益合計に含めない)
        // 投資信託評価損益合計に0を設定する｡
        BigDecimal mutualFundProfitAndLossTotal = BigDecimal.ZERO;
        int mutualFundProfitAndLossCount = 0;
        for (IfaPortfolioA001HoldingSecurityListMutualFundResponseDto holdingSecurity : holdingSecurityListMutualFundList) {
            String securityClassCodeStr = holdingSecurity.getSecurityClassCode();
            switch (securityClassCodeStr) {
            case "08": // fall through
            case "09":
                // 商品種別コード = '08' または '09' の場合
                // 以下の固定値についてnullをセットする｡
                holdingSecurity.setOpenPrice(null);     // 保有商品一覧_投資信託リスト.取得単価
                holdingSecurity.setPrice(null);         // 保有商品一覧_投資信託リスト.時価
                holdingSecurity.setValuation(null);     // 保有商品一覧_投資信託リスト.評価額（円貨）
                holdingSecurity.setProfitAndLoss(null); // 保有商品一覧_投資信託リスト.評価損益
                break;
            case "06":
                // 商品種別コード = '06' かつ投資信託評価損益合計がnull以外の場合
                if (mutualFundProfitAndLossTotal != null) {
                    String profitAndLossStr = holdingSecurity.getProfitAndLoss();
                    if (profitAndLossStr == null) {
                        // 保有商品一覧_投資信託リスト.評価損益がnullの場合、投資信託評価損益合計にnullを設定する。
                        mutualFundProfitAndLossTotal = null;
                    } else {
                        // 保有商品一覧_投資信託リスト.評価損益がnull以外の場合、保有商品一覧_投資信託リスト.評価損益に投資信託評価損益合計を足す。
                        mutualFundProfitAndLossTotal = mutualFundProfitAndLossTotal.add(new BigDecimal(profitAndLossStr));
                        mutualFundProfitAndLossCount++;
                    }
                }
                break;
            default:
                // nothing to do.
                break;
            }
        };
        // 商品種別コード = '06' かつ投資信託評価損益合計にnullが含まれる場合
        // または商品種別コード = '06' が1件も無い場合(つまり保有している投資信託が全て｢外国｣の場合)､
        // data.setMutualFundProfitAndLossTota に評価損益合計をセットしない(null になる)｡
        if (mutualFundProfitAndLossTotal != null && mutualFundProfitAndLossCount > 0) {
            data.setMutualFundProfitAndLossTotal(mutualFundProfitAndLossTotal.toPlainString());
        }

        // 外国株式評価損益合計を算出してdataに格納(1件でもNULLなら、合計もNULL)
        if (holdingSecurityListForeignStockList.stream().allMatch(val -> val.getProfitAndLoss() != null)) {
            BigDecimal foreignStockProfitAndLossTotal = holdingSecurityListForeignStockList.stream()
                    .map(val -> new BigDecimal(val.getProfitAndLoss())).reduce((x, y) -> x.add(y)).orElse(null);
            if (foreignStockProfitAndLossTotal != null) {
                data.setForeignStockProfitAndLossTotal(foreignStockProfitAndLossTotal.toPlainString());
            }
        }
        
        // 外貨建てMMF評価損益合計を算出してdataに格納(1件でもNULLなら、合計もNULL)
        if (holdingSecurityListForeignMmfList.stream().allMatch(val -> val.getProfitAndLoss() != null)) {
            BigDecimal foreignMmfProfitAndLossTotal = holdingSecurityListForeignMmfList.stream()
                    .map(val -> new BigDecimal(val.getProfitAndLoss())).reduce((x, y) -> x.add(y)).orElse(null);
            if (foreignMmfProfitAndLossTotal != null) {
                data.setForeignMmfProfitAndLossTotal(foreignMmfProfitAndLossTotal.toPlainString());
            }
        }
        
        // ST評価損益合計を算出してdataに格納(1件でもNULLなら、合計もNULL)
        if (holdingSecurityListSecurityTokenList.stream().allMatch(val -> val.getProfitAndLoss() != null)) {
            BigDecimal securityTokenProfitAndLossTotal = holdingSecurityListSecurityTokenList.stream()
                    .map(val -> new BigDecimal(val.getProfitAndLoss())).reduce((x, y) -> x.add(y)).orElse(null);
            if (securityTokenProfitAndLossTotal != null) {
                data.setSecurityTokenProfitAndLossTotal(securityTokenProfitAndLossTotal.toPlainString());
            }
        }
        
        /* ====================================================================== */
        /* 現金                                                                   */
        /* ====================================================================== */
        
        // SQL010を実行
        IfaPortfolioSql010RequestModel selSql010Req = new IfaPortfolioSql010RequestModel();
        selSql010Req.setButenCode(butenCode);
        selSql010Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql010ResponseModel> selSql010Res = dao.selectIfaPortfolioSql010(selSql010Req);
        
        // SQL010の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityListCashResponseDto> holdingSecurityListCashList = new ArrayList<>();
        for (IfaPortfolioSql010ResponseModel record : selSql010Res.getDataList()) {
            IfaPortfolioA001HoldingSecurityListCashResponseDto holdingSecurityListCash = new IfaPortfolioA001HoldingSecurityListCashResponseDto();
            BeanUtils.copyProperties(holdingSecurityListCash, record);
            
            // 為替レート、評価額(外貨)、評価損益にハイフンを設定
            holdingSecurityListCash.setFxRate("-");
            holdingSecurityListCash.setForeignValuation("-");
            holdingSecurityListCash.setProfitAndLoss("-");
            
            holdingSecurityListCashList.add(holdingSecurityListCash);
        }
        data.setHoldingSecurityListCash(holdingSecurityListCashList);
        
        /* ====================================================================== */
        /* 外貨現金                                                               */
        /* ====================================================================== */
        
        // SQL011を実行
        IfaPortfolioSql011RequestModel selSql011Req = new IfaPortfolioSql011RequestModel();
        selSql011Req.setButenCode(butenCode);
        selSql011Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql011ResponseModel> selSql011Res = dao.selectIfaPortfolioSql011(selSql011Req);
        
        // SQL011の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityListForeignDepositResponseDto> holdingSecurityListForeignDepositList = new ArrayList<>();
        for (IfaPortfolioSql011ResponseModel record : selSql011Res.getDataList()) {
            IfaPortfolioA001HoldingSecurityListForeignDepositResponseDto holdingSecurityListForeignDeposit = new IfaPortfolioA001HoldingSecurityListForeignDepositResponseDto();
            BeanUtils.copyProperties(holdingSecurityListForeignDeposit, record);
            
            // 評価損益に"-"(ハイフン)を設定
            holdingSecurityListForeignDeposit.setProfitAndLoss("-");
            
            holdingSecurityListForeignDepositList.add(holdingSecurityListForeignDeposit);
        }
        data.setHoldingSecurityListForeignDeposit(holdingSecurityListForeignDepositList);
        
        /* ====================================================================== */
        /* 国内信用                                                                */
        /* ====================================================================== */
        
        // 顧客共通情報.信用口座区分（国内）= 1(信用口座)ではない場合、空配列をセット
        if (!StringUtils.equals(IfaCommonUtil.getCustomerCommon().getDomesticMarginAccountType(),
                DOMESTIC_MARGIN_ACCOUNT_TYPE_MARGIN_ACCOUNT)) {
            data.setHoldingSecurityListMarginPosition(new ArrayList<>());
            
        } else { // 顧客共通情報.信用口座区分（国内）= 1(信用口座)の場合のみデータを取得
            // SQL012を実行
            IfaPortfolioSql012RequestModel selSql012Req = new IfaPortfolioSql012RequestModel();
            selSql012Req.setButenCode(butenCode);
            selSql012Req.setAccountNumber(accountNumber);
            DataList<IfaPortfolioSql012ResponseModel> selSql012Res = dao.selectIfaPortfolioSql012(selSql012Req);
            
            // SQL012の結果をdataに格納
            if (selSql012Res.size() > 0) {
                data.setDomesticMarginActualGrntRate(
                        selSql012Res.getDataList().get(0).getDomesticMarginActualGrntRate());
            }
            
            // SQL013を実行
            IfaPortfolioSql013RequestModel selSql013Req = new IfaPortfolioSql013RequestModel();
            selSql013Req.setButenCode(butenCode);
            selSql013Req.setAccountNumber(accountNumber);
            DataList<IfaPortfolioSql013ResponseModel> selSql013Res = dao.selectIfaPortfolioSql013(selSql013Req);
            
            // SQL013の結果をdataに格納
            ArrayList<IfaPortfolioA001HoldingSecurityListMarginPositionResponseDto> holdingSecurityListMarginPositionList = new ArrayList<>();
            for (IfaPortfolioSql013ResponseModel record : selSql013Res.getDataList()) {
                IfaPortfolioA001HoldingSecurityListMarginPositionResponseDto holdingSecurityListMarginPosition = new IfaPortfolioA001HoldingSecurityListMarginPositionResponseDto();
                BeanUtils.copyProperties(holdingSecurityListMarginPosition, record);
                
                holdingSecurityListMarginPositionList.add(holdingSecurityListMarginPosition);
            }
            data.setHoldingSecurityListMarginPosition(holdingSecurityListMarginPositionList);
            
            // 信用建玉評価損益合計を算出してdataに格納(1件でもNULLなら、合計もNULL)
            if (holdingSecurityListMarginPositionList.stream().allMatch(val -> val.getProfitAndLoss() != null)) {
                BigDecimal marginPositionProfitAndLossTotal = holdingSecurityListMarginPositionList.stream()
                        .map(val -> new BigDecimal(val.getProfitAndLoss())).reduce((x, y) -> x.add(y)).orElse(null);
                if (marginPositionProfitAndLossTotal != null) {
                    data.setMarginPositionProfitAndLossTotal(marginPositionProfitAndLossTotal.toPlainString());
                }
            }
        }
        
        /* ====================================================================== */
        /* 米株信用                                                                */
        /* ====================================================================== */
        
        // 顧客共通情報.信用口座区分（外国）= 1(米株信用口座)ではない場合、空配列をセット
        if (!StringUtils.equals(IfaCommonUtil.getCustomerCommon().getForeignMarginAccountType(),
                FOREIGN_MARGIN_ACCOUNT_TYPE_MARGIN_ACCOUNT)) {
            data.setHoldingSecurityListUsStockMarginPositionList(new ArrayList<>());
            
        } else { // 顧客共通情報.信用口座区分（外国）= 1(米株信用口座)の場合のみデータを取得
            // SQL014を実行
            IfaPortfolioSql014RequestModel selSql014Req = new IfaPortfolioSql014RequestModel();
            selSql014Req.setButenCode(butenCode);
            selSql014Req.setAccountNumber(accountNumber);
            DataList<IfaPortfolioSql014ResponseModel> selSql014Res = dao.selectIfaPortfolioSql014(selSql014Req);
            
            // SQL014の結果をdataに格納
            if (selSql014Res.size() > 0) {
                data.setAmericaMarginActualGrntRate(selSql014Res.get(0).getAmericaMarginActualGrntRate());
            }
            
            // SQL015を実行
            IfaPortfolioSql015RequestModel selSql015Req = new IfaPortfolioSql015RequestModel();
            selSql015Req.setButenCode(butenCode);
            selSql015Req.setAccountNumber(accountNumber);
            DataList<IfaPortfolioSql015ResponseModel> selSql015Res = dao.selectIfaPortfolioSql015(selSql015Req);
            
            // SQL015の結果をdataに格納
            ArrayList<IfaPortfolioA001HoldingSecurityListUsStockMarginPositionResponseDto> holdingSecurityListUsStockMarginPositionList = new ArrayList<>();
            for (IfaPortfolioSql015ResponseModel record : selSql015Res.getDataList()) {
                IfaPortfolioA001HoldingSecurityListUsStockMarginPositionResponseDto holdingSecurityListUsStockMarginPosition = new IfaPortfolioA001HoldingSecurityListUsStockMarginPositionResponseDto();
                BeanUtils.copyProperties(holdingSecurityListUsStockMarginPosition, record);
                
                holdingSecurityListUsStockMarginPositionList.add(holdingSecurityListUsStockMarginPosition);
            }
            data.setHoldingSecurityListUsStockMarginPositionList(holdingSecurityListUsStockMarginPositionList);
            
            // 米株信用建玉評価損益合計を算出してdataに格納(1件でもNULLなら、合計もNULL)
            if (holdingSecurityListUsStockMarginPositionList.stream().allMatch(val -> val.getProfitAndLoss() != null)) {
                BigDecimal usStockMarginPositionProfitAndLossTotal = holdingSecurityListUsStockMarginPositionList
                        .stream().map(val -> new BigDecimal(val.getProfitAndLoss())).reduce((x, y) -> x.add(y))
                        .orElse(null);
                if (usStockMarginPositionProfitAndLossTotal != null) {
                    data.setUsStockMarginPositionProfitAndLossTotal(
                            usStockMarginPositionProfitAndLossTotal.toPlainString());
                }
            }
        }
        
        /* ====================================================================== */
        /* SBIラップ口座                                                           */
        /* ====================================================================== */
        
        // SQL017を実行
        IfaPortfolioSql017RequestModel selSql017Req = new IfaPortfolioSql017RequestModel();
        selSql017Req.setButenCode(butenCode);
        selSql017Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql017ResponseModel> selSql017Res = dao.selectIfaPortfolioSql017(selSql017Req);
        
        // SQL017の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityListSbiRapAccountCashResponseDto> holdingSecurityListSbiRapAccountCashList = new ArrayList<>();
        for (IfaPortfolioSql017ResponseModel record : selSql017Res.getDataList()) {
            IfaPortfolioA001HoldingSecurityListSbiRapAccountCashResponseDto holdingSecurityListSbiRapAccountCash = new IfaPortfolioA001HoldingSecurityListSbiRapAccountCashResponseDto();
            BeanUtils.copyProperties(holdingSecurityListSbiRapAccountCash, record);
            
            // 為替レート、評価額(外貨)、評価損益に"-"(ハイフン)を設定
            holdingSecurityListSbiRapAccountCash.setFxRate("-");
            holdingSecurityListSbiRapAccountCash.setForeignValuation("-");
            holdingSecurityListSbiRapAccountCash.setProfitAndLoss("-");
            
            holdingSecurityListSbiRapAccountCash.setSecurityClassCode(SECURITY_CLASS_CODE_WRAP);
            holdingSecurityListSbiRapAccountCashList.add(holdingSecurityListSbiRapAccountCash);
        }
        data.setHoldingSecurityListSbiRapAccountCash(holdingSecurityListSbiRapAccountCashList);
        
        // 現金評価損益合計に"-"(ハイフン)を設定
        data.setCashProfitAndLossTotal("-");
        
        /* ====================================================================== */
        /* トータルリターン                                                        */
        /* ====================================================================== */
        
        // SQL018を実行
        IfaPortfolioSql018RequestModel selSql018Req = new IfaPortfolioSql018RequestModel();
        selSql018Req.setButenCode(butenCode);
        selSql018Req.setAccountNumber(accountNumber);
        DataList<IfaPortfolioSql018ResponseModel> selSql018Res = dao.selectIfaPortfolioSql018(selSql018Req);
        
        // 保有商品一覧_投資信託トータルリターンリスト.保有状況を算出 + SQL018の結果をdataに格納
        ArrayList<IfaPortfolioA001HoldingSecurityListMutualFundTotalReturnResponseDto> holdingSecurityListMutualFundTotalReturnList = new ArrayList<>();
        for (IfaPortfolioSql018ResponseModel record : selSql018Res.getDataList()) {
            IfaPortfolioA001HoldingSecurityListMutualFundTotalReturnResponseDto holdingSecurityListMutualFundTotalReturn = new IfaPortfolioA001HoldingSecurityListMutualFundTotalReturnResponseDto();
            
            // 共通の項目をコピー
            BeanUtils.copyProperties(holdingSecurityListMutualFundTotalReturn, record);

            // NRIコード1～4桁＋"."＋NRIコード6～7桁(△をトリム)
            if (record.getNriCd() != null) {
                String nriCd = record.getNriCd();
                String convertNriCd = nriCd.substring(0, 4) + "." + nriCd.substring(5).trim();
                holdingSecurityListMutualFundTotalReturn.setNriCd(convertNriCd);
            }
            
            // トータルリターン（率）の小数第3位を切り捨てて、格納
            // 値の正負に関わらず絶対値の方向に切り捨てる
            if (record.getTotalReturnRate() != null) {
                BigDecimal totalReturnRateScaled = record.getTotalReturnRate().setScale(2, RoundingMode.DOWN);
                holdingSecurityListMutualFundTotalReturn.setTotalReturnRate(totalReturnRateScaled.toPlainString());
            }
            
            // 保有商品一覧_投資信託トータルリターンリストに追加
            holdingSecurityListMutualFundTotalReturnList.add(holdingSecurityListMutualFundTotalReturn);
        }
        data.setHoldingSecurityListMutualFundTotalReturnList(holdingSecurityListMutualFundTotalReturnList);
        
        // 投資信託トータルリターン評価金額「円」を算出し、dataに格納
        BigDecimal mutualFundTotalReturnValuationTotal = holdingSecurityListMutualFundTotalReturnList.stream()
                .map(val -> new BigDecimal(val.getDepositTransferMarketValueToday())).reduce((x, y) -> x.add(y))
                .orElse(null);
        if (mutualFundTotalReturnValuationTotal != null) {
            data.setMutualFundTotalReturnValuationTotal(mutualFundTotalReturnValuationTotal.toPlainString());
        }
        
        // 投資信託トータルリターン売却金額「円」を算出し、dataに格納
        BigDecimal sellPriceTotal = holdingSecurityListMutualFundTotalReturnList.stream()
                .map(val -> new BigDecimal(val.getAmountSellRedemptionDeliverOutTotal())).reduce((x, y) -> x.add(y))
                .orElse(null);
        if (sellPriceTotal != null) {
            data.setSellPriceTotal(sellPriceTotal.toPlainString());
        }
        
        // 投資信託トータルリターン分配金額「円」を算出し、dataに格納
        BigDecimal dividendTotal = holdingSecurityListMutualFundTotalReturnList.stream()
                .map(val -> new BigDecimal(val.getCouponRevenueTotal())).reduce((x, y) -> x.add(y)).orElse(null);
        if (dividendTotal != null) {
            data.setDividendTotal(dividendTotal.toPlainString());
        }
        
        // 投資信託トータルリターン買付金額「円」合計を算出し、dataに格納
        BigDecimal buyPriceTotal = holdingSecurityListMutualFundTotalReturnList.stream()
                .map(val -> new BigDecimal(val.getAmountBuyReinvestSubscriptDeliverInTotal()))
                .reduce((x, y) -> x.add(y)).orElse(null);
        if (buyPriceTotal != null) {
            data.setBuyPriceTotal(buyPriceTotal.toPlainString());
        }
        
        // 投資信託トータルリターントータルリターン「円」合計を算出し、dataに格納
        BigDecimal totalReturnYenTotal = holdingSecurityListMutualFundTotalReturnList.stream()
                .map(val -> new BigDecimal(val.getTotalReturnYen())).reduce((x, y) -> x.add(y)).orElse(null);
        if (totalReturnYenTotal != null) {
            data.setTotalReturnYenTotal(totalReturnYenTotal.toPlainString());
        }
        
        //  投資信託トータルリターントータルリターン「率」(小数点第3位以下切り捨て)を算出し、dataに格納
        if (buyPriceTotal != null && totalReturnYenTotal != null) {
            
            // ゼロ除算にならない場合
            // 値の正負に関わらず絶対値の方向に切り捨てる
            if (buyPriceTotal.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal totalReturnRateTotal = totalReturnYenTotal.multiply(new BigDecimal("100"))
                        .divide(buyPriceTotal, 2, RoundingMode.DOWN);
                data.setTotalReturnRateTotal(totalReturnRateTotal.toPlainString());
                
            } else { // ゼロ除算の場合、ゼロをセットする
                data.setTotalReturnRateTotal("0.00");
            }
            
        }
        
        /* ====================================================================== */
        /* 正常終了のレスポンスを返す。                                              */
        /* ====================================================================== */
        DataList<IfaPortfolioA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(Arrays.asList(data),
                ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(), "");
        
        return dtoRes;
    }
    
    private List<IfaPortfolioSql001ResponseModel> convertSummary(List<IfaPortfolioSql001ResponseModel> srcList) {
        
        LOGGER.debug("convertSummary:src={}", srcList);

        // 03(国内債券)、04(外国債券(円建))、07(外国債券(外貨建))について、資産状況サマリリスト.評価損益にnullをセット
        for (IfaPortfolioSql001ResponseModel record : srcList) {
            if (StringUtils.equals(record.getSecurityClassCode(), SECURITY_CLASS_CODE_DOMESTIC_CLAIN) ||
                StringUtils.equals(record.getSecurityClassCode(), SECURITY_CLASS_CODE_FOREIGN_CLAIN_DOMESTIC) ||
                StringUtils.equals(record.getSecurityClassCode(), SECURITY_CLASS_CODE_FOREIGN_CLAIN_FOREIGN)) {
                    record.setProfitAndLoss(null);
            }
        }

        LOGGER.debug("convertSummary:dst={}", srcList);
        return srcList;
    }

    /**
     * 利払日 算出
     *
     * @param interestPaymentDate SQL003.利払日/SQL005.利払日
     * @param couponPaymentKbn SQL003.利払区分/SQL005.利払区分
     * @param isSql005 SQL005から呼び出されているか
     * @return 利払日
     */
    private String getInterestPaymentDate(String interestPaymentDate, String couponPaymentKbn, Boolean isSql005) {
        // SQL005の利払区分にはスペーストリムを行う
        if (isSql005 && couponPaymentKbn != null) {
            couponPaymentKbn = couponPaymentKbn.trim();
        }

        // nullを空文字に変換する
        interestPaymentDate = StringUtil.nullToEmpty(interestPaymentDate);

        // 以下の条件を満たさない場合、SQL.利払日をセットする
        String result = interestPaymentDate;
        
        // 利払区分が"1"の場合
        if ("1".equals(couponPaymentKbn)) {
            result = INTERSET_PAYMENT_DATE_TIME_PER_YEAR_1 + interestPaymentDate;
        // 利払区分が"2"の場合
        } else if ("2".equals(couponPaymentKbn)) {
            result = INTERSET_PAYMENT_DATE_TIME_PER_YEAR_2 + interestPaymentDate;
        // 利払区分が"3"の場合
        } else if ("3".equals(couponPaymentKbn)) {
            result = INTERSET_PAYMENT_DATE_TIME_PER_YEAR_4 + interestPaymentDate;
        // 利払区分が"4"の場合
        } else if ("4".equals(couponPaymentKbn)) {
            // 利払日の日付が一つ（MM/DDのみ）の場合
            if (!StringUtil.isNullOrEmpty(interestPaymentDate) && !interestPaymentDate.contains(",")) {
                result = INTERSET_PAYMENT_DATE_TIME_PER_YEAR_12 + extractDayAsString(interestPaymentDate) + "日";
            } else {
                result = INTERSET_PAYMENT_DATE_TIME_PER_YEAR_12;
            }

        } else if ("5".equals(couponPaymentKbn)) {
            // 利払日の日付が一つ（MM/DDのみ）の場合
            if (!StringUtil.isNullOrEmpty(interestPaymentDate) && !interestPaymentDate.contains(",")) {
                // MM（数値変換後）が偶数の場合
                if (isEvenMonth(interestPaymentDate)) {
                    result = INTERSET_PAYMENT_DATE_TIME_PER_YEAR_6_EVEN + extractDayAsString(interestPaymentDate) + "日";
                // MM（数値変換後）が奇数の場合
                } else {
                    result = INTERSET_PAYMENT_DATE_TIME_PER_YEAR_6_ODD + extractDayAsString(interestPaymentDate) + "日";
                }
            // 上記以外
            } else {
                result = INTERSET_PAYMENT_DATE_TIME_PER_YEAR_6;
            }
        // 利払区分が"6"の場合
        } else if ("6".equals(couponPaymentKbn) && isSql005) {
            result = INTERSET_PAYMENT_DATE_TIME_PER_YEAR_3 + interestPaymentDate;
        }
        
        return result;
    }

    /**
     * MM/DD形式の文字列のMM部分が偶数月であるかを判定する
     *
     * @param mmdd MM/DD形式の文字列
     * @return 偶数：True, 奇数：False
     */
    private Boolean isEvenMonth(String mmdd) {
        if (mmdd == null || mmdd.length() < 5 || mmdd.charAt(2) != '/') {
            // MM/DD 形式でない場合は false を返す
            return false;
        }

        // 先頭の2文字を数値に変換
        try {
            int month = Integer.parseInt(mmdd.substring(0, 2));
            return month % 2 == 0;

        } catch (NumberFormatException e) {
            // 数値に変換できない場合は false を返す
            return false;
        }
    }

    /**
     * MM/DD形式の文字列のDD部分を抽出する
     *
     * @param mmdd MM/DD形式の文字列
     * @return MM/DD形式の文字列のDD部分
     */
    private String extractDayAsString(String mmdd) {

        if (mmdd == null || mmdd.length() < 5 || mmdd.charAt(2) != '/') {
            return "";
        }

        return mmdd.substring(3);
    }
}
