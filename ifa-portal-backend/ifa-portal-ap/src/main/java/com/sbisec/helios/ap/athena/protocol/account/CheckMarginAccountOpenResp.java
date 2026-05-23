package com.sbisec.helios.ap.athena.protocol.account;

/**
 * 
 * 信用口座開設判定 Response
 * 
 * @author shuchen.xin
 * @date: 02/15/2022
 */
public class CheckMarginAccountOpenResp {

	public CheckMarginAccountOpenResp() {
	}

	// 信用口座開設状態。
    private Boolean opened;
    
    // 【区分値変換後】信用口座開設状態
    private String convOpened;

	/**
	 * @return 信用口座開設状態。true：信用口座開設済/false：信用口座未開設
	 */
    public Boolean getOpened() {
		return opened;
	}

	/**
	 * @param opened the opened to set
	 */
    public void setOpened(Boolean opened) {
		this.opened = opened;
	}

    public String getConvOpened() {
        
        return convOpened;
    }
    
    public void setConvOpened(String convOpened) {
        
        this.convOpened = convOpened;
    }
    
}
