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
public class IfaInfoUpdateSql005RequestModel {

    /** お知らせID（数字）. */
    private String notificationId;

    /** 個別担当者リスト. */
    private List<String> individualRepList;

}
