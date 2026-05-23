package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.SecuritiesBalances;
import com.sbisec.helios.ap.athena.protocol.common.Page;

/**
 * 円貨金銭残高スケジュール Response
 *
 * @author xiu.yan
 * @version 1.0
 * @date 2/15/2022
 */
public class ListSecuritiesBalancesResp {

	public ListSecuritiesBalancesResp() {
	}

	// 商品保有証券資産情報
	private List<SecuritiesBalances> securitiesBalances;
	// ページング
	private Page page;

	/**
	 * @return 商品保有証券資産情報
	 */
	public List<SecuritiesBalances> getSecuritiesBalances() {
		return securitiesBalances;
	}

	public void setSecuritiesBalances(List<SecuritiesBalances> securitiesBalances) {
		this.securitiesBalances = securitiesBalances;
	}

	/**
	 * @return ページング
	 */
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}