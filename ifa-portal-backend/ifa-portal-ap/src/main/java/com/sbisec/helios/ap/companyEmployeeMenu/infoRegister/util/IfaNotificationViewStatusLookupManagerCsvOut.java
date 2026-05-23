package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sbibits.earth.model.ModelBase;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupCsvItems;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import io.micrometer.core.instrument.util.StringUtils;

public class  IfaNotificationViewStatusLookupManagerCsvOut extends CsvOutPutUtil {
    
    private static final String NOTIFICATION_VIEW_STATUS = "1";
    
    private static final String NOTIFICATION_VIEW_STATUS_TEXT_1 = "済";
    
    private static final String NOTIFICATION_VIEW_STATUS_TEXT_OTHER = "未";
    
    public  IfaNotificationViewStatusLookupManagerCsvOut(ComplianceService comp) {
        
        super(comp);
        
    }
    
    /**
     * Csvタイトル行
     *
     * @return String タイトル行
     */
    @Override
    protected String getCsvHeader() {
        
        return getHeaders();
    }
    
    public static String getHeaders() {
        
        StringBuilder strCsvHead = new StringBuilder(512);
        strCsvHead.append(DOUBLE_QUOTATION + "仲介業者名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "営業員名" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "閲覧状況" + DOUBLE_QUOTATION);
        strCsvHead.append(CSV_SEPARATER);
        strCsvHead.append(DOUBLE_QUOTATION + "閲覧日" + DOUBLE_QUOTATION);
        
        return strCsvHead.toString();
    }
    
    /**
     * お知らせ閲覧状況照会データをcvs形式に変換
     *
     * @param entrustInvestment お知らせ閲覧状況照会
     * @return String cvsお知らせ閲覧状況照会データ
     */
    @Override
    protected String getCsvLine(ModelBase modelBase) {
       
        IfaNotificationViewStatusLookupCsvItems csvItems = (IfaNotificationViewStatusLookupCsvItems) modelBase;
        StringBuilder strCsv = new StringBuilder();

        // 仲介業者名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getBranchName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 営業員名
        strCsv.append(DOUBLE_QUOTATION + StringUtil.nullToEmpty(Cp932.toJIS(csvItems.getEmployeeName())) + DOUBLE_QUOTATION);
        strCsv.append(CSV_SEPARATER);
        // 閲覧状況
        if (NOTIFICATION_VIEW_STATUS.equals(csvItems.getT5nReadFlg())) {
            strCsv.append(DOUBLE_QUOTATION + NOTIFICATION_VIEW_STATUS_TEXT_1 + DOUBLE_QUOTATION);
        } else {
            strCsv.append(DOUBLE_QUOTATION + NOTIFICATION_VIEW_STATUS_TEXT_OTHER + DOUBLE_QUOTATION);
        }
        strCsv.append(CSV_SEPARATER);
        
        // 閲覧日
        if (NOTIFICATION_VIEW_STATUS.equals(csvItems.getT5nReadFlg())) {
            if (StringUtils.isNotEmpty(csvItems.getReadDate())) {
                DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(csvItems.getReadDate(), parser);
                LocalDate date = dateTime.toLocalDate();
                String formatReadDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                strCsv.append(DOUBLE_QUOTATION + formatReadDate + DOUBLE_QUOTATION);
            } else {
                strCsv.append(DOUBLE_QUOTATION + "-" + DOUBLE_QUOTATION);
            }
        } else {
            strCsv.append(DOUBLE_QUOTATION + "-" + DOUBLE_QUOTATION);
        }

        return strCsv.toString();
    }
}

