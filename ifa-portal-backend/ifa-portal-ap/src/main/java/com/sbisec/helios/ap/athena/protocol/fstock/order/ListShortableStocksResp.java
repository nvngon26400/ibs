package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.Page;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ShortableStock;


/**
 * 外国株式取引サービス - 外国株式信用売建可能銘柄一覧取得API Response.
 * 
 * @author mengzhe.li
 * @date: 03/09/2022
 */
public class ListShortableStocksResp {

	public ListShortableStocksResp() {
	}

	// 外国株式信用売建可能銘柄情報
	private List<ShortableStock> shortableStocks;
	// ページ
	private Page page;

	/**
	 * @return 外国株式信用売建可能銘柄情報
	 */
	public List<ShortableStock> getShortableStocks() {
		return shortableStocks;
	}

	public void setShortableStocks(List<ShortableStock> shortableStocks) {
		this.shortableStocks = shortableStocks;
	}

	/**
	 * @return ページ
	 */
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
