package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.common.Page;

/**
 * 外国株式取引サービス - 外国株式信用売建可能銘柄一覧取得API.
 * 
 * @author mengzhe.li
 * @date: 03/09/2022
 */
public class ListShortableStocksReq implements BaseRequest {

	public ListShortableStocksReq() {
	    
	}

	// headerとparameterインスタンス化
	private Header header = new Header();
	private Parameter parameter = new Parameter();

	@SuppressWarnings("unchecked")
	@Override
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public class Header {

	}

	public class Parameter {
		// 国コード
		private String countryCode;
		// 検索用キーワード（最大 384 バイト、大体 128 つ UTF-8 文字に相当する）
		private String searchKeyword;
		// 銘柄信用売建可能
		private String tradeStatus;
		// ページ
		private Page page;

		public String getCountryCode() {
			return countryCode;
		}
		/**
		 * @param searchKeyword 国コード
		 */
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String getSearchKeyword() {
			return searchKeyword;
		}
		/**
		 * @param searchKeyword 検索用キーワード
		 */
		public void setSearchKeyword(String searchKeyword) {
			this.searchKeyword = searchKeyword;
		}

		public String getTradeStatus() {
			return tradeStatus;
		}
		/**
		 * @param tradeStatus 銘柄信用売建可能
		 */
		public void setTradeStatus(String tradeStatus) {
			this.tradeStatus = tradeStatus;
		}

		public Page getPage() {
			return page;
		}
		/**
		 * @param page ページ
		 */
		public void setPage(Page page) {
			this.page = page;
		}

	}
}
