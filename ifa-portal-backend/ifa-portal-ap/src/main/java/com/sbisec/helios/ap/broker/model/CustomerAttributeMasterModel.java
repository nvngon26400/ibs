package com.sbisec.helios.ap.broker.model;

import com.sbibits.earth.model.ModelBase;

/**
 * CustomerAttributeMasterModel
 * 
 * @author xin.huang
 *
 */
public class CustomerAttributeMasterModel extends ModelBase {

	private static final long serialVersionUID = 1L;
	private String customerAttribute;
	private String customerAttributeName;
	private Integer course;

	/**
	 * customerAttributeを取得します。
	 * 
	 * @return customerAttribute
	 */
	public String getCustomerAttribute() {
		return customerAttribute;
	}

	/**
	 * customerAttributeを設定します。
	 * 
	 * @param customerAttribute customerAttribute
	 */
	public void setCustomerAttribute(String customerAttribute) {
		this.customerAttribute = customerAttribute;
	}

	/**
	 * customerAttributeNameを取得します。
	 * 
	 * @return customerAttributeName
	 */
	public String getCustomerAttributeName() {
		return customerAttributeName;
	}

	/**
	 * customerAttributeNameを設定します。
	 * 
	 * @param customerAttributeName customerAttributeName
	 */
	public void setCustomerAttributeName(String customerAttributeName) {
		this.customerAttributeName = customerAttributeName;
	}

	/**
	 * courseを取得します。
	 * 
	 * @return course
	 */
	public Integer getCourse() {
		return course;
	}

	/**
	 * courseを設定します。
	 * 
	 * @param course course
	 */
	public void setCourse(Integer course) {
		this.course = course;
	}

}
