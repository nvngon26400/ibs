package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupInternalAdminA003ResponseDto {

    /** CSVファイル名. */
    private String csvFileName;
    
    /** CSV出力件数. */
    private String csvOutputCount;

}
