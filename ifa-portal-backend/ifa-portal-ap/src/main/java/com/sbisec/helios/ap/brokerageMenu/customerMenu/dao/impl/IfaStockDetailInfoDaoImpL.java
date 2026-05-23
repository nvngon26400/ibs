package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaStockDetailInfoDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaStockDetailInfoMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql002ResponseModel;

/**
 * 画面ID：SUB0202_0208-02 画面名：株式詳細情報
 * 
 * @author <author-name>
 *
 *         2023/07/31 新規作成
 */
@Component
public class IfaStockDetailInfoDaoImpL extends RowSelectableDao implements IfaStockDetailInfoDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(IfaStockDetailInfoDaoImpL.class);
	@Autowired
	private IfaStockDetailInfoMapper mapper;

	/**
	 * SQLID：Sql001 SQL名：指定銘柄銘柄名取得 SQLタイプ：select
	 * リクエストクラス：IfaStockDetailInfoSql001RequestModel
	 * レスポンスクラス：IfaStockDetailInfoSql001ResponseModel
	 * 
	 * @param <paramName> <description of param value>
	 * @return <description of return value>
	 * @exception <exceptionName> <description>
	 * @see <reference item>
	 */
	public DataList<IfaStockDetailInfoSql001ResponseModel> selectIfaStockDetailInfoSql001(
			IfaStockDetailInfoSql001RequestModel req) throws Exception {

		DataList<IfaStockDetailInfoSql001ResponseModel> res = new DataList<IfaStockDetailInfoSql001ResponseModel>();
		res.setDataList(mapper.selectIfaStockDetailInfoSql001(req));
		return res;
	}

	/**
	 * SQLID：Sql002 SQL名：株式詳細情報取得 SQLタイプ：select
	 * リクエストクラス：IfaStockDetailInfoSql002RequestModel
	 * レスポンスクラス：IfaStockDetailInfoSql002ResponseModel
	 * 
	 * @param <paramName> <description of param value>
	 * @return <description of return value>
	 * @exception <exceptionName> <description>
	 * @see <reference item>
	 */
	public DataList<IfaStockDetailInfoSql002ResponseModel> selectIfaStockDetailInfoSql002(
			IfaStockDetailInfoSql002RequestModel req) throws Exception {

		DataList<IfaStockDetailInfoSql002ResponseModel> res = new DataList<IfaStockDetailInfoSql002ResponseModel>();
		res.setDataList(mapper.selectIfaStockDetailInfoSql002(req));
		return res;
	}

}
