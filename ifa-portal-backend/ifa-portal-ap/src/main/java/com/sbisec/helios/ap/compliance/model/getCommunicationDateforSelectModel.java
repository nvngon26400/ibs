
package com.sbisec.helios.ap.compliance.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class getCommunicationDateforSelectModel extends ModelBase {

	private String corLecId;
	private String corCommunicationDate;

	/**
	 * corLecIdを取得します。
	 * @return corLecId
	 */
	public String getCorLecId() {
	    return corLecId;
	}
	/**
	 * corLecIdを設定します。
	 * @param corLecId corLecId
	 */
	public void setCorLecId(String corLecId) {
	    this.corLecId = corLecId;
	}
	/**
	 * corCommunicationDateを取得します。
	 * @return corCommunicationDate
	 */
	public String getCorCommunicationDate() {
	    return corCommunicationDate;
	}
	/**
	 * corCommunicationDateを設定します。
	 * @param corCommunicationDate corCommunicationDate
	 */
	public void setCorCommunicationDate(String corCommunicationDate) {
	    this.corCommunicationDate = corCommunicationDate;
	}
}