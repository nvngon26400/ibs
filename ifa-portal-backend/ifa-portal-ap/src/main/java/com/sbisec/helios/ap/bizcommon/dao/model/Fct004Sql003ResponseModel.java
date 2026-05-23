package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 店頭取引注文情報の取得レスポンス
 * @author SCSK
 *
 */
@Data
public class Fct004Sql003ResponseModel extends ModelBase {
    
    // 管理番号
    private String orderNo;
    
    // 介業者コード
    private String brokerCode;
    
    // 銘柄コード
    private String brandCode;
    
    // 部店
    private String butenCode;
    
    // 預り区分
    private String depositType;
    
    // 口座番号
    private int accountNumber;
    
    // 注文数量
    private Long orderCount;
    
    // ステータス
    private String orderStatus;
    
    // 売買区分
    private String tradeType;
    
    // 約定金額
    private BigDecimal appointPrice;
    
}
