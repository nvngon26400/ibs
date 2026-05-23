package com.sbisec.helios.ap.testtool2.service.dto;

import java.util.Map;

import lombok.Data;

/**
 * APIテスト用サービスドイバー　リクエストパラメタ
 *
 */
@Data
public class ApiTestServiceRequest {
    
    /** 呼び出しクラス名　*/
    String testClassName;
    
    /** 呼び出しクラスのテスト対象メソッド名　*/
    String testMethodName;
    
    /** 呼び出しクラスのテスト対象メソッド名の　引数DTO型名*/
    String paramDtoType;
    
    /**　呼び出しクラスのテスト対象メソッドの　引数DTOに設定する引数値 */
    Map<String, Object> paramValue;
}
