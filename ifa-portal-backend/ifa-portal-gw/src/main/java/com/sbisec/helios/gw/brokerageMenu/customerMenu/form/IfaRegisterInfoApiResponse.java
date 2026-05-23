package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連 苗
 */
@Data
public class IfaRegisterInfoApiResponse {
    
    /** 分類一覧エリア */
    private List<IfaRegisterInfoRegisterInfoApiResponse> categoryExtensionNameList;
    
    /**  登録情報ヘッダ情報リスト */
    private List<IfaRegisterInfoHeaderValueApiResponse> registerInfoHeaderValueList;

    /** ユーザID用設定値 */
    private String ccsOpId;
}
