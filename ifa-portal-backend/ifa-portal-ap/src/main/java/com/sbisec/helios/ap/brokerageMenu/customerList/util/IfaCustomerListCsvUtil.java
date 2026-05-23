
package com.sbisec.helios.ap.brokerageMenu.customerList.util;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListPersonalCorporation;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListSql001ResponseModelCsvOutModel;
import com.sbibits.earth.util.Cp932;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.lang3.StringUtils;

/**
 * 顧客一覧_基本　CSV出力ユーティリティ
 */
public class IfaCustomerListCsvUtil extends CsvOutPutUtil {
    
    /**
     * 継承したコンストラクタ(コンプライアンスサービス)
     */
    public IfaCustomerListCsvUtil(ComplianceService comp) {
        
        super(comp);
    }
    
    /** 画面リクエスト.チェックボックスON(TRUE) */
    private static final String CHECK_BOX_ON = "true";

    /** ドロップダウンボックスを選択 */
    private static final String TRUE = "true";

    /** 取引制限 = 1 */
    private static final String ERROR_COUNT = "1";
    
    /** 〇 */
    private static final String EXIST = "〇";
    
    /** 空文字 */
    private static final String BLANK = "";
    
    /** YYYYMMDDHHMMSS */
    private static final String SEPARATED_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    
    /** YYYY/MM/DD */
    private static final String SEPARATED_YYYYMMDD = "yyyy/MM/dd";
    
    /**
     * CSVヘッダー(タイトル)
     *
     * @return String タイトル行
     */
    protected String getCsvHeader(IfaCustomerListA005RequestDto reqDto) {
        
        return getHeaders(reqDto);
    }
    
    /**
     * 顧客一覧_基本のCSVヘッダー行作成
     */
    public static String getHeaders(IfaCustomerListA005RequestDto reqDto) {
        
        StringBuilder strCsvHead = new StringBuilder();
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "部店" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "口座番号" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引コース" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(漢字)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "顧客名(カナ)" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "取引制限" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        if (checkOutputBirthDate(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "生年月日" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        
        if (checkOutputAge(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "年齢" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputPersonalCorporation(reqDto.getOccupation())) {
            strCsvHead.append(DOUBLE_QUOTATION + "職業" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        strCsvHead.append(DOUBLE_QUOTATION + "Cランク" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        if (checkOutputLastTradeDate(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "最終約定日" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        strCsvHead.append(DOUBLE_QUOTATION + "電子交付同意" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        if (checkOutputAddress(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "住所" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputOpenAccount(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "口座開設日" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputPhoneNumber(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "電話番号" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        strCsvHead.append(DOUBLE_QUOTATION + "預り金額" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "評価額" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "評価損益" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        if (checkOutputPersonalCorporation(reqDto.getFinancialAssets())) {
            strCsvHead.append(DOUBLE_QUOTATION + "金融資産" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        strCsvHead.append(DOUBLE_QUOTATION + "年間手数料" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "累計手数料" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        if (checkOutputViewAblrButen(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "閲覧可能部店" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputNisaContractKbnList(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "NISA口座" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputCourseChangeFinishDate(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "コース変更完了日" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputForeignSecurityAccountList(reqDto)) {
            strCsvHead.append(DOUBLE_QUOTATION + "外国証券取引口座" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputPersonalCorporation(reqDto.getInvestmentPlan())) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資の方針" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputPersonalCorporation(reqDto.getFundCharacter())) {
            strCsvHead.append(DOUBLE_QUOTATION + "資金の性格" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputPersonalCorporation(reqDto.getIncomeForm())) {
            strCsvHead.append(DOUBLE_QUOTATION + "主な収入源" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputPersonalCorporation(reqDto.getEmploymentPeriod())) {
            strCsvHead.append(DOUBLE_QUOTATION + "資産運用期間" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputPersonalCorporation(reqDto.getAnnualIncome())) {
            strCsvHead.append(DOUBLE_QUOTATION + "年収" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "01", "02")) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験有無（株式現物）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験年数（株式現物）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "03", "04")) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験有無（債券）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験年数（債券）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "05", "06")) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験有無（転換社債）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験年数（転換社債）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "07", "08")) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験有無（信用）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験年数（信用）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "09", "10")) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験有無（ワラント）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験年数（ワラント）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "11", "12")) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験有無（先物OP）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験年数（先物OP）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "13", "14")) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験有無（貯蓄型投信）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験年数（貯蓄型投信）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "15", "16")) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験有無（その他投信）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験年数（その他投信）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "17", "18")) {
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験有無（外国証券）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
            strCsvHead.append(DOUBLE_QUOTATION + "投資経験年数（外国証券）" + DOUBLE_QUOTATION);
            strCsvHead.append(CSV_SEPARATER);
        }
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店コード" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "支店名" + DOUBLE_QUOTATION);
        return strCsvHead.toString();
    }
    
    /**
     * 顧客一覧データをcsv形式に変換
     *
     * @param entrustInvestment 顧客一覧データ
     * @return String cvs形式顧客一覧データ
     */
    protected String getCsvLine(ModelBase modelBase, IfaCustomerListA005RequestDto reqDto) throws Exception {
        
        IfaCustomerListSql001ResponseModelCsvOutModel customerList = (IfaCustomerListSql001ResponseModelCsvOutModel) modelBase;
        
        StringBuilder strCsv = new StringBuilder();
        // 仲介業者名
        strCsv.append(
                DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getBranchNameOfBroker())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getBrokerChargeCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名 (担当者名)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getChargeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 部店(コード)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getButenCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 口座番号
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getAccountNumber()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引コース(契約締結前交付書面コード名)
        strCsv.append(
                DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getCustomerAttributeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(漢字)
        strCsv.append(
                DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getCustomerNameKanji())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 顧客名(カナ)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getCustomerNameKana())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 取引制限
        if (!StringUtil.isNullOrEmpty(customerList.getTradeRestriction())) {
            if (StringUtils.equals(customerList.getTradeRestriction(), ERROR_COUNT)) {
                strCsv.append(DOUBLE_QUOTATION + EXIST + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            } else {
                strCsv.append(DOUBLE_QUOTATION + BLANK + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            }
        } else {
            strCsv.append(DOUBLE_QUOTATION + BLANK + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 生年月日
        if (checkOutputBirthDate(reqDto)) {
            if (!StringUtil.isNullOrEmpty(customerList.getCorBirthFlg())) {
                strCsv.append(DOUBLE_QUOTATION + DateFormatUtil.convertDateString(customerList.getCorBirthFlg())
                        + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            } else {
                strCsv.append(DOUBLE_QUOTATION + BLANK + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            }
        }
        // 年齢
        if (checkOutputAge(reqDto)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getAge()) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 職業
        if (checkOutputPersonalCorporation(reqDto.getOccupation())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getOccupation())) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // Cランク
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getTcCompRank()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 最終約定日
        if (checkOutputLastTradeDate(reqDto)) {
            if (!StringUtil.isNullOrEmpty(customerList.getLastTradeDate())) {
                Date date = DateUtil.parse(customerList.getLastTradeDate(), SEPARATED_YYYYMMDDHHMMSS);
                strCsv.append(DOUBLE_QUOTATION + DateUtil.format(date, SEPARATED_YYYYMMDD) + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            } else {
                strCsv.append(DOUBLE_QUOTATION + BLANK + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            }
        }
        // 電子交付(電子交付承諾日付)
        if (!StringUtil.isNullOrEmpty(customerList.getElectronicDocConsentDate())) {
            Date date = DateUtil.parse(customerList.getElectronicDocConsentDate(), SEPARATED_YYYYMMDDHHMMSS);
            strCsv.append(DOUBLE_QUOTATION + DateUtil.format(date, SEPARATED_YYYYMMDD) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        } else {
            strCsv.append(DOUBLE_QUOTATION + BLANK + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 住所(郵便番号と住所)
        if (checkOutputAddress(reqDto)) {
            strCsv.append(DOUBLE_QUOTATION);
            strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(customerList.getZipCodeBeforeAndAfter())));
            strCsv.append(" ");
            strCsv.append(StringUtil.nullToEmpty(Cp932.toJIS(customerList.getAdressKanji())));
            strCsv.append(DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 口座開設日
        if (checkOutputOpenAccount(reqDto)) {
            if (!StringUtil.isNullOrEmpty(customerList.getOpenAcctDate())) {
                strCsv.append(DOUBLE_QUOTATION + DateFormatUtil.convertDateString(customerList.getOpenAcctDate())
                        + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            } else {
                strCsv.append(DOUBLE_QUOTATION + BLANK + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            }
        }
        // 電話番号
        if (checkOutputPhoneNumber(reqDto)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getCorporatePhoneNumber())
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 預り金額(総資産の合計)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getTotalAssets()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 評価額(評価額（円貨）の合計)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getValuationTotalJpyAmount()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 評価損益
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getCustomerListProfitAndLoss()))
                + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 金融資産
        if (checkOutputPersonalCorporation(reqDto.getFinancialAssets())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getFinancialAssets()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 年間手数料(手数料累計の合計(年間手数料用))
        strCsv.append(
                DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getCommTotalAmountOfYear()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 累計手数料(手数料累計の合計(累計手数料用))
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getCommTotalAmount()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 閲覧可能部店(閲覧可能部店コード)
        if (checkOutputViewAblrButen(reqDto)) {
            strCsv.append(
                    DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getViewAblrButenCode()) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // NISA口座(NISA口座表示情報)
        if (checkOutputNisaContractKbnList(reqDto)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getNisaContractKbnViewInfo())
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // コース変更完了日(変更完了日時)
        if (checkOutputCourseChangeFinishDate(reqDto)) {
            if (!StringUtil.isNullOrEmpty(customerList.getChangeFinishDateTime())) {
                strCsv.append(DOUBLE_QUOTATION
                        + DateFormatUtil.convertDateString(customerList.getChangeFinishDateTime()) + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            } else {
                strCsv.append(DOUBLE_QUOTATION + BLANK + DOUBLE_QUOTATION);
                strCsv.append(CSV_SEPARATER);
            }
        }
        // 外国証券取引口座(外国証券取引口座表示情報)
        if (checkOutputForeignSecurityAccountList(reqDto)) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getForeignSecurityAccountViewInfo())
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 投資の方針
        if (checkOutputPersonalCorporation(reqDto.getInvestmentPlan())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getInvestmentPlan()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 資金の性格
        if (checkOutputPersonalCorporation(reqDto.getFundCharacter())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getFundCharacter()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 主な収入源
        if (checkOutputPersonalCorporation(reqDto.getIncomeForm())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getIncomeForm()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 資産運用期間
        if (checkOutputPersonalCorporation(reqDto.getEmploymentPeriod())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getEmploymentPeriod()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 年収
        if (checkOutputPersonalCorporation(reqDto.getAnnualIncome())) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getAnnualIncome()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 株式現物
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "01", "02")) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getStockExpKbn()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getStockExp()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 債券
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "03", "04")) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getDebentureExpKbn()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getDebentureExp()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 転換社債
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "05", "06")) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getCbExpKbn()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            strCsv.append(
                    DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getCbExp())) + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 信用
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "07", "08")) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getMarginExpKbn()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getMarginExp()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // ワラント
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "09", "10")) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getWarrantExpKbn()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getWarrantExp()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 先物OP
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "11", "12")) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getFutureopExpKbn()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getFutureopExp()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 貯蓄型投信
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "13", "14")) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getSavedtypefundExpKbn()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getSavedtypefundExp()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // その他投信
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "15", "16")) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getOtherfundExpKbn()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getOtherfundExp()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 外国証券
        if (checkOutputInvestmentExp(reqDto.getInvestmentExp(), reqDto.getInvestmentExpDisplay(), "17", "18")) {
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getForeignExpKbn()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
            strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(customerList.getForeignExp()))
                    + DOUBLE_QUOTATION);
            strCsv.append(CSV_SEPARATER);
        }
        // 仲介業者コード
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getBrokerCode()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店コード(仲介業者支店コード)
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(customerList.getSubBrokerId()) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 支店名(仲介業者支店名（支店名用）)
        strCsv.append(DOUBLE_QUOTATION + Cp932.toJIS(customerList.getBranchNameOfBranch()) + DOUBLE_QUOTATION);
        return strCsv.toString();
    }
    
    /**
     * 顧客一覧_基本のテンポラリCSVファイルの作成処理
     * 現行のCsvoutPutUtil.doCreateCsvFile()では項目の出し分け処理を単純制御できないようであるため自前で作成
     * テンポラリファイルは、CORDYS.TB_MED_TEMP_FILE_DIRのレコードに格納されているFILE_DIRに作成される
     * そのためFUNC_ID：M6、CAT_ID：0 FILE_DIR：C:/work/ のようなレコードを登録してからでないとエラーとなる
     *
     * @param dataList ModelBaseを継承したCSV出力用のDTO
     * @param sessionId セッションID（frameworkSessionIdを使うらしいが現在はラップされていないので単純に取得できない）
     * @param userId 刷新に合わせて別の値になるのか不明。現状はUserAccountのuserId(パイロット開発を踏襲）
     * @param pattern
     *
     * @return テンポラリCSVファイルのパス名（例：c:/work/ファイル名.CSV）
     * @throws Exception 例外エラー
     * @see com.sbisec.helios.ap.common.util.CsvOutPutUtil#doCreateCsvFile(com.sbibits.earth.model.DataList, java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    public String doCreateCsvFile(DataList dataList, String sessionId, String userId, String pattern,
            IfaCustomerListA005RequestDto reqDto) throws Exception {
        
        // Create csv temporary file name
        String tmpCsv = String.format(TEMPORARY_CSV_FILE_NAME,
                DateTimeFormatter.ofPattern(CSV_TEMPORARY_DATE_PATTERN).format(LocalDateTime.now()), sessionId, userId);
        try {
            tmpCsv = getCsvTemporaryDirectory() + tmpCsv;
            File file = new File(tmpCsv);
            PrintWriter out = new PrintWriter(file);
            
            List list = dataList.getDataList();
            int listSize = list.size();
            
            // Write header
            out.println(pattern != null ? getCsvHeader(pattern) : getCsvHeader(reqDto));
            
            for (int i = 0; i < listSize; i++) {
                if (pattern != null) {
                    out.println(getCsvLine((ModelBase) list.get(i), pattern));
                } else {
                    out.println(getCsvLine((ModelBase) list.get(i), reqDto));
                }
            }
            
            out.flush();
            out.close();
            
            // Catch and ingore.
        } catch (Exception e) {
            throw e;
        }
        
        return tmpCsv;
    }
    
    /**
     * 住所情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputAddress(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getAdressDisplay(), CHECK_BOX_ON)
                || StringUtils.isNotEmpty(reqDto.getAdress());
    }
    
    /**
     * 電話番号情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputPhoneNumber(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getPhoneNumberDisplay(), CHECK_BOX_ON)
                || StringUtils.isNotEmpty(reqDto.getCorporatePhoneNumber());
    }
    
    /**
     * 年齢情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputAge(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getAgeDisplay(), CHECK_BOX_ON) || StringUtils.isNotEmpty(reqDto.getAgeFrom())
                || StringUtils.isNotEmpty(reqDto.getAgeTo());
    }
    
    /**
     * 生年月日情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputBirthDate(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getBirthDateDisplay(), CHECK_BOX_ON)
                || StringUtils.isNotEmpty(reqDto.getBirthDateFrom()) || StringUtils.isNotEmpty(reqDto.getBirthDateTo());
    }
    
    /**
     * 口座開設日情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputOpenAccount(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getOpenAccountDisplay(), CHECK_BOX_ON)
                || StringUtils.isNotEmpty(reqDto.getOpenAccountFrom())
                || StringUtils.isNotEmpty(reqDto.getOpenAccountTo());
    }
    
    /**
     * 閲覧可能部店情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputViewAblrButen(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getViewAblrButenDisplay(), CHECK_BOX_ON)
                || (
                    reqDto.getButenCodeArray() != null
                    && !reqDto.getButenCodeArray().isEmpty()
                    && reqDto.getButenCodeArray().size() > 0
                    && !reqDto.getButenCodeArray().get(0).isEmpty()
                );
    }

    /**
     * NISA口座情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputNisaContractKbnList(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getNisaContractKbnDisplay(), CHECK_BOX_ON)
                || StringUtils.isNotEmpty(reqDto.getNisaContractKbnList());
    }
    
    /**
     * 外国証券取引口座情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputForeignSecurityAccountList(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getForeignSecurityAccountDisplay(), CHECK_BOX_ON)
                || StringUtils.isNotEmpty(reqDto.getForeignSecurityAccountList());
    }
    
    /**
     * コース変更完了日情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputCourseChangeFinishDate(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getCourseChangeFinishDateDisplay(), CHECK_BOX_ON)
                || StringUtils.isNotEmpty(reqDto.getCourseChangeFinishDateFrom())
                || StringUtils.isNotEmpty(reqDto.getCourseChangeFinishDateTo());
    }
    
    /**
     * 最終約定日表示情報出力判定メソッド
     *
     * @param reqDto 画面のリクエストパラメータ
     * @return boolean
     */
    private static boolean checkOutputLastTradeDate(IfaCustomerListA005RequestDto reqDto) {
        
        return StringUtils.equals(reqDto.getLastTradeDateDisplay(), CHECK_BOX_ON)
                || StringUtils.isNotEmpty(reqDto.getLastTradeDateFrom())
                || StringUtils.isNotEmpty(reqDto.getLastTradeDateTo());
    }

    /**
     * 法人区分の下のドロップダウンボックス項目の出力判定メソッド
     * 
     * @param modelList 法人区分の下のドロップダウンボックス項目
     * @param isDisplay 法人区分の下のドロップダウンボックス項目（チェック）
     * @return boolean
     */
    public static boolean checkOutputPersonalCorporation(List<IfaCustomerListPersonalCorporation> modelList) {
        List<String> selectList = modelList.stream().filter(f -> f.getIsSelected().equals(TRUE)).map(m -> m.getId())
                .collect(Collectors.toList());
        return selectList.isEmpty() ? false : true;
    }

    /**
     * 投資経験出力判定メソッド
     * 
     * @param modelList 投資経験項目
     * @param isDisplay 投資経験項目（チェック）
     * @param expCode1  投資経験有無コード
     * @param expCode2  投資経験年数コード
     * @return boolean
     */
    public static boolean checkOutputInvestmentExp(List<IfaCustomerListPersonalCorporation> modelList, String isDisplay,
            String expCode1, String expCode2) {
        List<String> investmentExps = modelList.stream().filter(f -> f.getIsSelected().equals(TRUE)).map(m -> m.getId())
                .collect(Collectors.toList());
        if (StringUtils.equals(isDisplay, CHECK_BOX_ON) && investmentExps.isEmpty()) {
            return true;
        } else if (investmentExps.contains(expCode1) || investmentExps.contains(expCode2)) {
            return true;
        } else {
            return false;
        }
    }
}
