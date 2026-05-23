package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Data
public class IfaInfoUpdateA008aApiResponse {

    /** 登録ファイル1（ファイル名）. */
    private String registerFileName1;

    /** 登録ファイル2（ファイル名）. */
    private String registerFileName2;

    /** 登録ファイル3（ファイル名）. */
    private String registerFileName3;

    /** ファイルディレクトリ */
    private String fileDirectory;
}
