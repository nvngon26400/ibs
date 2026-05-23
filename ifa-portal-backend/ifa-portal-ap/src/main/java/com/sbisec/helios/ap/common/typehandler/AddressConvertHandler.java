package com.sbisec.helios.ap.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbibits.earth.util.Cp932;

@MappedJdbcTypes({ JdbcType.VARCHAR, JdbcType.CHAR })
public class AddressConvertHandler extends BaseTypeHandler<String> {

	private static final Logger logger = LoggerFactory.getLogger(AddressConvertHandler.class);

	public AddressConvertHandler() {

		super();

		if (logger.isDebugEnabled())
			logger.debug("Create new instance of addressConvertHandler:[" + hashCode() + "]");
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, Cp932.toJIS(parameter));
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String address = Cp932.toCp932(rs.getString(columnName));
		if (address != null) {
			return address.replaceAll("＿", "－");
		} else {
			return address;
		}
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String address = Cp932.toCp932(rs.getString(columnIndex));
		if (address != null) {
			return address.replaceAll("＿", "－");
		} else {
			return address;
		}
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String address = Cp932.toCp932(cs.getString(columnIndex));
		if (address != null) {
			return address.replaceAll("＿", "－");
		} else {
			return address;
		}
	}
}
