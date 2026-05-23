package com.sbisec.helios.ap.wholePortal.psite.model;

import com.sbibits.earth.model.ModelBase;

public class GetTbMedDisclaimerModel extends ModelBase {
	private String disclaimerId;
	private String contentId;
	private String itemName;
	private String title;
	private String content;
	/**
	 * disclaimerIdを取得します。
	 * @return disclaimerId
	 */
	public String getDisclaimerId() {
	    return disclaimerId;
	}
	/**
	 * disclaimerIdを設定します。
	 * @param disclaimerId disclaimerId
	 */
	public void setDisclaimerId(String disclaimerId) {
	    this.disclaimerId = disclaimerId;
	}
	/**
	 * contentIdを取得します。
	 * @return contentId
	 */
	public String getContentId() {
	    return contentId;
	}
	/**
	 * contentIdを設定します。
	 * @param contentId contentId
	 */
	public void setContentId(String contentId) {
	    this.contentId = contentId;
	}
	/**
	 * itemNameを取得します。
	 * @return itemName
	 */
	public String getItemName() {
	    return itemName;
	}
	/**
	 * itemNameを設定します。
	 * @param itemName itemName
	 */
	public void setItemName(String itemName) {
	    this.itemName = itemName;
	}
	/**
	 * titleを取得します。
	 * @return title
	 */
	public String getTitle() {
	    return title;
	}
	/**
	 * titleを設定します。
	 * @param title title
	 */
	public void setTitle(String title) {
	    this.title = title;
	}
	/**
	 * contentを取得します。
	 * @return content
	 */
	public String getContent() {
	    return content;
	}
	/**
	 * contentを設定します。
	 * @param content content
	 */
	public void setContent(String content) {
	    this.content = content;
	}

}
