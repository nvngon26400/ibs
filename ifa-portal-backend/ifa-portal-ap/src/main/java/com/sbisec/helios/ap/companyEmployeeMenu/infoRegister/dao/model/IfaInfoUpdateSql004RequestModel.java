package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaInfoUpdateSql004RequestModel {

    /** お知らせID（数字）. */
    private String notificationId;

    /** 権限コード（全角半角）. */
    private List<String> notificationReferenceAuthorityList;

    /** 登録者（全角半角）. */
    private String createBy;

    /** 更新者. */
    private String updateUser;

}
