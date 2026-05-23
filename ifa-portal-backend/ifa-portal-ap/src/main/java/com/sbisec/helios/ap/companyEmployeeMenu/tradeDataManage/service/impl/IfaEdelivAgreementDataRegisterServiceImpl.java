package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ipopo.ErrorType;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.IfaEdelivAgreementDataRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaEdelivAgreementDataRegisterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaEdelivAgreementDataRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterCheckResultSetDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterEdelivAgreementDataDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.service.IfaEdelivAgreementDataRegisterService;

/**
 * 画面ID：SUB0504_02-01
 * 画面名：電子交付同意データ登録
 */
@Component(value = "cmpIfaEdelivAgreementDataRegisterService")
public class IfaEdelivAgreementDataRegisterServiceImpl implements IfaEdelivAgreementDataRegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaEdelivAgreementDataRegisterServiceImpl.class);

    private static final String ERRORS_REQUIRED = "errors.required";
    private static final String ERRORS_TYPE = "errors.type";
    private static final String ERRORS_MAX_SIZE = "errors.maxSize";
    private static final String ERRORS_DATE_SPECIFY_FORMAT = "errors.date.specifyFormat";
    private static final String ERRORS_NUMBER_FORMAT = "errors.numberFormat";
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    private static final String INFO_ORDERED_DATA_NOT_EXIST = "info.orderedDataNotExist";
    private static final String INFO_UPLOAD_INSERT_COMPLETED = "info.uploadInsertCompleted";

    private static final String LABEL_BUTEN = "部店";
    private static final String LABEL_ACCOUNT_NUMBER = "口座番号";
    private static final String LABEL_EDELIV_AGREEMENT_DATE = "電子交付承諾日付";
    private static final String LABEL_EDELIV_AGREEMENT_KBN = "電子交付承諾区分";
    private static final String DATE_FORMAT = "YYYY/MM/DD";
    private static final String HALF_WIDTH_ALPHANUMERIC = "半角英数字";
    private static final String HALF_WIDTH_NUMBER = "半角数字";
    private static final String KBN_FORMAT = "0、1";
    private static final String REGISTERED_STATUS = "登録済";
    private static final String DISPLAY_MESSAGE_FLAG_ON = "1";
    private static final String EDELIV_AGREEMENT_KBN_AGREED = "1";
    private static final String EDELIV_AGREEMENT_KBN_KANJI_AGREED = "承諾済";
    private static final String EDELIV_AGREEMENT_KBN_KANJI_NOT_AGREED = "未承諾";

    private static final Pattern ALPHA_NUMERIC_PATTERN = Pattern.compile("^[a-zA-Z0-9]*$");
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^[0-9]*$");

    @Autowired
    private IfaEdelivAgreementDataRegisterDao dao;

    @Override
    public DataList<IfaEdelivAgreementDataRegisterA002ResponseDto> confirmA002(
            IfaEdelivAgreementDataRegisterA002RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaEdelivAgreementDataRegisterServiceImpl.confirmA002");
        }

        List<IfaEdelivAgreementDataRegisterEdelivAgreementDataDto> inputList = Optional.ofNullable(dtoReq)
                .map(IfaEdelivAgreementDataRegisterA002RequestDto::getEdelivAgreementDataList)
                .orElse(Collections.emptyList());

        List<IfaEdelivAgreementDataRegisterEdelivAgreementDataDto> resultList = new ArrayList<>();
        for (IfaEdelivAgreementDataRegisterEdelivAgreementDataDto row : inputList) {
            resultList.add(validateRow(row));
        }

        IfaEdelivAgreementDataRegisterA002ResponseDto responseDto = new IfaEdelivAgreementDataRegisterA002ResponseDto();
        responseDto.setEdelivAgreementDataList(resultList);

        return IfaCommonUtil.createDataList(
                Arrays.asList(responseDto),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaEdelivAgreementDataRegisterA003ResponseDto> registerA003(
            IfaEdelivAgreementDataRegisterA003RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaEdelivAgreementDataRegisterServiceImpl.registerA003");
        }

        List<IfaEdelivAgreementDataRegisterEdelivAgreementDataDto> inputList = Optional.ofNullable(dtoReq)
                .map(IfaEdelivAgreementDataRegisterA003RequestDto::getEdelivAgreementDataList)
                .orElse(Collections.emptyList());

        List<IfaEdelivAgreementDataRegisterEdelivAgreementDataDto> okRows = new ArrayList<>();
        for (IfaEdelivAgreementDataRegisterEdelivAgreementDataDto row : inputList) {
            if (ErrorType.OK.getLabel().equals(row.getCheckResult())) {
                okRows.add(row);
            }
        }

        if (okRows.isEmpty()) {
            return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.FATAL,
                    INFO_ORDERED_DATA_NOT_EXIST,
                    IfaCommonUtil.getMessage(INFO_ORDERED_DATA_NOT_EXIST, new String[] { "OK" }));
        }

        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String userId = userAccount.getUserId();

        for (IfaEdelivAgreementDataRegisterEdelivAgreementDataDto row : okRows) {
            IfaEdelivAgreementDataRegisterSql002RequestModel sql002Req = new IfaEdelivAgreementDataRegisterSql002RequestModel();
            sql002Req.setButenCode(row.getButen());
            sql002Req.setAccountNumber(row.getAccountNumber());
            sql002Req.setEdelivAgreementDate(row.getEdelivAgreementDate());
            sql002Req.setEdelivAgreementKbn(row.getEdelivAgreementKbn());
            sql002Req.setUserId(userId);
            dao.mergeIfaEdelivAgreementDataRegisterSql002(sql002Req);
            row.setCheckResult(REGISTERED_STATUS);
            row.setEdelivAgreementKbnKanji(toEdelivAgreementKbnKanji(row.getEdelivAgreementKbn()));
        }

        IfaEdelivAgreementDataRegisterA003ResponseDto responseDto = new IfaEdelivAgreementDataRegisterA003ResponseDto();
        responseDto.setEdelivAgreementDataList(inputList);

        String infoMessage = IfaCommonUtil.getMessage(INFO_UPLOAD_INSERT_COMPLETED, new String[] {
                String.valueOf(okRows.size())
        });

        return IfaCommonUtil.createDataList(
                Arrays.asList(responseDto),
                ErrorLevel.SUCCESS,
                INFO_UPLOAD_INSERT_COMPLETED,
                infoMessage);
    }

    private IfaEdelivAgreementDataRegisterEdelivAgreementDataDto validateRow(
            IfaEdelivAgreementDataRegisterEdelivAgreementDataDto row) throws Exception {

        IfaEdelivAgreementDataRegisterEdelivAgreementDataDto result = copyRow(row);
        List<IfaEdelivAgreementDataRegisterCheckResultSetDto> checkResultSetList = new ArrayList<>();

        String buten = trimValue(result.getButen());
        String accountNumber = trimValue(result.getAccountNumber());
        String edelivAgreementDate = trimValue(result.getEdelivAgreementDate());
        String edelivAgreementKbn = trimValue(result.getEdelivAgreementKbn());

        if (!StringUtil.isNullOrEmpty(buten)) {
            buten = buten.toUpperCase();
        }
        result.setButen(buten);
        result.setAccountNumber(accountNumber);
        result.setEdelivAgreementDate(edelivAgreementDate);
        result.setEdelivAgreementKbn(edelivAgreementKbn);
        result.setEdelivAgreementKbnKanji(toEdelivAgreementKbnKanji(edelivAgreementKbn));

        validateButen(buten, checkResultSetList);
        validateAccountNumber(accountNumber, checkResultSetList);
        validateEdelivAgreementDate(edelivAgreementDate, checkResultSetList);
        validateEdelivAgreementKbn(edelivAgreementKbn, checkResultSetList);

        if (checkResultSetList.isEmpty()) {
            IfaEdelivAgreementDataRegisterSql001RequestModel sql001Req = new IfaEdelivAgreementDataRegisterSql001RequestModel();
            sql001Req.setButenCode(buten);
            sql001Req.setAccountNumber(accountNumber);
            Integer count = dao.selectIfaEdelivAgreementDataRegisterSql001(sql001Req);
            if (count == null || count == 0) {
                addCheckResult(
                        checkResultSetList,
                        IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST, new String[] { buten, accountNumber }));
            }
        }

        if (checkResultSetList.isEmpty()) {
            result.setCheckResult(ErrorType.OK.getLabel());
            result.setDisplayMessageFlag(StringUtil.EMPTY_STRING);
        } else {
            result.setCheckResult(ErrorType.ERROR.getLabel());
            result.setDisplayMessageFlag(DISPLAY_MESSAGE_FLAG_ON);
        }
        result.setCheckResultSetList(checkResultSetList);
        return result;
    }

    private void validateButen(String buten, List<IfaEdelivAgreementDataRegisterCheckResultSetDto> checkResultSetList) {
        if (StringUtil.isNullOrEmpty(buten)) {
            addCheckResult(checkResultSetList, IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { LABEL_BUTEN }));
            return;
        }
        if (!ALPHA_NUMERIC_PATTERN.matcher(buten).matches()) {
            addCheckResult(checkResultSetList,
                    IfaCommonUtil.getMessage(ERRORS_TYPE, new String[] { LABEL_BUTEN, HALF_WIDTH_ALPHANUMERIC }));
        }
        if (buten.length() > 3) {
            addCheckResult(checkResultSetList,
                    IfaCommonUtil.getMessage(ERRORS_MAX_SIZE, new String[] { LABEL_BUTEN, "3" }));
        }
    }

    private void validateAccountNumber(String accountNumber,
            List<IfaEdelivAgreementDataRegisterCheckResultSetDto> checkResultSetList) {
        if (StringUtil.isNullOrEmpty(accountNumber)) {
            addCheckResult(checkResultSetList,
                    IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { LABEL_ACCOUNT_NUMBER }));
            return;
        }
        if (!NUMERIC_PATTERN.matcher(accountNumber).matches()) {
            addCheckResult(checkResultSetList,
                    IfaCommonUtil.getMessage(ERRORS_TYPE, new String[] { LABEL_ACCOUNT_NUMBER, HALF_WIDTH_NUMBER }));
        }
        if (accountNumber.length() > 6) {
            addCheckResult(checkResultSetList,
                    IfaCommonUtil.getMessage(ERRORS_MAX_SIZE, new String[] { LABEL_ACCOUNT_NUMBER, "6" }));
        }
    }

    private void validateEdelivAgreementDate(String edelivAgreementDate,
            List<IfaEdelivAgreementDataRegisterCheckResultSetDto> checkResultSetList) {
        if (StringUtil.isNullOrEmpty(edelivAgreementDate)) {
            return;
        }
        if (!isValidEdelivAgreementDate(edelivAgreementDate)) {
            addCheckResult(checkResultSetList, IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFY_FORMAT,
                    new String[] { LABEL_EDELIV_AGREEMENT_DATE, DATE_FORMAT }));
        }
    }

    private void validateEdelivAgreementKbn(String edelivAgreementKbn,
            List<IfaEdelivAgreementDataRegisterCheckResultSetDto> checkResultSetList) {
        if (StringUtil.isNullOrEmpty(edelivAgreementKbn)) {
            addCheckResult(checkResultSetList,
                    IfaCommonUtil.getMessage(ERRORS_REQUIRED, new String[] { LABEL_EDELIV_AGREEMENT_KBN }));
            return;
        }
        if (!("0".equals(edelivAgreementKbn) || "1".equals(edelivAgreementKbn))) {
            addCheckResult(checkResultSetList, IfaCommonUtil.getMessage(ERRORS_NUMBER_FORMAT,
                    new String[] { LABEL_EDELIV_AGREEMENT_KBN, KBN_FORMAT }));
        }
    }

    private boolean isValidEdelivAgreementDate(String value) {
        if (!Pattern.compile("^\\d{4}/\\d{2}/\\d{2}$").matcher(value).matches()) {
            return false;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            format.setLenient(false);
            format.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void addCheckResult(List<IfaEdelivAgreementDataRegisterCheckResultSetDto> checkResultSetList, String msg) {
        IfaEdelivAgreementDataRegisterCheckResultSetDto checkResult = new IfaEdelivAgreementDataRegisterCheckResultSetDto();
        checkResult.setStatus(ErrorType.ERROR.getLabel());
        checkResult.setMsg(StringUtils.replace(msg, "<br>", "\n"));
        checkResultSetList.add(checkResult);
    }

    private String toEdelivAgreementKbnKanji(String edelivAgreementKbn) {
        if (EDELIV_AGREEMENT_KBN_AGREED.equals(edelivAgreementKbn)) {
            return EDELIV_AGREEMENT_KBN_KANJI_AGREED;
        }
        if ("0".equals(edelivAgreementKbn)) {
            return EDELIV_AGREEMENT_KBN_KANJI_NOT_AGREED;
        }
        return StringUtil.EMPTY_STRING;
    }

    private IfaEdelivAgreementDataRegisterEdelivAgreementDataDto copyRow(
            IfaEdelivAgreementDataRegisterEdelivAgreementDataDto source) {
        IfaEdelivAgreementDataRegisterEdelivAgreementDataDto target = new IfaEdelivAgreementDataRegisterEdelivAgreementDataDto();
        target.setButen(source.getButen());
        target.setAccountNumber(source.getAccountNumber());
        target.setEdelivAgreementDate(source.getEdelivAgreementDate());
        target.setEdelivAgreementKbn(source.getEdelivAgreementKbn());
        target.setEdelivAgreementKbnKanji(source.getEdelivAgreementKbnKanji());
        target.setCheckResult(source.getCheckResult());
        target.setDisplayMessageFlag(source.getDisplayMessageFlag());
        if (source.getCheckResultSetList() != null) {
            target.setCheckResultSetList(new ArrayList<>(source.getCheckResultSetList()));
        }
        return target;
    }

    private String trimValue(String value) {
        return value == null ? StringUtil.EMPTY_STRING : value.trim();
    }
}
