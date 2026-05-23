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
public class IfaRegisterInfoHeaderValueDtoResponse {
    
    /**  登録情報詳細情報01~16リスト */
    private List<IfaRegisterInfoValue01Value16DtoResponse> registerInfoValueList;
    
    /** 登録情報分類列件数 */
    private int valueCount;
    
    /** 情報分類枝番 */
    private String extensionDetailCd;
    
    /** 登録情報分類コード */
    private String inforExtensionCd;
    
    /** 登録情報分類名称 */
    private String inforExtensionName;
    
    /** 分類名補足説明 */
    private String remarks;
    
    /** 縦の最大行数 */
    private String maxColumn;
    
    /** 詳細情報ヘッダ01 */
    private String headerValue01;
    
    /** 詳細情報ヘッダ02 */
    private String headerValue02;
    
    /** 詳細情報ヘッダ03 */
    private String headerValue03;
    
    /** 詳細情報ヘッダ04 */
    private String headerValue04;
    
    /** 詳細情報ヘッダ05 */
    private String headerValue05;
    
    /** 詳細情報ヘッダ06 */
    private String headerValue06;
    
    /** 詳細情報ヘッダ07 */
    private String headerValue07;
    
    /** 詳細情報ヘッダ08 */
    private String headerValue08;
    
    /** 詳細情報ヘッダ09 */
    private String headerValue09;
    
    /** 詳細情報ヘッダ10 */
    private String headerValue10;
    
    /** 詳細情報ヘッダ11 */
    private String headerValue11;
    
    /** 詳細情報ヘッダ12 */
    private String headerValue12;
    
    /** 詳細情報ヘッダ13 */
    private String headerValue13;
    
    /** 詳細情報ヘッダ14 */
    private String headerValue14;
    
    /** 詳細情報ヘッダ15 */
    private String headerValue15;
    
    /** 詳細情報ヘッダ16 */
    private String headerValue16;
    
}
