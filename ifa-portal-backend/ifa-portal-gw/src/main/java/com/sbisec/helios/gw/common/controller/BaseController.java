package com.sbisec.helios.gw.common.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.WebContentGenerator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.util.IfaDateUtil;

@Controller
public abstract class BaseController extends WebContentGenerator {
    
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    
    private static final String ATTR_KEY_RTIME = "rtime";
    
    protected static final String ATTR_KEY_SEARCH_RESULT_JS = "searchResultJsId";
    
    protected static final String ATTR_KEY_SEARCH_RESULT_JS_PARAM = "searchResultJsParam";
    
    protected static final String LF = "\n";
    
    protected static final String SLASH = "/";
    
    protected static final String HYPHEN = "-";
    
    protected static final String DOUBLE_QUOTATION = "\"";
    
    protected static final String PARAM_CSV = "CSV";
    
    protected static final String CSV_SEPARATER = ",";
    
    protected static final String CSV_CONTENT_TYPE_UTF8 = "application/octet-stream;charset=utf-8";
    
    protected static final String CRLF = "\r\n";
    
    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";
    
    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";
    
    private static final String CSV_FILE_NAME_ENCODING = "UTF-8";
    
    private static final String CSV_CONTENT_TYPE = "application/octet-stream;charset=shift_jis";
    
    private static final String CSV_TEMPORARY_FUNC_ID = "M6";
    
    private static final String CSV_TEMPORARY_CAT_ID = "0";
    
    private static final String CSV_TEMPORARY_DIRECTORY = "csv temporary directory";
    
    private static final String TEMPORARY_CSV_FILE_NAME = "%s_%s_%s.csv";
    
    private static final String CSV_TEMPORARY_DATE_PATTERN = "yyyyMMddHHmmssSSS";
    
    protected static final String STAR_UPLOAD_FILE_DOWNLOAD_PATH = "/opt/download/management/ipopo";
    
    private static final String REPLACE_NAME = "__NAME__";
    
    private static final String REPLACE_DATE = "__DATE__";
    
    private static final String CSV_FILE_NAME = REPLACE_NAME + "_" + REPLACE_DATE + ".csv";
    
    private static final String TRIM_CHARS = "\t\r\n\f";
    
    // mapping for the messages_ja.properties
    public static final String ERRORS_REQUIRED = "errors.required";
    
    public static final String ERRORS_ACCURATELY = "errors.accurately";
    
    public static final String ERRORS_SIZE = "errors.size";
    
    public static final String ERRORS_SELECTED = "errors.selected";
    
    public static final String ERRORS_DATE_SPECIFY_FORMAT = "errors.date.specifyFormat";
    
    public static final String ERRORS_DATE_SPECIFY_BEFORE_YESTERDAY = "errors.date.specifyBeforeYesterday";
    
    public static final String ERRORS_DATE_SPECIFY_FROM_TO = "errors.date.specifyFromTo";
    
    public static final String ERRORS_SESSION_LOST = "errors.session.lost";
    
    public static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";
    
    public static final String WARNINGS_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    public static final String WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    public static final String WARNINGS_DATALIST_CSV_OUTPUT = "warnings.dataList.csv.output";
    
    public static final String WARNINGS_DATALIST_CSV_HEADER_OVER_MAX_ROWNUM = "warnings.dataList.csv.header.overMaxRownum";
    
    public static final String WARNINGS_DATALIST_CSV_HEADER_OUTPUT = "warnings.dataList.csv.header.output";
    
    public static final String ERRORS_COR_COMPLIANCE_CONFIRMATION_COR_CONFIRMATION_FLG = "errors.COR_COMPLIANCE_CONFIRMATION.COR_CONFIRMATION_FLG";
    
    public static final String INFO_COR_COMPLIANCE_CONFIRMATION_COR_CONFIRMATION_FLG = "info.COR_COMPLIANCE_CONFIRMATION.COR_CONFIRMATION_FLG";
    
    public static final String ERRORS_COR_SELF_CHECK_CHANGE_SELF_CHECK = "errors.COR_SELF_CHECK.CHANGE_SELF_CHECK";
    
    public static final String INFO_COR_SELF_CHECK_CHANGE_SELF_CHECK = "info.COR_SELF_CHECK.CHANGE_SELF_CHECK";
    
    public static final String ERRORS_DATE_SPECIFY_BEFORE_TODAY = "errors.date.specifyBeforeToday";
    
    public static final String ERRORS_TEMPFILEDIR_NOTFOUND = "errors.tempFileDir.notfound";
    
    public static final String ERRORS_BROKERCODE_NOTEXIST = "errors.brokerCodeNotExist";
    
    public static final String ERRORS_BROKERCODE_NOTLENGTH = "errors.brokerCodeNotLength";
    
    public static final String ERRORS_BROKERCODE_DUPLICATE = "errors.brokerCodeDuplicate";
    
    public static final String ERRORS_BRANDCODE_NOT_EXIST = "errors.brandCodeNotExist";
    
    public static final String ERRORS_BROKER_BRAND_NOT_EXIST = "errors.brokerBrandNotExist";
    
    public static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    public static final String ERRORS_CSV_TEMPLATE = "errors.CSVTemplate";
    
    public static final String ERRORS_CSV_TEMPLATE_COUNT = "errors.CSVTemplateCount";
    
    public static final String ERRORS_DATE_SPECIFY_AFTER_TODAY = "errors.date.specifyAfterToday";
    
    public static final String ERRORS_DATE_SPECIFY_TODAY = "errors.date.specifyToday";
    
    public static final String ERRORS_DATE_RANGE = "errors.dateRange";
    
    public static final String ERRORS_DELETE_DATA_EXIST = "errors.deleteDataExist";
    
    public static final String ERRORS_DUPLICAT_EKEY = "errors.duplicateKey";
    
    public static final String ERRORS_EXCLUSIVE = "errors.exclusive";
    
    public static final String ERRORS_HANDLING_DATE_EXCLUDE = "errors.handlingDateExclude";
    
    public static final String ERRORS_INSERT_DATA_EXIST = "errors.insertDataExist";
    
    public static final String ERRORS_NUMBER_RANGE = "errors.numberRange";
    
    public static final String ERRORS_ONE_MORE_DATA = "errors.oneMoreData";
    
    public static final String ERRORS_ORDERED_DELETE = "errors.orderedDelete";
    
    public static final String ERRORS_OVER_LIMIT = "errors.overLimit";
    
    public static final String ERRORS_STATUS = "errors.status";
    
    public static final String ERRORS_TIME_SPECIFYFORMAT = "errors.time.specifyFormat";
    
    public static final String ERRORS_TRADE_PRICE_CHANGE = "errors.tradePriceChange";
    
    public static final String ERRORS_TRADE_RANGE = "errors.tradeRange";
    
    public static final String ERRORS_IPO_TRADE_RANGE = "errors.ipoTradeRange";
    
    public static final String ERRORS_TRADE_STOP = "errors.tradeStop";
    
    public static final String ERRORS_TIME_FROM_TO_DOWN = "errors.timeFromToDown";
    
    public static final String ERRORS_TIME_FROM_TO_UP = "errors.timeFromToUp";
    
    public static final String ERRORS_TRADE_TIME = "errors.tradeTime";
    
    public static final String ERRORS_MAX_SIZE = "errors.maxSize";
    
    public static final String ERRORS_NEQ_SIZE = "errors.neqSize";
    
    public static final String ERRORS_TYPE = "errors.type";
    
    public static final String ERRORS_MIXCHECK = "errors.mixCheck";
    
    public static final String ERRORS_SERVERERROR = "errors.serverError";
    
    public static final String ERRORS_TICKER_NOT_EXIST = "errors.tickerNotExist";
    
    public static final String ERRORS_MINVALUE = "errors.minValue";
    
    public static final String ERRORS_NUMBER_FORMAT = "errors.numberFormat";
    
    public static final String ERRORS_LESS_OR_EQUAL = "errors.lessOrEqual";
    
    public static final String ERRORS_BELOW_LIMIT = "errors.belowLimit";
    
    public static final String ERRORS_INVITATION_CHECK = "errors.invitationCheck";
    
    public static final String ERRORS_NUMBER_OVER_RANGE = "errors.numberOverRange";
    
    public static final String ERRORS_ORDER_PRIVILEGE = "errors.orderPrivilege";
    
    public static final String ERRORS_SPECIAL_WORDS = "errors.specialWords";
    
    public static final String ERRORS_EMPTY_RECORD = "errors.emptyRecord";
    
    public static final String ERRORS_FOREIGN_STOCK_CODE_CHANGE = "errors.foreignStockCodeChange";
    
    public static final String ERRORS_DATE_SPECIFY_TO_TODAY = "errors.date.specifyToToday";
    
    public static final String ERRORS_DATE_SPECIFY_AFTER_APPOINTED_DAY = "errors.date.specifyAfterAppointedDay";
    
    public static final String ERRORS_ENGPUBBRAND_CHOOSE = "errors.engPubBrand.choose";
    
    public static final String ERRORS_NOT_LENGTH = "errors.notLength";
    
    public static final String ERRORS_CODE_DUPLICATE = "errors.codeDuplicate";
    
    public static final String ERRORS_INFORMATION = "errors.informationCheck";
    
    public static final String ERRORS_NOTICE_ERROR = "errors.noticeErrorCheck";
    
    public static final String ERRORS_FOREIGN_STOCK_ACCOUNT = "errors.foreignStockAccountCheck";
    
    public static final String ERRORS_SPECIFIC_ACCOUNT = "errors.specificAccountCheck";
    
    public static final String ERRORS_NISA_ACCOUNT = "errors.nisaAccountCheck";
    
    public static final String ERRORS_NISA_ACCOUNT_BUY_CHECK = "errors.nisaAccountBuyCheck";
    
    public static final String ERRORS_NUMBER_INSERT_CHECK = "errors.numberInsertCheck";
    
    public static final String ERRORS_NOT_INSERT_REASON = "errors.notInsertReason";
    
    public static final String ERRORS_SR_REASON_CHECK = "errors.srReasonCheck";
    
    public static final String ERRORS_APPLY_TIME = "errors.applyTime";
    
    public static final String ERRORS_ORDER_TIME_NO_INSERT = "errors.orderTimeNoInsert";
    
    public static final String ERRORS_UNIT_CHECK = "errors.unitCheck";
    
    public static final String ERRORS_APPLICATION_PRICE_INSERT = "errors.applicationPriceInsert";
    
    public static final String ERRORS_APPLICATION_PRICE_RANGE = "errors.applicationPriceRange";
    
    public static final String ERRORS_APPLICATION_PRICE_UNIT = "errors.applicationPriceUnit";
    
    public static final String ERRORS_URGENT_STOP_CHECK = "errors.urgentStopCheck";
    
    public static final String ERRORS_INVESTOR_CHECK = "errors.investorCheck";
    
    public static final String ERRORS_FINANCIAL_ASSETS_CHECK = "errors.financialAssetsCheck";
    
    public static final String ERRORS_ORDER_TIME = "errors.orderTime";
    
    public static final String ERRORS_ORDER_LAST_TIME = "errors.orderLastTime";
    
    public static final String ERRORS_LOTTERY_RESULT_CHECK = "errors.lotteryResultCheck";
    
    public static final String ERRORS_NOT_LOTTERY_CHECK = "errors.notLotteryCheck";
    
    public static final String ERRORS_DATE_OVER_RANGE = "errors.dateOverRange";
    
    public static final String ERRORS_ORDERED_CHECK = "errors.orderedCheck";
    
    public static final String ERRORS_DISCOUNT_RANGE_CHECK = "errors.discountRangeCheck";
    
    public static final String ERRORS_DISCOUNT_UNIT_CHECK = "errors.discountUnitCheck";
    
    public static final String ERRORS_NUMBER_FORMAT_FOR_NARIYUKI = "errors.numberFormatForNariYuKi";
    
    public static final String ERRORS_PERIOD_INPUT_CHECK = "errors.periodInputCheck";
    
    public static final String ERRORS_IPO_OVER_LIMIT = "errors.ipoOverLimit";
    
    public static final String ERRORS_SECTION_ID = "errors.sectionId";
    
    public static final String ERRORS_MAX_UPLOAD = "errors.maxUpload";
    
    public static final String ERRORS_BUY_LIMIT_CHECK = "errors.buyLimitCheck";
    
    public static final String ERRORS_PERIOD_CHECK = "errors.periodCheck";
    
    public static final String ERRORS_EXCEEDED_MAXIMUM = "errors.exceededMaximum";
    
    public static final String ERRORS_COUNT_UNIT_CHECK = "errors.countUnitCheck";
    
    public static final String ERRORS_WARNING_APPLY_UNCHECKED = "errors.warningApplyUnchecked";
    
    public static final String ERRORS_BB_QUANTITY_ALLOC_CHECK = "errors.bbQuantityAllocCheck";
    
    public static final String ERRORS_BB_QUANTITY_ALLOC_INPUT = "errors.bbQuantityAllocCheckInput";
    
    public static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";
    
    public static final String ERRORS_NRI_QUERY_ACCOUNT_BALANCE = "errors.nriQueryAccountBalance";
    
    public static final String ERRORS_BEFORE_DATE = "errors.beforeDate";
    
    public static final String ERRORS_BRANDCODE_NOT_EXIST_OR_OVER_TIME = "errors.brandCodeNotExistOrOverTime";
    
    public static final String ERRORS_KEY_EXIST = "errors.keyExist";
    
    public static final String ERRORS_EXCEL_ONE_MORE_DATA = "errors.excelOneMoreData";
    
    public static final String ERRORS_STARUPLOAD_DUPLICATE = "errors.staruploadDuplicate";
    
    public static final String ERRORS_STARUPLOAD_NONE = "errors.staruploadNone";

    public static final String ERRORS_ABOVE_LIMIT = "errors.aboveLimit";
    
    public static final String ERRORS_HAS_PASSWORD = "errors.hasPassword";
    
    public static final String ERRORS_TARGET_PICKUP_FAILURE = "errors.targetPickUpFailure";
    
    public static final String ERRORS_CONTEXT_DEPENDENT_WORDS = "errors.contextDependentWords";
    
    public static final String ERRORS_STRING_BYTES = "errors.stringBytes";
    
    public static final String ERRORS_EXTAPI_ACCESS_DENIED = "errors.extapi.access.denied";
    
    public static final String ERRORS_VERIFY_COMPLIANCE_FILE_EXISTS = "errors.verifyComplianceFileExists";

    public static final String WARNINGS_NOTICE_WARNING = "warnings.noticeWarningCheck";
    
    public static final String WARNINGS_ORDER_INSERT_WITH_API_ERROR = "warnings.orderInsertWithApiError";
    
    public static final String WARNINGS_CSV_OVER_MAX_ROWNUM = "warnings.csv.overMaxRownum";
    
    public static final String INFO_CORRET_COMPLETED = "info.corretCompleted";
    
    public static final String INFO_DELETE_COMPLETED = "info.deleteCompleted";
    
    public static final String INFO_END_COMPLETED = "info.endCompleted";
    
    public static final String INFO_INSERT_COMPLETED = "info.insertCompleted";
    
    public static final String INFO_RECEPT_COMPLETED = "info.receptCompleted";
    
    public static final String INFO_UPDATE_COMPLETED = "info.updateCompleted";
    
    public static final String INFO_CANCLE_COMPLETED = "info.cancleCompleted";
    
    public static final String INFO_UPLOAD_COMPLETED = "info.uploadCompleted";
    
    public static final String INFO_UPLOAD_UPDATE_COMPLETED = "info.uploadUpdateCompleted";
    
    public static final String INFO_UPLOAD_INSERT_COMPLETED = "info.uploadInsertCompleted";
    
    public static final String INFO_ORDER_COMPLETED = "info.orderCompleted";
    
    public static final String INFO_ORDERED_DATA_NOT_EXIST = "info.orderedDataNotExist";

    public static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    public static final String INFO_APPLY_COMPLETED = "info.applyCompleted";
    
    public static final String ERRORS_CONTEXTDEPENDENTWORDS = "errors.contextDependentWords";
    
    public static final String ERRORS_TIMESTAMP_DUPLICATE = "errors.TimeStampDuplicate";
    
    public static final String ERRORS_CMN_PARAMETERS_NOT_EXIST = "errors.cmn.parameters.notExist";
    
    public static final String ERRORS_EXCLUSIVE_COUNTER_ALLMESSAGE = "errors.exclusive.counter.allmessage";

    public static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    public static final String ERRORS_ONLY_EDELIV_AGREEMENT_CHECK = "errors.onlyEdelivAgreementCheck";
    
    public static final String ERRORS_NOT_EXCEL_FILE = "errors.notExcelFile";
    
    public static final String ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    public static final String INFO_ITA_BROKERAUXILIARYBOOKDIRECTORY_NOTFOUND = "info.ita.BrokerAuxiliaryBookDirectory.notfound";
    
    // mapping for the messages_ja.properties Return code messeges
    public static final String RETURN_CODE_W0001 = "W0001";
    
    public static final String RETURN_CODE_W0002 = "W0002";
    
    public static final String RETURN_CODE_W0003 = "W0003";
    
    public static final String RETURN_CODE_W0004 = "W0004";
    
    public static final String RETURN_CODE_W0005 = "W0005";
    
    public static final String RETURN_CODE_W0006 = "W0006";
    
    // date format
    public static final String HH_MM = "HH:mm";
    
    public static final String HHMM = "HHmm";
    
    public static final String NOT_SEPARATED_YYYYMMDD_HHMM = "yyyyMMddHHmm";
    
    public static final String SEPARATED_YYYYMMDD_HHMM = "yyyy/MM/dd HH:mm";
    
    // date format
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    
    // get application.properties
    private static final String APPLICATION_PROPERTIES = "application.properties";
    
    private static final String UTF_8 = "UTF-8";
    
    /** ComplianceService */
    //	private ComplianceService complianceService =
    //			(ComplianceService) SerivceContext.getInstance().getService("com.sbibits.horus.ap.compliance.service.ComplianceService");
    
    /**
     * MessageSource(= messages.properties)
     */
    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected IfaDateUtil ifaDateUtil;
    
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest req) {
        
        // Set StringTrimmer
        // Empty string is ignore.
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(TRIM_CHARS, false));
    }
    
    /**
     * Get UserAccount from request scope.<br/>
     * When failed to get, return null.
     *
     * @param request
     * @return UserAccount
     */
    protected final UserAccount getUserAccount(HttpServletRequest request) {
        
        return IfaCommonUtil.getUserAccount();
    }
    
    protected void startProcedure(HttpSession session) {
        
        try {
            session.setAttribute("doing", Boolean.TRUE);
        } catch (Throwable e) {
        }
    }
    
    protected void endProcedure(HttpSession session) {
        
        try {
            session.removeAttribute("doing");
        } catch (Throwable e) {
        }
    }
    
    /**
     * Get message from messages.properties.
     *
     * @param messageKey
     * @param values
     * @return String
     */
    protected String getMessage(String messageKey, String[] values) {
        
        return messageSource.getMessage(messageKey, values, Locale.JAPANESE);
    }
    
    protected Date getRequestedTime(HttpServletRequest request) {
        
        return (Date) request.getAttribute(ATTR_KEY_RTIME);
    }
    
    protected String getFormattedRequestedTime(HttpServletRequest request) {
        
        return DateUtil.format(getRequestedTime(request), DateUtil.SEPARATED_YYYYMMDD_HHMMSS);
    }
    
    protected void setStatusAndMessageToDataList(DataList<?> dataList, boolean isCsv) {
        
        if (dataList.getTotalSize() == 0) {
            
            dataList.setMessage(getMessage(ERRORS_DATALIST_NOT_FOUND, new String[] {}));
            dataList.setErrorLevel(ErrorLevel.INFO.getId());
            dataList.setReturnCode(ERRORS_DATALIST_NOT_FOUND);
            
        } else if (dataList.isOverMaxRownum()) {
            
            String maxRownumDelimited = NumberFormat.getInstance(Locale.JAPAN).format(dataList.getMaxRownum());
            String totalSizeDelimited = NumberFormat.getInstance(Locale.JAPAN).format(dataList.getTotalSize());
            
            if (isCsv) {
                
                if (dataList.getMaxRownum() < dataList.getTotalSize()) {
                    
                    dataList.setMessage(getMessage(WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM,
                            new String[] { maxRownumDelimited, totalSizeDelimited, maxRownumDelimited }));
                    dataList.setReturnCode(WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM);
                    
                } else {
                    dataList.setMessage(getMessage(WARNINGS_DATALIST_CSV_OUTPUT, new String[] { totalSizeDelimited }));
                    dataList.setReturnCode(WARNINGS_DATALIST_CSV_OUTPUT);
                }
                
            } else {
                dataList.setMessage(getMessage(WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                        new String[] { maxRownumDelimited, totalSizeDelimited }));
                dataList.setReturnCode(WARNINGS_DATALIST_OVER_MAX_ROWNUM);
            }
            
            dataList.setErrorLevel(ErrorLevel.WARNING.getId());
            
        } else {
            
            dataList.setMessage(StringUtil.EMPTY_STRING);
            dataList.setErrorLevel(ErrorLevel.SUCCESS.getId());
            dataList.setReturnCode(ErrorLevel.SUCCESS.name());
        }

    }
    
    /**
     * Administration menu 個人情報管理台帳 view keeps track of exported data, and this function
     * is used to save the exported information to COR_USER_ATTRIBUTE table
     *
     * @param dataListLength CSV export row size
     * @param userName User who exported the list
     * @param headerString Exported CSV header
     */
    protected final void saveCvsExportRecord(int dataListLength, UserAccount userAccount, String headerString) {
        
        final String FAILSAFE_DOCUMENT_ID = "99";
        
        ScreenId screenId = (ScreenId) this.getClass().getAnnotation(ScreenId.class);
        
        int exportCount = dataListLength;
        
        if (exportCount > 9999) {
            exportCount = 9999; //DB column size limit
        }
        
        String nameFlag = "0";
        String addressFlag = "0";
        String telNumFlag = "0";
        String employerFlag = "0";
        String birthdayFlag = "0";
        String genderFlag = "0";
        String assetFlag = "0";
        String occupationFlag = "0";
        String butenAccountFlag = "0";
        String emailFlag = "0";
        String drawersAccountFlag = "0";
        String viewId = FAILSAFE_DOCUMENT_ID; //'Failsafe', field cannot be null, and there are some trimmings in some queries (so maybe better not use 0)
        
        if (screenId != null) {
            viewId = screenId.screenNumber();
            
            if (viewId.equals("")) {
                // RT3416 - 個人情報管理台帳のファイル欄が空
                logger.info("BaseController.saveCvsExportRecord : cordysCodeMasterScreenId is not set for "
                        + screenId.id() + ". CSV export not recorded in the COR_USER_ATTRBUTE_INFO");
                return;
            }
        } else {
            // RT3416 - 個人情報管理台帳のファイル欄が空
            logger.warn(
                    "BaseController.saveCvsExportRecord : Unable to get screenId from controller, CSV export not recorded in the COR_USER_ATTRBUTE_INFO");
            return;
        }
        
        if (headerString.contains("顧客名")) {
            nameFlag = "1";
        }
        
        if (headerString.contains("住所")) {
            addressFlag = "1";
        }
        
        if (headerString.contains("電話番号")) {
            telNumFlag = "1";
        }
        
        if (headerString.contains("勤務先")) {
            employerFlag = "1";
        }
        
        if (headerString.contains("生年月日")) {
            birthdayFlag = "1";
        }
        
        if (headerString.contains("性別")) {
            genderFlag = "1";
        }
        
        //Different views represent assets in different ways
        if (headerString.contains("前日評価損益") //信用
                || headerString.contains("受入証拠金") //先ＯＰ
                || headerString.contains("入出金額") //入出金・入出庫
                || headerString.contains("数量") //入出金・入出庫
                || headerString.contains("円額") //為替取引履歴
                || headerString.contains("受渡金額（円貨）") //共同募集　取引検索
                || headerString.contains("評価額（円貨）") //共同募集　証券金銭残高 & 証券・金銭残高償還
                || headerString.contains("予定概算金額") //元利払予定一覧
                || ("SUB05902".equals(screenId.id()) && headerString.contains("注文株数")) //IPO・PO 申込一覧
        ) {
            assetFlag = "1";
        }
        
        if (headerString.contains("職業")) {
            occupationFlag = "1";
        }
        
        if (headerString.contains("部店") && headerString.contains("口座番号")) {
            butenAccountFlag = "1";
        }
        
        if (headerString.contains("Ｅメール") //Full width
                || headerString.contains("Eメール")) { //1 byte
            emailFlag = "1";
        }
        
        if (headerString.contains("金融機関口座番号")) {
            drawersAccountFlag = "1";
        }
        
        if (nameFlag.equals("0") && addressFlag.equals("0") && telNumFlag.equals("0") && employerFlag.equals("0")
                && birthdayFlag.equals("0") && genderFlag.equals("0") && assetFlag.equals("0")
                && occupationFlag.equals("0") && butenAccountFlag.equals("0") && emailFlag.equals("0")
                && drawersAccountFlag.equals("0")) {
            return; //If there was nothing private in the export, then we don't need to record it
        }
        
        try {
            ApiRequestUtil.invoke("complianceService", "insertPrivateInformationDownloadRecord",
                    new TypeReference<Integer>() {
                    }, userAccount.getUserId(), //COR_USER_ID
                    nameFlag, //COR_NAME_FLG
                    addressFlag, //COR_ADDRESS_FLG
                    telNumFlag, //COR_TELNO_FLG
                    employerFlag, //COR_OFFICE_FLG
                    birthdayFlag, //COR_BIRTH_FLG
                    genderFlag, //COR_SEX_FLG
                    assetFlag, //COR_EMPLOYMENT_FLG
                    occupationFlag, //COR_OCCUPATION_FLG
                    butenAccountFlag, //COR_BUTEN_ACCOUNT_FLG
                    emailFlag, //COR_EMAIL_FLG
                    drawersAccountFlag, //COR_DRAWERSACCOUNT_FLG
                    viewId, //COR_DOCUMENT
                    String.valueOf(exportCount), //COR_DATA_COUNT
                    "1", //always one ? COR_TRANSACTION_DESC
                    "", //COR_TRANSFER_CODE
                    "", //COR_DEPOSIT
                    "", //COR_DONATION
                    "", //COR_DEPOSITORY
                    "", //COR_DEPOSITORY_TERM
                    "", //COR_DIPOSAL_METHOD
                    "", //COR_DIPOSAL_FLG
                    "", //COR_DIPOSAL_DATE
                    "", //COR_DEPOSIT_OUTLINE
                    "", //COR_DONATION_OUTLINE
                    "", //COR_DEPOSITORY_OUTLINE
                    userAccount.getUserId(), //COR_CREATE_BY
                    userAccount.getBrokerId(), //BROKER_CODE
                    userAccount.getUserNm() //INITIAL_GET_INFO_USER_NAME   
            );
        } catch (Exception e) {
        	logger.warn("BaseController.doOutputCsvFile : Exception occured. {}, ", e);
        }
    }
    
    @SuppressWarnings("rawtypes")
    protected final String doCreateCsvFile(HttpSession session, DataList dataList, UserAccount userAccount)
            throws Exception {
        
        return doCreateCsvFile(session, dataList, userAccount, null);
    }
    
    @SuppressWarnings("rawtypes")
    protected String doCreateCsvFile(HttpSession session, DataList dataList, UserAccount userAccount, String pattern)
            throws Exception {
        
        if (!userAccount.csvDownloadAllowed()) {
            logger.warn("Unauthorized user with ID:" + userAccount.getPrivId() + " attempted accessing CSV download");
            dataList.setErrorLevel(ErrorLevel.FATAL.getId());
            dataList.setMessage(
                    "Unauthorized user with ID:" + userAccount.getPrivId() + " attempted accessing CSV download");
            return String.valueOf(ErrorLevel.FATAL.getId());
        }
        
        String tmpCsv = String.format(
                TEMPORARY_CSV_FILE_NAME,
                DateTimeFormatter.ofPattern(CSV_TEMPORARY_DATE_PATTERN)
                        .format(ifaDateUtil.getCurrentLocalDateTime(ZoneId.of("Asia/Tokyo"))),
                session.getId(),
                userAccount.getUserId()
        );
        //		DataList<GetIdAndDirectoryModel> dirList = new DataList<GetIdAndDirectoryModel>();
        //		try {
        //
        //			dirList = complianceService.GetIdAndDirectory(CSV_TEMPORARY_FUNC_ID, CSV_TEMPORARY_CAT_ID);
        //			if (dirList.getDataList().isEmpty()) {
        //				dataList.setErrorLevel(ErrorLevel.FATAL.getId());
        //				dataList.setMessage(getMessage(ERRORS_TEMPFILEDIR_NOTFOUND, new String[]{CSV_TEMPORARY_DIRECTORY}));
        //				return String.valueOf(ErrorLevel.FATAL.getId());
        //			}
        //			String tmpPath = dirList.get(0).getFileDir();
        //
        //			tmpCsv = tmpPath + tmpCsv;
        //			File file = new File(tmpCsv);
        //			PrintWriter out = new PrintWriter(file);
        //
        //			List list = dataList.getDataList();
        //			int listSize = list.size();
        //
        //			// Write header
        //			out.println(pattern != null ? getCsvHeader(pattern) : getCsvHeader());
        //
        //			for (int i = 0; i < listSize; i++) {
        //				if (pattern != null) {
        //					out.println(getCsvLine((ModelBase) list.get(i), pattern));
        //				} else {
        //					out.println(getCsvLine((ModelBase) list.get(i)));
        //				}
        //			}
        //
        //			out.flush();
        //			out.close();
        //
        //			logger.debug("BaseController.doCreateCsvFile : completed:[{}]", tmpCsv);
        //
        //		// Catch and ingore.
        //		} catch (Exception e) {
        //			logger.warn("BaseController.doCreateCsvFile : Exception occured. {}, {}", tmpCsv, e);
        //			throw e;
        //		}
        
        return tmpCsv;
        
    }
    
    protected final void doDownLoadCsvFile(HttpServletResponse response, String tmpFileName, String fileName,
            UserAccount userAccount) {
        
        doDownLoadCsvFile(response, tmpFileName, fileName, userAccount, null, true);
    }
    
    protected final void doDownLoadCsvFile(HttpServletResponse response, String tmpFileName, String fileName,
            UserAccount userAccount, String pattern) {
        
        doDownLoadCsvFile(response, tmpFileName, fileName, userAccount, pattern, true);
    }
    
    protected final void doDownLoadCsvFile(HttpServletResponse response, String tmpFileName, String fileName,
            UserAccount userAccount, String pattern, Boolean isCheckCsvDownloadAllowed) {
        
        doDownLoadCsvFile(response, tmpFileName, fileName, userAccount, pattern, null, isCheckCsvDownloadAllowed);
    }
    
    protected final void doDownLoadCsvFile(HttpServletResponse response, String tmpFileName, String fileName,
            UserAccount userAccount, String pattern, String contentType, Boolean isCheckCsvDownloadAllowed) {
        
        if (isCheckCsvDownloadAllowed && !userAccount.csvDownloadAllowed()) {
            logger.warn("Unauthorized user with ID:" + userAccount.getPrivId() + " attempted accessing CSV download");
            return;
        }
        
        int lineCount = 0;
        File file = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION, HEADER_VALUE_ATTACHMENT + fileName);
            if (contentType == null) {
                response.setContentType(CSV_CONTENT_TYPE);
                out = response.getWriter();
            } else {
                response.setContentType(contentType);
                // Write byte order mark
                ServletOutputStream os = response.getOutputStream();
                byte[] bom = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
                os.write(bom);
                out = new PrintWriter(new OutputStreamWriter(os));
            }
            
            file = new File(tmpFileName);
            in = new BufferedReader(new FileReader(file));
            
            String line;
            while ((line = in.readLine()) != null) {
                out.println(line);
                lineCount++;
            }
            // 備考改行時、個人情報管理台帳記載エーラ
            //lineCount = getNewLineCount(file);
            
            // Catch and ingore.
        } catch (Exception e) {
            logger.warn("BaseController.doOutputCsvFile : Exception occured. {}, {}", fileName, e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    logger.warn("BaseController.doOutputCsvFile : Exception occured. {}, {}", fileName, e1);
                }
            }
            if (file != null && file.exists()) {
                try {
                    if (!file.delete()) {
                        logger.warn("Failed to delete temporary file: {}", file.getAbsolutePath());
                    }
                } catch (SecurityException e) {
                    logger.error("Security exception occurred. Delete file: {}", file.getAbsolutePath(), e);
                } catch (Exception e) {
                    logger.error("Unexpected error occurred. Delete file: {}", file.getAbsolutePath(), e);
                }
                
            }
            //deleteTempFile();
        }
        
        // We only record donwloads by the userID '3' to the database
        if (!userAccount.getPrivId().equals("3")) {
            return;
        }
        
        if (pattern == null) {
            saveCvsExportRecord(lineCount - 1, userAccount, getCsvHeader());
        } else {
            saveCvsExportRecord(lineCount - 1, userAccount, getCsvHeader(pattern));
        }
    }
    
    //	@SuppressWarnings("resource")
    //	private int getNewLineCount(File file) throws Exception {
    //		// Streamを開ける
    //		InputStream in = new FileInputStream(file);
    //		CSVReader reader = new CSVReader(new InputStreamReader(in));
    //		int lineCount = 0;
    //		while ((reader.readNext()) != null) {
    //			lineCount++;
    //		}
    //		return lineCount;
    //	}
    
    /**
     * 前々日以前のCSV temporary fileを削除
     */
    //	private void deleteTempFile(){
    //		try{
    //			DataList<GetIdAndDirectoryModel> dataList = new DataList<GetIdAndDirectoryModel>();
    //			dataList = complianceService.GetIdAndDirectory(CSV_TEMPORARY_FUNC_ID, CSV_TEMPORARY_CAT_ID);
    //			if (dataList.getDataList().isEmpty()) {
    //				logger.warn("BaseController.deleteTempFile : Exception occured. {}"
    //						   , getMessage(ERRORS_TEMPFILEDIR_NOTFOUND
    //						   , new String[]{CSV_TEMPORARY_DIRECTORY}));
    //				return;
    //			}
    //			LocalDate yesterday = LocalDate.now().minusDays(1);
    //			File dir = new File(dataList.get(0).getFileDir());
    //			File[] files = dir.listFiles();
    //			for (int i = 0; i < files.length; i++) {
    //				File file = files[i];
    //				LocalDate lastModifyDay = LocalDateTime.ofInstant( Instant.ofEpochMilli(file.lastModified())
    //						                                         , ZoneId.systemDefault()
    //						                                         ).toLocalDate();
    //				if(yesterday.compareTo(lastModifyDay) > 0){
    //					file.delete();
    //					logger.warn("BaseController.deleteTempFile Deleted : {}", file.getName());
    //				}
    //			}
    //		}catch(Exception e){
    //			logger.warn("BaseController.deleteTempfile : Exception occured. {}", e);
    //		}
    //	}
    
    @SuppressWarnings("rawtypes")
    protected final void doOutputCsvFile(HttpServletResponse response, String fileName, DataList dataList,
            UserAccount userAccount, String pattern, String contentType) {
        
        if (!userAccount.csvDownloadAllowed()) {
            logger.warn("Unauthorized user with ID:" + userAccount.getPrivId() + " attempted accessing CSV download");
            return;
        }
        
        try {
            PrintWriter out = null;
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION, HEADER_VALUE_ATTACHMENT + fileName);
            if (contentType == null) {
                response.setContentType(CSV_CONTENT_TYPE);
                out = response.getWriter();
            } else {
                response.setContentType(contentType);
                // Write byte order mark
                ServletOutputStream os = response.getOutputStream();
                byte[] bom = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
                os.write(bom);
                out = new PrintWriter(new OutputStreamWriter(os));
            }
            
            List list = dataList.getDataList();
            int listSize = list.size();
            
            // Write header
            out.println(pattern != null ? getCsvHeader(pattern) : getCsvHeader());
            
            for (int i = 0; i < listSize; i++) {
                if (pattern != null) {
                    out.println(getCsvLine((ModelBase) list.get(i), pattern));
                } else {
                    out.println(getCsvLine((ModelBase) list.get(i)));
                }
            }
            
            out.flush();
            out.close();
            
            logger.info("BaseController.doOutputCsvFile : completed:[{}]", fileName);
            
            // Catch and ingore.
        } catch (Exception e) {
            
            logger.warn("BaseController.doOutputCsvFile : Exception occured. {}, {}", fileName, e);
        }
        
        // We only record donwloads by the userID '3' to the database
        if (!userAccount.getPrivId().equals("3")) {
            return;
        }
        
        if (pattern == null) {
            //			saveCvsExportRecord(dataList.size(), userAccount, getCsvHeader());
        } else {
            //			saveCvsExportRecord(dataList.size(), userAccount, getCsvHeader(pattern));
        }
        
    }
    
    /**
     * STARアップロードファイル
     * @param response
     * @param tmpFileName (CRLF,UTF-8)
     * @param fileName
     * @param userAccount
     */
    protected final void doDownLoadStarCsvFile(HttpServletResponse response, String tmpFileName, String fileName,
            UserAccount userAccount) {
        
        if (!userAccount.csvDownloadAllowed()) {
            logger.warn("Unauthorized user with ID:" + userAccount.getPrivId() + " attempted accessing CSV download");
            return;
        }
        
        File file = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION, HEADER_VALUE_ATTACHMENT + fileName);
            response.setContentType(CSV_CONTENT_TYPE);
            out = response.getWriter();
            
            file = new File(tmpFileName);
            in = new BufferedReader(
                    new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("Shift_JIS"))));
            String line;
            while ((line = in.readLine()) != null) {
                out.write(line);
                out.write(CRLF);
            }
            
            // Catch and ingore.
        } catch (Exception e) {
            logger.warn("BaseController.doDownLoadStarCsvFile : Exception occured. {}, {}", fileName, e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    logger.warn("BaseController.doDownLoadStarCsvFile : Exception occured. {}, {}", fileName, e1);
                }
            }
            //			if(file != null && file.exists()){
            //				file.delete();
            //			}
            //			deleteTempFile();
        }
        
    }
    
    @SuppressWarnings("rawtypes")
    protected final void doOutputCsvFile(HttpServletResponse response, String fileName, DataList dataList,
            UserAccount userAccount) {
        
        doOutputCsvFile(response, fileName, dataList, userAccount, null);
    }
    
    @SuppressWarnings("rawtypes")
    protected final void doOutputCsvFile(HttpServletResponse response, String fileName, DataList dataList,
            UserAccount userAccount, String pattern) {
        
        doOutputCsvFile(response, fileName, dataList, userAccount, pattern, null);
    }
    
    protected String getCsvFileName(String name) throws Exception {
        
        String encordedName = URLEncoder.encode(name, CSV_FILE_NAME_ENCODING);
        
        return CSV_FILE_NAME.replaceAll(REPLACE_NAME, encordedName).replaceAll(REPLACE_DATE,
                DateUtil.format(ifaDateUtil.getCurrentDate(), DateUtil.YYYYMMDD));
    }
    
    protected String getCsvHeader() {
        
        throw new UnsupportedOperationException(
                "When the controller using doOutputCsvFile(), it's have to override getCsvHeader().");
    }
    
    protected String getCsvHeader(String pattern) {
        
        throw new UnsupportedOperationException(
                "When the controller using doOutputCsvFile(), it's have to override getCsvHeader().");
    }
    
    protected String getCsvLine(ModelBase model) {
        
        throw new UnsupportedOperationException(
                "When the controller using doOutputCsvFile(), it's have to override getCsvLine().");
    }
    
    protected String getCsvLine(ModelBase model, String pattern) {
        
        throw new UnsupportedOperationException(
                "When the controller using doOutputCsvFile(), it's have to override getCsvLine().");
    }
    
    protected Properties getApplicationProperties() throws Exception {
        
        PropertiesFactoryBean pfb = new PropertiesFactoryBean();
        //Set Application Properties File
        pfb.setLocation(new ClassPathResource(APPLICATION_PROPERTIES));
        pfb.setFileEncoding(UTF_8);
        try {
            pfb.afterPropertiesSet();
            return pfb.getObject();
        } catch (IOException e) {
            throw e;
        }
    }
    
    /**
     * Sub class must return first view name.
     *
     * @return first view name
     */
    protected String getFirstViewName() {
        return null;
    }
    
    // RT3760 - POSTでパラメータがnullでの送信に対応
    public String getViewName() {
        
        return getFirstViewName();
    }
}
