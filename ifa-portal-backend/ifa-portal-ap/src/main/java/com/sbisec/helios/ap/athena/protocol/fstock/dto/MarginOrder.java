package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 信用注文情報 Dto.
 * 
 * @author mengzhe.li
 * @date 02/10/2022
 */
@Data
public class MarginOrder implements Serializable {
    
    private static final long serialVersionUID = -6606687693974346972L;
    
    private Order order;// 注文情報
    
    private String closePositionKind;// 返済建玉指定方法
    
    private String closeSelectionSort;// 返済選択順序
    
    private String marginCloseLimitType;// 信用期日
    
    private String closedProfitLoss;// 決済損益
    
    private String marginDeficitAmount;// 保証金不足金額
    
    private String transferDepositAmount;// 振替預り金額
    
    private String transferEvaluationAmount;// 振替有価証券評価額
    
}
