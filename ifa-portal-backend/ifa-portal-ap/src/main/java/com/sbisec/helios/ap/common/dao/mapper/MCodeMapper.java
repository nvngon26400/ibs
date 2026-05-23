package com.sbisec.helios.ap.common.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.common.model.MCode;

import org.apache.ibatis.annotations.Mapper;

/**
 * コードマスタMapper
 *
 * @author SCSK
 *
 */
@Mapper
public interface MCodeMapper {
    
    /**
     * コードマスタリスト取得処理
     *
     * @return コードマスタリスト
     * @throws Exception 例外
     */
    public List<MCode> getMCodeList() throws Exception;
}
