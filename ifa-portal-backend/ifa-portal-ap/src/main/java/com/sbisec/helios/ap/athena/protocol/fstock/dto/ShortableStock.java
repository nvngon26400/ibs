package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import java.io.Serializable;

/**
 * 外国株式信用売建可能銘柄情報.
 * 
 * @author mengzhe.li
 * @date 03/09/2022
 */
public class ShortableStock implements Serializable {

	private static final long serialVersionUID = -703978556360571658L;

	public ShortableStock() {
	}

	// 外国株式銘柄情報
	private ForeignStock foreignStock;
	// 売建受注枠
	private String tradeMark;
	// 在庫株数
	private String positionQuantity;

	public ForeignStock getForeignStock() {
		return foreignStock;
	}
	public void setForeignStock(ForeignStock foreignStock) {
		this.foreignStock = foreignStock;
	}
	public String getTradeMark() {
		return tradeMark;
	}
	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}
	public String getPositionQuantity() {
		return positionQuantity;
	}
	public void setPositionQuantity(String positionQuantity) {
		this.positionQuantity = positionQuantity;
	}
}
