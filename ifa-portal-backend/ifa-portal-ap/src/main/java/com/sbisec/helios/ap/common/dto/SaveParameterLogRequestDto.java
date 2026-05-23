package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * パラメータログ登録リクエストDTOクラス。
 *
 * @author SCSK宮坂
 */
@Data
public class SaveParameterLogRequestDto {
    
    /** ユーザーID */
    private String userId = null;
    
    /** ユーザー名 */
    private String userNm = null;
    
    /** 権限ID */
    private String privId = null;
    
    /** メニューID */
    private String menuId = null;
    
    /** コントローラークラス名 */
    private String controllerClassNm = null;
    
    /** HTTPメソッド名 */
    private String httpMethodNm = null;
    
    /** コントローラーメソッド名 */
    private String controllerMethodNm = null;
    
    /** ポジション */
    private String position = null;
    
    /** パラメータ */
    private String parameters = null;
}
