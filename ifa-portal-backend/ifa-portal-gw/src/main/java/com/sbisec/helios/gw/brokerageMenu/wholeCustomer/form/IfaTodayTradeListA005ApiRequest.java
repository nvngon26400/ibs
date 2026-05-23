package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class IfaTodayTradeListA005ApiRequest {
    
    /** CSVファイル名 */
    private String csvDownloadFile;

    /** CSV出力データ. */
    private List<IfaTodayTradeListA005TodayTradeApiResponse> csvData = new ArrayList<IfaTodayTradeListA005TodayTradeApiResponse>();

}
