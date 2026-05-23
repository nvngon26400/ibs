package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import lombok.Data;

/**
 * 買付余力.
 * 
 * @author SCSK 福岡　利基
 *
 */
@Data
public class BuyPossibleAmount {
    
    /** 買付余力（払出制限なし） */
    private String noRestricted;
    
    /** 買付余力（払出制限あり） */
    private String Restricted;
 
}
