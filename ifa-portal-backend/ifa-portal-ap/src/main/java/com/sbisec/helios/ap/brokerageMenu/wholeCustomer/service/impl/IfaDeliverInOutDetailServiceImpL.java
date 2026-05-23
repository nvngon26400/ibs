package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Strings;
import com.ibm.icu.text.DecimalFormat;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct032;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaDeliverInOutDetailDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDeliverInOutDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDeliverInOutDetailSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailCsvItems;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailDeliverInOutDetail;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailTradingCourse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaDeliverInOutDetailService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaDeliverInOutDetailCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020302_0204-01
 * 画面名：入出庫明細
 * @author <author-name>
 *
 * 2024/04/03 新規作成
 */
@Component(value = "cmpIfaDeliverInOutDetailService")
public class IfaDeliverInOutDetailServiceImpL implements IfaDeliverInOutDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDeliverInOutDetailServiceImpL.class);
    
    @Autowired
    private IfaDeliverInOutDetailDao dao;
    
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
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    /** 取引コースチェックエラー  */
    private static final String ERRORS_SELECTED = "errors.selected";
    
    /** 0件メッセージ  */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 件数超過メッセージ  */
    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    /** CSV件数超過メッセージ  */
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /** 期間指定チェックエラー  */
    private static final String ERRORS_DATERANGE = "errors.dateRange";
    
    /** FCT030エラーメッセージ  */
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB020302_0204-01";
    
    /** 区分.契約締結前交付書面コード */
    private static final String PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    
    /** 区分.商品区分（入出庫） */
    private static final String DELIVER_IN_OUT_SECURITY_TYPE = "DELIVER_IN_OUT_SECURITY_TYPE";
    
    /** 区分.入出庫区分 */
    private static final String DELIVER_IN_OUT_TYPE = "DELIVER_IN_OUT_TYPE";
    
    private static final String VIEW_LIMIT_VALUE = "5000";
    
    /** SELECTED_0 */
    private static final String SELECTED = "取引コース";
    
    /** DATERANGE_0 */
    private static final String DATERANGE_0 = "期間指定";
    
    /** DATERANGE_1 */
    private static final String DATERANGE_1 = "過去2年間の範囲で6ヶ月";
    
    /** MAXROWNUM_0 */
    private static final String MAXROWNUM_0 = "5,000";
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaDeliverInOutDetailA002DtoRequest
     * Dto レスポンス：IfaDeliverInOutDetailA002DtoResponse
     * model リクエスト：IfaDeliverInOutDetailSql001RequestModel
     * model レスポンス：IfaDeliverInOutDetailSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDeliverInOutDetailA002ResponseDto> displayA002(IfaDeliverInOutDetailA002RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaDeliverInOutDetailA002ResponseDto> dtoRes = new DataList<IfaDeliverInOutDetailA002ResponseDto>();
        List<IfaDeliverInOutDetailA002ResponseDto> resDtoList = new ArrayList<IfaDeliverInOutDetailA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaDeliverInOutDetailServiceImplL.displayA002");
        
        //①   リクエスト.取引コースのチェックを行う。
        //　リクエスト.取引コースに値が未設定の場合：メッセージを表示し、処理終了。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_SELECTED,
                    IfaCommonUtil.getMessage(ERRORS_SELECTED, new String[] { SELECTED }));
            return dtoRes;
        }
        // リクエスト.取引コースに値が設定済みの場合：次の処理へ。
        
        //②   リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい　または
        // リクエスト.期間指定（From）がシステム日付-2年より小さい　または
        // リクエスト.期間指定（To）がシステム日付-2年より小さい　場合：メッセージを表示し、処理終了。
        // システム日付取得
        String systemDate = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD);
        LocalDate currDate = LocalDate.parse(systemDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate fromDate = LocalDate.parse(dtoReq.getPeriodYmFrom(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate toDate = LocalDate.parse(dtoReq.getPeriodYmTo(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (toDate.minusMonths(6).compareTo(fromDate) > 0 || currDate.minusYears(2).compareTo(fromDate) > 0
                || currDate.minusYears(2).compareTo(toDate) > 0) {
            // エラー(errors.dateRange)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_DATERANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATERANGE, new String[] { DATERANGE_0, DATERANGE_1 }));
            return dtoRes;
        }
        // 上記以外の場合：次の処理へ。
        
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        //③   ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        if (!StringUtils.equals(userAccount.getPrivId(), "1")) {
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList())
                    || fct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_IFAAGENTCODES_NOTEXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return dtoRes;
            }
            // FCT030.仲介業者営業員リストの件数が1件以上の場合：次の処理へ。
        }
        
        //④   入出庫明細情報を検索する。※最大取得件数を5001件とする。
        IfaDeliverInOutDetailSql001RequestModel selSql001Req = new IfaDeliverInOutDetailSql001RequestModel();
        //仲介業者コード
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        //仲介業者除外
        selSql001Req.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        //支店コード
        selSql001Req.setBranchCode(dtoReq.getBranchCode());
        //営業員コード
        selSql001Req.setEmpCode(dtoReq.getEmpCode());
        //部店コード
        selSql001Req.setButenCode(dtoReq.getButenCode());
        //口座番号
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        //顧客名(漢字/カナ)
        selSql001Req.setCustomerNameKanjiKana(dtoReq.getCustomerNameKanjiKana());
        //顧客名(漢字/カナ)_条件
        selSql001Req.setCustomerNameKanjiKanaTerms(dtoReq.getCustomerNameKanjiKanaTerms());
        //取引コース
        List<String> idList = new ArrayList<String>();
        if (!ObjectUtils.isEmpty(dtoReq.getCourse())) {
            for (IfaDeliverInOutDetailTradingCourse trade : dtoReq.getCourse()) {
                if (StringUtils.equals(trade.getIsSelected(), "true")) {
                    idList.add(trade.getId());
                }
            }
        }
        selSql001Req.setCourse(ObjectUtils.isEmpty(idList) ? null : idList);
        //期間指定（From)
        selSql001Req.setPeriodYmFrom(dtoReq.getPeriodYmFrom());
        //期間指定（To)
        selSql001Req.setPeriodYmTo(dtoReq.getPeriodYmTo());

        //FCT030.仲介業者営リスト
        if (!(fct030Dto.getBrokerChargeList() == null)) {
            selSql001Req.setBrokerChargeList(fct030Dto.getBrokerChargeList());
        }

        // ユーザ共通情報.権限コード
        selSql001Req.setPrivId(userAccount.getPrivId());

        //最大取得件数
        selSql001Req.setQuerySizeLimit(5001);
        DataList<IfaDeliverInOutDetailSql001ResponseModel> selSql001Res = dao
                .selectIfaDeliverInOutDetailSql001(selSql001Req);
        
        // SQL001.総件数
        int totalRow = 0;
        if (!(selSql001Res.getDataList().size() == 0)) {
            totalRow = selSql001Res.getDataList().get(selSql001Res.getDataList().size() - 1).getTotalRow();
        }
       
        //⑦   商品区分の表示内容をセットする。
        //⑧   入出庫区分の表示内容をセットする。
        //⑨   入出庫明細を表示する。
        List<IfaDeliverInOutDetailDeliverInOutDetail> deliverInOutDetailList = new ArrayList<IfaDeliverInOutDetailDeliverInOutDetail>();
        //SQL001.総件数が、0件の場合は、入出庫明細をクリアし、0件メッセージを表示する。
        if (totalRow == 0) {
            deliverInOutDetailList.clear();
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
            //SQL001.総件数が、1件～5000件の場合は、入出庫明細に検索結果を表示する。
        } else {
            for (IfaDeliverInOutDetailSql001ResponseModel sqlRec : selSql001Res.getDataList()) {
                
                //SQL001.総件数が、5000件超の場合は、5000件まで入出庫明細に表示
                if (deliverInOutDetailList.size() >= 5000) {
                    break;
                }
                IfaDeliverInOutDetailDeliverInOutDetail deliverInOutDetail = new IfaDeliverInOutDetailDeliverInOutDetail();
                //仲介業者名
                deliverInOutDetail.setBrokerName(sqlRec.getBrokerName());
                //営業員コード
                deliverInOutDetail.setEmpCode(sqlRec.getEmpCode());
                //営業員名
                deliverInOutDetail.setBrokerChargeName(sqlRec.getBrokerChargeName());
                //部店
                deliverInOutDetail.setButen(sqlRec.getButen());
                //口座番号
                deliverInOutDetail.setAccountNumber(sqlRec.getAccountNumber());
                //取引コース
                deliverInOutDetail.setCourse(sqlRec.getCourse());
                //顧客名(漢字)
                deliverInOutDetail.setCustomerNameKanji(sqlRec.getCustomerNameKanji());
                //顧客名(カナ)
                deliverInOutDetail.setCustomerNameKana(sqlRec.getCustomerNameKana());
                //銘柄名
                deliverInOutDetail.setBrandName(sqlRec.getBrandName());
                //商品区分
                deliverInOutDetail.setSecurityType(sqlRec.getSecurityType());
                //入出庫区分
                deliverInOutDetail.setDeliverInOutClassification(sqlRec.getDeliverInOutClassification());
                //数量
                deliverInOutDetail.setQuantity(sqlRec.getQuantity());
                //入庫日時価
                deliverInOutDetail.setCheckInOutPrice(sqlRec.getDeliverInOutDateMarketValue());
                //合計金額
                deliverInOutDetail.setTotalAmount(sqlRec.getTotalAmount());
                //入出庫日
                deliverInOutDetail.setCheckInOutDay(sqlRec.getCheckInOutDay());
                //仲介業者コード
                deliverInOutDetail.setBrokerCode(sqlRec.getBrokerCode());
                //支店コード
                deliverInOutDetail.setBranchCode(sqlRec.getBranchCode());
                //支店名
                deliverInOutDetail.setBranchName(sqlRec.getBranchName());
                
                deliverInOutDetailList.add(deliverInOutDetail);
            }
        }
        
        String totalRec = Integer.toString(totalRow);
        IfaDeliverInOutDetailA002ResponseDto resDto = new IfaDeliverInOutDetailA002ResponseDto();
        resDto.setAcquireCount(totalRec);
        resDto.setDataCount(totalRec);
        resDto.setDeliverInOutDetail(deliverInOutDetailList);
        resDtoList.add(resDto);

        //⑥   SQL001.総件数が5001件の場合、件数超過メッセージをセットする。
        if (totalRow > 5000) {
            resDto.setDataCount(VIEW_LIMIT_VALUE);
            String message = IfaCommonUtil.getMessage(
                    WARNINGS_DATALIST_OVERMAXROWNUM,
                    new String[] { MAXROWNUM_0, String.valueOf(totalRow) }
            );

            //※正規表現を利用して、件数超過メッセージの「()」部分を削除し、メッセージを加工する。
            String pattern = "（[0-9,]+件ヒット）";
            message = message.replaceFirst(pattern, "");

            dtoRes = IfaCommonUtil.createDataList(
                    resDtoList,
                    ErrorLevel.WARNING,
                    WARNINGS_DATALIST_OVERMAXROWNUM,
                    message
            );
            dtoRes.setOverMaxRownum(true);
            dtoRes.setTotalSize(totalRow);
            dtoRes.setMaxRownum(Integer.parseInt(VIEW_LIMIT_VALUE));

            return dtoRes;
        }

        dtoRes = IfaCommonUtil.createDataList(
                resDtoList,
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                ""
        );
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaDeliverInOutDetailA004DtoRequest
     * Dto レスポンス：IfaDeliverInOutDetailA004DtoResponse
     * model リクエスト：IfaDeliverInOutDetailSql001RequestModel
     * model レスポンス：IfaDeliverInOutDetailSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDeliverInOutDetailA004ResponseDto> csvOutputA004(IfaDeliverInOutDetailA004RequestDto dtoReq,
            String fwSessionId) throws Exception {
        
        DataList<IfaDeliverInOutDetailA004ResponseDto> dtoRes = new DataList<IfaDeliverInOutDetailA004ResponseDto>();
        List<IfaDeliverInOutDetailA004ResponseDto> resDtoList = new ArrayList<IfaDeliverInOutDetailA004ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaDeliverInOutDetailServiceImplL.csvOutputA004");
        
        //①   A002の①～③と同じ処理を行う。
        //①   リクエスト.取引コースのチェックを行う。
        //　リクエスト.取引コースに値が未設定の場合：メッセージを表示し、処理終了。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_SELECTED,
                    IfaCommonUtil.getMessage(ERRORS_SELECTED, new String[] { SELECTED }));
            return dtoRes;
        }
        // リクエスト.取引コースに値が設定済みの場合：次の処理へ。
        
        //②   リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい　または
        // リクエスト.期間指定（From）がシステム日付-2年より小さい　または
        // リクエスト.期間指定（To）がシステム日付-2年より小さい　場合：メッセージを表示し、処理終了。
        // システム日付取得
        String systemDate = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD);
        LocalDate currDate = LocalDate.parse(systemDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate fromDate = LocalDate.parse(dtoReq.getPeriodYmFrom(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate toDate = LocalDate.parse(dtoReq.getPeriodYmTo(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (toDate.minusMonths(6).compareTo(fromDate) > 0 || currDate.minusYears(2).compareTo(fromDate) > 0
                || currDate.minusYears(2).compareTo(toDate) > 0) {
            // エラー(errors.dateRange)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_DATERANGE,
                    IfaCommonUtil.getMessage(ERRORS_DATERANGE, new String[] { DATERANGE_0, DATERANGE_1 }));
            return dtoRes;
        }
        // 上記以外の場合：次の処理へ。
        
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        //③   ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        if (!StringUtils.equals(userAccount.getPrivId(), "1")) {
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList())
                    || fct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_IFAAGENTCODES_NOTEXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return dtoRes;
            }
            // FCT030.仲介業者営業員リストの件数が1件以上の場合：次の処理へ。
        }
        
        //②   CSVダウンロードMAX件数を取得し、+1した値を最大取得件数にセットする。
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();
        // 最大取得件数
        int maxAcquireCount = maxCsvRowNum + 1;
        
        //③   入出庫明細情報を検索する。※②で取得した値を最大取得件数とする。
        IfaDeliverInOutDetailSql001RequestModel selSql001Req = new IfaDeliverInOutDetailSql001RequestModel();
        //仲介業者コード
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        //仲介業者除外
        selSql001Req.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        //支店コード
        selSql001Req.setBranchCode(dtoReq.getBranchCode());
        //営業員コード
        selSql001Req.setEmpCode(dtoReq.getEmpCode());
        //部店コード
        selSql001Req.setButenCode(dtoReq.getButenCode());
        //口座番号
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        //顧客名(漢字/カナ)
        selSql001Req.setCustomerNameKanjiKana(dtoReq.getCustomerNameKanjiKana());
        //顧客名(漢字/カナ)_条件
        selSql001Req.setCustomerNameKanjiKanaTerms(dtoReq.getCustomerNameKanjiKanaTerms());
        //取引コース
        List<String> idList = new ArrayList<String>();
        if (!ObjectUtils.isEmpty(dtoReq.getCourse())) {
            for (IfaDeliverInOutDetailTradingCourse trade : dtoReq.getCourse()) {
                if (StringUtils.equals(trade.getIsSelected(), "true")) {
                    idList.add(trade.getId());
                }
            }
        }
        selSql001Req.setCourse(ObjectUtils.isEmpty(idList) ? null : idList);
        //期間指定（From)
        selSql001Req.setPeriodYmFrom(dtoReq.getPeriodYmFrom());
        //期間指定（To)
        selSql001Req.setPeriodYmTo(dtoReq.getPeriodYmTo());
        //FCT030.仲介業者営リスト
        if (!(fct030Dto.getBrokerChargeList() == null)) {
            selSql001Req.setBrokerChargeList(fct030Dto.getBrokerChargeList());
        }
        // ユーザ共通情報.権限コード
        selSql001Req.setPrivId(userAccount.getPrivId());
        //最大取得件数
        selSql001Req.setQuerySizeLimit(maxCsvRowNum);
        DataList<IfaDeliverInOutDetailSql001ResponseModel> selSql001Res = dao
                .selectIfaDeliverInOutDetailSql001(selSql001Req);
        
        // SQL001.総件数
        int totalRow = 0;
        if (!(selSql001Res.getDataList().size() == 0)) {
            totalRow = selSql001Res.get(0).getTotalRow();
        }
        //④   SQL001.総件数が0件の場合、0件メッセージをセットする。
        if (totalRow == 0) {
            dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    ERRORS_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND)
            );

            return dtoRes;
        }
        
        //⑤   商品区分の表示内容をセットする。
        //⑥   入出庫区分の表示内容をセットする。
        //⑦   検索結果をCSVファイルを出力する。
        IfaDeliverInOutDetailCsvOut csvOut = new IfaDeliverInOutDetailCsvOut(complianceService);
        DataList<IfaDeliverInOutDetailCsvItems> exportList = new DataList<IfaDeliverInOutDetailCsvItems>();
        
        // FCT032.営業員コード自動設定判定に沿って出力項目を制御する。
        InputFct032Dto fct032InData = new InputFct032Dto();
        OutputFct032Dto fct032Dto = fct032.getData(fct032InData);
        
        List<IfaDeliverInOutDetailCsvItems> csvItemsList = new ArrayList<>();
        DecimalFormat Format = new DecimalFormat("#####0");
        DecimalFormat decimalFormat = new DecimalFormat("#####0.#");
        for (IfaDeliverInOutDetailSql001ResponseModel sqlRes : selSql001Res.getDataList()) {

            // CSVダウンロードMAX件数超の場合、CSVダウンロードMAX件数のみCSV出力する
            if (csvItemsList.size() >= maxCsvRowNum) {
                break;
            }
            
            IfaDeliverInOutDetailCsvItems csvItems = new IfaDeliverInOutDetailCsvItems();
            // 仲介業者名
            if (StringUtils.equals(fct032Dto.getEmpCodeCodeSettingJudge(), "0")) {
                csvItems.setBrokerName(sqlRes.getBrokerName());
            }
            // 営業員コード
            if (StringUtils.equals(fct032Dto.getEmpCodeCodeSettingJudge(), "0")) {
                csvItems.setEmpCode(sqlRes.getEmpCode());
            }
            // 営業員名
            if (StringUtils.equals(fct032Dto.getEmpCodeCodeSettingJudge(), "0")) {
                csvItems.setBrokerChargeName(sqlRes.getBrokerChargeName());
            }
            // 部店
            csvItems.setButen(sqlRes.getButen());
            // 口座番号
            csvItems.setAccountNumber(formatNumber(sqlRes.getAccountNumber(), Format));
            // 取引コース
            csvItems.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, sqlRes.getCourse()));
            // 顧客名(漢字) 
            csvItems.setCustomerNameKanji(sqlRes.getCustomerNameKanji());
            // 顧客名(カナ)
            csvItems.setCustomerNameKana(sqlRes.getCustomerNameKana());
            // 銘柄名
            csvItems.setBrandName(sqlRes.getBrandName());
            // 商品区分
            csvItems.setSecurityType(codeListService.getValue(DELIVER_IN_OUT_SECURITY_TYPE, sqlRes.getSecurityType()));
            // 入出庫区分
            csvItems.setDeliverInOutClassification(
                    codeListService.getValue(DELIVER_IN_OUT_TYPE, sqlRes.getDeliverInOutClassification()));
            // 数量
            csvItems.setQuantity(formatNumber(sqlRes.getQuantity(), Format));
            // 入庫日時価
            csvItems.setDeliverInOutDateMarketValue(
                    formatNumber(sqlRes.getDeliverInOutDateMarketValue(), decimalFormat));
            // 合計金額
            csvItems.setTotalAmount(formatNumber(sqlRes.getTotalAmount(), decimalFormat));
            // 入出庫日
            csvItems.setCheckInOutDay(DateFormatUtil.dateFormatToSlash(sqlRes.getCheckInOutDay()));
            // 仲介業者コード
            if (StringUtils.equals(fct032Dto.getEmpCodeCodeSettingJudge(), "0")) {
                csvItems.setBrokerCode(sqlRes.getBrokerCode());
            }
            // 支店コード
            if (StringUtils.equals(fct032Dto.getEmpCodeCodeSettingJudge(), "0")) {
                csvItems.setBranchCode(sqlRes.getBranchCode());
            }
            // 支店名
            if (StringUtils.equals(fct032Dto.getEmpCodeCodeSettingJudge(), "0")) {
                csvItems.setBranchName(sqlRes.getBranchName());
            }
            csvItemsList.add(csvItems);
        }
        exportList.setDataList(csvItemsList);

        // CSVファイルを出力する
        String title = csvOut.doCreateCsvFile(exportList, fwSessionId, userAccount.getUserId(), null);
       
        // SQL001.総件数が、CSVダウンロードMAX件数超の場合は、CSVダウンロードMAX件数までCSVファイルに出力し、件数超過メッセージを表示する。
        if (totalRow > maxCsvRowNum) {
            String message = IfaCommonUtil.getMessage(
                    WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                    new String[] {
                            String.valueOf(maxCsvRowNum),
                            String.valueOf(totalRow),
                            String.valueOf(maxCsvRowNum)
                    }
            );

            //※正規表現を利用して、件数超過メッセージの「()」部分を削除し、メッセージを加工する。
            String pattern = "（[0-9,]+件ヒット）";
            message = message.replaceFirst(pattern, "");

            dtoRes = IfaCommonUtil.createDataList(
                    resDtoList,
                    ErrorLevel.WARNING,
                    WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                    message
            );
            dtoRes.setTitle(title);
            dtoRes.setTotalSize(totalRow);
            dtoRes.setMaxRownum(maxCsvRowNum);
            dtoRes.setOverMaxRownum(true);

            return dtoRes;
        }
        
        // SQL001.総件数が、1件～CSVダウンロードMAX件数の場合は、CSVファイルを出力する。
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        dtoRes.setTitle(title);
        dtoRes.setTotalSize(totalRow);
        dtoRes.setMaxRownum(totalRow);

        return dtoRes;
    }
    
    private String formatNumber(String value, DecimalFormat format) {
        
        if (Strings.isNullOrEmpty(value)) {
            return "";
        }
        try {
            // 文字列を数値に変換してフォーマットする
            BigDecimal valueBigDecimal = new BigDecimal(value);
            return format.format(valueBigDecimal);
        } catch (NumberFormatException e) {
            // 数値に変換できない場合は空文字列を返す
            return "";
        }
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
