package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.util.List;

import lombok.Data;


/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連 苗
 */
@Data
public class IfaRegisterInfoRequestModel {
    
    /** 分類一覧コードリスト */
    private List<String> categoryExtensionCdList;
    
    /** 顧客ID */
    private String customerId;
}
