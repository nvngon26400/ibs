package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB0501_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 2024/05/17 新規作成
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IfaInfoNewRegisterSql003RequestModel {
    
    /** ユーザーID */
    private String userId;
    
    /** お知らせ参照権限リスト（全角半角）. */
    private List<String> notificationReferenceAuthorityList;
}
