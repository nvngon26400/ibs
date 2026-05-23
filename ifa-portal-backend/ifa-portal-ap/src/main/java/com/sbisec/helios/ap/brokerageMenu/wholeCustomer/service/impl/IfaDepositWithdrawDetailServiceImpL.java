package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct032;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaDepositWithdrawDetailDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDepositWithdrawDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDepositWithdrawDetailSql001RequestModel.WithdrawBrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDepositWithdrawDetailSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailCsvItems;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailTradingCourse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaDepositWithdrawDetailService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaDepositWithdrawDetailCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020302_0203-01
 * 画面名：入出金明細
 *
 * @author 
 */
@Component(value = "cmpIfaDepositWithdrawDetailService")
public class IfaDepositWithdrawDetailServiceImpL implements IfaDepositWithdrawDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDepositWithdrawDetailServiceImpL.class);
    
    @Autowired
    private IfaDepositWithdrawDetailDao dao;

    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct032 fct032;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private ComplianceService complianceService;
    
    @Autowired
    private CodeListService codeListService;
    
    /** "取引コース" */
    private static final String COURSE = "取引コース";
    
    /** "期間指定" */
    private static final String PERIOD = "期間指定";
    
    /** "過去2年間の範囲で6ヶ月" */
    private static final String PERIOD_MESSAGE = "過去2年間の範囲で6ヶ月";
    
    /** 摘要に表示する日付、変換失敗した場合のデフォルト */
    private static final String DEF_DATE = "--/--/--";
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB020302_0203-01";
    
    /** 区分.契約締結前交付書面コード */
    private static final String PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    
    private enum MessageId {
        
        // errors.selected
        ERRORS_SELECTED("errors.selected"),
        // errors.dateRange
        ERRORS_DATERANGE("errors.dateRange"),
        // errors.cmn.ifaAgentCodes.notExist
        ERRORS_NOTEXIST("errors.cmn.ifaAgentCodes.notExist"),
        // errors.dataList.notFound
        ERRORS_DATALIST_NOT_FOUND("errors.dataList.notfound"),
        // warnings.dataList.overMaxRownum
        WARNINGS_OVERMAXROWNUM("warnings.dataList.overMaxRownum"),
        // warnings.dataList.csv.overMaxRownum
        WARNINGS_CSV_OVERMAXROWNUM("warnings.dataList.csv.overMaxRownum");
        
        private String key;
        
        private MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaDepositWithdrawDetailA002RequestDto
     * Dto レスポンス：IfaDepositWithdrawDetailA002ResponseDto
     * model リクエスト：IfaDepositWithdrawDetailSql001RequestModel
     * model レスポンス：IfaDepositWithdrawDetailSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaDepositWithdrawDetailA002ResponseDto> displayA002(IfaDepositWithdrawDetailA002RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaDepositWithdrawDetailA002ResponseDto> dtoRes = new DataList<IfaDepositWithdrawDetailA002ResponseDto>();
        List<IfaDepositWithdrawDetailA002ResponseDto> resDtoList = new ArrayList<IfaDepositWithdrawDetailA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDepositWithdrawDetailServiceImplL.displayA002");
        }
        
        // ① リクエスト.取引コースのチェックを行う。
        // リクエスト.取引コースに値が未設定の場合：メッセージを表示し、処理終了。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            // エラー(errors.selected)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_SELECTED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] { COURSE }));
            return dtoRes;
        }
        
        // ② リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい　または
        // リクエスト.期間指定（From）がシステム日付-2年より小さい　または
        // リクエスト.期間指定（To）がシステム日付-2年より小さい　場合：メッセージを表示し、処理終了。
        // 日付取得
        String systemDate = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD);
        LocalDate now = LocalDate.parse(systemDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate fromDate = LocalDate.parse(dtoReq.getPeriodYmFrom(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate toDate = LocalDate.parse(dtoReq.getPeriodYmTo(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (toDate.minusMonths(6).compareTo(fromDate) > 0 || now.minusYears(2).compareTo(fromDate) > 0
                || now.minusYears(2).compareTo(toDate) > 0) {
            // エラー(errors.dateRange)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_DATERANGE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATERANGE.key, new String[] { PERIOD, PERIOD_MESSAGE }));
            return dtoRes;
        }
        
        OutputFct030Dto fct030Res = new OutputFct030Dto();
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        if (!StringUtils.equals(IfaCommonUtil.getUserAccount().getPrivId(), "1")) {
            // ③ FCT030実行
            fct030Res = fct030.getData(new InputFct030Dto());
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (ObjectUtils.isEmpty(fct030Res.getBrokerChargeList()) || fct030Res.getBrokerChargeList().size() == 0) {
                // エラー( errors.cmn.ifaAgentCodes.notExist)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOTEXIST.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_NOTEXIST.key));
                return dtoRes;
            }
        }
        
        // SQL001リクエスト設定
        IfaDepositWithdrawDetailSql001RequestModel selSql001Req = new IfaDepositWithdrawDetailSql001RequestModel();
        setSqlParameter(selSql001Req, splitBrokerCode(dtoReq.getBrokerCode()), dtoReq.getChkBrokerCodeExclude(),
                dtoReq.getBranchCode(), dtoReq.getEmpCode(), dtoReq.getButenCode(), dtoReq.getAccountNumber(),
                dtoReq.getCustomerNameKanjiKana(), dtoReq.getCustomerNameKanjiKanaTerms(), dtoReq.getCourse(),
                dtoReq.getDepositWithdrawDetailType(), dtoReq.getPeriodYmFrom(), dtoReq.getPeriodYmTo(),
                IfaCommonUtil.getUserAccount().getPrivId(), null, fct030Res.getBrokerChargeList());
        // ④ 入出金明細情報を検索する。※最大取得件数を5001件とする。
        DataList<IfaDepositWithdrawDetailSql001ResponseModel> selSql001Res = new DataList<IfaDepositWithdrawDetailSql001ResponseModel>();
        try {
            selSql001Res = dao.selectIfaDepositWithdrawDetailSql001(selSql001Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaDepositWithdrawDetailServiceImplL.displayA002.SQL001");
            }
            throw e;
        }
        
        // SQL001.総件数が0件の場合、0件メッセージをセットする。
        IfaDepositWithdrawDetailA002ResponseDto resDto = new IfaDepositWithdrawDetailA002ResponseDto();
        if (ObjectUtils.isEmpty(selSql001Res.getDataList())) {
            dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    MessageId.ERRORS_DATALIST_NOT_FOUND.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOT_FOUND.key)
            );
            
            return dtoRes;
        }

        // レスポンス_入出金明細設定
        List<IfaDepositWithdrawDetailCsvItems> depositWithdrawDetail = new ArrayList<IfaDepositWithdrawDetailCsvItems>();
        for (int i = 0; i < Math.min(selSql001Res.size(), 5000); i++) {
            IfaDepositWithdrawDetailSql001ResponseModel model = selSql001Res.get(i);
            depositWithdrawDetail.add(setResponse(model, null));
        }
        resDto.setDepositWithdrawDetail(depositWithdrawDetail);
        
        // レスポンス設定
        // 取得件数
        int total = 0;
        if (selSql001Res.size() > 0) {
            total = Integer.valueOf(selSql001Res.getDataList().get(0).getTotal());
        }
        resDto.setAcquireCount(String.valueOf(total));
        // データ件数
        if (total > 5000) {
            resDto.setDataCount("5000");
        } else {
            resDto.setDataCount(String.valueOf(total));
        }
        
        // ⑦ 合計額、件数を集計する。
        // 出金額合計 合計額
        BigDecimal withdrawTotalAmount = BigDecimal.ZERO;
        // 出金額合計 件数
        Integer withdrawTotalCount = 0;
        // 入金額合計 合計額
        BigDecimal depositTotalAmount = BigDecimal.ZERO;
        // 入金額合計 件数
        Integer depositTotalCount = 0;
        // 振替出金額合計 合計額
        BigDecimal transferWithdrawTotalAmount = BigDecimal.ZERO;
        // 振替出金額合計 件数
        Integer transferWithdrawTotalCount = 0;
        // 振替入金額合計 合計額
        BigDecimal transferDepositTotalAmount = BigDecimal.ZERO;
        // 振替入金額合計 件数
        Integer transferDepositTotalCount = 0;
        for (IfaDepositWithdrawDetailCsvItems item : depositWithdrawDetail) {
            if (StringUtils.equals(item.getTrade(), "出金")) {
                withdrawTotalAmount = withdrawTotalAmount.add(item.getDeliveryAmount() != null ? item.getDeliveryAmount() : BigDecimal.ZERO);
                withdrawTotalCount++;
            } else if (StringUtils.equals(item.getTrade(), "入金")) {
                depositTotalAmount = depositTotalAmount.add(item.getDeliveryAmount() != null ? item.getDeliveryAmount() : BigDecimal.ZERO);
                depositTotalCount++;
            } else if (StringUtils.equals(item.getTrade(), "振替出金")) {
                transferWithdrawTotalAmount = transferWithdrawTotalAmount
                        .add(item.getDeliveryAmount() != null ? item.getDeliveryAmount() : BigDecimal.ZERO);
                transferWithdrawTotalCount++;
            } else if (StringUtils.equals(item.getTrade(), "振替入金")) {
                transferDepositTotalAmount = transferDepositTotalAmount
                        .add(item.getDeliveryAmount() != null ? item.getDeliveryAmount() : BigDecimal.ZERO);
                transferDepositTotalCount++;
            }
        }
        resDto.setWithdrawTotalAmount(withdrawTotalAmount.toPlainString());
        resDto.setWithdrawTotalCount(withdrawTotalCount.toString());
        resDto.setDepositTotalAmount(depositTotalAmount.toPlainString());
        resDto.setDepositTotalCount(depositTotalCount.toString());
        resDto.setTransferWithdrawTotalAmount(transferWithdrawTotalAmount.toPlainString());
        resDto.setTransferWithdrawTotalCount(transferWithdrawTotalCount.toString());
        resDto.setTransferDepositTotalAmount(transferDepositTotalAmount.toPlainString());
        resDto.setTransferDepositTotalCount(transferDepositTotalCount.toString());
        resDtoList.add(resDto);
        
        // SQL001.総件数が5001件の場合、件数超過メッセージをセットする。
        if (5000 < total) {
            // ワーニングメッセージを取得する
            String message = IfaCommonUtil.getMessage(
                    MessageId.WARNINGS_OVERMAXROWNUM.key,
                    new String[] { "5000", selSql001Res.getDataList().get(0).getTotal() }
            );

            // ワーニングメッセージの（n件ヒット）の部分を削除する。
            String pattern = "（[0-9,]+件ヒット）";
            message = message.replaceFirst(pattern, "");

            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING, MessageId.WARNINGS_OVERMAXROWNUM.key, message);
            dtoRes.setOverMaxRownum(true);
            dtoRes.setTotalSize(total);
            dtoRes.setMaxRownum(5000);
            return dtoRes;
        }
        
        // 正常終了のレスポンス
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaDepositWithdrawDetailA004RequestDto
     * Dto レスポンス：IfaDepositWithdrawDetailA004ResponseDto
     * model リクエスト：IfaDepositWithdrawDetailSql001RequestModel
     * model レスポンス：IfaDepositWithdrawDetailSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaDepositWithdrawDetailA004ResponseDto> csvOutputA004(
            IfaDepositWithdrawDetailA004RequestDto dtoReq, String fwSessionId) throws Exception {
        
        DataList<IfaDepositWithdrawDetailA004ResponseDto> dtoRes = new DataList<IfaDepositWithdrawDetailA004ResponseDto>();
        IfaDepositWithdrawDetailCsvOut csvOut = new IfaDepositWithdrawDetailCsvOut(complianceService);
        DataList<IfaDepositWithdrawDetailCsvItems> exportList = new DataList<IfaDepositWithdrawDetailCsvItems>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDepositWithdrawDetailServiceImplL.csvOutputA004");
        }
        // ① リクエスト.取引コースのチェックを行う。
        // リクエスト.取引コースに値が未設定の場合：メッセージを表示し、処理終了。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            // エラー(errors.selected)を返す
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, MessageId.ERRORS_SELECTED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] { COURSE }));
            return dtoRes;
        }
        
        // ② リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい　または
        // リクエスト.期間指定（From）がシステム日付-2年より小さい　または
        // リクエスト.期間指定（To）がシステム日付-2年より小さい　場合：メッセージを表示し、処理終了。
        // 日付取得
        String systemDate = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD);
        LocalDate now = LocalDate.parse(systemDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate fromDate = LocalDate.parse(dtoReq.getPeriodYmFrom(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate toDate = LocalDate.parse(dtoReq.getPeriodYmTo(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (toDate.minusMonths(6).compareTo(fromDate) > 0 || now.minusYears(2).compareTo(fromDate) > 0
                || now.minusYears(2).compareTo(toDate) > 0) {
            // エラー(errors.dateRange)を返す
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, MessageId.ERRORS_DATERANGE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATERANGE.key, new String[] { PERIOD, PERIOD_MESSAGE }));
            return dtoRes;
        }
        
        OutputFct030Dto fct030Res = new OutputFct030Dto();
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        if (!StringUtils.equals(IfaCommonUtil.getUserAccount().getPrivId(), "1")) {
            // ③ FCT030実行
            fct030Res = fct030.getData(new InputFct030Dto());
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (ObjectUtils.isEmpty(fct030Res.getBrokerChargeList()) || fct030Res.getBrokerChargeList().size() == 0) {
                // エラー( errors.cmn.ifaAgentCodes.notExist)を返す
                dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, MessageId.ERRORS_NOTEXIST.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_NOTEXIST.key));
                dtoRes.setTitle(csvOut.doCreateCsvFile(exportList, fwSessionId, IfaCommonUtil.getUserAccount().getUserId(), null));
                return dtoRes;
            }
        }
        
        //②   CSVダウンロードMAX件数を取得し、+1した値を最大取得件数にセットする。
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(IfaCommonUtil.getUserAccount().getPrivId());
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();
        // 最大取得件数
        String maxAcquireCount = String.valueOf(maxCsvRowNum + 1);
        
        //③   入出金明細情報を検索する。
        IfaDepositWithdrawDetailSql001RequestModel selSql001Req = new IfaDepositWithdrawDetailSql001RequestModel();
        setSqlParameter(selSql001Req, splitBrokerCode(dtoReq.getBrokerCode()), dtoReq.getChkBrokerCodeExclude(),
                dtoReq.getBranchCode(), dtoReq.getEmpCode(), dtoReq.getButenCode(), dtoReq.getAccountNumber(),
                dtoReq.getCustomerNameKanjiKana(), dtoReq.getCustomerNameKanjiKanaTerms(), dtoReq.getCourse(),
                dtoReq.getDepositWithdrawDetailType(), dtoReq.getPeriodYmFrom(), dtoReq.getPeriodYmTo(),
                IfaCommonUtil.getUserAccount().getPrivId(), maxAcquireCount, fct030Res.getBrokerChargeList());
        DataList<IfaDepositWithdrawDetailSql001ResponseModel> selSql001Res = new DataList<IfaDepositWithdrawDetailSql001ResponseModel>();
        try {
            selSql001Res = dao.selectIfaDepositWithdrawDetailSql001(selSql001Req);
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaDepositWithdrawDetailServiceImplL.displayA002.SQL001");
            }
            throw e;
        }
        
        // SQL001.総件数
        int total = 0;
        if (!(selSql001Res.getDataList().size() == 0)) {
            total = Integer.parseInt(selSql001Res.get(0).getTotal());
        }
        
        // SQL001.総件数が0件の場合、0件メッセージをセットする。
        if (total == 0) {
            dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    MessageId.ERRORS_DATALIST_NOT_FOUND.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOT_FOUND.key)
            );
            return dtoRes;
        }

        //⑥   検索結果をCSVファイルを出力する。
        List<IfaDepositWithdrawDetailCsvItems> csvItemsList = new ArrayList<IfaDepositWithdrawDetailCsvItems>();
        // FCT032.営業員コード自動設定判定に沿って出力項目を制御する。
        InputFct032Dto fct032InData = new InputFct032Dto();
        OutputFct032Dto fct032Dto = fct032.getData(fct032InData);
        
        for (int i = 0; i < Math.min(total, maxCsvRowNum); i++) {
            IfaDepositWithdrawDetailSql001ResponseModel sqlRes = selSql001Res.getDataList().get(i);
            IfaDepositWithdrawDetailCsvItems csvItems = setResponse(sqlRes, fct032Dto);
            
            // 取引コース(区分値を文字列に変換する)
            csvItems.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, csvItems.getCourse()));
            
            csvItemsList.add(csvItems);
        }
        exportList.setDataList(csvItemsList);
        String title = csvOut.doCreateCsvFile(exportList, fwSessionId, IfaCommonUtil.getUserAccount().getUserId(), null);
        

        // SQL001.総件数が、CSVダウンロードMAX件数超の場合は、件数超過メッセージを表示する。
        if (maxCsvRowNum < total) {
            // ワーニングメッセージを取得する。。
            String message = IfaCommonUtil.getMessage(
                    MessageId.WARNINGS_CSV_OVERMAXROWNUM.key,
                    new String[] {
                            String.format("%,d", maxCsvRowNum),
                            String.valueOf(total),
                            String.format("%,d", maxCsvRowNum)
                    }
            );

            // ワーニングメッセージの（n件ヒット）の部分を削除する。
            String pattern = "（[0-9,]+件ヒット）";
            message = message.replaceFirst(pattern, "");

            dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.WARNING,
                    MessageId.WARNINGS_CSV_OVERMAXROWNUM.key,
                    message
            );
            dtoRes.setTitle(title);
            dtoRes.setMaxRownum(maxCsvRowNum);
            dtoRes.setTotalSize(total);

            return dtoRes;
        }

        dtoRes = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                ""
        );
        dtoRes.setTitle(title);
        dtoRes.setMaxRownum(total);
        dtoRes.setTotalSize(total);
        
        return dtoRes;
    }
    
    /**
     * SQL001リクエストパラメータ設定
     *
     * @param selSql001Req sql001リクエストパラメータ
     * @param brokerCodeList 仲介業者コード
     * @param chkBrokerCodeExclude 仲介業者除外
     * @param branchCode 支店コード
     * @param empCode 営業員コード
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param customerNameKanjiKana 顧客名(漢字/カナ)
     * @param customerNameKanjiKanaTerms 顧客名(漢字/カナ)_条件
     * @param course 取引コース
     * @param code 区分
     * @param periodYmFrom 期間指定（From)
     * @param periodYmTo 期間指定（To)
     * @param privId ユーザ共通情報.権限コード
     * @param rownum 件数
     * @param brokerChargeList FCT030.仲介業者営業員リスト
     */
    private void setSqlParameter(IfaDepositWithdrawDetailSql001RequestModel selSql001Req, List<String> brokerCodeList,
            String chkBrokerCodeExclude, String branchCode, String empCode, String butenCode, String accountNumber,
            String customerNameKanjiKana, String customerNameKanjiKanaTerms,
            List<IfaDepositWithdrawDetailTradingCourse> course, String code, String periodYmFrom, String periodYmTo,
            String privId, String rownum, List<BrokerCharge> brokerChargeList) {
        
        // 仲介業者コード
        if (!ObjectUtils.isEmpty(brokerCodeList)) {
            selSql001Req.setBrokerCodeList(brokerCodeList);
        }
        // 仲介業者除外
        selSql001Req.setChkBrokerCodeExclude(StringUtil.nullToEmpty(chkBrokerCodeExclude));
        // 支店コード
        selSql001Req.setBranchCode(StringUtil.nullToEmpty(branchCode));
        // 営業員コード
        selSql001Req.setEmpCode(StringUtil.nullToEmpty(empCode));
        // 部店コード
        selSql001Req.setButenCode(StringUtil.nullToEmpty(butenCode));
        // 口座番号
        selSql001Req.setAccountNumber(StringUtil.nullToEmpty(accountNumber));
        // 顧客名(漢字/カナ)
        selSql001Req.setCustomerNameKanjiKana(StringUtil.nullToEmpty(customerNameKanjiKana));
        // 顧客名(漢字/カナ)_条件
        selSql001Req.setCustomerNameKanjiKanaTerms(StringUtil.nullToEmpty(customerNameKanjiKanaTerms));
        // 取引コース
        List<String> idList = new ArrayList<String>();
        if (!ObjectUtils.isEmpty(course)) {
            for (IfaDepositWithdrawDetailTradingCourse trade : course) {
                if (StringUtils.equals(trade.getIsSelected(), "true")) {
                    idList.add(trade.getId());
                }
            }
        }
        selSql001Req.setCourse(ObjectUtils.isEmpty(idList) ? null : idList);
        // 区分
        selSql001Req.setCode(StringUtil.nullToEmpty(code));
        // 期間指定（From)
        selSql001Req.setPeriodYmFrom(StringUtil.nullToEmpty(periodYmFrom));
        // 期間指定（To)
        selSql001Req.setPeriodYmTo(StringUtil.nullToEmpty(periodYmTo));
        // 権限コード
        selSql001Req.setPrivId(StringUtil.nullToEmpty(privId));
        // 件数
        selSql001Req.setRownum(StringUtil.nullToEmpty(rownum));
        // 仲介業者営業員リスト
        List<WithdrawBrokerCharge> sqlBrokerChargeList = new ArrayList<WithdrawBrokerCharge>();
        if (!ObjectUtils.isEmpty(brokerChargeList)) {
            for (BrokerCharge bc : brokerChargeList) {
                WithdrawBrokerCharge withdraw = new WithdrawBrokerCharge();
                withdraw.setBrokerCode(StringUtil.nullToEmpty(bc.getBrokerCode()));
                withdraw.setEmpCode(StringUtil.nullToEmpty(bc.getEmpCode()));
                sqlBrokerChargeList.add(withdraw);
            }
        }
        selSql001Req.setBrokerChargeList(sqlBrokerChargeList);
        
    }
    
    /**
     * 取引区分　項目編集
     *
     * @param targetTable 対象テーブル
     * @param tradeType 計上種別
     * @param tradeCode3 取引コード3
     * @param tradeCodePare 取引補助コード
     * @param accountingCode 勘定コード
     * @return 取引区分(1:入金 / 2:出金 / 3:振替入金 /4:振替出金)
     */
    private int checkPayDepKbn(String targetTable, String tradeType, String tradeCode3, String tradeCodePare,
            String accountingCode) {
        
        //WEB内製 入出金明細(リアル)テーブルから取得した値の場合
        if ("REAL_HDB10".equals(targetTable)) {
            if ("01".equals(tradeType)) {
                return 1;
            }
            if ("31".equals(tradeType)) {
                return 1;
            }
            if ("02".equals(tradeType)) {
                return 2;
            }
            if ("21".equals(tradeType)) {
                return 3;
            }
            if ("22".equals(tradeType)) {
                return 3;
            }
            if ("23".equals(tradeType)) {
                return 3;
            }
            if ("24".equals(tradeType)) {
                return 3;
            }
            if ("11".equals(tradeType)) {
                return 4;
            }
            if ("12".equals(tradeType)) {
                return 4;
            }
            if ("13".equals(tradeType)) {
                return 4;
            }
            if ("14".equals(tradeType)) {
                return 4;
            }
            
            //WEB内製 入出金明細(履歴)テーブルから取得した値の場合
        } else if ("HIST_HDB10".equals(targetTable)) {
            if (("01006".equals(tradeCodePare) && ("389031000".equals(accountingCode)
                    || "389031001".equals(accountingCode) || "389032010".equals(accountingCode)
                    || "389032020".equals(accountingCode) || "389032030".equals(accountingCode)
                    || "389032000".equals(accountingCode) || "320000103".equals(accountingCode)
                    || "320000105".equals(accountingCode)))
                    || ("01022".equals(tradeCodePare) || "01026".equals(tradeCodePare))) {
                if ("1".equals(tradeCode3)) {
                    return 3;
                } else if ("0".equals(tradeCode3)) {
                    return 4;
                }
            } else {
                if ("1".equals(tradeCode3)) {
                    return 1;
                } else if ("0".equals(tradeCode3)) {
                    return 2;
                }
            }
            //WEB内製 MRF取引履歴テーブルから取得した値の場合
        } else if ("HIST_HDB23".equals(targetTable)) {
            return 1;
        }
        return 0;
    }
    
    /**
     * 対象テーブル・計上種別・適用概要・取引コード・取引補助コード・商品コード・
     * 勘定コード・対客報告年月日・仕訳番号・受渡日から、画面表示文字列に変換する
     *
     * @param  depositRecordOutDto  入出金明細_OUTDTO
     * @return 画面表示文字列
     */
    private String getDispAbstract(IfaDepositWithdrawDetailSql001ResponseModel depositRecordOutDto) {
        
        String ret = "";
        String nisaTax = "";

        // 対象テーブル
        String targetTable = "";
        targetTable = StringUtil.nullToEmpty(depositRecordOutDto.getTargetTbl());

        // 計上種別
        String tradeType = "";
        tradeType = StringUtil.nullToEmpty(depositRecordOutDto.getTradeType());
        
        // 取引コード
        String torihikiCode = "";
        torihikiCode = StringUtil.nullToEmpty(depositRecordOutDto.getTradeCode());
        
        // 取引補助コード
        String torihikiHojyoKbn = "";
        torihikiHojyoKbn = StringUtil.nullToEmpty(depositRecordOutDto.getTradeCodePare());
        
        // 商品コード
        String productCode = "";
        productCode = StringUtil.nullToEmpty(depositRecordOutDto.getProductCode());
        
        // 勘定コード
        String kanjyouCode = "";
        kanjyouCode = StringUtil.nullToEmpty(depositRecordOutDto.getAccountingCode());
        
        // 対客報告年月日
        String reportDate = "";
        reportDate = StringUtil.nullToEmpty(depositRecordOutDto.getReportDate());
        
        // 仕訳番号
        String postingNo = "";
        postingNo = StringUtil.nullToEmpty(depositRecordOutDto.getPostingNo());
        
        // 受渡日
        String settleDate = "";
        settleDate = StringUtil.nullToEmpty(depositRecordOutDto.getSettleDate());


        //WEB内製 入出金明細(リアル)テーブルから取得した値の場合
        if ("REAL_HDB10".equals(targetTable)) {
            if ("11".equals(tradeType) || "21".equals(tradeType)) {
                return "外国為替保証金";
            } else if ("12".equals(tradeType) || "22".equals(tradeType)) {
                return "先物・オプション証拠金振替";
            } else if ("13".equals(tradeType) || "23".equals(tradeType)) {
                return "CFD（くりっく株365）証拠金振替";
            } else if ("14".equals(tradeType)) {
                return "金・プラチナ取引口座へ振替出金";
            } else if ("24".equals(tradeType)) {
                return "金・プラチナ取引口座より振替入金";
            } else if ("31".equals(tradeType)) {
                return "振込入金";
            }
        }
        
        if (torihikiCode.length() < 5 || torihikiHojyoKbn.length() < 5 || productCode.length() < 5) {
            return ret;
        }
        
        if (torihikiCode.charAt(4) == '1') {
            nisaTax = "（NISA：非課税）";
        }
        
        //新銀行の入出金は勘定コードのみで判定する
        //私募債募集の出金は勘定コードのみで判定する
        if (kanjyouCode.equals("211050600")) {
            ret = "追加保証金等自動振替入金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050700")) {
            ret = "SBIハイブリッド預金より自動振替入金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050800")) {
            ret = "SBIハイブリッド預金へ自動振替出金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050820")) {
            ret = "リアルタイム出金　SBI新生銀行" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050801")) {
            ret = "SBI新生銀行へ自動出金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050701")) {
            ret = "SBI新生銀行より投信積立自動入金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050822")) {
            ret = "SBIハイパー預金より自動振替入金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050823")) {
            ret = "SBIハイパー預金へ自動振替出金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050824")) {
            ret = "SBIハイパー預金自動振替入金取消" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050825")) {
            ret = "SBIハイパー預金自動振替出金取消" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050826")) {
            ret = "SBIハイパー預金より自動振替入金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050827")) {
            ret = "追加保証金等自動振替入金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("613011001")) {
            ret = "貸株金利" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("326030226")) {
            ret = "貸株配当金相当額" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050810")) {
            ret = "SBIハイブリッド預金自動振替出金取消" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050710")) {
            ret = "SBIハイブリッド預金自動振替入金取消" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("345090710")) {
            ret = "不動産担保ローン（匿名組合）" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("324410320")) {
            ret = "不動産担保ローン（匿名組合）返金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("324410330")) {
            ret = "不動産担保ローン（匿名組合）配当金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("324410340")) {
            ret = "不動産担保ローン（匿名組合）償還金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211051100")) {
            ret = "定時定額買付金の入金" + nisaTax;
            return ret;
        } else if (kanjyouCode.equals("211050210")) {
            ret = "ご紹介プログラムによる特典の受取" + nisaTax;
            return ret;
        }
        
        if (torihikiCode.charAt(0) == '2') {
            if (torihikiCode.charAt(1) == '2') {
                if (torihikiCode.charAt(2) == '0' || torihikiCode.charAt(2) == '1') {
                    if (torihikiHojyoKbn.equals("14001")) {
                        ret = "信用配当金";
                    } else if (torihikiHojyoKbn.equals("15000")) {
                        ret = "外証配当金";
                    }
                    if (torihikiCode.charAt(2) == '1') {
                        if (torihikiHojyoKbn.equals("13013")) {
                            ret = "資本剰余金配当/みなし譲渡";
                        }
                    }
                }
                if (torihikiCode.charAt(2) == '1') {
                    if (torihikiHojyoKbn.equals("13010")) {
                        ret = "株式配当金";
                    }
                }
            } else if (torihikiCode.charAt(1) == '3') {
                if (torihikiCode.charAt(2) == '0' || torihikiCode.charAt(2) == '1') {
                    if (torihikiHojyoKbn.equals("14002")) {
                        ret = "信用権利処理代金";
                    }
                }
            } else if (torihikiCode.charAt(1) == '4') {
                if (torihikiCode.charAt(2) == '1') {
                    if (torihikiCode.charAt(3) == '2') {
                        if (torihikiHojyoKbn.equals("01009")) {
                            ret = "端数処理代金";
                        }
                    }
                }
            } else if (torihikiCode.charAt(1) == '6') {
                if (torihikiCode.charAt(2) == '1') {
                    if (torihikiHojyoKbn.equals("12000")) {
                        ret = "償還金";
                    } else if (torihikiHojyoKbn.equals("12001")) {
                        ret = "償還金（一般口へ乗換）";
                    } else if (torihikiHojyoKbn.equals("12002")) {
                        ret = "償還金（累投口へ乗換）";
                    } else if (torihikiHojyoKbn.equals("15000")) {
                        ret = "外証配当金（償還金）";
                    }
                }
            } else if (torihikiCode.charAt(1) == '7') {
                if (torihikiCode.charAt(2) == '0' || torihikiCode.charAt(2) == '1') {
                    if (torihikiHojyoKbn.equals("13000")) {
                        ret = "利金収益金";
                    } else if (torihikiHojyoKbn.equals("13010")) {
                        ret = "利金収益金（配当金）";
                    } else if (torihikiHojyoKbn.equals("13011")) {
                        ret = "利金収益金（優待処分代）";
                    } else if (torihikiHojyoKbn.equals("13012")) {
                        ret = "利金収益金（合併交付金）";
                    } else if (torihikiHojyoKbn.equals("15000")) {
                        ret = "外証配当金（利金/収益金）";
                    }
                }
            } else if (torihikiCode.charAt(1) == '0') {
                if (torihikiCode.charAt(2) == '0' || torihikiCode.charAt(2) == '1') {
                    if (torihikiCode.charAt(2) == '0' && productCode.equals("Q 1TY")) {
                        ret = "中期国債ファンド";
                    } else if (torihikiCode.charAt(2) == '0' && productCode.equals("Q 1LM")) {
                        ret = "ＭＭＦ";
                    } else if (torihikiHojyoKbn.equals("01000")) {
                        ret = "現金入出金";
                    }
                }
            } else if (torihikiCode.charAt(1) == '1') {
                if (torihikiCode.charAt(2) == '0' || torihikiCode.charAt(2) == '1') {
                    if (torihikiCode.charAt(2) == '0' && torihikiHojyoKbn.equals("01003")) {
                        ret = "銀行振込(送金)";
                    } else if (torihikiCode.charAt(2) == '0' && torihikiHojyoKbn.equals("01004")) {
                        ret = "小切手出金";
                    } else if (torihikiCode.charAt(2) == '0' && torihikiHojyoKbn.equals("01007")) {
                        ret = "譲渡益税源泉徴収金";
                    } else if (torihikiCode.charAt(2) == '1' && torihikiHojyoKbn.equals("01003")) {
                        ret = "振込入金";
                    } else if (torihikiCode.charAt(2) == '1' && torihikiHojyoKbn.equals("01004")) {
                        ret = "小切手入金";
                    } else if (torihikiCode.charAt(2) == '1' && torihikiHojyoKbn.equals("99999")) {
                        ret = "譲渡益税月末調整金";
                    } else if (torihikiCode.charAt(2) == '1' && torihikiHojyoKbn.equals("01008")) {
                        ret = "譲渡益税還付金";
                    } else {
                        if (torihikiHojyoKbn.equals("01005")) {
                            ret = "送金料";
                        } else if (torihikiHojyoKbn.equals("01006")) {
                            if (kanjyouCode.equals("156016000") || kanjyouCode.equals("353016000")) {
                                if ("00000000".equals(reportDate)) {
                                    ret = "SBI証券ATMカード";
                                } else {
                                    ret = "SBI証券ATMカード（お取引日" + reportDateFormat(reportDate, "yyyy/MM/dd", "----/--/--")
                                            + "）";
                                }
                            } else if (kanjyouCode.equals("724433010")) {
                                if ("00000000".equals(reportDate)) {
                                    ret = "ATM手数料";
                                } else {
                                    ret = "ATM手数料（お取引日" + reportDateFormat(reportDate, "yyyy/MM/dd", "----/--/--")
                                            + "）";
                                }
                            } else if (torihikiCode.charAt(2) == '0' && kanjyouCode.equals("211051200")) {
                                ret = "新株予約権権利行使代金の払込";
                            } else if (kanjyouCode.equals("389031000") || kanjyouCode.equals("389031001")) {
                                ret = "外国為替保証金";
                            } else if (kanjyouCode.equals("389032010")) {
                                ret = "株価指数CFD保証金";
                            } else if (kanjyouCode.equals("389032020")) {
                                ret = "債券CFD保証金";
                            } else if (kanjyouCode.equals("389032030")) {
                                ret = "商品CFD保証金";
                            } else if (kanjyouCode.equals("211050500")) {
                                ret = "証明書発行手数料";
                            } else if (torihikiCode.charAt(2) == '1' && kanjyouCode.equals("761040100")) {
                                ret = "有料情報返金";
                            } else if (torihikiCode.charAt(2) == '0' && kanjyouCode.equals("761040100")) {
                                ret = "有料情報購入";
                            } else if (kanjyouCode.equals("389032000")) {
                                //CFD（くりっく株365）証拠金振替
                                ret = "CFD（くりっく株365）証拠金振替";
                            } else if (torihikiCode.charAt(2) == '1' && kanjyouCode.equals("320000103")) {
                                //金・プラチナ取引口座より振替入金
                                ret = "金・プラチナ取引口座より振替入金";
                            } else if (torihikiCode.charAt(2) == '0' && kanjyouCode.equals("320000103")) {
                                //金・プラチナ取引口座へ振替出金
                                ret = "金・プラチナ取引口座へ振替出金";
                            } else if (kanjyouCode.equals("211051004")) {
                                //Vポイント(旧T)より自動振替入金
                                ret = "Vポイント(旧T)より自動振替入金";
                            } else if (kanjyouCode.equals("320000105")) {
                                //WealthNavi for SBI証券取引
                                ret = "WealthNavi for SBI証券取引";
                            } else if (kanjyouCode.equals("751161540")) {
                                // 勘定コード =751161540 (特約付株券等貸借取引　特約権料) の場合
                                ret = "特約権料";
                            } else if (kanjyouCode.equals("327012000")) {
                                // 勘定コード =327012000 (譲渡益税預り金　所得税) の場合
                                ret = "譲渡益税預り金所得税";
                            } else if (kanjyouCode.equals("324331110")) {
                                // 勘定コード =324331110 (代行事務預り金　住民税　委託　国内上場株式等) の場合
                                ret = "代預　住民税委託国内上場株式等";
                            } else if (kanjyouCode.equals("324334000")) {
                                // 勘定コード =324334000 (代行事務預り金　所得税　特定口座（配当）) の場合
                                ret = "代預　所得税　特定口座（配当）";
                            } else if (kanjyouCode.equals("327012100")) {
                                // 勘定コード =327012100 (譲渡益税預り金　住民税　譲渡所得割) の場合
                                ret = "住民税譲渡所得割";
                            } else if (kanjyouCode.equals("724436800")) {
                                // 勘定コード =724436800 (その他受入手数料　投資一任運用報酬) の場合
                                ret = "投資一任運用報酬";
                            } else if (kanjyouCode.equals("211051006")) {
                                // 勘定コード =211051006 (振替勘定　Ｐｏｎｔａポイント入金) の場合
                                ret = "Ｐｏｎｔａポイントより自動振替";
                            } else if (kanjyouCode.equals("724190700")) {
                                // 勘定コード =724190700 (その他受入手数料　異名義移管手数料) の場合
                                ret = "株券等出庫手数料";
                            } else if (kanjyouCode.equals("327012300")) {
                                // 勘定コード =327012300 (譲渡益税預り金　所得税　ジュニアＮＩＳＡ) の場合
                                ret = "ジュニアＮＩＳＡ（譲渡）所得税";
                            } else if (kanjyouCode.equals("321070228")) {
                                // 勘定コード =321070228 (顧客からの預り金　継投ＬＴ　汎用累投) の場合
                                ret = "預り金　継投ＬＴ　汎用累投";
                            } else if (kanjyouCode.equals("724190400")) {
                                // 勘定コード =724190400 (その他受入手数料　株券　その他) の場合
                                ret = "手続料";
                            } else if (kanjyouCode.equals("211053600")) {
                                // 勘定コード =211053600 (振替勘定　通貨関連店頭デリバティブ) の場合
                                ret = "通貨デリバティブ決済資金";
                            } else if (kanjyouCode.equals("320000107")) {
                                // 勘定コード =320000107 (投信るいとうお買付預り金振替) の場合
                                ret = "投信るいとうお買付預り金振替";
                            } else if (kanjyouCode.equals("211050400")) {
                                // 勘定コード =211050400 (振替勘定　コムストックローン) の場合
                                ret = "コムストックローン";
                            } else if (kanjyouCode.equals("211051500")) {
                                // 勘定コード =211051500 (振替勘定　持株会精算金) の場合
                                ret = "持株会精算金";
                            } else if (kanjyouCode.equals("320000108")) {
                                // 勘定コード =320000108 (顧客からの預り金　ファンドラップ) の場合
                                ret = "預り金　ファンドラップ";
                            } else if (kanjyouCode.equals("324334200")) {
                                // 勘定コード =324334200 (代行事務預り金　所得税　ジュニアＮＩＳＡ　配当等) の場合
                                ret = "ジュニアＮＩＳＡ（配当）所得税";
                            } else if (kanjyouCode.equals("324331020")) {
                                // 勘定コード =324331020 (代行事務預り金　源泉税　国内上場株式等) の場合
                                ret = "代預　源泉税　国内上場株式等";
                            } else if (kanjyouCode.equals("211050200")) {
                                // 勘定コード =211050200 (振替勘定　その他) の場合
                                ret = "その他";
                            } else if (kanjyouCode.equals("351060110")) {
                                // 勘定コード =351060110 (投信積立クレジットカード返金) の場合
                                ret = "投信積立クレジットカード返金";
                            } else if (kanjyouCode.equals("324332147")) {
                                // 勘定コード =324332147 (代行事務預り金　住民税　委託　汎用累投配当割) の場合
                                ret = "代預　住民税委託汎用累投配当割";
                            } else if (kanjyouCode.equals("211051005")) {
                                // 勘定コード =211051005 (投信積立クレジットカード入金) の場合
                                ret = "投信積立クレジットカード入金";
                            } else if (kanjyouCode.equals("327012400")) {
                                // 勘定コード =327012400 (譲渡益税預り金　住民税　ジュニアNISA) の場合
                                ret = "ジュニアＮＩＳＡ（譲渡）住民税";
                            } else if (kanjyouCode.equals("326030973")) {
                                // 勘定コード =326030973 (その他付替顧客預り金) の場合
                                ret = "その他";
                            } else if (kanjyouCode.equals("211051007")) {
                                // 勘定コード =211051007 (旧Vポイントより自動振替入金) の場合
                                ret = "旧Vポイントより自動振替入金";
                            } else if (kanjyouCode.equals("211053500")) {
                                // 勘定コード =211053500 (振替勘定　外貨預り金入金) の場合
                                ret = "外貨建口座間の振替";
                            } else if (kanjyouCode.equals("211053400")) {
                                // 勘定コード =211053400 (振替勘定　外貨預り金出金) の場合
                                ret = "外貨建口座間の振替";
                            } else if (kanjyouCode.equals("724350600")) {
                                // 勘定コード =724350600 (その他受入手数料　投資信託出庫手数料) の場合
                                ret = "投資信託出庫手数料";
                            } else if (kanjyouCode.equals("324334300")) {
                                // 勘定コード =324334300 (代行事務預り金　住民税　ジュニアＮＩＳＡ　配当等) の場合
                                ret = "ジュニアＮＩＳＡ（配当）住民税";
                            } else if (kanjyouCode.equals("324331002")) {
                                // 勘定コード =324331002 (代行事務預り金　源泉税　国内投信) の場合
                                ret = "代行預り　源泉税　国内投信";
                            } else if (kanjyouCode.equals("324334100")) {
                                // 勘定コード =324334100 (代行事務預り金　住民税　特定口座（配当）) の場合
                                ret = "代預　住民税　特定口座（配当）";
                            } else if (kanjyouCode.equals("324170400")) {
                                // 勘定コード =324170400 (代行事務預り金　株　単元未満買増代金) の場合
                                ret = "代行預り　株　単元未満買増代金";
                            }  else if (kanjyouCode.equals("724430804")) {
                                // 勘定コード =724430804 (その他（課税キャンペーン）) の場合
                                ret = "その他（課税キャンペーン）";
                            }  else if (kanjyouCode.equals("532521000")) {
                                // 勘定コード =532521000 (その他（非課税キャンペーン）) の場合
                                ret = "その他（非課税キャンペーン）";
                            }  else if (kanjyouCode.equals("724430100")) {
                                // 勘定コード =724430100 (レベルフィー) の場合
                                ret = "その他の受入手数料　残高連動手数料";
                            }  else if (kanjyouCode.equals("211050220")) {
                                // 勘定コード =211050220 (不正アクセス等による補償金)の場合
                                ret = "不正アクセス等による補償金";
                            } else {
                                ret = "その他";
                            }
                        } else if (torihikiHojyoKbn.equals("01011")) {
                            ret = "書換料";
                        } else if (torihikiHojyoKbn.equals("01012")) {
                            ret = "現金投信収益金";
                        } else if (torihikiHojyoKbn.equals("01014")) {
                            ret = "現物債券利金";
                        } else if (torihikiHojyoKbn.equals("01015")) {
                            ret = "現物投信償還金";
                        } else if (torihikiHojyoKbn.equals("01016")) {
                            ret = "現物国債償還金";
                        } else if (torihikiHojyoKbn.equals("01017")) {
                            ret = "現物ＴＢ償還金";
                        } else if (torihikiHojyoKbn.equals("01018")) {
                            ret = "現物割債償還金";
                        } else if (torihikiHojyoKbn.equals("01019")) {
                            ret = "現物転社償還金";
                        } else if (torihikiHojyoKbn.equals("01020")) {
                            ret = "現物その他償還金";
                        } else if (torihikiHojyoKbn.equals("01026")) {
                            if (settleDate.compareTo("20130716") >= 0) {
                                ret = "先物・オプション証拠金振替";
                            } else {
                                ret = "先物・オプション証拠金振替(大証)";
                            }
                        } else if (torihikiHojyoKbn.equals("01022")) {
                            ret = "先物・オプション証拠金振替(東証)";
                        }
                    }
                }
            } else if (torihikiCode.charAt(1) == '8') {
                if (torihikiCode.charAt(2) == '0') {
                    if (torihikiHojyoKbn.equals("02000")) {
                        ret = "口座管理料";
                    }
                }
            } else if (torihikiCode.charAt(1) == 'Y') {
                if (torihikiCode.charAt(2) == '1') {
                    ret = "配当所得税還付金";
                }
            } else if (torihikiCode.charAt(1) == 'X') {
                if (torihikiCode.charAt(2) == '1') {
                    if (torihikiHojyoKbn.equals("01008")) {
                        ret = "譲渡益税還付金";
                    }
                }
            }
        } else if (torihikiCode.charAt(0) == '0') {
            if (torihikiCode.charAt(1) == '0') {
                if (torihikiCode.charAt(2) == '1') {
                    if (torihikiCode.charAt(3) == '9') {
                        if (productCode.equals("Q 1MF")) {
                            ret = "ＭＲＦ再投資";
                        }
                    }
                }
            }
        }
        if (kanjyouCode.equals("211052100")) {
            if (postingNo.equals("661")) {
                ret = "米国株式買付代金";
            } else if (postingNo.equals("662")) {
                ret = "韓国株式買付代金";
            } else if (postingNo.equals("663")) {
                ret = "中国株式買付代金";
            } else if (postingNo.equals("664")) {
                ret = "ロシア株式買付代金";
            } else if (postingNo.equals("665")) {
                ret = "ベトナム株式買付代金";
            } else if (postingNo.equals("666")) {
                ret = "インドネシア株式買付代金";
            } else if (postingNo.equals("667")) {
                ret = "カンボジア株式買付代金";
            } else if (postingNo.equals("668")) {
                ret = "インド株式買付代金";
            } else if (postingNo.equals("669")) {
                ret = "スリランカ株式買付代金";
            } else if (postingNo.equals("670")) {
                ret = "シンガポール株式買付代金";
            } else if (postingNo.equals("671")) {
                ret = "台湾株式買付代金";
            } else if (postingNo.equals("672")) {
                ret = "マレーシア株式買付代金";
            } else if (postingNo.equals("673")) {
                ret = "ブラジル株式買付代金";
            } else if (postingNo.equals("674")) {
                ret = "トルコ株式買付代金";
            } else if (postingNo.equals("689")) {
                ret = "タイ株式買付代金";
            }
        } else if (kanjyouCode.equals("211052200")) {
            if (postingNo.equals("675")) {
                ret = "米国株式売却代金";
            } else if (postingNo.equals("676")) {
                ret = "韓国株式売却代金";
            } else if (postingNo.equals("677")) {
                ret = "中国株式売却代金";
            } else if (postingNo.equals("678")) {
                ret = "ロシア株式売却代金";
            } else if (postingNo.equals("679")) {
                ret = "ベトナム株式売却代金";
            } else if (postingNo.equals("680")) {
                ret = "インドネシア株式売却代金";
            } else if (postingNo.equals("681")) {
                ret = "カンボジア株式売却代金";
            } else if (postingNo.equals("682")) {
                ret = "インド株式売却代金";
            } else if (postingNo.equals("683")) {
                ret = "スリランカ株式売却代金";
            } else if (postingNo.equals("684")) {
                ret = "シンガポール株式売却代金";
            } else if (postingNo.equals("685")) {
                ret = "台湾株式売却代金";
            } else if (postingNo.equals("686")) {
                ret = "マレーシア株式売却代金";
            } else if (postingNo.equals("687")) {
                ret = "ブラジル株式売却代金";
            } else if (postingNo.equals("688")) {
                ret = "トルコ株式売却代金";
            } else if (postingNo.equals("690")) {
                ret = "タイ株式売却代金";
            }
        }
        ret = ret + nisaTax;
        return ret;
    }
    
    /**
     * 日付変換
     *
     * @see java.lang.IllegalArgumentException
     * @param date    日付
     * @param format   書式
     * @param defFormat   デフォルト時のフォーマット("--/--/--")
     * @return 変換後日付文字列  日付に△が入ってきた場合　デフォルトフォーマットに変換
     *                           日付がそれ以外の場合　書式に指定されたように変換
     */
    private String reportDateFormat(String date, String format, String defFormat) {
        
        String strDate = "";
        
        // nullチェック
        if (null == date || null == format || null == defFormat) {
            return DEF_DATE;
        }
        
        date = date.trim();
        
        // 8桁であるかどうかのチェック
        if (date.length() != 8) {
            return DEF_DATE;
        }
        
        try {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(4, 6)) - 1;
            int day = Integer.parseInt(date.substring(6, 8));
            
            // 送られてきた書式で作成する
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            
            // GregorianCalendarクラスを呼び出しカレンダーを作成
            // 無効な日付の場合にはここでアベンド
            GregorianCalendar grCalen = new GregorianCalendar(year, month, day);
            grCalen.setLenient(false);
            
            strDate = dateFormat.format(grCalen.getTime());
            
        } catch (Exception e) {
            return DEF_DATE;
        }
        
        return strDate;
    }
    
    /**
     * 入出金明細設定
     *
     * @param IfaDepositWithdrawDetailSql001ResponseModel SQL001レスポンス
     * @param outputFct032Dto FCT032の結果(CSV出力項目の制御)
     * @return レスポンス編集内容
     */
    private IfaDepositWithdrawDetailCsvItems setResponse(
            IfaDepositWithdrawDetailSql001ResponseModel sqlResponse,
            OutputFct032Dto outputFct032Dto
    ) {
        
        IfaDepositWithdrawDetailCsvItems items = new IfaDepositWithdrawDetailCsvItems();
        
        // 部店
        items.setButen(sqlResponse.getButenCode());
        // 口座番号
        items.setAccountNumber(sqlResponse.getAccountNumber());
        // 取引コース
        items.setCourse(sqlResponse.getCourse());
        // 顧客名(漢字)
        items.setCustomerNameKanji(sqlResponse.getCustomerNameKanji());
        // 顧客名(カナ)
        items.setCustomerNameKana(sqlResponse.getCustomerNameKana());
        // 入出金日
        items.setDepositsAndWithdrawalsDate(DateUtil.dateFormat(
                sqlResponse.getSettleDate(),
                DateUtil.SEPARATED_YYYYMMDD,
                DateUtil.YYYYMMDD
        ));
        // 受渡金額
        items.setDeliveryAmount(sqlResponse.getDepositAmount());
        if (outputFct032Dto == null || StringUtils.equals(outputFct032Dto.getEmpCodeCodeSettingJudge(), "0")) {
            // 営業員コード
            items.setEmpCode(sqlResponse.getEmpCode());
            // 営業員名
            items.setBrokerChargeName(sqlResponse.getBrokerChargeName());
            // 仲介業者コード
            items.setBrokerCode(sqlResponse.getBrokerCode());
            // 仲介業者名
            items.setBrokerName(sqlResponse.getBrokerName());
            // 支店コード
            items.setBranchCode(sqlResponse.getBranchCode());
            // 支店名
            items.setBranchName(sqlResponse.getBranchName());
        }

        // 取引区分を取得
        int openTradeKbn = checkPayDepKbn(
                sqlResponse.getTargetTbl(),
                sqlResponse.getTradeType(),
                sqlResponse.getTradeCode3(),
                sqlResponse.getTradeCodePare(),
                sqlResponse.getAccountingCode()
        );
        items.setOpenTradeKbn(String.valueOf(openTradeKbn));

        // CSV出力用に金額と取引区分を設定
        switch (openTradeKbn) {
            case 1:
                items.setTrade("入金");
                items.setDeposit(sqlResponse.getDepositAmount().toPlainString());
                break;
            case 2:
                items.setTrade("出金");
                items.setWithdraw(sqlResponse.getDepositAmount().toPlainString());
                break;
            case 3:
                items.setTrade("振替入金");
                items.setTransferDeposit(sqlResponse.getDepositAmount().toPlainString());
                break;
            case 4:
                items.setTrade("振替出金");
                items.setTransferWithdraw(sqlResponse.getDepositAmount().toPlainString());
                break;
            default:
                break;
        }

        // 摘要
        String dispAbstract = StringUtil.nullToEmpty(getDispAbstract(sqlResponse));
        String formalKanjiSecName = StringUtil.nullToEmpty(StringUtil.trim(sqlResponse.getFormalKanjiSecName()));
        if (!StringUtil.isNullOrEmpty(formalKanjiSecName)) {
            dispAbstract = dispAbstract + " " + formalKanjiSecName;
        }

        items.setDispAbstract(dispAbstract);

        return items;
    }
    
    /** 仲介業者コード編集 */
    private List<String> splitBrokerCode(String brokerCode) {
        
        List<String> brokerCodeList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(brokerCode)) {
            brokerCodeList.addAll(Arrays.asList(brokerCode.split(",")));
        }
        return brokerCodeList;
    }
    
}
