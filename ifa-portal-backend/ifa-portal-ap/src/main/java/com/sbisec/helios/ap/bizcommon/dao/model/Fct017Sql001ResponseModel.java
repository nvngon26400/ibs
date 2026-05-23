package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

/**
 * DAOリクエストモデル：FCT017
 *
 * @author SCSK
 *
 */

@Data
public class Fct017Sql001ResponseModel {
    
    //国内投信ユーザー固有属性.受入書類コード１
    private String acceptanceCd1;
    
    //国内投信ユーザー固有属性.受入書類コード１に紐づくコードマスタ.コード２
    private String code2BondingAcceptanceCd1;
    
    //国内投信ユーザー固有属性.受入書類コード１に紐づくコードマスタ.値
    private String nameBondingAcceptanceCd1;
    
    //国内投信ユーザー固有属性.受入書類コード２
    private String acceptanceCd2;
    
    //国内投信ユーザー固有属性.受入書類コード２に紐づくコードマスタ.コード２
    private String code2BondingAcceptanceCd2;
    
    //国内投信ユーザー固有属性.受入書類コード２に紐づくコードマスタ.値
    private String nameBondingAcceptanceCd2;
    
    //国内投信ユーザー固有属性.受入書類コード３
    private String acceptanceCd3;
    
    //国内投信ユーザー固有属性.受入書類コード３に紐づくコードマスタ.コード２
    private String code2BondingAcceptanceCd3;
    
    //国内投信ユーザー固有属性.受入書類コード３に紐づくコードマスタ.値
    private String nameBondingAcceptanceCd3;
    
}
