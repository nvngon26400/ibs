package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB0501_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 2024/05/17 新規作成
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IfaInfoNewRegisterSql002RequestModel {
    
    /** タイトル */
    private String t4nTitle;
    
    /** 内容 */
    private String t4nContent;
    
    /** 添付ファイル１*/
    private String t4nFile1;
    
    /** 添付ファイル２*/
    private String t4nFile2;
    
    /** 添付ファイル３*/
    private String t4nFile3;
    
    /** 資料種別１*/
    private String t4nCat1;
    
    /** 添付ファイルコメント１*/
    private String t4nFileCom1;
    
    /** 添付ファイルコメント２*/
    private String t4nFileCom2;
    
    /** 添付ファイルコメント３*/
    private String t4nFileCom3;
    
    /** URL*/
    private String t4nUrl;
    
    /** URLコメント*/
    private String corUrlCom;
    
    /** 参照範囲*/
    private String corReferenceCondition;
    
    /** ディスクレーマー*/
    private String corDisclaimer;
    
    /** 既読管理フラグ*/
    private String corReadFlg;
    
    /** 登録者*/
    private String corCreateBy;
    
    /** 更新者*/
    private String corUpdateBy;
    
}
