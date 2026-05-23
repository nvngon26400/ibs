package com.sbisec.helios.ap.brokerageMenu.customerList.service.impl;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ibm.icu.text.Transliterator;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.IfaCustomerListFuturesOptionsDao;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListFuturesOptionsSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsA005CsvModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsA005aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListFuturesOptionsCommonRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.service.IfaCustomerListFuturesOptionsService;
import com.sbisec.helios.ap.brokerageMenu.customerList.util.IfaCustomerListFuturesOptionsCsvUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0201_03-01
 * 画面名：顧客一覧・先OP
 *
 * @author SCSK
 2024/05/29 新規作成
 */
@Component(value = "cmpIfaCustomerListFuturesOptionsService")
public class IfaCustomerListFuturesOptionsServiceImpl implements IfaCustomerListFuturesOptionsService {

    /** インフォ.検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String INFO_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    /** ワーニング.検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** ワーニング.検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 */
    private static final String WRANINGS_DATALIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";

    /** ワーニング.{0}件のcsv出力を行います。 */
    private static final String WARNINGS_DATALIST_CSV_OUTPUT = "warnings.dataList.csv.output";

    /** エラー.参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String MSGID_ERR_NOT_EXISTS = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 表示最大件数 */
    private static final int DISPLAY_MAX_ROW = 5000;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;
    
    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;
    
    /**
     * 顧客名条件定義
     *
     */
    private enum CustomerNameCondition {
        
        EQUAL_TO("1", "^%s$"), // 完全一致
        INCLUDING("3", ".*%s"), // 部分一致
        STARTS_WITH("2", "^%s") // 前方一致
        ;
        
        /** 画面からのコード */
        private final String code;
        
        /** DBの正規表現フォーマット */
        private final String dbFormat;
        
        CustomerNameCondition(final String code, final String dbFormat) {
            
            this.code = code;
            this.dbFormat = dbFormat;
        }
        
        /** 値をもとに検索する。見つからない場合はnull */
        static CustomerNameCondition findOrNull(String searchCode) {
            
            for (var c : CustomerNameCondition.values()) {
                if (StringUtils.equals(searchCode, c.code)) {
                    return c;
                }
            }
            return null;
        }
        
        String format(String value) {
            
            return String.format(dbFormat, value);
        }
    }
    
    @Autowired
    private IfaCustomerListFuturesOptionsDao dao;
    
    @Override
    public DataList<IfaCustomerListFuturesOptionsA002ResponseDto> displayA002(
            IfaCustomerListFuturesOptionsCommonRequestDto dtoReq) throws Exception {
        
        var builder = IfaCustomerListFuturesOptionsSql001RequestModel.builder();
        var checkRes = buildParameter(IfaCustomerListFuturesOptionsA002ResponseDto.class, dtoReq, builder);
        if (Objects.nonNull(checkRes)) {
            return checkRes;
        }
        builder.searchLimitRow(DISPLAY_MAX_ROW);
        var sqlRes = dao.selectIfaCustomerListFuturesOptionsSql001(builder.build()) //
                .getDataList();
        var dtoList = sqlRes.stream() //
                .map(r -> IfaCustomerListFuturesOptionsA002ResponseDto.builder() //
                        .accountNumber(r.getAccountNumber()) //
                        .beforeProfitAndLossFuturesOptions(r.getProfLossFuOp())//
                        .branchCode(r.getBrokerBranchCode())//
                        .branchName(r.getBranchName())//
                        .brokerChargeCode(r.getIntermediaryEmpCd()) //
                        .brokerCode(r.getBrokerCode()) //
                        .brokerName(r.getBranchNameHead()) //
                        .butenCode(r.getButenCode())//
                        .chargeName(StringUtil.nullToEmpty(r.getBrokerChargeName()))//
                        .courseName(r.getCustomerAttributeName())//
                        .customerNameKana(r.getNameKana())//
                        .customerNameKanji(r.getNameKanji())//
                        .marginMoneyFuturesOptions(r.getFuOpAcceptMargin()) //
                        .necessaryEntrustDepositFuturesOptions(r.getFuOpRequireMargin()) //
                        .tcCompRank(r.getTcCompRank()) //
                        .build()) //
                .collect(Collectors.toList());

        // 検索条件にヒットした総件数を取得
        int totalRow = dtoList.isEmpty() ? 0 : sqlRes.get(0).getTotalRow();

        // 0件の場合のレスポンス
        if (totalRow == 0) {
            DataList<IfaCustomerListFuturesOptionsA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    Arrays.asList(),
                    ErrorLevel.INFO,
                    INFO_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(INFO_DATALIST_NOT_FOUND)
            );

            return dtoRes;
        }

        // 5,000件超過の場合のレスポンス
        if (DISPLAY_MAX_ROW < totalRow) {
            DataList<IfaCustomerListFuturesOptionsA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    dtoList,
                    ErrorLevel.WARNING,
                    WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(
                            WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                            new String[]{
                                    String.format("%,d", DISPLAY_MAX_ROW),
                                    String.format("%,d", totalRow)
                            }
                    )
            );

            dtoRes.setOverMaxRownum(true);
            dtoRes.setMaxRownum(DISPLAY_MAX_ROW);
            dtoRes.setTotalSize(totalRow);

            return dtoRes;
        }

        // 正常終了の場合のレスポンス
        DataList<IfaCustomerListFuturesOptionsA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                dtoList,
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                ""
        );

        dtoRes.setMaxRownum(totalRow);
        dtoRes.setTotalSize(totalRow);

        return dtoRes;

    }
    
    /**
    * 共通検索チェックとパラメータ設定処理
    */
    private <T> DataList<T> buildParameter(Class<T> type, IfaCustomerListFuturesOptionsCommonRequestDto dtoReq,
            IfaCustomerListFuturesOptionsSql001RequestModel.IfaCustomerListFuturesOptionsSql001RequestModelBuilder builder) {
        
        // 抽出条件の生成
        builder.empCode(dtoReq.getEmpCode()) // 営業員コード
                .branchCode(dtoReq.getBranchCode()) // 支店コード
                .butenCode(dtoReq.getButenCode()) // 部店コード
                .accountNumber(dtoReq.getAccountNumber()) // 口座番号
                .necessaryEntrustDepositFrom(dtoReq.getNecessaryEntrustDepositFrom())// 必要委託保証金（From）
                .necessaryEntrustDepositTo(dtoReq.getNecessaryEntrustDepositTo())// 必要委託保証金（To）
                .marginMoneyFrom(dtoReq.getMarginMoneyFrom()) // 受入証拠金（From）
                .marginMoneyTo(dtoReq.getMarginMoneyTo()) //受入証拠金（To）
                .beforeProfitAndLossFrom(dtoReq.getBeforeProfitAndLossFrom()) //前日評価損益（From）
                .beforeProfitAndLossTo(dtoReq.getBeforeProfitAndLossTo()) //前日評価損益（To）
        ;
        
        List<String> reqBrokerCodes = StringUtils.isBlank(dtoReq.getBrokerCode()) ? Collections.emptyList()
                : Arrays.asList(dtoReq.getBrokerCode().split(","));
        // 仲介業者コード(抽出or除外)
        var isExclude = StringUtils.equals(dtoReq.getChkBrokerCodeExclude(), "true");
        if (isExclude) {
            builder.brokerCodes(Collections.emptyList());
            builder.brokerCodesExclude(reqBrokerCodes);
        } else {
            builder.brokerCodesExclude(Collections.emptyList());
            builder.brokerCodes(reqBrokerCodes);
        }
        // FCT030による絞りこみ
        builder.brokerChargeList(Collections.emptyList());
        
        if (!StringUtils.equals(IfaCommonUtil.getUserAccount().getPrivId(), PrivId.HEAD_OFFICE.getId())) {
            var fct030List = fct030.getData(new InputFct030Dto()).getBrokerChargeList();
            if (fct030List.isEmpty()) {
                return IfaCommonUtil.createDtaList(null, ErrorLevel.FATAL, MSGID_ERR_NOT_EXISTS);
            }
            builder.brokerChargeList(fct030List);
        }

        // ユーザ共通情報.権限コード
        builder.privId(IfaCommonUtil.getUserAccount().getPrivId());
        
        // 顧客名(漢字/カナ)
        if (StringUtils.isNotBlank(dtoReq.getCustomerNameKanjiKana())) {
            Optional.ofNullable(CustomerNameCondition.findOrNull(dtoReq.getCustomerNameKanjiKanaTerms()))//
                    .ifPresent(c -> //
                    builder.customerNameKanjiKana(c.format(convertCustomerName(toFullWidth(dtoReq.getCustomerNameKanjiKana())))));
        }
        // コース選択(全選択の場合は空リストをセット)
        var selectedCourseIds = dtoReq.getCourse().stream() //
                .filter(c -> StringUtils.equals(c.getIsSelected(), "true")) //
                .map(c -> c.getId()) //
                .collect(Collectors.toList());
        builder.courses(
                selectedCourseIds.size() == dtoReq.getCourse().size() ? Collections.emptyList() : selectedCourseIds);
        return null;
    }
    
    @Override
    public DataList<IfaCustomerListFuturesOptionsA005aResponseDto> csvOutputA005(
            IfaCustomerListFuturesOptionsCommonRequestDto dtoReq) throws Exception {
        
        var builder = IfaCustomerListFuturesOptionsSql001RequestModel.builder();
        var checkRes = buildParameter(IfaCustomerListFuturesOptionsA005aResponseDto.class, dtoReq, builder);
        if (Objects.nonNull(checkRes)) {
            return checkRes;
        }
        var csvMaxRow = getCsvMaxRow();
        builder.searchLimitRow(csvMaxRow);
        var sqlRes = dao.selectIfaCustomerListFuturesOptionsSql001(builder.build()) //
                .getDataList();
        var dtoList = sqlRes.stream() //
                .map(r -> IfaCustomerListFuturesOptionsA005CsvModel.builder() //
                        .accountNumber(r.getAccountNumber()) //
                        .beforeProfitAndLossFuturesOptions(r.getProfLossFuOp())//
                        .branchCode(r.getBrokerBranchCode())//
                        .branchName(r.getBranchName())//
                        .brokerChargeCode(r.getIntermediaryEmpCd()) //
                        .brokerCode(r.getBrokerCode()) //
                        .brokerName(r.getBranchNameHead()) //
                        .butenCode(r.getButenCode())//
                        .chargeName(r.getBrokerChargeName())//
                        .courseName(r.getCustomerAttributeName())//
                        .customerNameKana(r.getNameKana())//
                        .customerNameKanji(r.getNameKanji())//
                        .marginMoneyFuturesOptions(r.getFuOpAcceptMargin()) //
                        .necessaryEntrustDepositFuturesOptions(r.getFuOpRequireMargin()) //
                        .tcCompRank(r.getTcCompRank()) //
                        .build()) //
                .collect(Collectors.toList());

        // 検索条件にヒットした総件数を取得
        int totalRow = dtoList.isEmpty() ? 0 : sqlRes.get(0).getTotalRow();
        
        // 0件の場合のレスポンス
        if (totalRow == 0) {
            DataList<IfaCustomerListFuturesOptionsA005aResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    Arrays.asList(),
                    ErrorLevel.INFO,
                    INFO_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(INFO_DATALIST_NOT_FOUND)
            );

            return dtoRes;
        }

        // 1件以上ヒットした場合、CSVファイルを出力する。
        var csvUtil = new IfaCustomerListFuturesOptionsCsvUtil(complianceService);
        var dataList = new DataList<IfaCustomerListFuturesOptionsA005CsvModel>();
        dataList.setDataList(dtoList);
        var fileName = csvUtil.doCreateCsvFile(dataList, //
                IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class), //
                IfaCommonUtil.getUserAccount().getUserId(), null);

        // CSV出力最大件数超過の場合のレスポンス
        if (csvMaxRow < totalRow) {
            DataList<IfaCustomerListFuturesOptionsA005aResponseDto> dtoRes = IfaCommonUtil.createDataList(
                    Arrays.asList(),
                    ErrorLevel.WARNING,
                    WRANINGS_DATALIST_CSV_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(
                        WRANINGS_DATALIST_CSV_OVER_MAX_ROWNUM,
                            new String[]{
                                    String.format("%,d", csvMaxRow),
                                    String.format("%,d", totalRow),
                                    String.format("%,d", csvMaxRow)
                            }
                    )
            );

            dtoRes.setOverMaxRownum(true);
            dtoRes.setMaxRownum(DISPLAY_MAX_ROW);
            dtoRes.setTotalSize(totalRow);
            dtoRes.setTitle(fileName);

            return dtoRes;
        }

        // 正常終了の場合のレスポンス
        DataList<IfaCustomerListFuturesOptionsA005aResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(),
                ErrorLevel.WARNING,
                WARNINGS_DATALIST_CSV_OUTPUT,
                IfaCommonUtil.getMessage(
                        WARNINGS_DATALIST_CSV_OUTPUT,
                        new String[] { String.format("%,d", totalRow) }
                )
        );

        dtoRes.setMaxRownum(totalRow);
        dtoRes.setTotalSize(totalRow);
        dtoRes.setTitle(fileName);

        return dtoRes;

    }

    private int getCsvMaxRow() {
        
        var inDto = new InputFct038Dto();
        inDto.setScreenId("SUB0201_03-01");
        inDto.setUserAuthority(IfaCommonUtil.getUserAccount().getPrivId());
        return fct038.getData(inDto).getCsvDownloadNum();
    }
    
    /**
     * 顧客名の拗音・促音変換
     *
     * @param name 顧客名(漢字／カナ)
     * @return 検索用顧客名
     */
    private String convertCustomerName(String name) {
        
        name = name.replaceAll("ッ", "ツ");
        name = name.replaceAll("ャ", "ヤ");
        name = name.replaceAll("ュ", "ユ");
        name = name.replaceAll("ョ", "ヨ");
        name = name.replaceAll("ツ", "[ツッ]");
        name = name.replaceAll("ヤ", "[ヤャ]");
        name = name.replaceAll("ユ", "[ユュ]");
        name = name.replaceAll("ヨ", "[ヨョ]");
        
        return name;
        
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

    
    private char toFullWidthChar(char value) {
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
