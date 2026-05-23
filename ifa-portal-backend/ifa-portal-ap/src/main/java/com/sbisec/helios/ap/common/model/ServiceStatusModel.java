package com.sbisec.helios.ap.common.model;

import com.sbibits.earth.model.ModelBase;

public class ServiceStatusModel extends ModelBase {

	private static final long serialVersionUID = 5556052030869728500L;

	private String serviceId;
	//private String groupId;
	private String serviceStatus;
	/**
	 * serviceIdを取得します。
	 * @return serviceId
	 */
	public String getServiceId() {
	    return serviceId;
	}
	/**
	 * serviceIdを設定します。
	 * @param serviceId serviceId
	 */
	public void setServiceId(String serviceId) {
	    this.serviceId = serviceId;
	}
//	/**
//	 * groupIdを取得します。
//	 * @return groupId
//	 */
//	public String getGroupId() {
//	    return groupId;
//	}
//	/**
//	 * groupIdを設定します。
//	 * @param groupId groupId
//	 */
//	public void setGroupId(String groupId) {
//	    this.groupId = groupId;
//	}
	/**
	 * serviceStatusを取得します。
	 * @return serviceStatus
	 */
	public String getServiceStatus() {
	    return serviceStatus;
	}
	/**
	 * serviceStatusを設定します。
	 * @param serviceStatus serviceStatus
	 */
	public void setServiceStatus(String serviceStatus) {
	    this.serviceStatus = serviceStatus;
	}

}
