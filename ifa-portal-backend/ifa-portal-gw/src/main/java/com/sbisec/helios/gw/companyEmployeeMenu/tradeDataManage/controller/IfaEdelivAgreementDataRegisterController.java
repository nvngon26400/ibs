package com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.controller;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterEdelivAgreementDataDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaEdelivAgreementDataRegisterA002ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaEdelivAgreementDataRegisterA003ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaEdelivAgreementDataRegisterA003ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form.IfaEdelivAgreementDataRegisterEdelivAgreementDataApiRequest;

/**
 * 画面ID：SUB0504_02-01
 * 画面名：電子交付同意データ登録
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0504_02-01", screenNumber = "")
public class IfaEdelivAgreementDataRegisterController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IfaEdelivAgreementDataRegisterController.class);

    private final JsonConverter jc = JsonConverter.getInstance();

    private static final String SHEET_NAME = "電子交付同意";

    private static final String DETAIL_LABEL = "明細";

    private static final int LIMIT = 2000;

    private static final int COLUMN_COUNT = 4;

    /**
     * アクセス：/companyEmployeeMenu/tradeDataManage/ifaEdelivAgreementDataRegisterConfirmA002
     * アクションID：A002
     * アクション名：確認
     */
    @PostMapping(value = "/companyEmployeeMenu/tradeDataManage/ifaEdelivAgreementDataRegisterConfirmA002")
    @ResponseBody
    @ResponseJson
    public String confirmA002(@RequestParam("uploadFile") MultipartFile file) throws Exception {

        final long start = System.currentTimeMillis();
        logger.debug("IfaEdelivAgreementDataRegisterController.confirmA002 >> {}", hashCode());

        List<IfaEdelivAgreementDataRegisterA002ApiResponse> apiResList = new ArrayList<>();
        DataList<IfaEdelivAgreementDataRegisterA002ApiResponse> apiResDataList;

        if (file == null || file.isEmpty() || StringUtil.isNullOrEmpty(file.getOriginalFilename())) {
            return jc.toString(IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_NOT_EXCEL_FILE,
                    getMessage(ERRORS_NOT_EXCEL_FILE, new String[] {})));
        }

        if (!checkFileName(file.getOriginalFilename())) {
            return jc.toString(IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_NOT_EXCEL_FILE,
                    getMessage(ERRORS_NOT_EXCEL_FILE, new String[] {})));
        }

        Workbook workbook = null;
        try (InputStream is = file.getInputStream()) {
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) {
                return jc.toString(IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_SHEET_NOT_EXIST,
                        getMessage(ERRORS_SHEET_NOT_EXIST, new String[] { SHEET_NAME })));
            }

            int dataRowCount = getDataRowCount(sheet);
            if (dataRowCount < 1) {
                return jc.toString(IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, INFO_ORDERED_DATA_NOT_EXIST,
                        getMessage(INFO_ORDERED_DATA_NOT_EXIST, new String[] { DETAIL_LABEL })));
            }
            if (dataRowCount > LIMIT) {
                return jc.toString(IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_MAX_UPLOAD,
                        getMessage(ERRORS_MAX_UPLOAD, new String[] { String.valueOf(LIMIT) })));
            }

            List<IfaEdelivAgreementDataRegisterEdelivAgreementDataDto> parsedRows = parseSheet(sheet);
            IfaEdelivAgreementDataRegisterA002RequestDto appReq = new IfaEdelivAgreementDataRegisterA002RequestDto();
            appReq.setEdelivAgreementDataList(parsedRows);

            DataList<IfaEdelivAgreementDataRegisterA002ResponseDto> appRes = ApiRequestUtil.invoke(
                    "cmpIfaEdelivAgreementDataRegisterService", "confirmA002",
                    new TypeReference<DataList<IfaEdelivAgreementDataRegisterA002ResponseDto>>() {
                    }, appReq);

            IfaEdelivAgreementDataRegisterA002ApiResponse apiRes = new IfaEdelivAgreementDataRegisterA002ApiResponse();
            if (appRes != null && appRes.getDataList() != null && !appRes.getDataList().isEmpty()) {
                BeanUtils.copyProperties(apiRes, appRes.getDataList().get(0));
            }
            apiResList.add(apiRes);
            apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                    StringUtil.EMPTY_STRING);

        } catch (EncryptedDocumentException e) {
            logger.info("EncryptedDocumentException occured.", e);
            return jc.toString(IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_HAS_PASSWORD,
                    getMessage(ERRORS_HAS_PASSWORD, new String[] {})));
        } catch (Exception e) {
            logger.error("Exception occured.", e);
            return jc.toString(IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_SERVERERROR,
                    getMessage(ERRORS_SERVERERROR, new String[] {})));
        }

        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        return jc.toString(apiResDataList);
    }

    /**
     * アクセス：/companyEmployeeMenu/tradeDataManage/ifaEdelivAgreementDataRegisterRegisterA003
     * アクションID：A003
     * アクション名：登録
     */
    @PostMapping(value = "/companyEmployeeMenu/tradeDataManage/ifaEdelivAgreementDataRegisterRegisterA003")
    @ResponseBody
    @ResponseJson
    public String registerA003(@RequestBody IfaEdelivAgreementDataRegisterA003ApiRequest apiReq) throws Exception {

        IfaEdelivAgreementDataRegisterA003RequestDto appReq = new IfaEdelivAgreementDataRegisterA003RequestDto();
        if (apiReq != null && apiReq.getEdelivAgreementDataList() != null) {
            List<IfaEdelivAgreementDataRegisterEdelivAgreementDataDto> dataList = new ArrayList<>();
            for (IfaEdelivAgreementDataRegisterEdelivAgreementDataApiRequest row : apiReq.getEdelivAgreementDataList()) {
                IfaEdelivAgreementDataRegisterEdelivAgreementDataDto dto = new IfaEdelivAgreementDataRegisterEdelivAgreementDataDto();
                BeanUtils.copyProperties(dto, row);
                dataList.add(dto);
            }
            appReq.setEdelivAgreementDataList(dataList);
        }

        DataList<IfaEdelivAgreementDataRegisterA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaEdelivAgreementDataRegisterService", "registerA003",
                new TypeReference<DataList<IfaEdelivAgreementDataRegisterA003ResponseDto>>() {
                }, appReq);

        DataList<IfaEdelivAgreementDataRegisterA003ApiResponse> apiRes = new DataList<>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }

    private List<IfaEdelivAgreementDataRegisterEdelivAgreementDataDto> parseSheet(Sheet sheet) {
        List<IfaEdelivAgreementDataRegisterEdelivAgreementDataDto> rows = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String[] values = toArray(row);
            if (values == null) {
                continue;
            }
            IfaEdelivAgreementDataRegisterEdelivAgreementDataDto dto = new IfaEdelivAgreementDataRegisterEdelivAgreementDataDto();
            dto.setButen(values[0]);
            dto.setAccountNumber(values[1]);
            dto.setEdelivAgreementDate(values[2]);
            dto.setEdelivAgreementKbn(values[3]);
            rows.add(dto);
        }
        return rows;
    }

    private int getDataRowCount(Sheet sheet) {
        int count = 0;
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            if (toArray(row) != null) {
                count++;
            }
        }
        return count;
    }

    private String[] toArray(Row row) {
        String[] arr = new String[COLUMN_COUNT];
        DecimalFormat df = new DecimalFormat("#.#########");
        boolean isNull = true;
        for (int i = 0; i < COLUMN_COUNT; i++) {
            arr[i] = StringUtil.EMPTY_STRING;
            Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell != null) {
                isNull = false;
                String value = StringUtil.EMPTY_STRING;
                CellType cellType = cell.getCellType();
                switch (cellType) {
                case NUMERIC:
                    value = df.format(cell.getNumericCellValue());
                    if (i == 1 || i == 3) {
                        value = value.replaceAll("\\.0+$", "");
                    }
                    break;
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                default:
                    break;
                }
                arr[i] = value == null ? StringUtil.EMPTY_STRING : value.trim();
            }
        }
        if (isNull) {
            return null;
        }
        return arr;
    }

    private boolean checkFileName(String fileName) {
        if (StringUtil.isNullOrEmpty(fileName)) {
            return false;
        }
        String lowerName = fileName.toLowerCase();
        int index = lowerName.lastIndexOf(".");
        if (index < 0) {
            return false;
        }
        String ext = lowerName.substring(index + 1);
        return "xlsx".equals(ext) || "xls".equals(ext);
    }
}
