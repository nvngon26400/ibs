package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 接触履歴受付詳細 A001Apiレスポンス
 * 
 * @author 趙韫慧
 *
 */
@Data
public class IfaContactAcceptDetailA001ApiResponse {

    /** 接触履歴受付詳細リスト */
    private List<IfaContactAcceptDetailA001ContactAcceptDetailApiResponse> contactAcceptDetailInfoList;
}
