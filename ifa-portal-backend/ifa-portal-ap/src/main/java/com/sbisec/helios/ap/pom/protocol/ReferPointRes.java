package com.sbisec.helios.ap.pom.protocol;

import java.util.List;

import lombok.Data;

/** 
 * ポイント残高照会応答
 *
 */
@Data
public class ReferPointRes {
    
    /** 
     * 期間固定ポイント情報Item
     * */
    @Data
    public static class RestrictPointGroupItem {
        
        private String restrictPointId;
        
        private String restrictPoint;
        
        private String restrictDate;
        
    }
    
    /**
     * ポイントアカウント
     *
     */
    @Data
    public static class PointAccount {
        
        // ポイント区分
        private String pointType;
        
        // 会員ID
        private String memberId;
        
        // 会員ステータス
        private String state;
    }
    
    // 結果
    private String result;
    
    // 部店コード
    private String butenCode;
    
    // 口座番号
    private String accountNo;
    
    // 連携区分
    private String dataType;
    
    // ポイントアカウント
    private PointAccount pointAccount;
    
    // 利用可能ポイント数
    private Integer availablePoint;
    
    // ポイント交換比率
    private String pointRate;
    
    // 最低利用ポイント数
    private Integer minimumUsePoint;
    
    // 利用ポイント単位
    private String usePointUnit;
    
    // クレジットカードフラグ
    private String creditFlag;
    
    // ポイント有効期限
    private String expiredDate;
    
    // 期間固定情報：期間固定ポイント数合計
    private Integer restrictPointSum;
    
    // 期間固定情報：期間固定ポイント件数
    private String restrictPointCount;
    
    // 期間固定ポイント情報
    private List<RestrictPointGroupItem> restrictPointGroup;
    
    // 結果区分
    private String resultType;
    
    // 結果コード
    private String resultCode;
    
    // ポイント照会可能フラグ
    private String referPointFlag;
    
    // ポイント利用可能フラグ
    private String usePointFlag;
}
