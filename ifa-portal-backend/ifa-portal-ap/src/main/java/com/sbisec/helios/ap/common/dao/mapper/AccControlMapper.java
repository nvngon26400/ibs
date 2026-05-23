package com.sbisec.helios.ap.common.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.common.model.AccControl;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccControlMapper {

	public List<AccControl> getAccControl(@Param("userId") String userId) throws Exception;

}
