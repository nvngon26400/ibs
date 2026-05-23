package com.sbisec.helios.ap.testtool.service.dto;

import java.util.List;

import lombok.Data;

/**
 * DAOテスト用サービスドイバー　リクエストパラメタ
 *
 * @author 青山
 */
@Data
public class DaoTestServiceRequest {
    
    /** 呼び出しDaoクラス名　*/
    String testClassName;
    
    /** 呼び出しDaoクラスのテスト対象メソッド名　*/
    String testMethodName;
    
    /** 呼び出しDaoクラスのテスト対象メソッド名の　引数DTO型名*/
    String paramDtoType;
    
    /**　呼び出しDaoクラスのテスト対象メソッドの　引数DTOに設定する引数名　配列　*/
    List<String> paramNameList;
    
    /**　呼び出しDaoクラスのテスト対象メソッドの　引数DTOに設定する引数型名　配列　*/
    List<String> paramTypeList;
    
    /**　呼び出しDaoクラスのテスト対象メソッドの　引数DTOに設定する引数値　配列　*/
    List<Object> paramValueList;
}
