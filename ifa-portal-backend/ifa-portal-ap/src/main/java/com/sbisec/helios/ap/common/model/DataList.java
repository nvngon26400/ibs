package com.sbisec.helios.ap.common.model;

import java.util.List;

@SuppressWarnings("serial")
public class DataList<T> extends com.sbibits.earth.model.DataList<T> {

//	private Class<?> clazz;
//
////	public DataList() {
////		
////		
////	}
//	public DataList(T... param) {
//		clazz = param.getClass().getComponentType();
//	}
//
//	public Class getComponentClass() {
//		return clazz;
//	}

	public void setDataListFromObject(List<Object> dataList) {
		this.dataList = (List<T>) dataList;
	}
}
