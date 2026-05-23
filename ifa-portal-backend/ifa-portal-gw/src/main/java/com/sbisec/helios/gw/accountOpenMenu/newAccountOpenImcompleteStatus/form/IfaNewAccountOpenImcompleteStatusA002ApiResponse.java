package com.sbisec.helios.gw.accountOpenMenu.newAccountOpenImcompleteStatus.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB020305-01
 * 画面名：新規口座開設不備状況

 * @author 大崎辰弥
    2024/03/01 新規作成
 */

@Data
public class IfaNewAccountOpenImcompleteStatusA002ApiResponse {

    /** 新規口座開設不備状況リスト. */
    private List<IfaNewAccountOpenImcompleteStatusA002ApiResponseNewAccountOpenImcompleteStatus> newAccountOpenImcompleteStatusList;

}
