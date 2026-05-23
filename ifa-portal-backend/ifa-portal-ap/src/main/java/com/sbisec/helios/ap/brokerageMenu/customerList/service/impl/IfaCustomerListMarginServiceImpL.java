package com.sbisec.helios.ap.brokerageMenu.customerList.service.impl;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ibm.icu.text.Transliterator;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.IfaCustomerListMarginDao;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListMarginSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListMarginSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA002ResponseDtoCustomerList;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListMarginA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.service.IfaCustomerListMarginService;
import com.sbisec.helios.ap.brokerageMenu.customerList.util.IfaCustomerListMarginCsvUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.util.internal.StringUtil;

/**
 * 画面ID：SUB0201_02-01
 * 画面名：顧客一覧・信用
 * @author 鄒
 *
 * 2024/01/10 新規作成
 */
@Component(value = "cmpIfaCustomerListMarginService")
public class IfaCustomerListMarginServiceImpL implements IfaCustomerListMarginService {
    
    /** ユーザ共通情報.権限コード*/
    private static final String PRIV_ID_1 = "1";
    
    private static final String CHK_BROKER_CODE_EXCLUDE_ON = "true";
    
    private static final String CHK_BROKER_CODE_EXCLUDE_1 = "1";
    
    private static final String CHK_BROKER_CODE_EXCLUDE_0 = "0";
    
    private static final String MARGIN_CALL_CHECK_ON = "true";
    
    private static final String MARGIN_CALL_CHECK_1 = "1";
    
    private static final String WITHDRAWAL_DEFICIENT_CHECK_ON = "true";
    
    private static final String WITHDRAWAL_DEFICIENT_CHECK_1 = "1";
    
    /** "参照可能な仲介業者コード／営業員コードが存在しません。"*/
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    private static final String WARNINGS_DATALIST_CSV_OUTPUT = "warnings.dataList.csv.output";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerListMarginServiceImpL.class);
    
    /** FCT038 画面ID*/
    private static final String SCREEN_ID = "SUB0201_02-01";
    
    private static final String ROW_NUM = "5000";
    
    private static final String ROW_NUM_CSV = "700000";
    
    /** と等しい */
    private static final String  CONDITION_1= "1";
    
    /** を含む */
    private static final String  CONDITION_2= "2";
    
    /** で始まる */
    private static final String  CONDITION_3= "3";
    
    private static final String ROW_NUM_MSG = "5,000";
    
    @Autowired
    private IfaCustomerListMarginDao dao;
    
    /**
     * FCT030仲介業者コード営業員リスト取得
     */
    @Autowired
    private Fct030 fct030;
    
    /**
     * FCT038CSVダウンロードMAX件数取得
     */
    @Autowired
    private Fct038 fct038;
    
    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;

    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCustomerListMarginA002RequestDto
     * Dto レスポンス：IfaCustomerListMarginA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerListMarginA002ResponseDto> displayA002(IfaCustomerListMarginA002RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaCustomerListMarginA002ResponseDto> dtoRes = new DataList<IfaCustomerListMarginA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaCustomerListMarginServiceImplL.displayA002");
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        List<IfaCustomerListMarginA002ResponseDto> resDtoList = new ArrayList<IfaCustomerListMarginA002ResponseDto>();
        
        IfaCustomerListMarginA002ResponseDto a002ResponseDto = new IfaCustomerListMarginA002ResponseDto();
        
        // ② 検索前処理
        // ②-1　ユーザ共通情報.権限コード ≠ '1':SBI証券本店の場合：仲介業者コードと営業員コードの参照可能チェックを行う。
        OutputFct030Dto outputFct030Dto = new OutputFct030Dto();
        if (!PRIV_ID_1.equals(userAccount.getMedUsers().getPrivId())) {
            outputFct030Dto = fct030.getData(new InputFct030Dto());
            // FCT030.仲介業者担当者リストの件数が０件の場合：メッセージを表示し、処理終了。
            if(outputFct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return dtoRes;
            }
        }
        // ②-2　仲介業者コードの入力がある場合に仲介業者リストを設定する。
        List<String> brokerCodeList = new ArrayList<String>();
        if(!StringUtil.isNullOrEmpty(dtoReq.getBrokerCode())) {
            brokerCodeList = Arrays.asList(dtoReq.getBrokerCode().split(","));
        }
        
        // ②-3　仲介業者除外チェックボックスのチェック有無判定
        if (CHK_BROKER_CODE_EXCLUDE_ON.equals(dtoReq.getChkBrokerCodeExclude())) {
            // ②-3-1　仲介業者チェックボックスが"on"の場合、仲介業者除外チェックに”1”を設定する。
            dtoReq.setChkBrokerCodeExclude(CHK_BROKER_CODE_EXCLUDE_1);
        } else {
            // ②-3-2　上記以外の場合、仲介業者除外チェックに”0”を設定する。
            dtoReq.setChkBrokerCodeExclude(CHK_BROKER_CODE_EXCLUDE_0);
        }
        // ②-4　検索用顧客名(漢字/カナ)に促音、拗音となる文字が設定されていた場合、大文字・小文字の両方を検索対象とするように正規表現で条件を作成する。
        // ＜例＞入力文字：ツ（大文字）又は、ッ（小文字）　⇒　SQL検索条件（正規表現）：(ツ|ッ)　と両方を検索対象とする
        // ②-5　顧客名(漢字/カナ)条件が「と等しい」以外の場合、以下の通りとする。
        // ②-5-1　条件が「を含む」がの場合、顧客名(漢字/カナ) に ”%" + 顧客名(漢字/カナ) + ”%" を設定する。
        // ②-5-2　条件が「で始まる」がの場合、顧客名(漢字/カナ) に 顧客名(漢字/カナ) + ”%" を設定する。
        String customerNameKanjiKana = "";
        if(!customerNameKanjiKana.equals(dtoReq.getCustomerNameKanjiKana())) {
            String searchName = convertCustomerName(toFullWidth(dtoReq.getCustomerNameKanjiKana()));
            if (CONDITION_1.equals(dtoReq.getCustomerNameKanjiKanaConditionList())) {
                // 「と等しい」
                dtoReq.setCustomerNameKanjiKana(String.format("^%s$", searchName));
               
            } else if (CONDITION_2.equals(dtoReq.getCustomerNameKanjiKanaConditionList())) {
              //「で始まる」
                dtoReq.setCustomerNameKanjiKana(String.format("^%s", searchName));
            } else if (CONDITION_3.equals(dtoReq.getCustomerNameKanjiKanaConditionList())) { 
             // 「を含む」
                dtoReq.setCustomerNameKanjiKana(String.format(".*%s", searchName));
            }
        }
        
        // ②-6　追証チェックボックスのチェック有無判定
        if (MARGIN_CALL_CHECK_ON.equals(dtoReq.getMarginCallCheck())) {
            // ②-6-1　追証チェックボックスが"on"の場合、追証表示チェックに”1”を設定する。
            dtoReq.setMarginCallCheck(MARGIN_CALL_CHECK_1);
        } else {
            dtoReq.setMarginCallCheck(null);
        }
        // ②-7　引出金不足チェックボックスのチェック有無判定
        if (WITHDRAWAL_DEFICIENT_CHECK_ON.equals(dtoReq.getWithdrawalDeficientCheck())) {
            dtoReq.setWithdrawalDeficientCheck(WITHDRAWAL_DEFICIENT_CHECK_1);
        } else {
            dtoReq.setWithdrawalDeficientCheck(null);
        }
        
        // ③ 顧客一覧・信用を検索する。
        IfaCustomerListMarginSql001RequestModel sql001Request = new IfaCustomerListMarginSql001RequestModel();
        BeanUtils.copyProperties(sql001Request, dtoReq);
        sql001Request.setRowNum(ROW_NUM);
        sql001Request.setBrokerCodeList(brokerCodeList);
        sql001Request.setFct030Code(outputFct030Dto.getBrokerChargeList());
        sql001Request.setPrivId(userAccount.getMedUsers().getPrivId());
        
        DataList<IfaCustomerListMarginSql001ResponseModel> sql001Response = dao
                .selectIfaCustomerListMarginSql001(sql001Request);
        
        // ④-1-1　顧客一覧・信用情報データリストの取得件数が0件の場合、対象メッセージの設定を行い、処理を終了。
        if (sql001Response.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.INFO,
                    ERRORS_DATALIST_NOTFOUND, IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
        }
        
        List<IfaCustomerListMarginA002ResponseDtoCustomerList> customerListList = new ArrayList<IfaCustomerListMarginA002ResponseDtoCustomerList>();
        for (IfaCustomerListMarginSql001ResponseModel model : sql001Response.getDataList()) {
            IfaCustomerListMarginA002ResponseDtoCustomerList customer = new IfaCustomerListMarginA002ResponseDtoCustomerList();
            BeanUtils.copyProperties(customer, model);
            customerListList.add(customer);
        }
        a002ResponseDto.setCustomerListList(customerListList);
        resDtoList.add(a002ResponseDto);
        
        if (sql001Response.getDataList().size() != 0 && Integer.valueOf(sql001Response.getDataList().get(0).getTotalRow()) > 5000) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM, new String[] { ROW_NUM_MSG, customerListList.get(0).getTotalRow()}));
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        }
        
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerListMarginA005RequestDto
     * Dto レスポンス：IfaCustomerListMarginA005ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerListMarginA005ResponseDto> csvOutputA005(IfaCustomerListMarginA005RequestDto dtoReq, String sessionId)
            throws Exception {
        
        DataList<IfaCustomerListMarginA005ResponseDto> dtoRes = new DataList<IfaCustomerListMarginA005ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaCustomerListMarginServiceImplL.csvOutputA005");
        
        List<IfaCustomerListMarginA005ResponseDto> resDtoList = new ArrayList<IfaCustomerListMarginA005ResponseDto>();
        
        IfaCustomerListMarginA005ResponseDto a005ResponseDto = new IfaCustomerListMarginA005ResponseDto();
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //② 検索前処理
        //②-1　ユーザ共通情報.権限コード ≠ '1':SBI証券本店の場合：仲介業者コードと営業員コードの参照可能チェックを行う。
        OutputFct030Dto outputFct030Dto = new OutputFct030Dto();
        if (!PRIV_ID_1.equals(userAccount.getMedUsers().getPrivId())) {
            outputFct030Dto = fct030.getData(new InputFct030Dto());
            // FCT030.仲介業者担当者リストの件数が０件の場合：メッセージを表示し、処理終了。
            if(outputFct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return dtoRes;
            }
        }
        
        // ②-2　仲介業者コードの入力がある場合に仲介業者リストを設定する。
        List<String> brokerCodeList = new ArrayList<String>();
        if(!StringUtil.isNullOrEmpty(dtoReq.getBrokerCode())) {
            brokerCodeList = Arrays.asList(dtoReq.getBrokerCode().split(","));
        }
        
        // ②-3　仲介業者除外チェックボックスのチェック有無判定
        if (CHK_BROKER_CODE_EXCLUDE_ON.equals(dtoReq.getChkBrokerCodeExclude())) {
            // ②-3-1　仲介業者チェックボックスが"on"の場合、仲介業者除外チェックに”1”を設定する。
            dtoReq.setChkBrokerCodeExclude(CHK_BROKER_CODE_EXCLUDE_1);
        } else {
            // ②-3-2　上記以外の場合、仲介業者除外チェックに”0”を設定する。
            dtoReq.setChkBrokerCodeExclude(CHK_BROKER_CODE_EXCLUDE_0);
        }
        //②-4　検索用顧客名(漢字/カナ)に促音、拗音となる文字が設定されていた場合、大文字・小文字の両方を検索対象とするように正規表現で条件を作成する。
        //②-5　顧客名(漢字/カナ)条件が「と等しい」以外の場合、以下の通りとする。
        //②-5-1　条件が「を含む」がの場合、顧客名(漢字/カナ) に ”%" + 顧客名(漢字/カナ) + ”%" を設定する。
        //②-5-2　条件が「で始まる」がの場合、顧客名(漢字/カナ) に 顧客名(漢字/カナ) + ”%" を設定する。
        String customerNameKanjiKana = "";
        if(!customerNameKanjiKana.equals(dtoReq.getCustomerNameKanjiKana())) {
            String searchName = convertCustomerName(toFullWidth(dtoReq.getCustomerNameKanjiKana()));
            if (CONDITION_1.equals(dtoReq.getCustomerNameKanjiKanaConditionList())) {
                // 「と等しい」
                dtoReq.setCustomerNameKanjiKana(String.format("^%s$", searchName));
               
            } else if (CONDITION_2.equals(dtoReq.getCustomerNameKanjiKanaConditionList())) {
                //「で始まる」
                dtoReq.setCustomerNameKanjiKana(String.format("^%s", searchName));
                
            } else if (CONDITION_3.equals(dtoReq.getCustomerNameKanjiKanaConditionList())) {
                // 「を含む」
                dtoReq.setCustomerNameKanjiKana(String.format(".*%s", searchName));
                
            }
        }
        
        //③ 顧客一覧・信用を検索する。
        //③-1　取得最大件数の取得
        //CSVダウンロードMAX件数を取得し、取得最大件数にセットする。
        InputFct038Dto inputFct038Dto = new InputFct038Dto();
        inputFct038Dto.setScreenId(SCREEN_ID);
        inputFct038Dto.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto outputFct038Dto = fct038.getData(inputFct038Dto);
        
        //③-2　顧客一覧・信用の検索
        IfaCustomerListMarginSql001RequestModel sql001Request = new IfaCustomerListMarginSql001RequestModel();
        BeanUtils.copyProperties(sql001Request, dtoReq);
        sql001Request.setRowNum(String.valueOf(outputFct038Dto.getCsvDownloadNum()));
        sql001Request.setBrokerCodeList(brokerCodeList);
        sql001Request.setFct030Code(outputFct030Dto.getBrokerChargeList());
        sql001Request.setPrivId(userAccount.getMedUsers().getPrivId());
        DataList<IfaCustomerListMarginSql001ResponseModel> sql001Response = dao
                .selectIfaCustomerListMarginSql001(sql001Request);
        
        //④ ③で抽出した結果をCSVに出力する
        //④-1　③のDB検索結果件数が1件以上の場合、CSVファイルに出力する。
        //全件をアプリケーション内に一時確保することはせず、直接ファイルへ書き込みとする。
        String csvFileName = "";
        String pattern = dtoReq.getPattern() + "," + dtoReq.getMarginCallCheck() + ","
                + dtoReq.getWithdrawalDeficientCheck();
        if (null != sql001Response && sql001Response.getDataList().size() > 0) {
            IfaCustomerListMarginCsvUtil csvOut = new IfaCustomerListMarginCsvUtil(complianceService);
            
            csvFileName = csvOut.doCreateCsvFile(sql001Response, sessionId, userAccount.getUserId(), pattern);
            a005ResponseDto.setPattern(pattern);
            resDtoList.add(a005ResponseDto);
            
        }
        
        //④-2-1　顧客一覧・信用情報データリストの取得件数が0件の場合、対象メッセージを表示し、処理を終了。
        if (null == sql001Response || sql001Response.size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.INFO,
                    ERRORS_DATALIST_NOTFOUND, IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
        }
        
        if (Integer.parseInt(sql001Response.get(0).getTotalRow()) > outputFct038Dto.getCsvDownloadNum()) {
            //④-2-2　件数が取得最大件数超過の場合、対象メッセージを表示
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM, new String[] {String.valueOf(outputFct038Dto.getCsvDownloadNum()),
                            sql001Response.get(0).getTotalRow(),
                            String.valueOf(outputFct038Dto.getCsvDownloadNum())}));
        } else {
            //④-2-3　上記以外の場合、対象メッセージを表示
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OUTPUT,
                            new String[] { sql001Response.get(0).getTotalRow() }));
        }
        
        dtoRes.setTitle(csvFileName);
        
        return dtoRes;
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
