package com.sbisec.helios.ap.athena.protocol.common;

import java.io.Serializable;

import lombok.Data;

/**
 * 買付余力
 *
 * @author SCSK 宇田川達弥
 *
 */
@Data
public class BuyPossibleAmount implements Serializable {
    
    private static final long serialVersionUID = -5220131114278690953L;
    
    // 買付余力（払出制限なし）
    private String noRestricted;
    
    // 買付余力（払出制限あり）
    private String restricted;
}
