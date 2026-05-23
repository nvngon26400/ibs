package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 接触履歴 A001レスポンス
 * 
 * @author 趙韫慧
 *
 */
@Data
public class IfaContactA001ApiResponse {

    /** 接触履歴リスト */
    private List<IfaContactCommonApiResponse> contactInfoList;
}
