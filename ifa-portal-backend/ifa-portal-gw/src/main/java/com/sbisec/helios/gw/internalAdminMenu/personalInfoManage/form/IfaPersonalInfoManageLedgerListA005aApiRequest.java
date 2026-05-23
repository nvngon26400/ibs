package com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎辰弥
    2023/12/19 新規作成
 */

@Data
public class IfaPersonalInfoManageLedgerListA005aApiRequest {

    /** 処理日時From. */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @NotEmpty(message = "処理対象期間From")
    @Size(min = 10, max = 10, message = "処理対象期間From")
    private String processDayTimeFrom;

    /** 処理日時To. */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @NotEmpty(message = "処理対象期間To")
    @Size(min = 10, max = 10, message = "処理対象期間To")
    private String processDayTimeTo;


}
