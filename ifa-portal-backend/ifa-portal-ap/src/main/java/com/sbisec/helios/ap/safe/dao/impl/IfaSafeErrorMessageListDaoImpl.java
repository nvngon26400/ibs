package com.sbisec.helios.ap.safe.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.safe.dao.IfaSafeErrorMessageListDao;
import com.sbisec.helios.ap.safe.dao.mapper.IfaSafeErrorMessageListMapper;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001RequestModel;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001ResponseModel;


/**
 * 共通：異常処理
 * 
 * @author rongsheng.he
 *
 * 		   2025/04/22 新規作成
 */
@Component
public class IfaSafeErrorMessageListDaoImpl extends RowSelectableDao implements IfaSafeErrorMessageListDao {
	
	@Autowired 
	private IfaSafeErrorMessageListMapper mapper;
	
	public IfaSafeErrorMessageSql001ResponseModel selectIfaSafeErrorMessageSql001(IfaSafeErrorMessageSql001RequestModel req){
		
		return  mapper.selectIfaSafeErrorMessageSql001(req);
	}
}