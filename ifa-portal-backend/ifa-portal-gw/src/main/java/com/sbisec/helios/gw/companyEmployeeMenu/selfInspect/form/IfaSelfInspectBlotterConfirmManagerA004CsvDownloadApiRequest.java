package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form;

import lombok.Data;

/**
 *　SUB0506_01-01_自己点検記録簿確認（管理者用）A004CSVダウンロード要求
 *
 * @author SCSK
 *
 */
@Data
public class IfaSelfInspectBlotterConfirmManagerA004CsvDownloadApiRequest {
    
    /** CSVファイル名 */
    private String csvDownloadFile;
}
