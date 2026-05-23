package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.ArrayList;
import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaContactBigClass;

import lombok.Data;

/**
 * 接触履歴 A002リクエストDto
 * 
 * @author 趙韫慧
 *
 */
@Data
public class IfaContactA002RequestDto {

	/** 接触履歴大分類 */
	private List<IfaContactBigClass> bigClassList = new ArrayList<IfaContactBigClass>();

}
