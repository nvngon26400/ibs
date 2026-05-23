package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連 苗
 */
@Data
public class IfaRegisterInfoDtoResponse {
    
    /** 分類一覧エリア */
    private List<IfaRegisterInfoRegisterInfoDtoResponse> categoryExtensionNameList;
    
    /**  登録情報ヘッダ情報リスト */
    private List<IfaRegisterInfoHeaderValueDtoResponse> registerInfoHeaderValueList;

    /** ユーザID用設定値 */
    private String ccsOpId;
}
